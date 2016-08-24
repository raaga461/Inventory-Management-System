package InventoryDaos;

import Database.InventoryDatabase;

import InventoryBeans.Company;
import InventoryBeans.companybalance;
import InventoryBeans.voucherprofile;



import inventoryapplication.Inventoryapplication;

import java.sql.SQLException;

import java.util.ArrayList;

import settingsbeans.changepassword;
import settingsbeans.head;
import settingsbeans.klu;

public class SettingsDao {
    public SettingsDao() {
        super();
    }
    
    public static Company  getCompanyProfile(int companyid) throws SQLException {
        
      Company c=null;
        try {
            InventoryDatabase db;
          //  System.out.println("afdffds"+cmpid);
          //   db = Inventoryapplication.getInvApplication().getDatabase();
       db = new InventoryDatabase();
        c= db.getCompanyProfile(companyid);
        
          if (db != null)
         db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
    
    public static String  updateCompanyProfile(klu c) throws SQLException {
        
      String msg="";
        try {
            InventoryDatabase db;
          //  System.out.println("afdffds"+cmpid);
          //   db = Inventoryapplication.getInvApplication().getDatabase();
       db = new InventoryDatabase();
        msg= db.updateCompanyProfile(c);
        
          if (db != null)
         db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }
    
    public static head  getPdfHeadings(String cmpid) throws SQLException {
        
        head h=null;
        try {
        
        InventoryDatabase db;
        db = new InventoryDatabase();
        h = db.getPdfHeadings(cmpid);
        
          if (db != null)
         db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return h;
    }
    
    public static String  addPdfHeadings(head c1) throws SQLException {
        
        String msg="";
        try {
        
        InventoryDatabase db;
        db = new InventoryDatabase();
        msg = db.addPdfHeadings(c1);
        
          if (db != null)
         db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }
    
    public static String  saveChangePassword(changepassword c1) throws SQLException {
        
        String msg="";
        try {
        
        InventoryDatabase db;
        db = new InventoryDatabase();
        msg = db.saveChangePassword(c1);
        
          if (db != null)
         db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }
}
