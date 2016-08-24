package InventoryBeans;

public class routeprofile {

    int companyid;
    int routeid;
    String tourname;
    String areaname;
    String repname;
    String address;
    String email;
    int phonenumber;

    public routeprofile() {
        super();
    }

    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }

    public int getCompanyid() {
        return companyid;
    }

    public void setRouteid(int routeid) {
        this.routeid = routeid;
    }

    public int getRouteid() {
        return routeid;
    }

    public void setTourname(String tourname) {
        this.tourname = tourname;
    }

    public String getTourname() {
        return tourname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setRepname(String repname) {
        this.repname = repname;
    }

    public String getRepname() {
        return repname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getPhonenumber() {
        return phonenumber;
    }
}
