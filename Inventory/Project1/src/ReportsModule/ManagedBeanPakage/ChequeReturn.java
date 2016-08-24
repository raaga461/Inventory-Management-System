package ReportsModule.ManagedBeanPakage;

import ReportsModule.BeanPakage.ChequeReturnsBean;
import ReportsModule.BeanPakage.ChequeReturnsTableBean;

import ReportsModule.PDFReports.ChequeReturnPDF;

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

@ManagedBean(name = "chqret",eager = true)
@ViewScoped
public class ChequeReturn {
    private ChequeReturnsBean crb;
    Double amount=0.0,amount1=0.0,amount2=0.0;;
    private ChequeReturnsTableBean crtb;
    private List<ChequeReturnsTableBean>tdata;
    DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    
    public ChequeReturn() {
        super();
        crb=new ChequeReturnsBean();
        crtb=new ChequeReturnsTableBean();
        tdata=new ArrayList<ChequeReturnsTableBean>();
        Date d = new Date();
        String date = df.format(d);
        try {
            crb.setDate1(df.parse(date));
            crb.setDate2(df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void tableData(){
        tdata=Queries.chequeRetData(crb);
        if(amount!=0||amount1!=0||amount2!=0){
            amount=0.0;
            amount1=0.0;
            amount2=0.0;
        }
        for(int i=0;i<tdata.size();i++){
            amount=amount+tdata.get(i).getNtamt();
            amount1=amount1+tdata.get(i).getAmount();
            amount2=amount2+tdata.get(i).getBnkcharge();
        }
        
    }
    public void dateSelect(SelectEvent event) {
        crb.setDate2((Date)event.getObject());
        tableData();
    }
    public void todateSelect(SelectEvent event) {
        crb.setDate1((Date)event.getObject());
        tableData();
    }
    public void print(){
        String d1, d2;
        d1 = df.format(crb.getDate1());
        d2 = df.format(crb.getDate2());
        ChequeReturnPDF ob=new ChequeReturnPDF();
        ob.execute(d2, d1, tdata,amount);
    }
    public void refresh(){
        crb.setDate2(null);
        tdata.clear();
    }
    public void setCrb(ChequeReturnsBean crb) {
        this.crb = crb;
    }

    public ChequeReturnsBean getCrb() {
        return crb;
    }

    public void setCrtb(ChequeReturnsTableBean crtb) {
        this.crtb = crtb;
    }

    public ChequeReturnsTableBean getCrtb() {
        return crtb;
    }

    public void setTdata(List<ChequeReturnsTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<ChequeReturnsTableBean> getTdata() {
        return tdata;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount1(Double amount1) {
        this.amount1 = amount1;
    }

    public Double getAmount1() {
        return amount1;
    }

    public void setAmount2(Double amount2) {
        this.amount2 = amount2;
    }

    public Double getAmount2() {
        return amount2;
    }
}
