package controller.DAO;
import java.sql.*;
/**
 * ContactDAO : Class that handle the model Contact with the database
 * @author Gurvan.R
 */
public class DBConnection  {

    /**
     * Instance of the object
     */
    private static Connection instance;
    private DBConnection() {}
    /**
     * Get the instance of the DBConnection
     * @return unique instance of ContactDAO
     */
    public static Connection getInstance() {
        if ( !isConnected() ){
            connect();
        }
        return instance;
    }

    /**
     * Connect to the database
     */
    public static void connect(){
        try{
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            Connection con= DriverManager.getConnection(
                    //"jdbc:mysql://localhost:63306/crm_bdd","crm_app","m0tD3P&s$e*"); // Dev addr
                    "jdbc:mysql://database:53306/crm_bdd","crm_app","m0tD3P&s$e*"); // Prod addr
            System.out.println("Connected to the database....");
            instance= con;

        }catch(Exception e){ System.out.println(e);}

    }
    /**
     * Disconnect to the database
     */
    public static void disconnect()  {
        try{
            instance.close();
        }catch(Exception e){ System.out.println(e);}
    }
    /**
     * Check if the database is connected
     * @return true if the database is connected
     */
    public static boolean isConnected(){
        boolean ret = false;
        if (instance != null){
            ret=true;
        }
        return ret;
    }
}