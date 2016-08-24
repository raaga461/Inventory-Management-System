package ReportsModule.ManagedBeanPakage;


import ReportsModule.BeanPakage.RouteDataBean;
import ReportsModule.BeanPakage.RouteDataTableBean;

import ReportsModule.PDFReports.RouteDataPDF;

import ReportsModule.Queries.Queries;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "rdata" ,eager = true)
@ViewScoped
public class RouteData {
    private RouteDataBean rdb;
   // private RouteDataTableBean rdtb;
    private List<RouteDataTableBean>tdata;
    private List routeList=new ArrayList();
    private List routeList1=new ArrayList();
    private  String date;
    DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    
    public RouteData() {
        super();
        rdb=new RouteDataBean();
        tdata=new ArrayList<RouteDataTableBean>();
        Date d = new Date();
        date= df.format(d);
 
        rdb.setRoutename("All");
        
        tourName();


    }
  
    public void tableData(){
        tdata=Queries.routeData(rdb);
    }
   
    
    public void tourName(){
        routeList1=Queries.addRoutes1();
    }
    
    public void typeChanged(){
        tableData();
    }
    public void refresh(){
        rdb.setRoute("All");
        tdata.clear();
    }
    public void print(){
        RouteDataPDF ob=new RouteDataPDF();
        String ite =rdb.getRoutename();

               if (ite.equalsIgnoreCase("All")) {
                   ite = "%";

               }  
        ob.execute(ite, tdata);
    }

    public void setRdb(RouteDataBean rdb) {
        this.rdb = rdb;
    }

    public RouteDataBean getRdb() {
        return rdb;
    }

    public void setTdata(List<RouteDataTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<RouteDataTableBean> getTdata() {
        return tdata;
    }

    public void setRouteList(List routeList) {
        this.routeList = routeList;
    }

    public List getRouteList() {
        return routeList;
    }

    public void setRouteList1(List routeList1) {
        this.routeList1 = routeList1;
    }

    public List getRouteList1() {
        return routeList1;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
