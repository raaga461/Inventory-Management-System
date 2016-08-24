package ReportsModule.ManagedBeanPakage;


import ReportsModule.BeanPakage.PurchaseItemStockBean;
import ReportsModule.BeanPakage.PurchaseItemStockTableBean;

import ReportsModule.PDFReports.PurchaseItemStockPDF;

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


@ManagedBean(name = "pst", eager = true)
@ViewScoped
public class PurchaseItemStock {
    
    public PurchaseItemStockBean sd = new PurchaseItemStockBean();
    private PurchaseItemStockTableBean std;
    private List<PurchaseItemStockTableBean> tdata;
    DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");    
    List mp = new ArrayList();
    
    public PurchaseItemStock() {
        super();
        
        std = new PurchaseItemStockTableBean();
        tdata = new ArrayList<PurchaseItemStockTableBean>();
        sd.setItemname("ALL");
        
        
        cate();
        Date d = new Date();                                                    
        String date = df.format(d);
           

        try {
            sd.setDate1(df.parse(date));
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tabledata();
        
    }
    
    public void cate() {
        System.out.println("in cate()");

        mp = Queries.types1();

    }
    public void tabledata() {
        System.out.println("in table data"+"----------"+sd.getItemname());
       

       tdata = Queries.pist(sd);
    }
    
    public void print(){
        System.out.println("in print()");
        PurchaseItemStockPDF ps=new PurchaseItemStockPDF();
        ps.execute(tdata);
    }
    
    public void refresh(){
        System.out.println("in refresh()");
        tdata.clear();
    }
    
    public void typeChanged(){
        System.out.println("in typeChanged()");
        tabledata();
        
    }
    
    public void dateSelect(SelectEvent event) {
        sd.setDate1((Date)event.getObject());
        //tabledata();
    }

    public void setSd(PurchaseItemStockBean sd) {
        this.sd = sd;
    }

    public PurchaseItemStockBean getSd() {
        return sd;
    }

    public void setStd(PurchaseItemStockTableBean std) {
        this.std = std;
    }

    public PurchaseItemStockTableBean getStd() {
        return std;
    }

    public void setTdata(List<PurchaseItemStockTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<PurchaseItemStockTableBean> getTdata() {
        return tdata;
    }

    public void setMp(List mp) {
        this.mp = mp;
    }

    public List getMp() {
        return mp;
    }
}
