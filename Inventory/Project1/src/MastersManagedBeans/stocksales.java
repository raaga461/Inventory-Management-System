package MastersManagedBeans;


import InventoryBeans.Company;

import MASTERBEANS.stocksalesdetails;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import org.primefaces.event.SelectEvent;

import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

import javax.servlet.http.HttpSession;


public class stocksales {

    public stocksalesdetails c = new stocksalesdetails();
    public stocksalesdetails i = new stocksalesdetails();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Connection con;
    private Boolean status;
    public ArrayList<stocksalesdetails> al;
    private int curr_companyid;

    public stocksales() {
        super();
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
            Company co = (Company)session.getAttribute("company");
            setCurr_companyid(co.getCompanyid());
            c.setCompanyid(getCurr_companyid());
            System.out.println("the company id is" + c.getCompanyid());
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
            int cr = c.getCurrquantity();
            int nw = c.getNewquantity();
            int qn = cr + nw;
            c.setQuantity(qn);
        }
    }

    public void onsave() {
        Statement st;
        int i, j;
        try {
            st = con.createStatement();
            String qry =
                "UPDATE StockSales SET quantity= " + c.getQuantity() + "   WHERE `itemcode`= " + c.getItemcode() +
                " and cmpid=" + getCurr_companyid() + " ";
            System.out.println("" + qry);
            i = st.executeUpdate(qry);
            qry =
"INSERT INTO `StockSalesRecord` VALUES (" + c.getCompanyid() + "," + c.getItemcode() + ",'" + c.getItemname() + "','" +
  c.getItemcategory() + "'," + c.getQuantity() + ",'" + df.format(new Date()) + "')";
            System.out.println("" + qry);
            j = st.executeUpdate(qry);
            if (j > 0)

            {
                setStatus(true);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Saved Succesfully."));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refresh() {
        c = new stocksalesdetails();
        setStatus(true);
        c.setCompanyid(getCurr_companyid());
    }

    public void onsearch() {
        try {
            Statement st = con.createStatement();
            ResultSet rs;
            String query = "select * from stocksales where cmpid=" + getCurr_companyid() + "";
            rs = st.executeQuery(query);
            al = new ArrayList<stocksalesdetails>();
            while (rs.next()) {
                stocksalesdetails s = new stocksalesdetails();
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
        stocksalesdetails r = (stocksalesdetails)event.getObject();
        System.out.println("the selected details in edit are");
        c = r;
        if (c != null) {
            setStatus(false);
        } else
            setStatus(true);

    }

    public void setC(stocksalesdetails c) {
        this.c = c;
    }

    public stocksalesdetails getC() {
        return c;
    }

    public void setDf(SimpleDateFormat df) {
        this.df = df;
    }

    public SimpleDateFormat getDf() {
        return df;
    }

    public void setAl(ArrayList<stocksalesdetails> al) {
        this.al = al;
    }

    public ArrayList<stocksalesdetails> getAl() {
        return al;
    }


    public void setI(stocksalesdetails i) {
        this.i = i;
    }

    public stocksalesdetails getI() {
        return i;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setCurr_companyid(int curr_companyid) {
        this.curr_companyid = curr_companyid;
    }

    public int getCurr_companyid() {
        return curr_companyid;
    }
}
