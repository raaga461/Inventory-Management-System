package ReportsModule.BeanPakage;

public class TaxAccountReportTableBean {
    private String taxno;
    private String region;
    private String taxname;
    private String taxpercent;  
    private String cfreq;
    private String cftax;
        
    
    
    
    
    
    public TaxAccountReportTableBean() {
        super();
    }

    public void setTaxno(String taxno) {
        this.taxno = taxno;
    }

    public String getTaxno() {
        return taxno;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

    public void setTaxname(String taxname) {
        this.taxname = taxname;
    }

    public String getTaxname() {
        return taxname;
    }

    public void setTaxpercent(String taxpercent) {
        this.taxpercent = taxpercent;
    }

    public String getTaxpercent() {
        return taxpercent;
    }

    public void setCfreq(String cfreq) {
        this.cfreq = cfreq;
    }

    public String getCfreq() {
        return cfreq;
    }

    public void setCftax(String cftax) {
        this.cftax = cftax;
    }

    public String getCftax() {
        return cftax;
    }
}
