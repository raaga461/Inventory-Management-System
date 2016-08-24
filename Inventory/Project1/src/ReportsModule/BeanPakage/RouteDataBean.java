package ReportsModule.BeanPakage;

import java.util.Date;

public class RouteDataBean {
    private Date date1;
    private String route;
    private String routename;
    public RouteDataBean() {
        super();
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate1() {
        return date1;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }

    public void setRoutename(String routename) {
        this.routename = routename;
    }

    public String getRoutename() {
        return routename;
    }
}
