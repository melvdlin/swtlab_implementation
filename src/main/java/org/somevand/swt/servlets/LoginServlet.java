package org.somevand.swt.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.somevand.swt.application.UDEKApplication;
import org.somevand.swt.model.CustomerAccount;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet(name = "LoginServlet", value =  { "/login" })
public class LoginServlet extends HttpServlet {

    private static final String defaultPageUri = "/default";

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher(defaultPageUri)
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        boolean success = false;
        Optional<String> email = AttributeHelper.extract(
                request,
                AttributeHelper.Names.Parameters.loginEmail);
        Optional<String> password = AttributeHelper.extract(
                request,
                AttributeHelper.Names.Parameters.loginPassword);

        if (email.isPresent() && password.isPresent()) {
            try {
                Optional<CustomerAccount> customer = UDEKApplication.getInstance().forwardSubmitLogin(
                        email.get(), password.get());
                if (customer.isPresent()) {
                    request.getSession().setAttribute(
                            AttributeHelper.Names.Attributes.user,
                            customer.get());
                    request.getSession().setAttribute(
                            AttributeHelper.Names.Attributes.userName,
                            customer.get().email());
                    success = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String forwardTo = defaultPageUri;
        if (!success) {
            request.setAttribute(AttributeHelper.Names.Attributes.loginFailed, true);
        }

        request.getRequestDispatcher(forwardTo).forward(request, response);
    }
}
