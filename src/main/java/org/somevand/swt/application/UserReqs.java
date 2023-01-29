package org.somevand.swt.application;

import org.somevand.swt.model.CustomerAccount;

import java.sql.SQLException;
import java.util.Optional;

public interface UserReqs {
    boolean forwardSubmitRegistration(String email, String password) throws SQLException;
    Optional<CustomerAccount> forwardSubmitLogin(String email, String password) throws SQLException;
}
