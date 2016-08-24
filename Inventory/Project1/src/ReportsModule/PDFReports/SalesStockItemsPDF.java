package ReportsModule.PDFReports;


import ReportsModule.BeanPakage.SalesStockTableBean;

import ReportsModule.DBConnection.DBConnection;

import com.mysql.jdbc.Connection;

import java.awt.Desktop;

import java.io.File;

import java.util.List;
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
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


public class SalesStockItemsPDF {
    
    public void execute(List<SalesStockTableBean> tdata) {
       
        File f = new File(".");
        System.out.println("primt"+tdata);
        ServletContext ctx = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath = ctx.getRealPath("/");
        String reportSource =realPath+"\\reports\\StockSalesItemPDF.jrxml";
        System.out.println("-------------->"+reportSource);
            //"C:\\Users\\USER\\AppData\\Roaming\\JDeveloper\\system11.1.2.4.39.64.36.1\\o.j2ee\\drs\\Application2\\Project1WebApp.war\\reports\\SalesTheBigReportOfSalesInvPDF.jrxml";
        //  String reportSource = "C:\\Users\\USER\\Documents\\NetBeansProjects\\SSI\\src\\PDFReports\\SalesDatesPDF.jrxml";
        String reportDest = "D:\\SSI REPORTS\\StockSalesItemPDF.PDF";
        Map<String, Object> params = Logo.LogoMethod();
       
        //params.put("head", "SalesItemsCurrentStock");
        
        
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            System.out.println(" JasperCompileManager.compileReport(reportSource);");
            Connection conn = DBConnection.connection();
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Conn");
            }
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(tdata));
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportDest);
        
            Desktop.getDesktop().open(new File("D:\\SSI REPORTS\\StockSalesItemPDF.PDF"));
            //JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
            System.out.println("m1"+e.getMessage());
          
        } // Exception handling for the Class.forName method.
        catch (Exception ex) {
            System.out.println(("m2"+ex.getMessage()));
            JOptionPane.showMessageDialog(null, "Error in report" + ex.getMessage());
        }

        // Exception handling for the DriverManager.getConnection method.
    }
}
