package controller.servlets.sectors;

import controller.DAO.SectorDAO;
import model.Sector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class SectorHomeServlet extends HttpServlet {
    private static final String ATT_SECTORS = "sectors";

    private static final String VIEW = "/WEB-INF/admin/sectorHome.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.displaySectors(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.displaySectors(req, resp);
    }

    private void displaySectors(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get every sector and set them as request attribute
        SectorDAO sectorDAO = SectorDAO.getInstance();
        ArrayList<Sector> sectors = sectorDAO.getAllSectors();

        req.setAttribute(ATT_SECTORS, sectors);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }
}
