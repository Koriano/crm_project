package controller.servlets.events;

import controller.DAO.EventDAO;
import model.Event;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EventProfileServlet extends HttpServlet {
    private static final String VIEW = "/WEB-INF/readonly/eventProfile.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void displayEvent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get event to display
        EventDAO eventDAO = EventDAO.getInstance();
    }
}
