package model;


import java.util.ArrayList;

/**
 * This class represents a sector in the CRM
 *
 * @author Margaux SCHNELZAUER
 *
 * @inv !name.isEmpty() && name != null && this.contactList != null
 */
public class Sector {
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
     *
     * @pre : !name.isEmpty() && name != null
     */
    public Sector(String name) {
        assert !name.isEmpty() && name != null: "Pre condition violated";

        this.name = name;
        this.contactList = new ArrayList<Contact>();

        this.inv();
    }

    //-----------------------------------------------------------------//

    /**
     * Get the name of the sector
     *
     * @return the name of the contact
     */
    public String getName() {
        this.inv();
        return this.name;
    }


    /**
     * Get the contact list
     *
     * @return the contact list
     */
    public ArrayList<Contact> getContactList() {
        this.inv();
        return this.contactList;
    }

    //-----------------------------------------------------------------//

    /**
     * Set a new name of the sector
     *
     * @param name : the new name of the sector
     *
     * @pre : !name.isEmpty() && name != null
     */
    public void setName(String name) {
        // pre condition
        assert !name.isEmpty() && name != null : "Pre condition violated";

        this.name = name;
        this.inv();
    }


    /**
     * Add a contact to the contact list
     *
     * @param contact : new contact of the sector
     *
     * @pre contact != null && !this.contactList.contains(contact)
     * @post : this.contactList.contains(contact)
     */
    public void addContact(Contact contact){
        // pre condition
        assert !this.contactList.contains(contact) && contact != null : "Pre condition violated";

        contactList.add(contact);
        this.inv();

        // post condition
        assert this.contactList.contains(contact) : "Post condition violated";
    }


    /**
     * Remove a contact in the contact list
     *
     * @param contact : the contact to remove
     *
     * @pre : this.contactList.size() > 0 && contact != null && this.contactList.contains(contact)
     * @post : !this.contactList.contains(contact)
     */
    public void removeContact(Contact contact){
        // pre condition
        assert this.contactList.size() > 0 && contact != null && this.contactList.contains(contact) : "Pre condition violated";

        contactList.remove(contact);
        this.inv();

        // post condition
        assert !this.contactList.contains(contact) : "Post condition violated";
    }

    //-----------------------------------------------------------------//

    /**
     * Invariant of the class
     */
    private void inv(){
        assert !name.isEmpty() && name != null && this.contactList != null : "Invariant violated";
    }
}
