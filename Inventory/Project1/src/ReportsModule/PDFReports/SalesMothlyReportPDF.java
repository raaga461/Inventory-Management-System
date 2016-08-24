package ReportsModule.PDFReports;


import ReportsModule.BeanPakage.SalesMonthlyReportTableBean;

import ReportsModule.DBConnection.DBConnection;

import com.mysql.jdbc.Connection;

import java.awt.Desktop;

import java.io.File;

import java.util.Collection;
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


public class SalesMothlyReportPDF { 
    public SalesMothlyReportPDF() {
        super();
    }
    
    public void execute(String ite,String fromdate, String todate,List<SalesMonthlyReportTableBean> tdata,Double gros,Double stax,Double pos,Double oto, Double otr,Double ron,Double amount)  {
        File f = new File(".");
        ServletContext ctx = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath = ctx.getRealPath("/");
        String reportSource =
           realPath+"\\reports\\SalesMothlyReportPDF.jrxml";
        //  String reportSource = "C:\\Users\\USER\\Documents\\NetBeansProjects\\SSI\\src\\PDFReports\\SalesDatesPDF.jrxml";
        String reportDest = "D:\\SSI REPORTS\\SalesMonthlyReportPDF.PDF";
        Map<String, Object> params = Logo.LogoMethod();
        params.put("ite", ite);
        params.put("from", fromdate);
        params.put("to", todate);
        params.put("gros", gros);
        params.put("stax", stax);
        params.put("pos", pos);
        params.put("oto", oto);
        params.put("otr", otr);
        params.put("ron", ron);
        params.put("amt", amount);
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            System.out.println(" JasperCompileManager.compileReport(reportSource);");
            Connection conn = DBConnection.connection();
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Conn");
            }
            JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, params,new JRBeanCollectionDataSource((Collection)tdata) );
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportDest);
            Desktop.getDesktop().open(new File("D:\\SSI REPORTS\\SalesMonthlyReportPDF.PDF"));
            //JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error in report" + e.getMessage());
        } // Exception handling for the Class.forName method.
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error in report" + ex.getMessage());
        }

        // Exception handling for the DriverManager.getConnection method.

    }
}
    
    
    
    
    
    
    

