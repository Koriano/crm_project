package controller.servlets.account;

import controller.DAO.AccountDAO;
import model.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;

public class DeleteAccountServlet extends HttpServlet {
    private static final String PARAM_ACCOUNT_ID = "id";

    private static final String URL_REDIRECT = "/rights";

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

        try{
            Account account = accountDAO.getAccountById(Integer.parseInt(id));

            // Delete account
            accountDAO.deleteAccount(account);
        } catch (Exception ignored){

        }

        resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
    }
}
