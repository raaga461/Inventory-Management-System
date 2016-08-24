package ReportsModule.ManagedBeanPakage;


import ReportsModule.BeanPakage.PurchaseStockBean;
import ReportsModule.BeanPakage.PurchaseStockTableBean;

import ReportsModule.PDFReports.PurchaseStockPDF;

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


@ManagedBean(name = "ps", eager = true)
@ViewScoped
public class PurchaseStock {
    
    private PurchaseStockBean sd = new PurchaseStockBean();
    private PurchaseStockTableBean std;
    private List<PurchaseStockTableBean> tdata;
    DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    List mp = new ArrayList();
    
    public PurchaseStock() {
        super();
        
        std = new PurchaseStockTableBean();
        tdata = new ArrayList<PurchaseStockTableBean>();
        sd.setCategory("ALL");
        
        
        types1();
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
        
        System.out.println("in tabledata()  "+"--------->"+sd.getCategory());
       

        tdata = Queries.purStock(sd);          
       
    }
    
    public void print(){
        System.out.println("in print()");
        PurchaseStockPDF ss=new PurchaseStockPDF();
        ss.execute(tdata);
    }
    
    
    public void refresh() {
        tdata.clear();
        try{
        sd.setDate2(df.parse(df.format(new Date())));
        sd.setCategory("ALL");
        }catch (ParseException e) {
            e.printStackTrace();
        }
        
    }
    
    public void typeChanged(){
        tabledata();
    }
    
    public void types1() {
        System.out.println("in taxdata1()");

        mp = Queries.types1();

    }
    
    public void dateSelect(SelectEvent event) {
        sd.setDate2((Date)event.getObject());
        tabledata();
    }
    public void todateSelect(SelectEvent event) {
        sd.setDate1((Date)event.getObject());
        tabledata();
    }


    public void setSd(PurchaseStockBean sd) {
        this.sd = sd;
    }

    public PurchaseStockBean getSd() {
        return sd;
    }

    public void setStd(PurchaseStockTableBean std) {
        this.std = std;
    }

    public PurchaseStockTableBean getStd() {
        return std;
    }

    public void setTdata(List<PurchaseStockTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<PurchaseStockTableBean> getTdata() {
        return tdata;
    }

    public void setMp(List mp) {
        this.mp = mp;
    }

    public List getMp() {
        return mp;
    }
}
