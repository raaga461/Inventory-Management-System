package ReportsModule.DBConnection;


import ReportsModule.BeanPakage.AccountGroupTableBean;
import ReportsModule.BeanPakage.CFormByCompanyBean;
import ReportsModule.BeanPakage.CFormByCompanyTableBean;
import ReportsModule.BeanPakage.CFormByRepBean;
import ReportsModule.BeanPakage.CFormByRepTableBean;
import ReportsModule.BeanPakage.CFormOutstandingSummaryBean;
import ReportsModule.BeanPakage.CFormOutstandingSummaryTableBean;
import ReportsModule.BeanPakage.CFormQuartersItemTableBean;
import ReportsModule.BeanPakage.CFormQuartersSubTableBean;
import ReportsModule.BeanPakage.CFormStatusByQuartersBean;
import ReportsModule.BeanPakage.CFormStatusByQuartersTableBean;
import ReportsModule.BeanPakage.CFormSubmitDatesBean;
import ReportsModule.BeanPakage.CFormSubmitDatesTableBean;
import ReportsModule.BeanPakage.CformStatusByDateBean;
import ReportsModule.BeanPakage.CformStatusByDateBillTableBean;
import ReportsModule.BeanPakage.CformStatusByDateItemBean;
import ReportsModule.BeanPakage.CformStatusByDateTableBean;
import ReportsModule.BeanPakage.ChequeReturnsBean;
import ReportsModule.BeanPakage.ChequeReturnsTableBean;
import ReportsModule.BeanPakage.CurrentBalanceBean;
import ReportsModule.BeanPakage.CurrentBalanceTableBean;
import ReportsModule.BeanPakage.LedgerBean;
import ReportsModule.BeanPakage.LedgerReportBean;
import ReportsModule.BeanPakage.LedgerReportTableData;
import ReportsModule.BeanPakage.LedgerTableData;
import ReportsModule.BeanPakage.PartyOutstandingBean;
import ReportsModule.BeanPakage.PartyOutstandingTableBean;
import ReportsModule.BeanPakage.ProductWiseTableData;
import ReportsModule.BeanPakage.PurchaseItemBean;
import ReportsModule.BeanPakage.PurchaseItemStockBean;
import ReportsModule.BeanPakage.PurchaseItemStockTableBean;
import ReportsModule.BeanPakage.PurchaseItemTableBean;
import ReportsModule.BeanPakage.PurchaseQuotationBean;
import ReportsModule.BeanPakage.PurchaseQuotationTable;
import ReportsModule.BeanPakage.PurchaseReportBean;
import ReportsModule.BeanPakage.PurchaseReportTableBean;
import ReportsModule.BeanPakage.PurchaseStockBean;
import ReportsModule.BeanPakage.PurchaseStockTableBean;
import ReportsModule.BeanPakage.ReportByProductWise;
import ReportsModule.BeanPakage.RouteDataBean;
import ReportsModule.BeanPakage.RouteDataTableBean;
import ReportsModule.BeanPakage.SalesChartbyRepwiseBean;
import ReportsModule.BeanPakage.SalesCurrentStockBean;
import ReportsModule.BeanPakage.SalesCurrentStockTableBean;
import ReportsModule.BeanPakage.SalesDateBean;
import ReportsModule.BeanPakage.SalesDateTableData;
import ReportsModule.BeanPakage.SalesInvoiceBean;
import ReportsModule.BeanPakage.SalesInvoiceTableBean;
import ReportsModule.BeanPakage.SalesItemChartBean;
import ReportsModule.BeanPakage.SalesItemChartSubTableBean;
import ReportsModule.BeanPakage.SalesItemChartTableBean;
import ReportsModule.BeanPakage.SalesItemDataBean;
import ReportsModule.BeanPakage.SalesItemGranSummaryTableBean;
import ReportsModule.BeanPakage.SalesItemGrandSummaryBean;
import ReportsModule.BeanPakage.SalesItemTableBean;
import ReportsModule.BeanPakage.SalesMonthlyReportBean;
import ReportsModule.BeanPakage.SalesMonthlyReportTableBean;
import ReportsModule.BeanPakage.SalesQuotationTableBean;
import ReportsModule.BeanPakage.SalesQuotationsBean;
import ReportsModule.BeanPakage.SalesRepWiseBean;
import ReportsModule.BeanPakage.SalesStockBean;
import ReportsModule.BeanPakage.SalesStockTableBean;
import ReportsModule.BeanPakage.TaxAccountReportTableBean;
import ReportsModule.BeanPakage.VoucherReportBean;
import ReportsModule.BeanPakage.VoucherReportTableBean;
import ReportsModule.BeanPakage.YearlySalesBean;
import ReportsModule.BeanPakage.YearlySalesTableBean;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.model.chart.PieChartModel;


public class DBConnection {
    static Connection con;
    static Statement st,st1,st2,st3,st4;
    static ResultSet rs,rs1,rs2,rs3,rs4;
    static double amount;
    static Map<Integer, String> mp = new HashMap<Integer, String>();
    static DecimalFormat dfr = new DecimalFormat("#.##");
    static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public DBConnection() {
        //Statement sss  =Conn_Statement();
    }


