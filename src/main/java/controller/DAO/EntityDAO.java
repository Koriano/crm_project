package controller.DAO;
import model.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.BoldAction;

/**
 * Class that handle the communication between Entity & the database
 * @author Gurvan.R
 */
public class EntityDAO {

    /**
     * Instance of the object
     */
    public static EntityDAO instance;
    /**
     * Database connection
     */
    private Connection db;

    private EntityDAO(){
        this.db = DBConnection.getInstance();
    }

    /**
     * Get the instance of the DAO class
     * @return unique instance of EntityDAO
     */
    public static EntityDAO getInstance() {
        if (instance == null ){
            instance = new EntityDAO();
        }
        return instance;
    }
    /**
     * Save an entity in the database 
     * @param ent != null &&
     * !ent.getName().isEmpty() && ent.getName() != null && ent.getSiret().length() == 14 && ent.getType().isEmpty() && ent.getType() != null
     * @return true if resquest sucess 
     */
    public boolean saveEntity(Entity ent){

        assert (ent != null);
        assert !ent.getName().isEmpty() && ent.getName() != null && ent.getSiret().length() == 14 && ent.getType().isEmpty() && ent.getType() != null : "Pre condition violated";
        boolean ret=true;
        if (ent!=null){ 
            // Retrieve data from entity
            String name = ent.getName();
            String address = ent.getAddress();
            String siret = ent.getSiret();
            String description = ent.getDescription();
            int intern_nb = ent.getIntern_nb();
            int type = EntityTypeDAO.getInstance().getTypeByName( ent.getType());

            // Check if description is null 
            if(description == null){
                description="";
            }

            // Request to insert an Entity
            String req_insert ="INSERT INTO Entity(name,address,siret,description,intern_nb,type) VALUES (?,?,?,?,?,?)";

            try {
                if (type ==-1){
                    throw new Exception("Invalid value for Type: "+ent.getType());
                }
                // Forge prepared request
                PreparedStatement req_insertent_prep = db.prepareStatement(req_insert,Statement.RETURN_GENERATED_KEYS);
                req_insertent_prep.setString(1,name);
                req_insertent_prep.setString(2,address);
                req_insertent_prep.setString(3,siret);
                req_insertent_prep.setString(4,description);
                req_insertent_prep.setInt(5,intern_nb);
                req_insertent_prep.setInt(6,type);
                // Insert Entity 
                int insert = req_insertent_prep.executeUpdate();
                if (insert==0){
                    ret=false;
                }
               


            }
            catch (SQLException e) {
                System.out.println(e);
                ret=false;
            } 
            catch(Exception e){ 
                System.out.println(e);
                ret=false;
            }
        }
        else{
            System.out.println("ERROR EntityDAO: Impossible to save a null Entity object");
            ret=false;
        }
        return ret;
    }
    /**
    * Update the entity in the database
    * @param  ent entity object
    * @pre ent != null &&
    * !ent.getName().isEmpty() && ent.getName() != null && ent.getSiret().length() == 14 && ent.getType().isEmpty() && ent.getType() != null
    */
    public boolean updateEntity(Entity ent) {

        assert (ent != null);
        assert !ent.getName().isEmpty() && ent.getName() != null && ent.getSiret().length() == 14 && ent.getType().isEmpty() && ent.getType() != null : "Pre condition violated";

        boolean ret= true;
        if (ent!=null){

            // Retrieve data from entity
            
            String name = ent.getName();
            String address = ent.getAddress();
            String siret = ent.getSiret();
            String description = ent.getDescription();
            int intern_nb = ent.getIntern_nb();
            int type = EntityTypeDAO.getInstance().getTypeByName( ent.getType());
            
        
            // Request to update an entity 
            String req_update = "UPDATE Entity set name= ?,address= ? ,siret= ? ,description = ? ,intern_nb= ?,type=? where name = ? ";
            // Request to check if an given entity type already exist 
          

            try {
                  if (type ==-1){
                    throw new Exception("Invalid value for Type: "+ent.getType());
                }
                
                // Forge prepared request
                PreparedStatement req_update_prep = this.db.prepareStatement(req_update);
                req_update_prep.setString(1, name);
                req_update_prep.setString(2,address);
                req_update_prep.setString(3,siret);
                req_update_prep.setString(4,description);
                req_update_prep.setInt(5,intern_nb);
                req_update_prep.setInt(6,type);
                //WHERE parameter
                req_update_prep.setString(7, name);
                // Update Entity   
                int insert = req_update_prep.executeUpdate();
                if (insert==0){
                    ret=false;
                }

            }
            catch (SQLException e) {
                System.out.println(e);
                ret=false;
            }
            catch (Exception e) {
                e.printStackTrace();
                ret=false;
            }
        }
        else{
            System.out.println("ERROR EntityDAO : Impossible to update a null Entity object");
            ret=false;
        } 
        return ret;


    }
    /**
     * Delete the entity in the database
     * @param  ent entity object
     * @pre ent != null && 
     * !ent.getName().isEmpty() && ent.getName() != null && ent.getSiret().length() == 14 && ent.getType().isEmpty() && ent.getType() != null
     */
    public boolean deleteEntity(Entity ent){
        
        assert (ent!=null);
        assert !ent.getName().isEmpty() && ent.getName() != null && ent.getSiret().length() == 14 && ent.getType().isEmpty() && ent.getType() != null : "Pre condition violated";

        boolean ret = true;
        if (ent !=null && ent.getName()!=null){
            // Retrieve Primary Key
            int id = ent.getId();
            // Request to delete a given Entity
            String req_delete_entity = "DELETE FROM Entity where id=?";

            try {

                //Forge prepared statement
                PreparedStatement req_delete_entity_prep = this.db.prepareStatement(req_delete_entity );
                req_delete_entity_prep.setInt(1,id);
                // Delete entity
                int insert=req_delete_entity_prep.executeUpdate();
                if (insert==0){
                    ret=false;
                }

            }
            catch (SQLException e) {
                System.out.println(e);
                ret=false;
            }
            catch (Exception e) {
                e.printStackTrace();
                ret=false;
            }
            
        }
        else{
            ret=false;
            System.out.println("ERROR EntityDAO: Impossible to delete a null Entity object");
        }
        return ret;
    }
    /**
     * Get an entity by his contact
     * @param  name name of the entity
     * @pre contact != null &&
     * cont.getName().isEmpty() && !cont.getSurname().isEmpty() && !cont.getRole().isEmpty() && cont.getName()!=null && cont.getSurname() != null
     * cont.getReferent()!= null && cont.isReserved()|| (cont.getReferent()!= null && !cont.isReserved())
     */
    public Entity getEntityByContact(Contact cont) {
       
        assert (cont != null); 
        assert !cont.getName().isEmpty() && !cont.getSurname().isEmpty() && !cont.getRole().isEmpty() && cont.getName()!=null && cont.getSurname() != null: "Pre condition violated";
        assert  cont.getReferent()!= null && cont.isReserved()|| (cont.getReferent()!= null && !cont.isReserved()) : "Pre condition violated";
        int entity_id=-1;
        //Data
        Entity ret = null;
        if (cont != null){
            int id = cont.getId();
           
        
            // Request to select the entity of a contact 
            String req_select= "SELECT entity FROM Contact WHERE id = ?";
            try {
                
                //Forge prepared statement
                PreparedStatement req_select_prep = this.db.prepareStatement(req_select);
                req_select_prep.setInt(1,id);
                ResultSet res = req_select_prep.executeQuery();
                while (res.next()){
                    entity_id=res.getInt("entity");
                }
            } catch (SQLException e) {
                System.out.println(e);
            }catch (Exception e) {
                e.printStackTrace();
            }
            // If the contact got an entity 
            if (entity_id!=-1){
                ret = this.getEntityById(entity_id);
            }
        }
        else{
            System.out.println("ERROR EntityDAO : Impossible retrieve Entity from Contact, Contact is null");
        }
        assert(ret!=null);
        return ret;
    }
    /**
     * Get an entity by his name in the database
     * @param  name name of the entity
     * @pre name != null
     */
    public Entity getEntityById(int id){
        
        assert (id>0);

        // Attributes of the table Entity
        String address;
        String siret;
        String description;
        int intern_nb;
        String type;
        String name;
        //Request to select a given entity
        String req_select= "SELECT * FROM Entity WHERE id = ?";
        Entity ret = null;
        try {

            // Forge Entity
            PreparedStatement req_select_prep = this.db.prepareStatement(req_select);
            req_select_prep.setInt(1,id);
            ResultSet res = req_select_prep.executeQuery();
            // Retrieve results of the query 
            
            while (res.next()){
                
                name = res.getString("name");
                address = res.getString("address");
                siret = res.getString("siret");
                description = res.getString("description");
                intern_nb = res.getInt("intern_nb");
                type = EntityTypeDAO.getInstance().getNameByID(res.getInt("type"));
                
                // Build Entity object from result 
                Entity entity =  new Entity(name,siret,type,id);
                entity.setDescription(description);
                entity.setAddress(address);
                entity.setIntern_nb(intern_nb);
                ret = entity;

            }
        
        }catch (SQLException e) {
            System.out.println(e);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    /**
     * Get all entities in the database
     * @post ret != null
     */
    public ArrayList<Entity> getAllEntities(){
        
        // Attributes of the table Entity
        String name;
        String adress;
        String siret;
        String description;
        int intern_nb;
        String type;

        //Request to select all entities 
        String req_select_all= "SELECT * FROM Entity";
        ArrayList<Entity> ret = new ArrayList<Entity>();
        try {

            // Forge preapre statement 
            PreparedStatement req_select_all_prep = this.db.prepareStatement(req_select_all);
            ResultSet res = req_select_all_prep.executeQuery();
            // Get results of the query 
            while (res.next()){
                name = res.getString("name");
                adress = res.getString("address");
                siret = res.getString("siret");
                description = res.getString("description");
                intern_nb = res.getInt("intern_nb");
                type = EntityTypeDAO.getInstance().getNameByID(res.getInt("type"));

                // Build Entity Object 
                Entity entity =  new Entity(name,siret,type);
                entity.setDescription(description);
                entity.setAddress(adress);
                entity.setIntern_nb(intern_nb);

                ret.add(entity);

            }

        }catch (SQLException e) {
            System.out.println(e);
        }catch (Exception e) {
            e.printStackTrace();
        }
        assert (ret != null);
        return ret;
    }
    /**
     * Get the list of types in the database 
     * @return the list of types in the database 
     */
    public ArrayList<String> getAllTypes(){
        return EntityTypeDAO.getInstance().getAllTypes();
    }
}