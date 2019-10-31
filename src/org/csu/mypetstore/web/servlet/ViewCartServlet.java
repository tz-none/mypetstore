package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

public class ViewCartServlet extends HttpServlet {
    private static final String CART = "/WEB-INF/jsp/cart/Cart.jsp";
    private static final String ACCOUNT = "/WEB-INF/jsp/account/SignonForm.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if(account == null) {
            request.getRequestDispatcher(ACCOUNT).forward(request, response);
            return;
        }

        CatalogService service = new CatalogService();
        Cart cart = (Cart) service.getCartByUsername(account.getUsername());
        session.setAttribute("cart", cart);

        request.getRequestDispatcher(CART).forward(request, response);
    }
}
