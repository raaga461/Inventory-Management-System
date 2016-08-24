package ReportsModule.BeanPakage;

import java.util.Date;

public class CFormSubmitDatesTableBean {
    private String bill;
    private Date cfdate;
    private String period;
    private String name;
    private String tinno;
    private String serialno;
    private String cfoutstanding;
    private String cfgiven;
    private String status;   
    public CFormSubmitDatesTableBean() {
        super();
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getBill() {
        return bill;
    }

    public void setCfdate(Date cfdate) {
        this.cfdate = cfdate;
    }

    public Date getCfdate() {
        return cfdate;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTinno(String tinno) {
        this.tinno = tinno;
    }

    public String getTinno() {
        return tinno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setCfoutstanding(String cfoutstanding) {
        this.cfoutstanding = cfoutstanding;
    }

    public String getCfoutstanding() {
        return cfoutstanding;
    }

    public void setCfgiven(String cfgiven) {
        this.cfgiven = cfgiven;
    }

    public String getCfgiven() {
        return cfgiven;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
