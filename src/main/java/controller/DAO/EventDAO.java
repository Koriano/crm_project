package controller.DAO;
import model.*;
import java.sql.*;
import java.util.ArrayList;
/**
 * EntityDAO : Class that handle the model Entity with the database
 * @author Gurvan.R
 */
public class EventDAO {

    /**
     * Instance of the object
     */
    public static EventDAO instance;
    private Connection db;

    private EventDAO() {
        this.db = DBConnection.getInstance();
    }

    /**
     * Get the instance of the DAO class
     *
     * @return unique instance of EntityDAO
     */
    public static EventDAO getInstance() {
        if (instance == null) {
            instance = new EventDAO();
        }
        return instance;
    }

    /**
     * Save an event in the database
     * @param evt event
     * @pre evt != null && evt.getContactsList()==null &&
     * evt.getName() != null && evt.getDate() != null &&  evt.getContactsList().get(0) != null  && evt.getType().isEmpty() && evt.getType()!= null
     * @return true if the request is a sucess
     */
    public boolean saveEvent(Event evt) {
        assert (evt != null && evt.getContactsList()==null);
        assert evt.getName() != null && evt.getDate() != null &&  evt.getContactsList().get(0) != null  && evt.getType().isEmpty() && evt.getType()!= null: " Pre condition violated";

        boolean ret =false;
        String name = evt.getName();
        String description = evt.getDescription();
        Date date = new Date(evt.getDate().getTime());
        int  type = EventTypeDAO.getInstance().getTypeByName(evt.getType());
        ArrayList<Contact> contacts = evt.getContactsList();
        int author_id = evt.getContactsList().get(0).getId();
        int eventid=-1;
        if (description == null) {
            description = "";
        }
       
        // Request to insert Event
        String req_add_event = "INSERT INTO Event(name,description,date,type,authorId) VALUES (?,?,?,?,?)";
        // Request to insert contact
        

        try {
            if (type == -1){
                throw new Exception("Type of Event "+type+" undefined");
            }
          
            // Add Event
            PreparedStatement req_addevt_prep = this.db.prepareStatement(req_add_event,Statement.RETURN_GENERATED_KEYS);
            req_addevt_prep.setString(1, name);
            req_addevt_prep.setString(2, description);
            req_addevt_prep.setDate(3, date);
            req_addevt_prep.setInt(4, type);
            req_addevt_prep.setInt(5,author_id);
            int i = req_addevt_prep.executeUpdate();
            if (i>0){
                ResultSet rs = req_addevt_prep.getGeneratedKeys();
                if (rs != null && rs.next()) {
                    eventid = rs.getInt(1);
                }
               if (eventid>=0){
                    ret =this.saveContactList(contacts, eventid);
                    
               }
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
        
    }
    /**
     * Save the list of contacts linked to an Event 
     * @param list list of contacts linked to an Event 
     * @param id id of the event 
     * @return true if the request is a sucess 
     */
    private boolean saveContactList(ArrayList<Contact> list,int id){
       
        String req_insert_contact ="INSERT INTO Contact_Event_Asso(eventId,contactId) VALUES (?,?)";
        boolean ret = true;
        try {
            int ret_req;
        
            for(Contact cont:list){
                System.out.println(cont.getName());
                if (cont!=null && cont.getId()>0){
                    PreparedStatement req_insert_prep = this.db.prepareStatement(req_insert_contact);
                    req_insert_prep.setInt(1, id);
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
     * Delete the list of contacts linked to an Event 
     * @param id id of the event 
     * @return true if the request is a sucess 
     */
    private boolean deleteContactList(int id){
        
        String req_delete_contact ="DELETE FROM Contact_Event_Asso WHERE eventId=?";
        boolean ret = false;
        try {
            int ret_req;
    
            PreparedStatement req_delete_prep = this.db.prepareStatement(req_delete_contact);
            req_delete_prep.setInt(1, id);
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
     * Update the list of contacts linked to an Event 
     * @param evt id events
     * @pre evt != null && evt.getContactsList()==null &&
     * evt.getName() != null && evt.getDate() != null &&  evt.getContactsList().get(0) != null  && evt.getType().isEmpty() && evt.getType()!= null
     * @return true if the request is a sucess
     */
    public boolean updateEvent(Event evt){

        assert (evt != null && evt.getContactsList()==null);
        assert evt.getName() != null && evt.getDate() != null &&  evt.getContactsList().get(0) != null  && evt.getType().isEmpty() && evt.getType()!= null: " Pre condition violated";

        boolean ret =false;
        String name = evt.getName();
        String description = evt.getDescription();
        Date date = new Date(evt.getDate().getTime());
        int  type = EventTypeDAO.getInstance().getTypeByName( evt.getType());
        ArrayList<Contact> contacts = evt.getContactsList();
        
        int author_id = evt.getContactsList().get(0).getId();
        int eventid=evt.getId();
     
        if (description == null) {
            description = "";
        }
       
        // Request to insert Event
        String req_add_event = "UPDATE Event SET name=?,description=?,date=?,type=?,authorId=? WHERE id=?";

        try {
            
            if (type == -1){
                throw new Exception("Type of Event "+type+" undefined");
            }
            this.deleteContactList(eventid);
            this.saveContactList(contacts, eventid);
            // Update Event
            PreparedStatement req_updtevt_prep = this.db.prepareStatement(req_add_event);
            req_updtevt_prep.setString(1, name);
            req_updtevt_prep.setString(2, description);
            req_updtevt_prep.setDate(3, date);
            req_updtevt_prep.setInt(4, type);
            req_updtevt_prep.setInt(5,author_id);
            //WHERE
            req_updtevt_prep.setInt(6,eventid);
            
            int ret_up = req_updtevt_prep.executeUpdate();
            if (ret_up>0){
                ret=true;
            }

            
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Delete an event in the database
     * @param evt event to delete
     * @pre evt != null && evt.getContactsList()==null &&
     * evt.getName() != null && evt.getDate() != null &&  evt.getContactsList().get(0) != null  && evt.getType().isEmpty() && evt.getType()!= null
     * @return true if request success 
     */
    public boolean deleteEvent(Event evt){
        assert (evt != null && evt.getContactsList()==null);
        assert evt.getName() != null && evt.getDate() != null &&  evt.getContactsList().get(0) != null  && evt.getType().isEmpty() && evt.getType()!= null: " Pre condition violated";

        boolean ret = true;
        int name = evt.getId();
        String req_delete = "DELETE FROM Event WHERE id=?";
        try {
            int ret_req;
            // Delete Event
            PreparedStatement req_delete_prep = db.prepareStatement(req_delete);
            req_delete_prep.setInt(1,name);
            ret_req=req_delete_prep.executeUpdate();
            if(ret_req==0){
                ret=false;
            }

        }catch (Exception e){
            ret=false;
            System.out.println(e);
        }
        return ret;

    }
   /**
     * Get list of events by contact who create the event
     * @param cont contact
     * @pre cont!=null && 
     * !cont.getName().isEmpty() && !cont.getSurname().isEmpty() && !cont.getRole().isEmpty() && cont.getName()!=null && cont.getSurname() != null &&
     *  cont.getReferent()!= null && cont.isReserved()|| (cont.getReferent()!= null && !cont.isReserved())
     * @return list of events
     */
    public ArrayList<Event> getEventsByCreator(Contact cont){
        assert (cont != null); 
        assert !cont.getName().isEmpty() && !cont.getSurname().isEmpty() && !cont.getRole().isEmpty() && cont.getName()!=null && cont.getSurname() != null: "Pre condition violated";
        assert  cont.getReferent()!= null && cont.isReserved()|| (cont.getReferent()!= null && !cont.isReserved()) : "Pre condition violated";
        ArrayList<Event> ret = new ArrayList<>();
        Event evt;
        String name;
        int eventId;
        int contact_id = cont.getId();
        int author_id;
        String description;
        String type;
        java.util.Date date;
        ArrayList<Contact> contactList;
        String req_insert = "SELECT * FROM Event WHERE authorId=? ";
        try {
            PreparedStatement req_insert_prep = db.prepareStatement(req_insert);
            req_insert_prep.setInt(1,contact_id);
            req_insert_prep.executeQuery();
            ResultSet rs = req_insert_prep.getResultSet();
            while (rs.next()) {
                eventId=rs.getInt("id");
                name = rs.getString("name");
                author_id = rs.getInt("authorId");
                description =rs.getString("description");
                type = EventTypeDAO.getInstance().getNameByID(rs.getInt("type"));
                date = rs.getDate("date");
                evt = new Event(name, date,ContactDAO.getInstance().getContactById(author_id),type,eventId);
                evt.setDescription(description);
                contactList = ContactDAO.getInstance().getContactByEvent(evt);
                for (Contact contact :contactList){
                    if (contact!=null) evt.addContact(contact);
                }
                ret.add(evt);
            }                    
       
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    /**
     * Get list of events by contact 
     * @param cont contact
     * @pre cont!=null &&
     * !cont.getName().isEmpty() && !cont.getSurname().isEmpty() && !cont.getRole().isEmpty() && cont.getName()!=null && cont.getSurname() != null &&
     *  cont.getReferent()!= null && cont.isReserved()|| (cont.getReferent()!= null && !cont.isReserved())
     * @return list of events
     */
    public ArrayList<Event> getEventsByContact(Contact cont){
        assert (cont != null); 
        assert !cont.getName().isEmpty() && !cont.getSurname().isEmpty() && !cont.getRole().isEmpty() && cont.getName()!=null && cont.getSurname() != null: "Pre condition violated";
        assert  cont.getReferent()!= null && cont.isReserved()|| (cont.getReferent()!= null && !cont.isReserved()) : "Pre condition violated";
        ArrayList<Event> ret = new ArrayList<>();
        Event evt;
        String name;
        int eventId;
        int author_id = cont.getId();
        String description;
        String type;
        java.util.Date date;
        ArrayList<Contact> contactList;
        String req_insert = "SELECT id,name,description,date,type,authorId FROM crm_bdd.Event,crm_bdd.Contact_Event_Asso WHERE  eventId=id AND contactId=? ";
        try {
            PreparedStatement req_insert_prep = db.prepareStatement(req_insert);
            req_insert_prep.setInt(1,author_id);
            req_insert_prep.executeQuery();
            ResultSet rs = req_insert_prep.getResultSet();
            while (rs.next()) {
                eventId=rs.getInt("id");
                name = rs.getString("name");
                author_id = rs.getInt("authorId");
                description =rs.getString("description");
                type = EventTypeDAO.getInstance().getNameByID(rs.getInt("type"));
                date = rs.getDate("date");
                evt = new Event(name, date,  ContactDAO.getInstance().getContactById(author_id),type,eventId);
                evt.setDescription(description);
                contactList = ContactDAO.getInstance().getContactByEvent(evt);
                for (Contact contact :contactList){
                    if (contact!=null) evt.addContact(contact);
                }
                ret.add(evt);
            }                    
       
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
   
    /**
     * Get list of events by contact 
     * @param cont contact
     * @pre id>0
     * @return event 
     */
    public Event getEventById(int id){
       
        assert id>0;
        Event ret =null;
        String name;
        int eventId = id;
        int author_id;
        String description;
        String type;
        java.util.Date date;
        Contact author;
        ArrayList<Contact> contactList;
        String req_insert = "SELECT * FROM Event WHERE id=? ";
        try {
            PreparedStatement req_insert_prep = db.prepareStatement(req_insert);
            req_insert_prep.setInt(1,id);
            req_insert_prep.executeQuery();
            ResultSet rs = req_insert_prep.getResultSet();
            while (rs.next()) {
                eventId=rs.getInt("id");
                name = rs.getString("name");
                author_id = rs.getInt("authorId");
                description =rs.getString("description");
                type = EventTypeDAO.getInstance().getNameByID(rs.getInt("type"));
                date = rs.getDate("date");
                ret = new Event(name, date,  ContactDAO.getInstance().getContactById(author_id),type,eventId);
                ret.setDescription(description);
                contactList = ContactDAO.getInstance().getContactByEvent(ret);
                for (Contact contact :contactList){
                    if (contact!=null) ret.addContact(contact);
                }
             
            }                                        
       
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}