/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package letterbean;

import java.util.Date;

/**
 *
 * @author GreenBuds
 */
public class letterdetails {
    private String heading;
    private String place;
    private Date date;
    private String to_addr;
    private String salutaion;
    private String content;
    private String yours;
    private String sign;

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTo_addr() {
        return to_addr;
    }

    public void setTo_addr(String to_addr) {
        this.to_addr = to_addr;
    }

    public String getSalutaion() {
        return salutaion;
    }

    public void setSalutaion(String salutaion) {
        this.salutaion = salutaion;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getYours() {
        return yours;
    }

    public void setYours(String yours) {
        this.yours = yours;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
    
}
