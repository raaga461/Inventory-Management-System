package InventoryDaos;

import Database.InventoryDatabase;

import InventoryBeans.companybalance;
import InventoryBeans.voucherprofile;

import exceptions.InventoryException;

import inventoryapplication.Inventoryapplication;

import java.io.Serializable;

import java.sql.SQLException;

import java.util.ArrayList;

public class accountsdao implements Serializable {

    public accountsdao() {
        super();
        //   db = new InventoryDatabase();
    }


    public static String updateAdmissions() throws InventoryException {
        InventoryDatabase db;
        String msg = "";
        try {

            db = new InventoryDatabase();
        } catch (Exception e) {
            throw new InventoryException(e);
        }
        if (db != null)
            db.close();
        return msg;
    }


    /* public static void main(String [] args) throws InventoryException {
    accountsdao b=new accountsdao();
    b.updateAdmission();

             } */

    public static String adddvoucherentry(voucherprofile voucherprofile) throws SQLException, Exception {
        InventoryDatabase db;
        String msg = "";
        try {
            db = Inventoryapplication.getInvApplication().getDatabase();
            //db = new InventoryDatabase();
            db.addvoucherentry(voucherprofile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // if (db != null)
        //  db.close();
        return msg;
    }

    public static String updatevoucherentry(voucherprofile vouc) throws SQLException, Exception {
        InventoryDatabase db;
        String msg = "";
        try {
            db = Inventoryapplication.getInvApplication().getDatabase();
            // db = new InventoryDatabase();
            db.updatevoucherentry(vouc);

        } catch (Exception e) {
            e.printStackTrace();

        }
        //  if (db != null)
        //   db.close();
        return msg;
    }


    public static companybalance getcompanybalance(int companyid) throws SQLException, Exception {
        InventoryDatabase db;
        companybalance c = new companybalance();
        try {

            db = new InventoryDatabase();
            c = db.getcompanybalance(companyid);

        } catch (Exception e) {
            e.printStackTrace();

        }
        //  if (db != null)
        //   db.close();
        return c;
    }

    public static int idGen(String tbl_name, String field, int companyid) throws Exception {
        InventoryDatabase db;
        int id = 0;
        try {
            db = new InventoryDatabase();
            id = (int)(Math.random() * 9999) + 1000;
            for (; !db.checkId(tbl_name, field, "" + id); id = ((int)(Math.random() * 9999) + 1000))
                ;

        } catch (Exception e) {
            throw e;
        }
        return id;
    }

    public static ArrayList getvoucherdetails(int companyid) throws SQLException, Exception {
        InventoryDatabase db;
        ArrayList data = new ArrayList();
        try {
            // db = Inventoryapplication.getInvApplication().getDatabase();
            //    System.out.println("the connection name is"+ db.getConnection());
            db = new InventoryDatabase();
            data = db.getvoucherdetails(companyid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //  if (db != null)
        //   db.close();
        return data;
    }


    public static ArrayList getcustomersforcompany(int companyid) throws SQLException, Exception {
        InventoryDatabase db;
        ArrayList arrayList = new ArrayList();
        try {
            db = new InventoryDatabase();
            arrayList = db.getcustomersforcompany(companyid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // if (db != null)
        //   db.close();
        return arrayList;
    }


    public static ArrayList getvendorsforcompany(int companyid) throws SQLException, Exception {
        InventoryDatabase db;
        ArrayList arrayList = new ArrayList();
        try {
            db = new InventoryDatabase();
            arrayList = db.getvendorsforcompany(companyid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // if (db != null)
        //   db.close();
        return arrayList;
    }

}

