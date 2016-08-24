package ReportsModule.BeanPakage;

public class CFormStatusByQuartersBean {
    private String cyear;
    private String period;
    private String cname;
    
    public CFormStatusByQuartersBean() {
        super();
    }

    public void setCyear(String cyear) {
        this.cyear = cyear;
    }

    public String getCyear() {
        return cyear;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCname() {
        return cname;
    }
}
