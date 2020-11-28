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
     * @return unique instance of EntityDAO
     */
    public static EntityTypeDAO getInstance() {
        if (instance == null) {
            instance = new EntityTypeDAO();
        }
        return instance;
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
            System.out.println(ret);
        }catch (SQLException e) {
            System.out.println(e);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}