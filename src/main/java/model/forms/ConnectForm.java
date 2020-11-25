package model.forms;

import controller.DAO.AccountDAO;
import model.Account;

import javax.servlet.http.HttpServletRequest;

public class ConnectForm {
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";

    private boolean error;
    private String result;

    private AccountDAO accountDAO;

    public ConnectForm(){
        this.error = false;
        this.result = "";

        this.accountDAO = AccountDAO.getInstance();
    }

    public Account connectUser(HttpServletRequest req){
        // Get form inputs
        String username = req.getParameter(PARAM_USERNAME);
        String password = req.getParameter(PARAM_PASSWORD);

        // Check inputs
        this.usernameVerification(username);
        this.passwordVerification(password);

        // Check if login and password are correct, if true, get user account
        Account user = null;
        if(!this.error){
            this.error = !this.accountDAO.checkLogin(username, password);
            if(!this.error){
                user = this.accountDAO.getAccountByName(username);
            }
        }

        // If a field is not correct, then do not return user
        if (!this.error){
            this.result = "Succès de la connexion !";
        }
        else {
            this.result = "Echec de la connexion.";
        }

        return user;
    }

    // VERIFICATIONS

    private void usernameVerification(String username){
        if (username == null || username.trim().isEmpty()){
            this.error = true;
        }
        else if(username.trim().length() > 20){
            this.error = true;
        }
    }

    private void passwordVerification(String password){
        if(password == null || password.trim().isEmpty()){
            this.error = true;
        }
        else if(password.trim().length() > 100){
            this.error = true;
        }
    }

    // GETTERS

    public boolean hasError(){
        return this.error;
    }

    public String getResult(){
        return this.result;
    }

}