    public static Statement Conn_Statement() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory" +
                "", "root", "root");
            st = (Statement)con.createStatement();
            if (st != null) {
                //System.out.println("Connection is Created.............");
                return st;
            }

            else {
                //System.out.println("Connection Failed");
            }

            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return st;
    }
   
    public static Connection connection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory", "root", "root");

            if (con != null) {
                //System.out.println("Connection is Created.............");
                return con;
            }

            else {
                //System.out.println("Connection Failed");
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return con;
    }
    
    
    public static List<SalesStockTableBean> salesStock(SalesStockBean cbd){
            List<SalesStockTableBean>  tdata=new ArrayList<SalesStockTableBean>();
            List<SalesStockTableBean>  tdata1=new ArrayList<SalesStockTableBean>();
            SalesStockTableBean cbdt=new SalesStockTableBean();
            String qry = "";
            st=Conn_Statement();
            String d1, d2;
            d1 = df.format(cbd.getDate1());
            d2 = df.format(cbd.getDate2());
            
            if (cbd.getCategory() != null) {
               
                
               
                    String ite = cbd.getCategory();

                    if (ite.equalsIgnoreCase("ALL")) {
                        ite = "";
                    }
                System.out.println(d1 +"----------\t"+ d2+"\t"+"ite is\t"+ite);
            
            try {
                      System.out.println("ite is --------->"+ite);
                       qry = "SELECT   *   FROM  StockSalesRecord  WHERE  CATEGORY like'%" +  ite + "' AND sdate    between '"+d2+"' AND '"+d1+"' ORDER BY CATEGORY";
                      System.out.println("-->" + qry);
                      
                      rs = st.executeQuery(qry);
                      int k = 0;

                      while (rs.next()) {
                          cbdt=new SalesStockTableBean();
                          Integer a = rs.getInt(2);
                          String b = rs.getString(3);
                          String c = rs.getString(4);
                          Integer d = rs.getInt(5);                                          
                          Date e = rs.getDate(6);                                          
                          
                          
                          cbdt.setItemcode(a);
                          cbdt.setItemname(b);
                          cbdt.setCate(c);
                          cbdt.setQuantity(d);
                          cbdt.setSdate(e);
                          
                          
                          
                          
                         tdata1.add(cbdt);
                      }
                rs.close();
                st.close();
                con.close();
        tdata=tdata1;
                  } catch (Exception ex) {
                      ex.printStackTrace();
                  }
            }
            return tdata;
        }
    
    
    public static List<PurchaseItemStockTableBean> pist(PurchaseItemStockBean cbd){
            List<PurchaseItemStockTableBean>  tdata=new ArrayList<PurchaseItemStockTableBean>();
            List<PurchaseItemStockTableBean>  tdata1=new ArrayList<PurchaseItemStockTableBean>();
            PurchaseItemStockTableBean cbdt=new PurchaseItemStockTableBean();
            String qry = "";
            st=Conn_Statement();
            String d1;
            d1 = df.format(cbd.getDate1());
            
                
           if (cbd.getItemname() != null) {
               
                
               
                    String ite = cbd.getItemname();
                    System.out.println("Item value--------->"+ite);

                    if (ite.equalsIgnoreCase("ALL")) {
                        ite = "";
                    }
                
                System.out.println(d1 +"----------\t"+"ite is\t"+ite);
            
            try {
                      //System.out.println("ite is --------->"+ite);
                       qry = "SELECT *  FROM  StockPurchase WHERE CATEGORY like '%"+  ite + "' Order by CATEGORY" ;
                      System.out.println("-->" + qry);
                      
                      rs = st.executeQuery(qry);
                      int k = 0;

                      while (rs.next()) {
                          cbdt=new PurchaseItemStockTableBean();
                          Integer a = rs.getInt(2);
                          String b = rs.getString(3);
                          String c = rs.getString(4);
                          Integer d = rs.getInt(5);
                            cbdt.setItemcode(a);
                            cbdt.setItemname(b);
                            cbdt.setCate(c);
                            cbdt.setQuanty(d);
                          
                          
                         tdata1.add(cbdt);
                      }
                      rs.close();
                      st.close();
                      con.close();
        tdata=tdata1;
                  } catch (Exception ex) {
                      ex.printStackTrace();
                  }
        }
            System.out.println("------------>"+tdata);
            return tdata;
        }
    
    
    
    public static List<SalesCurrentStockTableBean> item(SalesCurrentStockBean cbd){
            List<SalesCurrentStockTableBean>  tdata=new ArrayList<SalesCurrentStockTableBean>();
            List<SalesCurrentStockTableBean>  tdata1=new ArrayList<SalesCurrentStockTableBean>();
            SalesCurrentStockTableBean cbdt=new SalesCurrentStockTableBean();
            String qry = "";
            st=Conn_Statement();
            String d1;
            d1 = df.format(cbd.getDate1());
            
                
           if (cbd.getItemname() != null) {
               
                
               
                    String ite = cbd.getItemname();
                    System.out.println("Item value--------->"+ite);

                    if (ite.equalsIgnoreCase("ALL")) {
                        ite = "";
                    }
                
                System.out.println(d1 +"----------\t"+"ite is\t"+ite);
            
            try {
                      //System.out.println("ite is --------->"+ite);
                       qry = "SELECT *  FROM  StockSales WHERE CATEGORY like '%"+  ite + "' Order by CATEGORY" ;
                      System.out.println("-->" + qry);
                      
                      rs = st.executeQuery(qry);
                      int k = 0;

                      while (rs.next()) {
                          cbdt=new SalesCurrentStockTableBean();
                          Integer a = rs.getInt(2);
                          String b = rs.getString(3);
                          String c = rs.getString(4);
                          Integer d = rs.getInt(5);
                            cbdt.setItemcode(a);
                            cbdt.setItemname(b);
                            cbdt.setCate(c);
                            cbdt.setQuanty(d);
                          
                          
                         tdata1.add(cbdt);
                      }
                      rs.close();
                      st.close();
                      con.close();
                
        tdata=tdata1;
                  } catch (Exception ex) {
                      ex.printStackTrace();
                  }
        }
            System.out.println("------------>"+tdata);
            return tdata;
        }
    
    
    
    
    public static List types() {

            st = Conn_Statement();
            List taxList = new ArrayList();
            taxList.clear();
            taxList.add("ALL");
            try {
                String qry = "SELECT distinct(CATEGORY)  FROM StockSalesRecord";
                System.out.println(qry);
                rs = st.executeQuery(qry);
                while (rs.next()) {
                    taxList.add(rs.getString(1));
                }
                rs.close();
                st.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return taxList;
        }
        
        public static List types1() {

            st = Conn_Statement();
            List taxList = new ArrayList();
            taxList.clear();
            taxList.add("ALL");
            try {
                String qry = "SELECT   CATEGORY   FROM  StockPurchase order by CATEGORY";
                System.out.println(qry);
                rs = st.executeQuery(qry);
                while (rs.next()) {
                    taxList.add(rs.getString(1));
                }
                rs.close();
                st.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return taxList;
        }
        
        
    
    public static List<PurchaseStockTableBean> purStock(PurchaseStockBean cbd){
           List<PurchaseStockTableBean>  tdata=new ArrayList<PurchaseStockTableBean>();
           List<PurchaseStockTableBean>  tdata1=new ArrayList<PurchaseStockTableBean>();
           PurchaseStockTableBean cbdt=new PurchaseStockTableBean();
           String qry = "";
           st=Conn_Statement();
           String d1, d2;
           d1 = df.format(cbd.getDate1());
           d2 = df.format(cbd.getDate2());
           
           if (cbd.getCategory() != null) {
              
               
              
                   String ite = cbd.getCategory();

                   if (ite.equalsIgnoreCase("ALL")) {
                       ite = "";
                   }
               System.out.println(d1 +"----------\t"+ d2+"\t"+"ite is\t"+ite);
           
           try {
                     System.out.println("ite is --------->"+ite);
                      qry = "SELECT   *   FROM  StockPurchaseRecord  WHERE  CATEGORY like'%" +  ite + "' AND sdate    between '"+d2+"' AND '"+d1+"' ORDER BY CATEGORY";
                     System.out.println("-->" + qry);
                     
                     rs = st.executeQuery(qry);
                     int k = 0;

                     while (rs.next()) {
                         cbdt=new PurchaseStockTableBean();
                         Integer a = rs.getInt(2);
                         String b = rs.getString(3);
                         String c = rs.getString(4);
                         Integer d = rs.getInt(5);                                          
                         Date e = rs.getDate(6);                                          
                         
                         
                         cbdt.setItemcode(a);
                         cbdt.setItemname(b);
                         cbdt.setCate(c);
                         cbdt.setQuantity(d);
                         cbdt.setSdate(e);
                         
                         
                         
                         
                        tdata1.add(cbdt);
                     }
                     rs.close();
                     st.close();
                     con.close();
               
       tdata=tdata1;
                 } catch (Exception ex) {
                     ex.printStackTrace();
                 }
           }
           return tdata;
       }
    
    
    
    public static List routeCombo1(PartyOutstandingBean rpw)
            {     
            List places=new ArrayList();
                ResultSet rs;
                  Statement st;
                  st =Conn_Statement();
                    String qry = "";
                if(rpw.getRoute()!=null && rpw.getReType()!=null){
                try {
                    String sel =rpw.getRoute();
                    String rsel = rpw.getReType();
                    places.clear();
                   places.add("All");   
                   if(sel.equalsIgnoreCase("All"))
                   {  
                        if(rsel.equalsIgnoreCase("All"))
                        {            
                        rsel = "%";
                        }
                        
              qry = "SELECT AREANAME  FROM RouteMaster  WHERE  REPNAME like  '%"+rsel+"%'   ORDER BY ROUTEID";
                  }
                   else{
                      if(rsel.equalsIgnoreCase("All"))
                      {
                        
                        rsel = "%";
                        }
                     qry = "SELECT AREANAME  FROM RouteMaster WHERE ROUTEID = '"+sel+"' and REPNAME like  '%"+rsel+"%'   ORDER BY ROUTEID";
                   }
                    System.out.println(""+qry);
                     rs = st.executeQuery(qry);
                     while(rs.next())
                     {
                     places.add(rs.getString(1));
                     }         
                    rs.close();
                    st.close();
                    con.close();         
                } catch (Exception e) {
                    e.printStackTrace();
                }   
                
            }
            return places;
            }


    public static List representData1() {

            st = Conn_Statement();
            List taxList = new ArrayList();
            taxList.clear();
            taxList.add("ALL");
            try {
                String qry = "SELECT distinct(ACNAME)  FROM  cform order by ACNAME";
                System.out.println(qry);
                rs = st.executeQuery(qry);
                while (rs.next()) {
                    taxList.add(rs.getString(1));
                }
                rs.close();
                st.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return taxList;
        }
    
    
    public static List Getplace() {

            st = Conn_Statement();
            List taxList = new ArrayList();
            taxList.clear();
            taxList.add("All");
            try {
                String qry = "select distinct(areaname) from routemaster order by areaname";
                System.out.println(qry);
                rs = st.executeQuery(qry);
                while (rs.next()) {
                    taxList.add(rs.getString(1));
                }
                rs.close();
                st.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            System.out.println("getplace------------>"+taxList);
            return taxList;
        }
    
    
    
    public static List<PartyOutstandingTableBean> pData(PartyOutstandingBean rpw) {
                List<PartyOutstandingTableBean> tdata1=new ArrayList<PartyOutstandingTableBean>();
                
                  st = Conn_Statement();
                String qry= " ";
                String d1, d2;
                d1 = df.format(rpw.getDate1());
                d2 = df.format(rpw.getDate2());
                if(rpw.getSalesType()!=null&& rpw.getReType()!=null&& rpw.getRoute()!=null&& rpw.getPlace()!=null ){
               Double  amount = 0.0;
               Integer acc=0;
                try {
                
              String stype= "", repre= "", rotid= "",city= "";
              
              stype =  rpw.getSalesType();
              if(stype.equalsIgnoreCase("All"))
              {
              stype = "%";
              }  
              repre =  rpw.getReType();
               if(repre.equalsIgnoreCase("All"))
              {
              repre = "%";
              }   
              city = rpw.getPlace();
               if(city.equalsIgnoreCase("All"))
              {
              city = "%";
              }
              
              
              
          rotid = rpw.getRoute();
               if(rotid.equalsIgnoreCase("All"))
              {
                  rotid = "%";
              qry = "SELECT acccode,companyname,addr,city,repname,currbal  FROM Customer  WHERE  city like '%" + city + "%' and  regdate between '"+d2+"' and '"+d1+"' and repname like '%" + repre + "%' and R_id like '%" + rotid + "%'  order BY  city, repname";
                   
              }else
               
               {
            qry = "SELECT acccode,companyname,addr,city,repname,currbal  FROM Customer  WHERE  city like '%" + city + "%' and  regdate between '"+d2+"' and '"+d1+"' and  repname like '%" + repre + "%' and R_id like '%" + rotid + "%' order BY  city, repname";
               
               }
                    System.out.println("Get Data Query----------------------->" + qry);

                    rs = st.executeQuery(qry);
                    while (rs.next()) {
                        PartyOutstandingTableBean ptdata=new PartyOutstandingTableBean();
                        String net = rs.getString(6);
                        amount = Double.parseDouble(net) ;
                        String acc1=rs.getString(1);
                        acc = Integer.parseInt(acc1) ;
                        
                          
                        ptdata.setAcccode(acc);
                        ptdata.setCname(rs.getString(2));
                        ptdata.setAddr(rs.getString(3));
                        ptdata.setCity(rs.getString(4));
                        ptdata.setRep(rs.getString(5));
                        ptdata.setCurbal(amount);
                       
                        tdata1.add(ptdata);
                    }
                    
                    rs.close();
                    st.close();
                    con.close();
                   
        rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
                System.out.println("Getting Data of party is ---------------"+tdata1);
                return tdata1;
                
            }
    
    
    
    public static List<SalesQuotationTableBean> quotdata(SalesQuotationsBean sd) {
            System.out.println("in databse connection ");
            //       SalesDateBean sd = new SalesDateBean();
            SalesQuotationTableBean std;
            List<SalesQuotationTableBean> tdata = new ArrayList<SalesQuotationTableBean>();
            List<SalesQuotationTableBean> data1 = new ArrayList<SalesQuotationTableBean>();
            String net = null;
            //   ResultSet rs = null;
            //  Statement st = null;
            Double d = 0.0;
            //st = DBConnection.DBConnection.Conn_Statement();
            st = Conn_Statement();

            if (sd.getType() != null) {
                sd.setAmount(0.0);
                String qry = "";
                try {
                    String ite = sd.getType();

                    if (ite.equalsIgnoreCase("All")) {
                        ite = "";
                    }

                    String d1, d2;
                    d1 = df.format(sd.getDate1());
                    d2 = df.format(sd.getDate2());
                    System.out.println(d1 +"----------\t"+ d2+"\t"+"ite is\t"+ite);
                    qry ="select distinct billno,salestype,acname,route,R_rep,invdate,invno,netvalue from quotesales   WHERE   taxtype   like'%" +  ite + "'  and invdate between '" + d2 + "' AND '" + d1 + "'   ORDER  BY R_rep";
                    rs = st.executeQuery(qry);
                    System.out.println("value\t" + qry);
                    //do{
                    while (rs.next()) {
                        //System.out.println("in while loop");
                        
                        std = new SalesQuotationTableBean();
                       
                        net = rs.getString(8);
                        d = Double.parseDouble(net) + sd.getAmount();
                        sd.setAmount((Double.parseDouble(dfr.format(d))));
                        std.setBillno(rs.getString(1));
                        std.setStype(rs.getString(2));
                        std.setAccname(rs.getString(3));
                        std.setRoute(rs.getString(4));
                        std.setRep(rs.getString(5));
                        System.out.println("Date------------pattern"+rs.getDate(6));
                        std.setInvdate(rs.getDate(6));
                        std.setInvno(rs.getString(7));
                        std.setNet(d);
                        
                       

                        data1.add(std);
                        
                        //System.out.println(std+"---------\t"+data1);

                    } //while(rs.next());
                    // System.out.println("invvalue"+sd.amount);
                    
                    tdata = data1;
                    System.out.println("before returning" + d);
                    rs.close();
                    st.close();
                    con.close();

                }

                catch (Exception e) {
                    e.printStackTrace();

                }
                //           FacesContext context = FacesContext.getCurrentInstance();
                //        context.addMessage(null, new FacesMessage("Please Select Tax Type"));


            }
            return tdata;
        }
        
        public static List<PurchaseQuotationTable> purdata(PurchaseQuotationBean sd) {
            System.out.println("in databse connection ");
            //       SalesDateBean sd = new SalesDateBean();
            PurchaseQuotationTable std;
            List<PurchaseQuotationTable> tdata = new ArrayList<PurchaseQuotationTable>();
            List<PurchaseQuotationTable> data1 = new ArrayList<PurchaseQuotationTable>();
            String net = null;
            //   ResultSet rs = null;
            //  Statement st = null;
            Double d = 0.0;
            //st = DBConnection.DBConnection.Conn_Statement();
            st = Conn_Statement();

            if (sd.getType() != null) {
                sd.setAmount(0.0);
                String qry = "";
                try {
                    String ite = sd.getType();

                    if (ite.equalsIgnoreCase("All")) {
                        ite = "";
                    }

                    String d1, d2;
                    d1 = df.format(sd.getDate1());
                    d2 = df.format(sd.getDate2());
                    System.out.println(d1 +"----------\t"+ d2+"\t"+"ite is\t"+ite);
                    qry ="select distinct billno,salestype,acname,city,R_rep,invdate,invno,netvalue from quotepurchase   WHERE   taxtype   like'%" +  ite + "'  and invdate between '" + d2 + "' AND '" + d1 + "'   ORDER  BY R_rep";
                    rs = st.executeQuery(qry);
                    System.out.println("value\t" + qry);
                    //do{
                    while (rs.next()) {
                        //System.out.println("in while loop");
                        
                        std = new PurchaseQuotationTable();
                       
                        net = rs.getString(8);
                        d = Double.parseDouble(net) + sd.getAmount();
                        sd.setAmount((Double.parseDouble(dfr.format(d))));
                        std.setBillno(rs.getString(1));
                        std.setStype(rs.getString(2));
                        std.setAccname(rs.getString(3));
                        std.setCity(rs.getString(4));
                        std.setRep(rs.getString(5));
                        std.setInvdate(rs.getDate(6));
                        std.setInvno(rs.getString(7));
                        std.setNet(d);
                        
                       

                        data1.add(std);
                        
                        //System.out.println(std+"---------\t"+data1);

                    } //while(rs.next());
                    // System.out.println("invvalue"+sd.amount);
                    tdata = data1;
                    System.out.println("before returning" + tdata);
                    rs.close();
                    st.close();
                    con.close();

                }

                catch (Exception e) {
                    e.printStackTrace();

                }
                //           FacesContext context = FacesContext.getCurrentInstance();
                //        context.addMessage(null, new FacesMessage("Please Select Tax Type"));


            }
            return tdata;
        }
    
    
    public static List<LedgerTableData> ledData(LedgerBean cbd){
            List<LedgerTableData>  tdata=new ArrayList<LedgerTableData>();
            List<LedgerTableData>  tdata1=new ArrayList<LedgerTableData>();
            LedgerTableData cbdt=new LedgerTableData();
            String qry = "";
            st=Conn_Statement();
            String d1, d2;
            d1 = df.format(cbd.getDate1());
            d2 = df.format(cbd.getDate2());
            try {
                      
                       qry = "SELECT lno,ldate,cname,city,billno,invno,dbamt,cramt FROM Ledger WHERE    ldate    between '"+d2+"' AND '"+d1+"'  ORDER BY lno";
                      System.out.println("-->" + qry);
                      
                      rs = st.executeQuery(qry);
                      int k = 0;

                      while (rs.next()) {
                          cbdt=new LedgerTableData();
                          String a = rs.getString(1);
                          Date b = rs.getDate(2);
                          String c = rs.getString(3);
                          String d = rs.getString(4);                                          
                          String e = rs.getString(5);
                          String f = rs.getString(6)+"-Sales-invoice";
                          Double g = rs.getDouble(7);
                          Double h = rs.getDouble(8);
                          
                          
                          cbdt.setSno(a);
                          cbdt.setInvdate(b);
                          cbdt.setCustomer(c);
                          cbdt.setCity(d);
                          cbdt.setBillno(e);
                          cbdt.setParticulars(f);
                          cbdt.setDebit(g);
                          cbdt.setCredit(h);
                          
                          
                         tdata1.add(cbdt);
                      }
                      rs.close();
                      st.close();
                      con.close();
                
        tdata=tdata1;
                  } catch (Exception ex) {
                      ex.printStackTrace();
                  }
            return tdata;
        }
        
        
        public static List<LedgerReportTableData> ledreport(LedgerReportBean cbd){
            List<LedgerReportTableData>  tdata=new ArrayList<LedgerReportTableData>();
            List<LedgerReportTableData>  tdata1=new ArrayList<LedgerReportTableData>();
            LedgerReportTableData cbdt=new LedgerReportTableData();
            String qry = "";
            st=Conn_Statement();
            String d1, d2;
            d1 = df.format(cbd.getDate1());
            d2 = df.format(cbd.getDate2());
            
            if (cbd.getParty() != null) {
               
                
               
                    String ite = cbd.getParty();

                    if (ite.equalsIgnoreCase("ALL")) {
                        ite = "";
                    }
                System.out.println(d1 +"----------\t"+ d2+"\t"+"ite is\t"+ite);
            
            try {
                      System.out.println("ite is --------->"+ite);
                       qry = "select distinct lno,ldate,billno,cname,dbamt,cramt from ledger  WHERE  cname like'%" +  ite + "' AND ldate    between '"+d2+"' AND '"+d1+"' ORDER BY lno";
                      System.out.println("-->" + qry);
                      
                      rs = st.executeQuery(qry);
                      int k = 0;
                      while (rs.next()) {
                          cbdt=new LedgerReportTableData();
                          String a = rs.getString(1);
                          //System.out.println("------------->"+a);
                          Date b = rs.getDate(2);
                          String c = rs.getString(3);
                          String d = rs.getString(4);                                          
                          Double e = rs.getDouble(5);
                          Double f = rs.getDouble(6);
                          
                          cbdt.setSno(a);
                          cbdt.setInvdate(b);
                          cbdt.setBillno(c);
                          cbdt.setParticulars(d);
                          cbdt.setDebit(e);
                          cbdt.setCredit(f);
                          System.out.print("----------getting the values>"+cbdt.getSno());
                          
                          
                         tdata1.add(cbdt);
                      }
                      rs.close();
                      st.close();
                      con.close();
                
        tdata=tdata1;
                  } catch (Exception ex) {
                      ex.printStackTrace();
                  }
            }
            return tdata;
        }
    
    

    public static List<SalesDateTableData> salesdata(SalesDateBean sd) {
        System.out.println("in databse connection ");
        //       SalesDateBean sd = new SalesDateBean();
        SalesDateTableData std;
        List<SalesDateTableData> tdata = new ArrayList<SalesDateTableData>();
        List<SalesDateTableData> data1 = new ArrayList<SalesDateTableData>();
        String net = null;
        //   ResultSet rs = null;
        //  Statement st = null;
        Double d = 0.0;
        //st = DBConnection.DBConnection.Conn_Statement();
        st = Conn_Statement();

        if (sd.getType() != null) {
            sd.setAmount(0.0);
            String qry = "";
            try {
                String ite = sd.getType();

                if (ite.equalsIgnoreCase("All")) {
                    ite = "";
                }

                String d1, d2;
                d1 = df.format(sd.getDate1());
                d2 = df.format(sd.getDate2());
                qry =
"SELECT distinct  Billno, accno,acname,city,R_rep,invdate,invno,INVVALUE  From  Vat_Sales   WHERE   taxtype   like'%" +
  ite + "'  and invdate between '" + d2 + "' AND '" + d1 + "'   ORDER  BY R_rep";
                rs = st.executeQuery(qry);
                System.out.println("value" + qry);
                //do{
                while (rs.next()) {
                    std = new SalesDateTableData();
                    net = rs.getString(8);
                    d = Double.parseDouble(net) + sd.getAmount();
                    sd.setAmount((Double.parseDouble(dfr.format(d))));
                    std.setBillno(rs.getString(1));
                    std.setAccno(rs.getString(2));
                    std.setAcname(rs.getString(3));
                    std.setCity(rs.getString(4));
                    std.setRep(rs.getString(5));
                    std.setInvdate(rs.getDate(6));
                    std.setInvno(rs.getString(7));
                    std.setInvvalue(net);

                    data1.add(std);

                } //while(rs.next());
                // System.out.println("invvalue"+sd.amount);
                tdata = data1;
                System.out.println("before returning" + tdata);
                st.close();
                rs.close();
con.close();
            }

            catch (Exception e) {
                e.printStackTrace();

            }
            //           FacesContext context = FacesContext.getCurrentInstance();
            //        context.addMessage(null, new FacesMessage("Please Select Tax Type"));


        }
        return tdata;
    }
    
    
    public static List<PurchaseItemTableBean> purchaseItemData( PurchaseItemBean sd) {
        List<PurchaseItemTableBean> tdata = new ArrayList<PurchaseItemTableBean>();
        List<PurchaseItemTableBean> tdata1 = new ArrayList<PurchaseItemTableBean>();
        PurchaseItemTableBean ptb;
        st = Conn_Statement();
       String qry="";
        try {
   
                  if(sd.getIcategoty().equalsIgnoreCase("All"))
                  {
                qry = "SELECT   itemcode  ,itemname, itemrate  ,CATEGORY , salesprice , MRP  , TAX ,Disc , maxdisc  FROM  ItemPurchase WHERE CATEGORY like '%%'";
                System.out.println(qry);
                  }
                else
                  {
                qry = "SELECT itemcode  ,itemname, itemrate  ,CATEGORY , salesprice , MRP  , TAX ,Disc , maxdisc  FROM  ItemPurchase WHERE CATEGORY like '%"+sd.getIcategoty()+"%'";
                  }
                
                  System.out.println("Get Data Query----------------------->"+qry);
                  
                  rs = st.executeQuery(qry);
                  
                  while(rs.next())
                  {
                  ptb=new PurchaseItemTableBean();
                  ptb.setIcode(rs.getInt(1));
                      ptb.setIname(rs.getString(2));
                      ptb.setRate(rs.getDouble(3));
                      ptb.setIcat(rs.getString(4));
                      ptb.setIprice((rs.getDouble(5)));
                      ptb.setMrp(rs.getDouble(6));
                      ptb.setTax(rs.getDouble(7));
                      ptb.setDisc(rs.getDouble(8));
                      ptb.setMxdisc(rs.getDouble(9));
                      tdata1.add(ptb);         
                  }
            rs.close();
            st.close();
            con.close();
         tdata=tdata1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tdata;
    }
    public static List<SalesItemGranSummaryTableBean>SalesItemGrand(SalesItemGrandSummaryBean sgb){
        List<SalesItemGranSummaryTableBean>tdata=new ArrayList<SalesItemGranSummaryTableBean>();
        List<SalesItemGranSummaryTableBean>tdata1=new ArrayList<SalesItemGranSummaryTableBean>();
        String d1, d2,cat2="",cata="";
        d1 = df.format(sgb.getDate1());
        d2 = df.format(sgb.getDate2());
        if (sgb.getStype() != null) {
            try{
                String ite = sgb.getStype();
                if (ite.equalsIgnoreCase("All")) {
                                   ite = "";
                               }
                st = Conn_Statement();
                rs=st.executeQuery("SELECT distinct(CATEGORY) FROM ItemMaster ORDER BY CATEGORY");
                while(rs.next()){
                    String catgery = rs.getString(1);
                    if(catgery!=null){
                        ResultSet rs1;
                        Statement st1=Conn_Statement();
                        rs1=st1.executeQuery("SELECT distinct(REPNAME) FROM RouteMaster ORDER BY  REPNAME");
                        while(rs1.next()){
                            String repnam = rs1.getString(1);
                            String qry = "SELECT sum(quantity),sum(netvalue),icat  FROM Vat_Sales WHERE R_rep like '%" + repnam + "%' AND  `icat` like '%" + catgery + "%'  and  taxtype  like'%" + ite + "%' and  invdate between '"+d2+"' AND '"+d1+"'   GROUP by icat ";
                           Statement st2=Conn_Statement();
                            ResultSet rs2=st2.executeQuery(qry);
                            if(rs2.next()){
                                if (!catgery.equalsIgnoreCase(cat2)) {
                                                              //    col++;
                                                                  String q = rs2.getString(1);
                                                                  String s = rs2.getString(2);
                                                                  System.out.println("New Row   :   " + q + "\t\t" + s + "******************************************************");
                                                                //  model.addRow(new Object[]{cata, q + "                             " + s});
                                                                  cat2 = cata;
                                                              } 
                            }
                            rs2.close();
                            st2.close();
                            con.close();
        
                        }
                        rs1.close();
                        st1.close();
                        con.close();
                        
                    }
                }
                rs.close();
                st.close();
                con.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        
        }
       return tdata; 
    }
    public static List yearData(){
        List yList = new ArrayList();
        st=Conn_Statement();
        String qry="Select * from YR";
        try{
      rs=st.executeQuery(qry);
            yList.clear();
            while(rs.next()){
             yList.add(rs.getString(1));
            }
            rs.close();
            st.close();
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return yList;
    }    
    public static List<CurrentBalanceTableBean> currentBalanceData( CurrentBalanceBean sd) {
        List<CurrentBalanceTableBean> tdata = new ArrayList<CurrentBalanceTableBean>();
      List<CurrentBalanceTableBean> tdata1 = new ArrayList<CurrentBalanceTableBean>();
        CurrentBalanceTableBean cbtb;
        st = Conn_Statement();
        String qry="";
        try {
           
               if(sd.getType().equalsIgnoreCase("All"))
               {
             qry = "SELECT * FROM `Curr_Bal`WHERE optype like '%%'";
               }
             else
               {
             qry = "SELECT * FROM Curr_Bal WHERE optype like  '%"+sd.getType()+"%'";
               }
             
               System.out.println("Get Data Query----------------------->"+qry);
               
               rs = st.executeQuery(qry);
               
               while(rs.next())
               {
               cbtb=new CurrentBalanceTableBean();
                 cbtb.setAcno(rs.getString(2));
                   cbtb.setType(rs.getString(3));
                   cbtb.setName(rs.getString(4));
                   cbtb.setBal(rs.getString(5));
                  tdata1.add(cbtb);
               }
               rs.close();
               st.close();
               con.close();
            
              tdata=tdata1;       
           } catch (Exception e) {
               e.printStackTrace();
           }
        return tdata;
    }  
    public static List icatData(){
        List icatList = new ArrayList();
        st = Conn_Statement();
        String qry="";
        try{
            icatList.clear();
                
                     rs = st.executeQuery("SELECT distinct(CATEGORY)  FROM `ItemMaster`");

                     icatList.add("All");

                       while (rs.next()) {
                           //if(rs2!=null)
                           icatList.add(rs.getString(1));
                       }
                       rs.close();
                      st.close();
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return icatList;
    }
    
    public static List routeListData(SalesItemChartBean sicb){
        List itemList=new ArrayList();
        st = Conn_Statement();
        String qry="";
   
                   try {
                       itemList.clear();
                       
                       if(sicb.getRepresentative().equalsIgnoreCase("All"))
                       {
          qry = "SELECT distinct(Tourname)  FROM  routemaster";
                       }
                       else
                       {
                       qry = "SELECT distinct(Tourname)  FROM  routemaster  WHERE Repname like '%"+sicb.getRepresentative()+"%'";
                       
                       }
                        rs = st.executeQuery(qry);
                       itemList.add("All");
                       while (rs.next()) { 
                           if(rs.getString(1)!=null){
                           itemList.add(rs.getString(1));
                       }
                       }
                       rs.close();
                       st.close();
                       con.close();

                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               
        return itemList; 
    }
    
    public static List itemgroupListData(){
        List itemList=new ArrayList();
        st = Conn_Statement();
        try{
            itemList.clear();
                      String qry = "SELECT distinct(MTCat) FROM `ItemMaster`";
                itemList.add("All");
                 rs = st.executeQuery(qry);
                      while (rs.next()) {
                          if(rs.getString(1)!=null){
                          itemList.add(rs.getString(1));
                          }
                      }
            rs.close();
            st.close();
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return itemList; 
    }
    
    public static List<CFormStatusByQuartersTableBean> quarteraData( String s) {
        List<CFormStatusByQuartersTableBean> tdata = new ArrayList<CFormStatusByQuartersTableBean>();
        List<CFormStatusByQuartersTableBean> tdata1 = new ArrayList<CFormStatusByQuartersTableBean>();
        CFormStatusByQuartersTableBean csqt;
        st = Conn_Statement();
        String qry="";
        try {
            qry = "SELECT ACCODE,ACNAME,INVNO ,sum(INVVAL) ,Clear,CITY,REP   FROM CFORM WHERE ACNAME like '%%' and  CFDATE   "+s+"  group by ACNAME,INVNO,ACCODE, Clear ,REP,CITY,rid"  ;
           rs=st.executeQuery(qry);
            while(rs.next()){
                csqt=new CFormStatusByQuartersTableBean();
                csqt.setAccCode(rs.getString(1));
                csqt.setAccName(rs.getString(2));
                csqt.setCity(rs.getString(6));
                csqt.setRep(rs.getString(7));
                csqt.setInvNo(rs.getString(3));
                csqt.setInvValue(rs.getString(4));
                csqt.setStatus(rs.getString(5));
                tdata1.add(csqt);        
            }
            
            rs.close();
            st.close();
            con.close();
            
tdata=tdata1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tdata;
    }
    
    
    public static List<CFormStatusByQuartersTableBean> quarterNameData( CFormStatusByQuartersBean csqb,String s) {
        List<CFormStatusByQuartersTableBean> tdata = new ArrayList<CFormStatusByQuartersTableBean>();
        List<CFormStatusByQuartersTableBean> tdata1 = new ArrayList<CFormStatusByQuartersTableBean>();
        CFormStatusByQuartersTableBean csqt;
        st=Conn_Statement();
        String qry="";
        try {
            if(csqb.getCname().isEmpty())
                    {
                   qry = "SELECT ACCODE,ACNAME,INVNO ,sum(INVVAL) ,Clear,CITY,REP    FROM CFORM WHERE ACNAME like '%%' and  CFDATE   "+s+"  group by ACNAME,INVNO,ACCODE,Clear,REP,CITY"  ;
                    }else
                    {
                     qry = "SELECT ACCODE,ACNAME,INVNO ,sum(INVVAL) ,Clear,CITY,REP    FROM CFORM WHERE ACNAME like '"+csqb.getCname()+"%' and  CFDATE   "+s+"  group by ACNAME,INVNO,ACCODE, Clear,REP,CITY"  ;
                    }
                    System.out.println("");
                    System.out.println("PERIOD---------->"+qry);
                    rs = st.executeQuery(qry);
                    int k=0;
                    
                    while(rs.next())
                    {
                        csqt=new CFormStatusByQuartersTableBean();
                        csqt.setAccCode(rs.getString(1));
                        csqt.setAccName(rs.getString(2));
                        csqt.setCity(rs.getString(6));
                        csqt.setRep(rs.getString(7));
                        csqt.setInvNo(rs.getString(3));
                        csqt.setInvValue(rs.getString(4));
                        csqt.setStatus(rs.getString(5));
                        tdata1.add(csqt);        
                    }
            rs.close();
            st.close();
            con.close();
            
           tdata=tdata1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tdata;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static List<SalesItemTableBean> salesItemData( SalesItemDataBean sd) {
        List<SalesItemTableBean> tdata = new ArrayList<SalesItemTableBean>();
        List<SalesItemTableBean> tdata1 = new ArrayList<SalesItemTableBean>();
        SalesItemTableBean sitb;
        st = Conn_Statement();
        String qry="";
        try {
            if (sd.getItem().equalsIgnoreCase("All")) {
            
                               qry = "SELECT   itemcode  ,itemname, itemrate  ,CATEGORY , aproduct , MRP  , TAX ,Disc , maxdisc   FROM  ItemMaster WHERE CATEGORY like '%%'  ORDER by CATEGORY";
                          
                           } else {
                               qry = "SELECT itemcode  ,itemname, itemrate  ,CATEGORY , aproduct , MRP  , TAX ,Disc , maxdisc  FROM  ItemMaster WHERE CATEGORY like '%" + sd.getItem() + "%'   ORDER by CATEGORY";
                           }
            System.out.println("Get Data Query----------------------->" + qry);
                        

                           rs = st.executeQuery(qry);

                           while (rs.next()) {
                            sitb=new SalesItemTableBean();
                            sitb.setIcode(rs.getInt(1));
                             sitb.setIname(rs.getString(2));
                               sitb.setRate(rs.getDouble(3));
                               sitb.setCategory(rs.getString(4));
                               sitb.setAproduct(rs.getString(5));
                               sitb.setAmount(rs.getDouble(6));
                               sitb.setTax(rs.getDouble(7));
                               sitb.setDisc(rs.getDouble(8));
                               sitb.setMaxdisc(rs.getDouble(9));
                               tdata1.add(sitb);
                               
                             //  model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)});

                           }
              rs.close();
             st.close();
            con.close();
        tdata=tdata1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tdata;
    }
    public static List<TaxAccountReportTableBean> taxDetailsData( ) {
        List<TaxAccountReportTableBean> tdata = new ArrayList<TaxAccountReportTableBean>();
        List<TaxAccountReportTableBean> tdata1 = new ArrayList<TaxAccountReportTableBean>();
        TaxAccountReportTableBean trtb;
        st = Conn_Statement();
        try {

            String qry = "SELECT taxno, region, taxname ,  tax, cf, cftax  FROM  Tax";
                   System.out.println("-------->"+qry);
                 rs = st.executeQuery(qry);
                 
                 while(rs.next())
                 {
                     trtb=new TaxAccountReportTableBean();
                     trtb.setTaxno(rs.getString(1));
                     trtb.setRegion(rs.getString(2));
                     trtb.setTaxname(rs.getString(3));
                     trtb.setTaxpercent(rs.getString(4));
                     trtb.setCfreq(rs.getString(5));
                     trtb.setCftax(rs.getString(6));
tdata1.add(trtb);
                 
                 }
            rs.close();
            st.close();
            con.close();
tdata=tdata1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tdata;
    }
    public static List<AccountGroupTableBean> accDetailsData( ) {
        List<AccountGroupTableBean> tdata = new ArrayList<AccountGroupTableBean>();
        List<AccountGroupTableBean> tdata1 = new ArrayList<AccountGroupTableBean>();
        AccountGroupTableBean agt;
        st = Conn_Statement();
        try {
            String qry = "SELECT Acc_No, MainGroup, SubGroup, Type, Opbal,CurrBal  FROM AccountGroup";
                    System.out.println("-------->"+qry);
                  rs = st.executeQuery(qry);
                  
                  while(rs.next())
                  {
                  agt=new AccountGroupTableBean();
                      agt.setAccno(rs.getString(1));
                      agt.setMgroup(rs.getString(2));
                      agt.setSgroup(rs.getString(3));
                      agt.setType(rs.getString(4));
                      agt.setOpbal(rs.getDouble(5));
                      agt.setCurrbal(rs.getDouble(6));
                      tdata1.add(agt);
                  }
            rs.close();
            st.close();
            con.close();
            tdata=tdata1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tdata;
    }
    
    public static List<PurchaseReportTableBean> purchaseData( PurchaseReportBean sd) {
        PurchaseReportTableBean prtb;
        List<PurchaseReportTableBean> tdata = new ArrayList<PurchaseReportTableBean>();
        List<PurchaseReportTableBean> tdata1 = new ArrayList<PurchaseReportTableBean>();
        st = Conn_Statement();
        String d1, d2;
        d1 = df.format(sd.getDate1());
        d2 = df.format(sd.getDate2());
        String qry = "";
        if(sd.getType()!=null)
        {
            try {
               
                String ite =sd.getType();
                
                if(ite.equalsIgnoreCase("All"))
                {
                    ite = "";
                }
               
              qry = "SELECT distinct Billno, accno,acname,city,R_rep,invdate,invno ,INVVALUE  FROM  Vat_Purchase   WHERE   taxtype   like'%"+ite+"'  and  invdate between '"+d2+"' AND  '"+d1+"'";
                System.out.println(qry);
          //                                                        1                   2                    3             4         5             6                      7               8             
              
                //System.out.println("Get Data Query----------------------->"+qry);
                
                rs = st.executeQuery(qry);
                
                while(rs.next())
                {
                     prtb=new PurchaseReportTableBean();
                   // String net = rs.getString(8);
                     prtb.setBillno(rs.getString(1));
                    prtb.setAccno(rs.getString(2));
                    prtb.setAcname(rs.getString(3));
                    prtb.setRoute(rs.getString(4));
                    prtb.setRep(rs.getString(5));
                    prtb.setInvdate(rs.getDate(6));
                    prtb.setInvno(rs.getString(7));
                    prtb.setInvvalue(rs.getString(8));
                    tdata1.add(prtb);
             //   model.addRow(new Object[]{   rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),df.format(rs.getDate(6)),rs.getString(7),net});
                
                }
                
                rs.close();
                st.close();
                con.close();
                
                tdata=tdata1;             
            }   
          
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tdata;
    } 
    public static List<VoucherReportTableBean> voucherTableData(VoucherReportBean vrb){
        List<VoucherReportTableBean>  tdata=new ArrayList<VoucherReportTableBean>();
        List<VoucherReportTableBean>  tdata1=new ArrayList<VoucherReportTableBean>();
        VoucherReportTableBean vrtb;
        st = Conn_Statement();
        String d1, d2;
        d1 = df.format(vrb.getDate1());
        d2 = df.format(vrb.getDate2());
        String qry="";
        try {
         
              String ite = vrb.getType();
              
              if(ite.equalsIgnoreCase("All"))
              {
                  ite = "";
              }
             
            qry = "SELECT  vno, vdate, vname, cname,ptype, amt,chq   FROM Voucher WHERE vname     like'%"+ite+"%'  and  vdate between '"+d2+"' AND '"+d1+"' ";
              System.out.println("Get Data Query----------------------->"+qry);
              
              rs = st.executeQuery(qry);
              
              while(rs.next())
              {
                  vrtb=new VoucherReportTableBean();
                  
                  Double net = rs.getDouble(6);
                  vrtb.setVno(rs.getString(1));
                  vrtb.setDate(rs.getDate(2));
                  vrtb.setVtype(rs.getString(3));
                  vrtb.setVname(rs.getString(4));
                  vrtb.setPaytype(rs.getString(5));  
                  vrtb.setChkno(rs.getString(7));
                  vrtb.setVamount(net);   
                  tdata1.add(vrtb);
              }
            
              rs.close();
              st.close();
              con.close();
            
            tdata=tdata1;    
          } catch (Exception e) {
              e.printStackTrace();
          }
        return tdata;
    }
    
   
    public static List<CformStatusByDateTableBean> cformTableData(CformStatusByDateBean cbd){
        List<CformStatusByDateTableBean>  tdata=new ArrayList<CformStatusByDateTableBean>();
        List<CformStatusByDateTableBean>  tdata1=new ArrayList<CformStatusByDateTableBean>();
        CformStatusByDateTableBean cbdt=new CformStatusByDateTableBean();
        String qry = "";
        st=Conn_Statement();
        String d1, d2;
        d1 = df.format(cbd.getDate1());
        d2 = df.format(cbd.getDate2());
        System.out.println(d1+"---"+d2);
        try {
                  if (cbd.getCName().isEmpty()) {
                      qry = "SELECT ACCODE,ACNAME,INVNO ,sum(INVVAL) ,Clear,CITY,REP   FROM CFORM WHERE ACNAME like '%%' and  CFDATE  between '"+d2+"' AND '"+d1+"' group by ACNAME,INVNO,ACCODE,Clear,REP,CITY,rid";
                      System.out.println(qry);
                  } else {
                      qry = "SELECT ACCODE,ACNAME,INVNO ,sum(INVVAL) ,Clear,CITY,REP      FROM CFORM WHERE ACNAME like '" + cbd.getCName() + "%' and  CFDATE  between '"+d2+"' AND '"+d1+"' group by ACNAME,INVNO,ACCODE,Clear,REP,CITY,rid";
                      System.out.println(qry);
                  }
                  rs = st.executeQuery(qry);
                  int k = 0;

                  while (rs.next()) {
                      cbdt=new CformStatusByDateTableBean();
                      String a = rs.getString(1);
                      String b = rs.getString(2);
                      String c = rs.getString(3);
                      String d = rs.getString(4);
                      String e = rs.getString(5);
         cbdt.setAccCode(a);
         cbdt.setAccName(b);           
         cbdt.setCity(rs.getString(6));
         cbdt.setRep(rs.getString(7));
         cbdt.setInvNo(c);
         cbdt.setInvValue(d);
         cbdt.setStatus(e);
                     tdata1.add(cbdt);
                  }
                  rs.close();
                  st.close();
                  con.close();
            
tdata=tdata1;
              } catch (Exception ex) {
                  ex.printStackTrace();
              }
        return tdata;
    }
     public static List<CformStatusByDateBillTableBean> billData(CformStatusByDateBean cbd,CformStatusByDateTableBean selectedCbt){
        List<CformStatusByDateBillTableBean>  tdata=new ArrayList<CformStatusByDateBillTableBean>();
        List<CformStatusByDateBillTableBean>  tdata1=new ArrayList<CformStatusByDateBillTableBean>();
        CformStatusByDateBillTableBean billBean;
        Double inv = 0.0;
        String qry = "";
        st=Conn_Statement();
        String d1, d2;
        d1 = df.format(cbd.getDate1());
        d2 = df.format(cbd.getDate2());
       /*  cbd.setDataRange(((d2+" , "+d1)));
         cbd.setAccCode(selectedCbt.getAccCode());
         cbd.setCustDetails(selectedCbt.getAccName()); */
               try {
                
                       qry = "SELECT BILLNO, CFDATE,ACNAME,INVVAL  FROM CFORM WHERE ACNAME like '" +  selectedCbt.getAccName()+ "' and  CFDATE  between '"+d2+"' AND '"+d1+"'  ";
                       rs = st.executeQuery(qry);

                       while (rs.next()) {
                           billBean=new CformStatusByDateBillTableBean();
                           String invS = rs.getString(4);
                           inv = Double.parseDouble(invS) + inv;
                           billBean.setBillNo(rs.getString(1));
                           Date d=df.parse(df.format(rs.getDate(2)));
                           System.out.println(d);
                       
                           billBean.setBillDate(df.parse(df.format((rs.getDate(2)))));
                           billBean.setAcName(rs.getString(3));
                           billBean.setInvAmt(invS);
                           tdata1.add(billBean);
                       }
                   
                   rs.close();
                   st.close();
                   con.close();
tdata=tdata1;
               } catch (Exception e) {
                   e.printStackTrace();
               }
        return tdata;  
    } 
    public static List<CFormQuartersSubTableBean> subData(CFormStatusByQuartersBean cbd,CFormStatusByQuartersTableBean selectedCbt,String s){
        List<CFormQuartersSubTableBean>  tdata=new ArrayList<CFormQuartersSubTableBean>();
        List<CFormQuartersSubTableBean>  tdata1=new ArrayList<CFormQuartersSubTableBean>();
        CFormQuartersSubTableBean cqtb;
        st=Conn_Statement();
        String qry="";
        try {
            if(cbd.getPeriod().equalsIgnoreCase("All"))
            {
            qry = "SELECT BILLNO, CFDATE,ACNAME,INVVAL FROM CFORM WHERE ACNAME like '"+selectedCbt.getAccName()+"'";
            System.out.println(qry);
            }
            else
            {
            qry = "SELECT BILLNO, CFDATE,ACNAME,INVVAL FROM CFORM WHERE ACNAME like '"+selectedCbt.getAccName()+"' and  CFDATE  "+s;
                System.out.println(qry);
            }
            rs=st.executeQuery(qry);
            while(rs.next()){
        cqtb=new CFormQuartersSubTableBean();
            cqtb.setBillnum(rs.getString(1));
                cqtb.setBillDate(rs.getDate(2));
                cqtb.setAcName(rs.getString(3));
                cqtb.setInvAmt(rs.getString(4));
                tdata1.add(cqtb);
            }
            
            rs.close();
            st.close();
            con.close();
            
            tdata=tdata1;
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return tdata;
    } 
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    public static List<CFormSubmitDatesTableBean> cformSubmitData(CFormSubmitDatesBean cbd){
        List<CFormSubmitDatesTableBean>  tdata=new ArrayList<CFormSubmitDatesTableBean>(); 
        List<CFormSubmitDatesTableBean>  tdata1=new ArrayList<CFormSubmitDatesTableBean>(); 
        CFormSubmitDatesTableBean cbt;
        st=Conn_Statement();
        String d1, d2;
        d1 = df.format(cbd.getDate1());
        d2 = df.format(cbd.getDate2());
        try {

                   String qry = "SELECT cfbill,cfdate,period,cyear,cname,tin,cfsno,cfout,cfamt,status   FROM CFormSubmit  WHERE cfdate between '"+d2+"' AND '"+d1+"' ";
                   System.out.println("-------->" + qry);
                   rs = st.executeQuery(qry);
                   while (rs.next()) {
                     cbt=new CFormSubmitDatesTableBean();
                       cbt.setBill(rs.getString(1));
                       cbt.setCfdate(rs.getDate(2));
                       cbt.setPeriod(rs.getString(3) + ", " + rs.getString(4));
                       cbt.setName(rs.getString(5));
                       cbt.setTinno(rs.getString(6));
                       cbt.setSerialno(rs.getString(7));
                       cbt.setCfoutstanding(rs.getString(8));
                       cbt.setCfgiven(rs.getString(9));
                       cbt.setStatus(rs.getString(10));  
                       tdata1.add(cbt);
                   }
            
                   rs.close();
                   st.close();
                   con.close();
            
            tdata=tdata1;
               } catch (Exception e) {
                   e.printStackTrace();
               }   
  return tdata;  
    }
    public static List<CformStatusByDateItemBean> 
    itemData(CformStatusByDateBillTableBean selectedcib){
        List<CformStatusByDateItemBean>  tdata=new ArrayList<CformStatusByDateItemBean>();
        List<CformStatusByDateItemBean>  tdata1=new ArrayList<CformStatusByDateItemBean>();
      Double  inv = 0.0;
               Double cff = 0.0;
               String qry = "";
        st=Conn_Statement();
               try {
                   //FulRemoveRows();
               //    int ind = smlTable.getSelectedRow();
                   //System.out.println("Selected Row----------->" + ind);

             //      if (ind == -1) {

                    //   JOptionPane.showMessageDialog(this.smlTable, "Please Select a Row");
               //    } else {

                      // bil.setText(smmodel.getValueAt(ind, 0).toString());
                     //  billdate.setText(smmodel.getValueAt(ind, 1).toString());
                     //  fcname.setText(smmodel.getValueAt(ind, 2).toString());


                       qry = "SELECT `prodname`, `orirate`,`quantity`,`discamt`,NET,salestax   FROM  `Vat_Sales` WHERE  `Billno`=" + selectedcib.getBillNo();
System.out.println("dadcsvc"+qry);
                       //System.out.println("SEMI QRY\n____________________________________________________\n" + qry);

                       rs = st.executeQuery(qry);

                       while (rs.next()) {
                           CformStatusByDateItemBean tb=new CformStatusByDateItemBean();
                           String invS = rs.getString(5);
                           String cffS = rs.getString(6);
                           inv = Double.parseDouble(invS) + inv;
                           cff = Double.parseDouble(cffS) + cff;
                           tb.setINmae(rs.getString(1));
                           tb.setPrice(rs.getString(2));
                           tb.setQuantity(rs.getString(3));
                           tb.setDisc((rs.getString(4)));
                           tb.setNet(invS);
                           tb.setTax(cffS);
                           tdata1.add(tb);
                          //fmodel.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), invS, cffS});

                       }
                   rs.close();
                   st.close();
                   con.close();
tdata=tdata1;
                 //      fmodel.addRow(new Object[]{"", "", ""});
                  //     fmodel.addRow(new Object[]{"", "", "", "", inv, cff});

               //    }//else End
               } catch (Exception e) {
                   e.printStackTrace();
               }
    return tdata;
    }
    public static List<CFormQuartersItemTableBean> quarterItemData(CFormQuartersSubTableBean cqst){
    
        List<CFormQuartersItemTableBean>  tdata=new ArrayList<CFormQuartersItemTableBean>();
        List<CFormQuartersItemTableBean>  tdata1=new ArrayList<CFormQuartersItemTableBean>();
        CFormQuartersItemTableBean cqtb;
        st=Conn_Statement();
       String qry;
        try {
            qry = "SELECT prodname, orirate,quantity,discamt ,NET,salestax   FROM  Vat_Sales WHERE  Billno ="+cqst.getBillnum();
            rs=st.executeQuery(qry);
            while(rs.next()){
                cqtb=new CFormQuartersItemTableBean();
                cqtb.setINmae(rs.getString(1));
                cqtb.setPrice(rs.getString(2));
                cqtb.setQuantity(rs.getString(3));
                cqtb.setDisc(rs.getString(4));
                cqtb.setNet(rs.getString(5));
                cqtb.setTax(rs.getString(6));
                tdata1.add(cqtb);
            }
            
            rs.close();
            st.close();
            con.close();
            
tdata=tdata1;
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return tdata;
    } 
     
    public static List<SalesDateTableData> chartTableData(SalesChartbyRepwiseBean sd) {
        String amt;
        SalesDateTableData std;
        List<SalesDateTableData> tdata = new ArrayList<SalesDateTableData>();
        List<SalesDateTableData> data1 = new ArrayList<SalesDateTableData>();
        String net = null;
       ResultSet rs = null;
        Statement st = null;
        st = Conn_Statement();

        if (sd.getType() != null) {
            sd.setAmount(0.0);
            String qry = "";
            try {
                String ite = sd.getType();

                if (ite.equalsIgnoreCase("All")) {
                    ite = "";
                }
                String re = sd.getRepType();
                if (re.equalsIgnoreCase("All")) {
                    re = "";
                }

                String d1, d2;
                d1 = df.format(sd.getDate1());
                d2 = df.format(sd.getDate2());
                qry =
"SELECT distinct Billno, accno,acname,city,R_rep,invdate,invno,INVVALUE  From  Vat_Sales   WHERE   taxtype   like'%" +
  ite + "'  and invdate between '" + d2 + "' AND '" + d1 + "' and R_rep like '%" + re + "%'  ORDER  BY R_rep";
                rs = st.executeQuery(qry);

                while (rs.next()) {
                    // System.out.println(rs.getString(1));
                    std = new SalesDateTableData();
                    net = rs.getString(8);
                    sd.setAmount(Double.parseDouble(net) + sd.getAmount());
                    //amt = dfr.format(sd.getAmount());
                    amount = sd.getAmount();
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
                
                
                
                System.out.println("invvalue" + sd.getAmount());
                tdata = data1;
                st.close();
                rs.close();
                con.close();
            }

            catch (Exception e) {
                e.printStackTrace();

            }
        }
        return tdata;
    }
    public static List<ChequeReturnsTableBean> chequeRetData( ChequeReturnsBean sd) {
        List<ChequeReturnsTableBean> tdata = new ArrayList<ChequeReturnsTableBean>();
        List<ChequeReturnsTableBean> tdata1= new ArrayList<ChequeReturnsTableBean>();
        ChequeReturnsTableBean crtb;
        ResultSet rs = null;
         Statement st = null;
         st = Conn_Statement();
        String d1, d2;
        d1 = df.format(sd.getDate1());
        d2 = df.format(sd.getDate2());
               try {

                   String qry = "SELECT * FROM ChqReturns WHERE retdate    between '"+d2+"' AND '"+d1+"' ORDER BY rid,rep,chqno";
                   System.out.println("-------->" + qry);
                   rs = st.executeQuery(qry);
                   while (rs.next()) {
                   crtb= new   ChequeReturnsTableBean();
                       Double gd =0.0;
                       gd = rs.getDouble(13);
                       crtb.setSno(rs.getInt(2));
                       crtb.setBillno(rs.getString(12));
                       crtb.setPname(rs.getString(4));
                       crtb.setCity(rs.getString(16));       
                       crtb.setRepresent(rs.getString(14));
                       crtb.setChqdate(rs.getDate(6));
                       crtb.setChqno(rs.getString(7));
                       crtb.setAmount(rs.getDouble(8));
                       crtb.setBnkcharge(rs.getDouble(9));
                       crtb.setRetdate(rs.getDate(10));
                       crtb.setNtamt(gd);
                       tdata1.add(crtb);
                     //  model.addRow(new Object[]{rs.getString(1),rs.getString(11), rs.getString(3)  ,rs.getString(15), rs.getString(13) , df.format(rs.getDate(5)), rs.getString(6), rs.getString(7), rs.getString(8), df.format(rs.getDate(9)), gd});
                   }
                   rs.close();
                   st.close();
                   con.close();
                   
              tdata=tdata1;
               } catch (Exception e) {
                   e.printStackTrace();
               }

        return tdata;
    }
   
    public static List<RouteDataTableBean> routeData( RouteDataBean sd) {
        List<RouteDataTableBean> tdata = new ArrayList<RouteDataTableBean>();
        List<RouteDataTableBean> tdata1 = new ArrayList<RouteDataTableBean>();
        RouteDataTableBean rdtb;
        ResultSet rs = null;
         Statement st = null;
         st = Conn_Statement();
      
     String qry="";
            try {
              
                if(sd.getRoutename().equalsIgnoreCase("All"))
                {
              qry = "SELECT   *   FROM  `RouteMaster` WHERE TOURNAME like '%%'  ORDER  BY  REPNAME,ROUTEID";
                }
              else
                {
              qry = "SELECT *   FROM  RouteMaster  WHERE TOURNAME like '%"+sd.getRoutename()+"%'  ORDER  BY  REPNAME,ROUTEID";
                }
              
                System.out.println("Get Data Query----------------------->"+qry);
                
                rs = st.executeQuery(qry); 
                while(rs.next())
                {
                rdtb=new RouteDataTableBean();
                  rdtb.setRno(rs.getInt(2));
                    rdtb.setTour(rs.getString(3));
                    rdtb.setCity(rs.getString(4));
                    rdtb.setRepname(rs.getString(5));
                    rdtb.setContactno(rs.getString(8));
                    rdtb.setAddress(rs.getString(6));
                    tdata1.add(rdtb);
                    
           //     model.addRow(new Object[]{   rs.getString(3),rs.getString(5),rs.getString(2),rs.getString(1),rs.getString(4),rs.getString(6)});
                
                }
                
                rs.close();
                st.close();
                con.close();
                
                tdata=tdata1;  
            } catch (Exception e) {
                e.printStackTrace();
            }  
        return tdata;
    }
        
    public static List< YearlySalesTableBean> yearlyData( YearlySalesBean ysb) {
        YearlySalesTableBean ystb;
        List<YearlySalesTableBean>tdata=new ArrayList<YearlySalesTableBean>();
        List<YearlySalesTableBean>tdata1=new ArrayList<YearlySalesTableBean>();
        String net = null;
        ResultSet rs = null;
        Statement st = null;
        st = Conn_Statement();
        if(ysb.getTaxType()!=null){
              // amount = 0.0;
               String qry = "";
               try {
        
                   String ite = ysb.getTaxType();
                   if (ite.equalsIgnoreCase("All")) {
                       ite = "";
                   }
                   String cmp = ysb.getPartyType();

                   if (cmp.equalsIgnoreCase("All")) {
                       cmp = "";
                   }
                   String d1, d2;
                   d1 = df.format(ysb.getDate1());
                   d2 = df.format(ysb.getDate2());
                   qry = "SELECT distinct Billno, accno,acname,city,R_rep,invdate,invno,INVVALUE  From  Vat_Sales   WHERE   taxtype   like'%" + ite + "'  and  invdate between '"+d2+"' AND '"+d1+"' and acname like '%"+cmp+"%'  ORDER BY  R_rep";
                   System.out.println("Get Data Query----------------------->" + qry);
                   rs = st.executeQuery(qry);
                   while (rs.next()) {
                       ystb=new YearlySalesTableBean(); 
                     net = rs.getString(8);
                      // amount = Double.parseDouble(net) + amount;
                   ystb.setBillno(rs.getString(1));
                       ystb.setAcno(rs.getString(2));
                       ystb.setAcname(rs.getString(3));
                       ystb.setCity(rs.getString(4));
                       ystb.setRep(rs.getString(5));
                       ystb.setInvdate(rs.getDate(6));
                       ystb.setInvno(rs.getString(7));
                       ystb.setInvamt(Double.parseDouble(net));
                       tdata1.add(ystb);
                      // model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), df.format(rs.getDate(6)), rs.getString(7), net});
                   }
                   
                   rs.close();
                   st.close();
                   con.close();
                   
               tdata=tdata1;
             
             //      model.addRow(new Object[]{"", "", "TOTAL INVOICE AMOUNT", "", "", "----------", "---------->", dfr.format(amount) + ""});

               } catch (Exception e) {
                   e.printStackTrace();
               }
           }     
   return tdata;     
    }

    public static List<SalesDateTableData> repWiseTableData(SalesRepWiseBean srb) {
        SalesDateTableData std;
        List<SalesDateTableData> tdata = new ArrayList<SalesDateTableData>();
        List<SalesDateTableData> data1 = new ArrayList<SalesDateTableData>();
        String net = null;
        ResultSet rs = null;
        Statement st = null;
        st = Conn_Statement();


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
                    //amt = dfr.format(srb.getAmount());
                    amount = srb.getAmount();
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
                
                rs.close();
                st.close();
                con.close();
                
                tdata = data1;
                st.close();
                rs.close();
                //con.close();
            }

            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tdata;
    }
    public static List<SalesMonthlyReportTableBean> MonthlyTableData(SalesMonthlyReportBean smb){
        //List<SalesMonthlyReportTableBean> tdata=new ArrayList<SalesMonthlyReportTableBean>();
          Double  pak, spl, aut, run, inv,mix=0.0,stax,pos,oto,otr,ron,amount,gros;
          ron=0.0;
         int sno=1;
    List<SalesMonthlyReportTableBean> tdata=new ArrayList<SalesMonthlyReportTableBean>();
        List<SalesMonthlyReportTableBean> tdata1=new ArrayList<SalesMonthlyReportTableBean>();
        ResultSet rs=null;
        java.sql.Statement st=null;
        st = Conn_Statement();
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
                    
                    SalesMonthlyReportTableBean   smtb1=new SalesMonthlyReportTableBean();
                    smtb1.setNet(gros.toString());
                    smtb1.setSalestax(stax.toString());
                    smtb1.setPostage(pos.toString());
                    smtb1.setMixvalue(oto);
                    smtb1.setOthers(otr.toString());
                    smtb1.setRoundof(ron.toString());
                    smtb1.setInvvalue(amount.toString());
                    tdata1.add(smtb1);
                    
                    
                    rs.close();
                    st.close();
                    con.close();
                    
                   tdata=tdata1; 
                } catch (Exception e) {
                    e.printStackTrace();
                }
               }
    return tdata;
    }

    public static PieChartModel createPieModel(SalesChartbyRepwiseBean sd) {
        PieChartModel pieModel = new PieChartModel();

     
        st =Conn_Statement();
        List ls = new ArrayList();
        int j = -1, x = -1;

        if (sd.getType() != null && sd.getRepType() != null) {
            try {
                String ite = sd.getType();
                if (ite.equalsIgnoreCase("All")) {
                    ite = "";
                }
                String re = sd.getRepType();
                if (re.equalsIgnoreCase("All")) {
                    re = "";
                }
                String d1, d2;
                d1 = df.format(sd.getDate1());
                d2 = df.format(sd.getDate2());
                String qry =
                    "select R_rep,sum(pp) as number_distinct_Billnos from (select R_rep, Billno, 1 as pp from Vat_Sales     WHERE   taxtype   like'%" +
                    ite + "'  and  invdate between  '" + d2 + "' AND '" + d1 + "' and R_rep like '%" + re +
                    "%'   group by R_rep, Billno) t    group by R_rep";
                System.out.println("the query is-------------------" + qry);
                rs = st.executeQuery(qry);
                while (rs.next()) {
                    ++j;
                    mp.put(j, rs.getString(1));
                    System.out.println(rs.getString(1));
                    String pms = rs.getString(1) + "[" + rs.getDouble(2) + " sales" + "]";
                    pieModel.set(pms, rs.getDouble(2));


                }

                System.out.println(mp);
                st.close();
                rs.close();
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return pieModel;
    }
    public static PieChartModel DataCharta(SalesItemChartBean sd ) {
        PieChartModel pieModel = new PieChartModel();
        st =Conn_Statement();
        String d1, d2;
        d1 = df.format(sd.getDate1());
        d2 = df.format(sd.getDate2());
        if (sd.getStype() != null && sd.getRoute() != null && sd.getRepresentative() != null && sd.getIgroup()!= null) {
                     // amount = 0.0;

                      try {

                          String ite = sd.getStype();

                          if (ite.equalsIgnoreCase("All")) {
                              ite = "";
                          }
String route=sd.getRoute();
        if (route.equalsIgnoreCase("All"))
        {
          route = "%";
                          }
                          String rep=sd.getRepresentative();
        if(rep.equalsIgnoreCase("All"))
        {
          rep = "%";
                          }
        String item=sd.getIgroup();
          if (item.equalsIgnoreCase("All"))
        {
          item = "%";
                          }

       String  ig_chr = item;
                          String qry = "SELECT  sum(quantity), MTCat  FROM Vat_Sales   WHERE taxtype  like'%" + ite + "%' and  invdate between '"+d2+"'AND '"+d1+"' and route  like '%" + route + "%' and `R_rep` like '%" + rep + "%' and MTCat  like '%" + item + "%'      GROUP BY MTCat";
               System.out.println("--->Chart qry1" + qry);

                          //******************************************************************************************** 
                           rs = st.executeQuery(qry);


                          while (rs.next()) {
                              String pms = rs.getString(2) + "[" + rs.getDouble(1) + " Items" + "]";

                              pieModel.set(pms, rs.getDouble(1));

                          }
                          
                          rs.close();
                          st.close();
                          con.close();

                      } catch (Exception e) {
                          e.printStackTrace();
                      }

                  }
        
        
        return pieModel;
    }
    public static PieChartModel dataChartc(SalesItemChartBean sd,String icat){
        PieChartModel pieModel = new PieChartModel();
        st =Conn_Statement();
        String d1, d2;
        d1 = df.format(sd.getDate1());
        d2 = df.format(sd.getDate2());
        if (sd.getStype() != null && sd.getRoute() != null && sd.getRepresentative() != null && sd.getIgroup()!= null) {
                     // amount = 0.0;

                      try {

                          String ite = sd.getStype();

                          if (ite.equalsIgnoreCase("All")) {
                              ite = "";
                          }
        String route=sd.getRoute();
        if (route.equalsIgnoreCase("All"))
        {
          route = "%";
                          }
                          String rep=sd.getRepresentative();
        if(rep.equalsIgnoreCase("All"))
        {
          rep = "%";
                          }
        String item=sd.getIgroup();
          if (item.equalsIgnoreCase("All"))
        {
          item = "%";
                          }

        String  ig_chr = item;
                          String qry = "SELECT  sum(quantity), subgrp  FROM Vat_Sales   WHERE taxtype  like'%" + ite + "%' and  invdate between '"+d2+"'AND '"+d1+"' and route  like '%" + route + "%' and R_rep like '%" + rep + "%' and icat  like '%" + icat + "%'      GROUP BY icat,subgrp";
                    System.out.println("--->Chart qry3:" + qry);


                          //******************************************************************************************** 
                         rs = st.executeQuery(qry);
                          while (rs.next()) {
                              String pms = rs.getString(2) + "[" + rs.getDouble(1) + " Items" + "]";

                              pieModel.set(pms, rs.getDouble(1));

                          }
                          
                          rs.close();
                          st.close();
                          con.close();

                      } catch (Exception e) {
                          e.printStackTrace();
                      }

                  }
        return pieModel;
    }   
    
    
    
    public static List<SalesItemChartSubTableBean> subTableData( SalesItemChartBean sd,String s) {
        List<SalesItemChartSubTableBean> tdata = new ArrayList<SalesItemChartSubTableBean>();
        List<SalesItemChartSubTableBean> tdata1 = new ArrayList<SalesItemChartSubTableBean>();
        SalesItemChartSubTableBean sitb;
        st =Conn_Statement();
        String d1, d2;
        d1 = df.format(sd.getDate1());
        d2 = df.format(sd.getDate2());
        if (sd.getStype()!= null) {
                     // amount = 0.0;
                     // quntity2 = 0.0;

                      try {
                      
                          String ite = sd.getStype();

                          if (ite.equalsIgnoreCase("All")) {
                              ite = "";
                          }

                          String route=sd.getRoute();
                          if (route.equalsIgnoreCase("All"))
                          {
                            route = "%";
                                            }
                                            String rep=sd.getRepresentative();
                          if(rep.equalsIgnoreCase("All"))
                          {
                            rep = "%";
                                            }
                          String item=sd.getIgroup();
                            if (item.equalsIgnoreCase("All"))
                          {
                            item = "%";
                                            }


                          String qry = "SELECT  acname,city,prodname ,sum(quantity),netvalue,R_rep   FROM Vat_Sales  WHERE taxtype  like'%" + ite + "%' and  invdate  between '"+d2+"' AND '"+d1+"' and route  like '%" + route + "%' and R_rep like '%" + rep + "%' and  aproduct  like '%" +s + "%'  GROUP BY acname,city,prodname ,R_rep,netvalue";
                          //                                                                     1         2                            3                  4            5                  6               7
                          //SELECT  `acname`,`city`,`prodname`,sum(`quantity`),`netvalue`,`R_rep`  FROM `Vat_Sales`   WHERE taxtype  like'%%' and  `invdate` between format('23-Jul-13', 'dd-MMM-yy') AND format('01-Apr-13','dd-MMM-yy') and route  like '%%' and `R_rep` like '%%' and icat like '%WHEEL ROD%'      GROUP BY `acname`,`city`,`prodname`,`R_rep`,`netvalue` 

                          System.out.println("Get Data Query----------------------->4" + qry);

                           rs =st.executeQuery(qry);

                          int sno = 0;
                          while (rs.next()) {
                              sitb=new SalesItemChartSubTableBean();
                              sno++;
                              String  qnt = rs.getString(4);
                              String net = rs.getString(5);
sitb.setSno(sno);
                              sitb.setPname(rs.getString(1));
                              sitb.setCity(rs.getString(2));
                              sitb.setProduct(rs.getString(3));
                              sitb.setQuantity(rs.getDouble(4));
                              sitb.setNet(rs.getDouble(5));
                              sitb.setRep(rs.getString(6));
                           ///   amount = Double.parseDouble(net) + amount;
                            //  quntity2 = Double.parseDouble(qnt) +quntity2;
tdata1.add(sitb);

                           
                          }
                          rs.close();
                          st.close();
                          con.close();
                       
tdata=tdata1;


                      } catch (Exception e) {
                          e.printStackTrace();
                      }
   
    }
    
        return tdata;
    }
    
    
    
    
    public static PieChartModel dataChartb(SalesItemChartBean sd,String icat){
        PieChartModel pieModel = new PieChartModel();
        st =Conn_Statement();
        String d1, d2;
        d1 = df.format(sd.getDate1());
        d2 = df.format(sd.getDate2());
        if (sd.getStype() != null && sd.getRoute() != null && sd.getRepresentative() != null && sd.getIgroup()!= null) {
        try {
            String ite = sd.getStype();

            if (ite.equalsIgnoreCase("All")) {
                ite = "";
            }
            String route=sd.getRoute();
            if (route.equalsIgnoreCase("All"))
            {
            route = "%";
            }
            String rep=sd.getRepresentative();
            if(rep.equalsIgnoreCase("All"))
            {
            rep = "%";
            }
            String item=sd.getIgroup();
            if (item.equalsIgnoreCase("All"))
            {
            item = "%";
            }
            String qry = "SELECT  sum(quantity), icat  FROM Vat_Sales   WHERE taxtype  like'%" + ite + "%' and  invdate between '"+d2+"' AND '"+d1+"' and route  like '%" + route + "%' and  R_rep like '%" + rep + "%' and MTCat  like '%" + icat + "%'      GROUP BY MTCat,icat";
           rs=st.executeQuery(qry);
            while(rs.next()){
                if(rs.getString(2)!=null){
                String pms = rs.getString(2) + "[" + rs.getDouble(1) + " Items" + "]";

                pieModel.set(pms, rs.getDouble(1));
                }
            }
            
            rs.close();
            st.close();
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
        return pieModel;
    }
     
    public static List taxdata() {

        st = Conn_Statement();
        List taxList = new ArrayList();
        taxList.clear();
        taxList.add("All");
        try {
            String qry = "SELECT taxname FROM Tax";
            rs = st.executeQuery(qry);
            while (rs.next()) {
                if(rs.getString(1)!=null){
                taxList.add(rs.getString(1));
                }
            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taxList;
    }
    public static List itemData(){
        st = Conn_Statement();
        String qry="";
        List itemList=new ArrayList();
        itemList.clear();
        itemList.add("All");
        try{
      qry="select distinct CATEGORY from ItemMaster";
            rs=st.executeQuery(qry);
            while(rs.next()){
                itemList.add(rs.getString(1));           
                }
            
            rs.close();
            st.close();
            con.close();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return itemList; 
    }





    public static List addRepresents() {
        List rapresentData = new ArrayList();
        List representativeList1 = new ArrayList();
        ResultSet rs = null;
        Statement st = null;
        st = Conn_Statement();
       rapresentData.clear();
        rapresentData.add("All");
        try {
            String qry = "SELECT distinct(REPNAME)  FROM RouteMaster";
            rs = st.executeQuery(qry);
            while (rs.next()) {

                rapresentData.add(rs.getString(1).toString());
                representativeList1.add(rs.getString(1).toString());
            }
            st.close();
            rs.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rapresentData;
    }
    
   
    public static List addRoutes() {
        List routData = new ArrayList();
        ResultSet rs;
          Statement st;
          st = Conn_Statement();
        try {
            String qry = "SELECT DISTINCT ROUTEID  FROM RouteMaster";
            rs = st.executeQuery(qry);
           routData.clear(); 
           routData.add("All");
            while (rs.next()) {
           routData.add(rs.getString(1));
            }
            st.close();
            rs.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    return routData;
    }

    
    public static List addRoutes1() {
        List routData = new ArrayList();
        ResultSet rs;
          Statement st;
          st = Conn_Statement();
        try {
            String qry = "SELECT distinct(TOURNAME)  FROM RouteMaster";
            rs = st.executeQuery(qry);
           routData.clear(); 
           routData.add("All");
            while (rs.next()) {
           routData.add(rs.getString(1));
            }
            st.close();
            rs.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    return routData;
    }
    
    public static List addItems() {
           List items = new ArrayList();
           ResultSet rs;
             Statement st;
             st = Conn_Statement();
           try {
             
               String qry = "SELECT distinct(itemname)  FROM ItemMaster";
               rs=st.executeQuery(qry);
               items.clear();
              items.add("All");
               while (rs.next()) {      
                   String  i = rs.getString(1);
                   if(i!=null)
                   {
                   items.add(i.trim());
                   }
               }
               rs.close();
               st.close();
               con.close();

           } catch (Exception e) {
               e.printStackTrace();
           }
    return items;
       }
    public static List addparties() {
            List party = new ArrayList();
            ResultSet rs;
              Statement st;
              st = Conn_Statement();
            try {

                String qry = "SELECT distinct(ACNAME)  FROM  cform";

                rs = st.executeQuery(qry);
party.clear();
                party.add("All");
                while (rs.next()) {
                    party.add(rs.getString(1));

                }
                
                rs.close();
                st.close();
                con.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            return party;
        }
    public static List AddItemGroup() {
            List itemGroup = new ArrayList();
            ResultSet rs;
              Statement st;
              st = Conn_Statement();
            try {
               
                String qry = "SELECT distinct(CATEGORY) FROM ItemMaster";
                itemGroup.clear();
                itemGroup.add("All");
                rs=st.executeQuery(qry);
                while (rs.next()) {
                   
               itemGroup.add(rs.getString(1));
                    
                }
                rs.close();
                st.close();
                con.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return itemGroup;
        }
    public static List RouteCombo(ReportByProductWise rpw)
        {     
        List places=new ArrayList();
            ResultSet rs;
              Statement st;
              st =Conn_Statement();
                String qry = "";
            if(rpw.getRoute()!=null && rpw.getReType()!=null){
            try {
                String sel =rpw.getRoute();
                String rsel = rpw.getReType();
                places.clear();
               places.add("All");   
               if(sel.equalsIgnoreCase("All"))
               {  
                    if(rsel.equalsIgnoreCase("All"))
                    {            
                    rsel = "%";
                    }
                    
          qry = "SELECT AREANAME  FROM RouteMaster  WHERE  REPNAME like  '%"+rsel+"%'   ORDER BY ROUTEID";
              }
               else{
                  if(rsel.equalsIgnoreCase("All"))
                  {
                    
                    rsel = "%";
                    }
                 qry = "SELECT AREANAME  FROM RouteMaster WHERE ROUTEID = '"+sel+"' and REPNAME like  '%"+rsel+"%'   ORDER BY ROUTEID";
               }
                System.out.println(""+qry);
                 rs = st.executeQuery(qry);
                 while(rs.next())
                 {
                 places.add(rs.getString(1));
                 } 
                
                rs.close();
                st.close();
                con.close();        
            } catch (Exception e) {
                e.printStackTrace();
            }   
            
        }
        return places;
        }
    public static List SalesInvoiceRouteCombo(SalesInvoiceBean rpw)
        {     
            ResultSet rs;
              Statement st;
              st =Conn_Statement();
              List places=new ArrayList();
            String qry = "";
                  if(rpw.getRoute()!=null && rpw.getReType()!=null){
                  try {
                      String sel = rpw.getRoute();
                      String rsel = rpw.getReType();
                      places.clear();
                      places.add("All");
                      
                      if(sel.equalsIgnoreCase("All"))
                      {
                      
                          if(rsel.equalsIgnoreCase("All"))
                          {
                          
                          rsel = "%";
                          }
                          
                     qry = "SELECT  AREANAME,ROUTEID,  TOURNAME  FROM RouteMaster  WHERE  REPNAME like  '%"+rsel+"%'   ORDER BY ROUTEID";
                      }
                      else{
                            if(rsel.equalsIgnoreCase("All"))
                          {
                          
                          rsel = "%";
                          }
                       qry = "SELECT  AREANAME,ROUTEID,  TOURNAME  FROM RouteMaster WHERE ROUTEID = "+sel+" and REPNAME like  '%"+rsel+"%'   ORDER BY ROUTEID";
                          System.out.println(qry);
                      }
                      //System.out.println(""+qry);
                  
                       rs = st.executeQuery(qry);
                       places.clear();
                      places.add("All");
                     
                       while(rs.next())
                       {
                       places.add(rs.getString(1));    
                       }
                rs.close();
                st.close();
                con.close();
            
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
                
              }
                  return places;
        }
    public static void ivoiceCurrPdfData( List<SalesInvoiceTableBean>tdata){
        try {
       
            ResultSet rs=null;
            Statement st=null;
            st=Conn_Statement();   
                   int cnt =tdata.size();
                   int i=0; 
                   st.executeUpdate("DELETE  FROM CurrPdf");
                   while(i<cnt)
                   {
                         String a0 = tdata.get(i).getPartyname();
                       String a1 = tdata.get(i).getCity();
                       String a2 = tdata.get(i).getRepresent();
                       String a3 =(tdata.get(i).getInvdate()).toString();
                       String a4 = tdata.get(i).getInvno();
                       String a5 =tdata.get(i).getInvvalue();
                       if(a0==null)
                       {
                       a0 = ".";
                       
                       }
                         if(a1==null)
                       {
                       a1 = ".";
                       
                       }
                         if(a2==null)
                       {
                       a2 = ".";
                       
                       }
                         if(a3==null)
                       {
                       a3 = ".";
                       
                       }
                         if(a4==null)
                       {
                       a4 = ".";
                       
                       }
                         if(a5==null)
                       {
                       a5 = "0.0";
                       
                       }
                        
                         a3 = a3+"     "+a4;
             String qry = "INSERT INTO CurrPdf  VALUES   ('"+a0+"' ,   '"+a1+"' ,   '"+a2+"',   '"+a3+"' ,  "+a5+")"
                     + "";//System.out.println(""+qry);
            int res = st.executeUpdate(qry);
            if(res>0)
            {
                i++;
                //System.out.println("Inserting   :   "+i);
            }
                                
                   } 
        rs.close();
        st.close();
        con.close();
            
    } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<SalesInvoiceTableBean> InvoiceData(SalesInvoiceBean rpw) {
           SalesInvoiceTableBean stb;
           List<SalesInvoiceTableBean>tdata=new ArrayList<SalesInvoiceTableBean>();
           List<SalesInvoiceTableBean>tdata1=new ArrayList<SalesInvoiceTableBean>();
           ResultSet rs;
             Statement st;
             st = Conn_Statement();
           String qry= " ";
           if(rpw.getSalesType()!=null&& rpw.getReType()!=null&& rpw.getRoute()!=null&& rpw.getPlace()!=null  ){
          Double  amount = 0.0,qty = 0.0;

           try {
               tdata.clear();
         String stype= "", repre= "", rotid= "",city= "",item= "",grop= "",subgrop= "";
         
         stype =  rpw.getSalesType();
         if(stype.equalsIgnoreCase("All"))
         {
         stype = "%";
         }
         
         repre =  rpw.getReType();
          if(repre.equalsIgnoreCase("All"))
         {
         repre = "%";
         }
    
         city = rpw.getPlace();
          if(city.equalsIgnoreCase("All"))
         {
         city = "%";
         }
               String d1, d2;
               d1 = df.format(rpw.getDate1());
               d2 = df.format(rpw.getDate2());
     rotid = rpw.getRoute();
          if(rotid.equalsIgnoreCase("All"))
         {
         qry = "SELECT acname ,city,R_rep,invdate,invno,invvalue  FROM Vat_Sales WHERE  taxtype LIKE '%"+stype+"%'   AND   invdate between '"+d2+"' AND '"+d1+"' AND  R_rep LIKE '%"+repre+"%'  AND   city LIKE  '%"+city+"%'   GROUP by  acname ,city,R_rep,invdate,accno,invno,invvalue order BY  R_rep";
        
         }else
          
          {
       qry = "SELECT acname, city,R_rep,invdate,invno,invvalue   FROM Vat_Sales WHERE  taxtype  LIKE '%"+stype+"%'   AND   invdate between '"+d2+"' AND  '"+d1+"' AND R_rep LIKE '%"+repre+"%'  AND  R_id = "+rotid+"   AND  city LIKE  '%"+city+"%'    GROUP by  acname, city,R_rep,invdate,accno,invno,invvalue order BY    R_rep";
              System.out.println("qey1"+qry);
          }
               //System.out.println("Get Data Query----------------------->" + qry);

               rs = st.executeQuery(qry);
              

               while (rs.next()) {
                   stb=new SalesInvoiceTableBean();
    String acnt = rs.getString(1);
                   String net = rs.getString(6);
                   amount = Double.parseDouble(net) + amount;
                   stb.setPartyname(acnt);
                  stb.setCity(rs.getString(2));
                   stb.setRepresent(rs.getString(3));
                   stb.setInvdate(rs.getDate(4));
                   stb.setInvno(rs.getString(5));
                   stb.setInvvalue(net);
tdata1.add(stb);
                   
                  // model.addRow(new Object[]{acnt,  rs.getString(2), rs.getString(3), df.format(rs.getDate(4)),  rs.getString(5),  net});
               }
               
               rs.close();
               st.close();
               con.close();
             
           
tdata=tdata1;


           } catch (Exception e) {
               e.printStackTrace();
           }

       }
           return tdata;
       }
    public static List<ProductWiseTableData> GetData(ReportByProductWise rpw) {
            List<ProductWiseTableData>tdata1=new ArrayList<ProductWiseTableData>();
          
              st = Conn_Statement();
            String qry= " ";
            String d1, d2;
            d1 = df.format(rpw.getDate1());
            d2 = df.format(rpw.getDate2());
            if(rpw.getSalesType()!=null&& rpw.getReType()!=null&& rpw.getRoute()!=null&& rpw.getPlace()!=null&& rpw.getItem()!=null&& rpw.getGroup()!=null && rpw.getSubGroup()!=null ){
           Double  amount = 0.0,qty = 0.0;
            try {
            
          String stype= "", repre= "", rotid= "",city= "",item= "",grop= "",subgrop= "";
          
          stype =  rpw.getSalesType();
          if(stype.equalsIgnoreCase("All"))
          {
          stype = "%";
          }  
          repre =  rpw.getReType();
           if(repre.equalsIgnoreCase("All"))
          {
          repre = "%";
          }   
          city = rpw.getPlace();
           if(city.equalsIgnoreCase("All"))
          {
          city = "%";
          }
          
          
           item =rpw.getItem();
            if(item.equalsIgnoreCase("All"))
          {
          item = "%";
          }   
           grop = rpw.getGroup();
            if(grop.equalsIgnoreCase("All"))
          {
          grop = "%";
          }    
          subgrop =  rpw.getSubGroup();
           if(subgrop.equalsIgnoreCase("All"))
          {
          subgrop = "%";
          }
      rotid = rpw.getRoute();
           if(rotid.equalsIgnoreCase("All"))
          {
           
          qry = "SELECT   acname,city,R_rep,prodname,subgrp,icat,invvalue,SUM(quantity)   FROM Vat_Sales  WHERE taxtype  like  '%" + stype + "%' and  invdate between '"+d2+"' and '"+d1+"' and city like '%" + city + "%' and R_rep like '%" + repre + "%' and icat like '%" + grop + "%'      and  prodname like '%" + item + "%' and  subgrp like '%" + subgrop + "%' GROUP BY   acname,city,R_rep, prodname,subgrp,icat,invvalue order by  R_rep";
               
          }else
           
           {
        qry = "SELECT   acname,city,R_rep, prodname,subgrp,icat,invvalue,SUM(quantity)   FROM Vat_Sales  WHERE taxtype  like  '%" + stype + "%' and  invdate between '" +d2 + "' AND '"+d1+"' and city like '%" + city + "%'  and  R_rep like '%" + repre + "%' and  icat like '%" + grop + "%'      and  R_id= '"+rotid+"' and prodname like '%" + item + "%' and  subgrp like '%" + subgrop + "%'  GROUP BY   acname,city,R_rep, prodname,subgrp,icat,invvalue    order by  R_rep";
           
           }
                System.out.println("Get Data Query----------------------->" + qry);

                rs = st.executeQuery(qry);
                while (rs.next()) {
                    ProductWiseTableData ptdata=new ProductWiseTableData();
                    String net = rs.getString(7);
                     String qtt = rs.getString(8);
                    amount = Double.parseDouble(net) + amount;
                      qty = Double.parseDouble(qtt) +qty;
                    ptdata.setPartyName(rs.getString(1));
                    ptdata.setCity(rs.getString(2));
                    ptdata.setRepresents(rs.getString(3));
                    ptdata.setItemName(rs.getString(4));
                    ptdata.setItemSubgroup(rs.getString(5));
                    ptdata.setItemGroup(rs.getString(6));
                    ptdata.setQuantity(qtt);
                    ptdata.setInvAmount(net);
                    tdata1.add(ptdata);
                }
               
                rs.close();
                st.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
            return tdata1;
        }



    public static List<SalesItemChartTableBean> saleschartData(SalesItemChartBean rpw) {
        List<SalesItemChartTableBean>tdata=new ArrayList<SalesItemChartTableBean>();
        List<SalesItemChartTableBean>tdata1=new ArrayList<SalesItemChartTableBean>();
        SalesItemChartTableBean sictb;
        st = Conn_Statement();
        String qry= " ";
        String d1, d2;
        d1 = df.format(rpw.getDate1());
        d2 = df.format(rpw.getDate2());
        try{
          String stax="",rep="",route="",igr="";
          stax=rpw.getStype();
            if(stax.equalsIgnoreCase("All")){
                stax="%";
            }
           
            rep=rpw.getRepresentative();
              if(rep.equalsIgnoreCase("All")){
                  rep="%";
              }

            route=rpw.getRoute();
              if(route.equalsIgnoreCase("All")){
                  route="%";
              }
         
            igr=rpw.getIgroup();
              if(igr.equalsIgnoreCase("All")){
                  igr="%";
              }
            
         qry = "SELECT  distinct invno, invdate, acname, city, R_rep, icat, invvalue FROM Vat_Sales  WHERE taxtype  like'%" + stax+ "%' and  `invdate` between '"+d2+"' AND  '"+d1+"'  and route  like '%" + route + "%' and R_rep  like '%" + rep + "%' and MTCat like '%" + igr + "%'";
          rs=st.executeQuery(qry);
            while(rs.next()){
               sictb=new SalesItemChartTableBean();
                sictb.setInvno(rs.getString(1));
                sictb.setInvdate(rs.getDate(2));
                sictb.setPname(rs.getString(3));
                sictb.setCity(rs.getString(4));
                sictb.setRep(rs.getString(5));
                sictb.setIcat(rs.getString(6));
                sictb.setInvamt(rs.getDouble(7));
                tdata1.add(sictb);        
            }
            rs.close();
            st.close();
            con.close();
         tdata=tdata1;     
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return tdata;
    } 


    public static void main(String[] args) {

        //   DBConnection ob = new DBConnection();


    }


    public static void setAmount(double amount) {
        DBConnection.amount = amount;
    }

    public static double getAmount() {
        return amount;
    }

    public static void setMp(Map<Integer, String> mp) {
        DBConnection.mp = mp;
    }

    public static Map<Integer, String> getMp() {
        return mp;
    }
    
    
    
    public static List<CFormOutstandingSummaryTableBean> cosData(CFormOutstandingSummaryBean cbd){
            List<CFormOutstandingSummaryTableBean>  tdata=new ArrayList<CFormOutstandingSummaryTableBean>();
            List<CFormOutstandingSummaryTableBean>  tdata1=new ArrayList<CFormOutstandingSummaryTableBean>();
            CFormOutstandingSummaryTableBean cbdt;
            String qry = "",qry1="",qry2="",qry3="",qry4="";
            Double I=0.0,II=0.0,III=0.0,IV=0.0,total=0.0,amt=0.0;
            int k;
            st=Conn_Statement();
           
            
            if (cbd.getRep() != null) {
               
                
               
                    String rep = cbd.getRep();
                    String yr=cbd.getYear();

                        if(rep.equalsIgnoreCase("ALL")){
                            rep="";
                            
                        }
                
            
            try {
                
                
                     
                
            
            System.out.println(rep+"----------"+yr+"-----"+cbd);
                      String qra = "SELECT CFDATE,BILLNO,INVNO,ACNAME,INVVAL,REP FROM CFORM WHERE REP LIKE '%" + rep + "%' AND    CFDATE  Between '"+yr+"-04-01"+"' and '"+yr+"-06-30"+"'" ;
                      System.out.println(""+qra);
                      st=DBConnection.Conn_Statement();
                      System.out.println("statement is created");
                      rs = st.executeQuery(qra);
                      //System.out.println("ResultSet  1---------->    "+rs);
                      k = 1;
                      
                      cbdt=new CFormOutstandingSummaryTableBean();
                      while (rs.next()) {
                            cbdt=new CFormOutstandingSummaryTableBean();
                      
                         
                         Date dt = rs.getDate(1);
                         String bno=rs.getString(2);
                         String ino=rs.getString(3);
                         String name=rs.getString(4);
                          
                         //cbd.setAmount(rs.getDouble(5) + cbd.getAmount());
                         //amount=cbd.getAmount();
                        
                         String re=rs.getString(6);
                         
                         cbdt.setK(k);
                         
                         cbdt.setRep(name); 
                         cbdt.setQta(rs.getDouble(5));
                          total=cbdt.getQta()+cbdt.getQtb()+cbdt.getQtc()+cbdt.getQtd();
                          cbdt.setTotal(total);
                         
                             tdata1.add(cbdt);
                            
                         k++;
                       
                      }
                      
                         
                         qry1 = "SELECT SUM(INVVAL) FROM CFORM WHERE REP LIKE '%" + rep + "%' AND    CFDATE  Between '"+yr+"-04-01"+"' and '"+yr+"-06-30"+"'" ;
                        
                         System.out.println("query is  -->" + qry1);                     
                          
                           rs = st.executeQuery(qry1);
                         //System.out.println("ResultSet  ----------2>    "+rs1);
                         //System.out.println("before if condition");
                        if (rs.next()) {
                           //System.out.println("in if condition");
                                               
                                                I = rs.getDouble(1);
                                                //System.out.println("the value is1"+I);
                                                
                                                   // I = Double.parseDouble(x);
                                                
                                                    //System.out.println("the value of I--  is"+I);
                                                    //cbdt.setI(I);
                                                    //System.out.println("ccc"+cbdt.getQta());
                                                    ////System.out.println("I     ___________>" + I);
                                                                                         }
                         
                      //cbdt.setQta(I);
                      rs.close();
                      st.close();
                      con.close();
                      
                      
                      //I closed
                      
                      
                       String qrb = "SELECT CFDATE,BILLNO,INVNO,ACNAME,INVVAL,REP FROM CFORM WHERE REP LIKE '%" + rep + "%' AND    CFDATE  Between '"+yr+"-07-01"+"' and '"+yr+"-09-30"+"'" ;
                       System.out.println(""+qrb);
                       st1=DBConnection.Conn_Statement();
                       System.out.println("statement is created");
                       rs1 = st1.executeQuery(qrb);
                       //System.out.println("ResultSet  1---------->    "+rs);
                       k = 1;
                      
                      
                      
                      while (rs1.next()) {
                           cbdt=new CFormOutstandingSummaryTableBean();
                       
                       
                       Date dt = rs1.getDate(1);
                       String bno=rs1.getString(2);
                       String ino=rs1.getString(3);
                       String name=rs1.getString(4);
                       cbd.setAmount(rs1.getDouble(5) + cbd.getAmount());
                       amount=cbd.getAmount();
                       amt=rs1.getDouble(5);
                       String re=rs1.getString(6);
                       
                       cbdt.setK(k);
                       
                       cbdt.setRep(name);
                         Double d=rs1.getDouble(5);
                         System.out.println("amount from the rs1 is--------"+d);
                           cbdt.setQtb(d);
                           total=cbdt.getQta()+cbdt.getQtb()+cbdt.getQtc()+cbdt.getQtd();
                           cbdt.setTotal(total);
                           
                           tdata1.add(cbdt);
                           
                          
                       k++;
                       }
                      
                         
                         
                         qry2="SELECT SUM(INVVAL) FROM     CFORM  WHERE REP like '%" + rep + "' AND    CFDATE  Between '"+yr+"-07-01"+"' and '"+yr+"-09-30"+"'" ;
                       
                          
                        
                          rs1 = st1.executeQuery(qry2);
                          
                         if (rs1.next()) {
                              //System.out.println("in rs2");
                                                 
                                                 II = rs1.getDouble(1);
                                                // System.out.println("2 vcalue"+II);
                                                 
                                                     //II = Double.parseDouble(x);
                                                     //
                                                     ////System.out.println("I     ___________>" + I);
                                                 
                                             }
                      //cbdt.setQtb(II);
                      
                      rs1.close();
                      st1.close();
                      con.close();
                      
                      //II closed
                      
                      
                      
                      String qrc = "SELECT CFDATE,BILLNO,INVNO,ACNAME,INVVAL,REP FROM CFORM WHERE REP LIKE '%" + rep + "%' AND    CFDATE  Between '"+yr+"-10-01"+"' and '"+yr+"-12-30"+"'" ;
                      System.out.println(""+qrc);
                      st2=DBConnection.Conn_Statement();
                      System.out.println("statement is created");
                      rs2 = st2.executeQuery(qrc);
                      //System.out.println("ResultSet  1---------->    "+rs);
                      k = 1;
                      
                     
                      
                      while (rs2.next()) {
                      cbdt=new CFormOutstandingSummaryTableBean();
                      
                      
                      Date dt = rs2.getDate(1);
                      String bno=rs2.getString(2);
                      String ino=rs2.getString(3);
                      String name=rs2.getString(4);
                      cbd.setAmount(rs2.getDouble(5) + cbd.getAmount());
                      amount=cbd.getAmount();
                      amt=rs2.getDouble(5);
                      String re=rs2.getString(6);
                      
                      cbdt.setK(k);
                      
                      cbdt.setRep(name);
                      cbdt.setQtc(rs2.getDouble(5));
                          total=cbdt.getQta()+cbdt.getQtb()+cbdt.getQtc()+cbdt.getQtd();
                          cbdt.setTotal(total);
                      tdata1.add(cbdt);
                      
                      k++;
                      }
                      
                      
                      
                      
                         qry3="SELECT SUM(INVVAL) FROM     CFORM  WHERE REP like '%" + rep + "' AND    CFDATE  Between '"+yr+"-10-01"+"' and '"+yr+"-12-31"+"'" ;
                         
                          
                          
                       
                          rs2 = st2.executeQuery(qry3);
                          
                          if(rs2.next()) {
                                                 //System.out.println("in rs3");
                                                
                                                 III = rs2.getDouble(1);
                                                // System.out.println("3 vcalue"+III);
                                                
                                                     //III = Double.parseDouble(x);
                                                     //cbdt.setIII(III);
                                                     ////System.out.println("I     ___________>" + I);
                                                 
                                             }
                      //cbdt.setQtc(III);
                      rs2.close();
                      st2.close();
                      con.close();
                      
                      //III closed
                      
                      
                      
                      String qrd = "SELECT CFDATE,BILLNO,INVNO,ACNAME,INVVAL,REP FROM CFORM WHERE REP LIKE '%" + rep + "%' AND    CFDATE  Between '"+yr+"-01-01"+"' and '"+yr+"-03-30"+"'" ;
                      System.out.println(""+qrd);
                      st3=DBConnection.Conn_Statement();
                      System.out.println("statement is created");
                      rs3 = st3.executeQuery(qrd);
                      //System.out.println("ResultSet  1---------->    "+rs);
                      k = 1;
                      
                      
                      
                      while (rs3.next()) {
                      
                      cbdt=new CFormOutstandingSummaryTableBean();
                      
                      Date dt = rs3.getDate(1);
                      String bno=rs3.getString(2);
                      String ino=rs3.getString(3);
                      String name=rs3.getString(4);
                      cbd.setAmount(rs3.getDouble(5) + cbd.getAmount());
                      amount=cbd.getAmount();
                      amt=rs3.getDouble(5);
                      String re=rs3.getString(6);
                      
                      cbdt.setK(k);
                      cbdt.setRep(name);
                      cbdt.setQtd(rs3.getDouble(5));
                          total=cbdt.getQta()+cbdt.getQtb()+cbdt.getQtc()+cbdt.getQtd();
                          cbdt.setTotal(total);
                      
                      tdata1.add(cbdt);
                      k++;
                      }
                      
                      
                      
                      
                      
                      
                          
                         qry4="SELECT SUM(INVVAL) FROM     CFORM  WHERE REP like '%" + rep + "' AND    CFDATE  Between '"+yr+"-01-01"+"' and '"+yr+"-03-30"+"'" ;
                         
                          
                          
                         
                          rs3 = st3.executeQuery(qry4);
                          
                          if (rs3.next()) {
                                                 //System.out.println("in rs4");
                                                 
                                                 IV = rs3.getDouble(1);
                                                 //System.out.println("4 vcalue"+IV);
                                                 
                                                     //IV = Double.parseDouble(x);
                                                     //cbdt.setIV(IV);
                                                     ////System.out.println("I     ___________>" + I);
                                                 
                                             }
                      //cbdt.setQtd(IV);
                         rs3.close();
                         st3.close();
                        con.close();
                         
                         
                         
                         //cbdt.setRep(aaa);
                        // cbdt.setK(k);
                         //cbdt.setQta(I);
                         //cbdt.setQtb(II);
                         //cbdt.setQtc(III);
                         //cbdt.setQtd(IV);
                         total=I+II+III+IV;
                         cbdt.setGrandt(total);
                    // tdata1.add(cbdt);
                         //System.out.println("instance value is"+cbdt);
                        // System.out.println("Total of-----"+name+"is----"+total);
                         
                         
                        
                         
                        
                         //System.out.println("size1"+tdata1.size());
                         
                        // }  //System.out.println("after adding data");
                      
                      System.out.println("Total is--------"+total+"----------"+cbdt.getGrandt());
                      
                      tdata=tdata1;


               
                
                
                  } catch (Exception ex) {
                      ex.printStackTrace();
                  }
            }
            return tdata;
        }
    
    
    public static List<CFormByRepTableBean> crepData(CFormByRepBean cbd){
            List<CFormByRepTableBean>  tdata=new ArrayList<CFormByRepTableBean>();
            List<CFormByRepTableBean>  tdata1=new ArrayList<CFormByRepTableBean>();
            CFormByRepTableBean cbdt;
            String qry = "",qry1="",qry2="",qry3="",qry4="";
            Double I=0.0,II=0.0,III=0.0,IV=0.0,total=0.0,amt=0.0;
            int k;
            st=Conn_Statement();
           cbd.setAmount(0.0);
            
            if (cbd.getRep() != null) {
               
                
               
                    String rep = cbd.getRep();
                    String yr=cbd.getYr();

                        if(rep.equalsIgnoreCase("ALL")){
                            rep="";
                            
                        }
                        System.out.println(rep+"--------------"+yr);
                        
                        
               // sd.setAmount(Double.parseDouble(net) + sd.getAmount());
                //amt = dfr.format(sd.getAmount());
               // amount = sd.getAmount();
               
            
            try {
                   
                
                       String qra = "SELECT CFDATE,BILLNO,INVNO,ACNAME,INVVAL,REP FROM CFORM WHERE REP LIKE '%" + rep + "%' AND    CFDATE  Between '"+yr+"-04-01"+"' and '"+yr+"-06-30"+"'" ;
                       System.out.println(""+qra);
                      st=DBConnection.Conn_Statement();
                System.out.println("statement is created");
                      rs = st.executeQuery(qra);
                //System.out.println("ResultSet  1---------->    "+rs);
                       k = 1;
                
                      cbdt=new CFormByRepTableBean();
                      while (rs.next()) {
                             cbdt=new CFormByRepTableBean();
                
                          
                          Date dt = rs.getDate(1);
                          String bno=rs.getString(2);
                          String ino=rs.getString(3);
                          String name=rs.getString(4);
                          cbd.setAmount(rs.getDouble(5) + cbd.getAmount());
                          amount=cbd.getAmount();
                          amt=rs.getDouble(5);
                          String re=rs.getString(6);
                          
                          cbdt.setSno(k);
                          cbdt.setSdate(dt);
                          cbdt.setBillno(bno);
                          cbdt.setIno(ino);
                          cbdt.setName(name);
                          cbdt.setIamt(amt);
                          cbdt.setRep(re); 
                          cbdt.setQta(rs.getDouble(5));
                          total=cbdt.getQta()+cbdt.getQtb()+cbdt.getQtc()+cbdt.getQtd();
                          cbdt.setTotal(total);
                          
                          
                              tdata1.add(cbdt);
                             
                          k++;
                        
                      }
               
                          
                          qry1 = "SELECT SUM(INVVAL) FROM CFORM WHERE REP LIKE '%" + rep + "%' AND    CFDATE  Between '"+yr+"-04-01"+"' and '"+yr+"-06-30"+"'" ;
                         
                          System.out.println("query is  -->" + qry1);                     
                           
                            rs = st.executeQuery(qry1);
                          //System.out.println("ResultSet  ----------2>    "+rs1);
                          //System.out.println("before if condition");
                         if (rs.next()) {
                            //System.out.println("in if condition");
                                                
                                                 I = rs.getDouble(1);
                                                 //System.out.println("the value is1"+I);
                                                 
                                                    // I = Double.parseDouble(x);
                                                 
                                                     //System.out.println("the value of I--  is"+I);
                                                     //cbdt.setI(I);
                                                     //System.out.println("ccc"+cbdt.getQta());
                                                     ////System.out.println("I     ___________>" + I);
                                                                                          }
                          
                      //cbdt.setQta(I);
                          rs.close();
                          st.close();
                          con.close();
                
                
                //I closed
                
             
                        String qrb = "SELECT CFDATE,BILLNO,INVNO,ACNAME,INVVAL,REP FROM CFORM WHERE REP LIKE '%" + rep + "%' AND    CFDATE  Between '"+yr+"-07-01"+"' and '"+yr+"-09-30"+"'" ;
                        System.out.println(""+qrb);
                        st1=DBConnection.Conn_Statement();
                        System.out.println("statement is created");
                        rs1 = st1.executeQuery(qrb);
                        //System.out.println("ResultSet  1---------->    "+rs);
                        k = 1;
                      
                
               
                       while (rs1.next()) {
                            cbdt=new CFormByRepTableBean();
                        
                        
                        Date dt = rs1.getDate(1);
                        String bno=rs1.getString(2);
                        String ino=rs1.getString(3);
                        String name=rs1.getString(4);
                        cbd.setAmount(rs1.getDouble(5) + cbd.getAmount());
                        amount=cbd.getAmount();
                        amt=rs1.getDouble(5);
                        String re=rs1.getString(6);
                        
                        cbdt.setSno(k);
                        cbdt.setSdate(dt);
                        cbdt.setBillno(bno);
                        cbdt.setIno(ino);
                        cbdt.setName(name);
                        cbdt.setIamt(amt);
                        cbdt.setRep(re);
                          Double d=rs1.getDouble(5);
                          System.out.println("amount from the rs1 is--------"+d);
                            cbdt.setQtb(d);
                            total=cbdt.getQta()+cbdt.getQtb()+cbdt.getQtc()+cbdt.getQtd();
                            cbdt.setTotal(total);
                            
                            tdata1.add(cbdt);
                            
                           
                        k++;
                        }
                      
                          
                          
                          qry2="SELECT SUM(INVVAL) FROM     CFORM  WHERE REP like '%" + rep + "' AND    CFDATE  Between '"+yr+"-07-01"+"' and '"+yr+"-09-30"+"'" ;
                        
                           
                         
                           rs1 = st1.executeQuery(qry2);
                           
                          if (rs1.next()) {
                               //System.out.println("in rs2");
                                                  
                                                  II = rs1.getDouble(1);
                                                 // System.out.println("2 vcalue"+II);
                                                  
                                                      //II = Double.parseDouble(x);
                                                      //
                                                      ////System.out.println("I     ___________>" + I);
                                                  
                                              }
                      //cbdt.setQtb(II);
                
                                rs1.close();
                                st1.close();
                                con.close();
                
                //II closed
                
              
                
                String qrc = "SELECT CFDATE,BILLNO,INVNO,ACNAME,INVVAL,REP FROM CFORM WHERE REP LIKE '%" + rep + "%' AND    CFDATE  Between '"+yr+"-10-01"+"' and '"+yr+"-12-30"+"'" ;
                 System.out.println(""+qrc);
                 st2=DBConnection.Conn_Statement();
                 System.out.println("statement is created");
                 rs2 = st2.executeQuery(qrc);
                 //System.out.println("ResultSet  1---------->    "+rs);
                  k = 1;
                 
                     
                      
                  while (rs2.next()) {
                       cbdt=new CFormByRepTableBean();
                   
                   
                   Date dt = rs2.getDate(1);
                   String bno=rs2.getString(2);
                   String ino=rs2.getString(3);
                   String name=rs2.getString(4);
                   cbd.setAmount(rs2.getDouble(5) + cbd.getAmount());
                   amount=cbd.getAmount();
                   amt=rs2.getDouble(5);
                   String re=rs2.getString(6);
                   
                   cbdt.setSno(k);
                   cbdt.setSdate(dt);
                   cbdt.setBillno(bno);
                   cbdt.setIno(ino);
                   cbdt.setName(name);
                   cbdt.setIamt(amt);
                   cbdt.setRep(re);
                       cbdt.setQtc(rs2.getDouble(5));
                      
                       total=cbdt.getQta()+cbdt.getQtb()+cbdt.getQtc()+cbdt.getQtd();
                       cbdt.setTotal(total);
                      
                       tdata1.add(cbdt);
                      
                   k++;
                   }
                 
                     
                
                
                          qry3="SELECT SUM(INVVAL) FROM     CFORM  WHERE REP like '%" + rep + "' AND    CFDATE  Between '"+yr+"-10-01"+"' and '"+yr+"-12-31"+"'" ;
                          
                           
                           
                        
                           rs2 = st2.executeQuery(qry3);
                           
                           if(rs2.next()) {
                                                  //System.out.println("in rs3");
                                                 
                                                  III = rs2.getDouble(1);
                                                 // System.out.println("3 vcalue"+III);
                                                 
                                                      //III = Double.parseDouble(x);
                                                      //cbdt.setIII(III);
                                                      ////System.out.println("I     ___________>" + I);
                                                  
                                              }
                //cbdt.setQtc(III);
                            rs2.close();
                            st2.close();
                            con.close();
                
                //III closed
                
                
                
                String qrd = "SELECT CFDATE,BILLNO,INVNO,ACNAME,INVVAL,REP FROM CFORM WHERE REP LIKE '%" + rep + "%' AND    CFDATE  Between '"+yr+"-01-01"+"' and '"+yr+"-03-30"+"'" ;
                 System.out.println(""+qrd);
                 st3=DBConnection.Conn_Statement();
                 System.out.println("statement is created");
                 rs3 = st3.executeQuery(qrd);
                 //System.out.println("ResultSet  1---------->    "+rs);
                  k = 1;
                 
                     
                 while (rs3.next()) {
                   
                       cbdt=new CFormByRepTableBean();
                   
                   Date dt = rs3.getDate(1);
                   String bno=rs3.getString(2);
                   String ino=rs3.getString(3);
                   String name=rs3.getString(4);
                   cbd.setAmount(rs3.getDouble(5) + cbd.getAmount());
                   amount=cbd.getAmount();
                   amt=rs3.getDouble(5);
                   String re=rs3.getString(6);
                   
                   cbdt.setSno(k);
                   cbdt.setSdate(dt);
                   cbdt.setBillno(bno);
                   cbdt.setIno(ino);
                   cbdt.setName(name);
                   cbdt.setIamt(amt);
                   cbdt.setRep(re);
                    cbdt.setQtd(rs3.getDouble(5));
                     
                       total=cbdt.getQta()+cbdt.getQtb()+cbdt.getQtc()+cbdt.getQtd();
                       cbdt.setTotal(total);
                   
                       tdata1.add(cbdt);
                   k++;
                   }
                
                     
                
                
                
                
                           
                          qry4="SELECT SUM(INVVAL) FROM     CFORM  WHERE REP like '%" + rep + "' AND    CFDATE  Between '"+yr+"-01-01"+"' and '"+yr+"-03-30"+"'" ;
                          
                           
                           
                          
                           rs3 = st3.executeQuery(qry4);
                           
                           if (rs3.next()) {
                                                  //System.out.println("in rs4");
                                                  
                                                  IV = rs3.getDouble(1);
                                                  //System.out.println("4 vcalue"+IV);
                                                  
                                                      //IV = Double.parseDouble(x);
                                                      //cbdt.setIV(IV);
                                                      ////System.out.println("I     ___________>" + I);
                                                  
                                              }
                //cbdt.setQtd(IV);
                             rs3.close();
                             st3.close();
                             con.close();
                         
                          
                          
                          
                          //cbdt.setRep(aaa);
                         // cbdt.setK(k);
                          //cbdt.setQta(I);
                          //cbdt.setQtb(II);
                          //cbdt.setQtc(III);
                          //cbdt.setQtd(IV);
                          total=I+II+III+IV;
                          
                          //System.out.println("instance value is"+cbdt);
                         // System.out.println("Total of-----"+name+"is----"+total);
                          
                          
                         
                          
                         
                          //System.out.println("size1"+tdata1.size());
                          
                         // }  //System.out.println("after adding data");
                    
                      System.out.println("Total is--------"+total);
                      
        tdata=tdata1;


                  } catch (Exception ex) {
                
                      ex.printStackTrace();
                    
                  }
            finally{
                try{
                    //System.out.println("statement is closed");
                    //con.close();;
                    //rs.close();
                    //st.close();
                }catch(Exception e2){
                   e2.printStackTrace(); 
                }
            }
            
            }
            return tdata;
        }
    
    
    public static List<CFormByCompanyTableBean> compData(CFormByCompanyBean cbd){
            List<CFormByCompanyTableBean>  tdata=new ArrayList<CFormByCompanyTableBean>();
            List<CFormByCompanyTableBean>  tdata1=new ArrayList<CFormByCompanyTableBean>();
            CFormByCompanyTableBean cbdt;
            String qry = "",qry1="",qry2="",qry3="",qry4="";
            Double I=0.0,II=0.0,III=0.0,IV=0.0,total=0.0,amt=0.0;
            int k;
            st=Conn_Statement();
           cbd.setAmount(0.0);
            
            if (cbd.getRep() != null) {
               
                
               
                    String rep = cbd.getRep();
                    String yr=cbd.getYr();

                        if(rep.equalsIgnoreCase("ALL")){
                            rep="";
                            
                        }
                        System.out.println(rep+"--------------"+yr);
                        
                        
               // sd.setAmount(Double.parseDouble(net) + sd.getAmount());
                //amt = dfr.format(sd.getAmount());
               // amount = sd.getAmount();
                
            
            try {
                    
                
                
                String qra = "SELECT CFDATE,BILLNO,INVNO,ACNAME,INVVAL,REP FROM CFORM WHERE ACNAME LIKE '%" + rep + "%' AND    CFDATE  Between '"+yr+"-04-01"+"' and '"+yr+"-06-30"+"'" ;
                System.out.println(""+qra);
                st=DBConnection.Conn_Statement();
                System.out.println("statement is created");
                rs = st.executeQuery(qra);
                //System.out.println("ResultSet  1---------->    "+rs);
                k = 1;
                
                cbdt=new CFormByCompanyTableBean();
                while (rs.next()) {
                      cbdt=new CFormByCompanyTableBean();
                
                   
                   Date dt = rs.getDate(1);
                   String bno=rs.getString(2);
                   String ino=rs.getString(3);
                   String name=rs.getString(4);
                   cbd.setAmount(rs.getDouble(5) + cbd.getAmount());
                   amount=cbd.getAmount();
                   amt=rs.getDouble(5);
                   String re=rs.getString(6);
                   
                   cbdt.setSno(k);
                   cbdt.setSdate(dt);
                   cbdt.setBillno(bno);
                   cbdt.setIno(ino);
                   cbdt.setName(name);
                   cbdt.setIamt(amt);
                   cbdt.setRep(re); 
                   cbdt.setQta(rs.getDouble(5));
                    total=cbdt.getQta()+cbdt.getQtb()+cbdt.getQtc()+cbdt.getQtd();
                    cbdt.setTotal(total);
                   
                    
                   
                       tdata1.add(cbdt);
                      
                   k++;
                 
                }
                
                   
                   qry1 = "SELECT SUM(INVVAL) FROM CFORM WHERE ACNAME LIKE '%" + rep + "%' AND    CFDATE  Between '"+yr+"-04-01"+"' and '"+yr+"-06-30"+"'" ;
                  
                   System.out.println("query is  -->" + qry1);                     
                    
                     rs = st.executeQuery(qry1);
                   //System.out.println("ResultSet  ----------2>    "+rs1);
                   //System.out.println("before if condition");
                  if (rs.next()) {
                     //System.out.println("in if condition");
                                         
                                          I = rs.getDouble(1);
                                          //System.out.println("the value is1"+I);
                                          
                                             // I = Double.parseDouble(x);
                                          
                                              //System.out.println("the value of I--  is"+I);
                                              //cbdt.setI(I);
                                              //System.out.println("ccc"+cbdt.getQta());
                                              ////System.out.println("I     ___________>" + I);
                                                                                   }
                   
                //cbdt.setQta(I);
                      rs.close();
                      st.close();
                      con.close();
                
                
                //I closed
                
                
                 String qrb = "SELECT CFDATE,BILLNO,INVNO,ACNAME,INVVAL,REP FROM CFORM WHERE ACNAME LIKE '%" + rep + "%' AND    CFDATE  Between '"+yr+"-07-01"+"' and '"+yr+"-09-30"+"'" ;
                 System.out.println(""+qrb);
                 st1=DBConnection.Conn_Statement();
                 System.out.println("statement is created");
                 rs1 = st1.executeQuery(qrb);
                 //System.out.println("ResultSet  1---------->    "+rs);
                 k = 1;
                
               
                
                while (rs1.next()) {
                     cbdt=new CFormByCompanyTableBean();
                 
                 
                 Date dt = rs1.getDate(1);
                 String bno=rs1.getString(2);
                 String ino=rs1.getString(3);
                 String name=rs1.getString(4);
                 cbd.setAmount(rs1.getDouble(5) + cbd.getAmount());
                 amount=cbd.getAmount();
                 amt=rs1.getDouble(5);
                 String re=rs1.getString(6);
                 
                 cbdt.setSno(k);
                 cbdt.setSdate(dt);
                 cbdt.setBillno(bno);
                 cbdt.setIno(ino);
                 cbdt.setName(name);
                 cbdt.setIamt(amt);
                 cbdt.setRep(re);
                   Double d=rs1.getDouble(5);
                   
                     cbdt.setQtb(d);
                     total=cbdt.getQta()+cbdt.getQtb()+cbdt.getQtc()+cbdt.getQtd();
                     cbdt.setTotal(total);
                     
                     tdata1.add(cbdt);
                     
                    
                 k++;
                 }
                
                   
                   
                   qry2="SELECT SUM(INVVAL) FROM     CFORM  WHERE ACNAME like '%" + rep + "' AND    CFDATE  Between '"+yr+"-07-01"+"' and '"+yr+"-09-30"+"'" ;
                 
                    
                  
                    rs1 = st1.executeQuery(qry2);
                    
                   if (rs1.next()) {
                        //System.out.println("in rs2");
                                           
                                           II = rs1.getDouble(1);
                                          // System.out.println("2 vcalue"+II);
                                           
                                               //II = Double.parseDouble(x);
                                               //
                                               ////System.out.println("I     ___________>" + I);
                                           
                                       }
                //cbdt.setQtb(II);
                
                      rs1.close();
                      st1.close();
                      con.close();
                
                //II closed
                
                
                
                String qrc = "SELECT CFDATE,BILLNO,INVNO,ACNAME,INVVAL,REP FROM CFORM WHERE ACNAME LIKE '%" + rep + "%' AND    CFDATE  Between '"+yr+"-10-01"+"' and '"+yr+"-12-30"+"'" ;
                System.out.println(""+qrc);
                st2=DBConnection.Conn_Statement();
                System.out.println("statement is created");
                rs2 = st2.executeQuery(qrc);
                //System.out.println("ResultSet  1---------->    "+rs);
                k = 1;
                
                
                
                while (rs2.next()) {
                cbdt=new CFormByCompanyTableBean();
                
                
                Date dt = rs2.getDate(1);
                String bno=rs2.getString(2);
                String ino=rs2.getString(3);
                String name=rs2.getString(4);
                cbd.setAmount(rs2.getDouble(5) + cbd.getAmount());
                amount=cbd.getAmount();
                amt=rs2.getDouble(5);
                String re=rs2.getString(6);
                
                cbdt.setSno(k);
                cbdt.setSdate(dt);
                cbdt.setBillno(bno);
                cbdt.setIno(ino);
                cbdt.setName(name);
                cbdt.setIamt(amt);
                cbdt.setRep(re);
                cbdt.setQtc(rs2.getDouble(5));
                    total=cbdt.getQta()+cbdt.getQtb()+cbdt.getQtc()+cbdt.getQtd();
                    cbdt.setTotal(total);
                    
                tdata1.add(cbdt);
                
                k++;
                }
                
                
                
                
                   qry3="SELECT SUM(INVVAL) FROM     CFORM  WHERE ACNAME like '%" + rep + "' AND    CFDATE  Between '"+yr+"-10-01"+"' and '"+yr+"-12-31"+"'" ;
                   
                    
                    
                 
                    rs2 = st2.executeQuery(qry3);
                    
                    if(rs2.next()) {
                                           //System.out.println("in rs3");
                                          
                                           III = rs2.getDouble(1);
                                          // System.out.println("3 vcalue"+III);
                                          
                                               //III = Double.parseDouble(x);
                                               //cbdt.setIII(III);
                                               ////System.out.println("I     ___________>" + I);
                                           
                                       }
                //cbdt.setQtc(III);
                rs2.close();
                st2.close();
                con.close();
                
                //III closed
                
                
                
                String qrd = "SELECT CFDATE,BILLNO,INVNO,ACNAME,INVVAL,REP FROM CFORM WHERE ACNAME LIKE '%" + rep + "%' AND    CFDATE  Between '"+yr+"-01-01"+"' and '"+yr+"-03-30"+"'" ;
                System.out.println(""+qrd);
                st3=DBConnection.Conn_Statement();
                System.out.println("statement is created");
                rs3 = st3.executeQuery(qrd);
                //System.out.println("ResultSet  1---------->    "+rs);
                k = 1;
                
                            
                while (rs3.next()) {
                
                cbdt=new CFormByCompanyTableBean();
                
                Date dt = rs3.getDate(1);
                String bno=rs3.getString(2);
                String ino=rs3.getString(3);
                String name=rs3.getString(4);
                cbd.setAmount(rs3.getDouble(5) + cbd.getAmount());
                amount=cbd.getAmount();
                amt=rs3.getDouble(5);
                String re=rs3.getString(6);
                
                cbdt.setSno(k);
                cbdt.setSdate(dt);
                cbdt.setBillno(bno);
                cbdt.setIno(ino);
                cbdt.setName(name);
                cbdt.setIamt(amt);
                cbdt.setRep(re);
                cbdt.setQtd(rs3.getDouble(5));
                    total=cbdt.getQta()+cbdt.getQtb()+cbdt.getQtc()+cbdt.getQtd();
                    cbdt.setTotal(total);
                
                tdata1.add(cbdt);
                k++;
                }
                
                
                
                
                
                
                    
                   qry4="SELECT SUM(INVVAL) FROM     CFORM  WHERE ACNAME like '%" + rep + "' AND    CFDATE  Between '"+yr+"-01-01"+"' and '"+yr+"-03-30"+"'" ;
                   
                    
                    
                   
                    rs3 = st3.executeQuery(qry4);
                    
                    if (rs3.next()) {
                                           //System.out.println("in rs4");
                                           
                                           IV = rs3.getDouble(1);
                                           //System.out.println("4 vcalue"+IV);
                                           
                                               //IV = Double.parseDouble(x);
                                               //cbdt.setIV(IV);
                                               ////System.out.println("I     ___________>" + I);
                                           
                                       }
                //cbdt.setQtd(IV);
                   rs3.close();
                   st3.close();
                   con.close();
                   
                   
                   
                   //cbdt.setRep(aaa);
                  // cbdt.setK(k);
                   //cbdt.setQta(I);
                   //cbdt.setQtb(II);
                   //cbdt.setQtc(III);
                   //cbdt.setQtd(IV);
                   total=I+II+III+IV;
                  
                   //System.out.println("instance value is"+cbdt);
                  // System.out.println("Total of-----"+name+"is----"+total);
                   
                   
                  
                   
                  
                   //System.out.println("size1"+tdata1.size());
                   
                  // }  //System.out.println("after adding data");
                
                System.out.println("Total is--------"+total);
                
                tdata=tdata1;

        
        
                  } catch (Exception ex) {
                      ex.printStackTrace();
                  }
            }
            return tdata;
        }
        
        
    
}

