/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package settingsreports;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import letterbean.letterdetails;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author GreenBuds
 */
public class OfficialLetterPDF {

    Connection con;

    public OfficialLetterPDF(letterdetails ld) {

        File f = new File(".");
        System.out.println("" + f.getAbsolutePath());
        String pp = f.getAbsolutePath();
        int n = pp.length();
        System.out.println(pp.substring(0, n - 1));
        String pp2 = pp.substring(0, n - 1);
        System.out.println("--->" + pp2);
//        String reportSource = pp2 + "src\\PDF\\OfficialLetterPDF.jrxml";
//        String reportDest = pp2 + "\\INVN\\OfficialLetterPDF.PDF";
        String reportSource = "C:\\Users\\GreenBuds\\Documents\\NetBeansProjects\\ReportGeneration\\src\\java\\pdf\\OfficialLetterPDF.jrxml";
        String reportDest = "D:\\netbeansreports\\OfficialLetterPDF.pdf";

        Map<String, Object> params = logo();
        // Map<String, Object> params = new HashMap<String, Object>();
        params.put("head", ld.getHeading());
        params.put("place", ld.getPlace());
        //  params.put("dat", ld.getDate());
        params.put("to", ld.getTo_addr());
        params.put("dear", ld.getSalutaion());
        params.put("msg", ld.getContent());
        params.put("yours", ld.getYours());
        params.put("name", ld.getSign());


        try {
            JasperReport jasperReport =
                    JasperCompileManager.compileReport(reportSource);
            System.out.println(" JasperCompileManager.compileReport(reportSource);");
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
            System.out.println("hi connected");
            JasperPrint jasperPrint =
                    JasperFillManager.fillReport(
                    jasperReport, params, con);

            System.out.println("asperPrint jasperPrint =");

            JasperExportManager.exportReportToPdfFile(jasperPrint, reportDest);

            System.out.println("xportReportToPdfFile......");
            JasperViewer.viewReport(jasperPrint, false);

        } // Exception handling for the Class.forName method.
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error in report" + ex.getMessage());
        }
    }

    public Map<String, Object> logo() {
        Map<String, Object> params = new HashMap<String, Object>();
        File f = new File(".");
        System.out.println("" + f.getAbsolutePath());
        String pp = f.getAbsolutePath();
        int n = pp.length();
        System.out.println(pp.substring(0, n - 1));
        String pp2 = pp.substring(0, n - 1);
        System.out.println("--->" + pp2);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from pdf");
            if (rs.next()) {
                params.put("parameter1", rs.getString(1));
                params.put("parameter2", rs.getString(2));
                params.put("parameter3", rs.getString(3));
                params.put("parameter4", rs.getString(4));
                params.put("parameter5", rs.getString(5));
                params.put("parameter6", rs.getString(6));
                params.put("parameter7", rs.getString(7));
                params.put("parameter8", rs.getString(8));

                // String img = rs.getString(9);
                // System.out.println("---->img path" + img);

                //params.put("parameter9", img);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }
}
