package org.somevand.swt.servlets;

import freemarker.template.Configuration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class AttributeHelper {
    private static class AttributeNames {
        private static final String freemarkerConfig = "freemarkerConfig";
        public static final String user = "user";
    }

    private AttributeHelper() { }

    public static Optional<Configuration> getFreemarkerConfig(ServletContext context) {
        if (context.getAttribute(AttributeNames.freemarkerConfig) instanceof Configuration cfg) {
            return Optional.of(cfg);
        } else {
            return Optional.empty();
        }
    }

    public static void setFreemarkerConfig(ServletContext context, Configuration configuration) {
        context.setAttribute(AttributeNames.freemarkerConfig, configuration);
    }

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
