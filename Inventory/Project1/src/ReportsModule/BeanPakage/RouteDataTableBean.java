package ReportsModule.BeanPakage;

public class RouteDataTableBean {
    private int rno;
    private String tour;
    private String city;
    private String repname;
    private String contactno;
    private String address;
    
    
    public RouteDataTableBean() {
        super();
    }

    public void setRno(int rno) {
        this.rno = rno;
    }

    public int getRno() {
        return rno;
    }

    public void setTour(String tour) {
        this.tour = tour;
    }

    public String getTour() {
        return tour;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setRepname(String repname) {
        this.repname = repname;
    }

    public String getRepname() {
        return repname;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getContactno() {
        return contactno;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
