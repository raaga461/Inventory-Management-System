package ReportsModule.ManagedBeanPakage;


import ReportsModule.BeanPakage.SalesItemDataBean;
import ReportsModule.BeanPakage.SalesItemTableBean;

import ReportsModule.PDFReports.SalesItemPDF;

import ReportsModule.Queries.Queries;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "salesitem",eager = true)
@ViewScoped()
public class SalesItem {
    private SalesItemDataBean sidb;
    private List<SalesItemTableBean>tdata=new ArrayList<SalesItemTableBean>();
    private String date;
    private Date d;
    private List iList=new ArrayList();;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    public SalesItem() {
        super();
        sidb=new SalesItemDataBean();
        tdata=new ArrayList<SalesItemTableBean>();
        d = new Date();
        date = df.format(d);
       sidb.setItem("All");
        itemData();
    }
    public void tableData(){
        tdata=Queries.salesItemData(sidb);
    }
    public void itemData(){
        iList=Queries.itemData();
    }
    public void refresh(){
    sidb.setItem("All");
    tdata.clear();
    }
    public void typeChanged(){
        tableData();
    }
    public void print(){
        String ite = sidb.getItem();
               if(ite.equalsIgnoreCase("All"))
               {
               
               ite = "%";
               }
        SalesItemPDF ob=new SalesItemPDF();
        ob.execute(ite, tdata);
    }

    public void setSidb(SalesItemDataBean sidb) {
        this.sidb = sidb;
    }

    public SalesItemDataBean getSidb() {
        return sidb;
    }

    public void setTdata(List<SalesItemTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<SalesItemTableBean> getTdata() {
        return tdata;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setIList(List iList) {
        this.iList = iList;
    }

    public List getIList() {
        return iList;
    }
}
