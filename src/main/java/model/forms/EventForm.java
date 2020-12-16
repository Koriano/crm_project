package model.forms;

import controller.DAO.ContactDAO;
import controller.DAO.EventDAO;
import controller.DAO.EventTypeDAO;
import model.Account;
import model.Contact;
import model.Event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

public class EventForm {
    private static final String PARAM_SESSION_USER_ACCOUNT = "user";

    private static final String PARAM_NAME = "name";
    private static final String PARAM_DATE = "date";
    private static final String PARAM_TIME = "time";
    private static final String PARAM_TYPE = "type";
    private static final String PARAM_DESCRIPTION = "description";
    private static final String PARAM_CONTACTS = "contacts";

    private HashMap<String, String> errors;
    private String result;

    private EventTypeDAO eventTypeDAO;
    private ContactDAO contactDAO;

    public EventForm(){
        this.errors = new HashMap<>();
        this.result = "";

        this.eventTypeDAO = EventTypeDAO.getInstance();
        this.contactDAO = ContactDAO.getInstance();
    }

    public Event createEvent(HttpServletRequest req){
        // Get session and user account
        HttpSession session = req.getSession();
        Account user = (Account) session.getAttribute(PARAM_SESSION_USER_ACCOUNT);

        // Get request attributes
        String name = req.getParameter(PARAM_NAME);
        String date = req.getParameter(PARAM_DATE);
        String time = req.getParameter(PARAM_TIME);
        String type = req.getParameter(PARAM_TYPE);
        String description = req.getParameter(PARAM_DESCRIPTION);
        String[] contacts_id = req.getParameterValues(PARAM_CONTACTS);

        // Verify each form field (except description)
        try{
            nameVerification(name);
        } catch (Exception e) {
            this.setError(PARAM_NAME, e.getMessage());
        }

        Date date_obj = null;
        try {
            date_obj = dateAndTimeVerification(date, time);
        } catch (Exception e) {
            this.setError(PARAM_DATE, e.getMessage());
        }

        try{
            typeVerification(type);
        } catch (Exception e) {
            this.setError(PARAM_TYPE, e.getMessage());
        }

        try{
            descriptionVerification(description);
        } catch (Exception e){
            this.setError(PARAM_DESCRIPTION, e.getMessage());
        }

        ArrayList<Contact> contact_list = new ArrayList<>();
        try{
            contact_list = contactsVerification(contacts_id);
        } catch (Exception e) {
            this.setError(PARAM_CONTACTS, e.getMessage());
        }

        // Create Event object
        Event event = new Event(name, date_obj, user.getContact(), type);
        event.setDescription(description);

        for(Contact contact:contact_list){
            event.addContact(contact);
        }

        // Set result depending on errors
        if (this.errors.isEmpty()){
            this.result = "Succès";
        } else {
            this.result = "Echec";
        }

        return event;
    }

    // VERIFICATION

    private void nameVerification(String name) throws Exception {
        if (name == null || name.isEmpty()){
            throw new Exception("Merci de nommer votre événement");
        }
    }

    private Date dateAndTimeVerification(String date, String time) throws Exception {
        // Set formatter
        SimpleDateFormat date_time_format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            dateVerification(date);
        } catch (Exception e){
            this.setError(PARAM_DATE, e.getMessage());
        }

        try {
            timeVerification(time);
        } catch (Exception e){
            this.setError(PARAM_TIME, e.getMessage());
        }

        try {
            return date_time_format.parse(date + " " + time);
        } catch (Exception e) {
            throw new Exception("La date et/ou l'heure n'est pas valide.");
        }
    }

    private void dateVerification(String date) throws Exception {
        // Set formatter
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");

        // Try to transform date into object
        try {
            date_format.parse(date);
        } catch (Exception e){
            throw new Exception("Merci de rentrer une date valide.");
        }
    }

    private void timeVerification(String time) throws Exception {
        // Set formatter
        SimpleDateFormat time_format = new SimpleDateFormat("HH:mm");

        // Try to transform date into object
        try {
            time_format.parse(time);
        } catch (Exception e){
            throw new Exception("Merci de rentrer une heure valide.");
        }
    }

    private void typeVerification(String type) throws Exception {
        // Get every type
        ArrayList<String> types = this.eventTypeDAO.getAllTypes();

        if (type == null || type.isEmpty()){
            throw new Exception("Merci de renseigner un type d'événement.");
        } else if (!types.contains(type)) {
            throw new Exception("Merci de renseigner un type d'événement existant");
        }
    }

    private void descriptionVerification(String description) throws Exception {
        if (description != null && description.length() > 1000){
            throw new Exception("La description est trop longue (1 000 caracteres max).");
        }
    }

    private ArrayList<Contact> contactsVerification(String[] contacts){
        ArrayList<Contact> contact_list = new ArrayList<>();

        if (contacts == null){
            return contact_list;
        }

        for (String contact_id:contacts){
            try {
                contact_list.add(contactVerification(contact_id));
            } catch (Exception e){
                this.setError(PARAM_CONTACTS, e.getMessage());
            }
        }

        return contact_list;
    }

    private Contact contactVerification(String contact_id) throws Exception {

        if(contact_id == null || contact_id.isEmpty()){
            throw new Exception("Merci de renseigner des contacts existants.");
        } else {

            try {
                int id = Integer.parseInt(contact_id);
                Contact contact = this.contactDAO.getContactById(id);

                if (contact != null){
                    return contact;
                } else {
                    throw new Exception("Merci de renseigner des contacts existants.");
                }

            } catch (Exception e){
                throw new Exception("Merci de renseigner des contacts existants.");
            }
        }
    }

    // UTILS

    /**
     * A method to set a message error associated with a parameter
     *
     * @param param the parameter name
     * @param message the error message
     */
    private void setError(String param, String message){
        this.errors.put(param, message);
    }

    // GETTERS
    public String getResult() {
        return this.result;
    }

    public HashMap<String, String> getErrors(){
        return this.errors;
    }
}
