package ReportsModule.BeanPakage;

public class PartyOutstandingTableBean {
    
    private Integer acccode;
    private String cname;
    private String addr;
    private String city;
    private String rep;
    private Double curbal;
    
    
    
    public PartyOutstandingTableBean() {
        super();
    }

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

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getAddr() {
        return addr;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public String getRep() {
        return rep;
    }

    public void setCurbal(Double curbal) {
        this.curbal = curbal;
    }

    public Double getCurbal() {
        return curbal;
    }
}
