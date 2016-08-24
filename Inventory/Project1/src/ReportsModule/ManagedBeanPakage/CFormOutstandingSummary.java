package ReportsModule.ManagedBeanPakage;


import ReportsModule.BeanPakage.CFormOutstandingSummaryBean;
import ReportsModule.BeanPakage.CFormOutstandingSummaryTableBean;

import ReportsModule.PDFReports.CFormOutstandingSummaryPDF;

import ReportsModule.Queries.Queries;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "cfs", eager = true)
@ViewScoped
public class CFormOutstandingSummary {
    
    private Double amt=0.0;
    
    private CFormOutstandingSummaryBean sd = new CFormOutstandingSummaryBean();
    private CFormOutstandingSummaryTableBean std;
    private List<CFormOutstandingSummaryTableBean> tdata;
    DecimalFormat dfr = new DecimalFormat("#.##");
    List mp = new ArrayList();
    List mp1 = new ArrayList();
    
    public CFormOutstandingSummary() {
        super();
        
        std = new CFormOutstandingSummaryTableBean();
        tdata = new ArrayList<CFormOutstandingSummaryTableBean>();
        sd.setRep("ALL");
        sd.setYear("2013");      
        yearData();
        addRepresents();
        //tabledata();
    }
    
    
    public void yearData() {
        mp = Queries.yearData();
    }

    
    public void addRepresents() {
        mp1=Queries.representData();
    
    }
    
    public void tabledata() {
        
        System.out.println("in tabledata()----  "+sd.getYear()+"--------->"+sd.getRep());
       
    
        tdata = Queries.cosData(sd);  
        if(amt!=0.0){
            amt=0.0;
        }
        for(int i=0;i<tdata.size();i++){
            amt=amt+tdata.get(i).getTotal();
        }
       
                 
       
    }
    
    public void typeChanged(){
        tabledata();
    }
    
    public void print(){
        System.out.println("in print()");
        CFormOutstandingSummaryPDF cs=new CFormOutstandingSummaryPDF();
        cs.execute(tdata);
    }
    
    public void refresh(){
        System.out.println("in refresh()");
        tdata.clear();
    }

    public void setSd(CFormOutstandingSummaryBean sd) {
        this.sd = sd;
    }

    public CFormOutstandingSummaryBean getSd() {
        return sd;
    }

    public void setStd(CFormOutstandingSummaryTableBean std) {
        this.std = std;
    }

    public CFormOutstandingSummaryTableBean getStd() {
        return std;
    }

    public void setTdata(List<CFormOutstandingSummaryTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<CFormOutstandingSummaryTableBean> getTdata() {
        return tdata;
    }

    public void setMp(List mp) {
        this.mp = mp;
    }

    public List getMp() {
        return mp;
    }

    public void setMp1(List mp1) {
        this.mp1 = mp1;
    }

    public List getMp1() {
        return mp1;
    }


    public void setAmt(Double amt) {
        this.amt = amt;
    }

    public Double getAmt() {
        return amt;
    }
}
