package MastersManagedBeans;

import InventoryBeans.Company;

import MASTERBEANS.routeprofile;

import java.io.Serializable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;


public class route implements Serializable {

    public routeprofile r = new routeprofile();
    public routeprofile r1 = new routeprofile();
    Boolean status = true;
    public ArrayList<routeprofile> al, totaldetails;
    public ArrayList tourname1, townname, repname;
    Connection con;
    Statement st;
    private int curr_companyid;

    public route() {
        try {

            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
            Company co = (Company)session.getAttribute("company");

            setCurr_companyid(co.getCompanyid());
            r1.setCompanyid(getCurr_companyid());

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
            System.out.println("hi connected");
            tourname1 = new ArrayList();
            townname = new ArrayList();
            repname = new ArrayList();
            onSearchSelect();
            getdetails();
            idegen();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void klu() {
        try {
            System.out.println("name is " + r.getTourname());
            System.out.println("town is " + r.getTownname());
            System.out.println("email is " + r.getEmail());
            System.out.println("address is " + r.getAddress());
            System.out.println("number is" + r.getNumber());
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_ERROR, "hi saved sucessfully",
                                                                          ""));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void getdetails() {
        //onSearchSelect();
        setTotaldetails(getAl());
        tourname1.clear();
        System.out.println("hi u r in getdetails");
        for (int i = 0; i < getTotaldetails().size(); i++) {
            routeprofile r = (routeprofile)getTotaldetails().get(i);
            if (tourname1.contains(r.getTourname())) {
            } else {
                System.out.println("the tourname details are" + r.getTourname());
                tourname1.add(r.getTourname());
            }
            //System.out.println("the route name" + r.getTourname());
        }
        // System.out.println("the route name" + getTourname1().size());
    }

    public void subtowndetails() {
        townname.clear();
        for (int i = 0; i < getTotaldetails().size(); i++) {
            routeprofile r = (routeprofile)getTotaldetails().get(i);
            if (r1.getTourname().equalsIgnoreCase(r.getTourname())) {
                if (getTownname().contains(r.getTownname())) {

                } else {
                    townname.add(r.getTownname());
                }
            }
        }
        System.out.println("The  towname is" + getTownname().size());

    }

    public void representnames() {
        repname.clear();
        for (int i = 0; i < getTotaldetails().size(); i++) {
            routeprofile r = (routeprofile)getTotaldetails().get(i);
            if (r1.getTownname().equalsIgnoreCase(r.getTownname())) {
                if (getRepname().contains(r.getRepresentname())) {

                } else {
                    repname.add(r.getRepresentname());

                }
            }
        }
        System.out.println("The  towname is" + getTownname().size());

    }

    public void representdetails() {
        for (int i = 0; i < getTotaldetails().size(); i++) {
            routeprofile r = (routeprofile)getTotaldetails().get(i);
            if (r1.getRepresentname().equalsIgnoreCase(r.getRepresentname())) {
                r1.setAddress(r.getAddress());
                r1.setNumber(r.getNumber());
                r1.setEmail(r.getEmail());
            }
        }
        //   System.out.println("The repnames is" + getTownname().size());
    }

    public void onAddSelect() {
        //System.out.println("hello");
        try {
            st = con.createStatement();
            //System.out.println("Before  is");
            String query =
                "insert into routemaster values(" + r1.getCompanyid() + "," + r1.getRouteid() + ",'" + r1.getTourname() +
                "','" + r1.getTownname() + "','" + r1.getRepresentname() + "','" + r1.getAddress() + "','" +
                r1.getEmail() + "'," + r1.getNumber() + ")";
            System.out.println("the query is is" + query);
            st.executeUpdate(query);
            al.add(r1);
            // System.out.println("Succesfully inserted.");
            FacesContext.getCurrentInstance().addMessage(r1.getTourname(),
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO, "Congratulations Successfully Inserted",
                                                                          ""));
            setStatus(false);
        } catch (Exception e) {
            System.out.println(e);
            FacesContext.getCurrentInstance().addMessage(r1.getTourname(),
                                                         new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(),
                                                                          ""));
        }
    }

