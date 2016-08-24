package ReportsModule.BeanPakage;

import java.util.Date;

public class SalesQuotationTableBean {
    
    private String billno;
    private String stype;
    private String accname;
    private String route;
    private String rep;
    private Date invdate;
    private String invno;
    private Double net;
    
    
    
    
    public SalesQuotationTableBean() {
        super();
    }

    

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getStype() {
        return stype;
    }

    public void setAccname(String accname) {
        this.accname = accname;
    }

    public String getAccname() {
        return accname;
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

    

    public void setRoute(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }


    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getBillno() {
        return billno;
    }


    public void setNet(Double net) {
        this.net = net;
    }

    public Double getNet() {
        return net;
    }
}
