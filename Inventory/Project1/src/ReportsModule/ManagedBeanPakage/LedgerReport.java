package ReportsModule.ManagedBeanPakage;


import ReportsModule.BeanPakage.LedgerReportBean;
import ReportsModule.BeanPakage.LedgerReportTableData;

import ReportsModule.PDFReports.LedgerReportCompanyPDF;

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


@ManagedBean(name = "ledger", eager = true)
@ViewScoped

public class LedgerReport {
    
    private LedgerReportBean sd = new LedgerReportBean();
    private LedgerReportTableData std;
    private List<LedgerReportTableData> tdata;
    DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private Double amt=0.0,amt1=0.0;
    List mp = new ArrayList();
    
    
    public LedgerReport() {
        super();
        System.out.println("LedgerReport Object is created");
        
        std = new LedgerReportTableData();
        tdata = new ArrayList<LedgerReportTableData>();
        sd.setParty("ALL");
        
        
        taxData();
        Date d = new Date();
        String date = df.format(d);
           

        try {
            sd.setDate1(df.parse(date));
            sd.setDate2(df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        tabledata();
    }
    
    
    public void tabledata() {
        
        System.out.println("in tabledata()  "+"--------->"+sd.getParty());
       

        tdata = Queries.tableData1(sd);
            if(amt!=0){
                amt=0.0;
            }
            if(amt1!=0){
                amt1=0.0;
            }

            
            for(int i=0;i<tdata.size();i++){
                amt=amt+tdata.get(i).getDebit();
            }
            
            for(int i=0;i<tdata.size();i++){
                amt1=amt1+tdata.get(i).getCredit();
            }
            
       
    }
    
    public void print() {
        
        String ite = sd.getParty();
        if (ite.equalsIgnoreCase("SELECT")) {
            ite = "%";
        }
        System.out.println(" in print()--------  "+ite);

        LedgerReportCompanyPDF ob = new LedgerReportCompanyPDF();

        try {
            ob.execute(df.format(sd.getDate2()), df.format(sd.getDate1()), ite,tdata,amt,amt1);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
    
    public void refresh() {
       
        sd.setDate2(null);
        sd.setParty("SELECT");
        
        tdata.clear();
    }
    
    public void typeChanged() {
        tabledata();
    }
    
    public void taxData() {
        mp = Queries.taxData();
    }
    
    public void dateSelect(SelectEvent event) {
        sd.setDate2((Date)event.getObject());
        tabledata();
    }
    public void todateSelect(SelectEvent event) {
        sd.setDate1((Date)event.getObject());
        tabledata();
    }
    
    

    public void setSd(LedgerReportBean sd) {
        this.sd = sd;
    }

    public LedgerReportBean getSd() {
        return sd;
    }

    public void setStd(LedgerReportTableData std) {
        this.std = std;
    }

    public LedgerReportTableData getStd() {
        return std;
    }

    public void setTdata(List<LedgerReportTableData> tdata) {
        this.tdata = tdata;
    }

    public List<LedgerReportTableData> getTdata() {
        return tdata;
    }

    public void setMp(List mp) {
        this.mp = mp;
    }

    public List getMp() {
        return mp;
    }

   
}
