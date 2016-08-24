package MastersManagedBeans;

import MASTERBEANS.accountsgroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


public class account {
    public accountsgroup a = new accountsgroup();
    public accountsgroup a1 = new accountsgroup();
    public ArrayList catarray = new ArrayList();
    public ArrayList subcatarray = new ArrayList();
    Boolean status;
    public ArrayList<accountsgroup> al;
    Connection con;
    Statement st;
    private int curr_companyid;

    public account() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
            System.out.println("hi connected");
            setCurr_companyid(1);
            a1.setCompanyid(getCurr_companyid());
            accountnogen();
            catquery();
            subcatset();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void accountnogen() {
        try {
            System.out.println("In generate id function");
            st = con.createStatement();
            String query = "select max(Acc_No) from accountgroup where cmpid=" + getCurr_companyid() + "";
            System.out.println("the query is" + query);
            ResultSet rs = st.executeQuery(query);
            int i;
            if (rs.next()) {
                i = rs.getInt(1);
                a1.setAccountnumber(i + 1);
            } else {
                i = 1;
                a1.setAccountnumber(i);
            }
            System.out.println("the account number is" + a1.getAccountnumber());
            String query1 = "select max(subno) from accountgroup where cmpid=" + getCurr_companyid() + "";
            System.out.println("the query1 is" + query1);

            ResultSet rs1 = st.executeQuery(query1);
            int u;
            if (rs1.next()) {
                u = rs1.getInt(1);
                a1.setSubnumber(u + 1);
            } else {
                u = 1;
                a1.setSubnumber(u);
            }
            System.out.println("the account number is" + a1.getSubnumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAddSelect() {
        //  System.out.println("hello");
        try {
            st = con.createStatement();
            //System.out.println("Before  is");
            String query;
            query =
                    "insert into accountgroup values('" + a1.getCompanyid() + "'," + a1.getAccountnumber() + "," + a1.getSubnumber() +
                    ",'" + a1.getUndergroup() + "','" + a1.getNewaccgroup() + "','" + a1.getType() + "'," +
                    a1.getOpeningbal() + "," + a1.getCurrentbalance() + ",'" + a1.getNotes() + "')";
            System.out.println("the query is is" + query);
            st.executeUpdate(query);
            setStatus(true);
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO, "Details Saved ",
                                                                          ""));

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /* public void klu() {
        try {
            System.out.println("Account Number is " + r.getName());
            System.out.println("town is " + r.getTown());
            System.out.println("email is " + r.getEmail());
            System.out.println("address is " + r.getAddress());
            System.out.println("number is" + r.getNumber());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "hi saved sucessfully", ""));
        } catch (Exception e) {
            System.out.println(e);
        }
    }*/


    public void refresh() {
        a1 = new accountsgroup();
        System.out.println("cleared");
        a1.setCompanyid(getCurr_companyid());
        accountnogen();
        setStatus(true);
    }

    public void catquery() {
        try {
            System.out.println("In catquery function");
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select distinct maingroup from accountgroup customer");
            String cc;
            while (rs.next()) {
                cc = rs.getString(1);
                catarray.add(cc);
            }
            for (int i = 0; i <= catarray.size(); ++i) {
                // System.out.println(al.get(i));
                if (i == 0) {
                    a1.setUndergroup(catarray.get(i).toString());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void subcatset() {
        try {
            subcatarray.clear();
            System.out.println("In subcatset function");
            st = con.createStatement();
            System.out.println("the category id is:" + a1.getUndergroup());
            String query = "select distinct subgroup from accountgroup where MainGroup like '" + a1.getUndergroup() + "'";
            System.out.println("the query is " + query);
            ResultSet rs = st.executeQuery(query);
            String sc;
            while (rs.next()) {
                sc = rs.getString(1);
                //c1.setSubcategory(sc);
                subcatarray.add(sc);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
       
    }

    public accountsgroup getA() {
        return a;
    }

    public void setA(accountsgroup a) {
        this.a = a;
    }

    public accountsgroup getA1() {
        return a1;
    }

    public void setA1(accountsgroup a1) {
        this.a1 = a1;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ArrayList<accountsgroup> getAl() {
        return al;
    }

    public void setAl(ArrayList<accountsgroup> al) {
        this.al = al;
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

    public void setSubcatarray(ArrayList subcatarray) {
        this.subcatarray = subcatarray;
    }

    public ArrayList getSubcatarray() {
        return subcatarray;
    }
}
