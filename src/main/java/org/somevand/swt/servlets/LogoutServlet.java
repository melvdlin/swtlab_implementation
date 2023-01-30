package org.somevand.swt.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.somevand.swt.model.CustomerAccount;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "LogoutServlet", value = { "/logout" })
public class LogoutServlet extends HttpServlet {

    private static final String defaultPageUri = "/default";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,
            IOException {
        Optional<CustomerAccount> user = AttributeHelper.extract(
                request.getSession(),
                AttributeHelper.Names.Attributes.user,
                AttributeHelper.Types.user);
        if (user.isPresent()) {
            request.getSession().removeAttribute(AttributeHelper.Names.Attributes.user);
            request.getSession().removeAttribute(AttributeHelper.Names.Attributes.userName);
        }
        request.getRequestDispatcher(defaultPageUri).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,
            IOException {
        doGet(request, response);
    }
}
