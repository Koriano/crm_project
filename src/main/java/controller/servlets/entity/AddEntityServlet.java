package controller.servlets.entity;

import controller.DAO.EntityDAO;
import controller.DAO.EntityTypeDAO;
import model.Entity;
import model.forms.EntityForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AddEntityServlet extends HttpServlet {
    private static final String PARAM_SESSION_DOUBLE = "double";
    private static final String PARAM_SESSION_NAME = "name";
    private static final String PARAM_SESSION_TYPE = "type";

    private static final String VIEW = "/WEB-INF/alimentation/addEntity.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // reset session attributes
        HttpSession session = req.getSession();
        session.removeAttribute(PARAM_SESSION_DOUBLE);
        session.removeAttribute(PARAM_SESSION_NAME);
        session.removeAttribute(PARAM_SESSION_TYPE);

        // Set entity type list
        this.setFormAttributes(req);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get entityDAO instance
        EntityDAO entityDAO = EntityDAO.getInstance();

        // Get session attributes
        HttpSession session = req.getSession();
        String is_double_form = (String) session.getAttribute(PARAM_SESSION_DOUBLE);
        String old_name = (String) session.getAttribute(PARAM_SESSION_NAME);
        String old_type = (String) session.getAttribute(PARAM_SESSION_TYPE);

        // Create form object and create entity
        EntityForm form = new EntityForm();
        Entity updated_entity = form.createEntity(req);

        // Get errors map
        HashMap<String, String> errors = form.getErrors();

        // if no form errors, save entity
        if(errors.isEmpty()){
            if("true".equals(is_double_form)) {
                if (updated_entity.getName().toLowerCase().equals(old_name.toLowerCase()) && updated_entity.getType().toLowerCase().equals(old_type.toLowerCase())){
                    // If no error, create the entity and redirect to the research page
                    if (req.getParameter("newType") != null && !req.getParameter("newType").trim().isEmpty()) {
                        EntityTypeDAO.getInstance().saveEntityType(req.getParameter("newType"));
                    }
                    entityDAO.saveEntity(updated_entity);
                    resp.sendRedirect(req.getContextPath() + "/research");
                } else if (!form.isDouble()){
                    // If no error, create the entity and redirect to the research page
                    if (req.getParameter("newType") != null && !req.getParameter("newType").trim().isEmpty()) {
                        EntityTypeDAO.getInstance().saveEntityType(req.getParameter("newType"));
                    }
                    entityDAO.saveEntity(updated_entity);
                    resp.sendRedirect(req.getContextPath() + "/research");
                } else {
                    // Set attributes to request
                    this.setFormAttributes(req);
                    req.setAttribute("entity", updated_entity);
                    req.setAttribute("form", form);
                    req.setAttribute("newType", req.getParameter("newType"));
                    session.setAttribute(PARAM_SESSION_NAME, updated_entity.getName());
                    session.setAttribute(PARAM_SESSION_TYPE, updated_entity.getType());

                    this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
                }
            } else if (!form.isDouble()){
                // If no error, create the entity and redirect to the research page
                if (req.getParameter("newType") != null && !req.getParameter("newType").trim().isEmpty()) {
                    EntityTypeDAO.getInstance().saveEntityType(req.getParameter("newType"));
                }
                entityDAO.saveEntity(updated_entity);
                resp.sendRedirect(req.getContextPath() + "/research");
            } else {
                // Set attributes to request
                this.setFormAttributes(req);
                req.setAttribute("entity", updated_entity);
                req.setAttribute("form", form);
                req.setAttribute("newType", req.getParameter("newType"));
                session.setAttribute(PARAM_SESSION_NAME, updated_entity.getName());
                session.setAttribute(PARAM_SESSION_TYPE, updated_entity.getType());

                this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
            }
        }
    }

    private void setFormAttributes(HttpServletRequest req){
        // Get entity DAO instance and get all types
        EntityDAO entityDAO = EntityDAO.getInstance();
        ArrayList<String> types = entityDAO.getAllTypes();
        types.add("Nouveau type entite...");

        req.setAttribute("types", types);
    }
}
