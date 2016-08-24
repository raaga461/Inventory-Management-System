package MASTERBEANS;
public class stockpurchasedetails {
   
    private int companyid;
    private int itemcode;
    private int quantity;
    private String itemname;
    private String itemcategory;
    private int currquantity;
    private int newquantity;


    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }

    public int getCompanyid() {
        return companyid;
    }

    public void setItemcode(int itemcode) {
        this.itemcode = itemcode;
    }

    public int getItemcode() {
        return itemcode;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemcategory(String itemcategory) {
        this.itemcategory = itemcategory;
    }

    public String getItemcategory() {
        return itemcategory;
    }

    public void setCurrquantity(int currquantity) {
        this.currquantity = currquantity;
    }

    public int getCurrquantity() {
        return currquantity;
    }

    public void setNewquantity(int newquantity) {
        this.newquantity = newquantity;
    }

    public int getNewquantity() {
        return newquantity;
    }
}
