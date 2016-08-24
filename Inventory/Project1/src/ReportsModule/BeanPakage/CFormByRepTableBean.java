package ReportsModule.BeanPakage;

import java.util.Date;

public class CFormByRepTableBean {
    private Integer sno;
    private Date sdate;
    private String billno;
    private String ino;
    private String name;
    private Double iamt;
    private String rep;
    private Double qta=0.0,qtb=0.0,qtc=0.0,qtd=0.0,total=0.0;

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getBillno() {
        return billno;
    }

    public void setIno(String ino) {
        this.ino = ino;
    }

    public String getIno() {
        return ino;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIamt(Double iamt) {
        this.iamt = iamt;
    }

    public Double getIamt() {
        return iamt;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public String getRep() {
        return rep;
    }

    public void setQta(Double qta) {
        this.qta = qta;
    }

    public Double getQta() {
        return qta;
    }

    public void setQtb(Double qtb) {
        this.qtb = qtb;
    }

    public Double getQtb() {
        return qtb;
    }

    public void setQtc(Double qtc) {
        this.qtc = qtc;
    }

    public Double getQtc() {
        return qtc;
    }

    public void setQtd(Double qtd) {
        this.qtd = qtd;
    }

    public Double getQtd() {
        return qtd;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotal() {
        return total;
    }
}
