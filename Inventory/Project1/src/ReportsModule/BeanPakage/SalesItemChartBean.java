package ReportsModule.BeanPakage;

import java.util.Date;

public class SalesItemChartBean {
    private Date date1;
    private Date date2;
    private String stype;
    private String route;
    private String igroup;
    private String representative;
    public SalesItemChartBean() {
        super();
    }

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

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getStype() {
        return stype;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }

    public void setIgroup(String igroup) {
        this.igroup = igroup;
    }

    public String getIgroup() {
        return igroup;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public String getRepresentative() {
        return representative;
    }
}
