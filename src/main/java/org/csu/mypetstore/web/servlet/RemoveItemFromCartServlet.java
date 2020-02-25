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

public class RemoveItemFromCartServlet extends HttpServlet {
    private static final String CART = "/WEB-INF/jsp/cart/Cart.jsp";
    private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";

    private String workingItemId;
    private Cart cart;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        workingItemId = request.getParameter("workingItemId");

        CatalogService service = new CatalogService();
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        cart = (Cart) session.getAttribute("cart");

        service.removeCartItem(cart.getCartItemById(workingItemId), account);
        Item item = cart.removeItemById(workingItemId);
        if(item == null) {
            session.setAttribute("msg", "Attempted to remove null CartItem from Cart.");
            request.getRequestDispatcher(ERROR).forward(request, response);
        }
        Logger logger = Logger.getLogger(RemoveItemFromCartServlet.class);
        logger.info(account.getUsername() + "移除物品" + item.getItemId());

        request.getRequestDispatcher(CART).forward(request, response);
    }
}
