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

public class ResearchServlet extends HttpServlet {
    private static final String ATT_CONTACTS = "contacts";
    private static final String ATT_ENTITIES = "entities";

    private static final String VIEW = "/WEB-INF/readonly/research.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.displayContactsAndEntities(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.displayContactsAndEntities(req, resp);
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
}