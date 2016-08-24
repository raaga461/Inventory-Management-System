/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MastersManagedBeans;


import InventoryBeans.Company;

import InventoryBeans.routeprofile;

import MASTERBEANS.Vendcustdetails;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;


public class vendaddcust {

    private ArrayList<Vendcustdetails> al;
    public ArrayList<routeprofile> routearray;
    Connection con;
    Statement st;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private Vendcustdetails vc = new Vendcustdetails();
    private Vendcustdetails vc1 = new Vendcustdetails();
    public ArrayList catarray = new ArrayList();
    public ArrayList subcatarray = new ArrayList();
    private int currcompanyid;
    Boolean status = true;

    public vendaddcust() {

        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
        Company co = (Company)session.getAttribute("company");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
            System.out.println("hi connected");
            setCurrcompanyid(co.getCompanyid());
            vc1.setCompanyid(co.getCompanyid());
            onSearchSelect();
            idegen();
            routedata();
            catquery();
            subcatset();
            typedeatils();
            Date d = new Date();
            vc1.setDate(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void get() {
        System.out.println("Succesfully inserted.");
    }

    public void onAddSelect() {

        System.out.println("Succesfully inserted.");
        try {
            st = con.createStatement();
            String query =
                "insert into vendor values(" + vc1.getCompanyid() + "," + vc1.getAccesscode() + ",'" + df.format(vc1.getDate()) +
                "','" + vc1.getCompanyname() + "','" + vc1.getCategory() + "','" + vc1.getOptype() + "','" +
                vc1.getSubcategory() + "','" + vc1.getRepname() + "','" + vc1.getAddress() + "','" + vc1.getCity() +
                "','" + vc1.getState() + "','" + vc1.getPincode() + "','" + vc1.getOfficeline() + "','" +
                vc1.getFixedline() + "','" + vc1.getMobile() + "','" + vc1.getContactperson() + "','" +
                vc1.getContactpersonphno() + "','" + vc1.getEmail() + "','" + vc1.getWebsite() + "','" +
                vc1.getTransport() + "','" + vc1.getTin() + "','" + vc1.getCst() + "','" + vc1.getPan() + "','" +
                vc1.getSalestax() + "','" + vc1.getBank() + "','" + vc1.getAccountno() + "','" + vc1.getIfsccode() +
                "'," + vc1.getRouteid() + ",'" + vc1.getRoute() + "','" + vc1.getRouterep() + "','" +
                vc1.getRepphno() + "'," + vc1.getDisc() + "," + vc1.getCreditlmtamount() + "," +
                vc1.getCreditlmtday() + "," + vc1.getCurrentbalance() + "," + vc1.getOpeningbalance() + "," +
                vc1.getClosingbalance() + ")";

            int i = st.executeUpdate(query);

            if (i > 0) {
                String qry =
                    "INSERT INTO `Curr_Bal`VALUES(" + vc1.getCompanyid() + "," + vc1.getAccesscode() + ",'" + vc1.getOptype() +
                    "','" + vc1.getCompanyname() + "'," + vc1.getCurrentbalance() + ")";
                System.out.println("CURR_BAL------------>" + qry);
                st.executeUpdate(qry);
                setStatus(false);
            }
            al.add(vc1);
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO, "Details Added Sucessfully",
                                                                          ""));
            System.out.println("Access id is " + vc1.getAccesscode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDeleteSelect() {
        try {
            if (vc1 != null) {
                int i = vc1.getAccesscode();
                st = con.createStatement();
                String SQL = "DELETE from vendor where acccode = " + i + " and cmpid=" + getCurrcompanyid() + "";
                System.out.println("the delete query id " + SQL);
                st.executeUpdate(SQL);
               
                FacesMessage msg = new FacesMessage("ROW HAS BEEN DELETED", null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
                for (int u = 0; u < al.size(); u++) {
                    Vendcustdetails r = (Vendcustdetails)al.get(u);
                    if (r.getAccesscode() == vc1.getAccesscode()) {
                        al.remove(vc1);
                    }
                }
                vc1 = new Vendcustdetails();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onSearchSelect() {
        try {

            System.out.println("hi u r i n the datatable getting ");
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from vendor where cmpid=" + getCurrcompanyid() + "");
            // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Search Completed.", ""));
            al = new ArrayList<Vendcustdetails>();
            while (rs.next()) {
                Vendcustdetails v = new Vendcustdetails();
                v.setCompanyid(rs.getInt(1));
                v.setAccesscode(rs.getInt(2));
                v.setDate(rs.getDate(3));
                v.setCompanyname(rs.getString(4));
                v.setCategory(rs.getString(5));
                v.setOptype(rs.getString(6));
                v.setSubcategory(rs.getString(7));
                v.setRepname(rs.getString(8));
                v.setAddress(rs.getString(9));
                v.setCity(rs.getString(10));
                v.setState(rs.getString(11));
                v.setPincode(rs.getString(12));
                v.setOfficeline(rs.getString(13));
                v.setFixedline(rs.getString(14));
                v.setMobile(rs.getString(15));
                v.setContactperson(rs.getString(16));
                v.setContactpersonphno(rs.getString(17));
                v.setEmail(rs.getString(18));
                v.setWebsite(rs.getString(19));
                v.setTransport(rs.getString(20));
                v.setTin(rs.getString(21));
                v.setCst(rs.getString(22));
                v.setPan(rs.getString(23));
                v.setSalestax(rs.getString(24));
                v.setBank(rs.getString(25));
                v.setAccountno(rs.getString(26));
                v.setIfsccode(rs.getString(27));
                v.setRouteid(rs.getInt(28));
                v.setRoute(rs.getString(29));
                v.setRouterep(rs.getString(30));
                v.setRepphno(rs.getString(31));
                v.setDisc(rs.getInt(32));
                v.setCreditlmtamount(rs.getInt(33));
                v.setCreditlmtday(rs.getInt(34));
                v.setCurrentbalance(rs.getDouble(35));
                v.setOpeningbalance(rs.getDouble(36));
                v.setClosingbalance(rs.getDouble(37));
                al.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onRowSelect(SelectEvent event) throws SQLException {
        Vendcustdetails r = (Vendcustdetails)event.getObject();
        System.out.println("the selected details are" + r.getAccesscode());
        vc1 = r;
        if (vc1 != null) {
            setStatus(false);
        } else {
            setStatus(true);
        }
    }

    public void onUpdateSelect() {
        //detail r = (detail) event.getObject();
      //  System.out.println("the id in update select" + vc1.getAccesscode());
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Succesfully updated."));
        try {
            System.out.println("Succesfully updated.");
            st = con.createStatement();
            String query =
                "UPDATE vendor SET regdate='" + df.format(vc1.getDate()) + "',companyname='" + vc1.getCompanyname() +
                "',catogory='" + vc1.getCategory() + "',optype='" + vc1.getOptype() + "',subcat='" +
                vc1.getSubcategory() + "',repname='" + vc1.getRepname() + "',addr='" + vc1.getAddress() + "',city='" +
                vc1.getCity() + "',state='" + vc1.getState() + "',pincode='" + vc1.getPincode() + "',phn_off='" +
                vc1.getOfficeline() + "',phn_land='" + vc1.getFixedline() + "',phn_mobile='" + vc1.getMobile() +
                "',contactperson='" + vc1.getContactperson() + "',con_phn='" + vc1.getContactpersonphno() +
                "',email='" + vc1.getEmail() + "',website='" + vc1.getWebsite() + "',transport='" +
                vc1.getTransport() + "',TIN='" + vc1.getTin() + "',CST='" + vc1.getCst() + "',PAN='" + vc1.getPan() +
                "',STAX='" + vc1.getSalestax() + "',bank='" + vc1.getBank() + "',accno='" + vc1.getAccountno() +
                "',ifsc='" + vc1.getIfsccode() + "',route='" + vc1.getRoute() + "',R_rep='" + vc1.getRouterep() +
                "',R_phn='" + vc1.getRepphno() + "',Disc=" + vc1.getDisc() + ",CR_LIM_AMT=" +
                vc1.getCreditlmtamount() + ",CR_LIM_DAYS=" + vc1.getCreditlmtday() + ",currbal=" +
                vc1.getCurrentbalance() + ",opbal=" + vc1.getOpeningbalance() + ",closebal=" +
                vc1.getClosingbalance() + ",R_id=" + vc1.getRouteid() + " where  acccode=" + vc1.getAccesscode() +
                " and cmpid=" + vc1.getCompanyid() + " ";
            System.out.println("the query is " + query);
            st.executeUpdate(query);
            refresh();
            System.out.println("After executing update");
            FacesMessage msg = new FacesMessage("ROW HAS BEEN UPDATED.", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refresh() {
        System.out.println("Now u are Refreshing...");
        Vendcustdetails vc2 = new Vendcustdetails();
        setStatus(true);
        vc1 = vc2;
        vc1.setCompanyid(getCurrcompanyid());
        idegen();
        //  c = new custdetails();
        //  RequestContext.getCurrentInstance().reset("form");
        System.out.println("the vendor details: " + vc1.getAccesscode());
    }

    private void idegen() {
        try {
            System.out.println("in idgen func");
            st = con.createStatement();
            String query = "select max(acccode) from vendor";
            System.out.println("the query is " + query);
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                int s = Integer.parseInt(rs.getString(1));
                s = s + 1;
                vc1.setAccesscode(s);
            } else {
                System.out.println("data empty");
                vc1.setAccesscode(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            vc1.setAccesscode(1);
        }
    }

    public void routedata() {
        try {
            System.out.println("In route data function");
            st = con.createStatement();
            String query = "select * from routemaster";
            System.out.println("the route data qery is" + query);
            ResultSet rs = st.executeQuery(query);
            routearray = new ArrayList<routeprofile>();
            while (rs.next()) {
                routeprofile r = new routeprofile();
                r.setRouteid(rs.getInt(2));
                r.setTourname(rs.getString(3));
                r.setAreaname(rs.getString(4));
                r.setRepname(rs.getString(5));
                r.setAddress(rs.getString(6));
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
            vc1.setRouteid(r.getRouteid());
            vc1.setRoute(r.getAreaname());
            vc1.setRouterep(r.getRepname());
            vc1.setRepphno("" + r.getPhonenumber());
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
                    vc1.setCategory(catarray.get(i).toString());
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
            System.out.println("the category id is:" + vc1.getCategory());
            String query = "select subgroup from accountgroup where MainGroup like '" + vc1.getCategory() + "'";
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
            System.out.println("the sub category  is:" + vc1.getSubcategory());
            String query = "select type from accountgroup where subgroup like '" + vc1.getSubcategory() + "'";
            System.out.println("the query is " + query);
            ResultSet rs = st.executeQuery(query);
            String sc;
            while (rs.next()) {
                sc = rs.getString(1);
                vc1.setOptype(sc);
                System.out.println("the debit credit is--------------" + sc);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Vendcustdetails getVc1() {
        return vc1;
    }

    public void setVc1(Vendcustdetails vc1) {
        this.vc1 = vc1;
    }

    public ArrayList<Vendcustdetails> getAl() {
        return al;
    }

    public void setAl(ArrayList<Vendcustdetails> al) {
        this.al = al;
    }


    public SimpleDateFormat getDf() {
        return df;
    }

    public void setDf(SimpleDateFormat df) {
        this.df = df;
    }

    public Vendcustdetails getVc() {
        return vc;
    }

    public void setVc(Vendcustdetails vc) {
        this.vc = vc;
    }

    public void setCurrcompanyid(int currcompanyid) {
        this.currcompanyid = currcompanyid;
    }

    public int getCurrcompanyid() {
        return currcompanyid;
    }

    public void setRoutearray(ArrayList<routeprofile> routearray) {
        this.routearray = routearray;
    }

    public ArrayList<routeprofile> getRoutearray() {
        return routearray;
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

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }
}
