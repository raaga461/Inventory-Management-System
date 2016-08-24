package ReportsModule.ManagedBeanPakage;


import ReportsModule.BeanPakage.YearlySalesBean;
import ReportsModule.BeanPakage.YearlySalesTableBean;

import ReportsModule.PDFReports.YearlySalesPDF;

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


@ManagedBean(name="yearly",eager = true)
@ViewScoped
public class YearlySales {
    private YearlySalesBean ysb;
    Double amount=0.0;
    private List<YearlySalesTableBean>tdata,tdata1;
    private YearlySalesTableBean ystb;
    DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private List taxList=new ArrayList();
    private List partyList=new ArrayList();
    public YearlySales() {
        super();
         ysb=new YearlySalesBean();
        tdata=new ArrayList<YearlySalesTableBean>();
  
        taxData();
        partyData();
        ysb.setTaxType("All");
        ysb.setPartyType("All");
        Date d = new Date();
        String date = df.format(d);
        try {
            ysb.setDate1( df.parse(date));
            ysb.setDate2( df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
       // String year="2013";
    //    String q="Between".concat("  ").concat("'").concat(""+year+"").concat("-04-01").concat("'").concat("  ").concat("And").concat("  ").concat("'").concat(""+year+"").concat("-06-30").concat("'");
       // System.out.println("date is....."+q);
    }
    
    public  void taxData(){
          taxList = Queries.taxData();
      }
    public void partyData(){
        partyList=Queries.partyData();
    }
   public void tableData(){
        //tdata=new ArrayList<YearlySalesTableBean>();
        tdata1=new ArrayList<YearlySalesTableBean>();
        tdata=Queries.yearlyData(ysb);
        tdata1=tdata;
        if(amount!=0.0){
            amount=0.0;
        }
        for(int i=0;i<tdata.size();i++){
            amount=amount+tdata1.get(i).getInvamt();
            amount=Double.parseDouble(dfr.format(amount));
        }
       
    }
        public void refresh() {
            ysb.setDate2(null);
            ysb.setTaxType("All");
            ysb.setPartyType("All");
            tdata.clear();
            amount=0.0;        
    }
    public void typeChanged(){
        tableData();
    }
    public void partyChanged(){
        tableData();
    }
    public void dateSelect(SelectEvent event) {
        ysb.setDate2((Date)event.getObject());
        tableData();

    }
    public void todateSelect(SelectEvent event) {
        ysb.setDate1((Date)event.getObject());
        tableData();

    }
    public void print(){
        String ite = ysb.getTaxType();
             String cmp = ysb.getPartyType();

             if (ite.equalsIgnoreCase("All")) {
                
                 ite = "%";
             }  if (cmp.equalsIgnoreCase("All")) {
                
                 cmp = "%";
             } 
             
                 //System.out.println("PDF ---> Iam from SELECTION");
                YearlySalesPDF ob = new YearlySalesPDF();
                 ob.execute(df.format(ysb.getDate2()), df.format(ysb.getDate1()),ite,cmp,tdata,amount); 
    }
    public void setYsb(YearlySalesBean ysb) {
        this.ysb = ysb;
    }

    public YearlySalesBean getYsb() {
        return ysb;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setTdata(List<YearlySalesTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<YearlySalesTableBean> getTdata() {
        return tdata;
    }

    public void setTaxList(List taxList) {
        this.taxList = taxList;
    }

    public List getTaxList() {
        return taxList;
    }

    public void setPartyList(List partyList) {
        this.partyList = partyList;
    }

    public List getPartyList() {
        return partyList;
    }

    public void setYstb(YearlySalesTableBean ystb) {
        this.ystb = ystb;
    }

    public YearlySalesTableBean getYstb() {
        return ystb;
    }
}
