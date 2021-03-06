package controller.servlets.connection;

import model.Account;
import model.forms.ConnectForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ConnectionServlet extends HttpServlet {
    private static final String ATT_SESSION_USER_ACCOUNT = "user";
    private static final String ATT_ACCOUNT = "account";
    private static final String ATT_FORM = "form";

    private static final String VIEW = "/WEB-INF/public/connection.jsp";
    private static final String URL_REDIRECT = "/home";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account user = (Account) session.getAttribute(ATT_SESSION_USER_ACCOUNT);

        if(user == null){
            this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
        }
        else {
            resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Create form and connect user
        ConnectForm connectForm = new ConnectForm();
        Account user = connectForm.connectUser(req);

        if(connectForm.hasError()){
            req.setAttribute(ATT_ACCOUNT, user);
            req.setAttribute(ATT_FORM, connectForm);
            this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
        }
        else {
            HttpSession session = req.getSession();
            session.setAttribute(ATT_SESSION_USER_ACCOUNT, user);

            resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
        }
    }
}
