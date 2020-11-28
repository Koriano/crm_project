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

public class ModifySectorServlet extends HttpServlet {
    private static final String PARAM_SECTOR = "name";

    private static final String ATT_CONTACTS = "contacts";
    private static final String ATT_SECTOR = "sector";
    private static final String ATT_FORM = "form";

    private static final String VIEW = "/WEB-INF/admin/modifySector.jsp";
    private static final String URL_REDIRECT = "/sectors";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get sector to modify
        SectorDAO sectorDAO = SectorDAO.getInstance();

        String sector_name = req.getParameter(PARAM_SECTOR);
        Sector sector = null;
        if (sectorDAO.isSectorExist(sector_name)){
            sector = new Sector(sector_name);
            sectorDAO.getContactList(sector);
        }

        // Set request attributes
        this.setRequestAttributes(req);
        req.setAttribute(ATT_SECTOR, sector);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get sector DAO
        SectorDAO sectorDAO = SectorDAO.getInstance();

        // Create modified sector
        SectorForm form = new SectorForm();
        Sector modified_sector = form.createSector(req);

        HashMap<String, String> errors = form.getErrors();

        // If no errors update and redirect
        if(errors.isEmpty()){
            sectorDAO.updateSector(modified_sector);

            resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
        }
        // If errors set request attributes and forward
        else {
            this.setRequestAttributes(req);
            req.setAttribute(ATT_SECTOR, modified_sector);
            req.setAttribute(ATT_FORM, form);

            this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
        }
    }

    private void setRequestAttributes(HttpServletRequest req){
        // Get every contact and set them as attribute
        ContactDAO contactDAO = ContactDAO.getInstance();
        ArrayList<Contact> contacts = contactDAO.getAllContacts();

        req.setAttribute(ATT_CONTACTS, contacts);
    }
}