    public void onSearchSelect() {
        try {
            System.out.println("hi u r i n the datatable getting ");
            st = con.createStatement();
            String query =
                "select cmpid,tourname,areaname,repname,address,email,phonenumber,routeid from routemaster where cmpid=" +
                getCurr_companyid() + "";
            ResultSet rs = st.executeQuery(query);
            System.out.println("the query is" + query);
            /*  FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO, "Search Completed.",
                                                                          ""));*/
            al = new ArrayList<routeprofile>();
            while (rs.next()) {
                routeprofile s = new routeprofile();
                s.setCompanyid(rs.getInt(1));
                s.setTourname(rs.getString(2));
                s.setTownname(rs.getString(3));
                s.setRepresentname(rs.getString(4));
                s.setAddress(rs.getString(5));
                s.setEmail(rs.getString(6));
                s.setNumber(rs.getString(7));
                s.setRouteid(rs.getInt(8));
                al.add(s);
            }
            System.out.println("the arraylist size is " + al.size());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void onRowSelect(SelectEvent event) {
        routeprofile temp = new routeprofile();
        temp = (routeprofile)event.getObject();
        System.out.println("hell before " + temp.getAddress());
        r1 = temp;
        setStatus(false);
        System.out.println("hello after" + r1.getAddress());
    }

    public void onUpdateSelect() {
        try {

            st = con.createStatement();
            String query =
                "UPDATE routemaster SET cmpid='" + r1.getCompanyid() + "',tourname='" + r1.getTourname() + "',areaname='" +
                r1.getTownname() + "',repname='" + r1.getRepresentname() + "',address='" + r1.getAddress() +
                "',email='" + r1.getEmail() + "',phonenumber='" + r1.getNumber() + "' where routeid='" +
                r1.getRouteid() + "'";
            System.out.println("the query is" + query);
            st.executeUpdate(query);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Succesfully updated."));
            // System.out.println("After executing update");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void refresh() {
        r1 = new routeprofile();
        r1.setCompanyid(getCurr_companyid());
        //System.out.println("cleared");
        idegen();
        setStatus(true);
    }

    private void idegen() {
        try {
            st = con.createStatement();
            String query = "select max(routeid) from routemaster where cmpid=" + getCurr_companyid() + "";
            System.out.println("the query is " + query);
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                int s = (rs.getInt(1));
                r1.setRouteid(+s + 1);
            } else {
                r1.setRouteid(1);
            }

        } catch (Exception e) {
            System.out.println(e);
            r1.setRouteid(1);
        }
    }

    public ArrayList<routeprofile> getAl() {
        return al;
    }

    public void setAl(ArrayList<routeprofile> al) {
        this.al = al;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public routeprofile getR1() {
        return r1;
    }

    public void setR1(routeprofile r1) {
        this.r1 = r1;
    }

    public routeprofile getR() {
        return r;
    }

    public void setR(routeprofile r) {
        this.r = r;
    }

    public routeprofile getS() {
        return r;
    }

    public void setS(routeprofile r) {
        this.r = r;
    }

    public ArrayList getTourname1() {
        return tourname1;
    }

    public void setTourname1(ArrayList tourname1) {
        this.tourname1 = tourname1;
    }

    public ArrayList<routeprofile> getTotaldetails() {
        return totaldetails;
    }

    public void setTotaldetails(ArrayList<routeprofile> totaldetails) {
        this.totaldetails = totaldetails;
    }

    public ArrayList getTownname() {
        return townname;
    }

    public void setTownname(ArrayList townname) {
        this.townname = townname;
    }

    public ArrayList getRepname() {
        return repname;
    }

    public void setRepname(ArrayList repname) {
        this.repname = repname;
    }

    public void setCurr_companyid(int curr_companyid) {
        this.curr_companyid = curr_companyid;
    }

    public int getCurr_companyid() {
        return curr_companyid;
    }
}
