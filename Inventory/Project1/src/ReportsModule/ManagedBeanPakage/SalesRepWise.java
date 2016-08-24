package ReportsModule.ManagedBeanPakage;

import ReportsModule.BeanPakage.SalesDateTableData;
import ReportsModule.BeanPakage.SalesRepWiseBean;

import ReportsModule.DBConnection.DBConnection;

import ReportsModule.PDFReports.SalesDatesPDF;

import ReportsModule.PDFReports.SalesRepwisePDF;

import ReportsModule.Queries.Queries;

import com.mysql.jdbc.Statement;

import java.sql.ResultSet;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

@ManagedBean(name = "salesrepwise",eager = true)
@ViewScoped
public class SalesRepWise {
    private String amt;
    private SalesRepWiseBean srb;
    private SalesDateTableData std;
    private List<SalesDateTableData> tdata;
    DecimalFormat dfr = new DecimalFormat("#.##"); 
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private List representativeList = new ArrayList();
    private List taxList = new ArrayList();
    
    public SalesRepWise() {
        super();
        srb=new SalesRepWiseBean();
        std = new SalesDateTableData();
        tdata = new ArrayList<SalesDateTableData>();     
        taxData();
        addRepresents();
        srb.setTaxType("All");
        srb.setRepType("All");
        Date d = new Date();
        String date = df.format(d);
        try {
            srb.setDate1(df.parse(date)); 
        } catch (ParseException e) {
            e.printStackTrace();
        }
     
    }
    public void tableData() {
        tdata = Queries.repwiseTableData(srb);
        amt=dfr.format(DBConnection.getAmount());
        
      /*   List<SalesDateTableData> data1 = new ArrayList<SalesDateTableData>();
        String net = null;
        ResultSet rs = null;
        Statement st = null;
      st = DBConnection1.DBConnection.Conn_Statement();
        

        if (srb.getTaxType() != null) {
            srb.setAmount(0.0);
            String qry = "";
            try {
                String ite = srb.getTaxType();

                if (ite.equalsIgnoreCase("All")) {
                    ite = "";
                }
                String re = srb.getRepType();
                if (re.equalsIgnoreCase("All")) {
                    re = "";
                }
                String d1, d2;
                d1 = df.format(srb.getDate1());
                d2 = df.format(srb.getDate2());
                qry =
    "SELECT distinct Billno, accno,acname,city,R_rep,invdate,invno,INVVALUE  From  Vat_Sales   WHERE   taxtype   like'%" +
    ite + "'  and invdate between '" + d2 + "' AND '" + d1 + "' and R_rep like '%" + re + "%'  ORDER  BY R_rep";
                rs = st.executeQuery(qry);
                
                while (rs.next()) {
                    // System.out.println(rs.getString(1));
                    std = new SalesDateTableData();
                    net = rs.getString(8);
                    srb.setAmount(Double.parseDouble(net) + srb.getAmount()); 
                    amt = dfr.format(srb.getAmount());
                    std.setBillno(rs.getString(1));
                    std.setAccno(rs.getString(2));
                    std.setAcname(rs.getString(3));
                    std.setCity(rs.getString(4));
                    std.setRep(rs.getString(5));
                    std.setInvdate(rs.getDate(6));
                    std.setInvno(rs.getString(7));
                    std.setInvvalue(net);
                    data1.add(std);
                }
                tdata = data1;
                st.close();
            }

            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data1; */
    }
    public void dateSelect(SelectEvent event) {
        srb.setDate2((Date)event.getObject());
        tableData();
    }
    public void todateSelect(SelectEvent event) {
        srb.setDate1((Date)event.getObject());
        tableData();
    }
    public void taxData() {
        taxList = Queries.taxData();
    }
    public void typeChanged() {
        tableData();
    }
    public void repChanged() {
        tableData();
    }
    public void addRepresents() {
        representativeList=Queries.representData();
    }
    public void refresh(){
        srb.setDate2(null);
        srb.setTaxType("All");
        srb.setRepType("All");
        amt = "0.0";
        tdata.clear(); 
    }
    public void print(){
        String ite =srb.getRepType();
        String taxs =srb.getTaxType();
               
               if(ite.equalsIgnoreCase("All"))
               {
               ite = "%";
               }
               if(taxs.equalsIgnoreCase("All"))
               {
               taxs = "%";
               }
        SalesRepwisePDF ob = new SalesRepwisePDF();
        // ob.execute(df.format(srb.getDate2()), df.format(srb.getDate1()),taxs,ite);
ob.execute(ite, taxs, df.format(srb.getDate2()),  df.format(srb.getDate1()));
        
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getAmt() {
        return amt;
    }

    public void setSrb(SalesRepWiseBean srb) {
        this.srb = srb;
    }

    public SalesRepWiseBean getSrb() {
        return srb;
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

    public void setRepresentativeList(List representativeList) {
        this.representativeList = representativeList;
    }

    public List getRepresentativeList() {
        return representativeList;
    }

    public void setTaxList(List taxList) {
        this.taxList = taxList;
    }

    public List getTaxList() {
        return taxList;
    }
}
