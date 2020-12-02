package controller.servlets.account;

import controller.DAO.AccountDAO;
import model.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class RightsServlet extends HttpServlet {
    private static final String ATT_ACCOUNTS = "accounts";

    private static final String VIEW = "/WEB-INF/admin/rights.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.displayAccounts(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.displayAccounts(req, resp);
    }

    private void displayAccounts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get every account and set it as request attribute
        AccountDAO accountDAO = AccountDAO.getInstance();
        ArrayList<Account> account_list = accountDAO.getAllAccounts();

        req.setAttribute(ATT_ACCOUNTS, account_list);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }
}
