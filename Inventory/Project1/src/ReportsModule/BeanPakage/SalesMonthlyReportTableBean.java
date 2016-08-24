package ReportsModule.BeanPakage;

import java.util.Date;
public class SalesMonthlyReportTableBean {
    private int sno;
    private Date invdate;
    private String acname;
    private String city;
    private String net;
    private String salestax;
    private String postage;
    private Double mixvalue;
    private String others;
    private String roundof;
    private String invvalue;
    
    
    
    public SalesMonthlyReportTableBean() {
        super();
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public int getSno() {
        return sno;
    }

    public void setInvdate(Date invdate) {
        this.invdate = invdate;
    }

    public Date getInvdate() {
        return invdate;
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

    public void setNet(String net) {
        this.net = net;
    }

    public String getNet() {
        return net;
    }

    public void setSalestax(String salestax) {
        this.salestax = salestax;
    }

    public String getSalestax() {
        return salestax;
    }

    public void setPostage(String postage) {
        this.postage = postage;
    }

    public String getPostage() {
        return postage;
    }

 

    public void setOthers(String others) {
        this.others = others;
    }

    public String getOthers() {
        return others;
    }

    public void setRoundof(String roundof) {
        this.roundof = roundof;
    }

    public String getRoundof() {
        return roundof;
    }

    public void setInvvalue(String invvalue) {
        this.invvalue = invvalue;
    }

    public String getInvvalue() {
        return invvalue;
    }


    public void setMixvalue(Double mixvalue) {
        this.mixvalue = mixvalue;
    }

    public Double getMixvalue() {
        return mixvalue;
    }
}
