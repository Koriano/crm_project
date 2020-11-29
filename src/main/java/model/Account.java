package model;


import java.util.ArrayList;

/**
 * This class represents an account
 *
 * @author Margaux SCHNELZAUER
 *
 */
public class Account {

    /**
     * The account id
     */
    private int id;

    /**
     * The name of the user
     */
    private String username;

    /**
     * The account password
     */
    private String password;

    /**
     * The account name
     */
    private String name;

    /**
     * The account right
     */
    private String right;

    /**
     * The account contact
     */
    private Contact contact;

    /**
     * The sector list of the account
     */
    private ArrayList<Sector> sectorsList;



    /**
     * The constructor of the account classS
     *
     * @param username : the name of the user
     * @param password : the account password
     * @param name : the account name
     * @param right : the account right
     * @param contact : the referent contact
     * @param sectorsList : the sector list of an account
     * @param id : the account id
     */
    public Account(String username, String password, String name, String right, Contact contact, ArrayList<Sector> sectorsList, int id) {


        this.username = username;
        this.password = password;
        this.name = name;
        this.right = right;
        this.contact = contact;
        this.sectorsList = sectorsList;
        this.id = id;

        if (contact != null){
            this.contact.setLinkAccount(true);
        }

    }


    /**
     * The constructor of the account classS
     *
     * @param username : the name of the user
     * @param password : the account password
     * @param name : the account name
     * @param right : the account right
     * @param contact : the referent contact
     * @param sectorsList : the sector list of an account
     */
    public Account(String username, String password, String name, String right, Contact contact, ArrayList<Sector> sectorsList) {


        this.username = username;
        this.password = password;
        this.name = name;
        this.right = right;
        this.contact = contact;
        this.sectorsList = sectorsList;
        this.id = 0;

        if (contact != null){
            this.contact.setLinkAccount(true);
        }

    }

    //-----------------------------------------------------------------//

    /**
     * Get the user name
     *
     * @return the user name
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the account password
     *
     * @return the account password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get the account password
     *
     * @return the account password
     */
    public String getName() {
        return name;
    }

    /**
     * Get the account right
     *
     * @return the account right
     */
    public String getRight() {
        return right;
    }

    /**
     * Get the account contact
     *
     * @return the account contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Get the sector list of the account
     *
     * @return the sector list of the account
     */
    public ArrayList<Sector> getSectors() {
        return sectorsList;
    }


    /**
     * Get the account id
     *
     * @return the account id
     */
    public int getId() {
        return this.id;
    }

    //-----------------------------------------------------------------//

    /**
     * Set a new username
     *
     * @param username : the new username
     */
    public void setUsername(String username) {

        this.username = username;
    }

    /**
     * Set a new password
     *
     * @param password : the new password
     *
     */
    public void setPassword(String password) {

        this.password = password;
    }


    /**
     * Set a new name
     *
     * @param name : the new name
     *
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * Set a new right
     *
     * @param right : a new type of right
     *
     */
    public void setRight(String right) {

        this.right = right;
    }

    /**
     * Set a new referent contact
     *
     * @param contact : the new referent contact
     *
     */
    public void setContact(Contact contact) {

        this.contact = contact;
        this.contact.setLinkAccount(true);
    }

    /**
     * Add a new sector in the sector list
     *
     * @param sector : the new sector to add
     *
     */
    public void addSector(Sector sector) {

        this.sectorsList.add(sector);

    }

    /**
     * Remove a sector in the sector list
     *
     * @param sector : the sector to remove
     *
     */
    public void removeSector(Sector sector) {

        this.sectorsList.remove(sector);

    }


    /**
     * Set a new account id
     *
     * @param id : the new account id
     */
    public void setId(int id) {
        this.id = id;
    }


}
