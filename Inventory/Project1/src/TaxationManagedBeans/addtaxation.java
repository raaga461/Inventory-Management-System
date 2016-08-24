/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TaxationManagedBeans;

import InventoryBeans.Company;

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

import org.primefaces.event.SelectEvent;

import InventoryBeans.addtaxationbean;

import InventoryDaos.TaxationDaos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Bhargava
 */
public class addtaxation implements Serializable {

    public addtaxationbean a = new addtaxationbean();
    public addtaxationbean a1 = new addtaxationbean();
    Connection con;
    Statement st;
    public ArrayList<addtaxationbean> al;
    int curr_companyid;
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    HttpServletRequest request = (HttpServletRequest)fc.getExternalContext().getRequest();

    public addtaxation() {
        try {
            
            Company c = (Company)session.getAttribute("company");
            setCurr_companyid(c.getCompanyid());
            TaxationDaos ob = new TaxationDaos();
            a1.setTaxno(ob.getTaxationId("tax"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void onAddSelect() {
        
        FacesContext fc = FacesContext.getCurrentInstance();
        a1.setCompanyid(getCurr_companyid());
        //System.out.println("hello");
        try {
            if(a1.getRegion().equalsIgnoreCase("Select")){
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Select Region", ""));
            }
            else if(a1.getTaxperitem()==0){
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Tax Per Item Can't Be Zero", ""));
            }
            else if(a1.getCFormtax().equalsIgnoreCase("Yes") && a1.getCFormtaxpercent()==0){
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"CForm Tax Can't Be Zero", ""));
            }
            else{
            TaxationDaos ob = new TaxationDaos();
            String msg = ob.addTaxation(a1);
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, ""));
            refresh();
            }
        } catch (Exception e) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,e.getMessage(), ""));
        }
    }

    public void refresh() {
        a1 = new addtaxationbean();
        TaxationDaos ob = new TaxationDaos();
        try {
            a1.setTaxno(ob.getTaxationId("tax"));
        } catch (SQLException e) {
        }
        a1.setCompanyid(getCurr_companyid());
    }

    public addtaxationbean getA() {
        return a;
    }

    public void setA(addtaxationbean a) {
        this.a = a;
    }

    public addtaxationbean getA1() {
        return a1;
    }

    public void setA1(addtaxationbean a1) {
        this.a1 = a1;
    }

    public ArrayList<addtaxationbean> getAl() {
        return al;
    }

    public void setAl(ArrayList<addtaxationbean> al) {
        this.al = al;
    }

    public void setCurr_companyid(int curr_companyid) {
        this.curr_companyid = curr_companyid;
    }

    public int getCurr_companyid() {
        return curr_companyid;
    }
}
