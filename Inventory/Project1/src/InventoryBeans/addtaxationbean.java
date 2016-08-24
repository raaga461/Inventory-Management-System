package InventoryBeans;

public class addtaxationbean {
    private int taxno;
    private String region;
    private String taxname;
    private double taxperitem;
    private String notes;
    private int companyid;
    private String CFormtax;
    private double CFormtaxpercent;

    public int getTaxno() {
        return taxno;
    }

    public void setTaxno(int taxno) {
        this.taxno = taxno;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTaxname() {
        return taxname;
    }

    public void setTaxname(String taxname) {
        this.taxname = taxname;
    }

    public double getTaxperitem() {
        return taxperitem;
    }

    public void setTaxperitem(double taxperitem) {
        this.taxperitem = taxperitem;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getCompanyid() {
        return companyid;
    }

    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }

    public String getCFormtax() {
        return CFormtax;
    }

    public void setCFormtax(String CFormtax) {
        this.CFormtax = CFormtax;
    }

    public double getCFormtaxpercent() {
        return CFormtaxpercent;
    }

    public void setCFormtaxpercent(double CFormtaxpercent) {
        this.CFormtaxpercent = CFormtaxpercent;
    }

}
