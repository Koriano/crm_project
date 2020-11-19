package model;

import java.util.ArrayList;

/**
 * This class represents a Contact in the CRM
 *
 * @author Margaux SCHNELZAUER
 *
 * @inv !this.name.isEmpty() && !this.surname.isEmpty() && !this.role.isEmpty()
 * this.name != null && this.surname != null
 */
public class Contact {

    /**
     * Id of the the contact
     */
    private final int id;

    /**
     * The contact name
     */
    private String name;

    /**
     * The contact surname
     */
    private String surname;

    /**
     * The contact address
     */
    private String address;

    /**
     * A list of contact phone number
     */
    private ArrayList<String> phonesList;

    /**
     * A list of contact mail address
     */
    private ArrayList<String> mailsList;

    /**
     * A boolean represent if the contact is reserved
     */
    private boolean isReserved;

    /**
     * The contact role
     */
    private String role;

    /**
     * The contact referent (if the contact is not reserved, referent is null, else it is a Contact)
     */
    private Contact referent;

    /**
     * A list of events creates by the contact
     */
    private ArrayList<Event> eventsList;

    /**
     * A list of comment write by the contact about other contacts
     */
    private ArrayList<Comment> commentsList;

    /**
     * The contact entity
     */
    private Entity entity;

    /**
     * A boolean represents if the contact is link with an account
     */
    private boolean isLinkAccount;


    /**
     * The constructor of a Contact (when the contact is not reserved)
     *
     * @param name : the contact name
     * @param surname : the contact surname
     * @param role : the contact role
     * @param contact : the referent when the contact is reserved
     *
     * @pre !name.isEmpty() && !surname.isEmpty() && !role.isEmpty() && name != null && surname != null
     *  (contact != null && isReserved) || (contact == null && !isReserved)
     */
    public Contact(String name, String surname, String role, Contact contact, boolean isReserved, int id) {
        // pre condition
        assert !name.isEmpty() && !surname.isEmpty() && !role.isEmpty() && name != null && surname != null: "Pre condition violated";
        assert (contact != null && isReserved) || (contact == null && !isReserved) : "Pre condition violated";

        this.name = name;
        this.surname = surname;
        this.role = role;
        this.address = "";
        this.commentsList = new ArrayList<Comment>();
        this.eventsList = new ArrayList<Event>();
        this.mailsList = new ArrayList<String>();
        this.phonesList = new ArrayList<String>();
        this.entity = null;
        this.isLinkAccount = false;
        this.referent = contact;
        this.isReserved = isReserved;
        this.id = id;

        this.inv();
    }


    /**
     * The constructor of a Contact (when the contact is not reserved)
     *
     * @param name : the contact name
     * @param surname : the contact surname
     * @param role : the contact role
     * @param contact : the referent when the contact is reserved
     *
     * @pre !name.isEmpty() && !surname.isEmpty() && !role.isEmpty() && name != null && surname != null
     *  (contact != null && isReserved) || (contact == null && !isReserved)
     */
    public Contact(String name, String surname, String role, Contact contact, boolean isReserved) {
        // pre condition
        assert !name.isEmpty() && !surname.isEmpty() && !role.isEmpty() && name != null && surname != null: "Pre condition violated";
        assert (contact != null && isReserved) || (contact == null && !isReserved) : "Pre condition violated";

        this.name = name;
        this.surname = surname;
        this.role = role;
        this.address = "";
        this.commentsList = new ArrayList<Comment>();
        this.eventsList = new ArrayList<Event>();
        this.mailsList = new ArrayList<String>();
        this.phonesList = new ArrayList<String>();
        this.entity = null;
        this.isLinkAccount = false;
        this.referent = contact;
        this.isReserved = isReserved;
        this.id = 0;

        this.inv();
    }
    //-----------------------------------------------------------------//

    /**
     * Get the name of the contact
     *
     * @return the name of the contact
     */
    public String getName() {
        this.inv();
        return this.name;
    }

    /**
     * Get the contact surname
     *
     * @return the contact surname
     */
    public String getSurname() {
        this.inv();
        return this.surname;
    }

