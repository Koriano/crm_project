package controller.DAO;
import model.*;
import java.sql.*;
import java.util.ArrayList;

public class EntityTypeDAO {
     /**
     * Instance of the object
     */
    public static EntityTypeDAO instance;
    private Connection db;

    private EntityTypeDAO() {
        this.db = DBConnection.getInstance();
    }

    /**
     * Get the instance of the DAO class
     *
     * @return unique instance of EntityTypeDAO
     */
    public static EntityTypeDAO getInstance() {
        if (instance == null) {
            instance = new EntityTypeDAO();
        }
        return instance;
    }
    /**
     * Save Entity_type in the database 
     * @pre type!=null
     * @param type type value 
     * @return true if request success 
     */
    public boolean saveEntityType(String type){
        assert type!=null :"Pre condition violated";
        
        boolean ret = true;
        String name = type;
        int ret_req;
        String req_insert = "INSERT INTO Entity_type(name) VALUES(?)";
        
        
        try {
            if ("Nouveau Type entite...".trim().toUpperCase().equals(name.trim().toUpperCase())){
                throw new SQLException("Error Trying to insert DUPLICATE KEY : Nouvelle Entité");
            }
            PreparedStatement req_insert_prep = this.db.prepareStatement(req_insert);
            req_insert_prep.setString(1,name);
    
            ret_req= req_insert_prep.executeUpdate();
            

        } catch (Exception e) {
            e.printStackTrace();
            ret=false;
        }
        return ret;
    }
    /**
     * Save Entity_type in the database 
     * @pre type!=null
     * @param type type value 
     * @return true if request success 
     */
    public boolean deleteEntityType(int id ){
        assert id>0 :"Pre condition violated";
        
        boolean ret = true;
        int ret_req;
        String req_delete = "DELETE FROM Entity_type WHERE id=?";
        
        try {
            PreparedStatement req_delete_prep = this.db.prepareStatement(req_delete);
            req_delete_prep.setInt(1,id);
            ret_req= req_delete_prep.executeUpdate();
            

        } catch (Exception e) {
            e.printStackTrace();
            ret=false;
        }
        return ret;
    }
    public ArrayList<String> getAllTypes(){
        // Request to check if an given entity  type already exist
        String req_select ="SELECT * FROM Entity_type";
        String name;
        ArrayList<String> ret = new ArrayList<String>();
        try {

            // Forge preapre statement 
            PreparedStatement req_select_all_prep = this.db.prepareStatement(req_select);
            ResultSet res = req_select_all_prep.executeQuery();
            // Get results of the query 
            while (res.next()){
                name = res.getString("name");
                ret.add(name);
            }

        }catch (SQLException e) {
            System.out.println(e);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public int getTypeByName(String type){
        // Request to check if an given entity  type already exist
        String req_select ="SELECT * FROM Entity_type WHERE name=?";
        int ret =-1;
        try {

            // Forge preapre statement 
            PreparedStatement req_select_all_prep = this.db.prepareStatement(req_select);
            req_select_all_prep.setString(1, type);
            ResultSet res = req_select_all_prep.executeQuery();
            // Get results of the query 
            while (res.next()){
                ret = res.getInt("id");
                
            }
           
        }catch (SQLException e) {
            System.out.println(e);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    public String getNameByID(int id){
        
       String req_select ="SELECT * FROM Entity_type WHERE id=?";
       String ret="";
        try {

            // Forge preapre statement 
            PreparedStatement req_select_all_prep = this.db.prepareStatement(req_select);
            req_select_all_prep.setInt(1,id);
            ResultSet res = req_select_all_prep.executeQuery();
            // Get results of the query 
            while (res.next()){
                ret = res.getString("name");
                
            }
            
        }catch (SQLException e) {
            System.out.println(e);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}