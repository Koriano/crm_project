package controller.DAO;
import java.util.ArrayList;
import model.*;
import java.sql.*;
import controller.DAO.DBConnection;
/**
 * ContactDAO : Class that handle the model Contact with the database
 * @author Gurvan.R
 */
public class ContactDAO {

    /**
     * Instance of the object
     */
    public static ContactDAO instance;
    private Connection db;
    private ContactDAO(){ this.db = DBConnection.getInstance();}
    /**
     * Get the instance of the DAO class
     * @return unique instance of ContactDAO
     */
    public static ContactDAO getInstance() {
        if (instance == null ){
            instance = new ContactDAO();
        }
        return instance;
    }

    /**
     * Save the contact
     * @param  cont contact object
     * @pre cont != null
     * @return true if request is a success
     */
    public boolean saveContact(Contact cont){

        assert (cont != null);
        // Get Data from cont
        boolean ret = true; 
        int contact_id = -1;
        String name = cont.getName();
        String surname = cont.getSurname();
        String address = cont.getAddress();
        ArrayList<String> phone = cont.getPhonesList();
        ArrayList<String> mail = cont.getMailsList();
        boolean reserved = cont.isReserved();
        String role = cont.getRole();
        Entity entity = cont.getEntity();
        String entityname = null;
        int referent=-1;
        if (reserved){
            referent = cont.getReferent().getId();
        }
        if (entity!=null){
            entityname=entity.getName();
        }
       
    
        // Requête
        String req_add_reserved ="INSERT INTO Contact(name,surname,address,role,referent,entity) VALUES (?,?,?,?,?,?)";
        String req_add ="INSERT INTO Contact(name,surname,address,role,entity) VALUES (?,?,?,?,?)";
       
        try {
            
            PreparedStatement req_add_prep =null;
            if (reserved){
                req_add_prep = this.db.prepareStatement(req_add_reserved,Statement.RETURN_GENERATED_KEYS);
                req_add_prep.setString(1,name);
                req_add_prep.setString(2,surname);
                req_add_prep.setString(3,address);
                req_add_prep.setString(4,role);
                req_add_prep.setInt(5,referent);
                req_add_prep.setString(6,entityname);
            }
            else{
                req_add_prep = this.db.prepareStatement(req_add,Statement.RETURN_GENERATED_KEYS);
                req_add_prep.setString(1,name);
                req_add_prep.setString(2,surname);
                req_add_prep.setString(3,address);
                req_add_prep.setString(4,role);
                req_add_prep.setString(5,entityname);
            }
            int insert= req_add_prep.executeUpdate();
            ResultSet rs = req_add_prep.getGeneratedKeys();
            if (rs != null && rs.next()) {
                contact_id = rs.getInt(1);
            }

            if (insert>0 && contact_id !=-1){
                        
                ret =this.saveMail(mail, contact_id) && this.savePhone(phone, contact_id);             
            }
            else{
                ret=false;
            }

        } catch(Exception e){ 
            e.printStackTrace();
            ret=false;
        }
        return ret;
    }
    /**
     * Save mails from a contact 
     * @param list list of mails 
     * @param contactId id of contact 
     * @return true if request is a success
    */
    public boolean saveMail(ArrayList<String> list,int contactId){
        String req_insert_mail = "INSERT INTO Mail(contactId,address) VALUES (?,?)";
        boolean ret = true;
        try {
            int ret_req;
            for(String mail:list){
                PreparedStatement req_insert_prep = this.db.prepareStatement(req_insert_mail);
                req_insert_prep.setInt(1, contactId);
                req_insert_prep.setString(2, mail);
                ret_req=req_insert_prep.executeUpdate();
                if (ret_req==0){
                    ret=false;
                }

            }
        }
        catch (Exception e) {
            ret=false;
            System.out.println(e);
        }
        return ret;
    }
    /**
     * Save phone number list from a contact 
     * @param list list of phones 
     * @param contactId id of contact
     * @return true if request is a success
     */
    public boolean savePhone(ArrayList<String> list,int contactId){
        String req_insert_phone = "INSERT INTO Phone(contactId,phoneNb) VALUES (?,?)";
        boolean ret = true;
        try {
            int ret_req;
            for(String phone:list){
                PreparedStatement req_insert_prep = this.db.prepareStatement(req_insert_phone);
                req_insert_prep.setInt(1, contactId);
                req_insert_prep.setString(2, phone);
                ret_req=req_insert_prep.executeUpdate();
                if (ret_req==0){
                    ret=false;
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
     * Update the contact in the database
     * @param  cont contact object
     * @pre cont != null
     * @return true if the request is a sucess
     */
    public boolean updateContact(Contact cont){


        assert (cont != null);
        // Get Data from cont
        boolean ret = true;
        int id = cont.getId() ;
        String name = cont.getName();
        String surname = cont.getSurname();
        String address = cont.getAddress();
        ArrayList<String> phone = cont.getPhonesList();
        ArrayList<String> mail = cont.getMailsList();
        boolean reserved= cont.isReserved();
        String role = cont.getRole();
        Entity entity = cont.getEntity();
        String entityname = null;
        int referent=-1;
        if (reserved){
            referent = cont.getReferent().getId();
        }
        if (entity!=null){
            entityname=entity.getName();
        }
        // Requête
        String req_update = "UPDATE Contact set name= ?,surname= ?,address= ?,role= ?,entity=? where id=?";
        String req_update_ref = "UPDATE Contact set name= ?,surname= ?,address= ?,role= ?, referent=?,entity=? where id=?";
        
        try {
            // --Update associated mails and phones
            this.deleteMail(id);
            this.deletePhone(id);
            this.saveMail(mail,id);
            this.savePhone(phone,id);


            PreparedStatement req_update_prep =null;
            if (reserved){
                req_update_prep = this.db.prepareStatement(req_update_ref);
                req_update_prep.setString(1,name);
                req_update_prep.setString(2,surname);
                req_update_prep.setString(3,address);
                req_update_prep.setString(4,role);
                req_update_prep.setInt(5,referent);
                req_update_prep.setString(6,entityname);
                 //WHERE
                 req_update_prep.setInt(7, id);
            }
            else{
                req_update_prep = this.db.prepareStatement(req_update );
                req_update_prep.setString(1,name);
                req_update_prep.setString(2,surname);
                req_update_prep.setString(3,address);
                req_update_prep.setString(4,role);
                req_update_prep.setString(5,entityname);
                //WHERE
                req_update_prep.setInt(6, id);
            }
    
            int i = req_update_prep.executeUpdate();
            if (i==0){
                ret =false;
            
            }
            

        } catch (Exception e) {
            System.out.println(e);
            ret=false;
        }
        return ret;
    }
    /**
     * Delete the contact in the database
     * @param  cont contact object
     * @pre cont != null
     */
    public boolean deleteContact(Contact cont){

        assert (cont != null);
        // Get Data from cont
        boolean ret = true;
        int id = cont.getId();

        // Request
        try {
            int ret_req;
            // -- Delete contact
            String req_delete_contact = "DELETE FROM Contact where id=?";
            PreparedStatement req_delete_contact_prep = this.db.prepareStatement(req_delete_contact);
            req_delete_contact_prep.setInt(1, id);
            ret_req=req_delete_contact_prep.executeUpdate();
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
     * Delete all the mails linked to a contact
     * @param contactId id of the contact 
     * @return true if the request is a success
     */
    private boolean deleteMail(int contactId){

        String req_delete_mail = "DELETE FROM Mail WHERE contactId=?";
        boolean ret = false;
        try {
            int ret_req;
            
            PreparedStatement req_delete_prep = this.db.prepareStatement(req_delete_mail);
            req_delete_prep.setInt(1, contactId);
            ret_req=req_delete_prep.executeUpdate();
            if (ret_req>0){
                ret=true;
            }
        }
        catch (Exception e) {
            System.out.println(e);
            ret=false;
        }
        return ret;
    }
   /**
   * Delete all the mails linked to a contact
   * @param contactId id of the contact 
   * @return true if the request is a success
   */
    private boolean deletePhone(int contactId){
        String req_delete_mail = "DELETE FROM Phone WHERE contactId=?";
        boolean ret = true;
        try {
            int ret_req;
            
            PreparedStatement req_delete_prep = this.db.prepareStatement(req_delete_mail);
            req_delete_prep.setInt(1, contactId);
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
     * Get the contact associated with an account
     * @param  acc account object
     * @pre acc != null
     */
    public Contact getContactByAccount(Account acc){
        assert (acc != null); 
        return AccountDAO.getInstance().getAccountByName(acc.getUsername()).getContact();
    }


    /**
     * Get the contact associated with an account
     * @param  sec sector object
     * @pre sec != null
     * @post ret != null
     */
    public ArrayList<Contact> getContactBySector(Sector sec){
        assert (sec != null);
        
        //Data
        int id;
        String nameSec = sec.getName();
        String name;
        String surname;
        String address;
        boolean reserved;
        String role;
        int refId;
        String entity;
        //Request
        String req_select = "SELECT cont.id,cont.name,cont.surname,cont.address,cont.reserved,cont.referent,cont.role,cont.entity FROM Contact_Sector_Asso AS sec_con,Contact AS cont WHERE sec_con.contactId=cont.id AND sectorName=? ORDER BY cont.name,cont.surname ASC";
        ArrayList<Contact> ret = new ArrayList<>();
        try {

            PreparedStatement req_select_prep = this.db.prepareStatement(req_select);
            req_select_prep.setString(1,nameSec );
            ResultSet res = req_select_prep.executeQuery();
            while (res.next()) {
                id = res.getInt("cont.id");
                name = res.getString("cont.name");
                surname = res.getString("cont.surname");
                address = res.getString("cont.address");
                reserved = res.getBoolean("cont.reserved");
                role = res.getString("cont.role");
                refId = res.getInt("cont.referent");
                entity = res.getString("cont.entity");
               
                Contact ref = null;
                if (reserved){
                    ref = this.getContactById(refId);
                }
                Contact ctc = new Contact(name, surname,role,ref,reserved,id);
                this.getMailByContact(ctc);
                this.getPhoneByContact(ctc);
                ctc.setEntity(EntityDAO.getInstance().getEntityByName(entity));
                ctc.setAddress(address);
                ret.add(ctc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        assert (ret != null);

        return ret;
    }

    /**
     * Get the contact list associated with an event
     * @param  event event object
     * @pre event != null
     * @post ret != null
     */
    public ArrayList<Contact> getContactByEvent(Event event) {
        assert (event != null);
        //Data
        int id;
        String name;
        String surname;
        String address;
        boolean reserved;
        String role;
        int refId;
        String entity;
        //Request
        String req_select = "SELECT cont.id,cont.name,cont.surname,cont.address,cont.reserved,cont.referent,cont.role,cont.entity FROM Contact_Event_Asso AS evt_con,Contact AS cont WHERE evt_con.contactId=cont.id AND eventId=? ORDER BY cont.name,cont.surname ASC";
        ArrayList<Contact> ret = new ArrayList<>();
        try {

            PreparedStatement req_select_prep = this.db.prepareStatement(req_select);
            req_select_prep.setInt(1,1);
            ResultSet res = req_select_prep.executeQuery();

            while (res.next()) {
                id = res.getInt("cont.id");
                name = res.getString("cont.name");
                surname = res.getString("cont.surname");
                address = res.getString("cont.address");
                reserved = res.getBoolean("cont.reserved");
                role = res.getString("cont.role");
                refId = res.getInt("cont.referent");
                entity = res.getString("cont.entity");
    
               
                Contact ref = null;

                if (reserved){
                    ref = this.getContactById(refId);
                }
                Contact ctc = new Contact(name, surname,role,ref,reserved,id);
                this.getMailByContact(ctc);
                this.getPhoneByContact(ctc);
                ctc.setAddress(address);
                ctc.setEntity(EntityDAO.getInstance().getEntityByName(entity));
                ret.add(ctc);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        assert (ret != null);
        return ret;
    }
    
    /**
     * Get the author of a comment
     * @param  cmt comment object
     * @pre cmt != null && mt.getAuthor()!=null
     * @post ret!=null
     */
    public Contact getAuthorByComment(Comment cmt){
        
        assert (cmt != null && cmt.getAuthor()!=null);
        //Data
        Contact ret =null;
        String req_select_contact = "SELECT * FROM Contact WHERE id=? ";
        int id = cmt.getAuthor().getId();
        String name;
        String surname;
        String address;
        boolean reserved;
        String role;
        String entity;
        int refId;
        //Request
        try {

            PreparedStatement  req_select_contact_prep = this.db.prepareStatement(req_select_contact);
            req_select_contact_prep.setInt(1,id);
            ResultSet res = req_select_contact_prep.executeQuery();

            while (res.next()){
                name = res.getString("name");
                surname = res.getString("surname");
                address = res.getString("address");
                reserved = res.getBoolean("reserved");
                role = res.getString("role");
                refId = res.getInt("referent");
                entity= res.getString("entity");
                Contact ref = null;
                if (reserved){
                    ref =this.getContactById(refId);
                }
                Contact contact=  new Contact(name,surname,role,ref,reserved,id);
                contact.setEntity(EntityDAO.getInstance().getEntityByName(entity));
                contact.setAddress(address);
                this.getMailByContact(contact);
                this.getPhoneByContact(contact);
                ret =contact;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        assert (ret != null);
        return ret;
    }
    /**
     * Get the contact associated by comment
     * @param  cmt comment object
     * @pre cmt != null && cmt.getConcernedContact()!=null
     * @post ret != null
     */
    public Contact getContactByComment(Comment cmt){
        assert (cmt != null && cmt.getConcernedContact()!=null);
        //Data
        Contact ret =null;
        String req_select_contact = "SELECT * FROM Contact WHERE id=? ";
        int id = cmt.getConcernedContact().getId();
        String name;
        String surname;
        String address;
        boolean reserved;
        String role;
        int refId;
        String entity;
        //Request
        try {

            PreparedStatement  req_select_contact_prep = this.db.prepareStatement(req_select_contact);
            req_select_contact_prep.setInt(1,id);
            ResultSet res = req_select_contact_prep.executeQuery();

            while (res.next()){
                name = res.getString("name");
                surname = res.getString("surname");
                address = res.getString("address");
                reserved = res.getBoolean("reserved");
                role = res.getString("role");
                refId = res.getInt("referent");
                entity = res.getString("entity");
                Contact ref = null;
                if (reserved){
                    ref =this.getContactById(refId);
                }
                Contact contact=  new Contact(name,surname,role,ref,reserved,id);
                contact.setEntity(EntityDAO.getInstance().getEntityByName(entity));
                contact.setAddress(address);
                this.getMailByContact(contact);
                this.getPhoneByContact(contact);
                ret =contact;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        assert (ret != null);
        return ret;
    }

    /**
     * Get all contact
     * @post ret != null
     */
    public ArrayList<Contact> getAllContacts(){
        //Data
        int id;
        String name;
        String surname;
        String address;
        boolean reserved;
        String role;
        int refId;
        String entity;
        Contact ref;
        //Request
        String req_select_all= "SELECT * FROM Contact ORDER BY name,surname ASC";
        ArrayList<Contact> ret = new ArrayList<>();
        try {

            PreparedStatement  req_select_contact_prep = this.db.prepareStatement(req_select_all);
            ResultSet res = req_select_contact_prep.executeQuery();
            while (res.next()){
                id = res.getInt("id");
                name = res.getString("name");
                surname = res.getString("surname");
                address = res.getString("address");
                reserved = res.getBoolean("reserved");
                role = res.getString("role");
                refId = res.getInt("referent");
                entity = res.getString("entity");
                ref = null;
                if (reserved){
                    ref =this.getContactById(refId);
                }

                Contact contact=  new Contact(name,surname,role,ref,reserved,id);
                contact.setAddress(address);
                this.getMailByContact(contact);
                this.getPhoneByContact(contact);
                contact.setEntity(EntityDAO.getInstance().getEntityByName(entity));
                ret.add(contact);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        assert (ret != null);
        return ret;
    }

    /***
     * Get the phone number list by contact 
     * @param cont contact
     * @throws SQLException
     * @pre cont!=null 
     */
    private void getPhoneByContact(Contact cont ) throws SQLException{
        
        assert(cont!=null);
        int id = cont.getId();
        String phonenum;
        //Request
        String req_select_mail= "SELECT phoneNb FROM Phone WHERE contactId=?";
        PreparedStatement req_select_all_prep = this.db.prepareStatement(req_select_mail);
        req_select_all_prep.setInt(1,id);
        ResultSet res = req_select_all_prep.executeQuery();
        while (res.next()){
            phonenum = res.getString("phoneNb");
            cont.addPhone(phonenum);
        }
    }

    /***
     * Get the list of mails tby contact 
     * @param cont contact
     * @throws SQLException
     * @pre cont!=null 
     */
    private void getMailByContact(Contact cont ) throws SQLException{
        int id = cont.getId(); 
        String mail;
        //Request
        String req_select_mail= "SELECT address FROM Mail WHERE contactId=?";
        PreparedStatement req_select_all_prep = this.db.prepareStatement(req_select_mail);
        req_select_all_prep.setInt(1,id);
        ResultSet res = req_select_all_prep.executeQuery();
        while (res.next()){
            mail = res.getString("address");
            cont.addMail(mail);
        }

    }
    /**
     * Get the contact by his id
     * @param id id of the contact
     * @pre id>0
     * @post ret != null
     */
    public Contact getContactById(int id) {
        assert (id>0);
        Contact ret =null;
        String name;
        String surname;
        String address;
        boolean reserved;
        String entity;
        String role;
        Contact ref;
        int refId;
        String req_select_contact = "SELECT * FROM Contact WHERE id=?";
        try {
            PreparedStatement  req_select_contact_prep = this.db.prepareStatement(req_select_contact);
            req_select_contact_prep.setInt(1, id);
            ResultSet res = req_select_contact_prep.executeQuery();

            while (res.next()){
                name = res.getString("name");
                surname = res.getString("surname");
                address = res.getString("address");
                reserved = res.getBoolean("reserved");
                role = res.getString("role");
                refId = res.getInt("referent");
                entity = res.getString("entity");
                Contact referent = null;
                if (reserved){
                    referent=this.getContactById(refId);
                }
                Contact contact=  new Contact(name,surname,role,referent,reserved,id);
                contact.setAddress(address);
                contact.setEntity(EntityDAO.getInstance().getEntityByName(entity));
                this.getMailByContact(contact);
                this.getPhoneByContact(contact);
                ret=contact;
            }
        } catch (Exception e) {e.printStackTrace();}

        assert (ret != null);
        return ret;

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



}