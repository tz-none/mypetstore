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
import java.io.PrintWriter;
import java.util.List;

public class ComputeCartItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        Cart cart = (Cart) session.getAttribute("cart");
        List<CartItem> cartItemList = cart.getCartItemList();
        CatalogService service = new CatalogService();

        StringBuffer stringBuffer = new StringBuffer("{\"items\" : [");
        for(CartItem cartItem : cartItemList) {
            String itemId = cartItem.getItem().getItemId();
            String quantity = request.getParameter(itemId);
            cartItem.setQuantity(Integer.parseInt(quantity));
            service.updateCart(cartItem, account);
            stringBuffer.append("{\"itemId\" : \"" + itemId + "\",\"quantity\" : \"" + quantity + "\",\"price\" : \"" + cartItem.getItem().getListPrice() + "\"},");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        stringBuffer.append("],\"subTotal\" : \""+ cart.getSubTotal() +"\"}");

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(stringBuffer.toString());
        out.flush();
        out.close();
    }
}
