package ReportsModule.ManagedBeanPakage;



import ReportsModule.Queries.Queries;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import ReportsModule.BeanPakage.SalesDateBean;
import ReportsModule.BeanPakage.SalesDateTableData;
import ReportsModule.PDFReports.SalesDatesPDF;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.sf.jasperreports.engine.JRException;

import org.primefaces.event.SelectEvent;


@ManagedBean(name = "salesdates", eager = true)
@ViewScoped
public class SalesDates {

    private SalesDateBean sd = new SalesDateBean();
    private SalesDateTableData std;
    private List<SalesDateTableData> tdata;
    DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    List mp = new ArrayList();

    public SalesDates() {
        super();
        std = new SalesDateTableData();
        tdata = new ArrayList<SalesDateTableData>();
        sd.setType("All");
      
        taxdata();
        Date d = new Date();
        String date = df.format(d);
        //  ServletContext ctx = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
        //   String realPath = ctx.getRealPath("/");
        //System.out.println("the realpathis" + realPath + "reports" + File.separator + "SalesDatesPDF.jrxml");
        //  System.out.println("the realpathis" + realPath + "images" + File.separator + "ssi-logo.jpg");

        try {
            sd.setDate1(df.parse(date));
            sd.setDate2(df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    //    typeChanged();
        tabledata();
    }


    public void tabledata() {

        tdata = Queries.tableData(sd);
       
    }

    public void dateSelect(SelectEvent event) {
        sd.setDate2((Date)event.getObject());
        tabledata();
    }
    public void todateSelect(SelectEvent event) {
        sd.setDate1((Date)event.getObject());
        tabledata();
    }

    public void typeChanged() {
        tabledata();
    }

    public void taxdata() {
        mp = Queries.taxData();
      
    }

    public void print() {
        String ite = sd.getType();
        if (ite.equalsIgnoreCase("All")) {
            ite = "%";
        }

        SalesDatesPDF ob = new SalesDatesPDF();

        try {
            ob.execute(df.format(sd.getDate2()), df.format(sd.getDate1()), ite);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public void refresh() {
       
        sd.setDate2(null);
        sd.setType("All");
        sd.setAmount(0.0);
        tdata.clear();
    }

    public void setSd(SalesDateBean sd) {
        this.sd = sd;
    }

    public SalesDateBean getSd() {
        return sd;
    }

    public void setTdata(List<SalesDateTableData> tdata) {
        this.tdata = tdata;
    }

    public List<SalesDateTableData> getTdata() {
        return tdata;
    }


    public void setStd(SalesDateTableData std) {
        this.std = std;
    }

    public SalesDateTableData getStd() {
        return std;
    }


    public void setMp(List mp) {
        this.mp = mp;
    }

    public List getMp() {
        return mp;
    }
}
