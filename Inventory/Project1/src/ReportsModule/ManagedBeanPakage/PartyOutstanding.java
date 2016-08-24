package ReportsModule.ManagedBeanPakage;


import ReportsModule.BeanPakage.PartyOutstandingBean;
import ReportsModule.BeanPakage.PartyOutstandingTableBean;

import ReportsModule.PDFReports.PartyOutstandingPDF;

import ReportsModule.Queries.Queries;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.sf.jasperreports.engine.JRException;

import org.primefaces.event.SelectEvent;


@ManagedBean(name = "pout",eager = true)
@ViewScoped
public class PartyOutstanding {
    private  PartyOutstandingBean rpw=new PartyOutstandingBean();
    private List<PartyOutstandingTableBean> tdata,tdata1;
    private PartyOutstandingTableBean ptdata;
     DecimalFormat dfr = new DecimalFormat("#.##");
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
     private List taxList=new ArrayList();
     private  List repList = new ArrayList();
     private List route  = new ArrayList();
     private List place = new ArrayList();
     private Double amt=0.0;
     
     
    public PartyOutstanding() {
        super();
        
       
        rpw.setSalesType("All");
        rpw.setReType("All");
        rpw.setRoute("All");
        rpw.setPlace("All");
        
        taxData();
        addRepresents();
        addRoutes();
        Getplace();
        
        Date d = new Date();
        String date = df.format(d);

        try {
            rpw.setDate2(df.parse(date));
            rpw.setDate1( df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        GetData();
    }
    
    public void GetData() {
        tdata=Queries.pData(rpw);
            if(amt!=0){
                amt=0.0;
            }
            
          
            for(int i=0;i<tdata.size();i++){
                amt=amt+tdata.get(i).getCurbal();
            }
          
        }
    
    public void print(){
        String stype= "", repre= "", rotid= "",city= "",route= "";
             
             stype =  rpw.getSalesType();
             if(stype.equalsIgnoreCase("All"))
             {
             stype = "%";
             }
             
             repre =  rpw.getReType();
              if(repre.equalsIgnoreCase("All"))
             {
             repre = "%";
             }          
             city = rpw.getPlace();
              if(city.equalsIgnoreCase("All"))
             {
             city = "%";
             }   
              
            route=rpw.getRoute();
            if(route.equalsIgnoreCase("All")){
            route = "%";
            }

         rotid = rpw.getRoute();
              if(rotid.equalsIgnoreCase("All"))
             {
                 rotid = "%";
             } 
                PartyOutstandingPDF ob = new PartyOutstandingPDF();
             try {
                  ob.execute(df.format(rpw.getDate2()), df.format(rpw.getDate1()),tdata,stype, route, repre,city,amt);
            } catch (JRException e) {
            e.printStackTrace();
        }
                  
    }
    
    
    public void refresh(){
        rpw.setDate2(null);
        rpw.setSalesType("All");
        rpw.setReType("All");
        rpw.setRoute("All");
        rpw.setPlace("All");
       
        amt = 0.0;
        tdata.clear(); 
    }
    
    
    public void RouteCombo(){            
        place=Queries.routeCombo1(rpw);
        }
    
    public void routeChanged(){
        place.clear();
    RouteCombo();
    GetData();
    }
    public void repChanged(){
      //  place.clear();
     //   tdata.clear();
        RouteCombo();
        GetData();
    }
    public void placeChanged(){
        tdata.clear();
        GetData();
    }
    
    public void taxData() {
        System.out.println("in tax data");
        taxList = Queries.taxData();
       
    }
    
   
    
    public void Getplace() {
        place = Queries.Getplace();
       
    }
    
   
    
    public void addRepresents() {
        repList=Queries.representData();
   
    }
    

    public void addRoutes() {
        route=Queries.addRoutes();
         }
    
    
    public void dateSelect(SelectEvent event) {
        rpw.setDate2((Date)event.getObject());
        GetData();

    }
    public void todateSelect(SelectEvent event) {
        rpw.setDate1((Date)event.getObject());
        GetData();

    }
    
    public void typeChanged() {  
     
         GetData();
       }  

    public void setRpw(PartyOutstandingBean rpw) {
        this.rpw = rpw;
    }

    public PartyOutstandingBean getRpw() {
        return rpw;
    }

    public void setTdata(List<PartyOutstandingTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<PartyOutstandingTableBean> getTdata() {
        return tdata;
    }

    public void setTdata1(List<PartyOutstandingTableBean> tdata1) {
        this.tdata1 = tdata1;
    }

    public List<PartyOutstandingTableBean> getTdata1() {
        return tdata1;
    }

    public void setPtdata(PartyOutstandingTableBean ptdata) {
        this.ptdata = ptdata;
    }

    public PartyOutstandingTableBean getPtdata() {
        return ptdata;
    }

    public void setTaxList(List taxList) {
        this.taxList = taxList;
    }

    public List getTaxList() {
        return taxList;
    }

    public void setRepList(List repList) {
        this.repList = repList;
    }

    public List getRepList() {
        return repList;
    }

    public void setRoute(List route) {
        this.route = route;
    }

    public List getRoute() {
        return route;
    }

    public void setPlace(List place) {
        this.place = place;
    }

    public List getPlace() {
        return place;
    }
}
