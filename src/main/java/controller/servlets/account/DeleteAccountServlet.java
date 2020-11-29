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
    private static final String PARAM_USERNAME = "username";

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
        String username = req.getParameter(PARAM_USERNAME);
        Account account = accountDAO.getAccountByName(username);

        // Delete account
        accountDAO.deleteAccount(account);

        resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
    }
}
