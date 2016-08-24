package MastersManagedBeans;

import InventoryBeans.Company;

import java.io.Serializable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import MASTERBEANS.stockpurchasedetails;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;

public class stockpurchase implements Serializable {
    public stockpurchasedetails c = new stockpurchasedetails();
    public stockpurchasedetails i = new stockpurchasedetails();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Connection con;
    private Boolean status;
    public ArrayList<stockpurchasedetails> al;
    private int curr_companyid;

    public stockpurchase() {

        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
            Company co = (Company)session.getAttribute("company");
            setCurr_companyid(co.getCompanyid());
            c.setCompanyid(co.getCompanyid());
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
            System.out.println("hi connected");
            onsearch();
            setStatus(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void totalquantity() {
        if (c.getCurrquantity() != 0 && c.getNewquantity() != 0) {
            System.out.println("the quantity is" + c.getCurrquantity());
            int cr = c.getCurrquantity();
            int nw = c.getNewquantity();
            int qn = cr + nw;
            System.out.println("the quantity is" + qn);
            c.setQuantity(qn);
        }
        //      else {
        //          c.setQuantity(c.getCurrquantity());
        //      }
    }

    public void onsave() {
        Statement st;
        try {
            st = con.createStatement();
            String qry =
                "UPDATE Stockpurchase SET quantity= " + c.getQuantity() + "   WHERE itemcode= " + c.getItemcode() +
                " and cmpid=" + c.getCompanyid() + "";
            System.out.println("" + qry);
            st.executeUpdate(qry);
            qry =
"INSERT INTO `StockpurchaseRecord` VALUES (" + c.getCompanyid() + "," + c.getItemcode() + ",'" + c.getItemname() +
  "','" + c.getItemcategory() + "'," + c.getCurrquantity() + ",'" + df.format(new Date()) + "')";
            System.out.println("" + qry);
            st.executeUpdate(qry);
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO, "Details Updated",
                                                                          ""));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refresh() {
        c = new stockpurchasedetails();
        c.setCompanyid(getCurr_companyid());
        setStatus(true);
    }

    public void onsearch() {
        try {
            Statement st = con.createStatement();
            ResultSet rs;
            String query = "select * from stockpurchase where cmpid=" + getCurr_companyid() + "";
            rs = st.executeQuery(query);
            al = new ArrayList<stockpurchasedetails>();
            while (rs.next()) {
                stockpurchasedetails s = new stockpurchasedetails();
                s.setCompanyid(rs.getInt(1));
                s.setItemcode(rs.getInt(2));
                s.setItemname(rs.getString(3));
                s.setItemcategory(rs.getString(4));
                s.setCurrquantity(rs.getInt(5));
                al.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onRowSelect(SelectEvent event) {
        try {
            stockpurchasedetails r = (stockpurchasedetails)event.getObject();
            System.out.println("the selected details in edit are" + r.getItemcode());
            if (r != null) {
                c = r;
                //                c.setItemcode(r.getItemcode());
                //                c.setItemname(r.getItemname());
                //                c.setItemcategory(r.getItemcategory());
                //                c.setNewquantity(r.getNewquantity());
                //                c.setCurrquantity(r.getCurrquantity());
                setStatus(false);
                System.out.println("the selected row value is" + r.getItemcode() + "item name" + r.getItemname() +
                                   "cuur quantity" + r.getCurrquantity() + "new quantity" + r.getNewquantity());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setC(stockpurchasedetails c) {
        this.c = c;
    }

    public stockpurchasedetails getC() {
        return c;
    }

    public void setDf(SimpleDateFormat df) {
        this.df = df;
    }

    public SimpleDateFormat getDf() {
        return df;
    }

    public void setAl(ArrayList<stockpurchasedetails> al) {
        this.al = al;
    }

    public ArrayList<stockpurchasedetails> getAl() {
        return al;
    }


    public void setI(stockpurchasedetails i) {
        this.i = i;
    }

    public stockpurchasedetails getI() {
        return i;
    }


    public void setCurr_companyid(int curr_companyid) {
        this.curr_companyid = curr_companyid;
    }

    public int getCurr_companyid() {
        return curr_companyid;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }
}
