package model.forms;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class PasswordForm {
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_CONFIRMATION = "confirmation";

    private HashMap<String, String> errors;

    public PasswordForm(){
        this.errors = new HashMap<>();
    }

    public String checkPassword(HttpServletRequest req){
        String password = req.getParameter(PARAM_PASSWORD);
        String confirmation = req.getParameter(PARAM_CONFIRMATION);

        // Check password
        try{
            passwordVerification(password, confirmation);
        } catch (Exception e){
            setError(PARAM_PASSWORD, e.getMessage());
        }

        return password;
    }

    // VERIFICATIONS

    private void passwordVerification(String password, String confirmation) throws Exception{
        if(password == null || password.isEmpty() || confirmation == null || confirmation.isEmpty()){
            throw new Exception("Merci de saisir un mot de passe et de le confirmer.");
        } else if(password.length() > 100){
            throw new Exception("Merci de saisir un mot de passe de 100 caractères maximum.");
        } else if(!password.equals(confirmation)){
            throw new Exception("Les deux mots de passe ne correspondent pas.");
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
}
