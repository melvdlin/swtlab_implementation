package org.somevand.swt.adapters;

import org.somevand.swt.model.CustomerAccount;

import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;

public interface ICustomerAccount {
    Optional<CustomerAccount> getCustomerAccount(String email) throws SQLException;
    void addCustomerAccount(String email, String password) throws SQLException;
    ReadWriteLock getLock();
}
