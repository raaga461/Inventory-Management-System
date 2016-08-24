package InventoryBeans;

public class user {
    private String userid;
    private String username;
    private String contact;
    private String role;
    private String status;

    public user() {
        super();
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }


    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }
   

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
