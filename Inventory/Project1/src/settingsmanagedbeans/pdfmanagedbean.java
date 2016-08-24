package settingsmanagedbeans;

import InventoryDaos.SettingsDao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import settingsbeans.head;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import InventoryBeans.Company;

import java.sql.SQLException;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;


public class pdfmanagedbean {
    public head al;
    private int i;
    public head c = new head();
    public head c1 = new head();
    Connection con;
    Statement st;
    private UploadedFile imagefile;
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    HttpServletRequest request = (HttpServletRequest)fc.getExternalContext().getRequest();
    
    public pdfmanagedbean() {
        super();
        

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
            System.out.println("hi connected");
            // idgen();

            System.out.println("increment");

        } catch (Exception e) {
            System.out.println(e);
        }
        getPdfHeaders();
    }
    
    public void getPdfHeaders(){
        
        
        SettingsDao ob = new SettingsDao();
        Company c = (Company)session.getAttribute("company");
        try {
            setC1(ob.getPdfHeadings(""+c.getCompanyid()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    

    public void setC(head c) {
        this.c = c;
    }

    public head getC() {
        return c;
    }

    public void setC1(head c1) {
        this.c1 = c1;
    }

    public head getC1() {
        return c1;
    }

    public void onAddSelect() {

       SettingsDao ob = new SettingsDao();
       String msg;
       FacesContext fc = FacesContext.getCurrentInstance();

        try {
            msg = ob.addPdfHeadings(c1);
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, ""));
        } catch (SQLException e) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), ""));
        }
    }


    public void fileupload(FileUploadEvent event) {

        imagefile = event.getFile();
         if (imagefile != null) {
                   System.out.println("Called Inside");
                } 


    }

    public void refresh() {
        c1 = new head();

        //idgen();
        System.out.println("refresh");

    }

    public void setImagefile(UploadedFile imagefile) {
        this.imagefile = imagefile;
    }

    public UploadedFile getImagefile() {
        return imagefile;
    }

    private void addimage() {
        System.out.println("after File");
        try {
            System.out.println("after Filkkkkkkkkkkkkkkkkkkkke");
            if (getImagefile() != null) {
                System.out.println("after File");

                File outputpath = new File("D" + File.separator + "Inv");
                System.out.println("after File" + outputpath.getAbsolutePath());
                InputStream is = getImagefile().getInputstream();
                System.out.println("after File");
                OutputStream out = new FileOutputStream(outputpath);
                System.out.println("after File");
                byte buf[] = new byte[1024];
                int len;
                System.out.println("before while");
                while ((len = is.read(buf)) > 0) {
                    //    System.out.println("before while" + len);
                    out.write(buf, 0, len);
                }
                is.close();
                out.close();
                // System.out.println("after File" + file.getAbsolutePath() + "\t" + file.getTotalSpace());
            }

        }


        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setAl(head al) {
        this.al = al;
    }

    public head getAl() {
        return al;
    }
}

