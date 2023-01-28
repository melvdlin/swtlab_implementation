package org.somevand.swt.adapters;

import org.somevand.swt.model.CustomerAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public enum CustomerAccountAdapter implements ICustomerAccount {
    INSTANCE;

    private static class SQLStrings {
        public static final String queryCA = "SELECT * FROM swt.customeraccounts WHERE email = ?;";
        public static final String updateCA = "INSERT INTO swt.customeraccounts (email, password) VALUES (?, ?);";
        public static final String emailCol = "email";
        public static final String passwordCol = "password";
    }

    @Override
    public Optional<CustomerAccount> getCustomerAccount(String email) throws SQLException {

        ConnectionHelper.getLock().readLock().lock();
        try (Connection connection = ConnectionHelper.getConnection();
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
        } finally {
            ConnectionHelper.getLock().readLock().unlock();
        }
    }

    @Override
    public void addCustomerAccount(String email, String password) throws SQLException {

        ConnectionHelper.getLock().writeLock().lock();
        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStrings.updateCA)
        ) {
            statement.setString(1, email);
            statement.setString(2, password);
            statement.executeUpdate();
        } finally {
            ConnectionHelper.getLock().writeLock().unlock();
        }
    }
}
