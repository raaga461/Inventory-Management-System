package MASTERBEANS;

public class accountsgroup {

    private int accountnumber;
    private int subnumber;
    private String undergroup;
    private String newaccgroup;
    private String openingbal;
    private String currentbalance;
    private String type;
    private String notes;
    private int companyid;

    public int getCompanyid() {
        return companyid;
    }

    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }


    public String getUndergroup() {
        return undergroup;
    }

    public void setUndergroup(String undergroup) {
        this.undergroup = undergroup;
    }

    public String getNewaccgroup() {
        return newaccgroup;
    }

    public void setNewaccgroup(String newaccgroup) {
        this.newaccgroup = newaccgroup;
    }

    public String getOpeningbal() {
        return openingbal;
    }

    public void setOpeningbal(String openingbal) {
        this.openingbal = openingbal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setCurrentbalance(String currentbalance) {
        this.currentbalance = currentbalance;
    }

    public String getCurrentbalance() {
        return currentbalance;
    }

    public void setAccountnumber(int accountnumber) {
        this.accountnumber = accountnumber;
    }

    public int getAccountnumber() {
        return accountnumber;
    }

    public void setSubnumber(int subnumber) {
        this.subnumber = subnumber;
    }

    public int getSubnumber() {
        return subnumber;
    }
}
