package controller.servlets;

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
    private static final String PARAM_ID = "id";

    private static final String SESSION_PARAM_ACCOUNT = "account";

    private static final String ATT_CONTACT = "contact";
    private static final String ATT_COMMENT = "comment";

    private static final String VIEW = "/WEB-INF/readonly/contactProfile.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get request parameters
        String id = req.getParameter(PARAM_ID);

        // Initialize contact DAO and comment DAO
//        ContactDAO contactDAO = ContactDAO.getInstance();
//        CommentDAO commentDAO = CommentDAO.getInstance();

        // Get session and user id
//        HttpSession session = req.getSession();
//        int user_id = ((Account)session.getAttribute(SESSION_PARAM_ACCOUNT)).getContact().getId();

        Contact contact = null;
        Comment comment = null;
        if(this.checkId(id)) {
//            contact = contactDAO.getContactById(id);
//            comment = CommentDAO.getCommentByAuthorAndContact(user_id, id);
        }

        contact = new Contact("Hamon", "Alexandre", "Ingénieur", null, false, 0);
        comment = new Comment(contact, contact, "oui 1234");

        // Set contact as request attribute
        req.setAttribute(ATT_CONTACT, contact);
        req.setAttribute(ATT_COMMENT, comment);

        req.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public boolean checkId(String id){
        return id != null && !id.isEmpty() && id.matches("^[0-9]+$");
    }
}
