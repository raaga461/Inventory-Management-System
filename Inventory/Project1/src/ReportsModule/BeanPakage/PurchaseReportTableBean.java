package ReportsModule.BeanPakage;

import java.util.Date;

public class PurchaseReportTableBean {
    String accno;
    String billno;
    String acname;
    String route;
    String rep;
    Date invdate;
    String invno;
    String invvalue;
    
    public PurchaseReportTableBean() {
        super();
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getAccno() {
        return accno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getBillno() {
        return billno;
    }

    public void setAcname(String acname) {
        this.acname = acname;
    }

    public String getAcname() {
        return acname;
    }

  

    public void setRep(String rep) {
        this.rep = rep;
    }

    public String getRep() {
        return rep;
    }

    public void setInvdate(Date invdate) {
        this.invdate = invdate;
    }

    public Date getInvdate() {
        return invdate;
    }

    public void setInvno(String invno) {
        this.invno = invno;
    }

    public String getInvno() {
        return invno;
    }

    public void setInvvalue(String invvalue) {
        this.invvalue = invvalue;
    }

    public String getInvvalue() {
        return invvalue;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }
}
