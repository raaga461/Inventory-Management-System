package transaction.beanpackage;

import java.util.Date;

public class CustomerDetailsBean {
    private Integer cmpid;
    private Integer sid;
    private Integer billno;
    //private String acno;   here acno=acccode
   private Integer acccode;
    private String cname;
    private Double currbal;
    private String salestype;
    private String optype;
    private String addr;
    private String city;
    private Date invdate;
    private String invno;
    private Integer refno;
    private String taxtype;
    private String pan;
    private String transport;
    private Integer slno;
    private Double postage = 0.0;
    private Double salestax = 0.0;
    private Double others = 0.0;
    private Double roundof = 0.0;
    private String plusminus;
    private String note;
    private String cate;
    private String subcat;
    private String stax;
    private String bank;
    private String accno;
    private String route;
    private String rep;
    private String phn;
    private Integer rid;
    private String modify;
    private Integer orno;
    private Date ordate;
    private Integer lrno;
    private Date lrDate;
    private Integer waybill;
    private Date waydate;
    private Double gunybags;
    private Double bundles;
    private Double packing = 0.0;
    private Double autochrg = 0.0;
    private String cformstatus;
    private String cf;
    private Double invvalue = 0.0;
    private Double vat5a=0.0;
    private Double vat5b=0.0;
    private Double vat14a=0.0;
    private Double vat14b=0.0;
    private Double cst2a=0.0;
    private Double cst2b=0.0;    
    private Double ntamt = 0.0;
    private String inolist;
   
    
    
   
   
   
    
    


    public void setAcccode(Integer acccode) {
        this.acccode = acccode;
    }

    public Integer getAcccode() {
        return acccode;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCname() {
        return cname;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getTransport() {
        return transport;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getPan() {
        return pan;
    }

    public void setCurrbal(Double currbal) {
        this.currbal = currbal;
    }

    public Double getCurrbal() {
        return currbal;
    }

    public void setStax(String stax) {
        this.stax = stax;
    }

    public String getStax() {
        return stax;
    }

    

    public void setOptype(String optype) {
        this.optype = optype;
    }

    public String getOptype() {
        return optype;
    }

    public void setBillno(Integer billno) {
        this.billno = billno;
    }

    public Integer getBillno() {
        return billno;
    }

    public void setRefno(Integer refno) {
        this.refno = refno;
    }

    public Integer getRefno() {
        return refno;
    }


    public void setLrno(Integer lrno) {
        this.lrno = lrno;
    }

    public Integer getLrno() {
        return lrno;
    }

    public void setOrno(Integer orno) {
        this.orno = orno;
    }

    public Integer getOrno() {
        return orno;
    }

    public void setInvdate(Date invdate) {
        this.invdate = invdate;
    }

    public Date getInvdate() {
        return invdate;
    }

    public void setOrdate(Date ordate) {
        this.ordate = ordate;
    }

    public Date getOrdate() {
        return ordate;
    }

    public void setLrDate(Date lrDate) {
        this.lrDate = lrDate;
    }

    public Date getLrDate() {
        return lrDate;
    }

    public void setSalestype(String salestype) {
        this.salestype = salestype;
    }

    public String getSalestype() {
        return salestype;
    }

    public void setTaxtype(String taxtype) {
        this.taxtype = taxtype;
    }

    public String getTaxtype() {
        return taxtype;
    }

    public void setCformstatus(String cformstatus) {
        this.cformstatus = cformstatus;
    }

    public String getCformstatus() {
        return cformstatus;
    }

    public void setGunybags(Double gunybags) {
        this.gunybags = gunybags;
    }

    public Double getGunybags() {
        return gunybags;
    }

    public void setBundles(Double bundles) {
        this.bundles = bundles;
    }

    public Double getBundles() {
        return bundles;
    }

    public void setVat5a(Double vat5a) {
        this.vat5a = vat5a;
    }

    public Double getVat5a() {
        return vat5a;
    }

    public void setVat5b(Double vat5b) {
        this.vat5b = vat5b;
    }

    public Double getVat5b() {
        return vat5b;
    }

    public void setVat14a(Double vat14a) {
        this.vat14a = vat14a;
    }

    public Double getVat14a() {
        return vat14a;
    }

    public void setVat14b(Double vat14b) {
        this.vat14b = vat14b;
    }

    public Double getVat14b() {
        return vat14b;
    }

    public void setCst2a(Double cst2a) {
        this.cst2a = cst2a;
    }

    public Double getCst2a() {
        return cst2a;
    }

    public void setCst2b(Double cst2b) {
        this.cst2b = cst2b;
    }

    public Double getCst2b() {
        return cst2b;
    }

    public void setNtamt(Double ntamt) {
        this.ntamt = ntamt;
    }

    public Double getNtamt() {
        return ntamt;
    }

    public void setSalestax(Double salestax) {
        this.salestax = salestax;
    }

    public Double getSalestax() {
        return salestax;
    }

    public void setPacking(Double packing) {
        this.packing = packing;
    }

    public Double getPacking() {
        return packing;
    }

    public void setPostage(Double postage) {
        this.postage = postage;
    }

    public Double getPostage() {
        return postage;
    }

    public void setRoundof(Double roundof) {
        this.roundof = roundof;
    }

    public Double getRoundof() {
        return roundof;
    }

    public void setOthers(Double others) {
        this.others = others;
    }

    public Double getOthers() {
        return others;
    }

    public void setAutochrg(Double autochrg) {
        this.autochrg = autochrg;
    }

    public Double getAutochrg() {
        return autochrg;
    }

    public void setInvvalue(Double invvalue) {
        this.invvalue = invvalue;
    }

    public Double getInvvalue() {
        return invvalue;
    }

    public void setInvno(String invno) {
        this.invno = invno;
    }

    public String getInvno() {
        return invno;
    }

    public void setWaybill(Integer waybill) {
        this.waybill = waybill;
    }

    public Integer getWaybill() {
        return waybill;
    }

    public void setWaydate(Date waydate) {
        this.waydate = waydate;
    }

    public Date getWaydate() {
        return waydate;
    }

    public void setCmpid(Integer cmpid) {
        this.cmpid = cmpid;
    }

    public Integer getCmpid() {
        return cmpid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getAddr() {
        return addr;
    }

    public void setSlno(Integer slno) {
        this.slno = slno;
    }

    public Integer getSlno() {
        return slno;
    }

    public void setPlusminus(String plusminus) {
        this.plusminus = plusminus;
    }

    public String getPlusminus() {
        return plusminus;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getCate() {
        return cate;
    }

    public void setSubcat(String subcat) {
        this.subcat = subcat;
    }

    public String getSubcat() {
        return subcat;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBank() {
        return bank;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getAccno() {
        return accno;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public String getRep() {
        return rep;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

    public String getPhn() {
        return phn;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setModify(String modify) {
        this.modify = modify;
    }

    public String getModify() {
        return modify;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getCf() {
        return cf;
    }

    public void setInolist(String inolist) {
        this.inolist = inolist;
    }

    public String getInolist() {
        return inolist;
    }
}
