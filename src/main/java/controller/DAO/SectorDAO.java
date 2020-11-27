package controller.DAO;
import model.*;
import java.sql.*;
import java.util.ArrayList;

import com.mysql.cj.result.StringValueFactory;
public class SectorDAO {
     /**
     * Instance of the object
     */
    public static SectorDAO instance;
    private Connection db;

    private SectorDAO() {
        this.db = DBConnection.getInstance();
    }

    /**
     * Get the instance of the DAO class
     *
     * @return unique instance of EntityDAO
     */
    public static SectorDAO getInstance() {
        if (instance == null) {
            instance = new SectorDAO();
        }
        return instance;
    }
    /**
     * Save sector in the database 
     * @pre sec!=null && sec.getName()!=null && !sec.getName().isEmpty() :"Pre condition violated"
     * @param sec sector object 
     * @return true if request success 
     */
    public boolean saveSector(Sector sec){
        assert sec!=null && sec.getName()!=null && !sec.getName().isEmpty() :"Pre condition violated";
        boolean ret = true;
        String name = sec.getName();
        int ret_req;
        String req_insert = "INSERT INTO Sector(name) VALUES(?)";
        ArrayList<Contact> contact_list = sec.getContactList();
        try {
            PreparedStatement req_insert_prep = this.db.prepareStatement(req_insert);
            req_insert_prep.setString(1,name);
            ret_req= req_insert_prep.executeUpdate();
            if (ret_req>0){
                    ret =this.saveContactList(contact_list, name);
                    
            }
            

        } catch (Exception e) {
            e.printStackTrace();
            ret=false;
        }
        return ret;
    }
    /**
     * Update a sector in the database 
     * @param sec sector 
     * @return true if the request is a sucess
     * @pre sec!=null && sec.getName()!=null && !sec.getName().isEmpty() 
     */
    public boolean updateSector(Sector sec){
        assert sec!=null && sec.getName()!=null && !sec.getName().isEmpty() :"Pre condition violated";
        boolean ret = true;
        String name = sec.getName();
        int ret_req;
        String req_update = "UPDATE Sector SET name=? WHERE name=?";
        ArrayList<Contact> contact_list = sec.getContactList();
        try {
            PreparedStatement req_update_prep = this.db.prepareStatement(req_update);
            req_update_prep.setString(1,name);
            //WHERE
            req_update_prep.setString(2,name);
            ret_req= req_update_prep.executeUpdate();
            if (ret_req>0){
                    
                    ret=this.deleteContactList(name) && this.saveContactList(contact_list,name);
                    
                    
            }
           

        } catch (Exception e) {
            ret=false;
            e.printStackTrace();
        }
        return ret;
    }
    /**
     * Save the contacts  list  of an sector 
     * @param list list of Contact 
     * @param sectorId id of the sector 
     * @return true if the request is a sucess 
     */
    public boolean saveContactList(ArrayList<Contact> list,String sectorId){
        String req_insert_cont = "INSERT INTO Contact_Sector_Asso(sectorName,contactId) VALUES (?,?)";
        boolean ret = true;
        try {
            int ret_req;
            for(Contact cont:list){
                    if (cont!=null){
                    PreparedStatement req_insert_prep = this.db.prepareStatement(req_insert_cont);
                    req_insert_prep.setString(1, sectorId);
                    req_insert_prep.setInt(2, cont.getId());
                    
                    ret_req=req_insert_prep.executeUpdate();
                    if (ret_req==0){
                        ret=false;
                    }
                }

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    /**
     * Delete the contact list of an sector 
     * @param sectorId id du sector 
     * @return true if the request is a sucess 
     */
    public boolean deleteContactList(String sectorId){
        String req_delete_sector = "DELETE FROM Contact_Sector_Asso WHERE sectorName=?";
        boolean ret = false;
        try {
            int ret_req;
            
            PreparedStatement req_delete_prep = this.db.prepareStatement(req_delete_sector);
            req_delete_prep.setString(1, sectorId);
            ret_req=req_delete_prep.executeUpdate();
            if (ret_req>0){
                ret=true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    /**
     * Delete a sector from the database 
     * @param sec sector 
     * @return true if the request is a sucess 
     */
    public boolean deleteSector(Sector sec){
        assert sec!=null && sec.getName()!=null && !sec.getName().isEmpty() :"Pre condition violated";
        boolean ret = true;
        String name = sec.getName();
        int ret_req;
        String req_delete = "DELETE FROM Sector WHERE name=?";
        try {
            PreparedStatement req_delete_prep = this.db.prepareStatement(req_delete );
            req_delete_prep.setString(1,name);
            ret_req= req_delete_prep.executeUpdate();
            if (ret_req==0){
                ret=false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    /**
     * Get sectors from an Account 
     * @pre !acc.getUsername().isEmpty() && !acc.getPassword().isEmpty() && !acc.getName().isEmpty() && !acc.getRight().isEmpty()
     * && acc.getUsername() != null && acc.getPassword()!= null &&  acc.getName() != null && acc.getRight()!= null: "Pre condition violated"
     * && acc.getSectors()!=null : "Pre condition violated"
     * && acc.getSectors().size()>0 && !acc.getContact().isLinkAccount(): "Pre condition violated"
     * @return list of sectors
     */
    public ArrayList<Sector> getSectorsByAccount(Account acc){
        assert acc != null : "Pre condition violated";
        assert !acc.getUsername().isEmpty() && !acc.getPassword().isEmpty() && !acc.getName().isEmpty() && !acc.getRight().isEmpty(): "Pre condition violated";
        assert acc.getUsername() != null && acc.getPassword()!= null &&  acc.getName() != null && acc.getRight()!= null: "Pre condition violated";
        assert acc.getSectors()!=null : "Pre condition violated";
        assert acc.getSectors().size()>0 && !acc.getContact().isLinkAccount(): "Pre condition violated";
        ArrayList<Sector> ret = new ArrayList<>();
        String name;
        String username = acc.getUsername();
        Sector sec;
        String req_select = "SELECT sectorName FROM Account_Sector_Asso WHERE accountUsername=? GROUP BY sectorName";
        
        try {
            PreparedStatement req_select_prep = this.db.prepareStatement(req_select );
            req_select_prep.setString(1, username);
            req_select_prep.executeQuery();
            ResultSet res = req_select_prep.getResultSet();
            while (res.next()){

                name = res.getString("sectorName");
                sec = new Sector(name);
                this.getContactList(sec);
                ret.add(sec);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    /**
     * Get all Sectors
     */
    public ArrayList<Sector> getAllSectors(){
        ArrayList<Sector> ret = new ArrayList<>();
        String name;
        String req_select = "SELECT * FROM Sector";
        Sector sec;
        try {
            PreparedStatement req_select_prep = this.db.prepareStatement(req_select );
            req_select_prep.executeQuery();
            ResultSet res = req_select_prep.getResultSet();
            while (res.next()){
                name = res.getString("name");
                sec = new Sector(name);
                this.getContactList(sec);
                ret.add(sec);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    /**
     * Get all the contact for one sector 
     * @param sec sector 
     * @pre sec!=null && sec.getName()!=null && !sec.getName().isEmpty()
     */
    public void  getContactList(Sector sec){
        assert sec!=null && sec.getName()!=null && !sec.getName().isEmpty() :"Pre condition violated";
        String req_selec_sector = "SELECT * FROM Contact_Sector_Asso WHERE sectorName=? ";
        int contactId;
        String sectorId = sec.getName()  ;
        try {

            
            PreparedStatement req_selec_prep = this.db.prepareStatement(req_selec_sector);
            req_selec_prep.setString(1, sectorId);
            req_selec_prep.executeQuery();
            ResultSet res = req_selec_prep.getResultSet();
            while (res.next()){
                contactId = res.getInt("contactId");
                sec.addContact(ContactDAO.getInstance().getContactById(contactId));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
       
    }
    /**
     * Check if a sector exists
     * @param sec sector 
     * @pre name!=null
     * @return ture if the sector exists
     */
    public boolean isSectorExist(String name){
        assert name!=null;
        boolean ret = true;
        String req_selec_sector = "SELECT COUNT(*) FROM Sector WHERE name=? ";
        String sectorId = name;
        try {

            
            PreparedStatement req_selec_prep = this.db.prepareStatement(req_selec_sector);
            req_selec_prep.setString(1, sectorId);
            req_selec_prep.executeQuery();
        
            ResultSet rs = req_selec_prep.executeQuery();
            rs.next();
            int nb_row = rs.getInt("count(*)");
            if (nb_row==1){
                ret=true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
       
    }
}
