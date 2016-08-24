package ReportsModule.ManagedBeanPakage;


import ReportsModule.BeanPakage.CformStatusByDateBean;
import ReportsModule.BeanPakage.CformStatusByDateBillTableBean;
import ReportsModule.BeanPakage.CformStatusByDateItemBean;
import ReportsModule.BeanPakage.CformStatusByDateTableBean;

import ReportsModule.PDFReports.CformStatusByDatePDF;

import ReportsModule.Queries.Queries;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.event.UnselectEvent;


@ManagedBean(name = "formbydate",eager = true)
@ViewScoped
public class CformStatusByDate {
    private CformStatusByDateBean cbd;
    private CformStatusByDateItemBean cib;
    private List<CformStatusByDateItemBean> iData;
    private List<CformStatusByDateBillTableBean>tBill;
    private CformStatusByDateBillTableBean cbtb,selectedcib;
    private CformStatusByDateTableBean cbt,selectedCbt;
    private List<CformStatusByDateTableBean>tdata;
    DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
 private  Double amount1=0.0,amount2=0.0,amount3=0.0; 
    public CformStatusByDate() {
        super();
        cbtb=new CformStatusByDateBillTableBean();
        selectedcib=new CformStatusByDateBillTableBean();
       iData=new ArrayList<CformStatusByDateItemBean>();
       cib=new CformStatusByDateItemBean();
        tdata=new ArrayList<CformStatusByDateTableBean>();
         cbd=new CformStatusByDateBean();   
         cbt=new CformStatusByDateTableBean();
        tBill=new ArrayList<CformStatusByDateBillTableBean>();
        selectedCbt=new CformStatusByDateTableBean();
        Date d = new Date();
        String date = df.format(d);
        try {
            cbd.setDate2(df.parse(date));
            cbd.setDate1( df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cbd.setCName("");
    }
     public void billData(){
       
       // String d1, d2;
     //   d1 = df.format(cbd.getDate1());
     //   d2 = df.format(cbd.getDate2());
       // cbd.setDataRange(((d2+" , "+d1)));
       ///  cbd.setAccCode(selectedCbt.getAccCode());
       //  cbd.setCustDetails(selectedCbt.getAccName());
        tBill=Queries.billData(cbd, selectedCbt);
        if(amount1!=0){
            amount1=0.0;
        }
        for(int i=0;i<tBill.size();i++){
            amount1=amount1+Double.parseDouble(tBill.get(i).getInvAmt());
        }
        
    } 
    public void tableData(){
        tdata=Queries.cformTableData(cbd);
    }
    public void onRowToggle(ToggleEvent event) {  
       selectedCbt=((CformStatusByDateTableBean)event.getData());
           billData();
        }  
    public void itemData(){
        cbd.setDate(selectedcib.getBillDate());
         cbd.setBillno(selectedcib.getBillNo());
         cbd.setCName(selectedcib.getAcName());
        iData=Queries.itemTableData(selectedcib);
      
    }
    public void displayData(){
         
       String s= cbd.getCName();
       System.out.println(s);
       tableData();
    }
   
   public void dateSelect(SelectEvent event) {
        cbd.setDate2((Date)event.getObject());
        tableData();
     //   GetData();

    }
   public void refresh(){
       cbd.setDate2(null);
       cbd.setCName("");
       tdata.clear();
       tBill.clear();
       iData.clear();
   }
    public void todateSelect(SelectEvent event) {
        cbd.setDate1((Date)event.getObject());
       // tableData();
        //GetData();
    }
    public void print(){
        CformStatusByDatePDF ob=new CformStatusByDatePDF();
        ob.execute(df.format(cbd.getDate2()), df.format(cbd.getDate1()), tdata);
    }

    public void setCbd(CformStatusByDateBean cbd) {
        this.cbd = cbd;
    }

    public CformStatusByDateBean getCbd() {
        return cbd;
    }

    public void setCbt(CformStatusByDateTableBean cbt) {
        this.cbt = cbt;
    }

    public CformStatusByDateTableBean getCbt() {
        return cbt;
    }

    public void setTdata(List<CformStatusByDateTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<CformStatusByDateTableBean> getTdata() {
        return tdata;
    }

    public void setSelectedCbt(CformStatusByDateTableBean selectedCbt) {
        this.selectedCbt = selectedCbt;
    }

    public CformStatusByDateTableBean getSelectedCbt() {
        return selectedCbt;
    }

    public void setTBill(List<CformStatusByDateBillTableBean> tBill) {
        this.tBill = tBill;
    }

    public List<CformStatusByDateBillTableBean> getTBill() {
        return tBill;
    }

    public void setCib(CformStatusByDateItemBean cib) {
        this.cib = cib;
    }

    public CformStatusByDateItemBean getCib() {
        return cib;
    }



    public void setIData(List<CformStatusByDateItemBean> iData) {
        this.iData = iData;
    }

    public List<CformStatusByDateItemBean> getIData() {
        return iData;
    }

    public void setCbtb(CformStatusByDateBillTableBean cbtb) {
        this.cbtb = cbtb;
    }

    public CformStatusByDateBillTableBean getCbtb() {
        return cbtb;
    }

    public void setSelectedcib(CformStatusByDateBillTableBean selectedcib) {
        this.selectedcib = selectedcib;
    }

    public CformStatusByDateBillTableBean getSelectedcib() {
        return selectedcib;
    }

    public void setAmount1(Double amount1) {
        this.amount1 = amount1;
    }

    public Double getAmount1() {
        return amount1;
    }

    public void setAmount2(Double amount2) {
        this.amount2 = amount2;
    }

    public Double getAmount2() {
        return amount2;
    }

    public void setAmount3(Double amount3) {
        this.amount3 = amount3;
    }

    public Double getAmount3() {
        return amount3;
    }
}
