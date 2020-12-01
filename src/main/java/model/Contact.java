package model;

import java.util.ArrayList;

/**
 * This class represents a Contact in the CRM
 *
 * @author Margaux SCHNELZAUER
 */
public class Contact {

    /**
     * Id of the the contact
     */
    private int id;

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
     * @param id : the contact id

     */
    public Contact(String name, String surname, String role, Contact contact, boolean isReserved, int id) {

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

    }


    /**
     * The constructor of a Contact (when the contact is not reserved)
     *
     * @param name : the contact name
     * @param surname : the contact surname
     * @param role : the contact role
     * @param contact : the referent when the contact is reserved

     */
    public Contact(String name, String surname, String role, Contact contact, boolean isReserved) {

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

    }
    //-----------------------------------------------------------------//

    /**
     * Get the name of the contact
     *
     * @return the name of the contact
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the contact surname
     *
     * @return the contact surname
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * Get the contact address
     *
     * @return the contact address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Get the phones numbers of the contact
     *
     * @return the phones numbers of the contact
     */
    public ArrayList<String> getPhonesList() {
        return this.phonesList;
    }

    /**
     * Get the mail address' og the contact
     *
     * @return the mail address' of the contact
     */
    public ArrayList<String> getMailsList() {
        return this.mailsList;
    }

    /**
     * Get the reservation state of the contact (true if a contact is reserved, else it is false)
     *
     * @return the reservation state of the contact
     */
    public boolean isReserved() {
        return this.isReserved;
    }

    /**
     * Get the contact role
     *
     * @return the contact role
     */
    public String getRole() {
        return this.role;
    }

    /**
     * Get the contact referent
     *
     * @return the contact referent
     */
    public Contact getReferent() {
        return this.referent;
    }

    /**
     * Get a list of event creates by the contact
     *
     * @return a list of event creates by the contact
     */
    public ArrayList<Event> getEventsList() {
        return this.eventsList;
    }

    /**
     * Get a list of contact comments
     *
     * @return a list of contact comments
     */
    public ArrayList<Comment> getCommentsList() {
        return this.commentsList;
    }

    /**
     * Get the contact entity
     *
     * @return the contact entity
     */
    public Entity getEntity() {
        return this.entity;
    }

    /**
     * Get the id of the contact
     *
     * @return the contact id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get the state of the link (true if the contact is link with an account, else false
     *
     * @return the state of the contact link with an account
     */
    public boolean isLinkAccount() {
        return this.isLinkAccount;
    }

    /**
     * Check if the contact is the creator of an event
     *
     * @param event the event to check the creator
     * @return true if the contact is the creator, else false
     */
    public boolean isCreator(Event event){
        return event.getContactsList().get(0).getId() == this.id;
    }

    //-----------------------------------------------------------------//

    /**
     * Set a new name for the contact
     *
     * @param name : the new contact
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set a new contact surname
     *
     * @param surname : the new contact surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Set a new contact address
     *
     * @param address : the new contact address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Add a phone number in the list
     *
     * @param phone : the new phone number to add
     */
    public void addPhone(String phone) {
        this.phonesList.add(phone);
    }

    /**
     * Remove a phone number in the list
     *
     * @param phone : the phone number to remove
     */
    public void removePhone(String phone) {
        this.phonesList.remove(phone);
    }

    /**
     * Add a new mail address to the list
     *
     * @param mail : the mail to add
     */
    public void addMail(String mail) {
        this.mailsList.add(mail);
    }

    /**
     * Remove a mail address in the list
     *
     * @param mail : the mail address to remove
     */
    public void removeMail(String mail) {
        this.mailsList.remove(mail);
    }

    /**
     * Set a new reservation state of the contact
     *
     * @param reserved : the new reservation state of the
     */
    public void setReserved(boolean reserved, Contact referent) {
        this.isReserved = reserved;
        this.referent = referent;
    }

    /**
     * Set a new contact role
     *
     * @param role : the new contact role
     */
    public void setRole(String role) {
        this.role = role;
    }


    /**
     * Add a new event in the list
     *
     * @param event : the new event to add
     */
    public void addEvent(Event event) {
        this.eventsList.add(event);
    }

    /**
     * Remove an event in the list
     *
     * @param event : the event to remove
     */
    public void removeEvent(Event event) {
        this.eventsList.remove(event);
    }

    /**
     * Add a comment in the contact list
     *
     * @param comment : the comment to add
     */
    public void addComment(Comment comment) {
        this.commentsList.add(comment);
    }

    /**
     * Remove a comment in the contact list
     *
     * @param comment : the comment to remove
     */
    public void removeComment(Comment comment) {
        this.commentsList.remove(comment);
    }

    /**
     * Set a new contact entity
     *
     * @param entity : the new contact entity
     */
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * Set a new link state for the contact
     *
     * @param isLinkAccount : the new link state of the contact
     */
    public void setLinkAccount(boolean isLinkAccount) {
        this.isLinkAccount = isLinkAccount;
    }

    /**
     * Set a new contact id
     *
     * @param id : the new contact id
     */
    public void setId(int id) {
        this.id = id;
    }



}
