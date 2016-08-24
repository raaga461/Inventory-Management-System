package ReportsModule.ManagedBeanPakage;


import ReportsModule.BeanPakage.LedgerBean;
import ReportsModule.BeanPakage.LedgerTableData;

import ReportsModule.PDFReports.LedgerSummaryPDF;

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


@ManagedBean(name = "ledsum", eager = true)
@ViewScoped
public class LedgerSummary {
    
    private LedgerBean lb=new LedgerBean();
    private LedgerTableData ltd;
    private List<LedgerTableData> ldata;
    DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    
    private Double amt=0.0,amt1=0.0;
    
    public LedgerSummary() {
        super();
        
        ltd = new LedgerTableData();
        ldata = new ArrayList<LedgerTableData>();
        
        
        //taxdata();
        Date d = new Date();
        System.out.println(d);
            
        String date = df.format(d);
        System.out.println(date);
        
        try {
            lb.setDate1(df.parse(date));
            lb.setDate2(df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
       
        //tabledata();
    }
    
    public void dateSelect(SelectEvent event) {
        lb.setDate2((Date)event.getObject());
        tabledata();
    }
    public void todateSelect(SelectEvent event) {
        lb.setDate1((Date)event.getObject());
        tabledata();
    }
    
    
    public void print() {
        System.out.println("in print()");
       

        LedgerSummaryPDF ob = new LedgerSummaryPDF();

        try {
            ob.execute(df.format(lb.getDate2()), df.format(lb.getDate1()),ldata,amt,amt1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void refresh() {
        System.out.println("in refresh ()");
       
        lb.setDate2(null);
        
        ldata.clear();
    }
    
    public void tabledata() {
        
        System.out.println();

        ldata = Queries.ledTableData1(lb);
        if(amt!=0){
            amt=0.0;
        }
        if(amt1!=0){
            amt1=0.0;
        }

        
        for(int i=0;i<ldata.size();i++){
            amt=amt+ldata.get(i).getDebit();
        }
       
        for(int i=0;i<ldata.size();i++){
            amt1=amt1+ldata.get(i).getCredit();
        }
        
    }

    public void setLb(LedgerBean lb) {
        this.lb = lb;
    }

    public LedgerBean getLb() {
        return lb;
    }

    public void setLdata(List<LedgerTableData> ldata) {
        this.ldata = ldata;
    }

    public List<LedgerTableData> getLdata() {
        return ldata;
    }
}
