package ReportsModule.ManagedBeanPakage;


import ReportsModule.BeanPakage.CFormByRepBean;
import ReportsModule.BeanPakage.CFormByRepTableBean;

import ReportsModule.DBConnection.DBConnection;

import ReportsModule.PDFReports.CFormByRepPDF;

import ReportsModule.Queries.Queries;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "cfr", eager = true)
@ViewScoped
public class CFormByRep {
    
    private CFormByRepBean sd = new CFormByRepBean();
    private CFormByRepTableBean std;
    private List<CFormByRepTableBean> tdata;
    DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    List mp = new ArrayList();
    List mp1 = new ArrayList();
    Double amt=0.0;
    
    public CFormByRep() {
        super();
        
        std = new CFormByRepTableBean();
        tdata = new ArrayList<CFormByRepTableBean>();
        sd.setRep("ALL");
        sd.setYr("2013");
        
        
        // taxdata1();
        Date d = new Date();
        String date = df.format(d);
           

        try {
            sd.setDate1(df.parse(date));
            sd.setDate2(df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        yearData();
        addRepresents();
    }
    
    public void yearData() {
        mp = Queries.yearData();
    }

    
    public void addRepresents() {
        mp1=Queries.representData();
   
    }
    
    
    public void tabledata(){
        System.out.println("in tabledata()");
        tdata = Queries.crepData(sd);
        amt=DBConnection.getAmount();
        System.out.println("Total Amount is-----"+amt);
           
    }
    
    public void typeChanged(){
        System.out.println("in typeChanged()");
        tabledata();
    }
    
    public void print(){
        System.out.println("in print()");
        CFormByRepPDF cp=new CFormByRepPDF();
        cp.execute(tdata,amt);
      
        
    }
    
    public void refresh(){
        System.out.println("in refresh()");
        tdata.clear();
       
        
    }

    public void setSd(CFormByRepBean sd) {
        this.sd = sd;
    }

    public CFormByRepBean getSd() {
        return sd;
    }

    public void setStd(CFormByRepTableBean std) {
        this.std = std;
    }

    public CFormByRepTableBean getStd() {
        return std;
    }

    public void setTdata(List<CFormByRepTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<CFormByRepTableBean> getTdata() {
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
