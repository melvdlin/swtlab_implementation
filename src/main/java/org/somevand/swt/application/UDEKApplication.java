package org.somevand.swt.application;

import org.somevand.swt.adapters.CustomerAccountAdapter;
import org.somevand.swt.model.CustomerAccount;
import org.somevand.swt.model.Showing;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public enum UDEKApplication implements UserReqs, NSUReqs {
    INSTANCE;


    @Override
    public List<Showing> forwardNSUBrowse() {
        return null;
    }

    @Override
    public boolean forwardSubmitRegistration(String email, String password) throws SQLException {
        boolean result = false;
        if (CustomerAccountAdapter.INSTANCE.getCustomerAccount(email).isEmpty()) {
            CustomerAccountAdapter.INSTANCE.addCustomerAccount(email, password);
            result = true;
        }
        return result;
    }

    @Override
    public Optional<CustomerAccount> forwardSubmitLogin(String email, String password) throws SQLException {
        Optional<CustomerAccount> customer = CustomerAccountAdapter.INSTANCE.getCustomerAccount(email);
        return CustomerAccountAdapter.INSTANCE.getCustomerAccount(email);
    }
}
