package org.csu.mypetstore.web.servlet;

import org.apache.log4j.Logger;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewItemServlet extends HttpServlet {
    private String itemId;
    private static final String ITEM = "/WEB-INF/jsp/catalog/Item.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        itemId = request.getParameter("itemId");

        CatalogService service = new CatalogService();
        Item item = service.getItem(itemId);

        HttpSession session = request.getSession();
        session.setAttribute("item", item);

        Account account = (Account) session.getAttribute("account");
        if(account != null) {
            Logger logger = Logger.getLogger(ViewCategoryServlet.class);
            logger.info(account.getUsername() + "浏览" + itemId);
        }

        request.getRequestDispatcher(ITEM).forward(request, response);
    }
}
