package org.csu.mypetstore.web.servlet;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShippingFormServlet extends HttpServlet {
    private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/ConfirmOrder.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        order.setShipToFirstName(request.getParameter("shipToFirstName"));
        order.setShipToLastName(request.getParameter("shipToLastName"));
        order.setShipAddress1(request.getParameter("shipAddress1"));
        order.setShipAddress2(request.getParameter("shipAddress2"));
        order.setShipCity(request.getParameter("shipCity"));
        order.setShipState(request.getParameter("shipState"));
        order.setShipZip(request.getParameter("shipZip"));
        order.setShipCountry(request.getParameter("shipCountry"));

        session.setAttribute("order", order);
        request.getRequestDispatcher(CONFIRM_ORDER).forward(request, response);
    }
}
