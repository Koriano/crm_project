package controller.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RootServlet extends HttpServlet {
    private static final String PARAM_SESSION_USER_ACCOUNT = "user";

    private static final String URL_REDIRECT_CONNECTED = "/home";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + URL_REDIRECT_CONNECTED);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + URL_REDIRECT_CONNECTED);
    }
}
