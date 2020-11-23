package controller.servlets.contact;

import model.Account;
import model.Comment;
import model.Contact;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ContactProfileServlet extends HttpServlet {
    private static final String PARAM_SESSION_CONTACT_ID = "contact_id";
    private static final String PARAM_SESSION_USER_ACCOUNT = "user";

    private static final String ATT_CONTACT = "contact";
    private static final String ATT_COMMENT = "comment";

    private static final String VIEW = "/WEB-INF/readonly/contactProfile.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Initialize contact DAO and comment DAO
//        ContactDAO contactDAO = ContactDAO.getInstance();
//        CommentDAO commentDAO = CommentDAO.getInstance();

        // Get id of contact to be displayed from session
        HttpSession session = req.getSession();
        String id = (String) session.getAttribute(PARAM_SESSION_CONTACT_ID);

//        int user_id = ((Account) session.getAttribute(PARAM_SESSION_USER_ACCOUNT)).getContact().getId();

        // Contact contact = contactDAO.getContactById(id);
        // Comment comment = CommentDAO.getCommentByAuthorAndContact(user_id, id);

        // SIMULATION A SUPPR *******************
        Contact referent = new Contact("Gros-taxi", "Oui-oui", "Professeur", null, false, 3);
        Contact contact = new Contact("Hamon", "Alexandre", "Eleve", referent, true, 0);
        Comment comment = new Comment(contact, contact, "oui 1234");
        // **************************************

        // Set contact as request attribute
        req.setAttribute(ATT_CONTACT, contact);
        req.setAttribute(ATT_COMMENT, comment);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public boolean checkId(String id){
        return id != null && !id.isEmpty() && id.matches("^[0-9]+$");
    }
}
