package org.somevand.swt.servlets;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class AttributeHelper {
    public static class Names {
        public static class Attributes {
            public static final String user = "user";
        }

        public static class Parameters {
            public static final String action = "action";
        }
    }

    public static class Types {
        public static final Class<String> user = String.class;
    }

    private AttributeHelper() { }

    public static <T> Optional<T> optionalCast(Object value, Class<T> type) {
        try {
            return Optional.ofNullable(type.cast(value));
        } catch (ClassCastException ignored) {
            return Optional.empty();
        }
    }

    public static <T> Optional<T> extract(
            HttpSession session,
            String attributeName,
            Class<T> attributeType
    ) {
        return optionalCast(session.getAttribute(attributeName), attributeType);
    }

    public static <T> Optional<T> extract(
            HttpServletRequest request,
            String attributeName,
            Class<T> attributeType
    ) {
        return optionalCast(request.getAttribute(attributeName), attributeType);
    }

    public static Optional<String> extract(
            HttpServletRequest request,
            String parameterName
    ) {
        return Optional.ofNullable(request.getParameter(parameterName));
    }
}
