package inventory;

import Database.InvDatabase;
import Database.InventoryDatabase;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InventoryApplicationListener implements ServletContextListener {
    public InventoryApplicationListener() {
        super();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println(" hi ur in context initialization");
        ServletContext context = sce.getServletContext();
        String dburl = context.getInitParameter("dbUrl");
        String dbusername = context.getInitParameter("dbUserName");
        String dbpassword = context.getInitParameter("dbPassword");
        String dbdriver = context.getInitParameter("dbDriver");
        InventoryDatabase db = new InventoryDatabase();

        System.out.println("Connection Establised.........");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println(" hi ur in context destroy");
    }
}
