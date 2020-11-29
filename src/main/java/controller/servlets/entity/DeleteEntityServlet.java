package controller.servlets.entity;

import controller.DAO.EntityDAO;
import model.Entity;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteEntityServlet extends HttpServlet {

    private static final String URL_REDIRECT_SUCCESS = "/research";
    private static final String URL_REDIRECT_FAIL = "/research/entityProfile";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Get entityDAO
        EntityDAO entityDAO = EntityDAO.getInstance();
        Entity entity = entityDAO.getEntityByName(req.getParameter("entity_name"));

        // Delete entity from DB
        entityDAO.deleteEntity(entity);

        resp.sendRedirect(req.getContextPath() + URL_REDIRECT_SUCCESS);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath() + URL_REDIRECT_FAIL);
    }
}
