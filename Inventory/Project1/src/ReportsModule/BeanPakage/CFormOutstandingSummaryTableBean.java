package ReportsModule.BeanPakage;

public class CFormOutstandingSummaryTableBean {
       private Integer k;
       private String rep;
       private Double qta = 0.0, qtb = 0.0, qtc = 0.0, qtd = 0.0, total=0.0,grandt=0.0;
      


    public void setK(Integer k) {
        this.k = k;
    }

    public Integer getK() {
        return k;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public String getRep() {
        return rep;
    }

   
    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotal() {
        return total;
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


    public void setGrandt(Double grandt) {
        this.grandt = grandt;
    }

    public Double getGrandt() {
        return grandt;
    }
}