    /**
     * Get the contact address
     *
     * @return the contact address
     */
    public String getAddress() {
        this.inv();
        return this.address;
    }

    /**
     * Get the phones numbers of the contact
     *
     * @return the phones numbers of the contact
     */
    public ArrayList<String> getPhonesList() {
        this.inv();
        return this.phonesList;
    }

    /**
     * Get the mail address' og the contact
     *
     * @return the mail address' of the contact
     */
    public ArrayList<String> getMailsList() {
        this.inv();
        return this.mailsList;
    }

    /**
     * Get the reservation state of the contact (true if a contact is reserved, else it is false)
     *
     * @return the reservation state of the contact
     */
    public boolean isReserved() {
        this.inv();
        return this.isReserved;
    }

    /**
     * Get the contact role
     *
     * @return the contact role
     */
    public String getRole() {
        this.inv();
        return this.role;
    }

    /**
     * Get the contact referent
     *
     * @return the contact referent
     */
    public Contact getReferent() {
        this.inv();
        return this.referent;
    }

    /**
     * Get a list of event creates by the contact
     *
     * @return a list of event creates by the contact
     */
    public ArrayList<Event> getEventsList() {
        this.inv();
        return this.eventsList;
    }

    /**
     * Get a list of contact comments
     *
     * @return a list of contact comments
     */
    public ArrayList<Comment> getCommentsList() {
        this.inv();
        return this.commentsList;
    }

    /**
     * Get the contact entity
     *
     * @return the contact entity
     */
    public Entity getEntity() {
        this.inv();
        return this.entity;
    }

    /**
     * Get the id of the contact
     *
     * @return the contact id
     */
    public int getId() {
        this.inv();
        return this.id;
    }

    /**
     * Get the state of the link (true if the contact is link with an account, else false
     *
     * @return the state of the contact link with an account
     */
    public boolean isLinkAccount() {
        this.inv();
        return this.isLinkAccount;
    }

    //-----------------------------------------------------------------//

    /**
     * Set a new name for the contact
     *
     * @param name : the new contact
     *
     * @pre !name.isEmpty() && name != null
     */
    public void setName(String name) {
        // pre condition
        assert !name.isEmpty() && name != null : "Pre condition violated";

        this.name = name;
        this.inv();
    }

    /**
     * Set a new contact surname
     *
     * @param surname : the new contact surname
     *
     * @pre !surname.isEmpty() && surname != null
     */
    public void setSurname(String surname) {
        // pre condition
        assert !surname.isEmpty() && surname != null : "Pre condition violated";

        this.surname = surname;
        this.inv();
    }

    /**
     * Set a new contact address
     *
     * @param address : the new contact address
     *
     * @pre address != null
     */
    public void setAddress(String address) {
        // pre condition
        assert address != null : "Pre condition violated";

        this.address = address;
        this.inv();
    }

    /**
     * Add a phone number in the list
     *
     * @param phone : the new phone number to add
     *
     * @pre !phone.isEmpty() && phone != null && !this.phonesList.contains(phone)
     * @post this.phonesList.contains(phone)
     */
    public void addPhone(String phone) {
        // pre condition
        assert !phone.isEmpty() && phone != null && !this.phonesList.contains(phone): "Pre condition violated";

        this.phonesList.add(phone);
        this.inv();

        // post condition
        assert this.phonesList.contains(phone): "Post condition violated";
    }

    /**
     * Remove a phone number in the list
     *
     * @param phone : the phone number to remove
     *
     * @pre !phone.isEmpty() && phone != null && this.phonesList.contains(phone)
     * @post !this.phonesList.contains(phone)
     */
    public void removePhone(String phone) {
        // pre condition
        assert !phone.isEmpty() && phone != null && this.phonesList.contains(phone): "Pre condition violated";

        this.phonesList.remove(phone);
        this.inv();

        // post condition
        assert !this.phonesList.contains(phone): "Post condition violated";
    }

    /**
     * Add a new mail address to the list
     *
     * @param mail : the mail to add
     *
     * @pre !mail.isEmpty() && mail != null && !this.mailsList.contains(mail)
     * @post this.mailsList.contains(mail)
     */
    public void addMail(String mail) {
        // pre condition
        assert !mail.isEmpty() && mail != null && !this.mailsList.contains(mail): "Pre condition violated";

        this.mailsList.add(mail);
        this.inv();

        // post condition
        assert this.mailsList.contains(mail): "Post condition violated";
    }

