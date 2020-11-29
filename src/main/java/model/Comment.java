package model;

/**
 * This class represents a comment in the CRM
 *
 * @author Margaux SCHNELZAUER
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
     */
    public Comment(Contact author, Contact concernedContact, String content, int id) {
        this.author = author;
        this.concernedContact = concernedContact;
        this.content = content;
        this.id = id;

    }

    /**
     * The constructor of Comment
     *
     * @param author : the comment author
     * @param concernedContact : the contact who is concerned by the comment
     */
    public Comment(Contact author, Contact concernedContact, String content) {

        this.author = author;
        this.concernedContact = concernedContact;
        this.content = content;
        this.id = 0;

    }

    //-----------------------------------------------------------------//

    /**
     * Get the comment content
     *
     * @return the comment content
     */
    public String getContent() {

        return this.content;
    }

    /**
     * Get the contact author
     *
     * @return the contact author
     */
    public Contact getAuthor() {

        return this.author;
    }

    /**
     * Get the contact who is concerned by the comment
     *
     * @return the contact who is concerned by the comment
     */
    public Contact getConcernedContact() {

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
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Set a new author
     *
     * @param author : the new comment author
     */
    public void setAuthor(Contact author) {
        this.author = author;
    }

    /**
     * Set a new concerned contact
     *
     * @param concernedContact : new concerned contact
     */
    public void setConcernedContact(Contact concernedContact) {
        this.concernedContact = concernedContact;
    }

    /**
     * Set a new comment id
     *
     * @param id : id of comment
     */
    public void setId(int id) {
        this.id = id;
    }


}
