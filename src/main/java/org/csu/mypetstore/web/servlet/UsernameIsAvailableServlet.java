package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UsernameIsAvailableServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        AccountService service = new AccountService();
        Account account = service.getAccountByUsername(username);

        response.setContentType("text/xml");
        PrintWriter out = response.getWriter();

        if(username=="" || account!=null) {
            out.println("<msg>NotAvailable</msg>");
        }else {
            out.println("<msg>Available</msg>");
        }
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
