package controller.servlets.contact;

import model.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SaveCommentServlet extends HttpServlet {
    private static final String PARAM_COMMENT_CONTENT = "commentContent";
    private static final String PARAM_SESSION_USER_ACCOUNT = "user";
    private static final String PARAM_SESSION_ID_CONTACT = "contact_id";

    private static final String URL_REDIRECT = "/research/contactProfile";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.saveComment(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.saveComment(req, resp);
    }

    private void saveComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get comment and contact DAO
//        CommentDAO commentDAO = CommentDAO.getInstance();
//        ContactDAO contactDAO = ContactDAO.getInstance();

        // Get session and parameters
        HttpSession session = req.getSession();
        Account user = (Account) session.getAttribute(PARAM_SESSION_USER_ACCOUNT);
        String contact_id = (String) session.getAttribute(PARAM_SESSION_ID_CONTACT);
        String content = (String) req.getAttribute(PARAM_COMMENT_CONTENT);

        try{
//            Contact contact = contactDAO.getContactById(Integer.parseInt(contact_id));
//            commentDAO.updateComment(new Comment(user.getContact(), contact, content));

            resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
