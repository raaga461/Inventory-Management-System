package transaction.beanpackage;

public class TransactionTableBean {
    
   private Integer ss;
    private String iname;
   
    private Double orirate=0.0;
    private Double quan=0.0;
    private Double dis1=0.0;
    private Double dis2=0.0;
    private Double dis3=0.0;
    private Double udis=0.0;
    private Double disamt=0.0;
    private Double ucost=0.0;
    private Double tax=0.0;
    private Double utax=0.0;
    private Double taxamt=0.0;
    private Double ntval=0.0;
    private Integer itemcode=0;
    private String cate;
    private String aproduct;
    private String subgr;
    private String mtcat;
    
    
    
    public TransactionTableBean() {
        super();
    }

   

    public void setIname(String iname) {
        this.iname = iname;
    }

    public String getIname() {
        return iname;
    }

    

    public void setOrirate(Double orirate) {
        this.orirate = orirate;
    }

    public Double getOrirate() {
        return orirate;
    }

    public void setQuan(Double quan) {
        this.quan = quan;
    }

    public Double getQuan() {
        return quan;
    }

    public void setDis1(Double dis1) {
        this.dis1 = dis1;
    }

    public Double getDis1() {
        return dis1;
    }

    public void setDis2(Double dis2) {
        this.dis2 = dis2;
    }

    public Double getDis2() {
        return dis2;
    }

    public void setDis3(Double dis3) {
        this.dis3 = dis3;
    }

    public Double getDis3() {
        return dis3;
    }

    public void setUdis(Double udis) {
        this.udis = udis;
    }

    public Double getUdis() {
        return udis;
    }

    public void setDisamt(Double disamt) {
        this.disamt = disamt;
    }

    public Double getDisamt() {
        return disamt;
    }

    public void setUcost(Double ucost) {
        this.ucost = ucost;
    }

    public Double getUcost() {
        return ucost;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTax() {
        return tax;
    }

    public void setUtax(Double utax) {
        this.utax = utax;
    }

    public Double getUtax() {
        return utax;
    }

    public void setTaxamt(Double taxamt) {
        this.taxamt = taxamt;
    }

    public Double getTaxamt() {
        return taxamt;
    }

    public void setNtval(Double ntval) {
        this.ntval = ntval;
    }

    public Double getNtval() {
        return ntval;
    }


    public void setSs(Integer ss) {
        this.ss = ss;
    }

    public Integer getSs() {
        return ss;
    }

    public void setItemcode(Integer itemcode) {
        this.itemcode = itemcode;
    }

    public Integer getItemcode() {
        return itemcode;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getCate() {
        return cate;
    }

    public void setAproduct(String aproduct) {
        this.aproduct = aproduct;
    }

    public String getAproduct() {
        return aproduct;
    }

    public void setSubgr(String subgr) {
        this.subgr = subgr;
    }

    public String getSubgr() {
        return subgr;
    }

    public void setMtcat(String mtcat) {
        this.mtcat = mtcat;
    }

    public String getMtcat() {
        return mtcat;
    }
}
