package ReportsModule.ManagedBeanPakage;

import ReportsModule.BeanPakage.CFormSubmitDatesBean;

import ReportsModule.BeanPakage.CFormSubmitDatesTableBean;

import ReportsModule.PDFReports.CFormSubmitDatesPDF;

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
@ManagedBean(name="cformsubmit",eager = true)
@ViewScoped
public class CFormSubmitDates {
    private CFormSubmitDatesBean cbd;
    private List<CFormSubmitDatesTableBean> tdata;
    DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    public CFormSubmitDates() {
        super();
       cbd=new CFormSubmitDatesBean();
       tdata=new ArrayList<CFormSubmitDatesTableBean>();
        Date d = new Date();
        String date = df.format(d);
        //  ServletContext ctx = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
        //   String realPath = ctx.getRealPath("/");
        //System.out.println("the realpathis" + realPath + "reports" + File.separator + "SalesDatesPDF.jrxml");
        //  System.out.println("the realpathis" + realPath + "images" + File.separator + "ssi-logo.jpg");

        try {
            cbd.setDate1(df.parse(date));
            cbd.setDate2(df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void tableData(){
        tdata=Queries.cformSubmitData(cbd);
        System.out.println("values...."+tdata);
    }
    public void dateSelect(SelectEvent event) {
        cbd.setDate2((Date)event.getObject());
        tableData();
    }
    public void todateSelect(SelectEvent event) {
        cbd.setDate1((Date)event.getObject());
        tableData();
    }
    public void refresh(){
        cbd.setDate2(null);
        tdata.clear();
    }
    public void print(){
        String d1, d2;
             d1 = df.format(cbd.getDate1());
                d2 = df.format(cbd.getDate2());
        CFormSubmitDatesPDF ob=new CFormSubmitDatesPDF();
        ob.execute(d2, d1, tdata);
    }

    public void setCbd(CFormSubmitDatesBean cbd) {
        this.cbd = cbd;
    }

    public CFormSubmitDatesBean getCbd() {
        return cbd;
    }

    public void setTdata(List<CFormSubmitDatesTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<CFormSubmitDatesTableBean> getTdata() {
        return tdata;
    }
}
