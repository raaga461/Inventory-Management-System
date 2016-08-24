package ReportsModule.ManagedBeanPakage;

import ReportsModule.BeanPakage.PurchaseReportBean;
import ReportsModule.BeanPakage.PurchaseReportTableBean;

import ReportsModule.PDFReports.PurchaseReportPDF;

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
@ManagedBean(name = "purchase",eager = true)
@ViewScoped
public class PurchaseReport {
    DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private PurchaseReportBean prb;
    private PurchaseReportTableBean prtb;
    private List<PurchaseReportTableBean>tdata;
    private List taxList=new ArrayList();
    private Double amount=0.0;
    public PurchaseReport() {
        super();
        prb=new PurchaseReportBean();
        tdata=new ArrayList<PurchaseReportTableBean>();
        Date d = new Date();
        String date = df.format(d);
        prb.setType("All");
        taxData();
        try {
            prb.setDate1(df.parse(date));
            prb.setDate2(df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void tableData(){
        tdata=Queries.purchaseData(prb);
        if(amount!=0){
            amount=0.0;
            }
        for(int i=0;i<tdata.size();i++){
            amount=amount+Double.parseDouble(tdata.get(i).getInvvalue());
        }
    }
    void taxData(){
     taxList= Queries.taxData();   
    }
    public void dateSelect(SelectEvent event) {
        prb.setDate2((Date)event.getObject());
       tableData();
    }
    public void refresh(){
        prb.setDate2(null);
        prb.setType("All");
        tdata.clear();
    }
    public void print(){
        String ite = prb.getType();

              if (ite.equalsIgnoreCase("All")) {
                  ite = "%";
              } 
                  //System.out.println("PDF ---> Iam from SELECTION");
                PurchaseReportPDF OB = new PurchaseReportPDF();

        try {
            OB.execute(df.format(prb.getDate2()), df.format(prb.getDate1()),ite,tdata,amount);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
    public void todateSelect(SelectEvent event) {
        prb.setDate1((Date)event.getObject());
        tableData();
    }
    public void typeChanged() {
        tableData();
    }

    public void setPrb(PurchaseReportBean prb) {
        this.prb = prb;
    }

    public PurchaseReportBean getPrb() {
        return prb;
    }

    public void setPrtb(PurchaseReportTableBean prtb) {
        this.prtb = prtb;
    }

    public PurchaseReportTableBean getPrtb() {
        return prtb;
    }

    public void setTdata(List<PurchaseReportTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<PurchaseReportTableBean> getTdata() {
        return tdata;
    }

    public void setTaxList(List taxList) {
        this.taxList = taxList;
    }

    public List getTaxList() {
        return taxList;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }
}
