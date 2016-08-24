package ReportsModule.ManagedBeanPakage;

import ReportsModule.BeanPakage.CFormQuartersItemTableBean;
import ReportsModule.BeanPakage.CFormQuartersSubTableBean;
import ReportsModule.BeanPakage.CFormStatusByQuartersBean;

import ReportsModule.BeanPakage.CFormStatusByQuartersTableBean;

import ReportsModule.BeanPakage.CformStatusByDateTableBean;

import ReportsModule.PDFReports.CFormQuartersPDF;

import ReportsModule.Queries.Queries;

import com.sun.org.apache.xpath.internal.operations.And;

import java.awt.Event;

import java.awt.event.ActionEvent;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;

import org.primefaces.event.ToggleEvent;

@ManagedBean(name = "quarter", eager = true)
@ViewScoped
public class CFormStatusByQuartes {
    private CFormStatusByQuartersBean csqb;
    private List<CFormStatusByQuartersTableBean> tdata;
    private List<CFormQuartersSubTableBean> subdata;
    private CFormQuartersSubTableBean selectedcqstb;
    private CFormStatusByQuartersTableBean selecteddata;
    private List<CFormQuartersItemTableBean> idata;
    private List yearList = new ArrayList();
    DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private Double amount1 = 0.0, amount2 = 0.0, amount3 = 0.0;
    private String year, period, time;

    public CFormStatusByQuartes() {
        super();
        selectedcqstb = new CFormQuartersSubTableBean();
        csqb = new CFormStatusByQuartersBean();
        tdata = new ArrayList<CFormStatusByQuartersTableBean>();
        subdata = new ArrayList<CFormQuartersSubTableBean>();
        selecteddata = new CFormStatusByQuartersTableBean();
        idata = new ArrayList<CFormQuartersItemTableBean>();
        Date d = new Date();
        String date = df.format(d);
        csqb .setCyear("-2");
        yearData();
        
    }

    public void yearData() {
        yearList = Queries.yearData();
    }

    public void yearChanged() {

        System.out.println("the menu valu is" + csqb.getCyear());

    }

    public void periodChanged() {
        //  if(csqb.getCyear().equalsIgnoreCase("Select")||csqb.getCyear().equalsIgnoreCase(null)){
        //FacesContext context = FacesContext.getCurrentInstance();
       // context.addMessage("Select Year", new FacesMessage("Select Year"));
        //    }
        //  else{
        year = csqb.getCyear();
        System.out.println("YR_______________" + year);
        //   }
        period = csqb.getPeriod();
        System.out.println("PERIOD*******************       :   " + period);
        if (period.equalsIgnoreCase("Apr-Jun")) {
            time =
"Between".concat("  ").concat("'").concat("" + year + "").concat("-04-01").concat("'").concat("  ").concat("And").concat("  ").concat("'").concat("" +
                                                                                                                                                  year +
                                                                                                                                                  "").concat("-06-30").concat("'");
            //  System.out.println("date is....."+q);
            //String q="Between".concat("  ").concat("'"+"").concat(year).concat("-04-01").concat(""+"''").concat("  ").concat("And").concat("'"+"").concat("year").concat("-06-30").concat(""+"''");
            //   time ="Between + '"+year+"'  +  "-04-01" +And+ '"+year+"' + "-06-30" ";
        } else if (period.equalsIgnoreCase("Jul-Sep")) {

            time =
"Between".concat("  ").concat("'").concat("" + year + "").concat("-07-01").concat("'").concat("  ").concat("And").concat("  ").concat("'").concat("" +
                                                                                                                                                  year +
                                                                                                                                                  "").concat("-09-30").concat("'");
        } else if (period.equalsIgnoreCase("Oct-Dec")) {

            time =
"Between".concat("  ").concat("'").concat("" + year + "").concat("-10-01").concat("'").concat("  ").concat("And").concat("  ").concat("'").concat("" +
                                                                                                                                                  year +
                                                                                                                                                  "").concat("-12-31").concat("'");
        } else if (period.equalsIgnoreCase("Jan-Mar")) {

            time =
"Between".concat("  ").concat("'").concat("" + year + "").concat("-01-01").concat("'").concat("  ").concat("And").concat("  ").concat("'").concat("" +
                                                                                                                                                  year +
                                                                                                                                                  "").concat("-03-31").concat("'");
        } else if (period.equalsIgnoreCase("All")) {

            time = "like  '%" + year + "%'";
        }
        tdata = Queries.quarteraData(time);
    }

    public void displayData() {
        tdata = Queries.quarterNameData(csqb, time);
    }
    public void refresh(){
        csqb.setCyear("Select");
        csqb.setCname("");
        csqb.setPeriod("All");
        tdata.clear();
        subdata.clear();
        idata.clear();
        selecteddata = new CFormStatusByQuartersTableBean();
        selectedcqstb = new CFormQuartersSubTableBean();
    }
    public void print(){
        CFormQuartersPDF ob=new CFormQuartersPDF();
        ob.execute(csqb.getCyear(), csqb.getPeriod(), tdata);
    }
    public void onRowToggle(ToggleEvent event) {
        System.out.println("roe toggle");
        selecteddata = ((CFormStatusByQuartersTableBean)event.getData());
        quarterBillData();
    }

    public void quarterBillData() {
        subdata = Queries.subData(csqb, selecteddata, time);
        System.out.println("subdata value"+subdata);
    }

    public void setCsqb(CFormStatusByQuartersBean csqb) {
        this.csqb = csqb;
    }

    public void itemData() {
        idata = Queries.quarterItemData(selectedcqstb);
    }

    public CFormStatusByQuartersBean getCsqb() {
        return csqb;
    }

    public void setTdata(List<CFormStatusByQuartersTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<CFormStatusByQuartersTableBean> getTdata() {
        return tdata;
    }

    public void setDfr(DecimalFormat dfr) {
        this.dfr = dfr;
    }

    public DecimalFormat getDfr() {
        return dfr;
    }

    public void setDf(SimpleDateFormat df) {
        this.df = df;
    }

    public SimpleDateFormat getDf() {
        return df;
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

    public void setAmount3(Double amount3) {
        this.amount3 = amount3;
    }

    public Double getAmount3() {
        return amount3;
    }

    public void setYearList(List yearList) {
        this.yearList = yearList;
    }

    public List getYearList() {
        return yearList;
    }

    public void setSelecteddata(CFormStatusByQuartersTableBean selecteddata) {
        this.selecteddata = selecteddata;
    }

    public CFormStatusByQuartersTableBean getSelecteddata() {
        return selecteddata;
    }

    public void setSubdata(List<CFormQuartersSubTableBean> subdata) {
        this.subdata = subdata;
    }

    public List<CFormQuartersSubTableBean> getSubdata() {
        return subdata;
    }

    public void setIdata(List<CFormQuartersItemTableBean> idata) {
        this.idata = idata;
    }

    public List<CFormQuartersItemTableBean> getIdata() {
        return idata;
    }

    public void setSelectedcqstb(CFormQuartersSubTableBean selectedcqstb) {
        this.selectedcqstb = selectedcqstb;
    }

    public CFormQuartersSubTableBean getSelectedcqstb() {
        return selectedcqstb;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
