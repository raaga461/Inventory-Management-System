package MASTERBEANS;

public class purchaseitem {
    private int cmpid;
    private String code;
    private String name;
    private String category;
    private String rate;
    private String tax;
    private String discount;
    private String max;
    private String status;
    private String cst;
    private String others;
    private double disc;
    private double maxdisc;
    private double mrp;
    private double salesprice;


  public purchaseitem()
    {
        super();
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRate() {
        return rate;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTax() {
        return tax;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMax() {
        return max;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setCmpid(int cmpid) {
        this.cmpid = cmpid;
    }

    public int getCmpid() {
        return cmpid;
    }

    public void setCst(String cst) {
        this.cst = cst;
    }

    public String getCst() {
        return cst;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getOthers() {
        return others;
    }

    public void setDisc(double disc) {
        this.disc = disc;
    }

    public double getDisc() {
        return disc;
    }

    public void setMaxdisc(double maxdisc) {
        this.maxdisc = maxdisc;
    }

    public double getMaxdisc() {
        return maxdisc;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }

    public double getMrp() {
        return mrp;
    }

    public void setSalesprice(double salesprice) {
        this.salesprice = salesprice;
    }

    public double getSalesprice() {
        return salesprice;
    }
}
