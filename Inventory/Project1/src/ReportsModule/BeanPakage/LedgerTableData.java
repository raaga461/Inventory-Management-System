package ReportsModule.BeanPakage;

import java.util.Date;

public class LedgerTableData {
    
    private String sno;
    private Date invdate;
    private String customer;
    private String city;
    private String billno;
    private String particulars;
    private Double debit;
    private Double credit;
    
    
    
    
    public LedgerTableData() {
        super();
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSno() {
        return sno;
    }

    public void setInvdate(Date invdate) {
        this.invdate = invdate;
    }

    public Date getInvdate() {
        return invdate;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getBillno() {
        return billno;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public Double getDebit() {
        return debit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Double getCredit() {
        return credit;
    }
}
