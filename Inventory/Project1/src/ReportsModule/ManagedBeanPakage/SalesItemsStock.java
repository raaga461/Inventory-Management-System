package ReportsModule.ManagedBeanPakage;


import ReportsModule.BeanPakage.SalesStockBean;
import ReportsModule.BeanPakage.SalesStockTableBean;

import ReportsModule.PDFReports.SalesStockItemsPDF;

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


@ManagedBean(name = "sis", eager = true)
@ViewScoped
public class SalesItemsStock {
    
    private SalesStockBean sd = new SalesStockBean();
    private SalesStockTableBean std;
    private List<SalesStockTableBean> tdata;
    DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    List mp = new ArrayList();
    
    public SalesItemsStock() {
        super();
        
        std = new SalesStockTableBean();
        tdata = new ArrayList<SalesStockTableBean>();
        sd.setCategory("ALL");
        
        
        types();
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
       

        tdata = Queries.salesStock(sd);          
       
    }
    
    public void print(){
        System.out.println("in print()");
        SalesStockItemsPDF ss=new SalesStockItemsPDF();
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
    
    public void types() {
        System.out.println("in taxdata1()");

        mp = Queries.types();

    }
    
    public void dateSelect(SelectEvent event) {
        sd.setDate2((Date)event.getObject());
        tabledata();
    }
    public void todateSelect(SelectEvent event) {
        sd.setDate1((Date)event.getObject());
        tabledata();
    }


    public void setSd(SalesStockBean sd) {
        this.sd = sd;
    }

    public SalesStockBean getSd() {
        return sd;
    }

    public void setStd(SalesStockTableBean std) {
        this.std = std;
    }

    public SalesStockTableBean getStd() {
        return std;
    }

    public void setTdata(List<SalesStockTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<SalesStockTableBean> getTdata() {
        return tdata;
    }

    public void setMp(List mp) {
        this.mp = mp;
    }

    public List getMp() {
        return mp;
    }
}
