package controller.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddContactSessionServlet extends HttpServlet {
    private static final String PARAM_ID_CONTACT = "id";

    private static final String ATT_SESSION_ID_CONTACT = "contact_id";

    private static final String URL_REDIRECT_SUCCESS = "/research/contactProfile";
    private static final String URL_REDIRECT_FAIL = "/research";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get contact id
        String id = req.getParameter(PARAM_ID_CONTACT);

        // Get HttpSession
        HttpSession session = req.getSession();

        if(this.checkId(id)){
            session.setAttribute(ATT_SESSION_ID_CONTACT, id);
            resp.sendRedirect(req.getContextPath() + URL_REDIRECT_SUCCESS);
        } else {
            resp.sendRedirect(req.getContextPath() + URL_REDIRECT_FAIL);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public boolean checkId(String id){
        return id != null && !id.isEmpty() && id.matches("^[0-9]+$");
    }
}
