/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryapplication;

import Database.InventoryDatabase;

import java.io.File;
import java.io.Serializable;

import javax.servlet.ServletContext;


/**
 *
 * @author greenbuds
 */
public final class Inventoryapplication implements Serializable {
    private static Inventoryapplication invapp;
    @SuppressWarnings("compatibility:2926880933626436126")
    private static final long serialVersionUID = 1L;
    private String applicationTitle = "School Management";
    private String sCode = "";
    private String sName = "";
    private ServletContext context;
    private static String DBDriver;
    private static String DBURL;
    private static String DBUserName;
    private static String DBPassword;
    private static String logoPath;
    private static String provider;
    private static boolean allUsersLogin = true;
    private static boolean proxyUsed = false;
    private static String proxyHost = "";
    private static String proxyport = "";

    private static String alertMessage = "Currently Site is in Maintenance..!. Please try After Some Time";
    private String supportAttachementsPath;

    public static String getProvider() {
        return provider;
    }

    private String title;
    private static String bannerPath;
    private static String studentsPhotoPath;
    private static String staffPhotoPath;

    public static String getStudentsPhotoPath() {
        return studentsPhotoPath;
    }

    public static void setStudentsPhotoPath(String studentsPhotoPath) {
        invapp.studentsPhotoPath = studentsPhotoPath;
    }

    public String getStudentsPhotoFullPath() {
        String ll = "";
        if (getContext() != null)
            ll = getContext().getRealPath(getStudentsPhotoPath());
        else
            ll = "C:\\inetpub\\vhosts\\greenbudsems.com\\httpdocs\\resources\\images\\students";
        return ll;
    }

    public String getLogoFullPath() {
        String ll = "";
        if (getContext() != null)
            ll = getContext().getRealPath(getLogoPath());
        else
            ll = "C:\\inetpub\\vhosts\\greenbudsems.com\\httpdocs\\resources\\images\\logo";
        return ll;
    }

    public String getReportsPath() {
        String ll = "";
        if (getContext() != null)
            ll = getContext().getRealPath("/reports");
        else
            ll = "C:\\inetpub\\vhosts\\greenbudsems.com\\httpdocs\\reports";
        return ll;
    }

    public static String getBackupsBath() {
        if (System.getProperty("os.name").toLowerCase().contains("linux"))
            return File.separator + "home" + File.separator + "greenbud" + File.separator + "backups";
        else
            return "C:" + File.separator + "backups";
    }

    public String getReportSourcePath() {
        String ll = "";
        if (getContext() != null)
            ll = getContext().getRealPath("/reportSource");
        else
            ll = "C:\\inetpub\\vhosts\\greenbudsems.com\\httpdocs\\reportSource";
        return ll;
    }

    public String getBannerPath() {
        return bannerPath;
    }

    public void setBannerPath(String bannerPath) {
        this.bannerPath = bannerPath;
    }

    public Inventoryapplication() {

    }

    public Inventoryapplication(ServletContext context) throws Exception {
        if (context == null || !(context instanceof ServletContext)) {
            try {
                ///  throw new SchoolException("Parameter context is not an instance of ServletContext.");
            } catch (Exception e) {
                context = null;
            }
        } else {
            this.context = context;
            return;
        }
    }

    public static void init(ServletContext context) throws Exception {
        InventoryDatabase db;

        DBDriver = (String)context.getAttribute("DBDriver");
        DBURL = (String)context.getAttribute("DBURL");
        DBUserName = (String)context.getAttribute("DBUserName");
        DBPassword = (String)context.getAttribute("DBPassword");
        logoPath = (String)context.getAttribute("logoPath");
        provider = (String)context.getAttribute("provider");
        alertMessage = (String)context.getAttribute("alertMessage");
        bannerPath = (String)context.getAttribute("bannerPath");
        studentsPhotoPath = (String)context.getAttribute("studentsPhotoPath");
        staffPhotoPath = (String)context.getAttribute("staffPhotoPath");
        invapp = new Inventoryapplication(context);

        //JOptionPane.showMessageDialog(new JApplet(),""+schoolApplication.DBPassword,"Message",1);
        invapp.applicationTitle = (String)context.getAttribute("title");
        db = null;
        try {
            db = invapp.getDatabase();
        } catch (Exception e) {
            // System.out.println((new StringBuilder("Error while Connecting to Database: ")).append(e).toString());
            e.printStackTrace();
        }
        if (db != null)
            db.close();
        context.removeAttribute("logPath");
        context.removeAttribute("title");
        context.removeAttribute("DBDriver");
        context.removeAttribute("DBURL");
        context.removeAttribute("DBUserName");
        context.removeAttribute("DBPassword");
        // System.out.println("Parameters Removed");
        //JOptionPane.showMessageDialog(new JApplet(),"Loaded","Message",1);
    }

