package controller.servlets.events;

import controller.DAO.EventDAO;
import model.Event;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class EventProfileServlet extends HttpServlet {
    private static final String PARAM_EVENT_ID = "id";

    private static final String ATT_EVENT = "event";
    private static final String ATT_DATE = "date";

    private static final String VIEW = "/WEB-INF/readonly/eventProfile.jsp";
    private static final String URL_REDIRECT = "/myEvents";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.displayEvent(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.displayEvent(req, resp);
    }

    private void displayEvent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get event to display
        EventDAO eventDAO = EventDAO.getInstance();

        String event_id = req.getParameter(PARAM_EVENT_ID);

        try {
            Event event = eventDAO.getEventById(Integer.parseInt(event_id));

            if (event != null){
                // Set formatter
                SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");

                req.setAttribute(ATT_EVENT, event);
                req.setAttribute(ATT_DATE, date_format.format(event.getDate()));

                this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
            }
        } catch (Exception e){
            resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
        }
    }
}
