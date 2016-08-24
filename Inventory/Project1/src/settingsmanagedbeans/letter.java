/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OfficialLetter;

import java.awt.Desktop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.Statement;

import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.servlet.ServletContext;

import letterbean.letterdetails;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.primefaces.model.DefaultStreamedContent;

import org.primefaces.model.StreamedContent;


/**
 *
 * @author GreenBuds
 */
public class letter {

    public letterdetails l = new letterdetails();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private StreamedContent file;

    public letter() {
        set();
    }

    public void set() {
        l.setHeading("OFFICIAL LETTER");
        l.setPlace("Vijayawada");
        l.setDate(new Date());
        l.setTo_addr("To                        " + "XXX INDUSTRIES,  " + "PNO:3-100,       " + "VIJAYAWADA");
        l.setSalutaion("Dear XXXXXXXXXXXXX,");
        l.setContent("This is to inform you that....(here you need to write your content)");
        l.setYours("Yours faithfully,");
        l.setSign("Rama krishna kishore");

    }

    public void refresh() {
        System.out.println("In Refresh Function");
        l = new letterdetails();
    }

    public void generatepdf() {
        try {
            try {

                String path = "";
                //   System.out.println("sdfkjsdkjfsdjkf" + path);
                InputStream stream;

                stream = new FileInputStream(path);
                // ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream(path);
                //System.out.println("the stream value is" + stream);
                file = new DefaultStreamedContent(stream, "applcation/pdf", "OfficialLetterPDF" + ".jrxml");

            } catch (FileNotFoundException e) {

                FacesContext.getCurrentInstance().addMessage(null,
                                                             new FacesMessage(FacesMessage.SEVERITY_FATAL, "PDF NOT GENERATED",
                                                                              "Select Due Details"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void pdf() {
        ServletContext ctx = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath = ctx.getRealPath("/");
        String reportSource = realPath + "\\reports\\OfficialLetterPDF.jrxml";
        String reportDest = "D:\\SSI REPORTS\\OfficialLetterPDF.PDF";
        System.out.println(reportSource);
        try {
            Connection con;
            Map params = logo();
            params.put("head", l.getHeading());
                  params.put("place", l.getPlace());
                  //  params.put("dat", ld.getDate());
                  params.put("to", l.getTo_addr());
                  params.put("dear", l.getSalutaion());
                  params.put("msg", l.getContent());
                  params.put("yours", l.getYours());
                  params.put("name", l.getSign());

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Map<String, Object> logo() {
        Connection con;
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

    public letterdetails getL() {
        return l;
    }

    public void setL(letterdetails l) {
        this.l = l;
    }

    public SimpleDateFormat getDf() {
        return df;
    }

    public void setDf(SimpleDateFormat df) {
        this.df = df;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public StreamedContent getFile() {
        return file;
    }
}
