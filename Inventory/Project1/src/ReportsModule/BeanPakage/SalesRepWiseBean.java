package ReportsModule.BeanPakage;

import java.util.Date;

public class SalesRepWiseBean {
    Date date1;
    Date date2;
    String taxType;
    String repType;
    Double amount;
    public SalesRepWiseBean() {
        super();
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public Date getDate2() {
        return date2;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setRepType(String repType) {
        this.repType = repType;
    }

    public String getRepType() {
        return repType;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }
}
