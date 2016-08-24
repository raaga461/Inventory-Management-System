package ReportsModule.BeanPakage;

import java.util.Date;

public class CFormByRepBean {
    private Date date1;
    private Date date2;
    private String yr;
    private String rep;
    private Double amount;

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public Date getDate2() {
        return date2;
    }

    public void setYr(String yr) {
        this.yr = yr;
    }

    public String getYr() {
        return yr;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public String getRep() {
        return rep;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }
}
