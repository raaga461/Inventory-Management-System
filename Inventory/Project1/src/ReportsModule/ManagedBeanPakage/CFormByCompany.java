package ReportsModule.ManagedBeanPakage;


import ReportsModule.BeanPakage.CFormByCompanyBean;
import ReportsModule.BeanPakage.CFormByCompanyTableBean;

import ReportsModule.DBConnection.DBConnection;

import ReportsModule.PDFReports.CFormByCompanyPDF;

import ReportsModule.Queries.Queries;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "cfc", eager = true)
@ViewScoped
public class CFormByCompany {
   
    
    private CFormByCompanyBean sd = new CFormByCompanyBean();
    private CFormByCompanyTableBean std;
    private List<CFormByCompanyTableBean> tdata;
    DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    List mp = new ArrayList();
    List mp1 = new ArrayList();
    Double amt=0.0;
    
    
    public CFormByCompany() {
        super();
        
        std = new CFormByCompanyTableBean();
        tdata = new ArrayList<CFormByCompanyTableBean>();
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
        representData();
        
        //tabledata();
    }
    
    
    public void yearData() {
        mp = Queries.yearData();
    }

    
    public void representData() {
        System.out.println("in taxdata1()");

        mp1 = Queries.representData1();

    }
    
    public void tabledata(){
        System.out.println("in tabledata()");
        tdata = Queries.compData(sd);
        amt=DBConnection.getAmount();
        System.out.println("Total Amount is-----"+amt);
           
    }
    public void typeChanged(){
        System.out.println("in typeChanged()");
        tabledata();
    }
    public void print(){
        System.out.println("in print()");
        CFormByCompanyPDF cp=new CFormByCompanyPDF();
        cp.execute(tdata,amt);
        
    }
    
    public void refresh(){
        System.out.println("in refresh()");
        tdata.clear();
       
        
    }

    public void setSd(CFormByCompanyBean sd) {
        this.sd = sd;
    }

    public CFormByCompanyBean getSd() {
        return sd;
    }

    public void setStd(CFormByCompanyTableBean std) {
        this.std = std;
    }

    public CFormByCompanyTableBean getStd() {
        return std;
    }

    public void setTdata(List<CFormByCompanyTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<CFormByCompanyTableBean> getTdata() {
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
