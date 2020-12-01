package controller.servlets.events;

import controller.DAO.EventDAO;
import model.Account;
import model.Contact;
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

    private static final String PARAM_RESEARCH = "research";

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

        // Get research filter
        String research = req.getParameter(PARAM_RESEARCH);

        // Get user account from session
        HttpSession session = req.getSession();
        Account user = (Account) session.getAttribute(PARAM_SESSION_USER_ACCOUNT);

        // Get every events from user account
        ArrayList<Event> my_events = eventDAO.getEventsByCreator(user.getContact());
        ArrayList<Event> concerned_events = eventDAO.getEventsByContact(user.getContact());

        // If research filter given, filter events
        if (research != null){

            if (my_events.size() > 0){
                this.filterEventByResearch(my_events, research);
            }

            if (concerned_events.size() > 0){
                this.filterEventByResearch(concerned_events, research);
            }
        }

        // Set them as parameters
        req.setAttribute(ATT_MY_EVENTS, my_events);
        req.setAttribute(ATT_CONCERNED_EVENTS, concerned_events);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    private void filterEventByResearch(ArrayList<Event> event_list, String filter) {
        // No case sensitivity
        filter = filter.toLowerCase();

        int i = 0;
        Event event;

        do {
            event = event_list.get(i);

            if (!event.getName().toLowerCase().contains(filter) &&
                    !event.getType().toLowerCase().contains(filter)){

                event_list.remove(i);

            } else {
                i++;
            }

        }while (i<event_list.size());
    }
}
