package org.sagittarius90.application;

import org.sagittarius90.database.adapter.utils.PersistenceHelper;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PersistenceHelper.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        PersistenceHelper.commit();
    }
}