package ReportsModule.ManagedBeanPakage;

import ReportsModule.BeanPakage.ItemPdfReportBean;
import ReportsModule.BeanPakage.SalesItemChartBean;
import ReportsModule.BeanPakage.SalesItemChartSubTableBean;
import ReportsModule.BeanPakage.SalesItemChartTableBean;

import ReportsModule.PDFReports.SalesItemBeanPDF;

import ReportsModule.Queries.Queries;

import com.sun.jndi.ldap.EntryChangeResponseControl;

import java.awt.Desktop;

import java.io.File;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean(name = "itemchart",eager = true)
@ViewScoped
public class SalesItemChart {
    private SalesItemChartBean sicb;
    private List<ItemPdfReportBean>chartData;
    private List<SalesItemChartTableBean>tdata;
    private List<SalesItemChartSubTableBean>tdata1;
    DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private List sList=new ArrayList();
    private List rList=new ArrayList();
    private List repList=new ArrayList();
    private List itemList1=new ArrayList();
    private List itemList4=new ArrayList();
    private List itemList5=new ArrayList();
    private List itemList6=new ArrayList();
    private List itemList2=new ArrayList();
    List itemList3=new ArrayList();
    private String itemname;
    private List itemList=new ArrayList();
    private PieChartModel pieModel,pieModel1,pieModel2; 
    private Map<Integer,String> mp1=new HashMap<Integer,String>();
    private Boolean b1=false,b2=false,b3=false;
    private String s1,s2;
    
