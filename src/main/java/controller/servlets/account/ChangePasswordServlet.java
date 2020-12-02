package controller.servlets.account;

import controller.DAO.AccountDAO;
import model.Account;
import model.forms.ChangePasswordForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * A servlet which handles the password change from user profile
 *
 * @author Alexandre HAMON
 */
public class ChangePasswordServlet extends HttpServlet {
    /**
     * Request attribute
     */
    private static final String ATT_FORM = "form";

    /**
     *  View file
     */
    private static final String VIEW = "/WEB-INF/readonly/changePassword.jsp";

    /**
     * View redirect
     */
    private static final String URL_REDIRECT = "/home";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Forward form page
        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get account DAO instance
        AccountDAO accountDAO = AccountDAO.getInstance();

        // Create form and get password
        ChangePasswordForm form = new ChangePasswordForm();
        Account modified_account = form.changePassword(req);
        HashMap<String, String> errors = form.getErrors();

        // If no errors, update account and redirect
        if(errors.isEmpty()){
            accountDAO.updateAccount(modified_account);

            resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
        }
        // If errors, forward with errors
        else {
            req.setAttribute(ATT_FORM, form);

            this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
        }
    }
}
