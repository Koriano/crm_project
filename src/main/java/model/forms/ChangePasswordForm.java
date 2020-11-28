package model.forms;

import model.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class ChangePasswordForm {
    private static final String PARAM_OLD_PASSWORD = "old_password";
    private static final String PARAM_SESSION_ACCOUNT = "user";

    private HashMap<String, String> errors;
    private String result;

    public ChangePasswordForm(){
        this.errors = new HashMap<>();
        this.result = "";
    }

    public Account changePassword(HttpServletRequest req){
        // Get parameters
        String old_password = req.getParameter(PARAM_OLD_PASSWORD);

        PasswordForm passwordForm = new PasswordForm();
        String password = passwordForm.checkPassword(req);
        this.errors = passwordForm.getErrors();

        // Get account password
        HttpSession session = req.getSession();
        Account user_account = (Account) session.getAttribute(PARAM_SESSION_ACCOUNT);

        try{
            passwordVerification(user_account.getPassword(), old_password);
        } catch (Exception e){
            this.setError(PARAM_OLD_PASSWORD, e.getMessage());
        }

        if (this.errors.isEmpty()){
            this.result = "Succes";
        } else {
            this.result = "Echec";
        }

        user_account.setPassword(password);

        return user_account;
    }

    // VERIFICATION

    private void passwordVerification(String password, String confirmation) throws Exception{
        if(password == null || password.isEmpty() || confirmation == null || confirmation.isEmpty()){
            throw new Exception("Merci de saisir votre ancien mot de passe.");
        } else if(!password.equals(confirmation)){
            throw new Exception("Votre ancien mot de passe n'est pas bon.");
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
