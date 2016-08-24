package AccountsManagedBeans;

import InventoryBeans.Company;
import InventoryBeans.chequereturnsprofile;

import InventoryBeans.voucherprofile;

import InventoryDaos.accountsdao;

import org.primefaces.event.SelectEvent;

import java.io.Serializable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;

public class chequereturns implements Serializable {

    @SuppressWarnings("compatibility:-7390345163507791444")
    private static final long serialVersionUID = 1L;
    public chequereturnsprofile c = new chequereturnsprofile();
    public chequereturnsprofile c1 = new chequereturnsprofile();
    public chequereturnsprofile cn = new chequereturnsprofile();
    Connection con;
    Statement st;
    public ArrayList<chequereturnsprofile> al;
    public ArrayList<chequereturnsprofile> array;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Boolean status = true;
    private Date chequedate, cheqereturndate;
    ArrayList<voucherprofile> voucherdeatils = new ArrayList<voucherprofile>();
    private int curr_companyid;
    double curbal;
    String city;

    public chequereturns() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
            Company co = (Company)session.getAttribute("company");
            c.setCompanyid(co.getCompanyid());
            setCurr_companyid(co.getCompanyid());
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
            System.out.println("hi connected");
            SNO();
            search();
            getcustnnames();
            setCurr_companyid(1);
            setCheqereturndate(new Date());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void save() {
        System.out.println("in save");
        c.setCompanyid(1);
        if (getChequedate() != null) {
            c.setChqdate(df.format(getChequedate()));
        }
        if (getChequedate() != null) {
            c.setReturndate(df.format(getCheqereturndate()));
        }
        try {
            st = con.createStatement();
            String qry =
                "INSERT  INTO `ChqReturns` VALUES (" + c.getCompanyid() + "   ," + c.getSno() + "   ," + c.getCid() +
                " , '" + c.getCustomername() + "'  ,'" + c.getType() + "' , '" + c.getChqdate() + "' ,    '" +
                c.getChqno() + "'  ," + c.getAmount() + "   ," + c.getBankcharge() + ",  '" + c.getReturndate() +
                "' ,    '" + c.getNotes() + "' ,    '" + c.getChno() + "'," + c.getTotalamt() + ",'" + c.getRep() +
                "'," + c.getRid() + ", '" + c.getCity() + "')";
            System.out.println("the query is is: " + qry);
            st.executeUpdate(qry);
            UpdateCurrBal();
            setStatus(true);
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO, "CHEQUE DETAILS HAS BEEN INSERTED.",
                                                                          ""));
          /*   FacesMessage msg = new FacesMessage("CHEQUE DETAILS HAS BEEN INSERTED.", null);
            FacesContext.getCurrentInstance().addMessage(null, msg); */
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void savechequereturnsprofile() {
        System.out.println("the query is is:========== ");
        c.setCompanyid(1);
        try {
            st = con.createStatement();
            String qry =
                "INSERT  INTO `ChqReturns` VALUES (" + c.getCompanyid() + "   ," + c.getSno() + "   ," + c.getCid() +
                " , '" + c.getCustomername() + "'  ,'" + c.getType() + "' , '" + c.getChqdate() + "' ,    '" +
                c.getChqno() + "'  ," + c.getAmount() + "   ," + c.getBankcharge() + ",  '" +
                df.format(c.getReturndate()) + "' ,    '" + c.getNotes() + "' ,    '" + c.getChno() + "'," +
                c.getTotalamt() + ",'" + c.getRep() + "'," + c.getRid() + ", '" + c.getCity() + "')";
            System.out.println("the query is is: " + qry);
            st.executeUpdate(qry);
            /* FacesMessage msg = new FacesMessage("CHEQUE DETAILS HAS BEEN INSERTED.", null);
            FacesContext.getCurrentInstance().addMessage(null, msg); */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SNO() {
        int snoo;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT max(`sno`)  FROM `ChqReturns` where cmpid=" + getCurr_companyid() + "");
            if (rs.next()) {
                snoo = rs.getInt(1);
                snoo++;
            } else {
                snoo = 1;
            }
            c.setSno(snoo);
            c.setChno("CHQRET/" + snoo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int lno() {
        int lno = 0;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT  max(`lno`) FROM `Ledger`");
            if (rs.next()) {
                lno = rs.getInt(1);
                lno++;
            } else {
                lno = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lno;
    }

    public void Ledger() {
        c.setCompanyid(1);
        if (getChequedate() != null) {
            c.setChqdate(df.format(getChequedate()));
        }
        if (getChequedate() != null) {
            c.setReturndate(df.format(getCheqereturndate()));
        }
        int lno = lno();
        try {
            Statement stmt = con.createStatement();


            String qry =
                "INSERT INTO `Ledger` VALUES (" + c.getCompanyid() + "," + lno + " , '" + df.format(new Date()) +
                "', '" + c.getCustomername() + "','" + c.getCity() + "', 000 , '" + c.getChno() + "'," +
                c.getAmount() + ",0, 'Chq Returns  ','Chq','" + c.getChqno() + "' ," + curbal + ",'DEBIT')";
            System.out.println("" + qry);
            stmt.executeUpdate(qry);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void UpdateCurrBal() {

        try {
            Statement stmt = con.createStatement();
            ResultSet rs;
            rs =
 stmt.executeQuery("SELECT `currbal`,`city` FROM `Customer` WHERE `acccode`= " + c.getCid() + " and cmpid=" +
                   c.getCompanyid() + "");

            if (rs.next()) {
                curbal = rs.getDouble(1);
                city = rs.getString(2);

            }

            curbal = curbal + c.getTotalamt();

            String qr =
                "UPDATE `Curr_Bal` SET `bal` =" + curbal + " WHERE `accno`=" + c.getCid() + " and cmpid=" + c.getCompanyid() +
                "";
            System.out.println("update----------->" + qr);
            int k = stmt.executeUpdate(qr);

            qr =
 "UPDATE `Customer` SET `currbal` = " + curbal + " WHERE `acccode`=" + c.getCid() + " and cmpid=" + c.getCompanyid() +
    "";
            k = stmt.executeUpdate(qr);
            System.out.println("update----------->" + qr);
            if (k > 0) {
                Ledger();
                FacesContext.getCurrentInstance().addMessage(null,
                                                             new FacesMessage(FacesMessage.SEVERITY_INFO, "CHEQUE DETAILS HAS BEEN UPDATED",
                                                                              ""));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void totalamt() {
        if (c.getAmount() != 0 && c.getBankcharge() != 0) {
            try {
                Double total = c.getAmount() + c.getBankcharge();
                c.setTotalamt(total);
            } catch (NullPointerException e) {
                e.printStackTrace();
                //  c.setTotalamt(c.getAmount());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public void search() {
        System.out.println("in Search");
        try {
            st = con.createStatement();
            String qry = "SELECT *  FROM `ChqReturns` where cmpid=" + c.getCompanyid() + "";
            ResultSet rs = st.executeQuery(qry);
            al = new ArrayList<chequereturnsprofile>();
            while (rs.next()) {
                chequereturnsprofile s = new chequereturnsprofile();
                s.setSno(rs.getInt(2));
                s.setCid(rs.getInt(3));
                s.setCustomername(rs.getString(4));
                s.setType(rs.getString(5));
                s.setChqdate(rs.getString(6));
                s.setChqno(rs.getString(7));
                s.setAmount(rs.getDouble(8));
                s.setBankcharge(rs.getDouble(9));
                s.setReturndate(rs.getString(10));
                s.setNotes(rs.getString(11));
                s.setChno(rs.getString(12));
                s.setTotalamt(rs.getDouble(13));
                s.setRep(rs.getString(14));
                s.setRid(rs.getInt(15));
                s.setCity(rs.getString(16));
                al.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onRowSelect(SelectEvent event) throws ParseException {
        chequereturnsprofile r = (chequereturnsprofile)event.getObject();
        System.out.println("the selected details in edit are" + r.getSno());
        if (c != null) {
            c = r;
            setChequedate(df.parse(r.getChqdate()));
            setStatus(false);
        } else {
            setStatus(true);
        }
    }

    public void refresh() {
        // System.out.println("to to to to toooo");
        status = true;
        c = new chequereturnsprofile();
        setCheqereturndate(null);
        setChequedate(null);
        SNO();
        setCheqereturndate(new Date());
        //  c = new custdetails();
        //   RequestContext.getCurrentInstance().reset("form");

    }

    public void update() {
        System.out.println("the query is is:========== ");
        c.setCompanyid(1);
        try {
            st = con.createStatement();
            String qry =
                "update  chqreturns set    cid=" + c.getCid() + " ,cname= '" + c.getCustomername() + "'  ,type='" +
                c.getType() + "' , chqdate='" + c.getChqdate() + "' ,  chqno='" + c.getChqno() + "'  ,amt=" +
                c.getAmount() + "   ,fine=" + c.getBankcharge() + ", retdate= '" + c.getReturndate() + "' , notes='" +
                c.getNotes() + "' ,  chno='" + c.getChno() + "',totalamt=" + c.getTotalamt() + ",rep='" + c.getRep() +
                "',rid=" + c.getRid() + ",city='" + c.getCity() + "' where cmpid=" + c.getCompanyid() + " and sno=" +
                c.getSno() + " ";
            System.out.println("the query is is: " + qry);
            st.executeUpdate(qry);
            FacesMessage msg = new FacesMessage("Updated Successfully.", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void getcustnnames() throws SQLException, Exception {
        System.out.println("in get customer names func");
        try {
           
            ArrayList<voucherprofile> temp = new ArrayList<voucherprofile>();
            temp = accountsdao.getvoucherdetails(getCurr_companyid());
            for (int i = 0; i < temp.size(); i++) {
                voucherprofile p = (voucherprofile)temp.get(i);
                if (p.getPaytype().equalsIgnoreCase("cheque") || p.getPaytype().equalsIgnoreCase("dd")) {
                    voucherdeatils.add(p);
                }
            }
            System.out.println("the voucherdetails are" + voucherdeatils.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCustSelect(SelectEvent se) throws ParseException {
        voucherprofile r = (voucherprofile)se.getObject();
        c.setCustomername(r.getCustomername());
        c.setType(r.getPaytype());
        // c.setChqdate(r.getVoucherdate()
        setChequedate(df.parse(r.getVoucherdate()));
        c.setChqno(r.getCheque_ddno());
        c.setAmount(r.getAmount());
        c.setRep(r.getRepname());
        c.setRid(r.getRepid());
        c.setCity(r.getCity());
        c.setCid(r.getAccountcode());
    }

    public void setC(chequereturnsprofile c) {
        this.c = c;
    }

    public chequereturnsprofile getC() {
        return c;
    }

    public void setAl(ArrayList<chequereturnsprofile> al) {
        this.al = al;
    }

    public ArrayList<chequereturnsprofile> getAl() {
        return al;
    }

    public void setArray(ArrayList<chequereturnsprofile> array) {
        this.array = array;
    }

    public ArrayList<chequereturnsprofile> getArray() {
        return array;
    }

    public void setDf(SimpleDateFormat df) {
        this.df = df;
    }

    public SimpleDateFormat getDf() {
        return df;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setChequedate(Date chequedate) {
        this.chequedate = chequedate;
    }

    public Date getChequedate() {
        return chequedate;
    }

    public void setCheqereturndate(Date cheqereturndate) {
        this.cheqereturndate = cheqereturndate;
    }

    public Date getCheqereturndate() {
        return cheqereturndate;
    }

    public void setC1(chequereturnsprofile c1) {
        this.c1 = c1;
    }

    public chequereturnsprofile getC1() {
        return c1;
    }

    public void setCn(chequereturnsprofile cn) {
        this.cn = cn;
    }

    public chequereturnsprofile getCn() {
        return cn;
    }

    public void setVoucherdeatils(ArrayList<voucherprofile> voucherdeatils) {
        this.voucherdeatils = voucherdeatils;
    }

    public ArrayList<voucherprofile> getVoucherdeatils() {
        return voucherdeatils;
    }


    public void setCurr_companyid(int curr_companyid) {
        this.curr_companyid = curr_companyid;
    }

    public int getCurr_companyid() {
        return curr_companyid;
    }

    public void setCurbal(double curbal) {
        this.curbal = curbal;
    }

    public double getCurbal() {
        return curbal;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}
