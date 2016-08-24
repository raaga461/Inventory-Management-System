package ReportsModule.BeanPakage;

public class CFormOutstandingSummaryBean {
   private String year;
   private String rep;
   private Double amount=0.0;
   

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public String getRep() {
        return rep;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }
}
