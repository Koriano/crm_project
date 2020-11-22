package controller.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteContactServlet extends HttpServlet {
    private static final String PARAM_SESSION_ID_CONTACT = "contact_id";

    private static final String URL_REDIRECT_SUCCESS = "/research";
    private static final String URL_REDIRECT_FAIL = "/research/contactProfile";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get contactDAO
//        ContactDAO contactDAO = ContactDAO.getInstance();

        // Get session and id
        HttpSession session = req.getSession();
        String id = (String) session.getAttribute(PARAM_SESSION_ID_CONTACT);

        // Delete contact from DB and its id from session
//        contactDAO.deleteContact(id);
        session.removeAttribute(PARAM_SESSION_ID_CONTACT);

        resp.sendRedirect(req.getContextPath() + URL_REDIRECT_SUCCESS);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + URL_REDIRECT_FAIL);
    }
}
