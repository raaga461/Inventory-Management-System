package ManagedBeans;

import InventoryBeans.companybalance;

import InventoryDaos.accountsdao;

import exceptions.InventoryException;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import InventoryBeans.voucherprofile;

import MASTERBEANS.customerbean;

import java.util.Date;

import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

/* @ManagedBean(name = "vouch", eager = true)
@SessionScoped */
public class voucherentry implements Serializable {
    @SuppressWarnings("compatibility:-7907657104182732678")
    private static final long serialVersionUID = 1L;
    Date vouchdate;
    voucherprofile vouc = new voucherprofile();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    int curr_companyid;
    ArrayList<customerbean> customerdetails;
    ArrayList<voucherprofile> voucherdeatils;
    Boolean status = true;
    double custcurrbal;

    public voucherentry() throws InventoryException, SQLException, Exception {
        super();
        try {
            setCurr_companyid(1);
            vouchernogenerate();
            customerdetails = accountsdao.getcustomersforcompany(getCurr_companyid());
            System.out.println("the customer names are" + getCustomerdetails().size());
            voucherdetails();
            compnaybalancedetails();
            setVouchdate(new Date());
            vouc.setVouchername("Payment");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /* public void stauschange() {
    if(vouc.getPaytype().equalsIgnoreCase("cash")) {
        setStatus("cash");
   }
    else {

    }
} */

    public void customerdetails() {
        try {
            customerdetails = accountsdao.getcustomersforcompany(getCurr_companyid());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addvoucher() throws Exception {
        //  vouc.setVoucherdate(df.format(vouchdate));
        //    System.out.pritnln
        try {
            if (getVouchdate() != null) {
                vouc.setVoucherdate(df.format(getVouchdate()));
                System.out.println("the date is " + getVouchdate());
            }
            vouc.setCompanyid(getCurr_companyid());
            moneyupdation();
            String msg = accountsdao.adddvoucherentry(vouc);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, ""));
            setStatus(false);
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(),
                                                                          ""));
        }
    }

    public double compnaybalancedetails() {
        double d = 0.0;
        companybalance c = new companybalance();
        try {
            c = accountsdao.getcompanybalance(getCurr_companyid());
            vouc.setCompbalance(c.getCompanybalance());
            d = c.getCompanybalance();
            System.out.println("company balance details are" + vouc.getCompbalance() + c.getCompanybalance());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }


    public void update() throws Exception {
        //  vouc.setVoucherdate(df.format(vouchdate));
        //    System.out.pritnln
        try {
            vouc.setCompanyid(getCurr_companyid());
            vouc.setUser("admin");
            moneyupdation();
            String msg = accountsdao.updatevoucherentry(vouc);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, ""));
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(),
                                                                          ""));

        }
    }

    public void voucherdetails() {
        try {
            voucherdeatils = accountsdao.getvoucherdetails(getCurr_companyid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onRowSelect(SelectEvent event) {
        customerbean c = (customerbean)event.getObject();
        vouc.setAccountcode(c.getAccesscode());
        vouc.setCity(c.getCity());
        vouc.setCustomername(c.getCompanyname());
        vouc.setCust_currbal(c.getCurrentbalance());
        vouc.setBankname(c.getBank());
        vouc.setRepname(c.getRouterep());
        vouc.setRepid(c.getRouteid());
        setCustcurrbal(c.getCurrentbalance());
       
        //System.out.println("the value is-------------------" + vouc.getAccountcode());
        //System.out.println("the city  is-------------------" + vouc.getCity());
        //  System.out.println("the Paying to  is-------------------" + vouc.getPayingto());
        System.out.println("therep id  is-------------------" + c.getRouterep() + "repname--------" + c.getRouteid() +
                           "route---" + c.getRoute() + "currrentbalance" + c.getCurrentbalance());
    }

    public void onvoucherRowSelect(SelectEvent event) {
        vouc = (voucherprofile)event.getObject();
        compnaybalancedetails();
        if (vouc != null) {
            setStatus(false);
        } else {
            setStatus(true);
        }
    }


    public void moneyupdation() {

        if (vouc.getVouchername().equalsIgnoreCase("payment")) {
            double amount = vouc.getAmount();
            double compbalance = compnaybalancedetails(); //vouc.getCompbalance();
            double custbalance = getCustcurrbal();
            vouc.setCompbalance(compbalance - amount);
            vouc.setCust_currbal(custbalance + amount);
            System.out.println("the customer current balance value " + getCustcurrbal());
            System.out.println("the current balnc in if vlaue is" + vouc.getCust_currbal());
        } else {
            double amount = vouc.getAmount();
            double compbalance = vouc.getCompbalance();
            double custbalance = getCustcurrbal();
            vouc.setCompbalance(compbalance + amount);
            vouc.setCust_currbal(custbalance - amount);
            System.out.println("the customer current balance value " + getCustcurrbal());
            System.out.println("the current balnc in else vlaue is" + vouc.getCust_currbal());
        }
    }

    public void refresh() {
        try {
            vouc = new voucherprofile();
            vouc.setCompanyid(getCurr_companyid());
            vouchernogenerate();
            compnaybalancedetails();
            vouc.setPaytype("Payment");
            setVouchdate(new Date());
            //    setCustcurrbal(0.0);
            setStatus(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void vouchernogenerate() throws Exception {
        int id;
        try {
            id = accountsdao.idGen("voucher", "vno", getCurr_companyid());
            vouc.setVoucherno(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setVouc(voucherprofile vouc) {
        this.vouc = vouc;
    }

    public voucherprofile getVouc() {
        return vouc;
    }

    public void setVouchdate(Date vouchdate) {
        this.vouchdate = vouchdate;
    }

    public Date getVouchdate() {
        return vouchdate;
    }

    public void setCurr_companyid(int curr_companyid) {
        this.curr_companyid = curr_companyid;
    }

    public int getCurr_companyid() {
        return curr_companyid;
    }


    public void setCustomerdetails(ArrayList<customerbean> customerdetails) {
        this.customerdetails = customerdetails;
    }

    public ArrayList<customerbean> getCustomerdetails() {
        return customerdetails;
    }

    public void setVoucherdeatils(ArrayList<voucherprofile> voucherdeatils) {
        this.voucherdeatils = voucherdeatils;
    }

    public ArrayList<voucherprofile> getVoucherdeatils() {
        return voucherdeatils;
    }

    public void setCustcurrbal(double custcurrbal) {
        this.custcurrbal = custcurrbal;
    }

    public double getCustcurrbal() {
        return custcurrbal;
    }

    public void setDf(SimpleDateFormat df) {
        this.df = df;
    }

    public SimpleDateFormat getDf() {
        return df;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }
}
