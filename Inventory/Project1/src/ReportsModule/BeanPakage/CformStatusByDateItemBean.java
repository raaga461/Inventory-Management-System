package ReportsModule.BeanPakage;

public class CformStatusByDateItemBean {
    private String iNmae;
    private String price;
    private String quantity;
    private String disc;
    private String net;
    private String tax;
     public CformStatusByDateItemBean() {
        super();
    }

   

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public String getDisc() {
        return disc;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getNet() {
        return net;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTax() {
        return tax;
    }

    public void setINmae(String iNmae) {
        this.iNmae = iNmae;
    }

    public String getINmae() {
        return iNmae;
    }
}
