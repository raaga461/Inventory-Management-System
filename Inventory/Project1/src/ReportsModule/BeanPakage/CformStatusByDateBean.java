package ReportsModule.BeanPakage;

import java.util.Date;

public class CformStatusByDateBean {
    private Date date1;
    private Date date2;
    private String cName;
    private String dataRange;
    private String custDetails;
    private String billno;
    private String accCode;
    private Date date;
    public CformStatusByDateBean() {
        super();
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate1() {
        return date1;
    }


    public void setCName(String cName) {
        this.cName = cName;
    }

    public String getCName() {
        return cName;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public Date getDate2() {
        return date2;
    }

   
    public void setCustDetails(String custDetails) {
        this.custDetails = custDetails;
    }

    public String getCustDetails() {
        return custDetails;
    }

    public void setAccCode(String accCode) {
        this.accCode = accCode;
    }

    public String getAccCode() {
        return accCode;
    }

    public void setDataRange(String dataRange) {
        this.dataRange = dataRange;
    }

    public String getDataRange() {
        return dataRange;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getBillno() {
        return billno;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
