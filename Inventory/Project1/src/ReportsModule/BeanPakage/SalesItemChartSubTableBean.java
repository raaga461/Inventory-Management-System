package ReportsModule.BeanPakage;

public class SalesItemChartSubTableBean {
    private int sno;
    private String pname;
    private String city;
    private String product;
    private Double quantity;
    private Double net;
    private String rep;
    
    public SalesItemChartSubTableBean() {
        super();
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public int getSno() {
        return sno;
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

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setNet(Double net) {
        this.net = net;
    }

    public Double getNet() {
        return net;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public String getRep() {
        return rep;
    }
}
