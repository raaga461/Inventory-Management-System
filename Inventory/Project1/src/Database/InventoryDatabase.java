package Database;

import InventoryBeans.Company;
import InventoryBeans.companybalance;
import InventoryBeans.voucherprofile;

import MASTERBEANS.customerbean;

import InventoryBeans.routeprofile;

import InventoryBeans.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import settingsbeans.changepassword;
import settingsbeans.*;
import InventoryBeans.addtaxationbean;
import InventoryBeans.etax;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


public class InventoryDatabase {
    Statement st;
    public Connection con;
    private String driver;
    private String url;
    private String userName;
    private String password;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public InventoryDatabase() {
        super();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "root");
            System.out.println("db connected");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*   public InventoryDatabase() throws Exception {
        this("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inventory", "root", "");
    } */

    public InventoryDatabase(String driver, String url, String userName, String password) throws Exception {
        //JOptionPane.showMessageDialog(new JApplet(),""+url,"Message",1);
        this.driver = "com.mysql.jdbc.Driver";
        this.url = "jdbc:mysql://localhost:3306/inventory";
        this.userName = "root";
        this.password = "root";
        try {
            this.driver = driver;
            this.url = url;
            this.userName = userName;
            this.password = password;
            if (driver == null || "".equals(driver.trim()))
                this.driver = "org.mysql.jdbc.Driver";
            if (url == null || "".equals(url.trim()))
                this.url = "jdbc:mysql://127.0.0.1/inventory";
            if (userName == null || "".equals(userName.trim()))
                this.userName = "root";
            if (password == null || "".equals(password.trim()))
                this.password = "root";
            {
                Class.forName(this.driver);
                con = DriverManager.getConnection(this.url, this.userName, this.password);
            }
            close();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    protected void finalize() throws Throwable {
        if (!con.isClosed()) {
            try {
                close();
            } finally {
                super.finalize();
            }
        }
    }

    public synchronized void setAutoCommit(boolean autoCommit) throws SQLException {
        con.setAutoCommit(autoCommit);
    }

    public synchronized void commit() throws SQLException {
        con.commit();
    }

    public synchronized void rollback() throws SQLException {
        con.rollback();
    }

    public synchronized void reconnect() throws SQLException {
        close();

        con = DriverManager.getConnection(this.url, this.userName, this.password);
    }

    public synchronized Connection getConnection() throws SQLException {
        close();

        con = DriverManager.getConnection(this.url, this.userName, this.password);
        return con;
    }

    public synchronized void close() {
        try {
            con.close();
        } catch (Exception exception) {

        }
    }


    public synchronized boolean checkLogin(String userid, String password) throws SQLException {
        boolean valid = false;
        if (con.isClosed())
            reconnect();
        Statement st = con.createStatement();
        String query;
        ResultSet rs;
        query = "select * from inv_accounts where userid like '" + userid + "' and password like '" + password + "'";
        System.out.println(query);
        rs = st.executeQuery(query);
        if (rs.next())
            valid = true;
        st.close();
        rs.close();
        return valid;
    }

    public user getuserdetails(String userid) throws SQLException {
        if (con.isClosed())
            reconnect();
        boolean check = true;
        user u = new user();
        Statement st = con.createStatement();
        String query =
            "select p.userid,p.username,p.contact,a.role,a.status from inv_user_profiles p,inv_accounts a where p.userid like '" +
            userid + "' and a.userid=p.userid ";
        System.out.println(query);
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            u = new user();
            u.setUserid(rs.getString(1));
            u.setUsername(rs.getString(2));
            u.setContact(rs.getString(3));
            u.setRole(rs.getString(4));
            u.setStatus(rs.getString(5));

        }
        rs.close();
        st.close();
        return u;
    }


    public synchronized int getUsercompany(String userid) throws SQLException {
        if (con.isClosed())
            reconnect();
        int id = 0;
        Statement st = con.createStatement();
        String query = "select companyid from inv_company_user where userid like '" + userid + "'";
        System.out.println("The Query is" + query);
        ResultSet rs = st.executeQuery(query);
        if (rs.next())
            id = rs.getInt(1);
        return id;
    }

    public synchronized Company getCompanyProfile(int companyid) throws SQLException {
        if (con.isClosed())
            reconnect();
        Company c = new Company();
        Statement st = con.createStatement();
        String query = "select * from company where cmpid='" + companyid + "'";
        System.out.println("The Query is" + query);
        ResultSet rs = st.executeQuery(query);

        if (rs.next()) {
            c = new Company();
            c.setCompanyid(rs.getInt(1));
            c.setCompanyname(rs.getString(2));
            c.setLegalname(rs.getString(3));
            c.setAddress(rs.getString(4));
            c.setCity(rs.getString(5));
            c.setState(rs.getString(6));
            c.setPincode(rs.getString(7));
            c.setCountry(rs.getString(8));
            c.setPhoneoffice(rs.getString(9));
            c.setPhonemobile(rs.getString(10));
            c.setFax(rs.getString(11));
            c.setRegno(rs.getString(12));
            c.setTin(rs.getString(13));
            c.setVat(rs.getString(14));
            c.setPan(rs.getString(15));
            c.setBank(rs.getString(16));
            c.setIfs(rs.getString(17));
            c.setAccno(rs.getString(18));
            c.setEmail(rs.getString(19));
            c.setWebsite(rs.getString(20));
            c.setOpbal(rs.getDouble(21));
            c.setCuurbal(rs.getDouble(22));
            c.setClosebal(rs.getDouble(23));
        }
        return c;
    }

    public synchronized void addvoucherentry(voucherprofile vouc) throws SQLException, Exception {
        //  System.out.println("hellllllllllllllllllllllllllllll");
        if (con.isClosed())
            reconnect();
        String msg = "";
        Statement st = con.createStatement();
        try {
            String query =
                "insert into voucher values(" + vouc.getCompanyid() + "," + vouc.getVoucherno() + ",'" + vouc.getVoucherdate() +
                "','" + vouc.getVouchername() + "','" + vouc.getCustomername() + "','" + vouc.getAccountcode() +
                "','" + vouc.getCity() + "'," + vouc.getCust_currbal() + ",'" + vouc.getPaytype() + "','" +
                vouc.getBankname() + "','" + vouc.getCheque_ddno() + "','" + vouc.getAmount() + "','" +
                vouc.getNarration() + "','" + vouc.getNarration() + "'," + vouc.getCust_newbal() + "," +
                vouc.getCompbalance() + "," + vouc.getCompnewbalance() + "," + vouc.getRepid() + ",'" +
                vouc.getRepname() + "' ,'" + vouc.getUser() + "')";
            System.out.println("query is" + query);
            st.executeUpdate(query);
            String query1 =
                "update comp_bal set bal=" + vouc.getCompbalance() + " where cmpid=" + vouc.getCompanyid() + "";
            System.out.println(query1);
            st.executeUpdate(query1);
            String query2 =
                "update customer set currbal=" + vouc.getCust_currbal() + " where cmpid=" + vouc.getCompanyid() +
                " and acccode='" + vouc.getAccountcode() + "'";
            System.out.println(query2);
            st.executeUpdate(query2);
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO, "Details are saved Succesfully.",
                                                                          ""));
        } catch (Exception e) {
            // st.close();
            // con.rollback();
            //  con.setAutoCommit(true);
            throw e;
        }
        // con.setAutoCommit(true);
    }

    public synchronized void updatevoucherentry(voucherprofile vouc) throws SQLException, Exception {
        System.out.println("hellllllllllllllllllllllllllllll");
        if (con.isClosed())
            reconnect();
        String msg = "";
        Statement st = con.createStatement();
        try {
            String query =
                "update  voucher set vdate='" + vouc.getVoucherdate() + "',vname='" + vouc.getVouchername() +
                "',cname='" + vouc.getCustomername() + "',accode=" + vouc.getAccountcode() + ",city='" +
                vouc.getCity() + "',currbal=" + vouc.getCust_currbal() + ",ptype='" + vouc.getPaytype() + "',bank='" +
                vouc.getBankname() + "',chq='" + vouc.getCheque_ddno() + "',amt=" + vouc.getAmount() + ",nar1='" +
                vouc.getNarration() + "',modify='" + vouc.getNarration() + "',fin_currbal=" + vouc.getCust_newbal() +
                ",bal=" + vouc.getCompbalance() + ",fin_bal=" + vouc.getCompnewbalance() + ",rid=" + vouc.getRepid() +
                ",rep='" + vouc.getRepname() + "' ,comtype='" + vouc.getUser() + "' where compid=" +
                vouc.getCompanyid() + " and vno=" + vouc.getVoucherno() + "";
            System.out.println(query);
            st.executeUpdate(query);
            String query1 =
                "update comp_bal set bal=" + vouc.getCompbalance() + " where cmpid=" + vouc.getCompanyid() + "";
            System.out.println(query1);
            st.executeUpdate(query1);
            String query2 =
                "update customer set currbal=" + vouc.getCust_currbal() + " where cmpid=" + vouc.getCompanyid() +
                " and acccode='" + vouc.getAccountcode() + "'";
            System.out.println(query2);
            st.executeUpdate(query2);
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated succesfully.",
                                                                          ""));
        } catch (Exception e) {
            // st.close();
            // con.rollback();
            //  con.setAutoCommit(true);
            throw e;
        }
        // con.setAutoCommit(true);
    }

    public synchronized ArrayList getvoucherdetails(int companyid) throws SQLException, Exception {
        System.out.println("hellllllllllllllllllllllllllllll");
        if (con.isClosed())
            reconnect();
        ArrayList data = new ArrayList();
        Statement st = con.createStatement();
        try {
            String query = "select * from voucher where compid=" + companyid + "";
            System.out.println("the query is" + query);
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                voucherprofile vouc = new voucherprofile();
                vouc.setVoucherno(rs.getInt(2));
                vouc.setVoucherdate(df.format(rs.getDate(3)));
                vouc.setVouchername(rs.getString(4));
                vouc.setCustomername(rs.getString(5));
                vouc.setAccountcode(rs.getInt(6));
                vouc.setCity(rs.getString(7));
                vouc.setCust_currbal(rs.getDouble(8));
                vouc.setPaytype(rs.getString(9));
                vouc.setBankname(rs.getString(10));
                vouc.setCheque_ddno(rs.getString(11));
                vouc.setAmount(rs.getDouble(12));
                vouc.setNarration(rs.getString(13));
                vouc.setNarration(rs.getString(14));
                vouc.setCust_newbal(rs.getDouble(15));
                vouc.setCompbalance(rs.getDouble(16));
                vouc.setCompnewbalance(rs.getDouble(17));
                vouc.setRepid(rs.getInt(18));
                vouc.setRepname(rs.getString(19));
                vouc.setUser(rs.getString(20));
                data.add(vouc);
                //vouc.setn
            }
        } catch (Exception e) {
            // st.close();
            // con.rollback();
            //  con.setAutoCommit(true);
            throw e;
        }
        return data;
    }

    public synchronized ArrayList getcustomersforcompany(int companyid) throws SQLException, Exception {
        ArrayList data = new ArrayList();
        if (con.isClosed())
            reconnect();
        String msg = "";
        Statement st = con.createStatement();
        try {
            String query = "select * from customer where cmpid=" + companyid + "";
            System.out.println("the query is"+query);
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                customerbean s = new customerbean();
                
             
                s.setCompanyid(rs.getInt(1));
                s.setAccesscode(rs.getInt(2));
                s.setRegdate(rs.getDate(3));
                s.setCompanyname(rs.getString(4));
                s.setCategory(rs.getString(5));
                //s.setRepname(rs.getString(6));
                s.setOptype(rs.getString(6));
                s.setSubcategory(rs.getString(7));
                s.setAddress(rs.getString(8));
                s.setCity(rs.getString(9));
                s.setState(rs.getString(10));
                s.setPincode(rs.getString(11));
                s.setLandline(rs.getString(12));
                s.setMobile(rs.getString(13));
                s.setFaxno(rs.getString(14));
                s.setContactperson(rs.getString(15));
                //s.setContactphno(rs.getString(16));
                s.setEmail(rs.getString(16));
                s.setWebsite(rs.getString(17));
                s.setTransport(rs.getString(18));
                s.setTin(rs.getString(19));
                s.setSalestax(rs.getString(20));
                s.setBank(rs.getString(21));
                s.setAccountno(rs.getString(22));
                s.setIfsccode(rs.getString(23));
                s.setRouteid(rs.getInt(24));
                s.setRoute(rs.getString(25));
                s.setRouterep(rs.getString(26));
                s.setRoutephno(rs.getString(27));
                // s.setPan(rs.getString(22));
                //s.setCst(rs.getString(21));
                s.setDisc(rs.getDouble(28));
                s.setCreditlmtamount(rs.getDouble(29));
                s.setCreditlmtdays(rs.getInt(30));
                s.setCurrentbalance(rs.getDouble(31));
                s.setOpeningbalance(rs.getDouble(32));
                s.setClosingbalance(rs.getDouble(33));
//                s.setCompanyid(rs.getInt(1));
//                s.setAccesscode(rs.getInt(2));
//                s.setRegdate(rs.getDate(3));
//                s.setCompanyname(rs.getString(4));
//                s.setCategory(rs.getString(5));
//                s.setOptype(rs.getString(6));
//                s.setSubcategory(rs.getString(7));
//                s.setAddress(rs.getString(8));
//                s.setCity(rs.getString(9));
//                s.setState(rs.getString(10));
//                s.setPincode(rs.getString(11));
//                s.setLandline(rs.getString(12));
//                s.setMobile(rs.getString(13));
//                s.setContactperson(rs.getString(14));
//                s.setEmail(rs.getString(15));
//                s.setWebsite(rs.getString(16));
//                s.setTransport(rs.getString(17));
//                //here dint add pan number
//                s.setSalestax(rs.getString(19));
//                s.setBank(rs.getString(20));
//                s.setAccountno(rs.getString(21));
//                s.setIfsccode(rs.getString(22));
//                s.setRouteid(rs.getInt(23));
//                s.setRoute(rs.getString(24));
//                s.setRouterep(rs.getString(25));
//                s.setRoutephno(rs.getString(26));
//                s.setDisc(rs.getDouble(27));
//                s.setCreditlmtamount(rs.getInt(28));
//                s.setCreditlmtdays(rs.getInt(29));
//                s.setCurrentbalance(rs.getDouble(30));
//                s.setOpeningbalance(rs.getDouble(31));
//                s.setClosingbalance(rs.getDouble(32));
//                /*s.setSalestax(rs.getString(23));
//                s.setFaxno(rs.getString(13));
//                //s.setContactphno(rs.getString(16));
//                s.setTin(rs.getString(20));
//                // s.setPan(rs.getString(22));
//                //s.setCst(rs.getString(21));
//                s.setDisc(rs.getDouble(31));
//                s.setCreditlmtamount(rs.getDouble(32));
//                s.setCreditlmtdays(rs.getInt(33));
//                s.setOpeningbalance(rs.getDouble(34));
//                s.setCurrentbalance(rs.getDouble(35));
//                s.setBank(rs.getString(24));
//                s.setAccountno(rs.getString(25));
//                s.setIfsccode(rs.getString(26));
//                s.setRouteid(rs.getInt(27));
//                s.setRoute(rs.getString(28));
//                s.setRouterep(rs.getString(29));
//                s.setRoutephno(rs.getString(30)); */
                data.add(s);
              
            }
            System.out.println("after data is added.");
        } catch (Exception e) {
            st.close();
            //con.rollback();
           // con.setAutoCommit(true);
            throw e;
        }
        con.setAutoCommit(true);
        return data;
    }


    public synchronized ArrayList getvendorsforcompany(int companyid) throws SQLException, Exception {
        ArrayList data = new ArrayList();
        if (con.isClosed())
            reconnect();
        String msg = "";
        Statement st = con.createStatement();
        try {
            String query = "select * from vendors where cmpid=" + companyid + "";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                /*customerbean s = new customerbean();
                s.setCompanyid(rs.getInt(1));
                s.setAccesscode(rs.getInt(2));
                s.setRegdate(rs.getDate(3));
                s.setCompanyname(rs.getString(4));
                s.setCategory(rs.getString(5));
                s.setOptype(rs.getString(6));
                s.setSubcategory(rs.getString(7));
                s.setAddress(rs.getString(8));
                s.setCity(rs.getString(9));
                s.setState(rs.getString(10));
                s.setPincode(rs.getString(11));
                s.setLandline(rs.getString(12));
                s.setMobile(rs.getString(13));
                s.setContactperson(rs.getString(14));
                s.setEmail(rs.getString(15));
                s.setWebsite(rs.getString(16));
                s.setTransport(rs.getString(17));
                //here dint add pan number
                s.setSalestax(rs.getString(19));
                s.setBank(rs.getString(20));
                s.setAccountno(rs.getString(21));
                s.setIfsccode(rs.getString(22));
                s.setRouteid(rs.getInt(23));
                s.setRoute(rs.getString(24));
                s.setRouterep(rs.getString(25));
                s.setRoutephno(rs.getString(26));
                s.setDisc(rs.getDouble(27));
                s.setCreditlmtamount(rs.getDouble(28));
                s.setCreditlmtdays(rs.getInt(29));
                s.setCurrentbalance(rs.getDouble(30));
                s.setOpeningbalance(rs.getDouble(31));
                s.setClosingbalance(rs.getDouble(32));
                /*s.setSalestax(rs.getString(23));
                s.setFaxno(rs.getString(13));
                //s.setContactphno(rs.getString(16));
                s.setTin(rs.getString(20));
                // s.setPan(rs.getString(22));
                //s.setCst(rs.getString(21));
                s.setDisc(rs.getDouble(31));
                s.setCreditlmtamount(rs.getDouble(32));
                s.setCreditlmtdays(rs.getInt(33));
                s.setOpeningbalance(rs.getDouble(34));
                s.setCurrentbalance(rs.getDouble(35));
                s.setBank(rs.getString(24));
                s.setAccountno(rs.getString(25));
                s.setIfsccode(rs.getString(26));
                s.setRouteid(rs.getInt(27));
                s.setRoute(rs.getString(28));
                s.setRouterep(rs.getString(29));
                s.setRoutephno(rs.getString(30)); */
                //data.add(s);
            }

        } catch (Exception e) {
            st.close();
            con.rollback();
            con.setAutoCommit(true);
            throw e;
        }
        con.setAutoCommit(true);
        return data;
    }

    public synchronized boolean checkId(String tbl_name, String field, String id) throws SQLException {
        if (con.isClosed())
            reconnect();
        boolean check = true;
        Statement st = con.createStatement();
        String query = "select * from " + tbl_name + " where " + field + " like '" + id + "'";
        //System.out.println(query);
        ResultSet rs = st.executeQuery(query);
        if (rs.next())
            check = false;
        rs.close();
        st.close();
        return check;
    }


    public synchronized companybalance getcompanybalance(int companyid) throws SQLException, Exception {
        ArrayList data = new ArrayList();
        if (con.isClosed())
            reconnect();
        String msg = "";
        companybalance c = new companybalance();
        Statement st = con.createStatement();
        try {
            String query = "select * from comp_bal where cmpid=" + companyid + "";
            System.out.println("the query is" + query);
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                c.setCompanyid(rs.getInt(1));
                c.setCompanyname(rs.getString(2));
                c.setCompanybalance(rs.getDouble(3));
            }

        } catch (Exception e) {
            st.close();
            con.rollback();
            con.setAutoCommit(true);
            throw e;
        }
        con.setAutoCommit(true);
        return c;
    }

    public synchronized companybalance getroutedetails(int companyid) throws SQLException, Exception {
        ArrayList data = new ArrayList();
        if (con.isClosed())
            reconnect();
        String msg = "";
        companybalance c = new companybalance();
        Statement st = con.createStatement();
        try {
            String query = "select * from routemaster where cmpid=" + companyid + "";
            System.out.println("the query is" + query);
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                /*     routeprofile r=new routeprofile();
                r.setCompanyid(rs.getInt(1));
                r.setRouteid(rs.getInt(2));
                r.setTourname(rs.getString(3));
                r.setAddress(rs.get); */


            }

        } catch (Exception e) {
            st.close();
            con.rollback();
            con.setAutoCommit(true);
            throw e;
        }
        con.setAutoCommit(true);
        return c;
    }
