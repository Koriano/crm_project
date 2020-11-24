package model.forms;

import model.Account;
import model.Contact;
import model.Sector;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class AccountRightsForm {
    private static final String PARAM_RIGHT = "right";

    private HashMap<String, String> error;
    private String result;

//    private AccountDAO accountDAO;

    public AccountRightsForm(){
        this.error = new HashMap<>();
        this.result = "";
//        this.accountDAO = AccountDAO.getInstance();
    }

    public ArrayList<Account> updateRights(HttpServletRequest req){
        // Get all accounts and get every parameter
//        ArrayList<Account> accounts = accountDAO.getAllAccounts();

        // SIMULATION A SUPPR ************************
        ArrayList<Account> accounts = new ArrayList<>();

        Contact contact = new Contact("Hamon", "Alexandre", "Eleve", null, false, 0);
        ArrayList<Sector> sectors = new ArrayList<>();
        sectors.add(new Sector("slt"));

        accounts.add(new Account("alex29", "1234azerty", "Alex", "Administrateur", contact, sectors));
        accounts.add(new Account("bibi29","1234","Bibi", "Lecture seule", contact, sectors));
        // **********************************************

        // Get all rights
        HashMap<Account, String> modifications = new HashMap<>();
        String right;

        for (Account acc:accounts){
            right = req.getParameter(acc.getUsername());

            try{
                rightVerification(right);
                modifications.put(acc, right);
//                accountDAO.updateAccount(acc);
            } catch (Exception e){
                this.setError(PARAM_RIGHT, e.getMessage());
            }
        }

        // Check if there's at least one admin
        try{
            atLeastOneAdminVerification(modifications.values());
        } catch (Exception e){
            this.setError(PARAM_RIGHT, e.getMessage());
        }

        // Update every account if no error
        if (this.error.isEmpty()) {
            for (Account acc : modifications.keySet()) {
                acc.setRight(modifications.get(acc));
//                accountDAO.updateAccount(acc);
            }

            this.result = "Succes.";
        }
        else {
            this.result = "Echec.";
        }

//        accounts = accountDAO.getAllAccounts();

        return accounts;
    }

    // VERIFICATION

    private void rightVerification(String right) throws Exception {
//        ArrayList<String> rights = AccountDAO.getAllRights();

        // SIMULATION A SUPPR *********************
        ArrayList<String> rights = new ArrayList<>();
        rights.add("Administrateur");
        rights.add("Alimentation");
        rights.add("Lecture seule");
        // ****************************************

        if(right == null){
            throw new Exception("Merci d'indiquer des droits pour tous les comptes.");
        } else if(!rights.contains(right)){
            throw new Exception("Merci d'indiquer des droits existants.");
        }
    }

    private void atLeastOneAdminVerification(Collection<String> rights) throws Exception{
        if(!rights.contains("Administrateur")){
            throw new Exception("Merci de laisser au moins un administrateur");
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
        this.error.put(param, message);
    }

    // GETTER
    public HashMap<String, String> getError(){
        return this.error;
    }

    public String getResult(){
        return this.result;
    }
}
