package org.somevand.swt.adapters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConnectionHelper {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static volatile ConnectionHelper instance;
    private static final ReadWriteLock instanceLock = new ReentrantReadWriteLock();

    private final String url;
    private final String user;
    private final String password;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public ConnectionHelper() {
        this.url = "jdbc:mysql://localhost:3306/swt";
        this.user = "swt_operator";
        this.password = "";
    }
    private ConnectionHelper(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public static ConnectionHelper getInstance() {
        instanceLock.readLock().lock();
        try {
            if (instance == null) {
                instance = new ConnectionHelper();
            }
            return instance;
        } finally {
            instanceLock.readLock().unlock();
        }
    }

    public static void setInstance(ConnectionHelper newInstance) {
        instanceLock.writeLock().lock();
        try {
            instance = newInstance;
        } finally {
            instanceLock.writeLock().unlock();
        }
    }

    public ReadWriteLock getLock() {
        return lock;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
