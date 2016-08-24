package InventoryBeans;

public class companybalance {

    int companyid;
    String companyname;
    double companybalance;

    public companybalance() {
        super();
    }

    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }

    public int getCompanyid() {
        return companyid;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanybalance(double companybalance) {
        this.companybalance = companybalance;
    }

    public double getCompanybalance() {
        return companybalance;
    }
}
