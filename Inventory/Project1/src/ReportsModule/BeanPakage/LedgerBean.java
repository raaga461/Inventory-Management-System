package ReportsModule.BeanPakage;

import java.util.Date;

public class LedgerBean {
    
    private Double amount=0.0;
    private Date date1;
    private Date date2;
     
     
    
    public LedgerBean() {
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

   
}
