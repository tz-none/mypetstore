package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CompleteSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        CatalogService service = new CatalogService();
        List<Product> productList = service.searchProductList(keyword);

        response.setContentType("text/xml");
        PrintWriter out = response.getWriter();
        out.println("<productList>");
        for(Product product : productList) {
            out.println("<product>"+product.getName()+"</product>");
        }
        out.println("</productList>");
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
