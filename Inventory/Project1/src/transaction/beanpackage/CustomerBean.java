package transaction.beanpackage;

public class CustomerBean {
    private Integer accno;
    private String custname;

    public void setAccno(Integer accno) {
        this.accno = accno;
    }

    public Integer getAccno() {
        return accno;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getCustname() {
        return custname;
    }
}
