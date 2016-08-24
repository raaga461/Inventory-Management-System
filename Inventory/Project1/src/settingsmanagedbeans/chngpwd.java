package settingsmanagedbeans;

import InventoryDaos.SettingsDao;

import LoginPrivilages.UserController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import settingsbeans.changepassword;

/**
 *
 * @author GreenBuds
 */

public class chngpwd {
    HttpSession session;
    FacesContext fc;
    String username, password;
    Connection con;
    Statement st;
    private boolean state = true;
    public ArrayList<changepassword> al;
    changepassword pd = new changepassword();
    changepassword pd1 = new changepassword();

    public chngpwd() throws SQLException {
        fc = FacesContext.getCurrentInstance();
        session = (HttpSession)fc.getExternalContext().getSession(false);
        HttpServletRequest request = (HttpServletRequest)fc.getExternalContext().getRequest();
        System.out.println("the values are");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
            System.out.println("hi connected");
            st = con.createStatement();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void onSave() {

        //FacesContext fc = FacesContext.getCurrentInstance();
        String msg = "";
        try {
            String uid = (String)session.getAttribute("loggeduser");
            pd.setUserid(uid);
            SettingsDao ob = new SettingsDao();
            msg = ob.saveChangePassword(pd);
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO, msg,
                                                                          ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(),
                                                                          ""));
        }
    }

    public void reset() {

        new changepassword();
    }

    public ArrayList<changepassword> getAl() {
        return al;
    }

    public void setAl(ArrayList<changepassword> al) {
        this.al = al;
    }

    public changepassword getPd() {
        return pd;
    }

    public void setPd(changepassword pd) {
        this.pd = pd;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //    public ArrayList<changepassword> al;
    //    private String type;
    //    Connection con;
    //    Statement st;
    //    String username, password;
    //    private changepassword pd = new changepassword();
    //
    //    public chngpwd() {
    //        try {
    //            Class.forName("com.mysql.jdbc.Driver");
    //            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
    //            System.out.println("hi connected");
    //            st = con.createStatement();
    //        } catch (Exception e) {
    //            System.out.println(e);
    //        }
    //    }
    //
    //    public void onSave() {
    //        System.out.println("Hi ur in onsave");
    //        try {
    //            st = con.createStatement();
    //            ResultSet rs = st.executeQuery("select * from login where userame='" + pd.getUsername() + "' ");
    //            while (rs.next()) {
    //                username = rs.getString(2);
    //                password = rs.getString(3);
    //            }
    //            //  System.out.println(username + " " + password);
    //            if (password.equalsIgnoreCase(pd.getPassword())) {
    //                Statement st1 = con.createStatement();
    ////              int i = st1.executeUpdate("update login set password='" + pd.getNewpassword() + "' where usr='" + username + "'");
    //                int j = st1.executeUpdate("update reg set password='" + pd.getNewpassword() + "' where usr='" + username + "'");
    //                System.out.println("Password changed successfully");
    //                st1.close();
    //                con.close();
    //            } else {
    //                System.out.println("Invalid Password");
    //            }
    //        } catch (Exception e) {
    //            System.out.println(e);
    //        }
    //    }
    //
    //    public Connection getCon() {
    //        return con;
    //    }
    //
    //    public void setCon(Connection con) {
    //        this.con = con;
    //    }
    //
    //    public Statement getSt() {
    //        return st;
    //    }
    //
    //    public void setSt(Statement st) {
    //        this.st = st;
    //    }
    //
    //    public String getUsername() {
    //        return username;
    //    }
    //
    //    public void setUsername(String username) {
    //        this.username = username;
    //    }
    //
    //    public String getPassword() {
    //        return password;
    //    }
    //
    //    public void setPassword(String password) {
    //        this.password = password;
    //    }
    //
    //    public ArrayList<changepassword> getAl() {
    //        return al;
    //    }
    //
    //    public void setAl(ArrayList<changepassword> al) {
    //        this.al = al;
    //    }
    //
    //    public changepassword getPd() {
    //        return pd;
    //    }
    //
    //    public void setPd(changepassword pd) {
    //        this.pd = pd;
    //    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void onCancel() {
        System.out.println("Now u are Refreshing...");
        changepassword pd1 = new changepassword();
        state = true;
        pd1 = pd;

        //        c = new custdetails();
        //  RequestContext.getCurrentInstance().reset("form");
        System.out.println("the vendor details: " + pd.getUsername());
    }
}

