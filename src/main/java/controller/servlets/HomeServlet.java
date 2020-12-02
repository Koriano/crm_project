package controller.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A servlet handling the home page view
 *
 * @author Alexandre HAMON
 */
public class HomeServlet extends HttpServlet {
    /**
     * View file
     */
    private static final String VIEW = "/WEB-INF/readonly/home.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.forward(req, resp);
    }

    /**
     * Method used to display the home page view
     *
     * @param req the request to forward
     * @param resp the response to be displayed
     * @throws ServletException
     * @throws IOException
     */
    private void forward(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }
}
