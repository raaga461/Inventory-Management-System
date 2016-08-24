package ReportsModule.BeanPakage;

import java.util.Date;

public class ChequeReturnsTableBean {
    private int sno;
    private String billno;
    private String pname;
    private String city;
    private String represent;
    private Date chqdate;
    private String chqno;
    private Double amount;
    private Double bnkcharge;
    private Date retdate;
    private Double ntamt;
        
    
    public ChequeReturnsTableBean() {
        super();
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public int getSno() {
        return sno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getBillno() {
        return billno;
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

    public void setRepresent(String represent) {
        this.represent = represent;
    }

    public String getRepresent() {
        return represent;
    }

    public void setChqdate(Date chqdate) {
        this.chqdate = chqdate;
    }

    public Date getChqdate() {
        return chqdate;
    }

    public void setChqno(String chqno) {
        this.chqno = chqno;
    }

    public String getChqno() {
        return chqno;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setBnkcharge(Double bnkcharge) {
        this.bnkcharge = bnkcharge;
    }

    public Double getBnkcharge() {
        return bnkcharge;
    }

    public void setRetdate(Date retdate) {
        this.retdate = retdate;
    }

    public Date getRetdate() {
        return retdate;
    }


    public void setNtamt(Double ntamt) {
        this.ntamt = ntamt;
    }

    public Double getNtamt() {
        return ntamt;
    }
}
