package controller.DAO;
import model.*;
import java.sql.*;
import java.util.ArrayList;

public class RoleDAO {
     /**
     * Instance of the object
     */
    public static RoleDAO instance;
    private Connection db;

    private RoleDAO() {
        this.db = DBConnection.getInstance();
    }

    /**
     * Get the instance of the DAO class
     *
     * @return unique instance of RoleDAO
     */
    public static RoleDAO getInstance() {
        if (instance == null) {
            instance = new RoleDAO();
        }
        return instance;
    }
    /**
     * Get all users roles  
     * @return list of roles
     */
    public ArrayList<String> getAllRoles(){
        ArrayList<String> ret = new ArrayList<>();
        String role;
        String req_select_roles = "SELECT * FROM Contact_role ORDER BY name ASC";
        try {
            PreparedStatement  req_select_roles_prep = this.db.prepareStatement( req_select_roles);
            ResultSet res = req_select_roles_prep.executeQuery();
            while (res.next()){
                role = res.getString("name");
                ret.add(role);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }
    /**
     *  Get role by Name 
     * @param name name of the role 
     * @return  id 
     */
    public int getRoleByName(String name){
        int ret =-1; 
        String req_select_roles = "SELECT * FROM Contact_role WHERE name=?";
        try {
            PreparedStatement  req_select_roles_prep = this.db.prepareStatement( req_select_roles);
            req_select_roles_prep.setString(1,name);
            ResultSet res = req_select_roles_prep.executeQuery();
            while (res.next()){
                ret = res.getInt("id");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }
    /**
     * Get right by id 
     * @param id id of the right
     * @return type string 
     */
    public String getNameByID(int id){
        // Request to check if an given entity  type already exist
       String req_select ="SELECT * FROM Contact_role WHERE id=?";
       String ret="";
        try {

            // Forge preapre statement 
            PreparedStatement req_select_all_prep = this.db.prepareStatement(req_select);
            req_select_all_prep.setInt(1, id);
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