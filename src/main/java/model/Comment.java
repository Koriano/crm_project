package model;

/**
 * This class represents a comment in the CRM
 *
 * @author Margaux SCHNELZAUER
 *
 * @inv this.author != null && this.concernedContact != null
 */
public class Comment {

    /**
     * The content of the comment
     */
    private String content;

    /**
     * The author of the comment
     */
    private Contact author;

    /**
     * The contact who is concerned by the comment
     */
    private Contact concernedContact;


    /**
     * The constructor of Comment
     *
     * @param author : the comment author
     * @param concernedContact : the contact who is concerned by the comment
     *
     * @pre author != null & concernedContact != null
     */
    public Comment(Contact author, Contact concernedContact) {
        // pre condition
        assert author != null & concernedContact != null : "Pre condition violated";

        this.content = "";
        this.author = author;
        this.concernedContact = concernedContact;

        this.inv();
    }

    //-----------------------------------------------------------------//

    /**
     * Get the comment content
     *
     * @return the comment content
     */
    public String getContent() {
        this.inv();
        return this.content;
    }

    /**
     * Get the contact author
     *
     * @return the contact author
     */
    public Contact getAuthor() {
        this.inv();
        return this.author;
    }

    /**
     * Get the contact who is concerned by the comment
     *
     * @return the contact who is concerned by the comment
     */
    public Contact getConcernedContact() {
        this.inv();
        return this.concernedContact;
    }

    //-----------------------------------------------------------------//

    /**
     * Set a new content in the comment
     *
     * @param content : the new content
     */
    public void setContent(String content) {
        this.content = content;
        this.inv();
    }

    /**
     * Set a new author
     *
     * @param author : the new comment author
     *
     * @pre author != null
     */
    public void setAuthor(Contact author) {
        // pre condition
        assert author != null : "Pre condition violated";

        this.author = author;
        this.inv();
    }

    /**
     * Set a new concerned contact
     *
     * @param concernedContact : the new concerned contact
     *
     * @pre concernedContact != null
     */
    public void setConcernedContact(Contact concernedContact) {
        // pre condition
        assert concernedContact != null : "Pre condition violated";

        this.concernedContact = concernedContact;
        this.inv();
    }

    //-----------------------------------------------------------------//

    /**
     * The invariant of the class
     */
    private void inv(){
        assert this.author != null && this.concernedContact != null : "Invariant violated";
    }

}