    public InventoryDatabase getDatabase() throws Exception {
        //JOptionPane.showMessageDialog(new JApplet(),"Con","Message",1);
        return new InventoryDatabase(DBDriver, DBURL, DBUserName, DBPassword);
    }

    public static Inventoryapplication getInvApplication() {
        return invapp;
    }

    public void setSchoolApplication(Inventoryapplication invapp) {
        this.invapp = invapp;
    }

    public static String getDBDriver() {
        return DBDriver;
    }

    public static void setDBDriver(String DBDriver) {
        Inventoryapplication.DBDriver = DBDriver;
    }

    public static String getDBPassword() {
        return DBPassword;
    }

    public static void setDBPassword(String DBPassword) {
        Inventoryapplication.DBPassword = DBPassword;
    }

    public static String getDBURL() {
        return DBURL;
    }

    public static void setDBURL(String DBURL) {
        Inventoryapplication.DBURL = DBURL;
    }

    public static String getDBUserName() {
        return DBUserName;
    }

    public static void setDBUserName(String DBUserName) {
        Inventoryapplication.DBUserName = DBUserName;
    }

    public ServletContext getContext() {
        return context;
    }

    public void setContext(ServletContext context) {
        this.context = context;
    }

    public static String getLogoPath() {
        //System.out.println("Logo Path from Getter :"+logoPath);
        return logoPath;
    }

    public static void setLogoPath(String logoPath) {
        Inventoryapplication.logoPath = logoPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getApplicationTitle() {
        return applicationTitle;
    }

    public void setApplicationTitle(String applicationTitle) {
        this.applicationTitle = applicationTitle;
    }

    public String getsCode() {
        return sCode;
    }

    public void setsCode(String sCode) {
        this.sCode = sCode;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getSupportAttachementsPath() {
        return context.getRealPath("/support");
    }

    public static void setProvider(String provider) {
        Inventoryapplication.provider = provider;
    }

    public void setSupportAttachementsPath(String supportAttachementsPath) {
        this.supportAttachementsPath = supportAttachementsPath;
    }

    public String getSupportAttachementsPath1() {
        return supportAttachementsPath;
    }

    public static void setAllUsersLogin(boolean allUsersLogin) {
        Inventoryapplication.allUsersLogin = allUsersLogin;
    }

    public static boolean isAllUsersLogin() {
        return allUsersLogin;
    }

    public static void setAlertMessage(String alertMessage) {
        Inventoryapplication.alertMessage = alertMessage;
    }

    public static String getAlertMessage() {
        return alertMessage;
    }

    public void setStaffPhotoPath(String staffPhotoPath) {
        Inventoryapplication.staffPhotoPath = staffPhotoPath;
    }

    public String getStaffPhotoPath() {
        String ll = "";
        if (getContext() != null)
            ll = getContext().getRealPath(staffPhotoPath);
        else
            ll = "C:\\inetpub\\vhosts\\greenbudsems.com\\httpdocs\\resource\\images\\staff";
        return ll;
    }

    public static void setProxyUsed(boolean proxyUsed) {
        Inventoryapplication.proxyUsed = proxyUsed;
    }

    public static boolean isProxyUsed() {
        return proxyUsed;
    }

    public static void setProxyHost(String proxyHost) {
        Inventoryapplication.proxyHost = proxyHost;
    }

    public static String getProxyHost() {
        return proxyHost;
    }

    public static void setProxyport(String proxyport) {
        Inventoryapplication.proxyport = proxyport;
    }

    public static String getProxyport() {
        return proxyport;
    }
}


