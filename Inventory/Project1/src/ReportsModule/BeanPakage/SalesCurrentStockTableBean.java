package ReportsModule.BeanPakage;

public class SalesCurrentStockTableBean {
    private Integer itemcode;
    private String itemname;
    private String cate;
    private Integer quanty;

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

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getCate() {
        return cate;
    }

    public void setQuanty(Integer quanty) {
        this.quanty = quanty;
    }

    public Integer getQuanty() {
        return quanty;
    }
}
