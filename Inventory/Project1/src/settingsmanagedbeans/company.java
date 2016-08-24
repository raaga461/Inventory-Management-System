package settingsmanagedbeans;

import InventoryBeans.Company;

import InventoryDaos.SettingsDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;

import settingsbeans.klu;

public class company {
    private List<String> images;
    public klu c = new klu();
    public klu c1 = new klu();
    Connection con;
    Statement st;
    Boolean status = true;
    private ArrayList<klu> companiesList;
    private klu selectedCompany;
    
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(true);
    HttpServletRequest request = (HttpServletRequest)fc.getExternalContext().getRequest();
    
    public company() {
        super();
        try {
            /* Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
            System.out.println("hi connected"); */
            // idgen();
            getCompanies();
            System.out.println("increment");

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void init() {
        images = new ArrayList<String>();

        for (int i = 1; i <= 10; i++) {
            images.add("galleria" + i + ".jpg");
        }
    }

    public void onAddSelect() {
        //System.out.println("Succesfully inserted.");
        try {
            st = con.createStatement();
            String query =
                "insert into company values(" + c1.getId() + "," + c1.getRno() + ",'" + c1.getCname() + "','" +
                c1.getLname() + "','" + c1.getAdress() + "','" + c1.getCity() + "','" + c1.getState() + "','" +
                c1.getCountry() + "'," + c1.getPincode() + "," + c1.getPhone() + "," + c1.getMobile() + ",'" +
                c1.getEmail() + "','" + c1.getWebsite() + "','" + c1.getVat() + "','" + c1.getTin() + "','" +
                c1.getBank() + "'," + c1.getAcc() + ",'" + c1.getIfs() + "'," + c1.getOb() + "," + c1.getCurrent() +
                "," + c1.getCb() + ",'" + c1.getPan() + "'," + c1.getFax() + ")";
            System.out.println("the query is is: " + query);
            System.out.println("iiiiiiiiii");
            st.executeUpdate(query);
            al.add(c1);
            System.out.println("Access id--------------------- is " + c1.getId());
            FacesMessage msg = new FacesMessage("Company Created of Id.", ((c1).getId()));
            FacesContext.getCurrentInstance().addMessage(c1.getId(), msg);
        } catch (Exception e) {
            System.out.println(e);
            FacesMessage msg = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void getCompanies() throws SQLException {
       
       SettingsDao ob = new SettingsDao();
       try {
           
           String cmpid=session.getAttribute("loggeduser").toString();
           int cmpid1=Integer.parseInt(cmpid.trim());
           
          
            //System.out.println("dsfa"+companiesList.size());
            Company c=ob.getCompanyProfile(cmpid1);
           klu k1=new klu();
           k1.setAcc(c.getAccno());
           k1.setAdress(c.getAddress());
           k1.setBank(c.getBank());
           k1.setCb(c.getClosebal());
           k1.setCity(c.getCity());
           k1.setCname(c.getCompanyname());
           k1.setCountry(c.getCountry());
           k1.setCurrent(c.getCuurbal());
           k1.setEmail(c.getEmail());
           k1.setFax(c.getFax());
           k1.setId(c.getCompanyid()+"");
           k1.setIfs(c.getIfs());
           k1.setLname(c.getLegalname());
           k1.setMobile(c.getPhonemobile());
           k1.setOb(c.getOpbal());
           k1.setPan(c.getPan());
    k1.setPhone(c.getPhoneoffice());
           k1.setPincode(c.getPincode());
           k1.setRno(c.getRegno());
            k1.setState(c.getState());
           k1.setTin(c.getTin());
           k1.setVat(c.getVat());
           k1.setWebsite(c.getWebsite());
           setC1(k1);
           
        } catch (SQLException e) {
        } catch (Exception e) {
        }
    }
    
    public void displayCompanyDetails(){
        
        
        setC1(getSelectedCompany());
    }
    /* public void onRowSelect(SelectEvent event) throws SQLException {
             klu r = (klu) event.getObject();
             System.out.println("the selected details are" + r.getId());
             c1= r;

             if(c1 !=null)
             {
             setStatus(false);
             }else{
             setStatus(true);
             }
             }*/

    public void onRowSelect(SelectEvent event) {
        System.out.println("in row select event");
        klu temp = new klu();
        temp = (klu)event.getObject();
        System.out.println("hell before " + temp.getId());
        c1 = temp;
        System.out.println("hello after" + c1.getId());
        if (c != null) {
            setStatus(false);
        } else {
            setStatus(true);
        }
    }



    public void onUpdateSelect() {
        
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            SettingsDao ob = new SettingsDao();
            String msg = ob.updateCompanyProfile(getC1());
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, ""));
        } catch (Exception e) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), ""));

        }
    }

    public void onDeleteSelect() {
        System.out.println("in delete");
        String i = c.getId();
        try {
            st = con.createStatement();
            String SQL;
            SQL = "DELETE from company where companyid = " + i + "";

            st.executeUpdate(SQL);
            System.out.println("the delete query id " + SQL);
            for (int u = 0; u < al.size(); u++) {
                klu k = (klu)al.get(u);
                if (k.getId().equalsIgnoreCase(c.getId())) {
                    al.remove(c);
                }

            }


            FacesMessage msg = new FacesMessage("COMPANY DETAILS HAS BEEN DELETED IS OF ID", ((c).getId()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
            c1 = new klu();
            status = true;

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void refresh() {
        c1 = new klu();
        status = true;
        //idgen();
        System.out.println("in refresh");

    }

    public List<String> getImages() {
        return images;
    }

    public klu getC1() {
        return c1;
    }

    public void setC1(klu c1) {
        this.c1 = c1;

    }

    public void setImages(List<String> images) {
        this.images = images;
    }
    public ArrayList<klu> al;
    private int i;

    public ArrayList<klu> getAl() {
        return al;
    }

    public void setAl(ArrayList<klu> al) {
        this.al = al;
    }

    public klu getC() {
        return c;
    }

    public void setC(klu c) {
        this.c = c;
    }


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    //    public void idgen() {
    //        //detail r = (detail) event.getObject();
    //        System.out.println("the id in update select" + c1.getId());
    //        // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Succesfully updated."));
    //        i = 1;
    //        try {
    //            System.out.println("Succesfully updated.");
    //            st = con.createStatement();
    //            System.out.println("okkkkk.");
    //            String query = "select max(companyid) from company";
    //
    //
    //            System.out.println("the query is " + query);
    //            ResultSet rs = st.executeQuery(query);
    //
    //            if (rs.next()) {
    //
    //                int i = Integer.parseInt(rs.getString(1));
    //                i = i + 1;
    //                c1.setId("" + i);
    //
    //
    //            } else {
    //                i = 1;
    //                c1.setId("1");
    //
    //            }
    //        } catch (Exception e) {
    //            System.out.println(e);
    //            c1.setId("1");
    //        }
    //    }


    


    public void setSelectedCompany(klu selectedCompany) {
        this.selectedCompany = selectedCompany;
    }

    public klu getSelectedCompany() {
        return selectedCompany;
    }

    public void setCompaniesList(ArrayList<klu> companiesList) {
        this.companiesList = companiesList;
    }

    public ArrayList<klu> getCompaniesList() {
        return companiesList;
    }
}

