package ReportsModule.BeanPakage;

import java.util.Date;

public class YearlySalesTableBean {
    private String billno;
    private String acno;
    private String acname;
    private String city;
    private String rep;
    private Date invdate;
    private String invno;
    private Double invamt;
    public YearlySalesTableBean() {
        super();
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getBillno() {
        return billno;
    }

    public void setAcno(String acno) {
        this.acno = acno;
    }

    public String getAcno() {
        return acno;
    }

    public void setAcname(String acname) {
        this.acname = acname;
    }

    public String getAcname() {
        return acname;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
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

    public void setInvamt(Double invamt) {
        this.invamt = invamt;
    }

    public Double getInvamt() {
        return invamt;
    }
}
