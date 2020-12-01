package model;


import java.util.ArrayList;

/**
 * This class represents a sector in the CRM
 *
 * @author Margaux SCHNELZAUER
 */
public class Sector {

    /**
     *  The sector id
     */
    private int id;

    /**
     * The name of the sector
     */
    private String name;

    /**
     * The contact list associated to the sector
     */
    private ArrayList<Contact> contactList;


    /**
     * The constructor of the Sector class
     *
     * @param name : the name of the sector
     * @param id : the sector id
     */
    public Sector(String name, int id) {
        this.name = name;
        this.contactList = new ArrayList<Contact>();
        this.id = id;
    }

    /**
     * The constructor of the Sector class
     *
     * @param name : the name of the sector
     */
    public Sector(String name) {
        this.name = name;
        this.contactList = new ArrayList<Contact>();
        this.id = 0;
    }

    //-----------------------------------------------------------------//

    /**
     * Get the name of the sector
     */
    public String getName() {
        return this.name;
    }


    /**
     * Get the contact list
     */
    public ArrayList<Contact> getContactList() {
        return this.contactList;
    }


    /**
     * Return true if contact is in the contactlist
     *
     * @param contact : contact to find
     */
    public boolean hasContact(Contact contact){
        boolean present;

        if (this.contactList.contains(contact)){
            present = true;
        }

        else{
            present = false;
        }

        return present;
    }

    /**
     * Get the sector id
     */
    public int getId() {
        return this.id;
    }

    //-----------------------------------------------------------------//

    /**
     * Set a new name of the sector
     *
     * @param name : the new name of the sector
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Add a contact to the contact list
     *
     * @param contact : new contact of the sector
     */
    public void addContact(Contact contact){
        contactList.add(contact);
    }


    /**
     * Remove a contact in the contact list
     *
     * @param contact : the contact to remove
     *
     */
    public void removeContact(Contact contact){
        contactList.remove(contact);
    }

    /**
     * Set a new sector id
     *
     * @param id : the new sector id
     */
    public void setId(int id) {
        this.id = id;
    }
}
