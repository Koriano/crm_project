package model.forms;

import controller.DAO.AccountDAO;
import model.Account;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class AccountRightsForm {
    private static final String PARAM_RIGHT = "right";

    private HashMap<String, String> errors;
    private String result;

    private AccountDAO accountDAO;

    public AccountRightsForm(){
        this.errors = new HashMap<>();
        this.result = "";

        this.accountDAO = AccountDAO.getInstance();
    }

    public ArrayList<Account> updateRights(HttpServletRequest req){
        // Get all accounts and get every parameter
        ArrayList<Account> accounts = accountDAO.getAllAccounts();

        // Get all rights
        HashMap<Account, String> modifications = new HashMap<>();
        String right;

        for (Account acc:accounts){
            right = req.getParameter(acc.getUsername());

            try{
                rightVerification(right);
                modifications.put(acc, right);
                accountDAO.updateAccount(acc);
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
        if (this.errors.isEmpty()) {
            for (Account acc : modifications.keySet()) {
                acc.setRight(modifications.get(acc));
                accountDAO.updateAccount(acc);
            }

            this.result = "Succes.";
        }
        else {
            this.result = "Echec.";
        }

        accounts = accountDAO.getAllAccounts();

        return accounts;
    }

    // VERIFICATION

    private void rightVerification(String right) throws Exception {
        ArrayList<String> rights = this.accountDAO.getAllRight();

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
        this.errors.put(param, message);
    }

    // GETTER
    public HashMap<String, String> getErrors(){
        return this.errors;
    }

    public String getResult(){
        return this.result;
    }
}
