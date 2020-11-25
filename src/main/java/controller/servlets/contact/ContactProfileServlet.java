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
    private static final String PARAM_SESSION_CONTACT_ID = "contact_id";
    private static final String PARAM_SESSION_USER_ACCOUNT = "user";

    private static final String ATT_CONTACT = "contact";
    private static final String ATT_COMMENT = "comment";

    private static final String VIEW = "/WEB-INF/readonly/contactProfile.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Initialize contact DAO and comment DAO
        ContactDAO contactDAO = ContactDAO.getInstance();
        CommentDAO commentDAO = CommentDAO.getInstance();

        // Get id of contact to be displayed from session
        HttpSession session = req.getSession();
        String id = (String) session.getAttribute(PARAM_SESSION_CONTACT_ID);
        Contact contact = contactDAO.getContactById(Integer.parseInt(id));

        Account user = (Account) session.getAttribute(PARAM_SESSION_USER_ACCOUNT);

        ArrayList<Comment> comment = commentDAO.getCommentByAuthorAndContact(user.getContact(), contact);

        // Set contact as request attribute
        req.setAttribute(ATT_CONTACT, contact);
        if(comment.size() > 0) {
            req.setAttribute(ATT_COMMENT, comment.get(0));
        } else {
            req.setAttribute(ATT_COMMENT, null);
        }

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public boolean checkId(String id){
        return id != null && !id.isEmpty() && id.matches("^[0-9]+$");
    }
}
