/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InventoryBeans;

import java.util.Date;

/**
 *
 * @author GreenBuds
 */
public class chequereturnsprofile {
    private int companyid;
    private int sno;
    private int cid;
    private String customername;
    private String type;
    private String chqdate;
    private String chqno;
    private double amount;
    private double bankcharge;
    private String returndate;
    private int rid;
    private String rep;
    private String city;
    private String chno;
    private double totalamt;
    private String notes;

    public int getCompanyid() {
        return companyid;
    }

    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getChqno() {
        return chqno;
    }

    public void setChqno(String chqno) {
        this.chqno = chqno;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBankcharge() {
        return bankcharge;
    }

    public void setBankcharge(double bankcharge) {
        this.bankcharge = bankcharge;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRep() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getChno() {
        return chno;
    }

    public void setChno(String chno) {
        this.chno = chno;
    }

    public double getTotalamt() {
        return totalamt;
    }

    public void setTotalamt(double totalamt) {
        this.totalamt = totalamt;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setChqdate(String chqdate) {
        this.chqdate = chqdate;
    }

    public String getChqdate() {
        return chqdate;
    }

    public void setReturndate(String returndate) {
        this.returndate = returndate;
    }

    public String getReturndate() {
        return returndate;
    }
}
