package controller.servlets.contact;

import controller.DAO.ContactDAO;
import controller.DAO.EntityDAO;
import model.Contact;
import model.Entity;
import model.forms.ContactForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AddContactServlet extends HttpServlet {

    private static final String ATT_CONTACTS = "contacts";
    private static final String ATT_ROLES = "roles";
    private static final String ATT_ENTITIES = "entities";
    private static final String ATT_CONTACT = "contact";
    private static final String ATT_FORM = "form";

    private static final String VIEW = "/WEB-INF/alimentation/addContact.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set contact list, entity list and roles list for view
        this.setFormAttributes(req);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get contactDAO instance
        ContactDAO contactDAO = ContactDAO.getInstance();

        // Create form object and create Contact
        ContactForm form = new ContactForm();
        Contact new_contact = form.createContact(req);

        // Get errors map
        HashMap<String, String> errors = form.getErrors();

        if(errors.isEmpty()){
            contactDAO.saveContact(new_contact);
            new_contact = null;
        }

        // Set attributes to request
        this.setFormAttributes(req);
        req.setAttribute(ATT_CONTACT, new_contact);
        req.setAttribute(ATT_FORM, form);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    private void setFormAttributes(HttpServletRequest req){
        // Get contact DAO instance, get all contacts and every role
        ContactDAO contactDAO = ContactDAO.getInstance();
        ArrayList<Contact> contacts = contactDAO.getAllContacts();
        ArrayList<String> roles = contactDAO.getAllRoles();

        // Get entity DAO instance and get all entities
        EntityDAO entityDAO = EntityDAO.getInstance();
        ArrayList<Entity> entities = entityDAO.getAllEntities();

        req.setAttribute(ATT_CONTACTS, contacts);
        req.setAttribute(ATT_ROLES, roles);
        req.setAttribute(ATT_ENTITIES, entities);
    }
}
