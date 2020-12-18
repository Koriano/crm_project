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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A servlet which handles the contact creation form
 *
 * @author Alexandre HAMON
 */
public class AddContactServlet extends HttpServlet {
    /**
     * Action to be forwarded to the form object
     */
    private static final String ACTION = "add";

    /**
     * Session attributes
     */
    private static final String PARAM_SESSION_CONTACT_ID = "contact_id";
    private static final String PARAM_SESSION_DOUBLE = "double";
    private static final String PARAM_SESSION_NAME = "name";
    private static final String PARAM_SESSION_SURNAME = "surname";

    /**
     * Request attributes
     */
    private static final String ATT_CONTACTS = "contacts";
    private static final String ATT_ROLES = "roles";
    private static final String ATT_ENTITIES = "entities";
    private static final String ATT_CONTACT = "contact";
    private static final String ATT_FORM = "form";

    /**
     * View page
     */
    private static final String VIEW = "/WEB-INF/alimentation/addContact.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Remove attributes from session
        HttpSession session = req.getSession();
        session.removeAttribute(PARAM_SESSION_CONTACT_ID);
        session.removeAttribute(PARAM_SESSION_DOUBLE);
        session.removeAttribute(PARAM_SESSION_NAME);
        session.removeAttribute(PARAM_SESSION_SURNAME);

        // Set contact list, entity list and roles list for view
        this.setFormAttributes(req);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get contactDAO instance
        ContactDAO contactDAO = ContactDAO.getInstance();

        // Get session
        HttpSession session = req.getSession();
        String is_double_form = (String) session.getAttribute(PARAM_SESSION_DOUBLE);
        String old_name = (String) session.getAttribute(PARAM_SESSION_NAME);
        String old_surname = (String) session.getAttribute(PARAM_SESSION_SURNAME);

        // Create form object and create Contact
        ContactForm form = new ContactForm();
        Contact new_contact = form.createContact(req, ACTION);

        // Get errors map
        HashMap<String, String> errors = form.getErrors();

        // if no form errors, save contact
        if(errors.isEmpty()){
            if("true".equals(is_double_form)) {
                if (new_contact.getName().toLowerCase().equals(old_name.toLowerCase()) && new_contact.getSurname().toLowerCase().equals(old_surname.toLowerCase())){
                    this.success(contactDAO, new_contact);
                    new_contact = null;
                } else if (!form.isDouble()){
                    this.success(contactDAO, new_contact);
                    new_contact = null;
                } else {
                    this.failure(session, new_contact);
                }
            } else if (!form.isDouble()){
                this.success(contactDAO, new_contact);
                new_contact = null;
            } else {
                this.failure(session, new_contact);
            }
        }

        // Set attributes to request
        this.setFormAttributes(req);
        req.setAttribute(ATT_CONTACT, new_contact);
        req.setAttribute(ATT_FORM, form);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    private void success(ContactDAO contactDAO, Contact new_contact){
        contactDAO.saveContact(new_contact);
    }

    private void failure(HttpSession session, Contact new_contact) {
        session.setAttribute(PARAM_SESSION_NAME, new_contact.getName());
        session.setAttribute(PARAM_SESSION_SURNAME, new_contact.getSurname());
    }

    /**
     * A method to set generic form attributes used to build the form
     *
     * @param req the request in which to set attributes
     */
    private void setFormAttributes(HttpServletRequest req){
        // Get contact DAO instance, get all contacts and every role
        ContactDAO contactDAO = ContactDAO.getInstance();
        ArrayList<Contact> contacts = contactDAO.getAllContacts();
        ArrayList<String> roles = contactDAO.getAllRoles();

        // Get entity DAO instance and get all entities
        EntityDAO entityDAO = EntityDAO.getInstance();
        ArrayList<Entity> entities = entityDAO.getAllEntities();

        // Set attributes to request
        req.setAttribute(ATT_CONTACTS, contacts);
        req.setAttribute(ATT_ROLES, roles);
        req.setAttribute(ATT_ENTITIES, entities);
    }
}
