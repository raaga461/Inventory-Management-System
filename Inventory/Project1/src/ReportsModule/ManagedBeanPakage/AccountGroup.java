package ReportsModule.ManagedBeanPakage;

import ReportsModule.BeanPakage.AccountGroupTableBean;

import ReportsModule.PDFReports.AccountGroupPDF;

import ReportsModule.Queries.Queries;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "acgroup",eager = true)
@ViewScoped
public class AccountGroup {
    private List<AccountGroupTableBean>tdata;
      private String date;
      private Date d;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    
    public AccountGroup() {
        super();
        tdata=new ArrayList<AccountGroupTableBean>();
        d = new Date();
        date = df.format(d);
       tableData();
    }
    public void tableData(){
        tdata=Queries.accDetailsData();
    }
    public void refresh(){
        tdata.clear();
    }
    public void print(){
        AccountGroupPDF ob=new AccountGroupPDF();
        ob.execute(tdata);
    }
    public void setTdata(List<AccountGroupTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<AccountGroupTableBean> getTdata() {
        return tdata;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
