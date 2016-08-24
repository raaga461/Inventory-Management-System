package ReportsModule.BeanPakage;

import java.util.Date;

public class SalesInvoiceTableBean {
    private String partyname;
    private String city;
    private String represent;
    private Date invdate;
    private String invno;
    private String invvalue; 
    public SalesInvoiceTableBean() {
        super();
    }


    public void setPartyname(String partyname) {
        this.partyname = partyname;
    }

    public String getPartyname() {
        return partyname;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setRepresent(String represent) {
        this.represent = represent;
    }

    public String getRepresent() {
        return represent;
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
}
