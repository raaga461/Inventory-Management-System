package ReportsModule.BeanPakage;

import java.util.Date;

public class VoucherReportTableBean {
    private String vno;
    private Date date;
    private String vtype;
    private String vname;
    private String paytype;
    private String chkno;
    private Double vamount;
    
    public VoucherReportTableBean() {
        super();
    }

    public void setVno(String vno) {
        this.vno = vno;
    }

    public String getVno() {
        return vno;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setVtype(String vtype) {
        this.vtype = vtype;
    }

    public String getVtype() {
        return vtype;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getVname() {
        return vname;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setChkno(String chkno) {
        this.chkno = chkno;
    }

    public String getChkno() {
        return chkno;
    }


    public void setVamount(Double vamount) {
        this.vamount = vamount;
    }

    public Double getVamount() {
        return vamount;
    }
}
