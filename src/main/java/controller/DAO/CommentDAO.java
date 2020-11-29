package controller.DAO;
import model.*;
import java.sql.*;
import java.util.ArrayList;
/**
 * CommentDAO : Class that handle the communication between Comment & the database
 * @author Gurvan.R
 */
public class CommentDAO {

    /**
     * Instance of the object
     */
    public static CommentDAO instance;
    
    /**
     * Database connection
     */
    private Connection db;

    private CommentDAO() {
        this.db = DBConnection.getInstance();
    }

    /**
     * Get the instance of the DAO class
     * @return unique instance of CommentDAO
     */
    public static CommentDAO getInstance() {
        if (instance == null) {
            instance = new CommentDAO();
        }
        return instance;
    }

    /**
     * Save a comment in the databse
     * @pre cmt != null && cmt.getAuthor() != null && cmt.getConcernedContact() != null && cmt.getContent()
     * @param Comment comment
     * @return true if request is a sucess
     */
    public boolean saveComment(Comment cmt){
        assert cmt != null && cmt.getAuthor() != null && cmt.getConcernedContact() != null && cmt.getContent() != null: "Pre condition violated";
        
        boolean ret=true;
        if (cmt != null && cmt.getAuthor() != null && cmt.getConcernedContact() != null){
            
            // Retrieve Data from Author 
            int author = cmt.getAuthor().getId();
            int contact= cmt.getConcernedContact().getId();
            String content = cmt.getContent();
            // Check if content is null 
            if (content == null){
                content = "";
            }

            // Request ton insert a Comment 
            String req_insert ="INSERT INTO Comment(authorId,contactId,content) VALUES (?,?,?)";

            try {

                // Forge request
                PreparedStatement req_insertcmt_prep = db.prepareStatement(req_insert);
                req_insertcmt_prep.setInt(1,author);
                req_insertcmt_prep.setInt(2,contact);
                req_insertcmt_prep.setString(3,content);

                //Insert Comment
                int insert = req_insertcmt_prep.executeUpdate();
                
                if (insert==0){
                    ret=false;
                }
            } catch( Exception e){
                e.printStackTrace();
                ret=false;
            }
        }
        else{
            ret=false;
            if (cmt==null){
                System.out.println("ERROR CommentDAO : Impossible to insert data, Comment is null");
            }
            else{
                System.out.println("ERROR CommentDAO : Impossible to insert data, Author or ConcernedContact is null");
            }
        }
        return ret;
    }

