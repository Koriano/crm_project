package controller.servlets.entity;

import controller.DAO.EntityDAO;
import model.Entity;
import model.forms.EntityForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ModifyEntityServlet extends HttpServlet {
    private static final String VIEW = "/WEB-INF/alimentation/modifyEntity.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Get DAO instance
        EntityDAO entityDAO = EntityDAO.getInstance();

        String name = req.getParameter("entity_name");

        try {
            // Create create entity
            Entity entity = entityDAO.getEntityByName(name);

            // Set request attributes for the view
            req.setAttribute("entity", entity);
            this.setFormAttributes(req);

            this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);

        } catch (Exception e){
            resp.sendRedirect(req.getContextPath() + "/research");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get entityDAO instance
        EntityDAO entityDAO = EntityDAO.getInstance();

        // Create form object and create entity
        EntityForm form = new EntityForm();
        Entity updated_entity = form.createEntity(req);

        // Get errors map
        HashMap<String, String> errors = form.getErrors();

        if(errors.isEmpty()){
            // If no error, update the entity and redirect to the entity page
            entityDAO.updateEntity(updated_entity);
            resp.sendRedirect(req.getContextPath() + "/research/entityProfile?entity_name="+updated_entity.getName());
        } else {
            // Set attributes to request
            this.setFormAttributes(req);
            req.setAttribute("entity", updated_entity);
            req.setAttribute("form", form);
            this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
        }
    }

    private void setFormAttributes(HttpServletRequest req){
        // Get entity DAO instance and get all types
        EntityDAO entityDAO = EntityDAO.getInstance();
        ArrayList<String> types = entityDAO.getTypes();

        req.setAttribute("types", types);
    }
}
