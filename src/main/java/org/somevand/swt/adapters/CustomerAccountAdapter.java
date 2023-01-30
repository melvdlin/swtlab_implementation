package org.somevand.swt.adapters;

import org.somevand.swt.model.CustomerAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;

public class CustomerAccountAdapter implements ICustomerAccount {
    private static volatile CustomerAccountAdapter instance;

    private static class SQLStrings {
        public static final String queryCA = "SELECT * FROM customeraccounts WHERE email = ?;";
        public static final String updateCA = "INSERT INTO customeraccounts (email, password) VALUES (?, ?);";
        public static final String emailCol = "email";
        public static final String passwordCol = "password";
    }

    private CustomerAccountAdapter() { }

    public static CustomerAccountAdapter getInstance() {
        if (instance == null) {
            synchronized (CustomerAccountAdapter.class) {
                if (instance == null) {
                    instance = new CustomerAccountAdapter();
                }
            }
        }
        return instance;
    }

    @Override
    public Optional<CustomerAccount> getCustomerAccount(String email) throws SQLException {

        try (Connection connection = ConnectionHelper.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStrings.queryCA)
        ) {
            statement.setString(1, email);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                return Optional.of(
                        new CustomerAccount(
                                res.getString(SQLStrings.emailCol),
                                res.getString(SQLStrings.passwordCol)));
            }
            return Optional.empty();
        }
    }

    @Override
    public void addCustomerAccount(String email, String password) throws SQLException {

        try (Connection connection = ConnectionHelper.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStrings.updateCA)
        ) {
            statement.setString(1, email);
            statement.setString(2, password);
            statement.executeUpdate();
        }
    }

    @Override
    public ReadWriteLock getLock() {
        return ConnectionHelper.getInstance().getLock();
    }
}
