package transaction.beanpackage;

public class InvoicePrintingBean {
    private Integer reffno;
    private String billtype;
    private String cname;
    private String address;
    private String city;
    private Double invval;
    
    public InvoicePrintingBean() {
        super();
    }

    public void setReffno(Integer reffno) {
        this.reffno = reffno;
    }

    public Integer getReffno() {
        return reffno;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype;
    }

    public String getBilltype() {
        return billtype;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCname() {
        return cname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setInvval(Double invval) {
        this.invval = invval;
    }

    public Double getInvval() {
        return invval;
    }
}
