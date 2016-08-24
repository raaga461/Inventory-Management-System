package ReportsModule.BeanPakage;

public class AccountGroupTableBean {
    private String accno;
    private String mgroup;
    private String sgroup;
    private String type;
    private Double opbal;
    private Double currbal;
    public AccountGroupTableBean() {
        super();
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getAccno() {
        return accno;
    }

    public void setMgroup(String mgroup) {
        this.mgroup = mgroup;
    }

    public String getMgroup() {
        return mgroup;
    }

    public void setSgroup(String sgroup) {
        this.sgroup = sgroup;
    }

    public String getSgroup() {
        return sgroup;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }


    public void setOpbal(Double opbal) {
        this.opbal = opbal;
    }

    public Double getOpbal() {
        return opbal;
    }

    public void setCurrbal(Double currbal) {
        this.currbal = currbal;
    }

    public Double getCurrbal() {
        return currbal;
    }
}
