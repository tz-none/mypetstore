package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewListOrdersServlet extends HttpServlet {
    private static final String LISTORDERS = "/WEB-INF/jsp/order/ListOrders.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        OrderService service = new OrderService();
        List<Order> orderList = service.getOrdersByUsername(account.getUsername());
        session.setAttribute("orderList", orderList);
        request.getRequestDispatcher(LISTORDERS).forward(request, response);
    }
}
