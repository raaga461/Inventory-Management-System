package ReportsModule.PDFReports;


import ReportsModule.BeanPakage.PartyOutstandingTableBean;

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


public class PartyOutstandingPDF {
    
    public void execute(String fromdate, String todate,List<PartyOutstandingTableBean> tdata,String stype,String route,String repre,String city,Double amt) throws JRException {
        File f = new File(".");
        ServletContext ctx = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath = ctx.getRealPath("/");
        System.out.println(realPath);
        String reportSource =realPath+"\\reports\\PartyOutstandingPDF.jrxml";
        System.out.println(reportSource);
         //   "C:\\Users\\USER\\AppData\\Roaming\\JDeveloper\\system11.1.2.4.39.64.36.1\\o.j2ee\\drs\\Application3\\Project1WebApp.war\\reports\\SalesDatesPDF.jrxml";
        //  String reportSource = "C:\\Users\\USER\\Documents\\NetBeansProjects\\SSI\\src\\PDFReports\\SalesDatesPDF.jrxml";
        String reportDest = "D:\\SSI REPORTS\\PartyOutstandingPDF.PDF";
        Map<String, Object> params = Logo.LogoMethod();
        params.put("from", fromdate);
        params.put("to", todate);        
        params.put("stype", stype);
        params.put("route", route);
        params.put("repre", repre);
        params.put("city", city);
        params.put("amt", amt);

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            System.out.println(" JasperCompileManager.compileReport(reportSource);");
            Connection conn = DBConnection.connection();
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Conn");
            }
            //JasperPrint jasperPrint =JasperFillManager.fillReport(jasperReport, params,conn );
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(tdata));
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportDest);
            Desktop.getDesktop().open(new File("D:\\SSI REPORTS\\PartyOutstandingPDF.PDF"));
            //JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error in report" + e.getMessage());
        } // Exception handling for the Class.forName method.
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error in report" + ex.getMessage());
        }
    }
}