    /**
     * Remove a mail address in the list
     *
     * @param mail : the mail address to remove
     *
     * @pre !mail.isEmpty() && mail != null && this.mailsList.contains(mail)
     * @post !this.mailsList.contains(mail)
     */
    public void removeMail(String mail) {
        // pre condition
        assert !mail.isEmpty() && mail != null && this.mailsList.contains(mail): "Pre condition violated";

        this.mailsList.remove(mail);
        this.inv();

        // post condition
        assert !this.mailsList.contains(mail): "Post condition violated";
    }

    /**
     * Set a new reservation state of the contact
     *
     * @param reserved : the new reservation state of the
     *
     * @pre (referent != null && isReserved) || (referent == null && !isReserved)
     * referent != this
     */
    public void setReserved(boolean reserved, Contact referent) {
        // pre condition
        assert (referent != null && isReserved) || (referent == null && !isReserved) : "Pre condition violated";
        assert referent != this : "Pre condition violated";

        this.isReserved = reserved;
        this.referent = referent;
        this.inv();
    }

    /**
     * Set a new contact role
     *
     * @param role : the new contact role
     *
     * @pre !role.isEmpty()
     */
    public void setRole(String role) {
        //pre condition
        assert !role.isEmpty():  "Pre condition violated";

        this.role = role;
        this.inv();
    }


    /**
     * Add a new event in the list
     *
     * @param event : the new event to add
     *
     * @pre event != null && !this.eventsList.contains(event)
     * @post this.eventsList.contains(event)
     */
    public void addEvent(Event event) {
        // pre condition
        assert event != null && !this.eventsList.contains(event): "Pre condition violated";

        this.eventsList.add(event);
        this.inv();

        // post condition
        assert this.eventsList.contains(event): "Post condition violated";
    }

    /**
     * Remove an event in the list
     *
     * @param event : the event to remove
     *
     * @pre event != null && this.eventsList.contains(event)
     * @post !this.eventsList.contains(event)
     */
    public void removeEvent(Event event) {
        // pre condition
        assert event != null && this.eventsList.contains(event): "Pre condition violated";

        this.eventsList.remove(event);
        this.inv();

        // post condition
        assert !this.eventsList.contains(event): "Post condition violated";
    }

    /**
     * Add a comment in the contact list
     *
     * @param comment : the comment to add
     *
     * @pre comment != null && this.commentsList.contains(comment)
     * @post !this.commentsList.contains(comment)
     */
    public void addComment(Comment comment) {
        // pre condition
        assert comment != null && this.commentsList.contains(comment): "Pre condition violated";

        this.commentsList.add(comment);
        this.inv();

        // post condition
        assert !this.commentsList.contains(comment): "Post condition violated";
    }

    /**
     * Remove a comment in the contact list
     *
     * @param comment : the comment to remove
     *
     * @pre comment != null && !this.commentsList.contains(comment)
     * @post this.commentsList.contains(comment)
     */
    public void removeComment(Comment comment) {
        // pre condition
        assert comment != null && !this.commentsList.contains(comment): "Pre condition violated";

        this.commentsList.remove(comment);
        this.inv();

        // post condition
        assert this.commentsList.contains(comment): "Post condition violated";
    }

    /**
     * Set a new contact entity
     *
     * @param entity : the new contact entity
     */
    public void setEntity(Entity entity) {

        this.entity = entity;
        this.inv();
    }

    /**
     * Set a new link state for the contact
     *
     * @param isLinkAccount : the new link state of the contact
     */
    public void setLinkAccount(boolean isLinkAccount) {
        this.isLinkAccount = isLinkAccount;
        this.inv();
    }

    //-----------------------------------------------------------------//

    /**
     * The invariant of the class
     */
    private void inv(){
        assert !this.name.isEmpty() && !this.surname.isEmpty() && !this.role.isEmpty(): "Invariant violated";
        assert this.name != null && this.surname != null : "Invariant violated";
    }

}
