package ReportsModule.BeanPakage;

import java.util.Date;

public class PurchaseStockTableBean {
    private Date sdate;
    private Integer itemcode;
    private String itemname;
    private String cate;
    private Integer quantity;


    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Date getSdate() {
        return sdate;
    }

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

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
