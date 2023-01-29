package org.somevand.swt.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

@WebListener
public class StartupListener implements ServletContextListener {

    public StartupListener() { }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        System.out.println("I'm alive!");
        for (var reg :
             sce.getServletContext().getServletRegistrations().values()) {
            System.out.printf(
                    "%s%n%s%n%s%n%n",
                    reg.getName(),
                    reg.getClassName(),
                    reg.getMappings());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }
}
