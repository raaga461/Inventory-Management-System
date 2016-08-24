package ReportsModule.BeanPakage;

import java.util.Date;

public class SalesItemChartTableBean {
    private String invno;
    private Date invdate;
    private String pname;
    private String city;
    private String rep;
    private String icat;
    private Double invamt;
    
    
    
    public SalesItemChartTableBean() {
        super();
    }

    public void setInvno(String invno) {
        this.invno = invno;
    }

    public String getInvno() {
        return invno;
    }

    public void setInvdate(Date invdate) {
        this.invdate = invdate;
    }

    public Date getInvdate() {
        return invdate;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPname() {
        return pname;
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

    public void setIcat(String icat) {
        this.icat = icat;
    }

    public String getIcat() {
        return icat;
    }

    public void setInvamt(Double invamt) {
        this.invamt = invamt;
    }

    public Double getInvamt() {
        return invamt;
    }
}
