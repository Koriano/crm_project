package controller.servlets.entity;

import model.Entity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EntityProfileServlet extends HttpServlet {
    private static final String PARAM_SESSION_ENTITY_ID = "entity_id";

    private static final String ATT_ENTITY = "entity";

    private static final String VIEW = "/WEB-INF/readonly/entityProfile.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get id of entity to be displayed from session
        HttpSession session = req.getSession();
        String id = (String) session.getAttribute(PARAM_SESSION_ENTITY_ID);

        // SIMULATION A SUPPR *******************
        Entity entity = new Entity("Thales", "111111111", "Entreprise");
        entity.setAddress("21 rue du oui");
        entity.setDescription("Usine à code");
        // **************************************

        // Set entity as request attribute
        req.setAttribute(ATT_ENTITY, entity);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {}
}
