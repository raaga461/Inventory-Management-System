package InventoryBeans;

public class etax {
    private int tax;
    private int taxno;
    private String region;
    private String taxname;
    private int taxperitem;
    private String notes;
    private int companyid;
    private String CFormtax;
    private double cftaxper;

    public double getCftaxper() {
        return cftaxper;
    }

    public void setCftaxper(double cftaxper) {
        this.cftaxper = cftaxper;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

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

    public int getTaxperitem() {
        return taxperitem;
    }

    public void setTaxperitem(int taxperitem) {
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


    public void setCFormtax(String CFormtax) {
        this.CFormtax = CFormtax;
    }

    public String getCFormtax() {
        return CFormtax;
    }
}
