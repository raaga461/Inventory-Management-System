package ReportsModule.PDFReports;

import ReportsModule.BeanPakage.SalesInvoiceTableBean;


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

public class SalesInvoicePDF {
    public SalesInvoicePDF() {
        super();
    }

    public void execute(String sType, String repType, String rid, String city, String from, String to,
                        List<SalesInvoiceTableBean> tdata, Double amount) {
        File f = new File(".");
        System.out.println("primt--------------------------" + tdata);
        ServletContext ctx = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath = ctx.getRealPath("/");
        String reportSource =
            realPath+"\\reports\\SalesTheBigReportOfSalesInvPDF.jrxml";
        //  String reportSource = "C:\\Users\\USER\\Documents\\NetBeansProjects\\SSI\\src\\PDFReports\\SalesDatesPDF.jrxml";
        String reportDest = "D:\\SSI REPORTS\\SalesInvoicePDF.PDF";
        Map<String, Object> params = Logo.LogoMethod();

        params.put("stype", sType);
        params.put("reptype", repType);
        params.put("rid", rid);
        params.put("city", city);
        params.put("from", from);
        params.put("to", to);
        params.put("amount", amount);
        try {
            System.out.println("m1----------------------------------------");
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            System.out.println(" JasperCompileManager.compileReport(reportSource);");
            Connection conn = DBConnection.connection();
            System.out.println("m2----------------------------------------");
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Conn");
            }
            System.out.println("m3----------------------------------------");
            JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(tdata,false));
            System.out.println("m4----------------------------------------");
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportDest);

          Desktop.getDesktop().open(new File("D:\\SSI REPORTS\\SalesInvoicePDF.PDF"));
            //JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
            System.out.println("m1" + e.getMessage());

        } // Exception handling for the Class.forName method.
        catch (Exception ex) {
            System.out.println(("m2" + ex.getMessage()));
            JOptionPane.showMessageDialog(null, "Error in report" + ex.getMessage());
        }

        // Exception handling for the DriverManager.getConnection method.
    }
}
