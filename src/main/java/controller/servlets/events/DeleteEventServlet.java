package controller.servlets.events;

import controller.DAO.EventDAO;
import model.Account;
import model.Event;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteEventServlet extends HttpServlet {
    private static final String PARAM_SESSION_USER_ACCOUNT = "user";

    private static final String PARAM_EVENT_ID = "id";

    private static final String URL_REDIRECT = "/myEvents";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.deleteEvent(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.deleteEvent(req, resp);
    }

    private void deleteEvent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get eventDAO instance
        EventDAO eventDAO = EventDAO.getInstance();

        // Get user account from session
        HttpSession session = req.getSession();
        Account user = (Account) session.getAttribute(PARAM_SESSION_USER_ACCOUNT);

        // Get id parameter
        String id = req.getParameter(PARAM_EVENT_ID);

        try {
            Event event = eventDAO.getEventById(Integer.parseInt(id));

            if (event != null && user.getContact().isCreator(event)){
                eventDAO.deleteEvent(event);
            }

            resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
        } catch (Exception e){
            resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
        }
    }
}
