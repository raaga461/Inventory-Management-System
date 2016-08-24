package inventoryapplication;


import com.itextpdf.text.log.Logger;

import exceptions.InventoryException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author greenbuds
 */
public class InventoryApplicationListener implements ServletContextListener {
    public static String contextPath = "";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("the values are in servletcontextlistener cotext initialized");

        ServletContext context = servletContextEvent.getServletContext();
        contextPath = context.getRealPath("");
        String configFile =
            (new StringBuilder(String.valueOf(contextPath))).append(File.separator).append(context.getInitParameter("configFile")).toString();
        String bannerPath = "/resources/images/school.png";
        try {
            Properties prop = new Properties();
            FileInputStream fr = new FileInputStream(configFile);
            prop.load(fr);
            fr.close();
            //String logoPath = prop.getProperty("logoPath");
            String applicationTitle = prop.getProperty("applicationTitle");
            String DBDriver = prop.getProperty("DBDriver");
            String DBURL = prop.getProperty("DBURL");
            String DBUserName = prop.getProperty("DBUserName");
            String DBPassword = prop.getProperty("DBPassword");
            String provider = prop.getProperty("provider");

            String logoPath1 = "/resources/images/logo/";
            String studentsPhotoPath1 = "/resources/images/students/";
            String staffPhotoPath = "/resources/images/staff/";
            prop.setProperty("logoPath", logoPath1);
            prop.setProperty("bannerPath", bannerPath);
            prop.setProperty("studentsPhotoPath", studentsPhotoPath1);
            prop.setProperty("attachmentsPath", "/suport");
            String alertMessage = prop.getProperty("alertMessage");
            String backupsPath = "";
            if (System.getProperty("os.name").toLowerCase().contains("linux"))
            {
                backupsPath = File.separator + "home" + File.separator + "greenbud" + File.separator + "backups";
            }
            else
            {
                backupsPath = "C:" + File.separator + "backups";
            }
            prop.setProperty("backupsPath", backupsPath);
            try {
                FileOutputStream out = new FileOutputStream(configFile);
                prop.store(out, "Schoolapplication initialization properties");
                out.flush();
                out.close();
            } catch (Exception e) {
                System.out.println("Error While Writing Configuration parameters" + e.getMessage());
            }

            context.setAttribute("title", applicationTitle);
            context.setAttribute("DBDriver", DBDriver);
            context.setAttribute("DBURL", DBURL);
            context.setAttribute("DBUserName", DBUserName);
            context.setAttribute("DBPassword", DBPassword);
            context.setAttribute("logoPath", logoPath1);
            context.setAttribute("bannerPath", bannerPath);
            context.setAttribute("studentsPhotoPath", studentsPhotoPath1);
            context.setAttribute("staffPhotoPath", staffPhotoPath);
            context.setAttribute("provider", provider);
            context.setAttribute("alertMessage", alertMessage);
            //  System.out.println("Normal Ok");
            //JOptionPane.showMessageDialog(new JApplet(),"Initialized"+logoPath,"Message",1);
          
            Inventoryapplication.init(context);

        } catch (Exception e) {
            e.printStackTrace();
            context.setAttribute("title", "Education Management Suite");
            context.setAttribute("DBDriver", "com.mysql.jdbc.Driver");
            context.setAttribute("DBURL", "jdbc:mysql://localhost/schooldb");
            context.setAttribute("DBUserName", "adminuser");
            context.setAttribute("DBPassword", "123456");
           // context.setAttribute("logoPath", logoPath);
            context.setAttribute("bannerPath", bannerPath);
           // context.setAttribute("studentsPhotoPath", studentsPhotoPath);
            context.setAttribute("provider", "GREENBUDS Software Technologies,Vijayawada.");
            try {
                Inventoryapplication.init(context);
            } catch (InventoryException f) {
                f.printStackTrace();
            } catch (Exception f) {
                f.printStackTrace();
            } finally {

            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("the values are in servletcontextlistener cotext destroyed");
    }
}
