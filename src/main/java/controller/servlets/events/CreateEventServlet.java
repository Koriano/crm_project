package controller.servlets.events;

import controller.DAO.ContactDAO;
import controller.DAO.EventDAO;
import controller.DAO.EventTypeDAO;
import controller.DAO.SectorDAO;
import model.Account;
import model.Contact;
import model.Event;
import model.Sector;
import model.forms.EventForm;

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
import java.util.Locale;

public class CreateEventServlet extends HttpServlet {
    private static final String PARAM_SESSION_USER_ACCOUNT = "user";

    private static final String ATT_TYPES = "types";
    private static final String ATT_CONTACTS = "contacts";
    private static final String ATT_EVENT = "event";
    private static final String ATT_FORM = "form";
    private static final String ATT_DATE = "date";
    private static final String ATT_TIME = "time";

    private static final String VIEW = "/WEB-INF/readonly/createEvent.jsp";
    private static final String URL_REDIRECT = "/myEvents";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set attributes and forward
        this.setRequestAttributes(req);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get EventDAO instance
        EventDAO eventDAO = EventDAO.getInstance();

        // Create form and new event
        EventForm form = new EventForm();
        Event event = form.createEvent(req);

        HashMap<String, String> errors = form.getErrors();

        // If no errors, save and redirect
        if (errors.isEmpty()){
            eventDAO.saveEvent(event);

            resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
        }
        // If errors, set request attributes and forward
        else {
            // Set format
            SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat time_format = new SimpleDateFormat("HH:mm");

            // Get date and time
            Date date = event.getDate();

            this.setRequestAttributes(req);
            req.setAttribute(ATT_EVENT, event);
            req.setAttribute(ATT_FORM, form);

            if (date != null) {
                req.setAttribute(ATT_DATE, date_format.format(date));
                req.setAttribute(ATT_TIME, time_format.format(date));
            }

            this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
        }
    }

    private void setRequestAttributes(HttpServletRequest req){
        // Get user from session
        HttpSession session = req.getSession();
        Account user = (Account) session.getAttribute(PARAM_SESSION_USER_ACCOUNT);

        // Get event types and contact DAO
        EventTypeDAO eventTypeDAO = EventTypeDAO.getInstance();
        ContactDAO contactDAO = ContactDAO.getInstance();

        // Get every type and every contact
        ArrayList<String> types = eventTypeDAO.getAllTypes();
        ArrayList<Contact> contacts = contactDAO.getAllContacts();

        // Filter contacts by sectors
        contacts = this.filterContactsBySector(contacts, user);

        // Set them as attributes
        req.setAttribute(ATT_TYPES, types);
        req.setAttribute(ATT_CONTACTS, contacts);
    }

    private ArrayList<Contact> filterContactsBySector(ArrayList<Contact> contact_list, Account user){
        ArrayList<Contact> returned_contacts = new ArrayList<>();

        // For each contact
        for (Contact contact:contact_list){
            boolean is_visible = false;

            // For each sector check if contact is in
            for (Sector sector:user.getSectors()){
                for (Contact sector_contact:sector.getContactList()){

                    if (contact.getId() == sector_contact.getId()){
                        is_visible = true;
                        break;
                    }

                }

                if (is_visible){
                    break;
                }
            }

            if (!is_visible){
                is_visible = !this.isAssociatedToSector(contact);
            }

            // if contained in at least one sector, then add to return
            if (is_visible){
                returned_contacts.add(contact);
            }
        }

        return returned_contacts;
    }

    private boolean isAssociatedToSector(Contact contact){
        SectorDAO sectorDAO = SectorDAO.getInstance();
        ArrayList<Sector> sectors = sectorDAO.getAllSectors();

        boolean is_associated = false;

        for (Sector sector:sectors){
            for (Contact sector_contact:sector.getContactList()){
                if (sector_contact.getId() == contact.getId()){
                    is_associated = true;
                    break;
                }
            }

            if (is_associated){
                break;
            }
        }

        return is_associated;
    }
}