//setting start
        //Vamsi Start
    public synchronized ArrayList<klu> getCompanyProfiles(String companyid) throws SQLException {
        
        if (con.isClosed())
            reconnect();
        //klu c = new klu();
       // System.out.println("Cpm :"+companyid);
        ArrayList result = new ArrayList();
        Statement st = con.createStatement();
        String query = "select * from company where cmpid='" + companyid + "'";
    //    System.out.println("The Query is" + query);
        ResultSet rs = st.executeQuery(query);
       
        if (rs.next()) {
            klu c = new klu();
            c.setId(rs.getString(1));
            c.setCname(rs.getString(2));
            c.setLname(rs.getString(3));
            c.setAdress(rs.getString(4));
            c.setCity(rs.getString(5));
            c.setState(rs.getString(6));
            c.setPincode(rs.getString(7));
            c.setCountry(rs.getString(8));
            c.setPhone(rs.getString(9));
            c.setMobile(rs.getString(10));
            c.setFax(rs.getString(11));
            c.setRno(rs.getString(12));
            c.setTin(rs.getString(13));
            c.setVat(rs.getString(14));
            c.setPan(rs.getString(15));
            c.setBank(rs.getString(16));
            c.setIfs(rs.getString(17));
            c.setAcc(rs.getString(18));
            c.setEmail(rs.getString(19));
            c.setWebsite(rs.getString(20));
            c.setOb(rs.getDouble(21));
            c.setCb(rs.getDouble(22));
            c.setCurrent(rs.getDouble(23));
            result.add(c);
            
        }
        return result;
    }
    
    public synchronized String updateCompanyProfile (klu c1) throws SQLException {
        
        if (con.isClosed())
            reconnect();
        String msg = "Updation Failed";
        Statement st = con.createStatement();
        String query =
            "UPDATE company SET regno=" + c1.getRno() + ",companyname='" +
            c1.getCname() + "',legalname='" + c1.getLname() + "',address='" + c1.getAdress() + "',city='" +
            c1.getCity() + "',state='" + c1.getState() + "',country='" + c1.getCountry() + "',pincode=" +
            c1.getPincode() + ",phn_off=" + c1.getPhone() + ",phn_mobile=" + c1.getMobile() + ",email='" +
            c1.getEmail() + "',website='" + c1.getWebsite() + "',vat='" + c1.getVat() + "',tin='" + c1.getTin() +
            "',bank='" + c1.getBank() + "',accno='" + c1.getAcc() + "',ifs='" + c1.getIfs() + "',opbal=" +
            c1.getOb() + ",currbal=" + c1.getCurrent() + ",closebal=" + c1.getCb() + ",pan='" +
            c1.getPan() + "',fax=" + c1.getFax() + " where cmpid= " + c1.getId() + "";
        if(st.executeUpdate(query)==1) msg = "Updated Successfully";
        return msg;
       
    }
    
    public synchronized String addPdfHeadings(head c1) throws SQLException {
        
        if (con.isClosed())
            reconnect();
        String msg = "Updation Failed";
        Statement st = con.createStatement();
        String query="select * from pdf where cmpid like "+c1.getCmpid()+"";
        ResultSet rs = st.executeQuery(query);
        if(rs.next()){
            
            rs.close();
            String query1="Update pdf set p1='"+c1.getTrade()+"',p2='"+c1.getCname()+"'," +
                "p3='"+c1.getAdress()+"',p4='"+c1.getPhone()+"',p5='"+c1.getEmail()+"'," +
                "p6='"+c1.getTin()+"',p7='"+c1.getIfs()+"',p8='"+c1.getAcc()+"'" +
                "where cmpid like '"+c1.getCmpid()+"'";
            if(st.executeUpdate(query1)==1) msg = "Updated Successfully";
        }
        else{
            
            String query2 =
                "insert into pdf values('" + c1.getTrade() + "','" + c1.getCname() + "','" + c1.getAdress() + "','" +
                c1.getPhone() + "','" + c1.getEmail() + "','" + c1.getTin() + "','" + c1.getIfs() + "','" +
                c1.getAcc() + "','" + c1.getImg() + "','"+c1.getCmpid()+"')";
            if(st.executeUpdate(query2)==1) msg = "Saved Successfully";
        }
        return msg;
       
    }
    
    public synchronized head getPdfHeadings(String cmpid) throws SQLException {
        
        if (con.isClosed())
            reconnect();
        String msg = "Getting Failed";
        head h=null;
        Statement st = con.createStatement();
        String query="select * from pdf where cmpid like '"+cmpid+"'";
        System.out.println("the query is"+query);
        ResultSet rs = st.executeQuery(query);
        if(rs.next()){
            h = new head();
            h.setTrade(rs.getString(1));
            h.setCname(rs.getString(2));
            h.setAdress(rs.getString(3));
            h.setPhone(rs.getString(4));
            h.setEmail(rs.getString(5));
            h.setTin(rs.getString(6));
            h.setIfs(rs.getString(7));
            h.setAcc(rs.getString(8));
            h.setImg(rs.getString(9));
            h.setCmpid(rs.getString(10));
        }
        return h;
       
    }
    
    public synchronized String saveChangePassword(changepassword pd) throws SQLException {
        
        if (con.isClosed())
            reconnect();
        String msg = "";
        Statement st = con.createStatement();
        System.out.println("in save change password method");
       // ResultSet rs = st.executeQuery("select * from login where userid='" + pd.getUserid() + "' and pass='" + pd.getPassword() + "'");
       // if (rs.next()) {
            //System.out.println("update login set pass='" + pd.getNewpassword() + "' where userid='" + uid+ "'");
          //  int j = st.executeUpdate("update login set pass='" + pd.getNewpassword() + "' where userid='" +pd.getUserid() + "'");
            int k = st.executeUpdate("update inv_accounts set password='" + pd.getNewpassword() + "' where userid='" +pd.getUserid() + "'");
          System.out.println("Password changed successfully");
            if( k>0) 
                msg = "Updated Successfully";
            //    }
        return msg;      
    }
    //Taxation
    
    public synchronized int generateId(String tblname) throws SQLException {
            
            if (con.isClosed())
                reconnect();
            int id = (int)(Math.random() * 9999) + 1000;
            String query = "select * from "+tblname+" where taxno like '"+id+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                
                generateId(tblname);
            }
            return id;
           
        }
        
        public synchronized String saveTaxDetails(addtaxationbean a1) throws SQLException {
            
            if (con.isClosed())
                reconnect();
            String msg = "Operation Failed";
            st = con.createStatement();
            String query =
                "insert into tax values(" + a1.getCompanyid() + "," + a1.getTaxno() + ",'" + a1.getRegion() + "','" +
                a1.getTaxname() + "'," + a1.getTaxperitem() + ",'" + a1.getCFormtax() + "'," +
                a1.getCFormtaxpercent() + ",'" + a1.getNotes() + "')";
            if(st.executeUpdate(query)==1) msg = "Saved Successfully";
            return msg;
           
        }
        
        public synchronized String updateTaxDetails(etax c1) throws SQLException {
            
            if (con.isClosed())
                reconnect();
            String msg = "Operation Failed";
            st = con.createStatement();
            String query =
                "UPDATE tax SET region='" + c1.getRegion() + "',taxname='" + c1.getTaxname() + "',tax=" + c1.getTaxperitem() +
                ",cf='" + c1.getCFormtax() + "',cftax='" + c1.getCftaxper() + "',notes='" + c1.getNotes() +
                "' where cmpid= " + c1.getCompanyid() + " and taxno=" + c1.getTaxno() + "";
            if(st.executeUpdate(query)==1) msg = "Updated Successfully";
            return msg;
           
        }
        
        public synchronized ArrayList<etax> getTaxDetails(int cmpid) throws SQLException {
            
            if (con.isClosed())
                reconnect();
            
            ArrayList<etax> result = new ArrayList<etax>();
            st = con.createStatement();
            String query ="Select * from tax where cmpid like '"+cmpid+"'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                etax s = new etax();
                s.setCompanyid(rs.getInt(1));
                s.setTaxno(rs.getInt(2));
                s.setRegion(rs.getString(3));
                s.setTaxname(rs.getString(4));
                s.setTaxperitem(rs.getInt(5));
                s.setCFormtax(rs.getString(6));
                s.setCftaxper(rs.getDouble(7));
                s.setNotes(rs.getString(8));
                result.add(s);
            }
            
            return result;
           
        }
        
        public synchronized String deleteTaxDetails(int cmpid,int taxno) throws SQLException {
            
            if (con.isClosed())
                reconnect();
            String msg = "Operation Failed";
            st = con.createStatement();
            String query ="delete from tax where cmpid like "+cmpid+" and taxno like "+taxno+"";
            if(st.executeUpdate(query)==1) msg = "Deleted Successfully";
            return msg;
           
        }
   
    //Vamsi End
}
