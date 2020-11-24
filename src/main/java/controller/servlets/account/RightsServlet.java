package controller.servlets.account;

import model.Account;
import model.Contact;
import model.Sector;
import model.forms.AccountRightsForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class RightsServlet extends HttpServlet {
    private static final String ATT_ACCOUNTS = "accounts";
    private static final String ATT_RIGHTS = "rights";
    private static final String ATT_FORM = "form";

    private static final String VIEW = "/WEB-INF/admin/rights.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get every account and right
//        AccountDAO accountDAO = AccountDAO.getInstance();
//        ArrayList<Account> account_list = accountDAO.getAllAccount();
//        ArrayList<String> rights = accountDAO.getAllRights();

        // SIMULATION A SUPPR ******************
        ArrayList<String> rights = new ArrayList<>();
        rights.add("Administrateur");
        rights.add("Alimentation");
        rights.add("Lecture seule");

        ArrayList<Account> accounts = new ArrayList<>();

        Contact contact = new Contact("Hamon", "Alexandre", "Eleve", null, false, 0);
        ArrayList<Sector> sectors = new ArrayList<>();
        sectors.add(new Sector("slt"));

        accounts.add(new Account("alex29", "1234azerty", "Alex", "Administrateur", contact, sectors));
        accounts.add(new Account("bibi29","1234","Bibi", "Lecture seule", contact, sectors));
        // *************************************


        req.setAttribute(ATT_ACCOUNTS, accounts);
        req.setAttribute(ATT_RIGHTS, rights);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get accountDAO to get all rights
//        AccoountDAO accountDAO = AccountDAO.getInstance();
//        ArrayList<String> rights = accountDAO.getAllRights();

        // SIMULATION A SUPPR *********************
        ArrayList<String> rights = new ArrayList<>();
        rights.add("Administrateur");
        rights.add("Alimentation");
        rights.add("Lecture seule");
        // ****************************************

        // Create form and get all modified contacts
        AccountRightsForm acc_form = new AccountRightsForm();
        ArrayList<Account> accounts = acc_form.updateRights(req);

        req.setAttribute(ATT_RIGHTS, rights);
        req.setAttribute(ATT_ACCOUNTS, accounts);
        req.setAttribute(ATT_FORM, acc_form);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }
}
