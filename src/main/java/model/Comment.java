package model;

/**
 * This class represents a comment in the CRM
 *
 * @author Margaux SCHNELZAUER
 *
 * @inv this.author != null && this.concernedContact != null && this.content != null
 */
public class Comment {


    /**
     * The comment id
     */
    private int id;

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
     * @param id : the comment id
     *
     * @pre author != null & concernedContact != null && content != null
     */
    public Comment(Contact author, Contact concernedContact, String content, int id) {
        // pre condition
        assert author != null & concernedContact != null && content != null: "Pre condition violated";

        this.author = author;
        this.concernedContact = concernedContact;
        this.content = content;
        this.id = id;

        this.inv();
    }

    /**
     * The constructor of Comment
     *
     * @param author : the comment author
     * @param concernedContact : the contact who is concerned by the comment
     *
     * @pre author != null & concernedContact != null && content != null
     */
    public Comment(Contact author, Contact concernedContact, String content) {
        // pre condition
        assert author != null & concernedContact != null && content != null: "Pre condition violated";

        this.author = author;
        this.concernedContact = concernedContact;
        this.content = content;
        this.id = 0;

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

    /**
     * Get the comment id
     *
     * @return the comment id
     */
    public int getId() {
        return this.id;
    }

    //-----------------------------------------------------------------//

    /**
     * Set a new content in the comment
     *
     * @param content : the new content
     *
     * @pre content != null
     */
    public void setContent(String content) {
        // pre condition
        assert content != null : "Pre condition violated";

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

    /**
     * Set a new comment id
     *
     * @param id : the new comment id
     */
    public void setId(int id) {
        this.id = id;
    }
    //-----------------------------------------------------------------//

    /**
     * The invariant of the class
     */
    private void inv(){
        assert this.author != null && this.concernedContact != null && this.content != null: "Invariant violated";
    }

}
