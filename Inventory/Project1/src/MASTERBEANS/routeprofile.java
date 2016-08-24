package MASTERBEANS;


public class routeprofile {

    private String tourname;
    private String townname;
    private String email;
    private String representname;
    private String address;
    private String number;
    private String status;
    private int routeid;
    private int companyid;

    public int getCompanyid() {
        return companyid;
    }

    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTourname() {
        return tourname;
    }

    public void setTourname(String tourname) {
        this.tourname = tourname;
    }

    public String getTownname() {
        return townname;
    }

    public void setTownname(String townname) {
        this.townname = townname;
    }

    public String getRepresentname() {
        return representname;
    }

    public void setRepresentname(String representname) {
        this.representname = representname;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRouteid(int routeid) {
        this.routeid = routeid;
    }

    public int getRouteid() {
        return routeid;
    }
}
