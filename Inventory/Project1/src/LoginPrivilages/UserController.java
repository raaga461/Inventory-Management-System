package LoginPrivilages;

import InventoryBeans.Company;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import InventoryBeans.user;

public class UserController {

    public String userid;
    public String password;
    private user userdetails;

    public UserController() {
        super();
    }

    public String login() throws Exception {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)fc.getExternalContext().getSession(true);
        HttpServletRequest request = (HttpServletRequest)fc.getExternalContext().getRequest();
        String alert = "";
        try {
            if (AccountUtil.checkLogin(getUserid(), getPassword())) {
                user us = new Profiles().getuserdetails(getUserid());
                userdetails = us;
                Company c = Profiles.getcompanyprofile(getUserid());
                System.out.println("the company id is" + c.getCompanyid());
                session.setAttribute("loggeduser", getUserid());
                session.setAttribute("userprofile", us);
                session.setAttribute("company", c);
                System.out.println("Login successfull");
                FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(),
                                                                                                           null,
                                                                                                           "/pages/home?faces-redirect=true");
            } else {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Invalid Login", ""));
            }
        } catch (Exception e) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Invalid Login", ""));
        }

        //FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/faces/index.xhtml?faces-redirect=true");
        return "failure";
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUserdetails(user userdetails) {
        this.userdetails = userdetails;
    }

    public user getUserdetails() {
        return userdetails;
    }
}
