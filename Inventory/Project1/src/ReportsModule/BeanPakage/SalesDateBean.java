package ReportsModule.BeanPakage;

import java.util.Date;
import java.util.List;

public class SalesDateBean {
    Double amount=0.0;
    Date date1;
    Date date2;
     String type;
     
    List<String> taxtype;
    public SalesDateBean() {
        super();
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
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

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setTaxtype(List<String> taxtype) {
        this.taxtype = taxtype;
    }

    public List<String> getTaxtype() {
        return taxtype;
    }

   
}

