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
     * @return unique instance of EntityDAO
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
     * Get the list of rights 
     * @return list of rights 
     */
    public int  getRightByName(String  name){
        int ret =-1;
        String req_select_right = "SELECT * FROM `Right` ORDER BY name ASC";
        try {
            PreparedStatement  req_select_right_prep = this.db.prepareStatement( req_select_right);
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
}