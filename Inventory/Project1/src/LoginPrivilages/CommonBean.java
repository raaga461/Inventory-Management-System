package LoginPrivilages;

import InventoryBeans.Company;

import InventoryBeans.companybalance;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primefaces.component.menuitem.MenuItem;


public class CommonBean {

    HttpSession session;
    Company comp;

    public CommonBean() {
        super();
        FacesContext fc = FacesContext.getCurrentInstance();
        session = (HttpSession)fc.getExternalContext().getSession(false);
        try {
            HttpServletRequest request = (HttpServletRequest)fc.getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse)fc.getExternalContext().getResponse();
            //  uc = (UserController)session.getAttribute("userController");
            //   sch = (School)session.getAttribute("school");
            /* if (session.getAttribute("login") == null) {
                FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(),
                                                                                                           null,
                                                                                                           "/index.xhtml?faces-redirect=true");
            } */
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(),
                                                                                                       null,
                                                                                                       "/index.xhtml?faces-redirect=true");
        }
    }

    public void add() {
        System.out.println("the slected menu value is");
    }

    public void getNavigator(ActionEvent evt) throws Exception {
        System.out.println("The selected Menu value is in GetNavigatorrrrrrrrrrrrrrrrrr");
        // UserController uc = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
        try {
            HttpServletRequest request = (HttpServletRequest)fc.getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse)fc.getExternalContext().getResponse();
            //uc = (UserController)session.getAttribute("userController");
            comp = (Company)session.getAttribute("company");
            if (session.getAttribute("loggeduser") == null) {
                System.out.println("The selected Menu value is in GetNavigator");
                FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(),
                                                                                                           null,
                                                                                                           "/index.xhtml?faces-redirect=true");
            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(),
                                                                                                       null,
                                                                                                       "/index.xhtml?faces-redirect=true");
        }
        MenuItem me;
        me = (MenuItem)evt.getSource();
        String choice = me.getValue().toString();

        if (choice.equalsIgnoreCase("New Customer")) {
            System.out.println("The  Selected Menu Value is" + choice);
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(),
                                                                                                       null,
                                                                                                       "/pages/masters/addcust.xhtml?faces-redirect=true");
        } else if (choice.equalsIgnoreCase("Add Taxation")) {
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(),
                                                                                                       null,
                                                                                                       "/pages/Taxation/addtaxation.xhtml?faces-redirect=true");
        } else if (choice.equalsIgnoreCase("Edit Taxation")) {
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(),
                                                                                                       null,
                                                                                                       "/pages/Taxation/taxationedit.xhtml?faces-redirect=true");
        } else if (choice.equalsIgnoreCase("Voucher Entry")) {
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(),
                                                                                                       null,
                                                                                                       "/pages/accounts/voucher.xhtml?faces-redirect=true");
        } else if (choice.equalsIgnoreCase("ChequeReturns")) {
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(),
                                                                                                       null,
                                                                                                       "/pages/accounts/chequereturns.xhtml?faces-redirect=true");
        }
    }

    public void setComp(Company comp) {
        this.comp = comp;
    }

    public Company getComp() {
        return comp;
    }
}
