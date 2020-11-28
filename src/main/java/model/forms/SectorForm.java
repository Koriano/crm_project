package model.forms;

import controller.DAO.ContactDAO;
import model.Contact;
import model.Sector;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

public class SectorForm {
    private static final String PARAM_NAME = "name";
    private static final String PARAM_CONTACTS = "contacts";

    private String result;
    private HashMap<String, String> errors;

    private ContactDAO contactDAO;

    public SectorForm(){
        this.result = "";
        this.errors = new HashMap<>();

        this.contactDAO = ContactDAO.getInstance();
    }

    public Sector createSector(HttpServletRequest req){
        // Get every parameters
        String name = req.getParameter(PARAM_NAME);
        String[] contacts = req.getParameterValues(PARAM_CONTACTS);

        // Verify every form field
        try{
            nameVerification(name);
        } catch (Exception e){
            this.setError(PARAM_NAME, e.getMessage());
        }

        ArrayList<Contact> contact_list = new ArrayList<>();
        try {
            contact_list = contactsVerification(contacts);
        } catch (Exception e){
            this.setError(PARAM_CONTACTS, e.getMessage());
        }

        // Create returned object
        Sector sector = new Sector(name);
        for (Contact contact:contact_list){
            sector.addContact(contact);
        }

        if(this.errors.isEmpty()){
            this.result = "Succès";
        } else {
            this.result = "Echec";
        }

        return sector;
    }

    // VERIFICATION

    private void nameVerification(String name) throws Exception {
        if(name == null || name.isEmpty()){
            throw new Exception("Merci de saisir un nom.");
        } else if(name.trim().length() > 40){
            throw new Exception("Merci de renseigner un nom de moins de 40 caractères.");
        }
    }

    private ArrayList<Contact> contactsVerification(String[] contacts){
        ArrayList<Contact> contact_list = new ArrayList<>();

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
    public HashMap<String, String> getErrors(){
        return this.errors;
    }

    public String getResult(){
        return this.result;
    }
}
