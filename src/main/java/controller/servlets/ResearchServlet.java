package controller.servlets;

import controller.DAO.ContactDAO;
import controller.DAO.EntityDAO;
import model.Contact;
import model.Entity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResearchServlet extends HttpServlet {
    private static final String PARAM_RESEARCH = "research";

    private static final String ATT_CONTACTS = "contacts";
    private static final String ATT_ENTITIES = "entities";

    private static final String VIEW = "/WEB-INF/readonly/research.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.search(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.search(req, resp);
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get parameter
        String research = req.getParameter(PARAM_RESEARCH);

        if (research == null){
            this.displayContactsAndEntities(req, resp);
        } else {
            this.displayWithFilter(req, resp, research);
        }
    }

    /**
     * Method used to get every contacts and entities and forward the request and response to the associated view
     *
     * @param req the request to set attribute and to be forwarded
     * @param resp the response to forward
     */
    private void displayContactsAndEntities(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Create contact and entities DAO to get them all
        ContactDAO contactDAO = ContactDAO.getInstance();
        EntityDAO entityDAO = EntityDAO.getInstance();

        ArrayList<Contact> contacts = contactDAO.getAllContacts();
        ArrayList<Entity> entities = entityDAO.getAllEntities();

        // Set contacts and entities as request attribute
        req.setAttribute(ATT_CONTACTS, contacts);
        req.setAttribute(ATT_ENTITIES, entities);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    private void displayWithFilter(HttpServletRequest req, HttpServletResponse resp, String filter) throws ServletException, IOException {
        // Create contact and entities DAO to get them all
        ContactDAO contactDAO = ContactDAO.getInstance();
        EntityDAO entityDAO = EntityDAO.getInstance();

        // No case sensitivity
        filter = filter.toLowerCase();

        // Filter contacts
        ArrayList<Contact> contacts = contactDAO.getAllContacts();

        int i = 0;
        Contact contact;

        do {
            contact = contacts.get(i);

            if (!contact.getName().toLowerCase().contains(filter) && !contact.getSurname().toLowerCase().contains(filter)){
                contacts.remove(i);
            } else {
                i++;
            }

        }while (i<contacts.size());

        // Filter entities
        ArrayList<Entity> entities = entityDAO.getAllEntities();

        i = 0;
        Entity entity;

        do {
            entity = entities.get(i);

            if (!entity.getName().toLowerCase().contains(filter)){
                entities.remove(i);
            } else {
                i++;
            }

        }while (i<entities.size());


        // Set contacts and entities as request attribute
        req.setAttribute(ATT_CONTACTS, contacts);
        req.setAttribute(ATT_ENTITIES, entities);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }
}
