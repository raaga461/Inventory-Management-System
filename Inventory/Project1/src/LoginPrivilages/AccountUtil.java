package LoginPrivilages;

import Database.InventoryDatabase;

import inventoryapplication.Inventoryapplication;

public class AccountUtil {
    public static boolean checkLogin(String userid, String password) throws Exception {

        InventoryDatabase db;
        boolean login = false;
        try {

            db =Inventoryapplication.getInvApplication().getDatabase();
            //db = new InventoryDatabase();
            //System.out.println(db.checkLogin(userid,password));
            if (db.checkLogin(userid, password))
                login = true;
            else {
                System.out.println("Failed");
                // throw new SchoolException("Invalid Login");
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        /*    if (db != null)
            db.close(); */
        return login;
    }
}
