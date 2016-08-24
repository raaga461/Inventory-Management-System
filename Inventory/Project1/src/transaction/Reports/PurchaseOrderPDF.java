package transaction.Reports;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.awt.Desktop;

import java.io.File;

import java.sql.DriverManager;

import java.sql.ResultSet;

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

public class PurchaseOrderPDF {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private Logo ob;
    public PurchaseOrderPDF() {
        super();
    }
    public void execute(String ite, String invdate, String nw,String term)   {
           
        
            File f = new File(".");
           System.out.println("" + f.getAbsolutePath());
           ServletContext ctx = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
           String realPath = ctx.getRealPath("/");
           String reportSource =realPath+"\\reports\\PurchaseOrderPDF.jrxml";
             String reportDest = "D:\\SSI REPORTS\\PurchaseOrderPDF.PDF";

        ob=new Logo();
              Map<String, Object> params =ob.LogoMethod();
              System.out.println("params value is"+params);
               params.put("ite",ite);
           params.put("from",invdate);
           params.put("nw",nw);
           params.put("term",term);
        
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
            Desktop.getDesktop().open(new File("D:\\SSI REPORTS\\PurchaseOrderPDF.PDF"));
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
}
