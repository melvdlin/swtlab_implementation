package org.somevand.swt.servlets;

import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class AttributeHelper {
    private static class AttributeNames {
        public static final String user = "user";
    }

    private AttributeHelper() { }

    public static Optional<String> getUser(HttpSession session) {
        if (session.getAttribute(AttributeNames.user) instanceof String user) {
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    public static void setUser(HttpSession session, String user) {
        session.setAttribute(AttributeNames.user, user);
    }
}
