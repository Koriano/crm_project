package model.forms;

import controller.DAO.ContactDAO;
import controller.DAO.EntityDAO;
import model.Account;
import model.Contact;
import model.Entity;
import sun.reflect.annotation.ExceptionProxy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ContactForm {
    /**
     * Form parameters
     */
    private static final String PARAM_ID = "id";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_SURNAME = "surname";
    private static final String PARAM_ROLE = "role";
    private static final String PARAM_ENTITY = "entity";
    private static final String PARAM_ADDRESS = "address";
    private static final String PARAM_PREFIX_PHONE = "phone";
    private static final String PARAM_PREFIX_MAIL = "mail";
    private static final String PARAM_RESERVED = "reserved";
    private static final String PARAM_REFERENT = "referent";

    /**
     * Result and errors map
     */
    private String result;
    private HashMap<String, String> errors;
    private boolean is_double;

    /**
     * DAOs
     */
    private ContactDAO contactDAO;
    private EntityDAO entityDAO;

    /**
     * Constructor used to initialize the form
     */
    public ContactForm(){
        this.result = "";
        this.errors = new HashMap<>();

        this.contactDAO = ContactDAO.getInstance();
        this.entityDAO = EntityDAO.getInstance();
    }

    /**
     * A method used to check form fields and create contact from this.
     *
     * @param req the request containing the parameters
     *
     * @return the newly created contact
     */
    public Contact createContact(HttpServletRequest req, String action){

        // Get parameter from request
        String id = req.getParameter(PARAM_ID);
        String name = req.getParameter(PARAM_NAME);
        String surname = req.getParameter(PARAM_SURNAME);
        String role = req.getParameter(PARAM_ROLE);
        String entity = req.getParameter(PARAM_ENTITY);
        String address = req.getParameter(PARAM_ADDRESS);
        ArrayList<String> phones = this.getAllParam(req, PARAM_PREFIX_PHONE);
        ArrayList<String> mails = this.getAllParam(req, PARAM_PREFIX_MAIL);
        String reserved = req.getParameter(PARAM_RESERVED);
        String referent = req.getParameter(PARAM_REFERENT);

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

        // Verify address
        try{
            addressVerification(address);
        } catch (Exception e){
            this.setError(PARAM_ADDRESS, e.getMessage());
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
            referent_contact = reservedVerification(reserved, referent, id, action);
        } catch (Exception e){
            this.setError(PARAM_REFERENT, e.getMessage());
        }

        // Create the new contact and set properties
        Contact contact = new Contact(name.trim(), surname.trim(), role, referent_contact, "on".equals(reserved));

        try{
            contact.setId(Integer.parseInt(id));
        } catch (Exception ignored){

        }

        // If no error on entity, set it
        if(!this.errors.containsKey(PARAM_ENTITY)){
            contact.setEntity(entity_object);
        }

        // If address is not null, set it
        if(address != null){
            contact.setAddress(address);
        }

        // Add every not empty phone
        for(String phone:phones){
            if (!phone.isEmpty()) {
                contact.addPhone(phone.trim());
            }
        }

        // Add every not empty mail
        for(String mail:mails){
            if (!mail.isEmpty()) {
                contact.addMail(mail.trim());
            }
        }

        this.setDouble(this.isDoubleVerification(contact));

        // Set result according to errors
        if (this.errors.isEmpty()) {
            this.result = "Succes !";
        } else {
            this.result = "Echec.";
        }

        return contact;
    }

    // VERIFICATIONS

    /**
     * A method to verify name field
     *
     * @param name the value of the name field
     * @throws Exception
     */
    private void nameVerification(String name) throws Exception{
        // if name field given
        if(name == null || name.trim().isEmpty()){
            throw new Exception("Merci de rentrer un nom.");
        }
        // if length is not too long
        else if (name.trim().length() > 50){
            throw new Exception("Merci de rentrer un nom de moins de 50 caracteres");
        }
    }

    /**
     * A method to verify surname field
     *
     * @param surname the value of the surname field
     * @throws Exception
     */
    private void surnameVerification(String surname) throws Exception{
        // if surname field is given
        if(surname == null || surname.trim().isEmpty()){
            throw new Exception("Merci de rentrer un prenom.");
        }
        // if surname is not too long
        else if (surname.trim().length() > 50){
            throw new Exception("Merci de rentrer un prenom de moins de 50 caracteres");
        }
    }

    /**
     * A method to verify role field
     *
     * @param role the value of the role field
     * @throws Exception
     */
    private void roleVerification(String role) throws Exception{
        ArrayList<String> roles = this.contactDAO.getAllRoles();

        // role needs to be in role list
        if(!roles.contains(role)){
            throw new Exception("Merci de rentrer un role valide.");
        }
    }

    /**
     * A method to verify entity field
     *
     * @param entity the value of entity field
     * @return the corresponding entity object
     * @throws Exception
     */
    private Entity entityVerification(String entity) throws Exception{
        if(entity != null) {

            if(entity.isEmpty()){
                return null;
            }

            try {
                Entity entity_obj = this.entityDAO.getEntityById(Integer.parseInt(entity));

                if(entity_obj == null){
                    throw new Exception("Cette entite n'existe pas, merci de renseigner une entite existante.");
                }
                else {
                    return entity_obj;
                }
            } catch (Exception e){
                throw new Exception("Merci de renseigner une entite.");
            }

        } else {
            throw new Exception("Merci de renseigner une entite.");
        }
    }

    /**
     * A method to verify address field
     *
     * @param address the contact address
     * @throws Exception
     */
    private void addressVerification(String address) throws Exception {
        if (address != null && address.length() > 80){
            throw new Exception("L'adresse contient plus de 80 caracteres.");
        }
    }

    /**
     * A method to verify phone field
     *
     * @param phone the value of the phone field
     * @throws Exception
     */
    private void phoneVerification(String phone) throws Exception{
        if (!phone.isEmpty() && !phone.matches("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$")){
            throw new Exception("Un ou plusieurs numeros de telephone sont invalides.");
        }else if(phone.trim().length() > 20){
            throw new Exception("Un ou plusieurs numeros de telephone contiennent plus de 20 chiffres.");
        }
    }

    /**
     * A method to verify mail field
     *
     * @param mail the value of the mail field
     * @throws Exception
     */
    private void mailVerification(String mail) throws Exception{
        if(!mail.isEmpty() && !mail.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")){
            throw new Exception("Une ou plusieurs adresses mail sont invalides.");
        }else if(mail.trim().length() > 60){
            throw new Exception("Une ou plusieurs adresses mail contiennent plus de 60 caracteres.");
        }
    }

    /**
     * A method to verify reserved field
     *
     * @param reserved whether the contact is reserved or not
     * @param referent the referent, if contact is reserved
     * @return
     * @throws Exception
     */
    private Contact reservedVerification(String reserved, String referent, String old_contact_id, String action) throws Exception{
        // If reserved field doesn't exist
        if (reserved == null){
            return null;
        }
        else {
            boolean is_reserved = "on".equals(reserved);

            // If the contact is reserved and there is a referent field not empty
            if(is_reserved && referent != null && !referent.isEmpty()){
                try{
                    int id = Integer.parseInt(referent);

                    if ("modify".equals(action)) {
                        int old_id = Integer.parseInt(old_contact_id);

                        if (id == old_id){
                            throw new Exception("Vous ne pouvez pas etre votre propre referent !");
                        }
                    }

                    Contact referent_contact = this.contactDAO.getContactById(id);

                    if (referent_contact == null){
                        throw new Exception("Merci de selectionner un referent existant.");
                    } else if (referent_contact.isReserved()){
                        throw new Exception("Ce contact est reserve et ne peut pas etre referent.");
                    } else {
                        return referent_contact;
                    }
                } catch (NumberFormatException e){
                    throw new Exception("Merci de selectionner un referent valide.");
                }
            }
            // If the contact is reserved but there's no referent field, or it is empty (selected by default)
            else if(referent == null || referent.isEmpty()){
                throw new Exception("Merci de selectionner un referent ou d'indiquer que le contact n'est pas reserve.");
            }
            else {
                return null;
            }
        }
    }

    /**
     * A method to check if a similar contact already exists in base
     *
     * @param contact the newly created contact to be checked
     * @return true if a similar contact exists, else false
     */
    private boolean isDoubleVerification(Contact contact){
        ArrayList<Contact> contacts = this.contactDAO.getAllContacts();
        // If name, surname are equals, and not itself
        for (Contact c:contacts){
            if (c.getName().toLowerCase().equals(contact.getName().toLowerCase()) && c.getSurname().toLowerCase().equals(contact.getSurname().toLowerCase()) && c.getId() != contact.getId()) {
                return true;
            }
        }

        return false;
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

    public void setDouble(boolean is_double){
        this.is_double = is_double;
    }

    // GETTERS
    public String getResult() {
        return this.result;
    }

    public HashMap<String, String> getErrors(){
        return this.errors;
    }

    public boolean isDouble(){
        return this.is_double;
    }
}
