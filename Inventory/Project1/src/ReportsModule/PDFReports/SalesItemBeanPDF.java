package ReportsModule.PDFReports;

import ReportsModule.BeanPakage.ItemPdfReportBean;
import ReportsModule.BeanPakage.YearlySalesTableBean;

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
import net.sf.jasperreports.view.JasperViewer;

public class SalesItemBeanPDF {
    public SalesItemBeanPDF() {
        super();
    }
    
    
    public void execute( String fromdate, String todate,String rtype,String rep,String route,String s1,String s2,String igroup,List<ItemPdfReportBean>tdata) {
        File f = new File(".");
        ServletContext ctx = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath = ctx.getRealPath("/");
        String reportSource =realPath+"\\reports\\SalesChartbyitemwise.jrxml";
           // "C:\\Users\\USER\\AppData\\Roaming\\JDeveloper\\system11.1.2.4.39.64.36.1\\o.j2ee\\drs\\Application2\\Project1WebApp.war\\reports\\SalesYealyPDF.jrxml";
        //  String reportSource = "C:\\Users\\USER\\Documents\\NetBeansProjects\\SSI\\src\\PDFReports\\SalesDatesPDF.jrxml";
        String reportDest = "D:\\SSI REPORTS\\SalesChartbyitemwise.PDF";
        Map<String, Object> params =Logo.LogoMethod();
      
                    params.put("from",fromdate);
                     params.put("to",todate);
        params.put("ite",rtype);
                   params.put("rep",rep);
        params.put("route",route);
        params.put("igroup",igroup);
        params.put("s1",s1);
        params.put("s2",s2);
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            System.out.println(" JasperCompileManager.compileReport(reportSource);");
            Connection conn = DBConnection.connection();
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Conn");
            }
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(tdata));
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportDest);
            Desktop.getDesktop().open(new File("D:\\SSI REPORTS\\SalesChartbyitemwise.PDF"));
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
