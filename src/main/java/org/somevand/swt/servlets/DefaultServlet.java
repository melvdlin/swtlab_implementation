package org.somevand.swt.servlets;

import com.mysql.cj.jdbc.Driver;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.somevand.swt.adapters.CustomerAccountAdapter;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(name = "DefaultServlet", value = { "", "/index", "/default" })
public class DefaultServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getParameterNames().asIterator().forEachRemaining(System.out::println);
        System.out.println(request.getRequestURL());
        System.out.println(request.getRequestURI());
        request.getRequestDispatcher("/templates/defaultWebpage.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        doGet(request, response);
    }
}
