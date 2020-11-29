package model.forms;

import controller.DAO.ContactDAO;
import controller.DAO.EntityDAO;
import model.Contact;
import model.Entity;
import sun.reflect.annotation.ExceptionProxy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

public class ContactForm {
    private static final String PARAM_SESSION_ID_CONTACT = "contact_id";

    private static final String PARAM_NAME = "name";
    private static final String PARAM_SURNAME = "surname";
    private static final String PARAM_ROLE = "role";
    private static final String PARAM_ENTITY = "entity";
    private static final String PARAM_ADDRESS = "address";
    private static final String PARAM_PREFIX_PHONE = "phone";
    private static final String PARAM_PREFIX_MAIL = "mail";
    private static final String PARAM_RESERVED = "reserved";
    private static final String PARAM_REFERENT = "referent";

    private String result;
    private HashMap<String, String> errors;

    private ContactDAO contactDAO;
    private EntityDAO entityDAO;

    public ContactForm(){
        this.result = "";
        this.errors = new HashMap<>();

        this.contactDAO = ContactDAO.getInstance();
        this.entityDAO = EntityDAO.getInstance();
    }

    public Contact createContact(HttpServletRequest req){
        // Get parameter from request
        String name = req.getParameter(PARAM_NAME);
        String surname = req.getParameter(PARAM_SURNAME);
        String role = req.getParameter(PARAM_ROLE);
        String entity = req.getParameter(PARAM_ENTITY);
        String address = req.getParameter(PARAM_ADDRESS);
        ArrayList<String> phones = this.getAllParam(req, PARAM_PREFIX_PHONE);
        ArrayList<String> mails = this.getAllParam(req, PARAM_PREFIX_MAIL);
        String reserved = req.getParameter(PARAM_RESERVED);
        String referent = req.getParameter(PARAM_REFERENT);

        // Get contact from session id
        HttpSession session = req.getSession();
        String id = (String) session.getAttribute(PARAM_SESSION_ID_CONTACT);

        // Verify name
        try{
            nameVerification(name);
        }catch (Exception e){
            this.setError(PARAM_NAME, e.getMessage());
        }

        // Verify surname
        try{
            surnameVerification(surname);
        }catch (Exception e){
            this.setError(PARAM_SURNAME, e.getMessage());
        }

        // Verify role
        try{
            roleVerification(role);
        }catch (Exception e){
            this.setError(PARAM_ROLE, e.getMessage());
        }

        // Verify entity
        Entity entity_object = null;
        try{
            entity_object = entityVerification(entity);
        }catch (Exception e){
            this.setError(PARAM_ENTITY, e.getMessage());
        }

        // Verify phones
        for(String phone:phones){
            try{
                phoneVerification(phone);
            }catch (Exception e){
                this.setError(PARAM_PREFIX_PHONE, e.getMessage());
                break;
            }
        }

        // Verify mails
        for(String mail:mails){
            try{
                mailVerification(mail);
            }catch (Exception e){
                this.setError(PARAM_PREFIX_MAIL, e.getMessage());
                break;
            }
        }

        // Verify reserved and referent
        Contact referent_contact = null;
        try {
            referent_contact = reservedVerification(reserved, referent);
        } catch (Exception e){
            this.setError(PARAM_REFERENT, e.getMessage());
        }

        // Create the new contact and set properties
        Contact contact;
        if(id != null){
            contact = new Contact(name.trim(), surname.trim(), role, referent_contact, "on".equals(reserved), Integer.parseInt(id));
        } else {
            contact = new Contact(name.trim(), surname.trim(), role, referent_contact, "on".equals(reserved));
        }

        if(!this.errors.containsKey(PARAM_ENTITY)){
            contact.setEntity(entity_object);
        }

        if(address != null){
            contact.setAddress(address);
        }

        for(String phone:phones){
            if (!phone.isEmpty()) {
                contact.addPhone(phone.trim());
            }
        }

        for(String mail:mails){
            if (!mail.isEmpty()) {
                contact.addMail(mail.trim());
            }
        }


        if(this.errors.isEmpty()){
            this.result = "Succès !";
        }
        else {
            this.result = "Echec.";
        }

        return contact;
    }

    // VERIFICATIONS

    private void nameVerification(String name) throws Exception{
        if(name == null || name.trim().isEmpty()){
            throw new Exception("Merci de rentrer un nom.");
        }else if (name.trim().length() > 50){
            throw new Exception("Merci de rentrer un nom de moins de 50 caractères");
        }
    }

    private void surnameVerification(String surname) throws Exception{
        if(surname == null || surname.trim().isEmpty()){
            throw new Exception("Merci de rentrer un prénom.");
        } else if (surname.trim().length() > 50){
            throw new Exception("Merci de rentrer un prénom de moins de 50 caractères");
        }
    }

    private void roleVerification(String role) throws Exception{
        ArrayList<String> roles = this.contactDAO.getAllRoles();

        if(!roles.contains(role)){
            throw new Exception("Merci de rentrer un role valide.");
        }
    }

    private Entity entityVerification(String entity) throws Exception{
        if(entity != null) {

            if(entity.isEmpty()){
                return null;
            }

            try {
                Entity entity_obj = this.entityDAO.getEntityById(Integer.parseInt(entity));

                if(entity_obj == null){
                    throw new Exception("Cette entité n'existe pas, merci de renseigner une entité existante.");
                }
                else {
                    return entity_obj;
                }
            } catch (Exception e){
                throw new Exception("Merci de renseigner une entité.");
            }

        } else {
            throw new Exception("Merci de renseigner une entité.");
        }
    }

    private void phoneVerification(String phone) throws Exception{
        if (!phone.isEmpty() && !phone.matches("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$")){
            throw new Exception("Un ou plusieurs numéros de téléphone sont invalides.");
        }else if(phone.trim().length() > 20){
            throw new Exception("Un ou plusieurs numéros de téléphone contiennent plus de 20 chiffres.");
        }
    }

    private void mailVerification(String mail) throws Exception{
        if(!mail.isEmpty() && !mail.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")){
            throw new Exception("Une ou plusieurs adresses mail sont invalides.");
        }else if(mail.trim().length() > 60){
            throw new Exception("Une ou plusieurs adresses mail contiennent plus de 60 caractères.");
        }
    }

    private Contact reservedVerification(String reserved, String referent) throws Exception{
        // If reserved field doesn't exists
        if (reserved == null){
            return null;
        }
        else {
            boolean is_reserved = "on".equals(reserved);

            // If the contact is reserved and there is a referent field not empty
            if(is_reserved && referent != null && !referent.isEmpty()){
                try{
                    int id = Integer.parseInt(referent);
                    return this.contactDAO.getContactById(id);

                }catch (Exception e){
                    throw new Exception("Merci de sélectionner un référent valide.");
                }
            }
            // If the contact is reserved but there's no referent field, or it is empty (selected by default)
            else if(referent == null || referent.isEmpty()){
                throw new Exception("Merci de sélectionner un référent valide.");
            }
            // If the contact is not reserved
            else {
                return null;
            }
        }
    }


    // UTILS

    /**
     * A method to get every parameter from req starting with prefix
     *
     * @param req the request containing the parameters
     * @param prefix the prefix of the parameters to get
     * @return a list of parameters, all starting with the same prefix
     */
    private ArrayList<String> getAllParam(HttpServletRequest req, String prefix){
        ArrayList<String> result = new ArrayList<>();
        int i = 0;
        String actual = req.getParameter(prefix + i);
        while(actual != null){
            result.add(actual);
            i++;
            actual = req.getParameter(prefix + i);
        }

        return result;
    }

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
