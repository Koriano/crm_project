package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * This class represents an Event in the CRM
 *
 * @author Margaux SCHNELZAUER
 *
 * @inv name != null && this.date != null && this.contactsList.size() > 0
 */
public class Event {

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
     *
     * @pre name != null && date != null  && author != null && !type.isEmpty() && type != null
     */
    public Event(String name, Date date, Contact author, String type) {
        // pre conditon
        assert name != null && date != null && author != null  && !type.isEmpty() && type != null: " Pre condition violated";

        this.name = name;
        this.date = date;
        this.contactsList = new ArrayList<Contact>();
        this.contactsList.add(author);
        this.type = type;
        this.description = "";

        this.inv();
    }

    //-----------------------------------------------------------------//

    /**
     * Get the event name
     *
     * @return the event name
     */
    public String getName() {
        this.inv();
        return this.name;
    }

    /**
     * Get the event description
     *
     * @return the event description
     */
    public String getDescription() {
        this.inv();
        return this.description;
    }


    /**
     * Get the event date
     *
     * @return the event date
     */
    public Date getDate() {
        this.inv();
        return this.date;
    }

    /**
     * Get the event type
     *
     * @return the event type
     */
    public String getType() {
        this.inv();
        return this.type;
    }

    /**
     * Get the list of contact for the event
     *
     * @return a list of contact concerned by the event
     */
    public ArrayList<Contact> getContactsList() {
        this.inv();
        return this.contactsList;
    }


    //-----------------------------------------------------------------//

    /**
     * Set a new name
     *
     * @param name : the new event name
     *
     * @pre name != null
     */
    public void setName(String name) {
        // pre condition
        assert name != null : "Pre condition violated";

        this.name = name;
        this.inv();
    }

    /**
     * Set a new event description
     *
     * @param description : the new event description
     *
     * @pre description != null
     */
    public void setDescription(String description) {
        // pre condition
        assert description != null : "Pre condition violated";

        this.description = description;
        this.inv();
    }

    /**
     * Set a new event date
     *
     * @param date : the new event date
     *
     * @pre date != null
     */
    public void setDate(Date date) {
        // pre condition
        assert date != null : "Pre condition violated";

        this.date = date;
        this.inv();
    }

    /**
     * Set a new event type
     *
     * @param type : the new event type
     *
     * @pre !type.isEmpty() && type != null
     */
    public void setType(String type) {
        // pre condition
        assert !type.isEmpty() && type != null : "Pre condition violated";

        this.type = type;
        this.inv();
    }

    /**
     * Add a new contact in the contacts list
     *
     * @param contact : the new contact to add
     *
     * @pre contact != null && !this.contactsList.contains(contact)
     * @post this.contactsList.contains(contact)
     */
    public void addContact(Contact contact){
        // pre condition
        assert contact != null && !this.contactsList.contains(contact) : "Pre condition violated";

        this.contactsList.add(contact);

        // post condition
        assert this.contactsList.contains(contact) : "Post condition violated";
        this.inv();
    }

    /**
     * Remove a contact from the contacts list
     *
     * @param contact : the contact to remove
     *
     * @pre contact != null && this.contactsList.contains(contact)
     * @post !this.contactsList.contains(contact)
     */
    public void removeContact(Contact contact){
        // pre condition
        assert contact != null && this.contactsList.contains(contact) : "Pre condition violated";

        this.contactsList.remove(contact);

        // post condition
        assert !this.contactsList.contains(contact) : "Post condition violated";
        this.inv();
    }
    //-----------------------------------------------------------------//

    /**
     * The invariant of the class
     */
    private void inv(){
        assert name != null && this.date != null && this.contactsList.size() > 0 : "Invariant violated";
    }
}
