package ReportsModule.ManagedBeanPakage;


import ReportsModule.*;


import ReportsModule.BeanPakage.SalesChartbyRepwiseBean;

import ReportsModule.PDFReports.SalesChartByRepwisePDF;

import ReportsModule.Queries.Queries;
import ReportsModule.BeanPakage.SalesDateTableData;


import ReportsModule.BeanPakage.SalesRepresentChartDataBean;

import ReportsModule.DBConnection.DBConnection;

import com.mysql.jdbc.Statement;

import java.sql.ResultSet;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.PieChartModel;
@ManagedBean(name = "chartbyrep", eager = true)
@ViewScoped
public class SalesChartbyRepwise {
    private String amt;
    Boolean visible = false;
    private List taxList = new ArrayList();
    private SalesChartbyRepwiseBean sd = new SalesChartbyRepwiseBean();
    private   SalesRepresentChartDataBean srcd;
    private SalesDateTableData std;
    private List<SalesDateTableData> tdata, tdata2;
   private List<SalesRepresentChartDataBean>chartData;
    DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private PieChartModel pieModel;
  private  List representativeList = new ArrayList();
    private  List list1 = new ArrayList();
    private  List list2 = new ArrayList();
    private Map<Integer, String> mp = new HashMap<Integer, String>();
    private Double chartValue;
   private  String chartKey;

    public SalesChartbyRepwise() {
        super();
        std = new SalesDateTableData();
        tdata = new ArrayList<SalesDateTableData>();
     chartData=new ArrayList<SalesRepresentChartDataBean>();
        pieModel = new PieChartModel();
        createPieModel();
        //  taxtype=taxData();
         taxData();
        addRepresents();
        sd.setType("All");
        sd.setRepType("All");
        Date d = new Date();
        String date = df.format(d);

        try {
            sd.setDate1( df.parse(date));
            sd.setDate2( df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tableData();
    }
    public void dateSelect(SelectEvent event) {
        sd.setDate2((Date)event.getObject());

    }
    public void todateSelect(SelectEvent event) {
        sd.setDate1((Date)event.getObject());

    }
    public void typeChanged() {
        tableData();
    }
    public void repChanged() {
        tableData();
    }

    public void tableData() {
        tdata = Queries.charttableData(sd);
        amt=dfr.format(DBConnection.getAmount());
    
    }
   

    public void taxData() {
        taxList = Queries.taxData();
    }

    public void addRepresents() {
        representativeList=Queries.representData();
   
    }

    private void createPieModel() {
        pieModel=Queries.createPieModel(sd);
     System.out.println("piemodel value is "+pieModel);
  
    }
    public void print(){
      
        String ss;
        for (Map.Entry<String, Number> e : pieModel.getData().entrySet()) {     
            String key = e.getKey();       
            int x=key.indexOf("[");
            ss =key.substring(0, x);  
          list1.add(ss);   
          list2.add(e.getValue());         
        } 
        System.out.println(1);
        for(int i=0;i<list1.size();i++){
            srcd=new SalesRepresentChartDataBean();
            srcd.setKey1((String)list1.get(i));
            srcd.setValue1((Double)list2.get(i));
            chartData.add(srcd);
        }
        System.out.println(2);
        String ite = sd.getType();
               if (ite.equalsIgnoreCase("All")) {
                   ite = "%";
               } 
        String re = sd.getRepType();
               if (re.equalsIgnoreCase("All")) {
                   re = "%";
               } 
                  
                 SalesChartByRepwisePDF ob=new SalesChartByRepwisePDF();
                 if(chartData.isEmpty()){
                 FacesContext context=FacesContext.getCurrentInstance();
                 context.addMessage("Empty Chart Data", new FacesMessage("Empty Chart Data,Press Graph Button to Generate Chart"));
                 }
                 else
                 {
                   ob.execute(df.format(sd.getDate2()), df.format(sd.getDate1()),ite,re,chartData);
                 }
    }

    public void graphData() {
        setVisible(true);
        if (visible) {
            createPieModel();

        }
    }

    public void setStd(SalesDateTableData std) {
        this.std = std;
    }

    public SalesDateTableData getStd() {
        return std;
    }

    public void setTdata(List<SalesDateTableData> tdata) {
        this.tdata = tdata;
    }

    public List<SalesDateTableData> getTdata() {
        return tdata;
    }


    public void setSd(SalesChartbyRepwiseBean sd) {
        this.sd = sd;
    }

    public SalesChartbyRepwiseBean getSd() {
        return sd;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }


    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getAmt() {
        return amt;
    }


    public void setTaxList(List taxList) {
        this.taxList = taxList;
    }

    public List getTaxList() {
        return taxList;
    }

    public void setRepresentativeList(List representativeList) {
        this.representativeList = representativeList;
    }

    public List getRepresentativeList() {
        return representativeList;
    }

    public void itemSelect(ItemSelectEvent event) {
        int z = event.getItemIndex(), i;
        tableData();
        tdata2 = tdata;
        String s = "All";
     mp=DBConnection.getMp();
        ArrayList<SalesDateTableData> templist = new ArrayList<SalesDateTableData>();
        System.out.println("the all value-----------------------------------------" + s + "the graph value" +
                           mp.get(z));
        //    if ((s.equalsIgnoreCase(mp.get(z)))) {
        for (i = 0; i < tdata2.size(); i++) {
            if (tdata2.get(i).getRep().equalsIgnoreCase(mp.get(z))) {
                System.out.println(tdata2.get(i).getRep());
                templist.add(tdata2.get(i));
            }
        }
        //   }
        System.out.println("the tdatas2 size is----------------------" + tdata2.size());
        System.out.println("the templist size is----------------------" + templist.size());
        //tdata2.removeAll(templist);
        tdata2 = templist;
        tdata = tdata2;

    }

    public void refresh() {
        sd.setDate2(null);
        sd.setType("All");
        sd.setRepType("All");
       amt="0.0";
        tdata.clear();
        setVisible(false);
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setTdata2(List<SalesDateTableData> tdata2) {
        this.tdata2 = tdata2;
    }

    public List<SalesDateTableData> getTdata2() {
        return tdata2;
    }
}
