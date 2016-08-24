package transaction.managedbean;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import java.io.Serializable;


import java.sql.DriverManager;
import java.sql.ResultSet;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import javax.swing.JOptionPane;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;

import transaction.Reports.InvoiceSales;

import transaction.Reports.InvoiceSalesSingle;

import transaction.beanpackage.CustomerBean;
import transaction.beanpackage.CustomerDetailsBean;
import transaction.beanpackage.ItemListBean;
import transaction.beanpackage.SearchBean;
import transaction.beanpackage.TransactionTableBean;


@ManagedBean(name = "tsales", eager = true)
@ViewScoped
public class Sales {
    private String string;
       private static String amount;
    private String st1[] = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven",
                    "Eight", "Nine", };
    private String st2[] = { "Hundred", "Thousand", "Lakh", "Crore" };
     private String st3[] = { "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
                    "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Ninteen", };
   private  String st4[] = { "Twenty", "Thirty", "Fourty", "Fifty", "Sixty", "Seventy",
                    "Eighty", "Ninty" };
    
    
    
    
    private String inm;
    private ItemListBean ib, selectedItem;
    private List<ItemListBean> ibdata, ibdata1;
    private Integer rid;
    private CustomerBean sd, selectedcust;
    private CustomerDetailsBean cdb;

    private TransactionTableBean ttb, selectedttb, tablessid;
    private TransactionTableBean selectedprofile, selecteddata;

    private List<TransactionTableBean> tdata, tdata1;
    private int companyid = 1;
    private List<CustomerBean> cdata, cdata1, cc;
    private String sname;
    private List taxlist = new ArrayList();
    private int salesid, billno, reffno, cfid, rfno;
    private Boolean b1 = false, b2 = false, b3 = false;
    DecimalFormat dfr = new DecimalFormat("#.##");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
     Connection con=null;
     Statement st = null;
     ResultSet rs = null;
     PreparedStatement ps = null;
    private int j;
    private SearchBean sb,selectedsb;
    private List<SearchBean> sblist1,sblist2;
    private List salesidlist;
