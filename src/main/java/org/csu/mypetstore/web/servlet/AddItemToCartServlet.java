package org.csu.mypetstore.web.servlet;

import org.apache.log4j.Logger;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddItemToCartServlet extends HttpServlet {
    private static final String CART = "/WEB-INF/jsp/cart/Cart.jsp";
    private static final String SIGNON = "/WEB-INF/jsp/account/SignonForm.jsp";
    private String workingItemId;
    private Cart cart;
    private CatalogService service;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if(account == null) {
            request.getRequestDispatcher(SIGNON).forward(request, response);
            return;
        }

        workingItemId = request.getParameter("workingItemId");
        CatalogService service = new CatalogService();
        cart = (Cart)session.getAttribute("cart");
        if(cart == null) {
            cart = new Cart();
        }

        if(cart.containsItemId(workingItemId)) {
            cart.incrementQuantityByItemId(workingItemId);
            //更新购物车数据
            service.updateCart(cart.getCartItemById(workingItemId), (Account) session.getAttribute("account"));
        } else {
            service = new CatalogService();
            boolean inInStock = service.isItemInStock(workingItemId);
            Item item = service.getItem(workingItemId);
            cart.addItem(item, inInStock);
            //更新购物车数据
            service.insertCartItem(cart.getCartItemById(workingItemId), (Account) session.getAttribute("account"));
        }
        Logger logger = Logger.getLogger(AddItemToCartServlet.class);
        logger.info(account.getUsername() + "添加物品" + workingItemId);

        session.setAttribute("cart", cart);
        request.getRequestDispatcher(CART).forward(request, response);
    }
}
