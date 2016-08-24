package ReportsModule.BeanPakage;

import java.util.Date;

public class CformStatusByDateBillTableBean {
    private String billNo;
    private Date billDate;
    private String acName;
    private String invAmt;
    
    public CformStatusByDateBillTableBean() {
        super();
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setAcName(String acName) {
        this.acName = acName;
    }

    public String getAcName() {
        return acName;
    }

    public void setInvAmt(String invAmt) {
        this.invAmt = invAmt;
    }

    public String getInvAmt() {
        return invAmt;
    }
}
