package org.csu.mypetstore.web.servlet;

import org.apache.log4j.Logger;
import org.csu.mypetstore.domain.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignoffServlet extends HttpServlet {
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Account account = (Account) session.getAttribute("account");
        if(account != null) {
            Logger logger = Logger.getLogger(SignoffServlet.class);
            logger.info(account.getUsername() + "用户下线");
        }

        session.removeAttribute("account");
        request.getRequestDispatcher(MAIN).forward(request, response);
    }
}
