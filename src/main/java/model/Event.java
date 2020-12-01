package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * This class represents an Event in the CRM
 *
 * @author Margaux SCHNELZAUER
 */
public class Event {

    /**
     * Event id
     */
    private int id;

    /**
     * The event name
     */
    private String name;

    /**
     * The event description
     */
    private String description;

    /**
     * The event date
     */
    private Date date;

    /**
     * The event type
     */
    private String type;

    /**
     * A list which contain the concerned contact
     */
    private ArrayList<Contact> contactsList;


    /**
     * The constructor of the Event
     *
     * @param name : the event name
     * @param date : the event date
     * @param author : the event author
     * @param type : the event type
     * @param id : the event id
     */
    public Event(String name, Date date, Contact author, String type, int id) {
        this.name = name;
        this.date = date;
        this.contactsList = new ArrayList<Contact>();
        this.contactsList.add(author);
        this.type = type;
        this.description = "";
        this.id = id;
    }

    /**
     * The constructor of the Event
     *
     * @param name : the event name
     * @param date : the event date
     * @param author : the event author
     * @param type : the event type
     */
    public Event(String name, Date date, Contact author, String type) {
        this.name = name;
        this.date = date;
        this.contactsList = new ArrayList<Contact>();
        this.contactsList.add(author);
        this.type = type;
        this.description = "";
        this.id = 0;
    }

    //-----------------------------------------------------------------//

    /**
     * Return the event id
     *
     * @return the event id
     */
    public int getId() {
        return this.id;
    }


    /**
     * Get the event name
     *
     * @return the event name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the event description
     *
     * @return the event description
     */
    public String getDescription() {
        return this.description;
    }


    /**
     * Get the event date
     *
     * @return the event date
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Get the event type
     *
     * @return the event type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Get the list of contact for the event
     *
     * @return a list of contact concerned by the event
     */
    public ArrayList<Contact> getContactsList() {
        return this.contactsList;
    }


    //-----------------------------------------------------------------//

    /**
     * Set a new event id
     *
     * @param id : the new event id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Set a new name
     *
     * @param name : the new event name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set a new event description
     *
     * @param description : the new event description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set a new event date
     *
     * @param date : the new event date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Set a new event type
     *
     * @param type : the new event type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Add a new contact in the contacts list
     *
     * @param contact : the new contact to add
     */

    public void addContact(Contact contact){
        // pre condition
        assert contact != null  : "Pre condition violated";

        if (!this.contactsList.contains(contact)){
            this.contactsList.add(contact);
        }

        // post condition
        assert this.contactsList.contains(contact) : "Post condition violated";
    }

    /**
     * Remove a contact from the contacts list
     *
     * @param contact : the contact to remove
     */
    public void removeContact(Contact contact){
        this.contactsList.remove(contact);
    }

}