    public SalesItemChart() {
        super();
        sicb=new SalesItemChartBean();
        tdata=new ArrayList<SalesItemChartTableBean>();
        tdata1=new ArrayList<SalesItemChartSubTableBean>();
        chartData=new ArrayList<ItemPdfReportBean>();
       pieModel=new PieChartModel();
        pieModel1=new PieChartModel();
        sicb.setIgroup("All");
        sicb.setRepresentative("All");
        sicb.setRoute("All");
        sicb.setStype("All");
        salesList();
        representativeList();
        routeList();
        itemgroupList();
        Date d = new Date();
        String date = df.format(d);
        try {
            sicb.setDate1( df.parse(date));
            sicb.setDate2( df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }   
        //DataCharta();
    }
    public void salesList(){
        sList=Queries.taxData();
    }
    public void representativeList(){
        repList=Queries.representData();
        
    }
    public void routeList(){
        rList=Queries.routeListData(sicb);
    }
    public void itemgroupList(){
     itemList=Queries.itemgroupListData();
   
   /*   int j=0;
     for(int i=0;i<itemList.size();i++){
         String s = (String)itemList.get(i);
                if(!s.equalsIgnoreCase("All")){
         System.out.println(s);
                mp1.put(j, s);
j++;
            }
     } */
    
    }
    public void tabeData(){
        tdata=Queries.saleschartData(sicb);
    }
    public void refresh(){
        setB1(false);
        setB2(false);
        setB3(false);
        sicb.setDate2(null);
        sicb.setStype("All");
        sicb.setRepresentative("All");
        sicb.setRoute("All");
        sicb.setIgroup("All");
        tdata.clear();
        itemList1.clear();
         itemList2.clear();
         itemList3.clear();
         itemList4.clear();
         itemList5.clear();
         itemList6.clear();
     pieModel.clear();
     pieModel1.clear();
     pieModel2.clear();
        chartData.clear();
 
     }
    public void print(){
        //pdfData();
      
        if(chartData.isEmpty()){
        
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Graph Data Empty",new FacesMessage("Graph Data Empty, Press Graph  Button For Generating Graph "));
       
        }
            else{
            String d1,d2;
            d1=df.format(sicb.getDate1());
            d2=df.format(sicb.getDate2());
            String stype=sicb.getStype();
            String rtype=sicb.getRepresentative();
            String route=sicb.getRoute();
            System.out.println("route value is "+route);
            String igrp=sicb.getIgroup();
            SalesItemBeanPDF ob=new SalesItemBeanPDF();
        ob.execute(d2, d1, stype, rtype, route, s1, s2, igrp, chartData);
        }
    
    }
    public void dateSelect(SelectEvent event) {
        sicb.setDate2((Date)event.getObject());
       
    }
    public void todateSelect(SelectEvent event) {
        sicb.setDate1((Date)event.getObject());
     
    }
    
    public void typeChanged(){
        tabeData();    
    }
    public void repChanged(){
        routeList();
        tabeData();
    }
    public void routeChanged(){
     
        tabeData();
    }
    
    
    
    public void itemChanged(){
        System.out.println("route changed.......................................................................");
        tabeData();
    }
    public void DataCharta(){
        setB1(true);
        String ss;
        pieModel=Queries.DataCharta(sicb);
        for (Map.Entry<String, Number> e : pieModel.getData().entrySet()) {
            String key = e.getKey();
            int x=key.indexOf("[");
            ss =key.substring(0, x);
            itemList1.add(ss);
            System.out.println("value..............."+itemList1);
            itemList4.add(e.getValue());
        }
  
 
    }
    public void itemSelect(ItemSelectEvent event){
        setB2(true);
        String ss;
        int z=event.getItemIndex();
        System.out.println(z);
        System.out.println(itemList1);
         s1 = (String)itemList1.get(z);
         System.out.println("value of cat  s is"+s1);
        pieModel1=Queries.dataChartb(sicb, s1); 
        System.out.println(pieModel1);
        for (Map.Entry<String, Number> e : pieModel1.getData().entrySet()) {
            String key = e.getKey();
            int x=key.indexOf("[");
            ss =key.substring(0, x);
            itemList2.add(ss);
            System.out.println("ite 2 value is"+itemList2);
            itemList5.add(e.getValue());
        }
        
    }
    
    public void pdfData(){
        ItemPdfReportBean iprb;
      
     //   int l=itemList1.size()+itemList2.size()+itemList3.size();
        for(int i=0;i<itemList1.size();i++){
            iprb=new  ItemPdfReportBean();
            iprb.setKey1((String)itemList1.get(i));
           iprb.setValue1((Number)itemList4.get(i));
            iprb.setKey2("");
            iprb.setValue2(0);
            iprb.setKey3("");
            iprb.setValue3(0);
            
            chartData.add(iprb);
        }
       // }
           for(int j=0;j<itemList2.size();j++){
              iprb=new  ItemPdfReportBean();
              iprb.setKey1("");
              iprb.setValue1(0);
            iprb.setKey2((String)itemList2.get(j));
            iprb.setValue2((Number)itemList5.get(j));
            
              iprb.setKey3("");
              iprb.setValue3(0);
              chartData.add(iprb);
          }
         for(int l=0;l<itemList3.size();l++){
               iprb=new  ItemPdfReportBean();
               iprb.setKey1("");
               iprb.setValue1(0);
               iprb.setKey2("");
               iprb.setValue2(0);
            iprb.setKey3((String)itemList3.get(l));
            iprb.setValue3((Number)itemList6.get(l));
             
               chartData.add(iprb);
           }
         //   chartData.add(iprb);
    //    }
     
    }
    public void subitemSelect(ItemSelectEvent event){
        setB3(true);
        String ss;
        int z=event.getItemIndex();
        System.out.println(z);
         s2 = (String)itemList2.get(z);
         System.out.println("value of d s is"+s2);
        pieModel2=Queries.dataChartc(sicb, s2); 
        System.out.println(pieModel2);
        for (Map.Entry<String, Number> e : pieModel2.getData().entrySet()) {
            String key = e.getKey();
            int x=key.indexOf("[");
            ss =key.substring(0, x);
            itemList3.add(ss);
            System.out.println("item list 3  value is "+itemList3);
            itemList6.add(e.getValue());
        }
        pdfData();
    }
    public void msubitemSelect(ItemSelectEvent event){
       
        int z=event.getItemIndex();
        System.out.println(z);
         itemname = (String)itemList3.get(z);
         System.out.println("value of s is...."+itemname);
         tdata1=Queries.subTableData(sicb, itemname);
         System.out.println("value of tdata 1"+tdata1);
    }
    
    
    
    
    
    public void setSicb(SalesItemChartBean sicb) {
        this.sicb = sicb;
    }

    public SalesItemChartBean getSicb() {
        return sicb;
    }

    public void setTdata(List<SalesItemChartTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<SalesItemChartTableBean> getTdata() {
        return tdata;
    }

    public void setSList(List sList) {
        this.sList = sList;
    }

    public List getSList() {
        return sList;
    }

    public void setRList(List rList) {
        this.rList = rList;
    }

    public List getRList() {
        return rList;
    }

    public void setRepList(List repList) {
        this.repList = repList;
    }

    public List getRepList() {
        return repList;
    }

    public void setItemList(List itemList) {
        this.itemList = itemList;
    }

    public List getItemList() {
        return itemList;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setB1(Boolean b1) {
        this.b1 = b1;
    }

    public Boolean getB1() {
        return b1;
    }

    public void setPieModel1(PieChartModel pieModel1) {
        this.pieModel1 = pieModel1;
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public void setB2(Boolean b2) {
        this.b2 = b2;
    }

    public Boolean getB2() {
        return b2;
    }

    public void setB3(Boolean b3) {
        this.b3 = b3;
    }

    public Boolean getB3() {
        return b3;
    }

    public void setPieModel2(PieChartModel pieModel2) {
        this.pieModel2 = pieModel2;
    }

    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    public void setTdata1(List<SalesItemChartSubTableBean> tdata1) {
        this.tdata1 = tdata1;
    }

    public List<SalesItemChartSubTableBean> getTdata1() {
        return tdata1;
    }

    public void setChartData(List<ItemPdfReportBean> chartData) {
        this.chartData = chartData;
    }

    public List<ItemPdfReportBean> getChartData() {
        return chartData;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS1() {
        return s1;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public String getS2() {
        return s2;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemname() {
        return itemname;
    }
}
