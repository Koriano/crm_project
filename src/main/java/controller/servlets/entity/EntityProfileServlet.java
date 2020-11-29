package controller.servlets.entity;

import controller.DAO.EntityDAO;
import model.Entity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EntityProfileServlet extends HttpServlet {
    private static final String ATT_ENTITY = "entity";

    private static final String VIEW = "/WEB-INF/readonly/entityProfile.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Initialize entity DAO
        EntityDAO entityDAO = EntityDAO.getInstance();
//        Entity entity = entityDAO.getEntityByName(req.getParameter("entity_name"));

        // Set entity as request attribute
//        req.setAttribute(ATT_ENTITY, entity);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {}
}
