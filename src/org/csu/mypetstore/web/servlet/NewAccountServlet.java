package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewAccountServlet extends HttpServlet {
    private static final String SIGNON = "/WEB-INF/jsp/account/SignonForm.jsp";
    private Account account;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        account = new Account();
        account.setUsername(request.getParameter("username"));
        if(request.getParameter("password").equals(request.getParameter("repeatedPassword")) && request.getParameter("password") != null) {
            account.setPassword(request.getParameter("password"));
        }
        if(request.getParameter("firstName") != null) {
            account.setFirstName(request.getParameter("firstName"));
        }

        if(request.getParameter("lastName") != null) {
            account.setLastName(request.getParameter("lastName"));
        }

        if(request.getParameter("email") != null) {
            account.setEmail(request.getParameter("email"));
        }

        if(request.getParameter("phone") != null) {
            account.setPhone(request.getParameter("phone"));
        }

        if(request.getParameter("address1") != null) {
            account.setAddress1(request.getParameter("address1"));
        }

        if(request.getParameter(request.getParameter("address2")) != null) {
            account.setAddress2(request.getParameter("address2"));
        }

        if(request.getParameter("city") != null) {
            account.setCity(request.getParameter("city"));
        }

        if(request.getParameter("state") != null) {
            account.setState(request.getParameter("state"));
        }

        if(request.getParameter("zip") != null) {
            account.setZip(request.getParameter("zip"));
        }

        if(request.getParameter("country") != null) {
            account.setCountry(request.getParameter("country"));
        }

        if(request.getParameter("languagePreference") != null) {
            account.setLanguagePreference(request.getParameter("languagePreference"));
        }

        if(request.getParameter("favouriteCategoryId") != null) {
            account.setFavouriteCategoryId(request.getParameter("favouriteCategoryId"));
        }

        if(request.getParameter("listOption") != null) {
            account.setListOption(request.getParameter("listOption").equals("1"));
        }

        if(request.getParameter("bannerOption") != null) {
            account.setListOption(request.getParameter("bannerOption").equals("1"));
        }

        AccountService service = new AccountService();
        service.insertAccount(account);

        request.getRequestDispatcher(SIGNON).forward(request, response);
    }
}
