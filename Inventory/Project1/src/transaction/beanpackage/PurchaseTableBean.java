package transaction.beanpackage;

public class PurchaseTableBean {
    private Integer ss;
    private String pname;
    private String code;
    private Double orirate=0.0;
    private Integer quantity;
    private Double disc=0.0;
    private Double disc2=0.0;
    private Double disc3=0.0;
    private Double udisc=0.0;
    private Double discamt=0.0;
    private Double unitcost=0.0;
    private Double tax=0.0;
    private Double unittax=0.0;
    private Double taxamt=0.0;
    private Double netval=0.0;
   


    public void setSs(Integer ss) {
        this.ss = ss;
    }

    public Integer getSs() {
        return ss;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPname() {
        return pname;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setOrirate(Double orirate) {
        this.orirate = orirate;
    }

    public Double getOrirate() {
        return orirate;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setDisc(Double disc) {
        this.disc = disc;
    }

    public Double getDisc() {
        return disc;
    }

    public void setDisc2(Double disc2) {
        this.disc2 = disc2;
    }

    public Double getDisc2() {
        return disc2;
    }

    public void setDisc3(Double disc3) {
        this.disc3 = disc3;
    }

    public Double getDisc3() {
        return disc3;
    }

    public void setUdisc(Double udisc) {
        this.udisc = udisc;
    }

    public Double getUdisc() {
        return udisc;
    }

    public void setDiscamt(Double discamt) {
        this.discamt = discamt;
    }

    public Double getDiscamt() {
        return discamt;
    }

    public void setUnitcost(Double unitcost) {
        this.unitcost = unitcost;
    }

    public Double getUnitcost() {
        return unitcost;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTax() {
        return tax;
    }

    public void setUnittax(Double unittax) {
        this.unittax = unittax;
    }

    public Double getUnittax() {
        return unittax;
    }

    public void setTaxamt(Double taxamt) {
        this.taxamt = taxamt;
    }

    public Double getTaxamt() {
        return taxamt;
    }

    public void setNetval(Double netval) {
        this.netval = netval;
    }

    public Double getNetval() {
        return netval;
    }
}
