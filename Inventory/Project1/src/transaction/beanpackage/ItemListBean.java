package transaction.beanpackage;

public class ItemListBean {
    private Integer itemcode;
    private String itemname;
    private Double rate;

    public void setItemcode(Integer itemcode) {
        this.itemcode = itemcode;
    }

    public Integer getItemcode() {
        return itemcode;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemname() {
        return itemname;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getRate() {
        return rate;
    }
}
