package ReportsModule.PDFReports;

import ReportsModule.BeanPakage.SalesRepresentChartDataBean;

import ReportsModule.DBConnection.DBConnection;

import com.mysql.jdbc.Connection;
import java.awt.Desktop;

import java.io.File;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import javax.servlet.ServletContext;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class SalesChartByRepwisePDF {
    public SalesChartByRepwisePDF() {
        super();
    }

    public void execute(String fromdate, String todate, String ite, String rep, List<SalesRepresentChartDataBean>chartData) {
        File f = new File(".");
        ServletContext ctx = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath = ctx.getRealPath("/");
        String reportSource =realPath+"\\reports\\SalesChartbyRepwise.jrxml";
          //  "C:\\Users\\USER\\AppData\\Roaming\\JDeveloper\\system11.1.2.4.39.64.36.1\\o.j2ee\\drs\\Application2\\Project1WebApp.war\\reports\\SalesChartbyRepwise.jrxml";
        //  String reportSource = "C:\\Users\\USER\\Documents\\NetBeansProjects\\SSI\\src\\PDFReports\\SalesDatesPDF.jrxml";
        String reportDest = "D:\\SSI REPORTS\\SalesChartByRepresentative.PDF";
        Map<String, Object> params = Logo.LogoMethod();
        params.put("from", fromdate);
        params.put("to", todate);
        params.put("ite", ite);
        params.put("re", rep);
       

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            System.out.println(" JasperCompileManager.compileReport(reportSource);");
            Connection conn = DBConnection.connection();
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Conn");
            }
         JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(chartData));
        JasperExportManager.exportReportToPdfFile(jasperPrint, reportDest);
            Desktop.getDesktop().open(new File("D:\\SSI REPORTS\\SalesChartByRepresentative.PDF"));
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

