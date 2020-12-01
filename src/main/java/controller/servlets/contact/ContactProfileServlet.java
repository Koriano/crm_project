package controller.servlets.contact;

import controller.DAO.CommentDAO;
import controller.DAO.ContactDAO;
import model.Account;
import model.Comment;
import model.Contact;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class ContactProfileServlet extends HttpServlet {
    private static final String PARAM_SESSION_USER_ACCOUNT = "user";
    private static final String PARAM_CONTACT_ID = "id";

    private static final String ATT_CONTACT = "contact";
    private static final String ATT_COMMENT = "comment";

    private static final String VIEW = "/WEB-INF/readonly/contactProfile.jsp";
    private static final String URL_REDIRECT = "/research";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.displayContactProfile(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.displayContactProfile(req, resp);
    }

    private void displayContactProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Initialize contact DAO and comment DAO
        ContactDAO contactDAO = ContactDAO.getInstance();
        CommentDAO commentDAO = CommentDAO.getInstance();

        // Get id of contact to be displayed from session
        HttpSession session = req.getSession();
        Account user = (Account) session.getAttribute(PARAM_SESSION_USER_ACCOUNT);

        String id = req.getParameter(PARAM_CONTACT_ID);

        try {
            Contact contact = contactDAO.getContactById(Integer.parseInt(id));

            if (contact != null){
                Comment comment = commentDAO.getCommentByAuthorAndContact(user.getContact(), contact);

                // Set contact as request attribute
                req.setAttribute(ATT_CONTACT, contact);
                req.setAttribute(ATT_COMMENT, comment);

                this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
            }
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
        }
    }
}