private Integer ssid=0;

    public Sales() {
        super();
        tdata = new ArrayList<TransactionTableBean>();
        selectedcust = new CustomerBean();
        selectedItem = new ItemListBean();
        selectedsb=new SearchBean();
        selectedttb = new TransactionTableBean();
        bill();
        custDetails();
        cdb.setSalestype("Both");
        //cdb.setTaxtype("GEN");
        Date d = new Date();
        String date = df.format(d);
        cdb.setInvdate(d);
        cdb.setOrdate(d);
        cdb.setLrDate(d);
        cdb.setWaydate(d);

        taxdata();

        itemDetails();
    }
    
    static{
        /* try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory", "root", "root");
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


        return st; */
    }


   public Statement Conn_Statement() {

     try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory", "root", "root");
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


     public Connection connection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory", "root", "root");
//con.setAutoCommit(false);
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
    
    /*    public void onRowDblClckSelect(final SelectEvent event) {

        String sk= ((CustomerBean) event.getObject()).getCustname();
        System.out.println("sk value is"+sk);
        // rest of your logic
    } */

    public void numGen() {

        int j = (int)(Math.random());
        System.out.println(j);

    }


    public void tableData() {

        //tdata.getClass()

        ttb = new TransactionTableBean();

        int i = tdata.size();

        ttb.setSs(i + 1);
        System.out.println("ss valuendad" + ttb.getSs());


        ttb.setItemcode(0);

        ttb.setIname("click here to select items");
        ttb.setOrirate(0.0);
        ttb.setQuan(0.0);
        ttb.setDis1(0.0);
        ttb.setDis2(0.0);
        ttb.setDis3(0.0);
        ttb.setUdis(0.0);
        ttb.setDisamt(0.0);
        ttb.setUcost(0.0);
        ttb.setTax(0.0);
        ttb.setUtax(0.0);
        ttb.setTaxamt(0.0);
        ttb.setNtval(0.0);
        tdata.add(ttb);


    }

    public void custDetails() {
        System.out.println("i am cust function");
        setB1(true);

        cdata = new ArrayList<CustomerBean>();
        st = Conn_Statement();
        String qry = "";
        try {
            qry = "SELECT acccode, cmpname FROM Customer";
            rs = st.executeQuery(qry);
            while (rs.next()) {
                sd = new CustomerBean();
                sd.setAccno(rs.getInt(1));
                sd.setCustname(rs.getString(2));
                cdata.add(sd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(cdata);
        // return cdata;


    }
    public void StoringCurrBalInPage() {
          try {
st=Conn_Statement();
              Double new_cbal = 0.0, old_cbal = 0.0, old_net = 0.0, new_net = 0.0;

              //Currbal = (currbal - oldnetAmt)+newnet Amt

              //1.getting old currnet bal

              String qry = "SELECT * FROM  Curr_Bal WHERE  cmpid="+companyid+" and accno =" + cdb.getAcccode() ;
              rs = st.executeQuery(qry);
              if (rs.next()) {

                  old_cbal = rs.getDouble("bal");
                  System.out.println("old balance is"+old_cbal);
                  //System.out.println("OLD Curr Bal        :           " + old_cbal);
              }



              new_net = cdb.getInvvalue();
              //System.out.println("Net Amount         :           " + new_net);

              new_cbal = (old_cbal) + new_net;

              //System.out.println("New Curr Bal         :    " + new_cbal);
               cdb.setCurrbal(new_cbal);
         
System.out.println("new currbalance is "+cdb.getCurrbal());
              rs.close();
              st.close();
          } catch (Exception e) {
              e.printStackTrace();
          }

      }
    public void itemDetails() {
        System.out.println("i am item function");
        setB1(true);

        ibdata = new ArrayList<ItemListBean>();
        st = Conn_Statement();
        String qry = "";
        try {
            qry = "SELECT itemcode,itemname,itemrate  from ItemMaster";
            rs = st.executeQuery(qry);
            while (rs.next()) {
                ib = new ItemListBean();
                ib.setItemcode(rs.getInt(1));
                ib.setItemname(rs.getString(2));
                ib.setRate(rs.getDouble(3));
                ibdata.add(ib);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(ibdata);


    }

    public void taxChanged(ValueChangeEvent event) {
        System.out.println("in taxchanged()");
        CustomerDetailsBean cb = (CustomerDetailsBean)(event.getNewValue());
        System.out.println(cb.getTaxtype());
        cb.setTaxtype(cb.getTaxtype());
        try {
            String C_Form = "";

            if (cdb.getTaxtype() != null) {
                System.out.println("in taxchanged()11111");
                if (cdb.getTaxtype().contains("GEN")) {

                    cdb.setInvno("GEN/" + cdb.getRefno());
                    C_Form = "NO";
                }

                if (cdb.getTaxtype().contains("VAT")) {

                    cdb.setInvno("VAT/" + cdb.getRefno());
                    C_Form = "NO";
                }


                if (cdb.getTaxtype().contains("CST 14.5")) {

                    cdb.setInvno("CST/" + cdb.getRefno());
                    C_Form = "NO";
                }

                if (cdb.getTaxtype().contains("CST 2%")) {

                    cdb.setInvno("CST/" + cdb.getRefno());
                    C_Form = "YES";
                }

                /*  if(cdb.getSalestype().equalsIgnoreCase("With in State")){
                           C_Form = "NO";

                       }
                       if(cdb.getSalestype().equalsIgnoreCase("Out of State")){
                           C_Form = "YES";

                       } */
                cdb.setCformstatus(C_Form);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void taxChanged1() {
        System.out.println("in taxchanged1()");

        try {
            String C_Form = "";
            System.out.println(cdb.getTaxtype());
            if (cdb.getTaxtype() != null) {


                if (cdb.getTaxtype().contains("GEN")) {

                    cdb.setInvno("GEN/" + cdb.getRefno());
                    C_Form = "NO";
                }

                if (cdb.getTaxtype().contains("VAT")) {

                    cdb.setInvno("VAT/" + cdb.getRefno());
                    C_Form = "NO";
                }


                if (cdb.getTaxtype().contains("CST 14.5")) {

                    cdb.setInvno("CST/" + cdb.getRefno());
                    C_Form = "NO";
                }

                if (cdb.getTaxtype().contains("CST 2%")) {

                    cdb.setInvno("CST/" + cdb.getRefno());
                    C_Form = "YES";
                }

                /*  if(cdb.getSalestype().equalsIgnoreCase("With in State")){
                           C_Form = "NO";

                       }
                       if(cdb.getSalestype().equalsIgnoreCase("Out of State")){
                           C_Form = "YES";

                       } */
                cdb.setCformstatus(C_Form);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void taxdata() {
        taxlist.clear();
        String C_Form = "";
        try {
            st = Conn_Statement();
            String ss = cdb.getSalestype();
            if (ss.equalsIgnoreCase("Both")) {
                ss = "";
            }

            String qry =
                "SELECT `taxname` FROM `Tax` WHERE `region` like  '%" + ss + "%' and cmpid='" + companyid + "' ";
            System.out.println("taxdata query          :   " + qry);

            rs = st.executeQuery(qry);
            taxlist.clear();
            while (rs.next()) {

                taxlist.add(rs.getString(1));

            }
            cdb.setTaxtype((String)taxlist.get(0));


        } catch (Exception e) {
            e.printStackTrace();
        }
        taxChanged1();

    }
    public void print(){
         String nw=NumToWord(dfr.format(cdb.getInvvalue()).toString());
         System.out.println("nw value is "+nw);
        InvoiceSalesSingle ob=new InvoiceSalesSingle();
       ob.execute(cdb.getInvno(), cdb.getInvdate().toString(), nw);
    }
    public void onRowSelect(SelectEvent event) {
        cc = (List<CustomerBean>)event.getComponent().getAttributes().get("cdata");
        System.out.println("val cc isa-------" + cc);
        System.out.println("i am event");
        sname = ((CustomerBean)event.getObject()).getCustname();


        st = Conn_Statement();
        String qry = "";
        try {

            qry = " SELECT * FROM Customer where cmpname='" + sname + "'";
            System.out.println(qry);
            rs = st.executeQuery(qry);
            if (rs.next()) {
                //cdb = new CustomerDetailsBean();

                cdb.setAcccode(rs.getInt("acccode"));
                cdb.setCname(rs.getString("cmpname"));
                cdb.setCity(rs.getString("city"));

                cdb.setTransport(rs.getString("transport"));
                cdb.setPan(rs.getString("pan"));
                cdb.setCurrbal(rs.getDouble("currbal"));

                cdb.setStax(rs.getString("stax"));

                
                String opt = rs.getString("optype");
                if (opt.equalsIgnoreCase("CREDIT")) {
                    cdb.setOptype("Credit");
                } else {
                    cdb.setOptype("Debit");
                }
                cdb.setRep(rs.getString("repname"));
                cdb.setRid(rs.getInt("R_id"));
                cdb.setRoute(rs.getString("route"));
                cdb.setAddr(rs.getString("addr"));
                cdb.setBank(rs.getString("bank"));
                cdb.setAccno(rs.getString("accno"));
                cdb.setPhn(rs.getString("phnmob"));
                cdb.setModify("NO");
                cdb.setSlno(0);
                cdb.setOrno(0);
                cdb.setLrno(0);
                cdb.setWaybill(0);
                cdb.setGunybags(0.0);
                cdb.setBundles(0.0);
                cdb.setNote(".");
                cdb.setCf("Not Submitted");
                cdb.setPlusminus(".");
               
               
                
               


            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("accountcode" + cdb.getAcccode() + "city" + cdb.getCity());
    }


    public void bill() {
        cdb = new CustomerDetailsBean();
        st = Conn_Statement();
        String qry = "";
        qry = "SELECT max(salesid), max(Billno),max(reffno) FROM Vat_Sales where cmpid='" + companyid + "'";
        try {
            rs = st.executeQuery(qry);
            if (rs.next()) {

                salesid = rs.getInt(1);
                billno = rs.getInt(2);
                reffno = rs.getInt(3);
                rfno = reffno;
                System.out.println("salesid is-----"+salesid);
            }
            rs.close();
            st.close();
            con.close();
            if (salesid == 0) {

                salesid = 1000;
                cdb.setSid(salesid);
            } else {

                salesid++;
                cdb.setSid(salesid);
            }
            if (billno == 0) {

                billno = 1000;
                cdb.setBillno(billno);
            } else {

                billno++;
                cdb.setBillno(billno);
            }
            if (rfno == 0) {
                rfno = 1;
                cdb.setRefno(rfno);
                cdb.setInvno("GEN/1");
                //  ref.setText(String.valueOf(1));
                //  inv.setText("GEN/1");


            } else {
                rfno++;
                cdb.setRefno(rfno);
                cdb.setInvno("GEN/" + rfno);
                System.out.println((cdb.getInvno()));
                // ref.setText(String.valueOf(reff));
                //  inv.setText("GEN/" + reff);

            }


            st = Conn_Statement();
            rs = st.executeQuery("SELECT max(CFID) FROM CFORM where cmpid='" + companyid + "'");
            if (rs.next()) {

                cfid = rs.getInt(1);
            }
            rs.close();
            st.close();
            con.close();
            if (cfid == 0) {
                cfid = 1;
            } else {

                cfid++;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    // CellEditEvent

    public void onCellEdit(CellEditEvent event) {
        rowVal();

        Double quan = 0.0, tax = 0.0, dis = 0.0, ntval = 0.0, ori = 0.0;
        Double dis2 = 0.0, dis3 = 0.0;
        Double ucost = 0.0, udisc = 0.0, discamt = 0.0, uta = 0.0, tamt = 0.0;
        double nam = 0.0, sta = 0.0;
        /* TransactionTableBean sstb = new TransactionTableBean();
        int l = event.getRowIndex();
        System.out.println("value of j is.........." + l); */
        System.out.println("i am taxcal");
        TransactionTableBean ttb = tdata.get(rid);
        quan = ttb.getQuan();
        tax = ttb.getTax();

        dis = ttb.getDis1();
        ntval = ttb.getNtval();
        ori = ttb.getOrirate();

        dis2 = ttb.getDis2();
        dis3 = ttb.getDis3();

        ucost = ttb.getUcost();
        //ucost=ori;
        udisc = ttb.getUdis();
        discamt = ttb.getDisamt();
        uta = ttb.getUtax();
        System.out.println("Tax is --------" + tax + "----UnitTax is --------" + uta);
        System.out.println("unit tax is -------" + uta);
        tamt = ttb.getTaxamt();

        System.out.println(ttb.getQuan());


        //step-I
        //step-II


        //step -I

        dis = ((ori * dis * 0.01));
        //step-II
        ucost = (ori - dis);
        //step-III
        uta = ((ucost * tax * 0.01));
        //step-III
        tamt = (uta * quan);


        //step-IV
        discamt = (quan * dis);

        ntval = (quan * ucost);

        System.out.println(uta);
        System.out.println(tamt);
        System.out.println(dis);
        System.out.println(ucost);


        tdata.get(rid).setUtax(uta);
        tdata.get(rid).setTaxamt(tamt);
        tdata.get(rid).setUdis(dis);
        tdata.get(rid).setUcost(ucost);
        tdata.get(rid).setDisamt(discamt);
        tdata.get(rid).setNtval(ntval);


        // cdb.setNtamt(ntval);
        // cdb.setSalestax(tamt);
        cal2();
        
        
        DataTable s = (DataTable) event.getSource();
        RequestContext.getCurrentInstance().update(s.getClientId(FacesContext.getCurrentInstance()) + ":" + event.getRowIndex() + ":ud");
        RequestContext.getCurrentInstance().update(s.getClientId(FacesContext.getCurrentInstance()) + ":" + event.getRowIndex() + ":disamt");
        RequestContext.getCurrentInstance().update(s.getClientId(FacesContext.getCurrentInstance()) + ":" + event.getRowIndex() + ":ucost");
        RequestContext.getCurrentInstance().update(s.getClientId(FacesContext.getCurrentInstance()) + ":" + event.getRowIndex() + ":utax");
        RequestContext.getCurrentInstance().update(s.getClientId(FacesContext.getCurrentInstance()) + ":" + event.getRowIndex() + ":tamt");
        RequestContext.getCurrentInstance().update(s.getClientId(FacesContext.getCurrentInstance()) + ":" + event.getRowIndex() + ":amt"); 
  
        
        
    }

    public void cal2() {
        Double nam = 0.0, sta = 0.0;
        System.out.println("after delete size is --------------" + tdata.size());

        if (tdata.size() == 0) {
            cdb.setNtamt(0.0);
            cdb.setSalestax(0.0);
        }

        for (int i = 0; i < tdata.size(); i++) {

            nam = nam + tdata.get(i).getNtval();
            sta = sta + tdata.get(i).getTaxamt();
            System.out.println("nam value is-------" + nam + "sta value is------" + sta);
            cdb.setNtamt(nam);
            cdb.setSalestax(sta);
           vatcal();
        }
       // vatcal();
        cal();

    }

    public void cal() {
        Double namt = 0.0, stax = 0.0, pack = 0.0, postage = 0.0, roundoff = 0.0, other = 0.0, auchrg = 0.0, ival =
            0.0;
        System.out.println("in cal()");
        namt = getCdb().getNtamt();
        stax = getCdb().getSalestax();
        pack = getCdb().getPacking();

        postage = getCdb().getPostage();
        roundoff = getCdb().getRoundof();
        other = getCdb().getOthers();
        System.out.println("other is" + other);
        auchrg = getCdb().getAutochrg();
        System.out.println("bad");
        //ival=namt+stax+pack+postage+roundoff+other+auchrg;
        ival = namt + stax + pack + postage + roundoff + other + auchrg;
        System.out.println("net amount is ------" + ival);
        getCdb().setInvvalue(ival);
    }


    public void vatcal() {
        try {
            System.out.println("");
            Double vat5t = 0.0, vat14t = 0.0, cst2t = 0.0;
            Double vat5a = 0.0, vat14a = 0.0, cst2a = 0.0;
            //tax % 11, 13, 14
            int cou = 0;
            int rc = tdata.size();
            System.out.println("Count----->" + rc);


            for (int m = 0; m < tdata.size(); m++) {

                double c = tdata.get(m).getTax();
                double t = tdata.get(m).getTaxamt();
                System.out.println();
                double a = tdata.get(m).getNtval();
                System.out.println("--->C  " + c + "            T  " + t + "        a   " + a);
                if (c == 5.0) {

                    vat5t = vat5t + t;
                    vat5a = vat5a + a;

                }

                if (c == 14.5) {

                    vat14t = vat14t + t;
                    vat14a = vat14a + a;

                }

                if (c == 2) {

                    cst2t = cst2t + t;
                    cst2a = cst2a + a;

                }
                cou++;

            }
            cdb.setVat5a(vat5t);
            cdb.setVat5b(vat5a);

            cdb.setVat14a(vat14t);
            cdb.setVat14b(vat14a);

            cdb.setCst2a(cst2t);
            cdb.setCst2b(cst2a);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        int cform=0,l=0,sales=0,rows=0,curbalup=0, crbalu=0;
        int rowcount=tdata.size();
        System.out.println("in save ()");
        System.out.println("inv date ---"+cdb.getInvdate());
String date1=df.format(cdb.getInvdate());
System.out.println(new java.sql.Date(cdb.getInvdate().getTime()));
        try {
            int bill=cdb.getBillno();
            String qry1="select * from vat_sales where Billno="+cdb.getBillno() +"";
            st=Conn_Statement();
            rs=st.executeQuery(qry1);

            if(rs.next()){
            System.out.println("in faces message");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("no records to delete", new FacesMessage("The Data is Already is Stored please choose the Update Button")); 
                rs.close();
                st.close();
            
            }else{
                StoringCurrBalInPage();

            String qry =
                "insert into vat_sales values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
System.out.println("Row size is ---"+tdata.size());
            for(int i=0;i<tdata.size();i++){
                Integer s=cdb.getSid();
                System.out.println("svalue is---"+s);
                
            ps = (PreparedStatement)con.prepareStatement(qry);
            System.out.println("prepared statement object is -------" + ps);
            ps.setInt(1, companyid);
            ps.setInt(2, (s+i));
            ps.setInt(3, cdb.getBillno());
                System.out.println("Bill No is===="+cdb.getBillno());
            ps.setString(4, cdb.getAcccode().toString());
                System.out.println("cname value is"+cdb.getCname());
            ps.setString(5, cdb.getCname());
            ps.setDouble(6, cdb.getCurrbal());
            ps.setString(7, cdb.getSalestype());
            ps.setString(8, cdb.getOptype());
            ps.setString(9, cdb.getAddr());
            ps.setString(10, cdb.getCity());
            
            ps.setDate(11,new java.sql.Date(cdb.getInvdate().getTime()) );
            ps.setString(12, cdb.getInvno());
            ps.setInt(13, cdb.getRefno());
            ps.setString(14, cdb.getTaxtype());
            ps.setString(15, cdb.getPan());
            ps.setString(16, cdb.getTransport());
            ps.setInt(17, cdb.getSlno());
                
            ps.setString(18, tdata.get(i).getIname());
                System.out.println(tdata.get(i).getItemcode().toString());
            ps.setInt(19, tdata.get(i).getItemcode());
                System.out.println(tdata.get(i).getItemcode().toString());
            ps.setDouble(20, tdata.get(i).getOrirate());
            
            ps.setDouble(21, tdata.get(i).getQuan());
            ps.setDouble(22, tdata.get(i).getDis1());
            ps.setDouble(23, tdata.get(i).getUdis());
            ps.setDouble(24, tdata.get(i).getDisamt());
            ps.setDouble(25, tdata.get(i).getUcost());
            ps.setDouble(26, tdata.get(i).getTax());
            ps.setDouble(27, tdata.get(i).getUtax());
            ps.setDouble(28, tdata.get(i).getTaxamt()); 
            ps.setDouble(29, tdata.get(i).getNtval());
            ps.setDouble(30, cdb.getPostage());
            
            ps.setDouble(31, cdb.getSalestax());
            ps.setDouble(32, cdb.getOthers());
            ps.setDouble(33, cdb.getRoundof());
            ps.setString(34, cdb.getPlusminus());
                ps.setDouble(35, cdb.getNtamt());    
            //ps.setDouble(35, tdata.get(i).getNtval());
                
            ps.setString(36, cdb.getNote());
            ps.setString(37, tdata.get(i).getCate());
            ps.setString(38, tdata.get(i).getSubgr());
            ps.setString(39, cdb.getStax());
            ps.setString(40, cdb.getBank());
            
            ps.setString(41, cdb.getAccno());
            ps.setString(42, cdb.getRoute());
            ps.setString(43, cdb.getRep());
            ps.setString(44, cdb.getPhn());
            ps.setInt(45, cdb.getRid());
            ps.setString(46, cdb.getModify());
            ps.setInt(47, cdb.getOrno());
            ps.setDate(48, new java.sql.Date(cdb.getOrdate().getTime()));
            ps.setInt(49, cdb.getLrno());
           ps.setDate(50, new java.sql.Date(cdb.getLrDate().getTime()));
            
            ps.setInt(51, cdb.getWaybill());
            ps.setDate(52, new java.sql.Date(cdb.getWaydate().getTime()));
            ps.setDouble(53, cdb.getGunybags());
            ps.setDouble(54, cdb.getBundles());
                //System.out.println("gunny bags");
            ps.setDouble(55, cdb.getPacking());
            ps.setDouble(56, cdb.getAutochrg());
            ps.setString(57, cdb.getCformstatus());
            ps.setString(58, cdb.getCf());
            ps.setDouble(59, cdb.getInvvalue());
                
            ps.setString(60, tdata.get(i).getCate());            
            ps.setDouble(61, tdata.get(i).getDis2());
            ps.setDouble(62, tdata.get(i).getDis3());
                
            ps.setDouble(63, cdb.getVat5a());
            ps.setDouble(64, cdb.getVat5b());
            ps.setDouble(65, cdb.getVat14a());
            ps.setDouble(66, cdb.getVat14b());
            ps.setDouble(67, cdb.getCst2a());
            ps.setDouble(68, cdb.getCst2b());
                
            ps.setString(69, tdata.get(i).getAproduct());
            ps.setString(70, tdata.get(i).getSubgr());
            ps.setString(71, tdata.get(i).getMtcat()); 
                
                System.out.println(tdata.get(i).getTaxamt());
                
                 rows=ps.executeUpdate();
                if(rows>0){
                  
                  l= Ledger();
                  sales= Stock();
                }
                System.out.println(rows);
            
            }
           
              

            }
            if(cdb.getTaxtype().equalsIgnoreCase("CST 2%"))    {
              
                String qry="insert into cform values(?,?,?,?,?,?,?,?,?,?,?,?,?)  ";
                ps = (PreparedStatement)con.prepareStatement(qry);
                ps.setInt(1, companyid);
                ps.setInt(2,cfid);
                ps.setDate(3, new java.sql.Date(cdb.getInvdate().getTime()));
                ps.setInt(4,cdb.getBillno());
                ps.setString(5, cdb.getAccno());
                ps.setString(6, cdb.getCname());
                ps.setDouble(7,cdb.getInvvalue());
                
                ps.setString(8,"no");
                ps.setString(9, "Not Submitted");
                ps.setString(10, cdb.getInvno());
                ps.setString(11, cdb.getRep());
                ps.setString(12, cdb.getCity());
                ps.setInt(13,cdb.getRid());
               cform= ps.executeUpdate(); 
            }
        
            ps = (PreparedStatement)con.prepareStatement("UPDATE Curr_Bal SET bal =" + cdb.getCurrbal()+ " WHERE accno=" + cdb.getAcccode()+" and cmpid="+companyid+"");
            curbalup=ps.executeUpdate();
          
            ps=(PreparedStatement)con.prepareStatement("UPDATE Customer SET currbal = " + cdb.getCurrbal()+ " WHERE acccode="+cdb.getAcccode() +"");
          
            crbalu=ps.executeUpdate();
          
            ps.close();
            con.close();
            if(rows>0&&l>0 && sales>0 && curbalup>0 && crbalu>0){
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Data Successfully Stored ")); 
            }else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Data Storing failed ")); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    public int Ledger() {
           System.out.println("iam from ledger");
           System.out.println(cdb.getInvdate());
           int lno,res=0;
           st=Conn_Statement();
           try {

               //|        lno         |       ldate        |       cname        |        city        |       billno       |       invno        |        amt         |       ptype        |        bank        |        chq         |    fin_currbal     |paytype

               rs = st.executeQuery("SELECT  max(lno) FROM Ledger where cmpid="+companyid+"");

               if (rs.next()) {

                   lno = rs.getInt(1);
                   lno++;
               } else {
                   lno = 1;
               }
rs.close();
               st.close();
               st=Conn_Statement();
System.out.println("lno value is "+lno);
               String qry = "INSERT INTO Ledger VALUES ("+companyid+", "+ lno + " , '" + new java.sql.Date(cdb.getInvdate().getTime())+ "', '" + cdb.getCname()+ "','" + cdb.getCity()+ "', '" + cdb.getBillno()+ "' , '" + cdb.getInvno() + "'," + cdb.getInvvalue()+ ",0, 'Sales  ','-','Invoice' ," + cdb.getCurrbal()+ ",'DEBIT')";
                res = st.executeUpdate(qry);

            
               rs.close();
               st.close();

           } catch (Exception e) {
               e.printStackTrace();
           }

return res;
       }
    
    
    

    public int Stock()
    {
    int stock=0;
        try {
                      int count = 0;
      int rowc = tdata.size(); 
      Double sqty = 0.0;
      
    
    while (rowc > count) {
   // String iname = table.getValueAt(count, 1).toString();
        String iname = tdata.get(count).getIname();
    Double qty = tdata.get(count).getQuan();


    String qry = "SELECT quantity FROM StockSales WHERE itemname like '%"+iname+"%' and cmpid="+companyid+"";
        st=Conn_Statement();
            rs =st.executeQuery(qry);
            if(rs.next())                
            {
          sqty = sqty-qty;
          
            }
    
        st=Conn_Statement();
            qry = "UPDATE StockSales SET quantity = "+sqty+" WHERE itemname like '%"+iname+"%' and  cmpid="+companyid+"";
            
            stock = st.executeUpdate(qry);
            if(stock>0)
                System.out.println("Stock updated");
                       count++;
      
    }//while end
            rs.close();
            st.close();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    return stock;
    }
    
    public int save1() {
        Integer s;
        int j=0, k=0,l=0,m=0;
        System.out.println("in save ()");
        System.out.println("inv date ---"+cdb.getInvdate());
    String date1=df.format(cdb.getInvdate());
    System.out.println(new java.sql.Date(cdb.getInvdate().getTime()));
        Integer rows=0;
        try {


          


            String qry =
                "insert into vat_sales values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    System.out.println("Row size is ---"+tdata.size());
            for(int i=0;i<tdata.size();i++){
                if(ssid!=0){
               s=getSsid();
                }
                else{
                    s=cdb.getSid();
                }
                System.out.println("value of s is "+s);
            ps = (PreparedStatement)con.prepareStatement(qry);
            System.out.println("prepared statement object is -------" + ps);
            ps.setInt(1, companyid);
            ps.setInt(2, (s+i));
            ps.setInt(3, cdb.getBillno());
                System.out.println("Bill No is===="+cdb.getBillno());
            ps.setString(4, cdb.getAcccode().toString());
                System.out.println("cname value is"+cdb.getCname());
            ps.setString(5, cdb.getCname());
            ps.setDouble(6, cdb.getCurrbal());
            ps.setString(7, cdb.getSalestype());
            ps.setString(8, cdb.getOptype());
            ps.setString(9, cdb.getAddr());
            ps.setString(10, cdb.getCity());
            
            ps.setDate(11,new java.sql.Date(cdb.getInvdate().getTime()) );
            ps.setString(12, cdb.getInvno());
            ps.setInt(13, cdb.getRefno());
            ps.setString(14, cdb.getTaxtype());
            ps.setString(15, cdb.getPan());
            ps.setString(16, cdb.getTransport());
            ps.setInt(17, cdb.getSlno());
                
            ps.setString(18, tdata.get(i).getIname());
                System.out.println(tdata.get(i).getItemcode().toString());
            ps.setString(19, tdata.get(i).getItemcode().toString());
                System.out.println(tdata.get(i).getItemcode().toString());
            ps.setDouble(20, tdata.get(i).getOrirate());
            
            ps.setDouble(21, tdata.get(i).getQuan());
            ps.setDouble(22, tdata.get(i).getDis1());
            ps.setDouble(23, tdata.get(i).getUdis());
            ps.setDouble(24, tdata.get(i).getDisamt());
            ps.setDouble(25, tdata.get(i).getUcost());
            ps.setDouble(26, tdata.get(i).getTax());
            ps.setDouble(27, tdata.get(i).getUtax());
            ps.setDouble(28, tdata.get(i).getTaxamt()); 
            ps.setDouble(29, tdata.get(i).getNtval());
            ps.setDouble(30, cdb.getPostage());
            
            ps.setDouble(31, cdb.getSalestax());
            ps.setDouble(32, cdb.getOthers());
            ps.setDouble(33, cdb.getRoundof());
            ps.setString(34, cdb.getPlusminus());
                ps.setDouble(35, cdb.getNtamt());    
            //ps.setDouble(35, tdata.get(i).getNtval());
                
            ps.setString(36, cdb.getNote());
            ps.setString(37, tdata.get(i).getCate());
            ps.setString(38, tdata.get(i).getSubgr());
            ps.setString(39, cdb.getStax());
            ps.setString(40, cdb.getBank());
            
            ps.setString(41, cdb.getAccno());
            ps.setString(42, cdb.getRoute());
            ps.setString(43, cdb.getRep());
            ps.setString(44, cdb.getPhn());
            ps.setInt(45, cdb.getRid());
            ps.setString(46, cdb.getModify());
            ps.setInt(47, cdb.getOrno());
            ps.setDate(48, new java.sql.Date(cdb.getOrdate().getTime()));
            ps.setInt(49, cdb.getLrno());
           ps.setDate(50, new java.sql.Date(cdb.getLrDate().getTime()));
            
            ps.setInt(51, cdb.getWaybill());
            ps.setDate(52, new java.sql.Date(cdb.getWaydate().getTime()));
            ps.setDouble(53, cdb.getGunybags());
            ps.setDouble(54, cdb.getBundles());
                //System.out.println("gunny bags");
            ps.setDouble(55, cdb.getPacking());
            ps.setDouble(56, cdb.getAutochrg());
            ps.setString(57, cdb.getCformstatus());
            ps.setString(58, cdb.getCf());
            ps.setDouble(59, cdb.getInvvalue());
                
            ps.setString(60, tdata.get(i).getCate());            
            ps.setDouble(61, tdata.get(i).getDis2());
            ps.setDouble(62, tdata.get(i).getDis3());
                
            ps.setDouble(63, cdb.getVat5a());
            ps.setDouble(64, cdb.getVat5b());
            ps.setDouble(65, cdb.getVat14a());
            ps.setDouble(66, cdb.getVat14b());
            ps.setDouble(67, cdb.getCst2a());
            ps.setDouble(68, cdb.getCst2b());
                
            ps.setString(69, tdata.get(i).getAproduct());
            ps.setString(70, tdata.get(i).getSubgr());
            ps.setString(71, tdata.get(i).getMtcat()); 
                
                System.out.println(tdata.get(i).getTaxamt());
                
                 rows=ps.executeUpdate();
              /*   if(rows>0){
                    System.out.println("ledger start-------------------------------------------------------------------");
                    Ledger();
                    System.out.println("stock star start------------------------------------------------------------------");
                    Stock();
                } */
               
           
            
            }
            if(cdb.getTaxtype().equalsIgnoreCase("CST 2%"))    {
              
                String qry1="insert into cform values(?,?,?,?,?,?,?,?,?,?,?,?,?)  ";
                ps = (PreparedStatement)con.prepareStatement(qry1);
                ps.setInt(1, companyid);
                ps.setInt(2,cfid);
                ps.setDate(3, new java.sql.Date(cdb.getInvdate().getTime()));
                ps.setInt(4,cdb.getBillno());
                ps.setString(5, cdb.getAccno());
                ps.setString(6, cdb.getCname());
                ps.setDouble(7,cdb.getInvvalue());
                
                ps.setString(8,"no");
                ps.setString(9, "Not Submitted");
                ps.setString(10, cdb.getInvno());
                ps.setString(11, cdb.getRep());
                ps.setString(12, cdb.getCity());
                ps.setInt(13,cdb.getRid());
               j= ps.executeUpdate(); 
            }
            
            ps = (PreparedStatement)con.prepareStatement("UPDATE Curr_Bal SET bal =" + cdb.getCurrbal()+ " WHERE accno=" + cdb.getAcccode()+" and cmpid="+companyid+"");
            k=ps.executeUpdate();
            
            ps=(PreparedStatement)con.prepareStatement("UPDATE Customer SET currbal = " + cdb.getCurrbal()+ " WHERE acccode="+cdb.getAcccode() +"");
            
            l=ps.executeUpdate();
System.out.println("values are-----------------"+rows+"      "+j+"              "+k+"                         "+l);
            if(rows>0&&k>0&&l>0){ 
                m=1;
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return m;
    }
    
    public int StoringCurrBal() {
            int i=0,j=0,k=0,l=0;

            try {

                Double new_cbal = 0.0, old_cbal = 0.0, old_net = 0.0, new_net = 0.0;
            
               

                String qry = "SELECT * FROM  Curr_Bal WHERE accno=" + cdb.getAcccode();
                System.out.println("" + qry);
                st=Conn_Statement();
                rs = st.executeQuery(qry);
                if (rs.next()) {

                    old_cbal = rs.getDouble("bal");
                }
                rs.close();
                st.close();
                qry = "SELECT distinct(invvalue)  FROM  Vat_Sales WHERE Billno =" +cdb.getBillno();
                System.out.println("" + qry);
                st=Conn_Statement();
                rs = st.executeQuery(qry);
                if (rs.next()) {
                
                    old_net = rs.getDouble(1);
                }
rs.close();
                st.close();

                new_net = cdb.getNtamt();  


                new_cbal = (old_cbal - old_net) + new_net;

                cdb.setCurrbal(new_cbal);

              /*   qry = "DELETE FROM Vat_Sales WHERE Billno =" + cdb.getBillno();
                System.out.println("" + qry);
                st=Conn_Statement();
                i = st.executeUpdate(qry);
st.close(); */
                qry = "DELETE FROM CFORM WHERE Billno=" + cdb.getBillno();
                System.out.println("" + qry);
                st=Conn_Statement();
                 j = st.executeUpdate(qry);
st.close();
                qry = "DELETE FROM Ledger  WHERE Billno =" +cdb.getBillno();
                System.out.println("" + qry);
                st=Conn_Statement();
                k = st.executeUpdate(qry);
                st.close();
                System.out.println("====================="+i+j+k);
                if(j>0&&k>0){
                    l=1;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("-----------------------------------------------------------------------"+l);
return l;
        }
    
    public void reset() {  
        /* System.out.println("in reset()");
            RequestContext.getCurrentInstance().reset("f1"); 
            System.out.println("after reset"); */
        cdb=new CustomerDetailsBean();
        setSname("");
            selectedprofile=new TransactionTableBean();
        tdata.clear();
        bill();
        } 
    
   
    

    public void rowVal() {
        /*  UICommand cmdlink=(UICommand)ae.getComponent();
        UIData dt=(UIData)cmdlink.getNamingContainer();
        Integer ri=dt.getRowIndex(); */
        // System.out.println(ri);
        System.out.println("in select event");
        //Integer i1=(Integer)ae.getComponent().getNamingContainer().getAttributes().get("rowIndex");
        //  System.out.println(i1);

        // event.getPhaseId().getOrdinal();
        rid = ((DataTable)(FacesContext.getCurrentInstance().getViewRoot().findComponent("f1:tab"))).getRowIndex();
        System.out.println("Row index value is -------- " + rid);
    }

    public void onRowSelect1(SelectEvent event) {

        //
        if (selecteddata != null) {
            int x = selecteddata.getSs();
            /*  tablessid=(TransactionTableBean)event.getObject();
            j=tablessid.getSs(); */

            //System.out.println("j value..."+j);
        }

        String itemname = ((ItemListBean)event.getObject()).getItemname();

        //int ssid=tdata.get(0).getSs();
        System.out.println(itemname);
        String qry =
            "select ITEMCODE , ITEMRATE,TAX,itemname,Disc,CATEGORY,aproduct,`subgrp`,`MTCat`,CATEGORY   from ItemMaster WHERE ITEMNAME  like '%" +
            itemname + "%'";
        System.out.println(qry);
        j = 0;
        //  tdata1=new ArrayList<TransactionTableBean>();
        try {
            rs = st.executeQuery(qry);
            if (rs.next()) {
                // ttb = new TransactionTableBean();


                tdata.get(rid).setItemcode(rs.getInt(1));
                tdata.get(rid).setOrirate(rs.getDouble(2));
                tdata.get(rid).setTax(rs.getDouble(3));
                tdata.get(rid).setIname(rs.getString(4));
                tdata.get(rid).setDis1(rs.getDouble(5));
                tdata.get(rid).setCate(rs.getString(6));
                tdata.get(rid).setAproduct(rs.getString(7));
                tdata.get(rid).setSubgr(rs.getString(8));
                tdata.get(rid).setMtcat(rs.getString(9));
                tdata.get(rid).setCate(rs.getString(10));

                tdata.get(rid).setDis2(0.0);
                tdata.get(rid).setDis3(0.0);
                tdata.get(rid).setUcost(0.0);
                tdata.get(rid).setDisamt(0.0);
                tdata.get(rid).setUtax(0.0);
                tdata.get(rid).setTaxamt(0.0);
                tdata.get(rid).setNtval(0.0);
                tdata.get(rid).setQuan(0.0);


                /*  ttb.setItemcode(rs.getInt(1));
        ttb.setOrirate(rs.getDouble(2));
        ttb.setTax(rs.getDouble(3));
        ttb.setIname(rs.getString(4));
        ttb.setDis1(rs.getDouble(5));
        ttb.setCate(rs.getString(6));
        ttb.setAproduct(rs.getString(7));
        ttb.setSubgr(rs.getString(8));
        ttb.setMtcat(rs.getString(9));

        ttb.setDis2(0.0);
        ttb.setDis3(0.0);
        ttb.setUdis(0.0);
        ttb.setUcost(0.0);
        ttb.setDisamt(0.0);
        ttb.setUtax(0.0);
        ttb.setTaxamt(0.0);
        ttb.setNtval(0.0);
        ttb.setQuan(0.0);

        tdata1.add(ttb);
         */
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //tdata=tdata1;

    }
    
    public void onRowSelect2(SelectEvent event) {
        salesidlist=new ArrayList();
        selectedsb=(SearchBean)event.getObject();
        System.out.println(selectedsb.getRefno());
        Integer refno=selectedsb.getRefno();
        tdata=new ArrayList<TransactionTableBean>();
       int z=0;
        TransactionTableBean searchttb;
        String qry="select * from vat_sales where reffno="+refno+"";
        try{
        st=Conn_Statement();
        rs=st.executeQuery(qry);
            while(rs.next()){
               
                System.out.println("zvalue is"+z);
                cdb=new CustomerDetailsBean();
                searchttb=new TransactionTableBean();
                cdb.setCmpid(rs.getInt(1));
                cdb.setSid(rs.getInt(2));
                if(z==0){
                    System.out.println("inside if");
                   
                       
                     ssid=rs.getInt(2);
                     System.out.println("svalue in save ()"+ssid);
                    
                }
                cdb.setBillno(rs.getInt(3));
                cdb.setAcccode(rs.getInt(4));
                setSname(rs.getString(5));
             cdb.setCname(rs.getString(5));
                cdb.setCurrbal(rs.getDouble(6));
                cdb.setSalestype(rs.getString(7));
                
                String opt = rs.getString(8);
                            if (opt.equalsIgnoreCase("CREDIT")) {
                                cdb.setOptype("Credit");
                            } else {
                                cdb.setOptype("Debit");
                            }
                
                //cdb.setOptype(rs.getString(8));
                cdb.setAddr(rs.getString(9));
                cdb.setCity(rs.getString(10));
                cdb.setInvdate(rs.getDate(11));
                cdb.setInvno(rs.getString(12));
                cdb.setRefno(rs.getInt(13));
                cdb.setTaxtype(rs.getString(14));
                cdb.setPan(rs.getString(15));
                cdb.setTransport(rs.getString(16));
                cdb.setSlno(rs.getInt(17));
                searchttb.setSs(z);
                searchttb.setIname(rs.getString(18));
                
                searchttb.setItemcode((rs.getInt(19)));
                System.out.println("itemcode values are------"+searchttb.getItemcode()+"from db-----"+rs.getInt(19));
                searchttb.setOrirate(rs.getDouble(20));
                searchttb.setQuan(rs.getDouble(21));
                searchttb.setDis1(rs.getDouble(22));
                searchttb.setUdis(rs.getDouble(23));
                searchttb.setDisamt(rs.getDouble(24));
                searchttb.setUcost(rs.getDouble(25));
                searchttb.setTax(rs.getDouble(26));
                searchttb.setUtax(rs.getDouble(27));
                searchttb.setTaxamt(rs.getDouble(28));                
                searchttb.setNtval(rs.getDouble(29));
                
                searchttb.setCate(rs.getString(60));
                searchttb.setDis2(rs.getDouble(61));
                searchttb.setDis3(rs.getDouble(62));
               
                searchttb.setAproduct(rs.getString(69));
                searchttb.setSubgr(rs.getString(70));
                searchttb.setMtcat(rs.getString(71));
                
                cdb.setPostage(rs.getDouble(30));
                cdb.setSalestax(rs.getDouble(31));
                cdb.setOthers(rs.getDouble(32));
                cdb.setRoundof(rs.getDouble(33));
                cdb.setPlusminus(rs.getString(34));
                cdb.setNtamt(rs.getDouble(35));
                
                cdb.setNote(rs.getString(36));
                cdb.setCate(rs.getString(37));
                cdb.setSubcat(rs.getString(38));
                cdb.setStax(rs.getString(39));
                cdb.setBank(rs.getString(40));
                cdb.setAccno(rs.getString(41));
                cdb.setRoute(rs.getString(42));
                cdb.setRep(rs.getString(43));
                cdb.setPhn(rs.getString(44));
                cdb.setRid(rs.getInt(45));
                cdb.setModify(rs.getString(46));
                cdb.setOrno(rs.getInt(47));
                cdb.setOrdate(rs.getDate(48));
                cdb.setLrno(rs.getInt(49));
                cdb.setLrDate(rs.getDate(50));
                cdb.setWaybill(rs.getInt(51));
                cdb.setWaydate(rs.getDate(52));
                cdb.setGunybags(rs.getDouble(53));
                cdb.setBundles(rs.getDouble(54));
                cdb.setPacking(rs.getDouble(55));
                cdb.setAutochrg(rs.getDouble(56));
                cdb.setCformstatus(rs.getString(57));
                cdb.setCf(rs.getString(58));
                cdb.setInvvalue(rs.getDouble(59));
                
                
                cdb.setVat5a(rs.getDouble(63));
                cdb.setVat5b(rs.getDouble(64));
                cdb.setVat14a(rs.getDouble(65));
                cdb.setVat14b(rs.getDouble(66));
                cdb.setCst2a(rs.getDouble(67));
                cdb.setCst2b(rs.getDouble(68));
                
                tdata.add(searchttb);
                
                z++;
            }
       
            
            
            
            
            
            
            }catch(Exception e){
                e.printStackTrace();
            }
        cal2();
    }
    public void evet(){
        System.out.println("insidew event");
    }
    public void update()  {
        int stcurrstatus=0,k=0;
            try{
                
            stcurrstatus  =StoringCurrBal();
        st=Conn_Statement();
        System.out.println("reff no----------"+cdb.getBillno());
        String qry="delete from vat_sales where Billno="+cdb.getBillno() +"";
         int m=st.executeUpdate(qry);
              
                System.out.println("m value is");
       
                if(m>0&&stcurrstatus>0){
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Preparation for updation sucessfully ")); 
                }
                st.close();
            System.out.println("m value is ----"+m);
             k=save1();
                    if(k>0){
                        System.out.println("ledger start-------------------------------------------------------------------");
                        Ledger();
                        System.out.println("stock star start------------------------------------------------------------------");
                        Stock();
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("The Data is Updated Successfully ")); 
                    }
                    else{
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("Data  Updation Failed "));  
                    }
            con.close();
        }catch(Exception e){
           
            e.printStackTrace();
            
        } 
       ssid=0;
        
    }
    
    public void searchCust(){
        st=Conn_Statement();
        String qry="select distinct reffno,acname from vat_sales";
        sblist1=new ArrayList<SearchBean>();
        try{
        
        rs=st.executeQuery(qry);
            while(rs.next()){
                sb=new SearchBean();
                sb.setRefno(rs.getInt(1));
                sb.setCname(rs.getString(2));
                sblist1.add(sb) ;
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void delete() {
        System.out.println("delete mwtgflknadflkqwhdfr");
        
        
        try {
            // System.out.println("befire remobing"+tdata.size() + "the tb name is" + tdata.get(0).getIname());
            //  System.out.println( getSelectedprofile());
            // System.out.println(getSelectedttb().getIname());
            if (tdata.size() <= 0) {

                //System.out.println(getTdata().size());
                //               for(int i=0;i<getTdata().size();i++) {
                //                    TransactionTableBean temp=tdata.get(i);
                //
                //
                //                   if(temp.getIname().equalsIgnoreCase(selectedprofile.getIname())) {
                //                        System.out.println("the deleted valu is"+selectedprofile.getIname());
                //
                //                    getTdata().remove(selectedprofile);
                //
                //                         }
                //               }
               System.out.println("in if inside");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("no records to delete", new FacesMessage("no records to delete"));

            }

            if (selectedprofile != null) {
                System.out.println("in fhwfkjhefoiljfew");
                //System.out.println(tdata.size());
                // System.out.println(((TransactionTableBean)getSelectedprofile().getRowData()).getIname());
                System.out.println("size            " + tdata.size());
                if (tdata.size() <= 0) {

                    System.out.println("inside else");
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage("no records to delete", new FacesMessage("no records to delete"));

                } else {

                    for (int i = 0; i < getTdata().size(); i++) {
                        TransactionTableBean temp = tdata.get(i);


                        if (temp.getIname().equalsIgnoreCase(selectedprofile.getIname())) {
                            System.out.println("the deleted valu is" + selectedprofile.getIname());

                            getTdata().remove(selectedprofile);

                        }
                    }


                }


            }
            /* else{
                System.out.println("in else inside");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("no records to delete", new FacesMessage("no records to delete"));

            } */

        } catch (Exception e) {
            e.printStackTrace();
        }
        cal2();


        /*    //   System.out.println(selectedttb.getIname());
        //         List<TransactionTableBean> templist=new ArrayList<TransactionTableBean>();

        for (int i = 0; i < getTdata().size(); i++) {
            if (getTdata().get(i).getIname().equalsIgnoreCase(getSelectedprofile().getIname())) {
                //  templist.add(tdata.get(i));
                getTdata().remove(i);
            }
        } */


    }

    public void setCdata(List<CustomerBean> cdata) {
        this.cdata = cdata;

    }

    public List<CustomerBean> getCdata() {
        return cdata;
    }

    public void setSelectedcust(CustomerBean selectedcust) {
        this.selectedcust = selectedcust;

    }

    public CustomerBean getSelectedcust() {
        // System.out.println(selectedcust);
        return selectedcust;

    }

    public void setB1(Boolean b1) {
        this.b1 = b1;
    }

    public Boolean getB1() {
        return b1;
    }

    public void setB2(Boolean b2) {
        this.b2 = b2;
    }

    public Boolean getB2() {
        return b2;
    }

    public void setB3(Boolean b3) {
        this.b3 = b3;
    }

    public Boolean getB3() {
        return b3;
    }

    public void setCdata1(List<CustomerBean> cdata1) {
        this.cdata1 = cdata1;
    }

    public List<CustomerBean> getCdata1() {
        return cdata1;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSname() {
        return sname;
    }

    public void setCdb(CustomerDetailsBean cdb) {
        this.cdb = cdb;
    }

    public CustomerDetailsBean getCdb() {
        return cdb;
    }

    public void setTaxlist(List taxlist) {
        this.taxlist = taxlist;
    }

    public List getTaxlist() {
        return taxlist;
    }


    public void setSelectedttb(TransactionTableBean selectedttb) {
        this.selectedttb = selectedttb;
    }

    public TransactionTableBean getSelectedttb() {
        return selectedttb;
    }


    public void setIbdata(List<ItemListBean> ibdata) {
        this.ibdata = ibdata;
    }

    public List<ItemListBean> getIbdata() {
        return ibdata;
    }

    public void setIbdata1(List<ItemListBean> ibdata1) {
        this.ibdata1 = ibdata1;
    }

    public List<ItemListBean> getIbdata1() {
        return ibdata1;
    }

    public void setSelectedItem(ItemListBean selectedItem) {
        this.selectedItem = selectedItem;
    }

    public ItemListBean getSelectedItem() {
        return selectedItem;
    }

    public void setTdata1(List<TransactionTableBean> tdata1) {
        this.tdata1 = tdata1;
    }

    public List<TransactionTableBean> getTdata1() {
        return tdata1;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getJ() {
        return j;
    }

    public void setSelecteddata(TransactionTableBean selecteddata) {
        this.selecteddata = selecteddata;
    }

    public TransactionTableBean getSelecteddata() {
        return selecteddata;
    }

    public void setTdata(List<TransactionTableBean> tdata) {
        this.tdata = tdata;
    }

    public List<TransactionTableBean> getTdata() {
        return tdata;
    }


    public void setSelectedprofile(TransactionTableBean selectedprofile) {
        this.selectedprofile = selectedprofile;
    }

    public TransactionTableBean getSelectedprofile() {
        return selectedprofile;
    }

    public void setInm(String inm) {
        this.inm = inm;
    }

    public String getInm() {
        return inm;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setSb(SearchBean sb) {
        this.sb = sb;
    }

    public SearchBean getSb() {
        return sb;
    }

    public void setSelectedsb(SearchBean selectedsb) {
        this.selectedsb = selectedsb;
    }

    public SearchBean getSelectedsb() {
        return selectedsb;
    }

    public void setSblist1(List<SearchBean> sblist1) {
        this.sblist1 = sblist1;
    }

    public List<SearchBean> getSblist1() {
        return sblist1;
    }

    public void setSblist2(List<SearchBean> sblist2) {
        this.sblist2 = sblist2;
    }

    public List<SearchBean> getSblist2() {
        return sblist2;
    }

    public void setSalesidlist(List salesidlist) {
        this.salesidlist = salesidlist;
    }

    public List getSalesidlist() {
        return salesidlist;
    }

    public void setSsid(Integer ssid) {
        this.ssid = ssid;
    }

    public Integer getSsid() {
        return ssid;
    }


  /* public class RowCounter implements Serializable {

        private transient int row = 0;

        public int getRow() {
            return ++row;
        }

    } */
    public String NumToWord(String  a)
    {
    System.out.println("A value is \t"+a);
    amount="";
    //convert(a);
    String str2 ="";
           
    //                Scanner input = new Scanner(System.in);
    //                System.out.print("Enter Number: ");
           
          // String amt=input.next();
           String amt=a;
          System.out.println(amt+"\t amount from main");
          
          
    int rupees = Integer.parseInt(amt.split("\\.")[0]);
    String str1 =  convert(rupees);
    str1 += " Rupees & ";
    int paise = Integer.parseInt(amt.split("\\.")[1]);
    if(paise!=0){
    str2 += " and";
    str2 = convert(paise);
    str2 += " Paise";
    }
          
    
    String nw = str1+str2+" Only";
    System.out.println(""+nw);
           //System.out.println(str1+str2+" Only");
    return nw;
    }
    
    public String convert(int number) {
           int n = 1;
           int word;
           string = "";
           System.out.println(number+"\t number");
           
           while (number != 0) {
                   switch (n) {
                   case 1:
                           word = number % 100;
                           pass(word);
                           if (number > 100 && number % 100 != 0) {
                                   show("and ");
                           }
                           number /= 100;
                           break;

                   case 2:
                           word = number % 10;
                           if (word != 0) {
                                   show(" ");
                                   show(st2[0]);
                                   show(" ");
                                   pass(word);
                           }
                           number /= 10;
                           break;

                   case 3:
                           word = number % 100;
                           if (word != 0) {
                                   show(" ");
                                   show(st2[1]);
                                   show(" ");
                                   pass(word);
                           }
                           number /= 100;
                           break;

                   case 4:
                           word = number % 100;
                           if (word != 0) {
                                   show(" ");
                                   show(st2[2]);
                                   show(" ");
                                   pass(word);
                           }
                           number /= 100;
                           break;

                   case 5:
                           word = number % 100;
                           if (word != 0) {
                                   show(" ");
                                   show(st2[3]);
                                   show(" ");
                                   pass(word);
                           }
                           number /= 100;
                           break;

                   }
                   n++;
           }
           System.out.println("String \t"+string);
           return string;
    }

    public void pass(int number) {
           int word, q;
           if (number < 10) {
                   show(st1[number]);
           }
           if (number > 9 && number < 20) {
                   show(st3[number - 10]);
           }
           if (number > 19) {
                   word = number % 10;
                   if (word == 0) {
                           q = number / 10;
                           show(st4[q - 2]);
                   } else {
                           q = number / 10;
                           show(st1[word]);
                           show(" ");
                           show(st4[q - 2]);
                   }
           }
    }

    public void show(String s) {
           String st;
           st = string;
           string = s;
           string += st;
    }

}
