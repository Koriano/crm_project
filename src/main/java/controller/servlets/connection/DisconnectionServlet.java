package controller.servlets.connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DisconnectionServlet extends HttpServlet {
    private static final String URL_REDIRECT = "/connect";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.disconnectUser(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.disconnectUser(req, resp);
    }

    private void disconnectUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get session and invalidate it
        HttpSession session = req.getSession();
        session.invalidate();

        resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
    }
}
