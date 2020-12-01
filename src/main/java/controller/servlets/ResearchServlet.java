package controller.servlets;

import controller.DAO.ContactDAO;
import controller.DAO.EntityDAO;
import model.Account;
import model.Contact;
import model.Entity;
import model.Sector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class ResearchServlet extends HttpServlet {
    private static final String PARAM_USER_ACCOUNT = "user";

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

        // Get user account
        HttpSession session = req.getSession();
        Account user = (Account) session.getAttribute(PARAM_USER_ACCOUNT);

        // Create contact and entities DAO to get them all
        ContactDAO contactDAO = ContactDAO.getInstance();
        EntityDAO entityDAO = EntityDAO.getInstance();

        ArrayList<Contact> contacts = contactDAO.getAllContacts();
        ArrayList<Entity> entities = entityDAO.getAllEntities();

        contacts = this.filterContactsBySector(contacts, user);

        if (research != null){

            if (contacts.size() > 0){
                this.filterContactsByResearch(contacts, research);
            }

            if (entities.size() > 0){
                this.filterEntitiesByResearch(entities, research);
            }
        }

        // Set contacts and entities as request attribute
        req.setAttribute(ATT_CONTACTS, contacts);
        req.setAttribute(ATT_ENTITIES, entities);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
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

            // if contained in at least one sector, then add to return
            if (is_visible){
                returned_contacts.add(contact);
            }
        }

        return returned_contacts;
    }

    private void filterContactsByResearch(ArrayList<Contact> contact_list, String filter){
        // No case sensitivity
        filter = filter.toLowerCase();

        int i = 0;
        Contact contact;

        do {
            contact = contact_list.get(i);

            if (!contact.getName().toLowerCase().contains(filter) &&
                    !contact.getSurname().toLowerCase().contains(filter) &&
                    !contact.getRole().toLowerCase().contains(filter) &&
                    (contact.getEntity() == null || !contact.getEntity().getName().toLowerCase().contains(filter))){

                contact_list.remove(i);

            } else {
                i++;
            }

        }while (i<contact_list.size());
    }

    private void filterEntitiesByResearch(ArrayList<Entity> entities_list, String filter){
        // No case sensitivity
        filter = filter.toLowerCase();

        int i = 0;
        Entity entity;

        do {
            entity = entities_list.get(i);

            if (!entity.getName().toLowerCase().contains(filter) &&
                    !entity.getType().toLowerCase().contains(filter)){
                entities_list.remove(i);
            } else {
                i++;
            }

        }while (i<entities_list.size());
    }
}
