package MastersManagedBeans;

import InventoryBeans.Company;
import InventoryBeans.routeprofile;

import MASTERBEANS.customerbean;

import java.awt.MenuItem;
import java.awt.event.ActionEvent;

import java.io.Serializable;

import java.sql.Connection;

import java.util.Date;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;

import java.util.ArrayList;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;

public class addcust implements Serializable {
    public ArrayList<customerbean> al;
    public ArrayList<routeprofile> routearray;
    public ArrayList catarray = new ArrayList();
    public ArrayList subcatarray = new ArrayList();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    public customerbean c = new customerbean();
    public customerbean c1 = new customerbean();
    public customerbean rm = new customerbean();
    Connection con;
    Statement st, st1;
    Boolean status = true;
    private int currcompanyid;


    public addcust() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
        Company co = (Company)session.getAttribute("company");
        //  System.out.println("the company details are" + co.getCompanyname() + "The comapny id is" + co.getCompanyid());
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
            System.out.println("hi connected");
            setCurrcompanyid(co.getCompanyid());
            generateid();
            onSearchSelect();
            routedata();
            catquery();
            subcatset();
            typedeatils();
            Date d = new Date();
            c1.setRegdate(d);
            c1.setCompanyid(1);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void onDeleteSelect() {
        if (c1 != null) {
            int g = 0;
            int i = c1.getAccesscode();
            try {
                st = con.createStatement();
                String SQL = "DELETE from customer where acccode= " + i + " and cmpid=" + c1.getCompanyid() + "";
                g = st.executeUpdate(SQL);
                System.out.println("the delete query id " + SQL);
                if (g > 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Deleted Succesfully."));
                    for (int u = 0; u < al.size(); u++) {
                        customerbean r = (customerbean)al.get(u);
                        if (r.getAccesscode() == c1.getAccesscode()) {
                            al.remove(c1);
                        }
                    }
                    refresh();
                    //setStatus(true);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }


    public void hello() {
        System.out.println("Succesfully inserted.");
    }

    public void onAddSelect() {

        System.out.println("Succesfully inserted.");
        try {
            st = con.createStatement();
            String query =
                "insert into customer  values(" + c1.getCompanyid() + "," + c1.getAccesscode() + ",'" + df.format(c1.getRegdate()) +
                "','" + c1.getCompanyname() + "','" + c1.getCategory() + "','" + c1.getOptype() + "','" +
                c1.getSubcategory() + "','" + c1.getAddress() + "','" + c1.getCity() + "','" + c1.getState() + "','" +
                c1.getPincode() + "','" + c1.getLandline() + "','" + c1.getMobile() + "','" + c1.getFaxno() + "','" +
                c1.getContactperson() + "','" + c1.getEmail() + "','" + c1.getWebsite() + "','" + c1.getTransport() +
                "','"+ c1.getTin() +"','" + c1.getSalestax() + "','" + c1.getBank() + "','" + c1.getAccountno() + "','" +
                c1.getIfsccode() + "'," + c1.getRouteid() + ",'" + c1.getRoute() + "','" + c1.getRouterep() + "','" +
                c1.getRoutephno() + "'," + c1.getDisc() + "," + c1.getCreditlmtamount() + "," + c1.getCreditlmtdays() +
                "," + c1.getCurrentbalance() + "," + c1.getOpeningbalance() + "," + c1.getClosingbalance() + ")";
            System.out.println("the query is is: " + query);
            int i = st.executeUpdate(query);

            if (i == 1) {
                String qry =
                    "INSERT INTO `Curr_Bal`VALUES(" + c1.getCompanyid() + "," + c1.getAccesscode() + ",'" + c1.getOptype() +
                    "','" + c1.getCompanyname() + "'," + c1.getCurrentbalance() + ")";
                System.out.println("CURR_BAL------------>" + qry);
                st.executeUpdate(qry);
                setStatus(false);
               
            }
            al.add(c1);
            FacesMessage msg = new FacesMessage("ROW HAS BEEN INSERTED.", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.out.println("Access id is " + c1.getAccesscode());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void onUpdateSelect() {
        //detail r = (detail) event.getObject();
        // System.out.println("the id in update select" + c.getAccesscode());
        // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Succesfully updated."));
        try {
            System.out.println("Succesfully updated.");
            st = con.createStatement();
            String query =
                "UPDATE customer SET regdate='" + df.format(c1.getRegdate()) + "', companyname='" + c1.getCompanyname() +
                "',catogory='" + c1.getCategory() + "',subcat='" + c1.getSubcategory() + "',repname='" +
                c1.getRouterep() + "',optype='" + c1.getOptype() + "',addr='" + c1.getAddress() + "',state='" +
                c1.getState() + "',city='" + c1.getCity() + "',pincode='" + c1.getPincode() + "',phnland='" +
                c1.getLandline() + "',phnmob='" + c1.getMobile() + "',fax='" + c1.getFaxno() + "',conperson='" +
                c1.getContactperson() + "',website='" + c1.getWebsite() + "',email='" + c1.getEmail() +
                "',transport='" + c1.getTransport() + "',pan='" + c1.getTin() + "',stax='" + c1.getSalestax() +
                "',bank='" + c1.getBank() + "',accno='" + c1.getAccountno() + "',ifsc='" + c1.getIfsccode() +
                "',r_id='" + c1.getRouteid() + "',route='" + c1.getRoute() + "',repname='" + c1.getRouterep() +
                "',r_phn='" + c1.getRoutephno() + "',disc=" + c1.getDisc() + ",cr_lim_amt=" + c1.getCreditlmtamount() +
                ",cr_lim_days=" + c1.getCreditlmtdays() + ",currbal=" + c1.getCurrentbalance() + ",opbal=" +
                c1.getOpeningbalance() + ",closebal=" + c1.getClosingbalance() + " where  acccode=" +
                c1.getAccesscode() + " and cmpid=" + c1.getCompanyid() + "";
            System.out.println("the query is " + query);
            st.executeUpdate(query);
            refresh();
            System.out.println("After executing update");
            FacesMessage msg = new FacesMessage("ROW HAS BEEN UPDATED.", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void onSearchSelect() {
        try {

            System.out.println("hi u r i n the datatable getting ");
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from customer where cmpid=" + getCurrcompanyid() + "");
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO, "Search Completed.",
                                                                          ""));

            al = new ArrayList<customerbean>();
            while (rs.next()) {
                customerbean s = new customerbean();
                s.setCompanyid(rs.getInt(1));
                s.setAccesscode(rs.getInt(2));
                s.setRegdate(rs.getDate(3));
                s.setCompanyname(rs.getString(4));
                s.setCategory(rs.getString(5));
                //s.setRepname(rs.getString(6));
                s.setOptype(rs.getString(6));
                s.setSubcategory(rs.getString(7));
                s.setAddress(rs.getString(8));
                s.setCity(rs.getString(9));
                s.setState(rs.getString(10));
                s.setPincode(rs.getString(11));
                s.setLandline(rs.getString(12));
                s.setMobile(rs.getString(13));
                s.setFaxno(rs.getString(14));
                s.setContactperson(rs.getString(15));
                //s.setContactphno(rs.getString(16));
                s.setEmail(rs.getString(16));
                s.setWebsite(rs.getString(17));
                s.setTransport(rs.getString(18));
                s.setTin(rs.getString(19));
                s.setSalestax(rs.getString(20));
                s.setBank(rs.getString(21));
                s.setAccountno(rs.getString(22));
                s.setIfsccode(rs.getString(23));
                s.setRouteid(rs.getInt(24));
                s.setRoute(rs.getString(25));
                s.setRouterep(rs.getString(26));
                s.setRoutephno(rs.getString(27));
                // s.setPan(rs.getString(22));
                //s.setCst(rs.getString(21));
                s.setDisc(rs.getDouble(28));
                s.setCreditlmtamount(rs.getDouble(29));
                s.setCreditlmtdays(rs.getInt(30));
                s.setCurrentbalance(rs.getDouble(31));
                s.setOpeningbalance(rs.getDouble(32));
                s.setClosingbalance(rs.getDouble(33));
                al.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void onRowSelect(SelectEvent event) throws SQLException {
        customerbean r = (customerbean)event.getObject();
        System.out.println("the selected details are" + r.getAccesscode());
        c1 = r;
        if (c1 != null) {
            setStatus(false);
        } else {
            setStatus(true);
        }
    }

    public void refresh() {
        setStatus(true);
        c1 = new customerbean();
        c1.setCompanyid(getCurrcompanyid());
        generateid();
    }

    public void generateid() {
        try {
            System.out.println("In generate id function");
            st = con.createStatement();
            String query = "select max(acccode) from customer where cmpid=" + getCurrcompanyid() + "";
            System.out.println("the query is" + query);
            ResultSet rs = st.executeQuery(query);
            int i;
            if (rs.next()) {
                i = rs.getInt(1);
                c1.setAccesscode(i + 1);
            } else {
                i = 1;
                c1.setAccesscode(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void routedata() {
        try {
            System.out.println("In route data function");
            st = con.createStatement();
            String query =
                "select Routeid,Tourname,Areaname,Repname,address from routemaster where cmpid=" + getCurrcompanyid() +
                "";
            System.out.println("the route data qery is" + query);
            ResultSet rs = st.executeQuery(query);
            routearray = new ArrayList<routeprofile>();
            while (rs.next()) {
                routeprofile r = new routeprofile();
                r.setRouteid(rs.getInt(1));
                r.setTourname(rs.getString(2));
                r.setAreaname(rs.getString(3));
                r.setRepname(rs.getString(4));
                r.setAddress(rs.getString(5));
                routearray.add(r);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void onRouteselect(SelectEvent se) {
        try {
            routeprofile r = (routeprofile)se.getObject();
            System.out.println("the selected details are" + r.getRouteid());
            c1.setRouteid(r.getRouteid());
            c1.setRoute(r.getAreaname());
            c1.setRouterep(r.getRepname());
            c1.setRoutephno("" + r.getPhonenumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void catquery() {
        try {
            System.out.println("In catquery function");
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select distinct maingroup from accountgroup");
            String cc;
            while (rs.next()) {
                cc = rs.getString(1);
                catarray.add(cc);
            }
            for (int i = 0; i <= catarray.size(); ++i) {
                // System.out.println(al.get(i));
                if (i == 0) {
                    c1.setCategory(catarray.get(i).toString());
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
            System.out.println("the category id is:" + c1.getCategory());
            String query = "select distinct subgroup from accountgroup where MainGroup like '" + c1.getCategory() + "'";
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
        typedeatils();
    }

    public void typedeatils() {
        try {
            System.out.println("In typedetails function------------------------------------");
            st = con.createStatement();
            System.out.println("the sub category  is:" + c1.getSubcategory());
            String query = "select type from accountgroup where subgroup like '" + c1.getSubcategory() + "'";
            System.out.println("the query is " + query);
            ResultSet rs = st.executeQuery(query);
            String sc;
            while (rs.next()) {
                sc = rs.getString(1);
                c1.setOptype(sc);
                System.out.println("the debit credit is--------------" + sc);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList getSubcatarray() {
        return subcatarray;
    }

    public void setSubcatarray(ArrayList subcatarray) {
        this.subcatarray = subcatarray;
    }


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public SimpleDateFormat getDf() {
        return df;
    }

    public void setDf(SimpleDateFormat df) {
        this.df = df;
    }


    public ArrayList getCatarray() {
        return catarray;
    }

    public void setCatarray(ArrayList catarray) {
        this.catarray = catarray;
    }


    public void setAl(ArrayList<customerbean> al) {
        this.al = al;
    }

    public ArrayList<customerbean> getAl() {
        return al;
    }


    public void setC1(customerbean c1) {
        this.c1 = c1;
    }

    public customerbean getC1() {
        return c1;
    }


    public void setRoutearray(ArrayList<routeprofile> routearray) {
        this.routearray = routearray;
    }

    public ArrayList<routeprofile> getRoutearray() {
        return routearray;
    }

    public void setC(customerbean c) {
        this.c = c;
    }

    public customerbean getC() {
        return c;
    }

    public void setRm(customerbean rm) {
        this.rm = rm;
    }

    public customerbean getRm() {
        return rm;
    }

    public void setCurrcompanyid(int currcompanyid) {
        this.currcompanyid = currcompanyid;
    }

    public int getCurrcompanyid() {
        return currcompanyid;
    }
}


