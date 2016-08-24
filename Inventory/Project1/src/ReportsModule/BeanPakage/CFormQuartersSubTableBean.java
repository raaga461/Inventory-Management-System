package ReportsModule.BeanPakage;

import java.util.Date;

public class CFormQuartersSubTableBean {
    private String billnum;
    private Date billDate;
    private String acName;
    private String invAmt;
    
    
    
    
    public CFormQuartersSubTableBean() {
        super();
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

    public void setBillnum(String billnum) {
        this.billnum = billnum;
    }

    public String getBillnum() {
        return billnum;
    }
}
