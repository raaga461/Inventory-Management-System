package ReportsModule.BeanPakage;

public class PurchaseItemTableBean {
    private int icode;
    private String iname;
    private Double rate;
    private String icat;
    private Double iprice;
    private Double mrp;
    private Double tax;
    private Double disc;
    private Double mxdisc;
    
    
    
    
    public PurchaseItemTableBean() {
        super();
    }

    public void setIcode(int icode) {
        this.icode = icode;
    }

    public int getIcode() {
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

    public void setIcat(String icat) {
        this.icat = icat;
    }

    public String getIcat() {
        return icat;
    }

    public void setIprice(Double iprice) {
        this.iprice = iprice;
    }

    public Double getIprice() {
        return iprice;
    }

    public void setMrp(Double mrp) {
        this.mrp = mrp;
    }

    public Double getMrp() {
        return mrp;
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

    public void setMxdisc(Double mxdisc) {
        this.mxdisc = mxdisc;
    }

    public Double getMxdisc() {
        return mxdisc;
    }
}
