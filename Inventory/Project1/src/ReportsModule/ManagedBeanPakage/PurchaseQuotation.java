package ReportsModule.ManagedBeanPakage;


import ReportsModule.BeanPakage.PurchaseQuotationBean;
import ReportsModule.BeanPakage.PurchaseQuotationTable;

import ReportsModule.PDFReports.PurchaseQuotationPDF;

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


@ManagedBean(name = "purquot", eager = true)
@ViewScoped

public class PurchaseQuotation {
    private PurchaseQuotationBean sd = new PurchaseQuotationBean();
    private PurchaseQuotationTable std;
    private List<PurchaseQuotationTable> tdata;
    DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    List mp = new ArrayList();
    private Double net=0.0;
    public PurchaseQuotation() {
        super();
        std = new PurchaseQuotationTable();
        tdata = new ArrayList<PurchaseQuotationTable>();
        sd.setType("All");
        
        taxdata();
        Date d = new Date();
        String date = df.format(d);
        //  ServletContext ctx = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
        //   String realPath = ctx.getRealPath("/");
        //System.out.println("the realpathis" + realPath + "reports" + File.separator + "SalesDatesPDF.jrxml");
        //  System.out.println("the realpathis" + realPath + "images" + File.separator + "ssi-logo.jpg");

        try {
            sd.setDate1(df.parse(date));
            sd.setDate2(df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //    typeChanged();
        tabledata();
    }
    
    public void dateSelect(SelectEvent event) {
        sd.setDate2((Date)event.getObject());
        tabledata();
    }
    public void todateSelect(SelectEvent event) {
        sd.setDate1((Date)event.getObject());
        tabledata();
    }

    public void typeChanged() {
        tabledata();
    }
    
    
    public void tabledata() {

        tdata = Queries.purData(sd);
        
            if(net!=0){
                net=0.0;
            }

            
            for(int i=0;i<tdata.size();i++){
                net=net+tdata.get(i).getNet();
            }
       
    }
    
    public void print() {
        String ite = sd.getType();
        if (ite.equalsIgnoreCase("All")) {
            ite = "%";
        }

        PurchaseQuotationPDF ob = new PurchaseQuotationPDF();

        try {
            ob.execute(df.format(sd.getDate2()), df.format(sd.getDate1()), ite,tdata,net);
            } catch (JRException e) {
            e.printStackTrace();
        }
    }
    
    
    public void refresh() {
       
        sd.setDate2(null);
        sd.setType("All");
        sd.setAmount(0.0);
        tdata.clear();
    }
    
    public void taxdata() {
        mp = Queries.taxData();
      
    }

    public void setSd(PurchaseQuotationBean sd) {
        this.sd = sd;
    }

    public PurchaseQuotationBean getSd() {
        return sd;
    }

    public void setStd(PurchaseQuotationTable std) {
        this.std = std;
    }

    public PurchaseQuotationTable getStd() {
        return std;
    }

    public void setTdata(List<PurchaseQuotationTable> tdata) {
        this.tdata = tdata;
    }

    public List<PurchaseQuotationTable> getTdata() {
        return tdata;
    }

    public void setMp(List mp) {
        this.mp = mp;
    }

    public List getMp() {
        return mp;
    }
}
