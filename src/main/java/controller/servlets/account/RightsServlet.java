package controller.servlets.account;

import controller.DAO.AccountDAO;
import model.Account;
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
        AccountDAO accountDAO = AccountDAO.getInstance();
        ArrayList<Account> account_list = accountDAO.getAllAccounts();
        ArrayList<String> rights = accountDAO.getAllRight();

        req.setAttribute(ATT_ACCOUNTS, account_list);
        req.setAttribute(ATT_RIGHTS, rights);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get accountDAO to get all rights
        AccountDAO accountDAO = AccountDAO.getInstance();
        ArrayList<String> rights = accountDAO.getAllRight();

        // Create form and get all modified contacts
        AccountRightsForm acc_form = new AccountRightsForm();
        ArrayList<Account> accounts = acc_form.updateRights(req);

        req.setAttribute(ATT_RIGHTS, rights);
        req.setAttribute(ATT_ACCOUNTS, accounts);
        req.setAttribute(ATT_FORM, acc_form);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }
}
