package transaction.Reports;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.awt.Desktop;

import java.io.File;

import java.sql.DriverManager;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;

import javax.servlet.ServletContext;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class InvoiceSalesSingle {
    private Statement st;
    private ResultSet rs;
    private Connection con;

  
    public InvoiceSalesSingle() {
        super();
    }
    
    public void execute(String ite, String invdate, String nw)   {
           
        
            File f = new File(".");
           System.out.println("" + f.getAbsolutePath());
           ServletContext ctx = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
           String realPath = ctx.getRealPath("/");
           String reportSource =realPath+"\\reports\\SalesInvoicePDF.jrxml";
             String reportDest = "D:\\SSI REPORTS\\SalesInvoicePDF.PDF";

        
              Map<String, Object> params =LogoMethod();
              System.out.println("params value is"+params);
               params.put("ite",ite);
           params.put("from",invdate);
           params.put("nw",nw);
        
           System.out.println("params value is"+params);
             try {
               JasperReport jasperReport =
                       JasperCompileManager.compileReport(reportSource);
                 System.out.println(" JasperCompileManager.compileReport(reportSource);");

              

               Connection conn = connection();
                 System.out.println("Connection conn = DBConnection.DBConnection.connection();");
                 
                 
                 
               JasperPrint jasperPrint =
                       JasperFillManager.fillReport(
                       jasperReport, params, conn);
               
                 System.out.println("asperPrint jasperPrint =");

            JasperExportManager.exportReportToPdfFile(jasperPrint, reportDest);
                
          
             
                 System.out.println("xportReportToPdfFile......");
            Desktop.getDesktop().open(new File("D:\\SSI REPORTS\\SalesInvoicePDF.PDF"));
     //    JasperViewer.viewReport(jasperPrint, false);
           } catch (JRException e) {
               System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null,"Error in report"+e.getMessage());
           } // Exception handling for the Class.forName method.
           catch (Exception ex) {
               System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(null,"Error in report"+ex.getMessage());
                ex.printStackTrace();
           }

           // Exception handling for the DriverManager.getConnection method.

       }
       
         public void executeText(String ite, String invdate)   {
           
        
            File f = new File(".");
           System.out.println("" + f.getAbsolutePath());
           String pp = f.getAbsolutePath();
           int n = pp.length();
           System.out.println(pp.substring(0, n - 1));
           String pp2 = pp.substring(0, n - 1);
           System.out.println("--->"+pp2);
           String reportSource = pp2 + "src\\PDFReports\\SalesInvoicePDF.jrxml";
             String reportDest = "D:\\SSI REPORTS\\SalesInvoiceHTML.html";

        
              Map<String, Object> params =LogoMethod();
               params.put("ite",ite);
           params.put("from",invdate);
        

             try {
               JasperReport jasperReport =
                       JasperCompileManager.compileReport(reportSource);
                 System.out.println(" JasperCompileManager.compileReport(reportSource);");

              

               Connection conn = connection();
                 System.out.println("Connection conn = DBConnection.DBConnection.connection();");
                 
                 
                 
               JasperPrint jasperPrint =
                       JasperFillManager.fillReport(
                       jasperReport, params, conn);
               
                 System.out.println("asperPrint jasperPrint =");

              // JasperExportManager.exportReportToPdfFile(jasperPrint, reportDest);
                 JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
                 
                  
                 System.out.println("xportReportToPdfFile......");
               JasperViewer.viewReport(jasperPrint, false);
           } catch (JRException e) {
               System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null,"Error in report"+e.getMessage());
           } // Exception handling for the Class.forName method.
           catch (Exception ex) {
               System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(null,"Error in report"+ex.getMessage());
           }

           // Exception handling for the DriverManager.getConnection method.

       }
         
    public static Map<String, Object>  LogoMethod()
       {
       Map<String, Object> params = new HashMap<String, Object>();
       File f = new File(".");
       
            params.put("parameter1", "TRADE MARK REGD. COMPANY SINCE: 22-6-1987");
            params.put("parameter2", " SAMBASIVA DISTRIBUTORS");
            params.put("parameter3", "Plot No 192, Block 9, 5th Road,J.Auto Nagar, Vijayawada - 520007 ");
            params.put("parameter4", "PHONE: (0866) 2543625 , MOBILE: 9963296777,9000659999");
            params.put("parameter5", "E-Mail : anne9.ssd@gmail.com");
            params.put("parameter6", "TIN NO : 28960189862");
            params.put("parameter7", "TM");
            params.put("parameter8", "IFS CODE: ANDB000714  ,  A/C NO: 071413046000071");
           ServletContext ctx = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
           String realPath = ctx.getRealPath("/");
           //System.out.println("the realpathis" + realPath + "images" + File.separator + "ssi-logo.jpg");
           //tring img = f.getAbsolutePath().substring(0, f.getAbsolutePath().length() - 1) + "images\\ssi-logo.jpg";
         String  img = realPath+"\\images\\ssi-logo.jpg";
           //System.out.println("---->img path"+img);
        //   params.put("parameter9", "C:\\Users\\USER\\AppData\\Roaming\\JDeveloper\\system11.1.2.4.39.64.36.1\\o.j2ee\\drs\\Application2\\Project1WebApp.war\\images\\ssi-logo.jpg");
                     params.put("parameter9", img);
                        return  params;
       
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
     
       public static void main(String[] args) {
           
           InvoiceSales ob = new InvoiceSales();
    //ob.execute("CST /6","13-Jun-13");
       }
     
    
    
    
}

