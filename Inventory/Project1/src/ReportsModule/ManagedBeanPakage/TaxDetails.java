package ReportsModule.ManagedBeanPakage;


import ReportsModule.BeanPakage.TaxAccountReportTableBean;

import ReportsModule.PDFReports.TaxDetailsPDF;

import ReportsModule.Queries.Queries;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="taxdetails",eager = true)
@ViewScoped
public class TaxDetails {
    private List<TaxAccountReportTableBean>tdata;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String date;
   private Date d;
    public TaxDetails() {
        super();
        tdata=new ArrayList<TaxAccountReportTableBean>();
         d = new Date();
       date = df.format(d);
        tableData();
    }

    public void tableData(){
        tdata=Queries.taxDetailsData();
    }
    public void refresh(){
        tdata.clear();
    }
    public void print(){
        TaxDetailsPDF ob=new TaxDetailsPDF();
        ob.execute(tdata);
    }
    public void setTdata(List<TaxAccountReportTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<TaxAccountReportTableBean> getTdata() {
        return tdata;
    }
   

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
