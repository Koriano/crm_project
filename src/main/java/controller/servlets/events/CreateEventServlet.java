package controller.servlets.events;

import controller.DAO.ContactDAO;
import controller.DAO.EventDAO;
import model.Contact;
import model.Event;
import model.forms.EventForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CreateEventServlet extends HttpServlet {
    private static final String ATT_TYPES = "types";
    private static final String ATT_CONTACTS = "contacts";
    private static final String ATT_EVENT = "event";
    private static final String ATT_FORM = "form";
    private static final String ATT_DATE = "date";

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
            SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = event.getDate();

            this.setRequestAttributes(req);
            req.setAttribute(ATT_EVENT, event);
            req.setAttribute(ATT_FORM, form);
            if (date != null) {
                req.setAttribute(ATT_DATE, date_format.format(date));
            }

            this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
        }
    }

    private void setRequestAttributes(HttpServletRequest req){
        // Get event and contact DAO
        EventDAO eventDAO = EventDAO.getInstance();
        ContactDAO contactDAO = ContactDAO.getInstance();

        // Get every type and every contact
//        ArrayList<String> types = eventDAO.getAllTypes();
        ArrayList<Contact> contacts = contactDAO.getAllContacts();

        // SIMULATION A SUPPR **********
        ArrayList<String> types = new ArrayList<>();
        types.add("RDV");
        types.add("REUNION");
        types.add("DEPOT D'OFFRES");
        // *****************************

        // Set them as attributes
        req.setAttribute(ATT_TYPES, types);
        req.setAttribute(ATT_CONTACTS, contacts);
    }
}
