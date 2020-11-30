package controller.servlets.account;

import model.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserProfileServlet extends HttpServlet {
    private static final String PARAM_SESSION_USER_ACCOUNT = "user";

    private static final String ATT_USER = "user";

    private static final String VIEW = "/WEB-INF/readonly/userProfile.jsp";
    private static final String URL_REDIRECT = "/home";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.displayUserProfile(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.displayUserProfile(req, resp);
    }

    private void displayUserProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get account from session
        HttpSession session = req.getSession();
        Account user = (Account) session.getAttribute(PARAM_SESSION_USER_ACCOUNT);

        if (user != null) {
            System.out.println(ATT_USER);
            System.out.println(user.getName());
            // Put it as parameter
            req.setAttribute(ATT_USER, user);

            // forward
            this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
        }
    }
}
