package ReportsModule.ManagedBeanPakage;

import ReportsModule.BeanPakage.SalesItemGranSummaryTableBean;
import ReportsModule.BeanPakage.SalesItemGrandSummaryBean;

import ReportsModule.Queries.Queries;

import java.io.Serializable;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

@ManagedBean(name ="item",eager = true)
@ViewScoped
public class SalesItemGrandSummary {
    
    private SalesItemGrandSummaryBean sgb;
    private List<SalesItemGranSummaryTableBean>tdata;
    private List list1=new ArrayList();
    private List list2=new ArrayList();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    
    
    
    public SalesItemGrandSummary() {
        super();
        sgb=new SalesItemGrandSummaryBean();
        tdata=new ArrayList<SalesItemGranSummaryTableBean>();
        Date d = new Date();
        String date = df.format(d);
        sgb.setStype("All");
        taxData();
        colData();
        try {
            sgb.setDate1( df.parse(date));
            sgb.setDate2( df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }   
    }
    public void  taxData(){
        list1=Queries.taxData();
    }
    public void  colData(){
        System.out.println(list2);
        list2=Queries.representData();
        list2.remove("All");
      
        System.out.println(list2);
    }
    public void tableData(){
        System.out.println("dcwd");
    }
    public void refresh(){
        sgb.setDate2(null);
        sgb.setStype("All");
        tdata.clear();
    }
  
    
    
    
    
    public void print() {
        System.out.println("print function");
    }
    public void dateSelect(SelectEvent event) {
        sgb.setDate2((Date)event.getObject());
       
    }
    public void todateSelect(SelectEvent event) {
        sgb.setDate1((Date)event.getObject());
     
    }
    public void setSgb(SalesItemGrandSummaryBean sgb) {
        this.sgb = sgb;
    }

    public SalesItemGrandSummaryBean getSgb() {
        return sgb;
    }

    public void setTdata(List<SalesItemGranSummaryTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<SalesItemGranSummaryTableBean> getTdata() {
        return tdata;
    }

    public void setList1(List list1) {
        this.list1 = list1;
    }

    public List getList1() {
        return list1;
    }

    public void setList2(List list2) {
        this.list2 = list2;
    }

    public List getList2() {
        return list2;
    }
}
