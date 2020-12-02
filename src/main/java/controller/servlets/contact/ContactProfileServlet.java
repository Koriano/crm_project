package controller.servlets.contact;

import controller.DAO.CommentDAO;
import controller.DAO.ContactDAO;
import controller.DAO.EventDAO;
import controller.DAO.SectorDAO;
import model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * A servlet which handles the contact profile view
 *
 * @author Alexandre HAMON
 */
public class ContactProfileServlet extends HttpServlet {
    /**
     * Request parameters
     */
    private static final String PARAM_CONTACT_ID = "id";

    /**
     * Session attributes
     */
    private static final String PARAM_SESSION_USER_ACCOUNT = "user";

    /**
     * Request attributes
     */
    private static final String ATT_CONTACT = "contact";
    private static final String ATT_COMMENT = "comment";
    private static final String ATT_EVENTS = "events";
    private static final String ATT_VISIBLE = "is_visible";
    private static final String ATT_DATES = "dates";

    /**
     * View page
     */
    private static final String VIEW = "/WEB-INF/readonly/contactProfile.jsp";

    /**
     * View redirect
     */
    private static final String URL_REDIRECT = "/research";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.displayContactProfile(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.displayContactProfile(req, resp);
    }

    /**
     * A method to display the contact profile view
     *
     * @param req the request to be forwarded
     * @param resp the response to be sent
     * @throws ServletException
     * @throws IOException
     */
    private void displayContactProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Initialize contact DAO and comment DAO
        ContactDAO contactDAO = ContactDAO.getInstance();
        CommentDAO commentDAO = CommentDAO.getInstance();
        EventDAO eventDAO = EventDAO.getInstance();

        // Get id of contact to be displayed from session
        HttpSession session = req.getSession();
        Account user = (Account) session.getAttribute(PARAM_SESSION_USER_ACCOUNT);

        // Get contact id
        String id = req.getParameter(PARAM_CONTACT_ID);

        try {
            // Get contact from base
            Contact contact = contactDAO.getContactById(Integer.parseInt(id));

            // If contact exists
            if (contact != null){
                // get associated comment and events
                Comment comment = commentDAO.getCommentByAuthorAndContact(user.getContact(), contact);
                ArrayList<Event> events = eventDAO.getEventsByContact(contact);
                events = this.filterEventByUser(events, user.getContact());

                // save event dates
                HashMap<Integer, String> dates = new HashMap<>();
                this.addDates(dates, events);

                // Set contact as request attribute
                req.setAttribute(ATT_CONTACT, contact);
                req.setAttribute(ATT_COMMENT, comment);
                req.setAttribute(ATT_EVENTS, events);
                req.setAttribute(ATT_VISIBLE, this.isVisible(contact, user));
                req.setAttribute(ATT_DATES, dates);

                this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
            }
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
        }
    }

    /**
     * A method to filter contacts with user contact
     *
     * @param events the event list to be filtered
     * @param user_contact the contact associated to the user profile
     * @return the filtered event list
     */
    private ArrayList<Event> filterEventByUser(ArrayList<Event> events, Contact user_contact){
        ArrayList<Event> returned_list = new ArrayList<>();

        // For each event, check id event needs to be displayed
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

    /**
     * A method to check if a contact is visible accoridng to user sectors
     *
     * @param contact the contact of which visibility
     * @param user the user account containing the sectors
     * @return true if the contact is visible, else false
     */
    private boolean isVisible(Contact contact, Account user){
        boolean is_visible = false;

        // For each sector check if contact is in
        for (Sector sector:user.getSectors()){
            for (Contact sector_contact:sector.getContactList()){

                if (contact.getId() == sector_contact.getId()){
                    is_visible = true;
                    break;
                }

            }

            // If contact already visible, don't check other sectors
            if (is_visible){
                break;
            }
        }

        // If contact not visible in any user sector, check if visible in any global sector (if no make it visible for user)
        if (!is_visible){
            is_visible = !this.isAssociatedToSector(contact);
        }

        return is_visible;
    }

    /**
     * A method to check whether a contact is associated to any sectors or not
     *
     * @param contact the contact to be checked
     *
     * @return true if given contact is associated at least to one sector, else false
     */
    private boolean isAssociatedToSector(Contact contact){
        // Get every sector
        SectorDAO sectorDAO = SectorDAO.getInstance();
        ArrayList<Sector> sectors = sectorDAO.getAllSectors();

        // For each contact of every sector, check if present in list
        boolean is_associated = false;

        for (Sector sector:sectors){
            for (Contact sector_contact:sector.getContactList()){
                if (sector_contact.getId() == contact.getId()){
                    is_associated = true;
                    break;
                }
            }

            // if already associated, do not check the others (at least one sector)
            if (is_associated){
                break;
            }
        }

        return is_associated;
    }

    private void addDates(HashMap<Integer, String> dateTime, ArrayList<Event> events) {
        for (Event evt: events){

            if (!dateTime.containsKey(evt.getId())){
                Date date = evt.getDate();

                // Set formatter
                SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");

                dateTime.put(evt.getId(), date_format.format(date));
            }
        }
    }
}
