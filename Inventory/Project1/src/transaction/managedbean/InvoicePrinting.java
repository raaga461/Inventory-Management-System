package transaction.managedbean;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;



import java.sql.DriverManager;

import java.sql.ResultSet;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


import org.apache.commons.digester.annotations.rules.FactoryCreate;

import org.apache.taglibs.standard.lang.jstl.test.beans.Factory;

import org.primefaces.event.SelectEvent;


import org.primefaces.model.StreamedContent;

import transaction.Reports.InvoiceSales;

import transaction.Reports.InvoiceSalesSingle;

import transaction.beanpackage.InvoicePrintingBean;

import transaction.numbertoword.NumToWords;

@ManagedBean(name = "invoicep", eager = true)
@ViewScoped


public class InvoicePrinting {

        
    private String dates;
    private Date d;
    private InvoicePrintingBean ipb;
    private int companyide=1;
    private String invoicetype;
    private String salestype;
    private String invno;
    private StreamedContent file;
    private byte[] bfile;
    private List saleslist=new ArrayList();
    
    private List invoiceList=new ArrayList();
  
    static Connection con;
    static Statement st;
    static ResultSet rs;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    
 
    public InvoicePrinting() {
        super();
        Date todaydate=new Date();
        String todate=df.format(todaydate);
        try{
            d=df.parse(todate);
        }catch(Exception e){
            e.printStackTrace();
        }
        ipb=new InvoicePrintingBean();
        setInvoicetype("Select");
        setSalestype("All");
        TaxMehod();
     
    }

  
  
