package ReportsModule.ManagedBeanPakage;


import ReportsModule.BeanPakage.SalesCurrentStockBean;
import ReportsModule.BeanPakage.SalesCurrentStockTableBean;

import ReportsModule.PDFReports.SalesCurrentStockPDF;

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


@ManagedBean(name = "scs", eager = true)
@ViewScoped

public class SalesCurrentStock {
    
    public SalesCurrentStockBean sd = new SalesCurrentStockBean();
    private SalesCurrentStockTableBean std;
    private List<SalesCurrentStockTableBean> tdata;
    DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
   
    List mp = new ArrayList();
    
    public SalesCurrentStock() {
        super();
        std = new SalesCurrentStockTableBean();
        tdata = new ArrayList<SalesCurrentStockTableBean>();
        sd.setItemname("ALL");
        
        
        itemData();
        Date d = new Date();                                                    
        String date = df.format(d);
           

        try {
            sd.setDate1(df.parse(date));
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tabledata();
    }
  
    public void itemData() {
        System.out.println("in itemdata()");

        mp = Queries.itemData();

    }
    public void tabledata() {
        System.out.println("in table data"+"----------"+sd.getItemname());
       

        tdata = Queries.item(sd);
    }
    
    public void dateSelect(SelectEvent event) {
        sd.setDate1((Date)event.getObject());
      //  tabledata();
    }
    public void typeChanged(){
        tabledata();
    }
    
    public void print(){
        System.out.println("in print ()");
        SalesCurrentStockPDF sc=new SalesCurrentStockPDF();
        sc.execute(tdata);
    }
    
    public void refresh(){
        System.out.println("in refresh ()");
        tdata.clear();
    }

    public void setSd(SalesCurrentStockBean sd) {
        this.sd = sd;
    }

    public SalesCurrentStockBean getSd() {
        return sd;
    }

    public void setStd(SalesCurrentStockTableBean std) {
        this.std = std;
    }

    public SalesCurrentStockTableBean getStd() {
        return std;
    }

    public void setTdata(List<SalesCurrentStockTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<SalesCurrentStockTableBean> getTdata() {
        return tdata;
    }

    public void setMp(List mp) {
        this.mp = mp;
    }

    public List getMp() {
        return mp;
    }
}
