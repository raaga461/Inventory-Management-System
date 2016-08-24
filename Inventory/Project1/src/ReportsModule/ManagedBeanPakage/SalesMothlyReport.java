package ReportsModule.ManagedBeanPakage;

import ReportsModule.BeanPakage.SalesMonthlyReportBean;
import ReportsModule.BeanPakage.SalesMonthlyReportTableBean;

import ReportsModule.DBConnection.DBConnection;

import ReportsModule.PDFReports.SalesMothlyReportPDF;

import ReportsModule.Queries.Queries;

import java.sql.ResultSet;

import java.sql.Statement;

import java.text.DecimalFormat;
import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

@ManagedBean(name="monthly",eager = true)
@ViewScoped
public class SalesMothlyReport {
    DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
  private  SalesMonthlyReportBean smb=new  SalesMonthlyReportBean();
  private List<SalesMonthlyReportTableBean> tdata,tdata1,tdata2;
    private SalesMonthlyReportTableBean smtb;
  private List taxList=new ArrayList();
    private Double amount = 0.0;
   private  Double gros, stax, pos, pak, spl, aut, otr, run, inv,mix=0.0,oto,ron=0.0;
    int sno=1;
  
    public SalesMothlyReport() {
        super();
         tdata=new ArrayList<SalesMonthlyReportTableBean>();
          tdata1=new ArrayList<SalesMonthlyReportTableBean>();
        taxData();
      smb.setType("All");
        Date d = new Date();
        String date = df.format(d);
        try {
            smb.setDate1( df.parse(date));
            smb.setDate2( df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
       // tableData();
    }
  public  void taxData(){
        taxList = Queries.taxData();
    }
  public void tableData(){
      tdata=DBConnection.MonthlyTableData(smb);

gros=Double.parseDouble(tdata.get(tdata.size()-1).getNet());
stax=Double.parseDouble(tdata.get(tdata.size()-1).getSalestax());
pos=Double.parseDouble(tdata.get(tdata.size()-1).getPostage());
oto=Double.parseDouble(dfr.format(tdata.get(tdata.size()-1).getMixvalue()));
otr=Double.parseDouble(tdata.get(tdata.size()-1).getOthers());
ron=Double.parseDouble(tdata.get(tdata.size()-1).getRoundof());
amount=Double.parseDouble(tdata.get(tdata.size()-1).getInvvalue());
    tdata1=new ArrayList<SalesMonthlyReportTableBean>();
    for(int i=0;i<tdata.size()-1;i++){
        tdata1.add(tdata.get(i));
    }

//    tdata1=new ArrayList<SalesMonthlyReportTableBean>();
 //   tdata1 = (List<SalesMonthlyReportTableBean>)(tdata.get(tdata.size()-1));
//    System.out.println(gros);
    //DBConnection.MonthlyTableData(smb, gros, stax, pos, oto, otr, ron, amount).
     
      //List<SalesMonthlyReportTableBean> tdata=new ArrayList<SalesMonthlyReportTableBean>();
   /*    List<SalesMonthlyReportTableBean> tdata1=new ArrayList<SalesMonthlyReportTableBean>();
      ResultSet rs=null;
      Statement st=null;
      st = DBConnection.Conn_Statement();
      if(smb.getType()!=null){
             amount = 0.0;
              gros = 0.0;
              stax = 0.0;
              pos = 0.0;
              pak = 0.0;
              spl = 0.0;
              aut = 0.0;
              otr = 0.0;
              run = 0.0;
              inv = 0.0;
               oto = 0.0;
              sno =1;
              String qry = "";
              try {
                
                  String ite = smb.getType();

                  if (ite.equalsIgnoreCase("All")) {
                      ite = "";
                  }
                  
                  if(smb.isCst())
                  {   
                  ite = "cst";
                  }
                  String d1, d2;
                  d1 = df.format(smb.getDate1());
                  d2 = df.format(smb.getDate2());
                  qry = "SELECT  invdate,acname,city, NET, salestax, postage, packing,autochrg,others,roundof,invvalue,invno  FROM  `Vat_Sales` WHERE   taxtype   like'%" + ite + "%'  and  invdate between '"+d2+"' AND '"+d1+"'   GROUP BY invno,invdate,acname, city, NET, salestax, postage, packing,autochrg,others,roundof,invvalue";
               

               System.out.println("Get Data Query----------------------->" + qry);

                  rs = st.executeQuery(qry);

                  while (rs.next()) {
                    SalesMonthlyReportTableBean   smtb=new SalesMonthlyReportTableBean();
                    
                      smtb.setSno(sno);
                      smtb.setInvdate(rs.getDate(1));
                      smtb.setAcname(rs.getString(2));
                      smtb.setCity(rs.getString(3));  
                    String nt = rs.getString(4);
                      smtb.setNet(nt);
                       gros =Double.parseDouble(dfr.format(Double.parseDouble(nt) + gros)) ;    
                      String stx = rs.getString(5);
                      smtb.setSalestax(stx);  
                      stax =Double.parseDouble(dfr.format((Double.parseDouble(stx) + stax))) ;  
                      String po = rs.getString(6);
                      smtb.setPostage(po);   
                      pos =Double.parseDouble((dfr.format(Double.parseDouble(po) + pos)));    
                      String pk = rs.getString(7); 
                      pak = Double.parseDouble(pk) ;//*
                      String at = rs.getString(8);    
                      aut = Double.parseDouble(at);//*
                      String ot = rs.getString(9);
                      smtb.setOthers(ot);
                      otr = Double.parseDouble(ot)+otr;//*
                       String rou = rs.getString(10);
                      smtb.setRoundof(rou);
                      ron = Double.parseDouble(dfr.format(Double.parseDouble(rou)+ron));    
                      mix =pak+aut;      
                      smtb.setMixvalue(mix);
                      oto = oto+mix;
                      String fin = rs.getString(11);
                      smtb.setInvvalue(fin);
                      amount =Double.parseDouble(dfr.format(Double.parseDouble(fin) + amount));        
                      tdata1.add(smtb);
                      sno++;

                  }
           
             
                 tdata=tdata1; 
                

              } catch (Exception e) {
                  e.printStackTrace();
              }
             }
  return tdata1; */
}
    public void refresh() {
        smb.setDate2(null);
        smb.setType("All");
        tdata1.clear();
        gros=0.0;
        stax=0.0;
        pos=0.0;
        oto=0.0;
        otr=0.0;
        ron=0.0;
        amount=0.0;        
        smb.setCst(false);
    }
    public void cstChanged(){
        if(smb.isCst()){
            tableData();
        }
        if(!smb.isCst()){
            tableData();
        }
    }
   public void print(){
        String ite = smb.getType();

              if (ite.equalsIgnoreCase("All")) {
                 ite = "%";
              }  
              
                  if(smb.isCst())
                  {
                  
                  ite = "%cst%";
                  }
                  //System.out.println("PDF ---> Iam from SELECTION");
                SalesMothlyReportPDF ob = new SalesMothlyReportPDF();
                 ob.execute( ite,df.format(smb.getDate2()), df.format(smb.getDate1()),tdata1,gros,stax,pos,oto,otr,ron,amount);
        
    }
    public void typeChanged(){
        tableData();
    }
    public void dateSelect(SelectEvent event) {
        smb.setDate2((Date)event.getObject());
        tableData();

    }
    public void todateSelect(SelectEvent event) {
        smb.setDate1((Date)event.getObject());
        tableData();
    }
    public void setSmb(SalesMonthlyReportBean smb) {
        this.smb = smb;
    }

    public SalesMonthlyReportBean getSmb() {
        return smb;
    }

    public void setTdata(List<SalesMonthlyReportTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<SalesMonthlyReportTableBean> getTdata() {
        return tdata;
    }

    public void setSmtb(SalesMonthlyReportTableBean smtb) {
        this.smtb = smtb;
    }

    public SalesMonthlyReportTableBean getSmtb() {
        return smtb;
    }

    public void setTaxList(List taxList) {
        this.taxList = taxList;
    }

    public List getTaxList() {
        return taxList;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public int getSno() {
        return sno;
    }

    public void setGros(Double gros) {
        this.gros = gros;
    }

    public Double getGros() {
        return gros;
    }

    public void setStax(Double stax) {
        this.stax = stax;
    }

    public Double getStax() {
        return stax;
    }

    public void setPos(Double pos) {
        this.pos = pos;
    }

    public Double getPos() {
        return pos;
    }

    public void setOtr(Double otr) {
        this.otr = otr;
    }

    public Double getOtr() {
        return otr;
    }

    public void setOto(Double oto) {
        this.oto = oto;
    }

    public Double getOto() {
        return oto;
    }

    public void setRon(Double ron) {
        this.ron = ron;
    }

    public Double getRon() {
        return ron;
    }

    public void setTdata1(List<SalesMonthlyReportTableBean> tdata1) {
        this.tdata1 = tdata1;
    }

    public List<SalesMonthlyReportTableBean> getTdata1() {
        return tdata1;
    }
}
