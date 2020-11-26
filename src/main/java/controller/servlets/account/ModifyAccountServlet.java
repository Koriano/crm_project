package controller.servlets.account;

import controller.DAO.AccountDAO;
import controller.DAO.ContactDAO;
import controller.DAO.SectorDAO;
import model.Account;
import model.Contact;
import model.Sector;
import model.forms.AccountForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ModifyAccountServlet extends HttpServlet {
    private static final String PARAM_USERNAME = "username";

    private static final String ATT_RIGHTS = "rights";
    private static final String ATT_CONTACTS = "contacts";
    private static final String ATT_SECTORS = "sectors";
    private static final String ATT_ACCOUNT = "account";
    private static final String ATT_FORM = "form";

    private static final String VIEW = "/WEB-INF/admin/modifyAccount.jsp";
    private static final String URL_REDIRECT = "/rights";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get account to be modified
        AccountDAO accountDAO = AccountDAO.getInstance();
        String username = req.getParameter(PARAM_USERNAME);
        Account account = accountDAO.getAccountByName(username);

        // Set request parameter
        req.setAttribute(ATT_ACCOUNT, account);
        this.setRequestAttributes(req);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get accountDAO
        AccountDAO accountDAO = AccountDAO.getInstance();

        // Create form and account
        AccountForm form = new AccountForm();
        Account modified_account = form.createAccount(req);

        // If no error, save account and redirect to rights
        if (form.getErrors().isEmpty()){
            accountDAO.updateAccount(modified_account);
            resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
        }
        // If errors, forward to view with form
        else {
            req.setAttribute(ATT_ACCOUNT, modified_account);
            req.setAttribute(ATT_FORM, form);
            this.setRequestAttributes(req);

            this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
        }
    }

    private void setRequestAttributes(HttpServletRequest req){
        // DAOs
        AccountDAO accountDAO = AccountDAO.getInstance();
        ContactDAO contactDAO = ContactDAO.getInstance();
        SectorDAO sectorDAO = SectorDAO.getInstance();

        // Get all rights
        ArrayList<String> rights = accountDAO.getAllRight();
        ArrayList<Contact> contacts = contactDAO.getAllContacts();
        ArrayList<Sector> sectors = sectorDAO.getAllSectors();

        // Set attributes
        req.setAttribute(ATT_RIGHTS, rights);
        req.setAttribute(ATT_CONTACTS, contacts);
        req.setAttribute(ATT_SECTORS, sectors);
    }
}
