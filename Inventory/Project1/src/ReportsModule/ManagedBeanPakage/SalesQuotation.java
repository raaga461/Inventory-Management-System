package ReportsModule.ManagedBeanPakage;


import ReportsModule.BeanPakage.SalesQuotationTableBean;
import ReportsModule.BeanPakage.SalesQuotationsBean;

import ReportsModule.PDFReports.SalesQuotationPDF;

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


@ManagedBean(name = "salesquot", eager = true)
@ViewScoped
public class SalesQuotation {
    
    private SalesQuotationsBean sd = new SalesQuotationsBean();
    private SalesQuotationTableBean std;
    private List<SalesQuotationTableBean> tdata;
    DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    List mp = new ArrayList();
    private Double net=0.0;
    
    
    public SalesQuotation() {
        super();
        
        std = new SalesQuotationTableBean();
        tdata = new ArrayList<SalesQuotationTableBean>();
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

        tdata = Queries.quotData(sd);
        
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

        SalesQuotationPDF ob = new SalesQuotationPDF();

        try {
            SalesQuotationTableBean s=(SalesQuotationTableBean)tdata.get(1);
            
            System.out.println("Date  -----"+s.getInvdate());
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

    public void setSd(SalesQuotationsBean sd) {
        this.sd = sd;
    }

    public SalesQuotationsBean getSd() {
        return sd;
    }

    public void setStd(SalesQuotationTableBean std) {
        this.std = std;
    }

    public SalesQuotationTableBean getStd() {
        return std;
    }

    public void setTdata(List<SalesQuotationTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<SalesQuotationTableBean> getTdata() {
        return tdata;
    }

    public void setMp(List mp) {
        this.mp = mp;
    }

    public List getMp() {
        return mp;
    }
}