   /*  static {
        connection();
        Conn_Statement();
    } */
    public     Statement Conn_Statement() {

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


      public   Connection connection() {

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
    
   
    public void TaxMehod()
        {
          

            saleslist.clear();
            st=Conn_Statement();
            try {
                saleslist.add("All");
                String qry = "SELECT taxname FROM Tax where cmpid="+companyide+" ";
                System.out.println("Sales Method            :   "+qry);
                rs = st.executeQuery(qry);
                while(rs.next())
                {
                saleslist.add(rs.getString(1));
                }
                rs.close();
                st.close();
                con.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        
        }

    public void dateSelect(SelectEvent event) {
    setD((Date)event.getObject());
    
       System.out.println(dates);
        invNo();
    }
  
    public void taxselect(){
        
    //    invoicetype=(String)(evt.getComponent());
        System.out.println("invoice value is "+salestype);
       
        invNo();
    }
    
    public void invoiceselect(){
        
    //    invoicetype=(String)(evt.getComponent());
        System.out.println("invoice value is "+invoicetype);
        invNo();
    }
    
    
    public void print(){
        String nw =new NumToWords().NumToWord(ipb.getInvval().toString());
            InvoiceSalesSingle ob = new  InvoiceSalesSingle();
       ob.execute(getInvno(),df.format(d) ,nw);
    //    file = new DefaultStreamedContent(bfile, "image/jpg", "downloaded_optimus.jpg");  
    }
  
    public void  invNo()
        {
     System.out.println("inside invoice function");
        st=Conn_Statement();
        System.out.println("invoice function");
      dates=df.format(d);
        System.out.println(invoicetype);
        System.out.println(dates);
        System.out.println(salestype);
            invoiceList.clear();
            try {
                
                String qry = "";
                
                String ite =salestype;
                if(invoicetype.equalsIgnoreCase("Select")){
                    FacesContext context=FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Please Select Invoice Type"));
                }else{
                if(invoicetype.equalsIgnoreCase("Sales Invoice")){
                    
                if(ite.equalsIgnoreCase("All"))
                {
                
                 qry = "SELECT   distinct   invno  FROM Vat_Sales WHERE  invdate between '"+dates+"' AND '"+dates+"' ";
                } 
                else
                {
                 qry = "SELECT  distinct   invno  FROM Vat_Sales WHERE taxtype like  '%"+salestype+"%' and invdate between '"+dates+"' AND '"+dates+"'";
                }
                }
                if(invoicetype.equalsIgnoreCase("Purchase Invoice")){
                    
                if(ite.equalsIgnoreCase("All"))
                {
                
                 qry = "SELECT   distinct   invno  FROM Vat_Purchase WHERE  invdate between '"+dates+"' AND '"+dates+"' ";
                }
                else
                {
                 qry = "SELECT  distinct   invno  FROM Vat_Purchase WHERE taxtype like  '%"+salestype+"%' and invdate between '"+dates+"' AND '"+dates+"'";
                }
                }
                
                System.out.println("Sales Method            :   "+qry);
                
      rs = st.executeQuery(qry);
                
                while(rs.next())
                {
                
                    invoiceList.add(rs.getString(1));
                }
                    rs.close();
                    st.close();
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
          
        }

    
    public void invchange(){
        GetData();
    }

  

    public  void GetData()
    {
      System.out.println("inside getdata function");
     
        String qry="";
        st=Conn_Statement();
        try {
            
          if(getInvno()!=null)
          {
            
            if(getInvoicetype().equalsIgnoreCase("Sales Invoice")){
                qry= "SELECT  reffno,billtype,acname,addr,city,invvalue FROM Vat_Sales WHERE invno like '%"+getInvno()+"%'";
              
            }
            if(getInvoicetype().equalsIgnoreCase("Purchase Invoice")){
                qry= "SELECT  reffno,billtype,acname,addr,city,invvalue FROM Vat_Purchase WHERE invno like '%"+getInvno()+"%'";
            }
            System.out.println("get data query"+qry);
              rs = st.executeQuery(qry);
              if(rs.next())
              {
              ipb.setReffno(rs.getInt(1));
              ipb.setBilltype(rs.getString(2));
              ipb.setCname(rs.getString(3));
              ipb.setAddress(rs.getString(4));
              ipb.setCity(rs.getString(5));
              ipb.setInvval(rs.getDouble(6));    
              }
          }
            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
           
        }

    }
    

    public void printall(){
        try {
                 
               int whcnt=0;
                 int cnt = invoiceList.size();
                 System.out.println("Items"+cnt);
                 
                 while(cnt>0)
                 {
                   cnt--;
                 String invo = invoiceList.get(cnt).toString();
                 System.out.println("Items"+cnt+"    :   "+invo);
               
                 
                    String nw =new NumToWords().NumToWord((ipb.getInvval().toString()));
           InvoiceSales ob = new  InvoiceSales();
        ob.execute(invo, df.format(getD()),nw,cnt);
                 
                 
                 }
                 
                 
             } catch (Exception e) {
                 e.printStackTrace();
             }
    }
    
    
    
  

    public void setInvoicetype(String invoicetype) {
        
        this.invoicetype = invoicetype;
    }

    public String getInvoicetype() {
        return invoicetype;
    }

    public void setSalestype(String salestype) {
        this.salestype = salestype;
    }

    public String getSalestype() {
        return salestype;
    }

    public void setInvno(String invno) {
        this.invno = invno;
    }

    public String getInvno() {
        return invno;
    }

    public void setSaleslist(List saleslist) {
        this.saleslist = saleslist;
    }

    public List getSaleslist() {
        return saleslist;
    }

    public void setInvoiceList(List invoiceList) {
        this.invoiceList = invoiceList;
    }

    public List getInvoiceList() {
        return invoiceList;
    }

    public void setCompanyide(int companyide) {
        this.companyide = companyide;
    }

    public int getCompanyide() {
        return companyide;
    }

    public void setIpb(InvoicePrintingBean ipb) {
        this.ipb = ipb;
    }

    public InvoicePrintingBean getIpb() {
        return ipb;
    }


    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getDates() {
        return dates;
    }

    public void setD(Date d) {
        this.d = d;
    }

    public Date getD() {
        return d;
    }

 
}
