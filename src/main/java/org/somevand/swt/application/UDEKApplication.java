package org.somevand.swt.application;

import org.somevand.swt.adapters.CustomerAccountAdapter;
import org.somevand.swt.adapters.ICustomerAccount;
import org.somevand.swt.adapters.IShowing;
import org.somevand.swt.adapters.ShowingAdapter;
import org.somevand.swt.model.CustomerAccount;
import org.somevand.swt.model.Showing;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;

public class UDEKApplication implements UserReqs, NSUReqs {
    private static volatile UDEKApplication instance;
    private final ICustomerAccount customerAccountAdapter;
    private final IShowing showingAdapter;

    private UDEKApplication(ICustomerAccount customerAccountAdapter, IShowing showingAdapter) {
        this.customerAccountAdapter = customerAccountAdapter;
        this.showingAdapter = showingAdapter;
    }

    public static UDEKApplication getInstance() {
        if (instance == null) {
            synchronized (UDEKApplication.class) {
                if (instance == null) {
                    instance = new UDEKApplication(CustomerAccountAdapter.getInstance(), ShowingAdapter.getInstance());
                }
            }
        }

        return instance;
    }

    @Override
    public List<Showing> forwardNSUBrowse() throws SQLException {

        final Lock lock = showingAdapter.getLock().readLock();

        lock.lock();
        try {
            return showingAdapter.getNonArchivedShowings();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean forwardSubmitRegistration(String email, String password) throws SQLException {

        final Lock lock = customerAccountAdapter.getLock().writeLock();
        boolean result = false;

        lock.lock();
        try {
            if (customerAccountAdapter.getCustomerAccount(email).isEmpty()) {
                customerAccountAdapter.addCustomerAccount(email, password);
                result = true;
            }
        } finally {
            lock.unlock();
        }

        return result;
    }

    @Override
    public Optional<CustomerAccount> forwardSubmitLogin(String email, String password) throws SQLException {

        final Lock lock = customerAccountAdapter.getLock().readLock();

        lock.lock();
        try {
            return customerAccountAdapter.getCustomerAccount(email);
        } finally {
            lock.unlock();
        }
    }
}
