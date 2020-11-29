package model.forms;

import controller.DAO.AccountDAO;
import controller.DAO.ContactDAO;
import controller.DAO.SectorDAO;
import model.Account;
import model.Contact;
import model.Sector;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AccountForm {
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_ACCOUNT_NAME = "name";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_CONFIRMATION = "confirmation";
    private static final String PARAM_RIGHT = "right";
    private static final String PARAM_CONTACT = "contact";
    private static final String PARAM_SECTORS = "sectors";

    private HashMap<String, String> errors;
    private String result;

    private final AccountDAO accountDAO;
    private final ContactDAO contactDAO;
    private final SectorDAO sectorDAO;

    public AccountForm(){
        this.errors = new HashMap<>();
        this.result = "";

        this.accountDAO = AccountDAO.getInstance();
        this.contactDAO = ContactDAO.getInstance();
        this.sectorDAO = SectorDAO.getInstance();
    }

    public Account createAccount(HttpServletRequest req, String action){
        // Get parameters
        String username = req.getParameter(PARAM_USERNAME);
        String name = req.getParameter(PARAM_ACCOUNT_NAME);
        String right = req.getParameter(PARAM_RIGHT);
        String contact_id = req.getParameter(PARAM_CONTACT);
        String[] sectors = req.getParameterValues(PARAM_SECTORS);

        String password = null;
        // Get password if on add page
        if ("add".equals(action)){
            PasswordForm passwordForm = new PasswordForm();
            password = passwordForm.checkPassword(req);
            this.errors = passwordForm.getErrors();
        }

        // Check username
        try{
            usernameVerification(username);
        } catch (Exception e){
            setError(PARAM_USERNAME, e.getMessage());
        }

        // Check account name
        try{
            nameVerification(name);
        } catch (Exception e){
            setError(PARAM_ACCOUNT_NAME, e.getMessage());
        }

        // Check right
        try{
            rightVerification(right);
        } catch (Exception e){
            setError(PARAM_RIGHT, e.getMessage());
        }

        // Check associated contact
        Contact contact = null;
        try{
            contact = contactVerification(contact_id);
        } catch (Exception e){
            setError(PARAM_CONTACT, e.getMessage());
        }

        // Check sectors
        ArrayList<Sector> sector_list = null;
        try{
            sector_list = sectorsVerification(sectors);
        } catch (Exception e){
            this.setError(PARAM_SECTORS, e.getMessage());
        }


        Account account = new Account(username.trim(), password, name.trim(), right, contact, sector_list);

        if(this.errors.isEmpty()){
            this.result = "Succes !";
        } else {
            this.result = "Echec.";
        }

        return account;
    }

    // VERIFICATIONS

    private void usernameVerification(String username) throws Exception{
        if(username == null || username.isEmpty()){
            throw new Exception("Merci de rentrer un nom d'utilisateur.");
        } else if(username.trim().length() > 25){
            throw new Exception("Merci de renter un nom de 25 caractères maximum.");
        }
    }

    private void nameVerification(String name) throws Exception{
        if(name == null || name.isEmpty()){
            throw new Exception("Merci de rentrer un nom de compte.");
        } else if(name.trim().length() > 30){
            throw new Exception("Merci de renter un nom de 30 caractères maximum.");
        }
    }

    private void rightVerification(String right) throws Exception{
        ArrayList<String> rights = this.accountDAO.getAllRight();

        if(right == null){
            throw new Exception("Merci de sélectionner un droit.");
        } else if (!rights.contains(right)){
            throw new Exception("Merci de sélectionner un droit existant.");
        }
    }

    private Contact contactVerification(String contact_id) throws Exception{
        int id = -1;
        try{
            id = Integer.parseInt(contact_id);
        } catch (Exception e){
            throw new Exception("Merci de sélectionner un contact valide.");
        }

        if(id != -1){
            Contact contact = this.contactDAO.getContactById(id);

            if(contact != null){
                return contact;
            } else {
                throw new Exception("Merci de sélectionner un contact existant");
            }
        } else {
            return null;
        }
    }

    private ArrayList<Sector> sectorsVerification(String[] sectors) throws Exception{
        ArrayList<Sector> returned_sectors = new ArrayList<>();

        if (sectors == null){
            throw new Exception("Merci de sélectionner au moins un secteur.");
        }

        for (String sector:sectors){
            try {
                returned_sectors.add(sectorVerification(sector));
            } catch (Exception e){
                this.setError(PARAM_SECTORS, e.getMessage());
            }
        }

        return returned_sectors;
    }

    private Sector sectorVerification(String sector) throws Exception{
        if (sector == null || sector.isEmpty() || !this.sectorDAO.isSectorExist(sector)){
            throw new Exception("Merci de sélectionner des secteurs existants.");
        }
        else {
            Sector sector_obj = new Sector(sector);
            this.sectorDAO.getContactList(sector_obj);
            return sector_obj;
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
