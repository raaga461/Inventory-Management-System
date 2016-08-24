package InventoryBeans;

public class voucherprofile {
    private int companyid;
    private int voucherno, repid, accountcode;
    private String vouchername, customername, repname;
    private String voucherdate, payingto, receivingfrom, paytype, bankname, narration, cheque_ddno,city,user;
    private double compbalance, compnewbalance, cust_newbal, cust_currbal, amount;

    public voucherprofile() {
        super();
    }

    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }

    public int getCompanyid() {
        return companyid;
    }

    public void setVoucherno(int voucherno) {
        this.voucherno = voucherno;
    }

    public int getVoucherno() {
        return voucherno;
    }

    public void setVouchername(String vouchername) {
        this.vouchername = vouchername;
    }

    public String getVouchername() {
        return vouchername;
    }

    public void setVoucherdate(String voucherdate) {
        this.voucherdate = voucherdate;
    }

    public String getVoucherdate() {
        return voucherdate;
    }

    public void setPayingto(String payingto) {
        this.payingto = payingto;
    }

    public String getPayingto() {
        return payingto;
    }

    public void setReceivingfrom(String receivingfrom) {
        this.receivingfrom = receivingfrom;
    }

    public String getReceivingfrom() {
        return receivingfrom;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBankname() {
        return bankname;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getNarration() {
        return narration;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCheque_ddno(String cheque_ddno) {
        this.cheque_ddno = cheque_ddno;
    }

    public String getCheque_ddno() {
        return cheque_ddno;
    }

    public void setRepid(int repid) {
        this.repid = repid;
    }

    public int getRepid() {
        return repid;
    }

    public void setRepname(String repname) {
        this.repname = repname;
    }

    public String getRepname() {
        return repname;
    }

    public void setCompbalance(double compbalance) {
        this.compbalance = compbalance;
    }

    public double getCompbalance() {
        return compbalance;
    }

    public void setCompnewbalance(double compnewbalance) {
        this.compnewbalance = compnewbalance;
    }

    public double getCompnewbalance() {
        return compnewbalance;
    }

    public void setCust_newbal(double cust_newbal) {
        this.cust_newbal = cust_newbal;
    }

    public double getCust_newbal() {
        return cust_newbal;
    }

    public void setCust_currbal(double cust_currbal) {
        this.cust_currbal = cust_currbal;
    }

    public double getCust_currbal() {
        return cust_currbal;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setAccountcode(int accountcode) {
        this.accountcode = accountcode;
    }

    public int getAccountcode() {
        return accountcode;
    }
}
