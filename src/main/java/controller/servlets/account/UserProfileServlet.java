package controller.servlets.account;

import model.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * A servlet which handles the user profile view
 *
 * @author Alexandre HAMON
 */
public class UserProfileServlet extends HttpServlet {
    /**
     * Session attributes
     */
    private static final String PARAM_SESSION_USER_ACCOUNT = "user";

    /**
     * Request attributes
     */
    private static final String ATT_USER = "user";

    /**
     * View file
     */
    private static final String VIEW = "/WEB-INF/readonly/userProfile.jsp";
    /**
     * View redirect
     */
    private static final String URL_REDIRECT = "/home";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.displayUserProfile(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.displayUserProfile(req, resp);
    }

    /**
     * A method to display the user profile
     *
     * @param req the request to be forwarded
     * @param resp the response to be sent
     * @throws ServletException
     * @throws IOException
     */
    private void displayUserProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get account from session
        HttpSession session = req.getSession();
        Account user = (Account) session.getAttribute(PARAM_SESSION_USER_ACCOUNT);

        // If user is connected
        if (user != null) {
            // Put it as parameter
            req.setAttribute(ATT_USER, user);

            // forward
            this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
        }
    }
}
