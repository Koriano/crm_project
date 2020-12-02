package controller.servlets.contact;

import controller.DAO.ContactDAO;
import model.Contact;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * A servlet which handles the delete contact feature
 *
 * @author Alexandre HAMON
 */
public class DeleteContactServlet extends HttpServlet {
    /**
     * Request parameter
     */
    private static final String PARAM_ID_CONTACT = "id";

    /**
     * View redirects
     */
    private static final String URL_REDIRECT_SUCCESS = "/research";
    private static final String URL_REDIRECT_FAIL = "/research/contact";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.deleteContact(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.deleteContact(req, resp);
    }

    /**
     * A method to delete contact
     *
     * @param req the request containing parameters
     * @param resp the response to be sent
     * @throws ServletException
     * @throws IOException
     */
    private void deleteContact(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get contactDAO
        ContactDAO contactDAO = ContactDAO.getInstance();

        // Get contact id
        String id = req.getParameter(PARAM_ID_CONTACT);

        // Build redirect url
        String redirect_fail_url = req.getContextPath() + URL_REDIRECT_FAIL + "?id=" + id;

        try {
            Contact contact = contactDAO.getContactById(Integer.parseInt(id));

            if (contact != null){
                // Delete contact from DB and its id from session
                contactDAO.deleteContact(contact);
                resp.sendRedirect(req.getContextPath() + URL_REDIRECT_SUCCESS);
            } else {
                resp.sendRedirect(redirect_fail_url);
            }

        } catch (Exception e) {
            resp.sendRedirect(redirect_fail_url);
        }
    }
}
