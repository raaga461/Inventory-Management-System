/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MastersManagedBeans;

import InventoryBeans.Company;

import MASTERBEANS.customerbean;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import org.primefaces.event.CellEditEvent;


public class balance {
    public ArrayList<customerbean> array = new ArrayList<customerbean>();
    private String type;
    Connection con;
    Statement st;
    private int curr_companyid;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public balance() {
        
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
        Company co = (Company)session.getAttribute("company");
        try {
          
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
            System.out.println("hi connected");
            st = con.createStatement();
            setCurr_companyid(co.getCompanyid());
            set();
          
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void balancedetails() {
        try {
            System.out.println(" hi ur in drop down change evnt");
            System.out.println("The selected drop down value is" + getType());
            String query="select * from CURR_BAL where optype like '" + getType() + "' and cmpid="+ getCurr_companyid() +" ";
            System.out.println(query);
            ResultSet rs = st.executeQuery(query);
            array = new ArrayList<customerbean>();
            while (rs.next()) {
                customerbean s = new customerbean();
                s.setCompanyid(rs.getInt(1));
                 s.setAccesscode(rs.getInt(2));
                s.setCompanyname(rs.getString(4));
                s.setOptype(rs.getString(3));
                s.setCurrentbalance(rs.getDouble(5)); 
                array.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public void set() {
        System.out.println("In set functuion");
        try {
            System.out.println("hi u r i n the datatable getting ");
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from curr_bal where cmpid=" + getCurr_companyid() + "");
            array = new ArrayList<customerbean>();
            while (rs.next()) {
                customerbean s = new customerbean();
                s.setCompanyid(rs.getInt(1));
                s.setAccesscode(rs.getInt(2));
                s.setCompanyname(rs.getString(4));
                s.setOptype(rs.getString(3));
                s.setCurrentbalance(rs.getDouble(5));
                array.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    } 
    public void onCellEdit(CellEditEvent event) {  
            Object oldValue = event.getOldValue();  
            Object newValue = event.getNewValue();  
        int i=event.getRowIndex();
              
            if(newValue != null && !newValue.equals(oldValue)) {  
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + i);  
                FacesContext.getCurrentInstance().addMessage(null, msg);  
            }  
        }  
    public void pdfgen() {
        try {
            System.out.println("in pdf generation func");
            printbalance p = new printbalance(getType());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setArray(ArrayList<customerbean> array) {
        this.array = array;
    }

    public ArrayList<customerbean> getArray() {
        return array;
    }

    public void setCurr_companyid(int curr_companyid) {
        this.curr_companyid = curr_companyid;
    }

    public int getCurr_companyid() {
        return curr_companyid;
    }
}
