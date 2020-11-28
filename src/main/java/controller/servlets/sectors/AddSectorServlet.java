package controller.servlets.sectors;

import controller.DAO.ContactDAO;
import controller.DAO.SectorDAO;
import model.Contact;
import model.Sector;
import model.forms.SectorForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AddSectorServlet extends HttpServlet {
    private static final String ATT_CONTACTS = "contacts";
    private static final String ATT_SECTOR = "sector";
    private static final String ATT_FORM = "form";

    private static final String VIEW = "/WEB-INF/admin/addSector.jsp";
    private static final String URL_REDIRECT = "/sectors";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.setRequestAttributes(req);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get sectorDAO instance
        SectorDAO sectorDAO = SectorDAO.getInstance();

        // Create form and sector
        SectorForm form = new SectorForm();
        Sector sector = form.createSector(req);

        // Get result and redirect or forward
        HashMap<String, String> errors = form.getErrors();

        if(!errors.isEmpty()){
            // Set attributes request
            this.setRequestAttributes(req);
            req.setAttribute(ATT_SECTOR, sector);
            req.setAttribute(ATT_FORM, form);

            this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
        } else {
            sectorDAO.saveSector(sector);

            resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
        }
    }

    private void setRequestAttributes(HttpServletRequest req){
        // Get every contact and set them as attribute
        ContactDAO contactDAO = ContactDAO.getInstance();
        ArrayList<Contact> contacts = contactDAO.getAllContacts();

        req.setAttribute(ATT_CONTACTS, contacts);
    }
}
