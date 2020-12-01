package controller.servlets.contact;

import controller.DAO.CommentDAO;
import controller.DAO.ContactDAO;
import controller.DAO.EventDAO;
import model.Account;
import model.Comment;
import model.Contact;
import model.Event;

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
    private static final String ATT_EVENTS = "events";

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
        EventDAO eventDAO = EventDAO.getInstance();

        // Get id of contact to be displayed from session
        HttpSession session = req.getSession();
        Account user = (Account) session.getAttribute(PARAM_SESSION_USER_ACCOUNT);

        String id = req.getParameter(PARAM_CONTACT_ID);

        try {
            Contact contact = contactDAO.getContactById(Integer.parseInt(id));

            if (contact != null){
                Comment comment = commentDAO.getCommentByAuthorAndContact(user.getContact(), contact);
                ArrayList<Event> events = eventDAO.getEventsByContact(contact);
                events = this.filterEventByUser(events, user.getContact());

                // Set contact as request attribute
                req.setAttribute(ATT_CONTACT, contact);
                req.setAttribute(ATT_COMMENT, comment);
                req.setAttribute(ATT_EVENTS, events);

                this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
            }
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
        }
    }

    private ArrayList<Event> filterEventByUser(ArrayList<Event> events, Contact user_contact){
        ArrayList<Event> returned_list = new ArrayList<>();

        for (Event evt:events){
            boolean user_is_participant = false;

            for (Contact contact_evt: evt.getContactsList()){
                if (contact_evt.getId() == user_contact.getId()){
                    user_is_participant = true;
                    break;
                }
            }

            if (user_is_participant){
                returned_list.add(evt);
            }
        }

        return returned_list;
    }
}
