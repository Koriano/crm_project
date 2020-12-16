package model.forms;

import controller.DAO.EntityDAO;
import model.Entity;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

public class EntityForm {
    private static final String PARAM_NAME = "name";
    private static final String PARAM_TYPE = "type";
    private static final String PARAM_NEWTYPE = "newType";
    private static final String PARAM_SIRET = "siret";
    private static final String PARAM_ADDRESS = "address";
    private static final String PARAM_INTERN_NB = "intern_nb";
    private static final String PARAM_DESCRIPTION = "description";

    private boolean result;
    private HashMap<String, String> errors;

    private EntityDAO entityDAO;

    public EntityForm(){
        this.result = false;
        this.errors = new HashMap<>();
        this.entityDAO = EntityDAO.getInstance();
    }

    public Entity createEntity(HttpServletRequest req){
        // Get parameter from request
        int id = -1;
        try {
            id = Integer.parseInt(req.getParameter("entityId"));
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        String name = req.getParameter(PARAM_NAME);
        String type = req.getParameter(PARAM_TYPE);
        String newType = req.getParameter(PARAM_NEWTYPE);
        String siret = req.getParameter(PARAM_SIRET);
        if (!"Entreprise".equals(type)) {
            siret = null;
        }
        String address = req.getParameter(PARAM_ADDRESS);
        String intern_nb = req.getParameter(PARAM_INTERN_NB);
        String description = req.getParameter(PARAM_DESCRIPTION);
        if (description == null) {
            description = "";
        }

        // Verify name
        try{
            this.nameVerification(name);
        }catch (Exception e){
            this.setError(PARAM_NAME, e.getMessage());
        }

        // Verify type
        try{
            this.typeVerification(type, newType);
        } catch (Exception e){
            if ("Merci de rentrer un nouveau type valide".equals(e.getMessage())) {
                this.setError(PARAM_NEWTYPE, e.getMessage());
            } else {
                this.setError(PARAM_TYPE, e.getMessage());
            }
        }

        // Verify siret
        try{
            this.siretVerification(siret, type);
        }catch (Exception e){
            this.setError(PARAM_SIRET, e.getMessage());
        }

        // Verify address
        try{
            this.addressVerification(address);
        }catch (Exception e){
            this.setError(PARAM_ADDRESS, e.getMessage());
        }

        // Verify intern_nb
        try{
            this.internVerification(intern_nb);
        }catch (Exception e){
            this.setError(PARAM_INTERN_NB, e.getMessage());
        }

        // Verify description
        try{
            this.descriptionVerification(description);
        } catch (Exception e){
            this.setError(PARAM_DESCRIPTION, e.getMessage());
        }

        // Create the new entity and set properties
        Entity entity = new Entity(name, siret, "Nouveau type entite...".equals(type) ? newType : type);
        entity.setAddress(address);
        try {
            entity.setIntern_nb(Integer.parseInt(intern_nb));
        } catch (NumberFormatException e) {
            entity.setIntern_nb(0);
        }
        entity.setDescription(description);
        if (id != -1) {
            entity.setId(id);
        }

        this.result = this.errors.isEmpty();

        return entity;
    }

    // VERIFICATIONS

    private void nameVerification(String name) throws Exception{
        if(name == null || name.trim().isEmpty()){
            throw new Exception("Merci de rentrer un nom.");
        } else if (name.trim().length() > 20){
            throw new Exception("Merci de rentrer un nom de 20 caractères ou moins");
        }
    }

    private void siretVerification(String siret, String type) throws Exception{
        if("Entreprise".equals(type) && (siret == null || siret.trim().isEmpty())){
            throw new Exception("Merci de rentrer un num de SIRET.");
        } else if ("Entreprise".equals(type) && siret.trim().length() != 14){
            throw new Exception("Merci de rentrer un num de SIRET de 14 chiffres exactement");
        }
    }

    private void typeVerification(String type, String newType) throws Exception{
        ArrayList<String> types = this.entityDAO.getAllTypes();
        types.add("Nouveau type entite...");

        if(!types.contains(type)){
            throw new Exception("Merci de rentrer un type valide.");
        }

        if ("Nouveau type entite...".equals(type) && (newType == null || newType.trim().isEmpty() || newType.equalsIgnoreCase("Nouveau type entite..."))){
            throw new Exception("Merci de rentrer un nouveau type valide");
        }
    }

    private void addressVerification(String address) throws Exception{
        if(address == null || address.trim().isEmpty()){
            throw new Exception("Merci de rentrer une adresse.");
        } else if (address.length() > 80) {
            throw new Exception("L'adresse contient plus de 80 caracteres.");
        }
    }

    private void internVerification(String intern_nb) throws Exception{
        if(intern_nb == null || intern_nb.trim().isEmpty()){
            throw new Exception("Merci de rentrer un nombre de stagiaires.");
        }
        try {
            int nb = Integer.parseInt(intern_nb);
            if (nb < 0) {
                throw new Exception("Merci de rentrer un nombre positif ou nul de stagiaires.");
            }
        } catch (NumberFormatException e){
            throw new Exception("Merci de rentrer un nombre valide de stagiaires.");
        }
    }

    private void descriptionVerification(String description) throws Exception {
        if (description != null && description.length() > 1000){
            throw new Exception("La description est trop longue (1 000 caracteres max).");
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

    /**
     * Returns the result of the entity update
     * @return true if the entity has correctly been updated, false if the entity has been refused, i.e. bad parameters have been entered by the user
     */
    public boolean getResult() {
        return this.result;
    }

    /**
     * Returns the error map of the form
     * @return the error map of the form
     */
    public HashMap<String, String> getErrors(){
        return this.errors;
    }
}
