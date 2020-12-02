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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class EventsHomeServlet extends HttpServlet {
    private static final String PARAM_SESSION_USER_ACCOUNT = "user";

    private static final String PARAM_RESEARCH = "research";

    private static final String ATT_MY_EVENTS = "my_events";
    private static final String ATT_CONCERNED_EVENTS = "concerned_events";
    private static final String ATT_DATES = "dates";

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

        HashMap<Integer, String> dateTime = new HashMap<>();

        this.addDates(dateTime, my_events);
        this.addDates(dateTime, concerned_events);

        // If research filter given, filter events
        if (research != null){
            String[] filters = research.split(" ");

            if (my_events.size() > 0){
                this.filterEventByResearch(my_events, filters);
            }

            if (concerned_events.size() > 0){
                this.filterEventByResearch(concerned_events, filters);
            }
        }

        // Set them as parameters
        req.setAttribute(ATT_MY_EVENTS, my_events);
        req.setAttribute(ATT_CONCERNED_EVENTS, concerned_events);
        req.setAttribute(ATT_DATES, dateTime);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    private void addDates(HashMap<Integer, String> dateTime, ArrayList<Event> events) {
        for (Event evt: events){

            if (!dateTime.containsKey(evt.getId())){
                Date date = evt.getDate();

                // Set formatter
                SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");

                dateTime.put(evt.getId(), date_format.format(date));
            }
        }
    }

    private void filterEventByResearch(ArrayList<Event> event_list, String[] filters) {
        int i = 0;
        Event event;
        boolean is_visible;

        do {
            event = event_list.get(i);
            is_visible = false;

            for (String filter:filters){
                // No case sensitivity
                filter = filter.toLowerCase();

                if (event.getName().toLowerCase().contains(filter) ||
                        event.getType().toLowerCase().contains(filter)){

                    is_visible = true;
                } else {
                    is_visible = false;
                    break;
                }
            }

            if (!is_visible){
                event_list.remove(i);
            }
            else {
                i++;
            }

        }while (i<event_list.size());
    }
}
