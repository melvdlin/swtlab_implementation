package org.somevand.swt.servlets;

import com.mysql.cj.jdbc.Driver;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class StartupListener implements ServletContextListener {

    private Driver mysqlDriver;

    public StartupListener() { }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        System.out.println("I'm alive!");
        try {
            mysqlDriver = new Driver();
            DriverManager.registerDriver(mysqlDriver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            DriverManager.deregisterDriver(mysqlDriver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
