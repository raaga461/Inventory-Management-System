package ReportsModule.BeanPakage;

public class SalesItemTableBean {
    private Integer icode;
    private String iname;
    private Double rate;
    private String category;
    private String aproduct;
    private Double amount;
    private Double tax;
    private Double disc;
    private Double maxdisc;

    public SalesItemTableBean() {
        super();
    }

    public void setIcode(Integer icode) {
        this.icode = icode;
    }

    public Integer getIcode() {
        return icode;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public String getIname() {
        return iname;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getRate() {
        return rate;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setAproduct(String aproduct) {
        this.aproduct = aproduct;
    }

    public String getAproduct() {
        return aproduct;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTax() {
        return tax;
    }

    public void setDisc(Double disc) {
        this.disc = disc;
    }

    public Double getDisc() {
        return disc;
    }

    public void setMaxdisc(Double maxdisc) {
        this.maxdisc = maxdisc;
    }

    public Double getMaxdisc() {
        return maxdisc;
    }
}
