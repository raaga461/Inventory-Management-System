package ReportsModule.BeanPakage;

import java.util.Date;

public class PartyOutstandingBean {
    
    private Date date1;
    private Date date2;
    private String salesType;
    private String reType;
    private String route;
    private String place;


    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public Date getDate2() {
        return date2;
    }

    public void setSalesType(String salesType) {
        this.salesType = salesType;
    }

    public String getSalesType() {
        return salesType;
    }

    public void setReType(String reType) {
        this.reType = reType;
    }

    public String getReType() {
        return reType;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }
}
