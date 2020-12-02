package controller.servlets.contact;

import controller.DAO.CommentDAO;
import controller.DAO.ContactDAO;
import model.Account;
import model.Comment;
import model.Contact;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * A servlet which handles the save comment feature
 *
 * @author Alexandre HAMON
 */
public class SaveCommentServlet extends HttpServlet {
    /**
     * Session parameter
     */
    private static final String PARAM_SESSION_USER_ACCOUNT = "user";

    /**
     * Request parameters
     */
    private static final String PARAM_COMMENT_CONTENT = "commentContent";
    private static final String PARAM_ID_CONTACT = "id";

    /**
     * View redirect
     */
    private static final String URL_REDIRECT = "/research/contact";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.saveComment(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.saveComment(req, resp);
    }

    /**
     * A method to save comment
     *
     * @param req the request containing the parameters
     * @param resp the response to be sent
     * @throws ServletException
     * @throws IOException
     */
    private void saveComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get comment and contact DAO
        CommentDAO commentDAO = CommentDAO.getInstance();
        ContactDAO contactDAO = ContactDAO.getInstance();

        // Get session and parameters
        HttpSession session = req.getSession();
        Account user = (Account) session.getAttribute(PARAM_SESSION_USER_ACCOUNT);

        String contact_id = req.getParameter(PARAM_ID_CONTACT);
        String content = req.getParameter(PARAM_COMMENT_CONTENT);

        // Build redirect url
        String redirect_url = req.getContextPath() + URL_REDIRECT + "?id=" + contact_id;

        try{
            Contact contact = contactDAO.getContactById(Integer.parseInt(contact_id));
            Comment comment = commentDAO.getCommentByAuthorAndContact(user.getContact(), contact);

            if(comment == null) {
                commentDAO.saveComment(new Comment(user.getContact(), contact, content));
            } else {
                comment.setContent(content);
                commentDAO.updateComment(comment);
            }

            resp.sendRedirect(redirect_url);
        }
        catch (Exception e){
            resp.sendRedirect(redirect_url);
        }
    }
}
