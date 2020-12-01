package controller.servlets.account;

import controller.DAO.AccountDAO;
import model.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class DeleteAccountServlet extends HttpServlet {
    private static final String PARAM_ACCOUNT_ID = "id";

    private static final String URL_REDIRECT_SUCCESS = "/rights";
    private static final String URL_REDIRECT_FAIL = "/rights/account";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.deleteAccount(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.deleteAccount(req, resp);
    }

    private void deleteAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get Account
        AccountDAO accountDAO = AccountDAO.getInstance();
        String id = req.getParameter(PARAM_ACCOUNT_ID);

        // Build fail url
        String redirect_url = req.getContextPath() + URL_REDIRECT_FAIL + "?id=" + id;

        try{
            Account account = accountDAO.getAccountById(Integer.parseInt(id));

            if (account != null && this.atLeastOneAdminExists(account)){
                // Delete account
                accountDAO.deleteAccount(account);

                resp.sendRedirect(req.getContextPath() + URL_REDIRECT_SUCCESS);
            } else {
                resp.sendRedirect(redirect_url);
            }
        } catch (Exception ignored){
            resp.sendRedirect(redirect_url);
        }
    }

    private boolean atLeastOneAdminExists(Account account){
        // Get every account
        AccountDAO accountDAO = AccountDAO.getInstance();
        ArrayList<Account> accounts = accountDAO.getAllAccounts();

        boolean at_least_one_admin = false;
        int count = 0;
        Account admin = null;

        // Check if there's at least 1 admin account
        for (Account acc:accounts){
            if ("Administrateur".equals(acc.getRight())){
                at_least_one_admin = true;
                admin = acc;
                count++;

                if (count == 2){
                    break;
                }

            }
        }

        // If there's only one admin, which is the modified account, and the new right is not admin, set at least one admin at false
        if(count == 1 && admin.getId() == account.getId()){
            at_least_one_admin = false;
        }

        return at_least_one_admin;
    }
}
