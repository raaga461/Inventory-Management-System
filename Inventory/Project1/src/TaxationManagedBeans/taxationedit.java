package TaxationManagedBeans;

import InventoryBeans.Company;

import java.io.Serializable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import InventoryBeans.etax;

import InventoryDaos.TaxationDaos;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author GreenBuds
 */
//@ManagedBean(name = "route")
//@ViewScoped
public class taxationedit implements Serializable {

    public ArrayList<etax> al;
    private int i;
    public etax c = new etax();
    public etax c1 = new etax();
    private etax temp;
    Connection con;
    Statement st;
    int curr_companyid;
    Boolean status = true;
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    HttpServletRequest request = (HttpServletRequest)fc.getExternalContext().getRequest();

    public taxationedit() {
        try {

            Company c = (Company)session.getAttribute("company");
            setCurr_companyid(c.getCompanyid());
            onSearchSelect();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //

    public void onAddSelect() {

        //System.out.println("Succesfully inserted.");

        try {

            st = con.createStatement();
            c1.setCompanyid(1);

            String query =
                "insert into tax values(" + c1.getCompanyid() + "," + c1.getTaxno() + ",'" + c1.getRegion() + "','" +
                c1.getTaxname() + "'," + c1.getTax() + ",'" + c1.getCFormtax() + "'," + c1.getCftaxper() + ",'" +
                c1.getNotes() + "')";
            System.out.println("the query is is: " + query);
            System.out.println("iiiiiiiiii");
            st.executeUpdate(query);
            // al.add(c1);

            System.out.println("Access id--------------------- is " + c1.getCompanyid());

            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_WARN, "Sucessfully Inserted",
                                                                          ""));

        } catch (Exception e) {
            System.out.println(e);

            //            FacesMessage msg = new FacesMessage(e.getMessage());
            //            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void refresh() {
        c1 = new etax();
        setStatus(true);
        c1.setCompanyid(getCurr_companyid());
        System.out.println("in refresh");

    }

    public void onUpdateSelect() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (c1.getRegion().equalsIgnoreCase("Select")) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Select Region", ""));
        } else if (c1.getTaxperitem() == 0) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Tax Per Item Can't Be Zero", ""));
        } else if (c1.getCFormtax().equalsIgnoreCase("Yes") && c1.getCftaxper() == 0) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "CForm Tax Can't Be Zero", ""));
        } else {

            try {

                TaxationDaos ob = new TaxationDaos();
                String msg = ob.updateTaxation(c1);
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, ""));
                refresh();
            } catch (Exception e) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), ""));

            }
        }

    }

    public void deleteTaxDetails() {

        FacesContext fc = FacesContext.getCurrentInstance();
        TaxationDaos ob = new TaxationDaos();
        try {
            String msg = ob.deleteTaxation(getCurr_companyid(), c1.getTaxno());
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, ""));
            //al.remove(c1.getTaxno());
            refresh();
        } catch (SQLException e) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), ""));
        }
    }

    public void onSearchSelect() {

        FacesContext fc = FacesContext.getCurrentInstance();
        try {

            TaxationDaos ob = new TaxationDaos();
            al = ob.getTaxationDetails(getCurr_companyid());

        } catch (Exception e) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), ""));
        }
    }


    public void onRowSelect(SelectEvent event) {

        //temp = (etax)event.getObject();
        //System.out.println("Tax No :"+temp.getTaxno()+"Tax :"+temp.getTaxperitem());
        // setC1((etax)event.getObject());
        etax s = (etax)event.getObject();
        c1 = new etax();
        c1.setTaxno(s.getTaxno());
        c1.setTaxname(s.getTaxname());
        c1.setRegion(s.getRegion());
        System.out.println("region is "+ c1.getRegion() );
        System.out.println("Tax1 :" + s.getTaxperitem());
        c1.setTaxperitem(s.getTaxperitem());
        System.out.println("Tax2 :" + c1.getTaxperitem());
        c1.setNotes(s.getNotes());
        c1.setCFormtax(s.getCFormtax());
        c1.setCftaxper(s.getCftaxper());
        //System.out.println("Tax : "+s.getTaxperitem());
        if (c1 != null) {
            setStatus(false);
        } else {
            setStatus(true);
        }


    }

    public ArrayList<etax> getAl() {
        return al;
    }

    public void setAl(ArrayList<etax> al) {
        this.al = al;
    }

    public etax getC() {
        return c;
    }

    public void setC(etax c) {
        this.c = c;
    }

    public etax getC1() {
        return c1;
    }

    public void setC1(etax c1) {
        this.c1 = c1;
    }

    public void setCurr_companyid(int curr_companyid) {
        this.curr_companyid = curr_companyid;
    }

    public int getCurr_companyid() {
        return curr_companyid;
    }

    public void setTemp(etax temp) {
        this.temp = temp;
    }

    public etax getTemp() {
        return temp;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }
}
