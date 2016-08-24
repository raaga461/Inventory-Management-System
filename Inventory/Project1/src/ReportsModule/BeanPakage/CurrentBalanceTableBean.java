package ReportsModule.BeanPakage;

public class CurrentBalanceTableBean {
    private String acno;
    private String type;
    private String name;
    private String bal;
    
    public CurrentBalanceTableBean() {
        super();
    }

    public void setAcno(String acno) {
        this.acno = acno;
    }

    public String getAcno() {
        return acno;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBal(String bal) {
        this.bal = bal;
    }

    public String getBal() {
        return bal;
    }
}
