package org.somevand.swt.adapters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ConnectionHelper {

    private static final String url = "jdbc:mysql://localhost:3306";
    private static final String user = "swt_operator";
    private static final String password = "";

    private static final ReadWriteLock lock = new ReentrantReadWriteLock();

    private ConnectionHelper() { }

    public static ReadWriteLock getLock() {
        return lock;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
