package crm_project.controller.DAO;
import model.*;
import java.sql.*;
import java.util.ArrayList;
/**
 * CommentDAO : Class that handle the model Entity with the database
 * @author Gurvan.R
 */
public class CommentDAO {

    /**
     * Instance of the object
     */
    public static CommentDAO instance;
    private Connection db;

    private CommentDAO() {
        this.db = DBConnection.getInstance();
    }

    /**
     * Get the instance of the DAO class
     *
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
     * @param Comment comment
     */
    public void saveCommsent(Comment cmt){
        assert (cmt != null);
        // Get Data from cont
        int author = cmt.getAuthor().getId();
        int contact= cmt.getConcernedContact().getId();
        String content = cmt.getContent();
        if (content == null){
            content = "";
        }
        // Requête
        String req_insert ="INSERT INTO Commment(author,contact,content) VALUES (?,?,?)";

        try {

            // Insert Comment
            PreparedStatement req_insertcmt_prep = db.prepareStatement(req_insert);
            req_insertcmt_prep.setInt(1,author);
            req_insertcmt_prep.setInt(2,contact);
            req_insertcmt_prep.setString(3,content);
            req_insertcmt_prep.executeQuery();

        } catch(Exception e){ System.out.println(e);}
    }

    /**
     * Update a comment in the database
     * @param Comment cmt s
     */
    public void updateComment(Comment cmt ){
        assert (cmt != null);
        // Get Data from cont
        int author = cmt.getAuthor().getId();
        int contact= cmt.getConcernedContact().getId();
        String content = cmt.getContent();
        if (content == null){
            content = "";
        }
        // Requête
        String req_update = "UPDATE Entity set author= ? and contact= ? and content= ?  where author = ?  and contact=?";

        try {
            // Insert Comment
            PreparedStatement req_updatcmt_prep = db.prepareStatement(req_update);
            req_updatcmt_prep.setInt(1,author);
            req_updatcmt_prep.setInt(2,contact);
            req_updatcmt_prep.setString(3,content);
            //WHERE
            req_updatcmt_prep.setInt(4,author);
            req_updatcmt_prep.setInt(5,contact);
            req_updatcmt_prep.executeQuery();

        } catch(Exception e){ System.out.println(e);}
    }
    /**
     * Delete comment in the database
     */
    public void deleteComment(Comment cmt){
        int author = cmt.getAuthor().getId();
        int contact= cmt.getConcernedContact().getId();
        String content = cmt.getContent();
        if (content == null){
            content = "";
        }
        // Requête
        String req_del = "DELETE FROM Comment WHERE author=? and contact=?";
        try {
            // Insert Comment
            PreparedStatement req_del_prep = db.prepareStatement(req_del);
            req_del_prep.setInt(1,author);
            req_del_prep.setInt(2,contact);
            req_del_prep.setString(3,content);
            //WHERE
            req_del_prep.setInt(4,author);
            req_del_prep.setInt(5,contact);
            req_del_prep.executeQuery();

        } catch(Exception e){ System.out.println(e);
        }
    }
    public Comment getCommentByAuthorAndContact(Contact author,Contact contact){
        assert(author!=null && contact!=null);
        Comment ret = null;
        int author_id = author.getId();
        int contact_id = contact.getId();
        String content;
        String req_select = "SELECT * FROM Comment WHERE author=? AND contact=?";
        try {
            // Insert Comment
            PreparedStatement req_select_prep = db.prepareStatement(req_select );
            req_select_prep.setInt(1,author_id);
            req_select_prep.setInt(2,contact_id);
            ResultSet res = req_select_prep.executeQuery();

            while (res.next()){
                content = res.getString("content");
                if (content==null){
                    content ="";
                }
                ret = new Comment(author,contact,content);

            }
        }catch(Exception e){ System.out.println(e); }
        assert(ret!=null);
        return ret;
    }


}