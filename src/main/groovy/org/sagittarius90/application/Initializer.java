package org.sagittarius90.application;

import org.sagittarius90.database.adapter.utils.PersistenceUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("####### NLC.contextInitialized #######");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        PersistenceUtil.closeEntityManagerFactory();
    }
}