package controller.DAO;
import model.*;
import java.sql.*;
import java.util.ArrayList;

public class EventTypeDAO {
     /**
     * Instance of the object
     */
    public static EventTypeDAO instance;
    private Connection db;

    private EventTypeDAO() {
        this.db = DBConnection.getInstance();
    }

    /**
     * Get the instance of the DAO class
     *
     * @return unique instance of EventTypeDAO
     */
    public static EventTypeDAO getInstance() {
        if (instance == null) {
            instance = new EventTypeDAO();
        }
        return instance;
    }
    /**
     * FGet all types 
     */
    public ArrayList<String> getAllTypes(){
        // Request to check if an given entity  type already exist
        String req_select ="SELECT * FROM Event_type";
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
    /**
     * Get if of a type by name 
     * @param type  type string 
     * @return id  ( default -1)
     */
    public int getTypeByName(String type){
        // Request to check if an given entity  type already exist
        String req_select ="SELECT * FROM Event_type WHERE name=?";
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
    /**
     * Get type by id 
     * @param id id of the Event
     * @return type string 
     */
    public String getNameByID(int id){
        // Request to check if an given entity  type already exist
       String req_select ="SELECT * FROM Event_type WHERE id=?";
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