package controller.servlets.account;

import controller.DAO.AccountDAO;
import model.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountProfileServlet extends HttpServlet {
    private static final String PARAM_USERNAME = "username";

    private static final String ATT_ACCOUNT = "account";

    private static final String VIEW = "/WEB-INF/admin/accountProfile.jsp";
    private static final String URL_REDIRECT = "/rights";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.displayProfile(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.displayProfile(req, resp);
    }

    private void displayProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get account DAO
        AccountDAO accountDAO = AccountDAO.getInstance();

        // Get parameter and corresponding account
        String username = req.getParameter(PARAM_USERNAME);
        Account account = accountDAO.getAccountByName(username);

        // If account doesn't exist, redirect to account home page
        if(account == null){
            resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
        }
        // If account exists, set it as attribute and forward the request
        else {
            req.setAttribute(ATT_ACCOUNT, account);

            this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
        }
    }
}
