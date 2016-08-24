package transaction.Reports;

import java.io.File;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;

import javax.servlet.ServletContext;
 public  class Logo {
     String realPath;

    public Logo() {
        super();
    }

     public Map<String, Object> LogoMethod() {
        Map<String, Object> params = new HashMap<String, Object>();
        File f = new File(".");
        params.put("parameter1", "TRADE MARK REGD. COMPANY SINCE: 22-6-1987");
        params.put("parameter2", " SAMBASIVA DISTRIBUTORS");
        params.put("parameter3", "Plot No 192, Block 9, 5th Road,J.Auto Nagar, Vijayawada - 520007 ");
        params.put("parameter4", "PHONE: (0866) 2543625 , MOBILE: 9963296777,9000659999");
        params.put("parameter5", "E-Mail : anne9.ssd@gmail.com");
        params.put("parameter6", "TIN NO : 28960189862");
        params.put("parameter7", "TM");
        params.put("parameter8", "IFS CODE: ANDB000714  ,  A/C NO: 071413046000071");
        ServletContext ctx = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
        realPath = ctx.getRealPath("/");
        //System.out.println("the realpathis" + realPath + "images" + File.separator + "ssi-logo.jpg");
        //tring img = f.getAbsolutePath().substring(0, f.getAbsolutePath().length() - 1) + "images\\ssi-logo.jpg";
        String img = realPath + "\\images\\ssi-logo.jpg";
        //System.out.println("---->img path"+img);
        //    params.put("parameter9", "C:\\Users\\USER\\AppData\\Roaming\\JDeveloper\\system11.1.2.4.39.64.36.1\\o.j2ee\\drs\\Application2\\Project1WebApp.war\\images\\ssi-logo.jpg");
        params.put("parameter9", img);
        return params;

    }

    public static void main(String args[]) {

    }
}
