package ReportsModule.ManagedBeanPakage;

import ReportsModule.BeanPakage.VoucherReportBean;
import ReportsModule.BeanPakage.VoucherReportTableBean;

import ReportsModule.PDFReports.VoucherDatesPDF;

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

@ManagedBean(name = "voucher",eager = true)
@ViewScoped
public class VoucherReport {
   private VoucherReportBean vrb;
    private VoucherReportTableBean vrbt;
    private List<VoucherReportTableBean>tdata;
    private List vList=new ArrayList();
    private List vList1=new ArrayList();
    DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Double amount=0.0;
    
    public VoucherReport() {
        super();
        vrb=new VoucherReportBean();
        tdata=new ArrayList<VoucherReportTableBean>();
        Date d = new Date();
        vrb.setType("All");
        vList=vData();
   
        String date = df.format(d);
        try {
            vrb.setDate1(df.parse(date));
            vrb.setDate2(df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void tableData(){
        tdata=Queries.voucherTableData(vrb);
        System.out.println(tdata);
        if(amount!=0){
            amount=0.0;
        }
        for(int i=0;i<tdata.size();i++)
        amount=Double.parseDouble(dfr.format(amount+tdata.get(i).getVamount()));
        System.out.println(amount);
    }
    public List vData(){
        vList1.add("All");
        vList1.add("Payment");
        vList1.add("Receipt");
        vList1.add("Credit Note");
        vList1.add("Debit Note");   
        return vList1;
    }
    public void dateSelect(SelectEvent event) {
        vrb.setDate2((Date)event.getObject());
        tableData();
    }
    public void todateSelect(SelectEvent event) {
        vrb.setDate1((Date)event.getObject());
        tableData();
    }

    public void typeChanged() {
        tableData();
    }
    public void refresh(){
        vrb.setDate2(null);
        vrb.setType("All");
        tdata.clear();
    }
    public void print(){
        String ite = vrb.getType();
              if (ite.equalsIgnoreCase("All")) {
                 ite = "%";
              }  
                  
            VoucherDatesPDF ob = new VoucherDatesPDF();
        try {
            ob.execute(df.format(vrb.getDate2()), df.format(vrb.getDate1()),ite, tdata, amount);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
    public void setVrb(VoucherReportBean vrb) {
        this.vrb = vrb;
    }

    public VoucherReportBean getVrb() {
        return vrb;
    }

    public void setVrbt(VoucherReportTableBean vrbt) {
        this.vrbt = vrbt;
    }

    public VoucherReportTableBean getVrbt() {
        return vrbt;
    }

    public void setTdata(List<VoucherReportTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<VoucherReportTableBean> getTdata() {
        return tdata;
    }

    public void setVList(List vList) {
        this.vList = vList;
    }

    public List getVList() {
        return vList;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }
}
