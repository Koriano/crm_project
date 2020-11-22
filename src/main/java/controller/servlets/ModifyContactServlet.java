package controller.servlets;

import model.Contact;
import model.Entity;
import model.forms.ContactForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public class ModifyContactServlet extends HttpServlet {
    private static final String PARAM_SESSION_ID_CONTACT = "id_contact";

    private static final String ATT_CONTACTS = "contacts";
    private static final String ATT_ROLES = "roles";
    private static final String ATT_ENTITIES = "entities";
    private static final String ATT_CONTACT = "contact";
    private static final String ATT_FORM = "form";

    private static final String VIEW = "/WEB-INF/alimentation/modifyContact.jsp";
    private static final String URL_REDIRECT = "/research/contactProfile";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get DAO instance
//        ContactDAO contactDAO = ContactDAO.getInstance();

        // Get user from session id
        HttpSession session = req.getSession();
        String id = (String) session.getAttribute(PARAM_SESSION_ID_CONTACT);

        try {
//            Contact contact = contactDAO.getContactById(Integer.parseInt(id));
        } catch (Exception e){
            resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
        }

        // SIMULATION A SUPPR *********
        Contact contact = new Contact("Hamon", "Alexandre", "Eleve", null, false, 0);
        // ****************************

        // Set request attributes for the view
        req.setAttribute(ATT_CONTACT, contact);
        this.setFormAttributes(req);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get contact DAO instance
//        ContactDAO contactDAO = ContactDAO.getInstance();

        // Create the modified contact
        ContactForm form = new ContactForm();
        Contact modified_contact = form.createContact(req);

        // Get errors map
        HashMap<String, String> errors = form.getErrors();

        // If no errors: modify and redirect to profile
        if(errors.isEmpty()){
//            contactDAO.updateContact(modified_contact);
            resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
        }else {
            this.setFormAttributes(req);
            req.setAttribute(ATT_CONTACT, modified_contact);
            req.setAttribute(ATT_FORM, form);

            this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
        }
    }

    private void setFormAttributes(HttpServletRequest req){
        // Get contact DAO instance, get all contacts and every role
//        ContactDAO contactDAO = ContactDAO.getInstance();
//        Contact[] contacts = contactDAO.getAllContacts();
//        String[] roles = contactDAO.getAllRoles();

        // Get entity DAO instance and get all entities
//        EntityDAO entityDAO = EntityDAO.getInstance();
//        Entity[] entities = entityDAO.getAllEntities();

        // SIMULATION A SUPPR ***************
        Contact[] contacts = {new Contact("Hamon", "Alexandre", "Eleve", null, false, 0)};
        String[] roles = {"Prof", "Eleve", "Responsable de formation", "Chargé de com", "Chargé d'alternance"};
        Entity[] entities = {new Entity("Thalès", "11111111111111", "Entreprise"), new Entity("ENSIBS", null, "Etablissement d'enseignement")};
        // **********************************

        req.setAttribute(ATT_CONTACTS, contacts);
        req.setAttribute(ATT_ROLES, roles);
        req.setAttribute(ATT_ENTITIES, entities);
    }
}