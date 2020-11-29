package controller.servlets.sectors;

import controller.DAO.SectorDAO;
import model.Sector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteSectorServlet extends HttpServlet {
    private static final String PARAM_SECTOR_ID = "id";

    private static final String URL_REDIRECT = "/sectors";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.deleteSector(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.deleteSector(req, resp);
    }

    private void deleteSector(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get Sector DAO instance
        SectorDAO sectorDAO = SectorDAO.getInstance();

        // Get sector and delete it
        String id = req.getParameter(PARAM_SECTOR_ID);

        try{
            Sector sector = sectorDAO.getSectorById(Integer.parseInt(id));

            if (sector != null){
                sectorDAO.deleteSector(sector);
            }
        } catch (Exception ignored){

        }

        resp.sendRedirect(req.getContextPath() + URL_REDIRECT);
    }
}
