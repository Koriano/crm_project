package model;


import java.util.ArrayList;

/**
 * This class represents an account
 *
 * @author Margaux SCHNELZAUER
 *
 * @inv !"".equals(this.username) && !"".equals(this.password) && !"".equals(this.name) && !"".equals(this.right) && this.contact != null && this.sectorsList.size()>0
 */
public class Account {
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
     *
     * @pre !"".equals(username) && !"".equals(password) && !"".equals(name) && !"".equals(right) && contact != null && sectorsList.size()>0 && !contact.isLinkAccount()
     */
    public Account(String username, String password, String name, String right, Contact contact, ArrayList<Sector> sectorsList) {
        // pre condition
        assert !"".equals(username) && !"".equals(password) && !"".equals(name) && !"".equals(right) && contact != null && sectorsList.size()>0 && !contact.isLinkAccount(): "Pre condition violated";

        this.username = username;
        this.password = password;
        this.name = name;
        this.right = right;
        this.contact = contact;
        this.contact.setLinkAccount(true);
        this.sectorsList = sectorsList;

        this.inv();
    }

    //-----------------------------------------------------------------//

    /**
     * Get the user name
     *
     * @return the user name
     */
    public String getUsername() {
        this.inv();
        return username;
    }

    /**
     * Get the account password
     *
     * @return the account password
     */
    public String getPassword() {
        this.inv();
        return password;
    }

    /**
     * Get the account password
     *
     * @return the account password
     */
    public String getName() {
        this.inv();
        return name;
    }

    /**
     * Get the account right
     *
     * @return the account right
     */
    public String getRight() {
        this.inv();
        return right;
    }

    /**
     * Get the account contact
     *
     * @return the account contact
     */
    public Contact getContact() {
        this.inv();
        return contact;
    }

    /**
     * Get the sector list of the account
     *
     * @return the sector list of the account
     */
    public ArrayList<Sector> getSectors() {
        this.inv();
        return sectorsList;
    }

    //-----------------------------------------------------------------//

    /**
     * Set a new username
     *
     * @param username : the new username
     *
     * @pre !"".equals(username)
     */
    public void setUsername(String username) {
        // pre condition
        assert !"".equals(username) : "Pre condition violated";

        this.username = username;
        this.inv();
    }

    /**
     * Set a new password
     *
     * @param password : the new password
     *
     * @pre !"".equals(password)
     */
    public void setPassword(String password) {
        // pre condition
        assert !"".equals(password) : "Pre condition violated";

        this.password = password;
        this.inv();
    }


    /**
     * Set a new name
     *
     * @param name : the new name
     *
     * @pre !"".equals(name)
     */
    public void setName(String name) {
        // pre condition
        assert !"".equals(name) : "Pre condition violated";

        this.name = name;
        this.inv();
    }

    /**
     * Set a new right
     *
     * @param right : a new type of right
     *
     * @pre !"".equals(right)
     */
    public void setRight(String right) {
        // pre condition
        assert !"".equals(right) : "Pre condition violated";

        this.right = right;
        this.inv();
    }

    /**
     * Set a new referent contact
     *
     * @param contact : the new referent contact
     *
     * @pre contact != null && !contact.isLinkAccount()
     */
    public void setContact(Contact contact) {
        // pre condition
        assert contact != null && !contact.isLinkAccount(): "Pre condition violated";

        this.contact = contact;
        this.contact.setLinkAccount(true);

        this.inv();
    }

    /**
     * Add a new sector in the sector list
     *
     * @param sector : the new sector to add
     *
     * @pre sector != null && !this.sectorList.contains(sector)
     * @post : this.sectorList.contains(sector)
     */
    public void addSector(Sector sector) {
        // pre condition
        assert !this.sectorsList.contains(sector) && sector != null : "Pre condition violated";

        this.sectorsList.add(sector);
        this.inv();

        //post condition
        assert this.sectorsList.contains(sector) : "Post condition violated";
    }

    /**
     * Remove a sector in the sector list
     *
     * @param sector : the sector to remove
     *
     * @pre sector != null && this.sectorList.contains(sector)
     * @post : !this.sectorList.contains(sector)
     */
    public void removeSector(Sector sector) {
        // pre condition
        assert this.sectorsList.contains(sector) && sector != null : "Pre condition violated";

        this.sectorsList.remove(sector);
        this.inv();

        //post condition
        assert !this.sectorsList.contains(sector) : "Post condition violated";
    }

    //-----------------------------------------------------------------//

    /**
     * The invariant of the class
     */
    private void inv(){
        assert !"".equals(this.username) && !"".equals(this.password) && !"".equals(this.name) && !"".equals(this.right) && this.contact != null && this.sectorsList.size()>0 : "Invariant violated";
    }

}
