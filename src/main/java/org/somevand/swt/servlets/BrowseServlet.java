package org.somevand.swt.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.somevand.swt.application.UDEKApplication;
import org.somevand.swt.model.Showing;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "BrowseServlet", value = "/browse")
public class BrowseServlet extends HttpServlet {

    private static final String showingsPageUri = "/templates/showShowings.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,
            IOException {
        List<Showing> showings = List.of();
        try {
            showings = UDEKApplication.getInstance().forwardNSUBrowse();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute(AttributeHelper.Names.Attributes.showings, showings);
        request.getRequestDispatcher(showingsPageUri).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,
            IOException {
        doGet(request, response);
    }
}
