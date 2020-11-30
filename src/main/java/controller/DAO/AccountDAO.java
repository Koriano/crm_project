
package controller.DAO;
import model.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.BoldAction;

/**
 * AccountDAO : Class that handle the communication between Account & the database
 * @author Gurvan.R
 */
public class AccountDAO {

    /**
     * Instance of the object
     */
    public static AccountDAO instance;
    
    /**
     * Database connection
     */
    private Connection db;

    private AccountDAO() {
        this.db = DBConnection.getInstance();
    }

    /**
     * Get the instance of the DAO class
     * @return unique instance of CommentDAO
     */
    public static AccountDAO getInstance() {
        if (instance == null) {
            instance = new AccountDAO();
        }
        return instance;
    }

    /**
     * Save an account in the data database 
     * @param acc
     * @pre acc != null
     * @return true if the insertion is performed 
     */
    public boolean saveAccount(Account acc){
        assert(acc != null);
        boolean ret=true;
        String username = acc.getUsername();
        String name = acc.getName();
        String password = acc.getPassword();
        int right = RightDAO.getInstance().getRightByName(acc.getRight());
        int account_id=-1;
        Contact contact = acc.getContact();
    
        ArrayList<Sector> sectors = acc.getSectors();
        String req_insert = "INSERT INTO Account(`username`,`password`,`name`,`right`,`contactId`) VALUES (?,?,?,?,?)";
        try{
            PreparedStatement req_insert_prep = this.db.prepareStatement(req_insert,Statement.RETURN_GENERATED_KEYS);
            req_insert_prep.setString(1,username);
            req_insert_prep.setString(2,password);
            req_insert_prep.setString(3,name);
            req_insert_prep.setInt(4,right);
            req_insert_prep.setInt(5,contact.getId());
            int insert;
            insert =req_insert_prep.executeUpdate();
            ResultSet rs = req_insert_prep.getGeneratedKeys();
            if (rs != null && rs.next()) {
                account_id = rs.getInt(1);
            }
            if (insert>0 && account_id !=-1){
                ret = this.addSectors(sectors, account_id);
            }
            else{
                ret=false;
            }


        }catch(Exception e){
            e.printStackTrace();
            ret=false;
        }
        return ret;

    }
    /**
     * Update an account in the database 
     * @param acc account to update
     * @pre acc != null
     * @return true if update performed 
     */
    public boolean updateAccount(Account acc){
        assert(acc != null);
        boolean ret=true;
        String username = acc.getUsername();
        String name = acc.getName();
        String password = acc.getPassword();
        int right = RightDAO.getInstance().getRightByName(acc.getRight());
        int account_id = acc.getId();
        Contact contact = acc.getContact();
        ArrayList<Sector> sectors = acc.getSectors();
        String req_insert = "UPDATE Account SET `username`=?,`password`=?,`name`=?,`right`=?,`contactId`=? WHERE id=?";
        try{
        
            PreparedStatement req_insert_prep = this.db.prepareStatement(req_insert,Statement.RETURN_GENERATED_KEYS);
            req_insert_prep.setString(1,username);
            req_insert_prep.setString(2,password);
            req_insert_prep.setString(3,name);
            req_insert_prep.setInt(4,right);
            req_insert_prep.setInt(5,contact.getId());
            req_insert_prep.setInt(6,acc.getId());
            int insert;
            insert =req_insert_prep.executeUpdate();
            if (insert>0 && account_id !=-1){
                this.deleteSectors(account_id);
                if (sectors!=null && sectors.size()!=0) ret = this.addSectors(sectors, account_id);
                else ret=false;
            }
            else{
                ret=false;
            }


        }catch(Exception e){
            ret=false;
            e.printStackTrace();
        }
        return ret;

    }
    /**
     * Add the sectors linked with the account 
     * @param list lis of sectors 
     * @param username id of the account 
     * @pre accountId>0
     * @return true id insertion performed 
     */
    private boolean addSectors(ArrayList<Sector> list,int accountId){
        assert(accountId>0);
        String req_insert_sector= "INSERT INTO Account_Sector_Asso(sectorName,accountId) VALUES (?,?)";
        boolean ret = true;
        try {
            int ret_req;
            for(Sector sector:list){
               
                if (sector!=null && sector.getName()!=null){
                    PreparedStatement req_insert_prep = this.db.prepareStatement(req_insert_sector); 
                    req_insert_prep.setInt(1,sector.getId());
                    req_insert_prep.setInt(2, accountId);
                    ret_req=req_insert_prep.executeUpdate();
                    if (ret_req==0){
                        ret=false;
                }
                }
                

            }
        }
        catch (Exception e) {
            System.out.println(e);
            ret=false;
        }
        return ret;
    }
    /**
     * Delete sectors linked to an account 
     * @param username id of the account 
     * @pre username != null
     * @return true if deletion performed 
     */
    private boolean deleteSectors(int accountId){
        assert (accountId>0);
        String req_delete_sector= "DELETE FROM Account_Sector_Asso WHERE accountId=?";
        boolean ret = true;
        try {
            int ret_req;
           
          
           
            PreparedStatement req_delete_prep = this.db.prepareStatement(req_delete_sector); 
            req_delete_prep.setInt(1, accountId);
            ret_req=req_delete_prep.executeUpdate();
            if (ret_req==0){
                ret=false;
            }    
            
        }
        catch (Exception e) {
            System.out.println(e);
            ret=false;
        }
        return ret;
    }
    /**
     * Delete account in the database 
     * @param acc account 
     * @pre acc !=null 
     * @return true if deletion performed 
     */
    public boolean deleteAccount(Account acc){
        assert(acc != null);
        boolean ret=true;
        int id = acc.getId();
        String req_delete_account= "DELETE FROM Account WHERE id=?";
        try {
            int ret_req;
            PreparedStatement req_delete_prep = this.db.prepareStatement(req_delete_account); 
            req_delete_prep.setInt(1, id);
            ret_req=req_delete_prep.executeUpdate();
            if (ret_req==0){
                ret=false;
            }    

        } catch (Exception e) {
            e.printStackTrace();
            ret=false;
        }
        return ret;
    }
    /**
     * Check the tuple login/password to log into an account
     * @param login usersname
     * @param password password
     * @pre login != null && password != null
     * @return true if the tuple match in the database
     */
    public boolean checkLogin(String login,String password){
        assert(login != null && password != null);
        boolean ret =false;
        String req_login ="SELECT COUNT(*) FROM Account WHERE username=? AND password=?";
        try {
           
            PreparedStatement req_login_prep = this.db.prepareStatement(req_login); 
            req_login_prep.setString(1, login);
            req_login_prep.setString(2, password);
            ResultSet rs = req_login_prep.executeQuery();
            rs.next();
            int nb_row = rs.getInt("count(*)");
            if (nb_row==1){
                ret=true;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    /**
     * Get all the accounts 
     * @return all the accounts 
     * @post ret != null
     */
    public ArrayList<Account> getAllAccounts(){
        ArrayList<Account> ret =new ArrayList<>();
        String username;
        String name;
        String password;
        String right;
        Contact contact;
        int contact_id;
        Account acc;
        int id;
        ArrayList<Sector> sectors = new ArrayList<>();
        String req_select ="SELECT * FROM Account";
        try {
           
            PreparedStatement req_select_prep = this.db.prepareStatement(req_select); 
            ResultSet res = req_select_prep.executeQuery();
            while (res.next()){
                id = res.getInt("id");
                username = res.getString("username");
                name = res.getString("name");
                password = res.getString("password");
                right = RightDAO.getInstance().getNameByID(res.getInt("right"));
                contact_id = res.getInt("contactId");
                contact = ContactDAO.getInstance().getContactById(contact_id);
                acc = new Account(username, password, name, right, contact, sectors,id);
                sectors = SectorDAO.getInstance().getSectorsByAccount(acc);
                for (Sector sector : sectors){
                    acc.addSector(sector);
                }
                
                ret.add(acc);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return ret;
    }
    /**
     * Get account by name 
     * @param username name
     * @return account 
     */
    public Account getAccountByName(String username){
        Account ret = null;
        String name;
        String password;
        String right;
        Contact contact;
        int contact_id;
        int id;
        Account acc;
        ArrayList<Sector> sectors = new ArrayList<>();
        String req_select ="SELECT * FROM Account WHERE username=?";
        try {
           
            PreparedStatement req_select_prep = this.db.prepareStatement(req_select);
            req_select_prep.setString(1,username);
            ResultSet res = req_select_prep.executeQuery();
            while (res.next()){
                id = res.getInt("id");
                username = res.getString("username");
                name = res.getString("name");
                password = res.getString("password");
                right = RightDAO.getInstance().getNameByID(res.getInt("right"));
                contact_id = res.getInt("contactId");
                contact = ContactDAO.getInstance().getContactById(contact_id);
                
                acc = new Account(username, password, name, right, contact, sectors,id);
                sectors = SectorDAO.getInstance().getSectorsByAccount(acc);
                for (Sector sector : sectors){
                    acc.addSector(sector);
                }
                
                ret =acc;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        assert(ret==null);
        return ret;
    }
    /**
     * Get account by id
     * @param id id of account
     * @return account 
     */
    public Account getAccountById(int id){
        Account ret = null;
        String name;
        String password;
        String right;
        Contact contact;
        String username;
        int contact_id;
        Account acc;
        ArrayList<Sector> sectors = new ArrayList<>();
        String req_select ="SELECT * FROM Account WHERE id=?";
        try {
           
            PreparedStatement req_select_prep = this.db.prepareStatement(req_select);
            req_select_prep.setInt(1,id);
            ResultSet res = req_select_prep.executeQuery();
            while (res.next()){
                username = res.getString("username");
                name = res.getString("name");
                password = res.getString("password");
                right = RightDAO.getInstance().getNameByID(res.getInt("right"));
                contact_id = res.getInt("contactId");
                contact = ContactDAO.getInstance().getContactById(contact_id);
                
                acc = new Account(username, password, name, right, contact, sectors,id);
                sectors = SectorDAO.getInstance().getSectorsByAccount(acc);
                for (Sector sector : sectors){
                    acc.addSector(sector);
                }
                
                ret =acc;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        assert(ret==null);
        return ret;
    }
    /**
     * Get the list of rights 
     * @return list of rights 
     */
    public ArrayList<String> getAllRight(){
        return RightDAO.getInstance().getAllRight();
    }
    
    

}
      