package LoginPrivilages;

import Database.InventoryDatabase;

import InventoryBeans.Company;
import InventoryBeans.user;

import exceptions.NoDataException;

import java.sql.SQLException;

import java.util.ArrayList;

public class Profiles {
    public static user getuserdetails(String userid) throws SQLException, Exception {
        InventoryDatabase db;
        user u = new user();
        ArrayList arrayList = new ArrayList();
        try {
            db = new InventoryDatabase();
            u = db.getuserdetails(userid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*  if (db != null)
            db.close(); */
        return u;
    }

    public static Company getcompanyprofile(String userid) throws SQLException, Exception {
        InventoryDatabase db;
        Company co = new Company();
        try {
            db = new InventoryDatabase();
            int companyid = db.getUsercompany(userid);
            if (companyid == 0) {
            }
            co = db.getCompanyProfile(companyid);
            System.out.println("the company id is"+co.getCompanyid());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*  if (db != null)
            db.close(); */
        return co;
    }
}

