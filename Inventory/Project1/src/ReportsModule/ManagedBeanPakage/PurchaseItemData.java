package ReportsModule.ManagedBeanPakage;


import ReportsModule.BeanPakage.PurchaseItemBean;
import ReportsModule.BeanPakage.PurchaseItemTableBean;

import ReportsModule.PDFReports.PurchaseItemDataPDF;

import ReportsModule.Queries.Queries;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "purchaseitem",eager = true)
@ViewScoped
public class PurchaseItemData {
    private PurchaseItemBean pib;
    private List<PurchaseItemTableBean>tdata;
    private String date;
    private Date d;
    private List iList=new ArrayList();;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    public PurchaseItemData() {
        super();
        pib=new PurchaseItemBean();
        tdata=new ArrayList<PurchaseItemTableBean>();
        d = new Date();
        date = df.format(d);
        pib.setIcategoty("All");
        itemData();
        tableData();
    }
    public void tableData(){
        tdata=Queries.purchaseItemData(pib);
    }
    public void refresh(){
        pib.setIcategoty("All");
      tdata.clear();  
    }
    public void itemData(){
        iList=Queries.types1();
        System.out.println(iList);
            
    }
    public void typeChanged(){
        tableData();
    }
    public void print(){
        PurchaseItemDataPDF ob=new PurchaseItemDataPDF();
        ob.execute(tdata);
    }

    public void setPib(PurchaseItemBean pib) {
        this.pib = pib;
    }

    public PurchaseItemBean getPib() {
        return pib;
    }

    public void setTdata(List<PurchaseItemTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<PurchaseItemTableBean> getTdata() {
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
