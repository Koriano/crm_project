package controller.servlets;

import controller.DAO.ContactDAO;
import controller.DAO.EntityDAO;
import controller.DAO.SectorDAO;
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
import java.util.Arrays;

/**
 * A servlet handling the research page view, which displays contacts and entities user can see. The user can also
 * filter the results with a research field.
 *
 * @author Alexandre HAMON
 */
public class ResearchServlet extends HttpServlet {
    /**
     * Request parameters
     */
    private static final String PARAM_RESEARCH = "research";

    /**
     * Session attributes
     */
    private static final String PARAM_USER_ACCOUNT = "user";

    /**
     * Request attributes
     */
    private static final String ATT_CONTACTS = "contacts";
    private static final String ATT_ENTITIES = "entities";

    /**
     * View file
     */
    private static final String VIEW = "/WEB-INF/readonly/research.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.search(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.search(req, resp);
    }

    /**
     * A method to display contacts and entities depending on :
     *  - Sectors associated to user account
     *  - Research filter written
     *
     * @param req the request to be forwarded
     * @param resp the response to be sent
     * @throws ServletException
     * @throws IOException
     */
    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get filter parameter
        String research = req.getParameter(PARAM_RESEARCH);

        // Get user account
        HttpSession session = req.getSession();
        Account user = (Account) session.getAttribute(PARAM_USER_ACCOUNT);

        // Create contacts and entities DAO to get all instances
        ContactDAO contactDAO = ContactDAO.getInstance();
        EntityDAO entityDAO = EntityDAO.getInstance();

        ArrayList<Contact> contacts = contactDAO.getAllContacts();
        ArrayList<Entity> entities = entityDAO.getAllEntities();

        // Filter contacts with user sectors
        contacts = this.filterContactsBySector(contacts, user);

        // if research has been made, filter with research words
        if (research != null){
            String[] filters = research.split(" ");

            if (contacts.size() > 0){
                this.filterContactsByResearch(contacts, filters);
            }

            if (entities.size() > 0){
                this.filterEntitiesByResearch(entities, filters);
            }
        }

        // Set contacts and entities as request attribute
        req.setAttribute(ATT_CONTACTS, contacts);
        req.setAttribute(ATT_ENTITIES, entities);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    /**
     * A method to filter a contact list with sectors associated to a given account.
     *
     * @param contact_list the list of contacts ot be filtered
     * @param user the account containing the sectors to filter with
     *
     * @return the filtered list of contacts
     */
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

                // If contact already visible, don't check other sectors
                if (is_visible){
                    break;
                }
            }

            // If contact not visible in any user sector, check if visible in any global sector (if no make it visible for user)
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

    /**
     * A method that filters a contact list according to filter words
     *
     * @param contact_list the contact list to filter
     * @param filters the list of filter words split from user input
     */
    private void filterContactsByResearch(ArrayList<Contact> contact_list, String[] filters){
        // Initialize variables
        int i = 0;
        Contact contact;
        boolean is_visible;

        // For each contact
        do {
            contact = contact_list.get(i);
            is_visible = false;

            // For each filter word check if research criteria corresponds (all filter words need to match, event partially)
            for (String filter:filters){
                // No case sensitivity
                filter = filter.toLowerCase();

                // If the filter word matches (even partially) : name, surname, role, entity name (if any)
                if (contact.getName().toLowerCase().contains(filter) ||
                        contact.getSurname().toLowerCase().contains(filter) ||
                        contact.getRole().toLowerCase().contains(filter) ||
                        (contact.getEntity() != null && contact.getEntity().getName().toLowerCase().contains(filter))){

                    is_visible = true;
                } else {
                    is_visible = false;
                    break;
                }
            }

            // If not visible, then remove, else continue
            if (!is_visible){
                contact_list.remove(i);
            }
            else {
                i++;
            }

        }while (i<contact_list.size());
    }

    /**
     * A method that filters an entity list according to filter words
     *
     * @param entities_list the contact list to filter
     * @param filters the list of filter words split from user input
     */
    private void filterEntitiesByResearch(ArrayList<Entity> entities_list, String[] filters){
        // Initialize variables
        int i = 0;
        Entity entity;
        boolean is_visible;

        // For each entity
        do {
            entity = entities_list.get(i);
            is_visible = false;

            // For each filter word check if research criteria corresponds (all filter words need to match, event partially)
            for (String filter:filters){
                // No case sensitivity
                filter = filter.toLowerCase();

                // If the filter word matches (even partially) : name, type
                if (entity.getName().toLowerCase().contains(filter) ||
                        entity.getType().toLowerCase().contains(filter)){

                    is_visible = true;
                } else {
                    is_visible = false;
                    break;
                }
            }

            // If not visible, then remove, else continue
            if (!is_visible){
                entities_list.remove(i);
            } else {
                i++;
            }

        }while (i<entities_list.size());
    }
}
