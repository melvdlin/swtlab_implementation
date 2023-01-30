package org.somevand.swt.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.somevand.swt.application.UDEKApplication;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet(name = "RegisterServlet", value = { "/register" })
public class RegisterServlet extends HttpServlet {


    private static final String defaultPageUri = "/default";
    private static final String successPageUri = "/templates/registrationSuccess.jsp";
    private static final String failedPageUri = "/templates/registrationFailed.jsp";

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
                AttributeHelper.Names.Parameters.registerEmail);
        Optional<String> password = AttributeHelper.extract(
                request,
                AttributeHelper.Names.Parameters.registerPassword);

        if (email.isPresent() && password.isPresent()) {
            try {
                success = UDEKApplication.getInstance().forwardSubmitRegistration(
                        email.get(), password.get());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String forwardTo;
        if (success) {
            forwardTo = successPageUri;
        } else {
            forwardTo = failedPageUri;
        }
        request.getRequestDispatcher(forwardTo).forward(request, response);
    }
}
