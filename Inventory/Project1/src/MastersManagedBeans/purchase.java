package MastersManagedBeans;


import InventoryBeans.Company;

import MASTERBEANS.purchaseitem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;


public class purchase {

    public purchaseitem s = new purchaseitem();
    public ArrayList<purchaseitem> al;
    public purchaseitem s1 = new purchaseitem();
    Boolean status;
    Connection con;
    Statement st;
    private int curr_companyid;
    private ArrayList catarray = new ArrayList();


    public purchase() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
            Company co = (Company)session.getAttribute("company");
            s1.setCmpid(co.getCompanyid());
            setCurr_companyid(co.getCompanyid());
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
            System.out.println("hi connected");
            onSearchSelect();
            itemcatdetails();
            itemcode();
            setStatus(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void onAddSelect() {
        System.out.println("hello");
        System.out.println("the id is:_" + s1.getCode());
        try {
            st = con.createStatement();
            String query;
            //            query =
            //                    "insert into itempurchase values(" + s1.getCmpid() + "," + s1.getCode() + ",'" + s1.getName() + "','" +
            //                    s1.getCategory() + "'," + s1.getRate() + "," + s1.getTax() + "," + s1.getDiscount() + "," +
            //                    s1.getMaxdisc() + ")";
            query =
                    "insert into itempurchase values(" + s1.getCmpid() + "," + s1.getCode() + ",'" + s1.getName() + "'," +
                    s1.getRate() + ",'" + s1.getCategory() + "'," + s1.getSalesprice() + "," + s1.getMrp() + "," +
                    s1.getTax() + ",'" + s1.getCst() + "','" + s1.getOthers() + "'," + s1.getDiscount() + "," +
                    s1.getMaxdisc() + ")";
            System.out.println("the query is is" + query);
            st.executeUpdate(query);
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO, "Details Added",
                                                                          ""));
            setStatus(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void itemcode() {
        try {
            int i = 0;
            System.out.println("hi u r i n the datatable getting ");
            st = con.createStatement();
            String query = "select max(itemcode) from itempurchase where cmpid=" + s1.getCmpid() + "";
            System.out.println("The query is" + query);
            ResultSet rs = st.executeQuery(query);
            //  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, ""));
            if (rs.next()) {
                int k = rs.getInt(1);

                i = k + 1;

            } else {
                i = 100;
            }

            s1.setCode("" + i);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void onSearchSelect() {
        try {
            System.out.println("hi u r i n the datatable getting ");
            st = con.createStatement();
            String query = "select * from itempurchase where cmpid=" + s1.getCmpid() + "";
            System.out.println("The query is" + query);
            ResultSet rs = st.executeQuery(query);
            //  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, ""));
            al = new ArrayList<purchaseitem>();
            while (rs.next()) {
                purchaseitem n = new purchaseitem();
                n.setCmpid(rs.getInt(1));
                n.setCode(rs.getString(2));
                n.setName(rs.getString(3));
                n.setRate(rs.getString(4));
                n.setCategory(rs.getString(5));
                n.setSalesprice(rs.getDouble(6));
                n.setMrp(rs.getDouble(7));
                n.setTax(rs.getString(8));
                n.setCst(rs.getString(9));
                n.setOthers(rs.getString(10));
                n.setDiscount(rs.getString(11));
                n.setMaxdisc(rs.getDouble(12));
                al.add(n);
            }
            ///itemcatdetails();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void itemcatdetails() {
        try {
            System.out.println("hi u r i n the datatable getting ");
            st = con.createStatement();
            String query = "select distinct CATEGORY from itempurchase where cmpid=" + s1.getCmpid() + "";
            System.out.println("The query is" + query);
            ResultSet rs = st.executeQuery(query);
            //  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, ""));
            while (rs.next()) {
                catarray.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void onRowSelect(SelectEvent event) {
        //  System.out.println("in row select");
        purchaseitem temp = new purchaseitem();
        temp = (purchaseitem)event.getObject();
        //   System.out.println("the selected details are" + temp.getCode());
        if (temp != null) {
            s1 = temp;
            setStatus(false);
            System.out.println("the selected details after are" + s1.getCategory());
            System.out.println("the selected details after are" + s1.getMaxdisc());
            System.out.println("the selected details after are" + s1.getCode());
        }
    }

    public void refresh() {
        s1 = new purchaseitem();
        System.out.println("in refresh");
        s1.setCmpid(getCurr_companyid());
        setStatus(true);
        itemcode();
    }

    public void update() {
        try {
            System.out.println("hi u r in the datatable getting ");
            st = con.createStatement();
            String query =
                "update itempurchase set itemname='" + s1.getName() + "',itemrate=" + s1.getRate() + ",CATEGORY='" +
                s1.getCategory() + "',salesprice=" + s1.getSalesprice() + ",MRP=" + s1.getMrp() + ",TAX=" +
                s1.getTax() + ",CST='" + s1.getCst() + "',Others='" + s1.getOthers() + "',Disc=" + s1.getDiscount() +
                ",maxdisc=" + s1.getMaxdisc() + " where  itemcode=" + s1.getCode() + " and cmpid=" +
                getCurr_companyid() + "";
            System.out.println("The query is" + query);
            st.executeUpdate(query);
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated Successfully",
                                                                          ""));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public purchaseitem getS() {
        return s;
    }

    public void setS(purchaseitem s) {
        this.s = s;
    }

    public ArrayList<purchaseitem> getAl() {
        return al;
    }

    public void setAl(ArrayList<purchaseitem> al) {
        this.al = al;
    }

    public purchaseitem getS1() {
        return s1;
    }

    public void setS1(purchaseitem s1) {
        this.s1 = s1;
    }


    public void setCurr_companyid(int curr_companyid) {
        this.curr_companyid = curr_companyid;
    }

    public int getCurr_companyid() {
        return curr_companyid;
    }

    public void setCatarray(ArrayList catarray) {
        this.catarray = catarray;
    }

    public ArrayList getCatarray() {
        return catarray;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }
}