    /**
     * Update a comment in the database
     * @param Comment cmt 
     * @pre cmt != null && cmt.getAuthor() != null && cmt.getConcernedContact() != null && cmt.getContent()
     * @return true if request is a success 
     */
    public boolean updateComment(Comment cmt ){
        assert cmt != null && cmt.getAuthor() != null && cmt.getConcernedContact() != null && cmt.getContent() != null: "Pre condition violated";

        boolean ret =true;
        if (cmt != null && cmt.getAuthor() != null && cmt.getConcernedContact() != null){
            
            // Retrieve data from author and concerned contact
            int id = cmt.getId();
            int author = cmt.getAuthor().getId();
            int contact= cmt.getConcernedContact().getId();
            String content = cmt.getContent();
            if (content == null){
                content = "";
            }
            // Request to uodate a Comment
            String req_update = "UPDATE Comment SET authorId= ? , contactId= ? , content= ?   where authorId= ? AND contactId= ? ";

            try {
                
                // Forge request 
                PreparedStatement req_updatcmt_prep = db.prepareStatement(req_update);
                req_updatcmt_prep.setInt(1,author);
                req_updatcmt_prep.setInt(2,contact);
                req_updatcmt_prep.setString(3,content);
                //WHERE
                req_updatcmt_prep.setInt(4,author);
                req_updatcmt_prep.setInt(5,contact);
                
                //Update Comment
                int insert =req_updatcmt_prep.executeUpdate();
                if (insert==0){
                    ret=false;
                }

            }catch (SQLException e) {
                ret=false;
                System.out.println(e);
            }catch (Exception e) {
                e.printStackTrace();
                ret=false;
            }
        }
        else{
            
            //ERROR 
            ret=false;
            if (cmt==null){
                System.out.println("ERROR CommentDAO : Impossible to update data, Comment is null");
            }
            else{
                System.out.println("ERROR CommentDAO : Impossible to update data, Author or ConcernedContact is null");
            }
        }
        return ret;
    }
    /**
    * Delete comment in the database
    * @param Comment cmt 
    * @pre ccmt != null && cmt.getAuthor() != null && cmt.getConcernedContact() != null && cmt.getContent()
    * @return true the request is a sucess
    */
    public boolean deleteComment(Comment cmt){
        assert cmt != null && cmt.getAuthor() != null && cmt.getConcernedContact() != null && cmt.getContent() != null: "Pre condition violated";

        boolean ret = true;
        if (cmt != null && cmt.getAuthor() != null && cmt.getConcernedContact() != null){

            int author = cmt.getAuthor().getId();
            int contact= cmt.getConcernedContact().getId();
            String content = cmt.getContent();
            if (content == null){
                content = "";
            }
            // Requête
            String req_del = "DELETE FROM Comment WHERE authorId=? and contactId=?";
            try {
                // Insert Comment
                PreparedStatement req_del_prep = db.prepareStatement(req_del);
                req_del_prep.setInt(1,author);
                req_del_prep.setInt(2,contact);
                int insert=req_del_prep.executeUpdate();
                if (insert==0){
                    ret=false;
                }

            }catch (SQLException e) {
                System.out.println(e);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            //ERROR
            if (cmt == null){
                System.out.println("ERROR CommentDAO : Impossible to delete data, Comment is null ");
                ret=false;
            }
            else{
                System.out.println("ERROR CommentDAO : Impossible to delete data, Author or ConcernedContact is null ");
                ret=false;
            }
        }
        return ret;
    }
    /**
     * Get a list of Comments from an author and a contact
     * @return ret list of comments
     * @pre cont != null && 
     * !cont.getName().isEmpty() && !cont.getSurname().isEmpty() && !cont.getRole().isEmpty() && cont.getName()!=null && cont.getSurname()&&
     * cont.getReferent()!= null && cont.isReserved()|| (cont.getReferent()!= null && !cont.isReserved()) &&
     * !author.getName().isEmpty() && !author.getSurname().isEmpty() && !author.getRole().isEmpty() && author.getName()!=null && author.getSurname() != null &&
     * author.getReferent()!= null && author.isReserved()|| (author.getReferent()!= null && !author.isReserved()) && author != null 
     * @post ret != null 
     * 
     */
    public Comment getCommentByAuthorAndContact(Contact author,Contact cont){

        assert cont != null : "Pre condition violated"; 
        assert !cont.getName().isEmpty() && !cont.getSurname().isEmpty() && !cont.getRole().isEmpty() && cont.getName()!=null && cont.getSurname() != null : "Pre condition violated";
        assert cont.getReferent()!= null && cont.isReserved()|| (cont.getReferent()!= null && !cont.isReserved()) : "Pre condition violated";
        assert author != null : "Pre condition violated"; 
        assert !author.getName().isEmpty() && !author.getSurname().isEmpty() && !author.getRole().isEmpty() && author.getName()!=null && author.getSurname() != null : "Pre condition violated";
        assert author.getReferent()!= null && author.isReserved()|| (author.getReferent()!= null && !author.isReserved()) : "Pre condition violated";
        Comment ret =null;
        if (author!=null && cont!=null){
            
            //Retrieve data from author and contact 
            int author_id = author.getId();
            int contact_id = cont.getId();
            String content;
            
            //Request to select commments from authorID and contactID 
            String req_select = "SELECT * FROM Comment WHERE authorId=? AND contactId=?";
            try {
                
                // Forge request 
                PreparedStatement req_select_prep = db.prepareStatement(req_select );
                req_select_prep.setInt(1,author_id);
                req_select_prep.setInt(2,contact_id);
                
                // Get query results 
                ResultSet res = req_select_prep.executeQuery();

                while (res.next()){
                    content = res.getString("content");
                    if (content==null){
                        content ="";
                    }
                    //Add comment in the list 
                    ret = new Comment(author,cont,content);

                }
            }catch (SQLException e) {
                System.out.println(e);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("ERROR CommentDAO : Impossible to retrieve data : author or contact is null ");
        }
        assert(ret!=null);
        return ret;
    }


}