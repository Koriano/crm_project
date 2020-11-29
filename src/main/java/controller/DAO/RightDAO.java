package controller.DAO;
import model.*;
import java.sql.*;
import java.util.ArrayList;

public class RightDAO {
     /**
     * Instance of the object
     */
    public static RightDAO instance;
    private Connection db;

    private RightDAO() {
        this.db = DBConnection.getInstance();
    }

    /**
     * Get the instance of the DAO class
     *
     * @return unique instance of Right
     */
    public static RightDAO getInstance() {
        if (instance == null) {
            instance = new RightDAO();
        }
        return instance;
    }
    /**
     * Get the list of rights 
     * @return list of rights 
     */
    public ArrayList<String> getAllRight(){
        ArrayList<String> ret = new ArrayList<>();
        String role;
        String req_select_right = "SELECT * FROM `Right` ORDER BY name ASC";
        try {
            PreparedStatement  req_select_right_prep = this.db.prepareStatement( req_select_right);
            ResultSet res = req_select_right_prep.executeQuery();
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
     * Get right by Name
     * @param name name of the right
     * @return id 
     */
    public int  getRightByName(String  name){
        int ret =-1;
        String req_select_right = "SELECT * FROM `Right` WHERE name=? ORDER BY name ASC";
        try {
            PreparedStatement  req_select_right_prep = this.db.prepareStatement( req_select_right);
            req_select_right_prep.setString(1,name);
            ResultSet res = req_select_right_prep.executeQuery();
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
       String req_select ="SELECT * FROM `Right` WHERE id=?";
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