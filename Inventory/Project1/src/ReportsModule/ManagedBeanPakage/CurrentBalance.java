package ReportsModule.ManagedBeanPakage;


import ReportsModule.BeanPakage.CurrentBalanceBean;
import ReportsModule.BeanPakage.CurrentBalanceTableBean;

import ReportsModule.PDFReports.CurrentBalPDF;

import ReportsModule.Queries.Queries;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "cbal",eager = true)
@ViewScoped
public class CurrentBalance {
    private CurrentBalanceBean cb;
    private Date date,ddate;
   private List<CurrentBalanceTableBean>tdata;
   DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String date1;
   Double amount=0.0;
    public CurrentBalance() {
        super();
        cb=new CurrentBalanceBean();
        tdata=new ArrayList<CurrentBalanceTableBean>();
        date = new Date();
       date1 = df.format(date);
       
    }
    public void tableData(){
        tdata=Queries.currentBalanceData(cb);
        if(amount!=0){
            amount=0.0;
        }
        for(int i=0;i<tdata.size();i++){
            amount=Double.parseDouble(dfr.format(amount+Double.parseDouble(tdata.get(i).getBal())));
        }
    }
    public void refresh(){
        cb.setType("All");
        tdata.clear();
    }
    public void typeChanged(){
        tableData();
    }
    public void print(){
        String ite = cb.getType();

               if (ite.equalsIgnoreCase("All")) {
                ite = "%";

               }  
                 CurrentBalPDF ob = new CurrentBalPDF();
                 ob.execute(ite,tdata,amount);
                 
    }
    public void setCb(CurrentBalanceBean cb) {
        this.cb = cb;
    }

    public CurrentBalanceBean getCb() {
        return cb;
    }

    public void setTdata(List<CurrentBalanceTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<CurrentBalanceTableBean> getTdata() {
        return tdata;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDdate(Date ddate) {
        this.ddate = ddate;
    }

    public Date getDdate() {
        return ddate;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate1() {
        return date1;
    }
}
