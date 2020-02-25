package org.csu.mypetstore.web.servlet;

import org.apache.log4j.Logger;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

public class ComfirmOrderServlet extends HttpServlet {
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        Order order = (Order) session.getAttribute("order");
        OrderService orderService = new OrderService();
        orderService.insertOrder(order);

        Cart cart = (Cart) session.getAttribute("cart");
        Iterator<CartItem> cartItems = cart.getAllCartItems();
        CatalogService catalogService = new CatalogService();
        while(cartItems.hasNext()) {
            CartItem cartItem = (CartItem) cartItems.next();
            catalogService.removeCartItem(cartItem, account);
        }

        if(account != null) {
            Logger logger = Logger.getLogger(ViewCategoryServlet.class);
            logger.info(account.getUsername() + "生成订单" + order.getOrderId());
        }

        request.getRequestDispatcher(VIEW_ORDER).forward(request, response);
    }
}
