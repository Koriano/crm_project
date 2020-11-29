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
import java.util.ArrayList;

public class EventsHomeServlet extends HttpServlet {
    private static final String PARAM_SESSION_USER_ACCOUNT = "user";

    private static final String ATT_MY_EVENTS = "my_events";
    private static final String ATT_CONCERNED_EVENTS = "concerned_events";

    private static final String VIEW = "/WEB-INF/readonly/eventsHome.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.displayEvents(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.displayEvents(req, resp);
    }

    private void displayEvents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get eventsDAO instance
        EventDAO eventDAO = EventDAO.getInstance();

        // Get user account from session
        HttpSession session = req.getSession();
        Account user = (Account) session.getAttribute(PARAM_SESSION_USER_ACCOUNT);

        // Get every events from user account
        ArrayList<Event> my_events = eventDAO.getEventsByCreator(user.getContact());
        ArrayList<Event> concerned_events = eventDAO.getEventsByContact(user.getContact());

        // Set them as parameters
        req.setAttribute(ATT_MY_EVENTS, my_events);
        req.setAttribute(ATT_CONCERNED_EVENTS, concerned_events);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }
}
