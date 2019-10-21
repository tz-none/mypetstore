package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewOrderServlet extends HttpServlet {
    private static final String SHIPPINGFORM = "/WEB-INF/jsp/order/ShippingForm.jsp";
    private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/ConfirmOrder.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        order.setCardType(request.getParameter("cardType"));
        order.setCreditCard(request.getParameter("creditCard"));
        order.setExpiryDate(request.getParameter("expiryDate"));
        order.setBillToFirstName(request.getParameter("billToFirstName"));
        order.setBillToLastName(request.getParameter("billToLastName"));
        order.setBillAddress1(request.getParameter("billAddress1"));
        order.setBillAddress2(request.getParameter("billAddress2"));
        order.setBillCity(request.getParameter("billCity"));
        order.setBillState(request.getParameter("billState"));
        order.setBillZip(request.getParameter("billZip"));
        order.setBillCountry(request.getParameter("billCountry"));

        String shippingAddressRequired = request.getParameter("shippingAddressRequired");
        if(shippingAddressRequired!=null && shippingAddressRequired.equals("1")) {
            request.getRequestDispatcher(SHIPPINGFORM).forward(request, response);
        } else {
            request.getRequestDispatcher(CONFIRM_ORDER).forward(request, response);
        }
    }
}
