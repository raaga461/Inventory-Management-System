package Database;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;

public class InvDatabase {
    public InvDatabase() {
        super();
    }

    private static Connection con;

    public static void createConnection(String dbUrl, String dbusername, String dbPassword, String dbdriver) {
        try {
            Class.forName(dbdriver);
            con = (Connection)DriverManager.getConnection(dbUrl, dbusername, dbPassword);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return con;
    }

}
