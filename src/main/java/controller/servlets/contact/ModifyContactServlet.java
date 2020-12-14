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
 * A servlet which handles the modify contact view
 *
 * @author Alexandre HAMON
 */
public class ModifyContactServlet extends HttpServlet {
    /**
     * Action to be forwarded to the form object
     */
    private static final String ACTION = "modify";

    /**
     * Request parameter
     */
    private static final String PARAM_ID_CONTACT = "id";

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
    private static final String VIEW = "/WEB-INF/alimentation/modifyContact.jsp";

    /**
     * View redirect
     */
    private static final String URL_REDIRECT = "/research/contact";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get DAO instance
        ContactDAO contactDAO = ContactDAO.getInstance();

        // Get contact id
        String id = req.getParameter(PARAM_ID_CONTACT);

        // Build redirect url
        String redirect_url = req.getContextPath() + URL_REDIRECT + "?id=" + id;

        try {
            Contact contact = contactDAO.getContactById(Integer.parseInt(id));

            if (contact != null){
                // Set request attributes for the view
                req.setAttribute(ATT_CONTACT, contact);
                this.setFormAttributes(req);

                this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
            } else {
                resp.sendRedirect(redirect_url);
            }
        } catch (Exception e){
            resp.sendRedirect(redirect_url);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get contact DAO instance
        ContactDAO contactDAO = ContactDAO.getInstance();

        // Get id from request
        String id_contact = req.getParameter(PARAM_ID_CONTACT);

        try {
            int id = Integer.parseInt(id_contact);

            // Create the modified contact
            ContactForm form = new ContactForm();
            Contact modified_contact = form.createContact(req, ACTION);
            modified_contact.setId(id);

            // Get errors map
            HashMap<String, String> errors = form.getErrors();

            // If no errors: modify and redirect to profile
            if(errors.isEmpty()){
                contactDAO.updateContact(modified_contact);
                resp.sendRedirect(req.getContextPath() + URL_REDIRECT + "?id=" + id_contact);
            }else {
                this.setFormAttributes(req);
                req.setAttribute(ATT_CONTACT, modified_contact);
                req.setAttribute(ATT_FORM, form);

                this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
            }
        } catch (Exception e){
            resp.sendRedirect(req.getContextPath() + URL_REDIRECT + "?id=" + id_contact);
        }
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

        req.setAttribute(ATT_CONTACTS, contacts);
        req.setAttribute(ATT_ROLES, roles);
        req.setAttribute(ATT_ENTITIES, entities);
    }
}
