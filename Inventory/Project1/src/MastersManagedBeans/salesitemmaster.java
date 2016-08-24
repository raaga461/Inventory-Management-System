package MastersManagedBeans;


import InventoryBeans.Company;

import MASTERBEANS.salesitemprofile;

import java.io.Serializable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;


public class salesitemmaster implements Serializable {
    Connection con;
    Statement st;
    salesitemprofile s = new salesitemprofile();
    Boolean status = true;
    public ArrayList catarray = new ArrayList();
    public ArrayList subcatarray = new ArrayList();
    public ArrayList lastarray = new ArrayList();
    public ArrayList<salesitemprofile> al;
    private int i;
    private int curr_companyid;

    public salesitemmaster() {
        super();
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
            Company co = (Company)session.getAttribute("company");
            setCurr_companyid(co.getCompanyid());
            s.setCompid(co.getCompanyid());
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
            System.out.println("hi connected");
            generateid();
            search();
            catquery();
            //  subcatset();
            //  last();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void onAddSelect() {
        //System.out.println("Succesfully inserted.");
        //System.out.println("heeeeeeee");
        try {
            st = con.createStatement();
            String query =
                "insert into itemmaster values(" + s.getCompid() + "," + s.getItemcode() + ",'" + s.getItemname() +
                "'," + s.getItemrate() + ",'" + s.getItemcategory() + "',0,0," + s.getTax() + ",0,0," + s.getDiscount() +
                "," + s.getMaxdiscount() + ",'" + s.getShortname() + "','" + s.getSubcategory() + "','" +
                s.getItemgroup() + "')";
            System.out.println("the query is is: " + query);
            System.out.println("iiiiiiiiii");
            st.executeUpdate(query);
            System.out.println("Access id--------------------- is " + s.getItemcode());
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucessfully Inserted",
                                                                          ""));
            setStatus(false);
        } catch (Exception e) {
            e.printStackTrace();

            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(),
                                                                          ""));
        }
    }


    public void search() {
        try {
            System.out.println("hi u r i n the datatable getting ");
            st = con.createStatement();
            String query =
                "select cmpid,itemcode,itemname,aproduct,mtcat,category,subgrp,itemrate,tax,disc,maxdisc from itemmaster where cmpid=" +
                getCurr_companyid() + " ";
            ResultSet rs = st.executeQuery(query);
            System.out.println("the query is " + query);
            //  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Search Completed.", ""));
            al = new ArrayList<salesitemprofile>();
            while (rs.next()) {
                salesitemprofile n = new salesitemprofile();
                n.setCompid(rs.getInt(1));
                n.setItemcode(rs.getInt(2));
                n.setItemname(rs.getString(3));
                n.setShortname(rs.getString(4));
                n.setItemgroup(rs.getString(5));
                n.setItemcategory(rs.getString(6));
                n.setSubcategory(rs.getString(7));
                n.setItemrate(rs.getDouble(8));
                n.setTax(rs.getDouble(9));
                n.setDiscount(rs.getDouble(10));
                n.setMaxdiscount(rs.getDouble(11));
                al.add(n);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onRowSelect(SelectEvent event) {
        System.out.println("in row select event");
        salesitemprofile temp = new salesitemprofile();
        temp = (salesitemprofile)event.getObject();
        System.out.println("hell before " + s.getItemcode());
        s = temp;
        System.out.println("hello after" + s.getItemcode() + "itemcat" + s.getItemcategory() + "itemgroup" +
                           s.getItemgroup() + "itemsubgroup" + s.getSubcategory());
        if (s != null) {
            setStatus(false);
        } else {
            setStatus(true);
        }
    }

    public void refresh() {
        s = new salesitemprofile();
        s.setCompid(getCurr_companyid());
        generateid();
        setStatus(true);

    }

    public void onUpdateSelect() {
        System.out.println(" u r in update methodddddddddddddddddddd enter");
        // System.out.println("the id in update select" + c1.getId());
        // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Succesfully updated."));
        try {
            System.out.println("Succesfully updated.");
            st = con.createStatement();
            System.out.println("okkkkk.");
            String query =
                "UPDATE itemmaster SET itemname='" + s.getItemname() + "',aproduct='" + s.getShortname() + "',MTCat='" +
                s.getItemgroup() + "',category='" + s.getItemcategory() + "',subgrp='" + s.getSubcategory() +
                "',itemrate=" + s.getItemrate() + ",TAX=" + s.getTax() + ",disc=" + s.getDiscount() + ", maxdisc=" +
                s.getMaxdiscount() + " where cmpid= " + s.getCompid() + " and itemcode=" + s.getItemcode() + "";
            System.out.println("the query is " + query);
            st.executeUpdate(query);
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO, "Updation completed",
                                                                          ""));
            System.out.println(" u r in update methodddddddddddddddddddd enddddddddddddddddddd");
            search();

        } catch (Exception e) {
            System.out.println(e);
            FacesMessage msg = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_ERROR, "Item code already exists",
                                                                          ""));


        }
    }


    public void generateid() {
        System.out.println("In generate id");
        try {
            System.out.println("In generate id function");
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select max(itemcode) from itemmaster where cmpid=" + s.getCompid() + "");
            int i;
            if (rs.next()) {
                i = rs.getInt(1);
                s.setItemcode(i + 1);
            } else {
                i = 1;
                s.setItemcode(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void catquery() {
        catarray.clear();
        try {
            System.out.println("In catquery function");
            st = con.createStatement();
            //ResultSet rs = st.executeQuery("select distinct itemgroup from itemmaster");
            ResultSet rs = st.executeQuery("SELECT distinct(`MTCat`) FROM `ItemMaster");
            String cc;
            while (rs.next()) {
                cc = rs.getString(1);
                catarray.add(cc);
            }
            for (int i = 0; i <= catarray.size(); i++) {
                // System.out.println(al.get(i));
                if (i == 0) {
                    s.setItemgroup(catarray.get(i).toString());
                    subcatset();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void subcatset() {
        System.out.println("In subcatset function");
        subcatarray.clear();
        ArrayList<salesitemprofile> tmp = new ArrayList<salesitemprofile>();
        tmp = getAl();
        for (int i = 0; i < tmp.size(); i++) {
            salesitemprofile s1 = (salesitemprofile)tmp.get(i);
            System.out.println("In item group" + s.getItemgroup());
            if (s.getItemgroup().equalsIgnoreCase(s1.getItemgroup())) {
                System.out.println("the subcat values are--------------------------" + s1.getItemcategory());
                if (subcatarray.contains(s1.getItemcategory())) {
                } else {
                    subcatarray.add(s1.getItemcategory());
                }
                if (i == 0) {
                    s.setItemcategory(s1.getItemcategory());
                    last();
                }

            }

        }
        System.out.println("The subcat values are" + subcatarray.size() + s.getItemcategory());

     /*   try {
            //subcatarray.clear();
            System.out.println("In subcatset function");
            st = con.createStatement();
            System.out.println("the category id is:" + s.getItemgroup());
            String query ="select distinct category from itemmaster where mtcat like '" + s.getItemgroup() + "'";
            System.out.println("the query is " + query);
            ResultSet rs = st.executeQuery(query);
            String sc;
            while (rs.next()) {
                sc = rs.getString(1);
                //c1.setSubcategory(sc);
                subcatarray.add(sc);
                System.out.println(sc);
               // last();
            }
            last();
            System.out.println("the value is" + subcatarray.size());
        } catch (Exception e) {
            System.out.println(e); */
        } 
       

    public void last() {
        lastarray.clear();
        ArrayList<salesitemprofile> tmp = new ArrayList<salesitemprofile>();
        System.out.println("In slast---------- function");
        System.out.println("the category id is:" + s.getItemcategory());
        tmp = getAl();
        for (int i = 0; i < tmp.size(); i++) {
            salesitemprofile s1 = (salesitemprofile)tmp.get(i);
            System.out.println("In item group" + s1.getItemgroup());
            if (s.getItemcategory().equalsIgnoreCase(s1.getItemcategory())) {
                if (lastarray.contains(s1.getSubcategory())) {
                } else {
                    lastarray.add(s1.getSubcategory());
                }
                if (i == 0) {
                    s.setSubcategory(s1.getSubcategory());
                }
            }

        }
        System.out.println("In item group" + s.getSubcategory());
        System.out.println("The last array values are " + lastarray.size()); 
        /*     try {
            lastarray.clear();
            System.out.println("In last function");
            st = con.createStatement();
            System.out.println("the itemcategory id is:" + s.getItemcategory());
            String query =
                "select distinct  subgrp from itemmaster where category like '" + s.getItemcategory() + "'";
            System.out.println("the query is " + query);
            ResultSet rs = st.executeQuery(query);
            String sc;
            while (rs.next()) {
                sc = rs.getString(1);

                //c1.setSubcategory(sc);
                lastarray.add(sc);
                System.out.println(sc);
            }
            System.out.println("the value is" + lastarray.size());
        } catch (Exception e) {
            System.out.println(e);
        }  */
    }


    public void setSubcatarray(ArrayList subcatarray) {
        this.subcatarray = subcatarray;
    }

    public ArrayList getSubcatarray() {
        return subcatarray;
    }

    public void setLastarray(ArrayList lastarray) {
        this.lastarray = lastarray;
    }

    public ArrayList getLastarray() {
        return lastarray;
    }

    public void setAl(ArrayList<salesitemprofile> al) {
        this.al = al;
    }

    public ArrayList<salesitemprofile> getAl() {
        return al;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setS(salesitemprofile s) {
        this.s = s;
    }

    public salesitemprofile getS() {
        return s;
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
}


