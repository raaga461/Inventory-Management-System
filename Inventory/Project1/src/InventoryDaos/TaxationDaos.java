package InventoryDaos;

import Database.InventoryDatabase;

import InventoryBeans.addtaxationbean;

import InventoryBeans.etax;

import java.sql.SQLException;

import java.util.ArrayList;

public class TaxationDaos {
    public TaxationDaos() {
        super();
    }
    
    public static int getTaxationId(String tblname) throws SQLException {
        
      int i=0;
        try {
            InventoryDatabase db;
          //  System.out.println("afdffds"+cmpid);
          //   db = Inventoryapplication.getInvApplication().getDatabase();
       db = new InventoryDatabase();
        i= db.generateId(tblname);
        
          if (db != null)
         db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
    
    public static String addTaxation(addtaxationbean a1) throws SQLException {
        
      String msg="";
        try {
            InventoryDatabase db;
          //  System.out.println("afdffds"+cmpid);
          //   db = Inventoryapplication.getInvApplication().getDatabase();
       db = new InventoryDatabase();
        msg= db.saveTaxDetails(a1);
        
          if (db != null)
         db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }
    
    public static String updateTaxation(etax a1) throws SQLException {
        
      String msg="";
        try {
            InventoryDatabase db;
          //  System.out.println("afdffds"+cmpid);
          //   db = Inventoryapplication.getInvApplication().getDatabase();
       db = new InventoryDatabase();
        msg= db.updateTaxDetails(a1);
        
          if (db != null)
         db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }
    
    public static String deleteTaxation(int cmpid,int taxno) throws SQLException {
        
      String msg="";
        try {
            InventoryDatabase db;
          //  System.out.println("afdffds"+cmpid);
          //   db = Inventoryapplication.getInvApplication().getDatabase();
       db = new InventoryDatabase();
        msg= db.deleteTaxDetails(cmpid,taxno);
        
          if (db != null)
         db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }
    
    public static ArrayList<etax> getTaxationDetails(int cmpid) throws SQLException {
        
      ArrayList<etax> result=new ArrayList<etax>();
        try {
            InventoryDatabase db;
          //  System.out.println("afdffds"+cmpid);
          //   db = Inventoryapplication.getInvApplication().getDatabase();
       db = new InventoryDatabase();
        result= db.getTaxDetails(cmpid);
        
          if (db != null)
         db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
