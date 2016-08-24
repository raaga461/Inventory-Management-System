package MastersManagedBeans;

import java.awt.Desktop;

import java.io.File;

import java.sql.Connection;
import java.sql.DriverManager;

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

public class printbalance {

    private static String ty;

    printbalance(String ty) {
        ServletContext ctx = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath = ctx.getRealPath("/");
        String reportSource = realPath + "\\reports\\printbalancedetails.jrxml";
        System.out.println("the report path is" + reportSource);
        String reportDest = "D:\\SSI REPORTS\\BalanceDetails.PDF";
        System.out.println(reportSource);
        try {
            Connection con;
            Map params = new HashMap();
            params.put("parameter1", "TRADE MARK REGD. COMPANY SINCE: 22-6-1987");
            params.put("parameter2", " SAMBASIVA DISTRIBUTORS");
            params.put("parameter3", "Plot No 192, Block 9, 5th Road,J.Auto Nagar, Vijayawada - 520007 ");
            params.put("parameter4", "PHONE: (0866) 2543625 , MOBILE: 9963296777,9000659999");
            params.put("parameter5", "E-Mail : anne9.ssd@gmail.com");
            params.put("parameter6", "TIN NO : 28960189862");
            params.put("parameter7", "TM");
            params.put("parameter8", "IFS CODE: ANDB000714  ,  A/C NO: 071413046000071");
            String img =
                realPath+"\\images\\logo.jpg";
            //System.out.println("---->img path"+img);
            params.put("parameter9",
                      img);
            //   params.put("parameter9", img);

            // params.put("name", "SITE MAINTAINANCE DETAILS");
            params.put("type", ty);
            // params.put("headimg", "C:\\Users\\GreenBuds\\Documents\\NetBeansProjects\\WebApplication1\\src\\java\\balance\\img\\head.JPG");
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            System.out.println(" JasperCompileManager.compileReport(reportSource);");
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
            System.out.println("hi connected");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, con);
            //jasperReport, params, conn);
            System.out.println("Helloo jasperPrint =");
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportDest);
            System.out.println("xportReportToPdfFile......");
            // JasperViewer.viewReport(jasperPrint, false);
            Desktop.getDesktop().open(new File(reportDest));
        } catch (JRException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error in report" + e.getMessage());
        } // Exception handling for the Class.forName method.
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error in report" + ex.getMessage());
        }
    }

    public static void main(String args[]) {
        // printbalance pb=new printbalance(String typ);
        printbalance pb = new printbalance(ty);

    }
}
