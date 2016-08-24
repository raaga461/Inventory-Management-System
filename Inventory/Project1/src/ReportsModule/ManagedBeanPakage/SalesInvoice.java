package ReportsModule.ManagedBeanPakage;


import ReportsModule.BeanPakage.SalesInvoiceBean;
import ReportsModule.BeanPakage.SalesInvoiceTableBean;

import ReportsModule.PDFReports.SalesInvoicePDF;

import ReportsModule.Queries.Queries;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;


@ManagedBean(name = "invoice",eager = true)
@ViewScoped
public class SalesInvoice {
    private SalesInvoiceBean sib;
    private SalesInvoiceTableBean stb;
    private List<SalesInvoiceTableBean>tdata;
    DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private List taxList=new ArrayList();
    private  List representativeList = new ArrayList();
    private List route  = new ArrayList();
    private List place = new ArrayList();
    private List details = new ArrayList();
    private Double amount=0.0;
    
    public SalesInvoice() {
        super();
         sib=new SalesInvoiceBean();
         tdata=new  ArrayList<SalesInvoiceTableBean>();
           sib.setSalesType("All");
           sib.setReType("All");
        sib.setRoute("All");
        sib.setPlace("All");
         taxData();
        addRepresents();
        addRoutes();
        RouteCombo();
        Date d = new Date();
        String date = df.format(d);

        try {
            sib.setDate2(df.parse(date));
            sib.setDate1( df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
      //  GetData();
    }
    public void taxData() {
        taxList = Queries.taxData();
    }
    public void addRepresents() {
        representativeList=Queries.representData();
    }
    public void addRoutes() {
        route=Queries.addRoutes();
    }
    public void RouteCombo()
        {     
        place=Queries.SalesInvoiceRouteCombo(sib);
                
        }
    public void GetData(){
        tdata=Queries.salesInvoiceData(sib);
        String amt;
        if(amount!=0.0){
            amount=0.0;
        }
        
        for(int i=0;i<tdata.size();i++){
            
            amount=amount+Double.parseDouble(tdata.get(i).getInvvalue());
            amount=Double.parseDouble(dfr.format(amount));
        }
       System.out.println("amt value is "+amount);
    }
    public void typeChanged() {  
     //  tdata.clear();
         GetData();
       }  
    public void routeChanged(){
        //place.clear();
    RouteCombo();
    GetData();
    }
    public void refresh(){
        Date d = new Date();
        String date = df.format(d);

        try {
            sib.setDate2(df.parse(date));
            sib.setDate1( df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
      
        tdata.clear();
        sib.setSalesType("All");
        sib.setReType("All");
        sib.setRoute("All");
        sib.setPlace("All");
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
    public void dateSelect(SelectEvent event) {
        sib.setDate2((Date)event.getObject());
        GetData();

    }
    public void todateSelect(SelectEvent event) {
        sib.setDate1((Date)event.getObject());
        GetData();
    }
     public void print(){
      //  Queries.ivoiceCurrPdfData(tdata);
    //    try {   
        String stype= "", repre= "", rotid= "",city= "";
              
              stype =  sib.getSalesType();
            System.out.println(stype);
              if(stype.equalsIgnoreCase("All"))
              {
              stype = "%";
              }
              
              repre =  sib.getReType();
                    System.out.println(repre);
               if(repre.equalsIgnoreCase("All"))
              {
              repre = "%";
              }
               
              city = sib.getPlace();
                    System.out.println(city);
               if(city.equalsIgnoreCase("All"))
              {
              city = "%";
              }
          rotid = sib.getRoute();
                    System.out.println(rotid);
               if(rotid.equalsIgnoreCase("All"))
              {
                  rotid = "%";
              }
            System.out.println(tdata.size());
                 SalesInvoicePDF ob = new  SalesInvoicePDF();
              ob.execute(stype, repre, rotid,city,df.format(sib.getDate2()), df.format(sib.getDate1()),tdata,amount);
        System.out.println("-----------------"+tdata.size());
                    
               // } catch (Exception e) {
               //     e.printStackTrace(); 
                    
            //    }
    } 

    public void setSib(SalesInvoiceBean sib) {
        this.sib = sib;
    }

    public SalesInvoiceBean getSib() {
        return sib;
    }

    public void setStb(SalesInvoiceTableBean stb) {
        this.stb = stb;
    }

    public SalesInvoiceTableBean getStb() {
        return stb;
    }

    public void setTdata(List<SalesInvoiceTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<SalesInvoiceTableBean> getTdata() {
        return tdata;
    }

    public void setTaxList(List taxList) {
        this.taxList = taxList;
    }

    public List getTaxList() {
        return taxList;
    }

    public void setRepresentativeList(List representativeList) {
        this.representativeList = representativeList;
    }

    public List getRepresentativeList() {
        return representativeList;
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

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

   

    public void setDetails(List details) {
        this.details = details;
    }

    public List getDetails() {
        return details;
    }
}
