package MASTERBEANS;

public class salesitemprofile {
    private int itemcode, compid;
    private String itemname, shortname, itemgroup, itemcategory, subcategory;
    private Double itemrate, tax, discount, maxdiscount;

    public salesitemprofile() {
        super();
    }

    public void setItemcode(int itemcode) {
        this.itemcode = itemcode;
    }

    public int getItemcode() {
        return itemcode;
    }

    public void setCompid(int compid) {
        this.compid = compid;
    }

    public int getCompid() {
        return compid;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemname() {
        return itemname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getShortname() {
        return shortname;
    }

    public void setItemgroup(String itemgroup) {
        this.itemgroup = itemgroup;
    }

    public String getItemgroup() {
        return itemgroup;
    }

    public void setItemcategory(String itemcategory) {
        this.itemcategory = itemcategory;
    }

    public String getItemcategory() {
        return itemcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setItemrate(Double itemrate) {
        this.itemrate = itemrate;
    }

    public Double getItemrate() {
        return itemrate;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTax() {
        return tax;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setMaxdiscount(Double maxdiscount) {
        this.maxdiscount = maxdiscount;
    }

    public Double getMaxdiscount() {
        return maxdiscount;
    }

    void setItemcode(String string) {
    }
}
