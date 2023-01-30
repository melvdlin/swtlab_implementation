package org.somevand.swt.servlets;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.somevand.swt.model.CustomerAccount;
import org.somevand.swt.model.Showing;

import java.util.List;
import java.util.Optional;

class AttributeHelper {
    public static class Names {
        public static class Attributes {
            public static final String user = "user";
            public static final String userName = "userName";
            public static final String loginFailed = "loginFailed";
            public static final String showings = "showings";
        }

        public static class Parameters {
            public static final String action = "action";
            public static final String loginEmail = "email";
            public static final String loginPassword = "password";
            public static final String registerEmail = "email";
            public static final String registerPassword = "password";
        }
    }

    public static class Types {
        public static final Class<CustomerAccount> user = CustomerAccount.class;
        public static final Class<String> userName = String.class;
        public static final Class<Boolean> loginFailed = Boolean.class;
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
