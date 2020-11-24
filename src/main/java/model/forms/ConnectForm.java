package model.forms;

import model.Account;
import model.Contact;
import model.Sector;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ConnectForm {
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";

    private boolean error;
    private String result;

//    private AccountDAO accountDAO;

    public ConnectForm(){
        this.error = false;
        this.result = "";

//        this.accountDAO = AccountDAO.getInstance();
    }

    public Account connectUser(HttpServletRequest req){
        String username = req.getParameter(PARAM_USERNAME);
        String password = req.getParameter(PARAM_PASSWORD);

        this.usernameVerification(username);
        this.passwordVerification(password);

        Account user = null;
        if(!this.error){
//            this.error = !this.accountDAO.checkLogin(username, password);
            if(!this.error){
//                user = this.accountDAO.getAccountByName(username);
            }
        }

        // SIMULATION A SUPPR ************
        Contact contact = new Contact("Hamon", "Alexandre", "Eleve", null, false, 0);
        ArrayList<Sector> sectors = new ArrayList<>();
        sectors.add(new Sector("slt"));

        user = new Account("alex29", "1234azerty", "Alex", "Administrateur", contact, sectors);
        // *******************************

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
