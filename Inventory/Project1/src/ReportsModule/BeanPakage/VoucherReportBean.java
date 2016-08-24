package ReportsModule.BeanPakage;

import java.util.Date;

public class VoucherReportBean {
    private Date date1;
    private Date date2;
    private String type;
    public VoucherReportBean() {
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

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
