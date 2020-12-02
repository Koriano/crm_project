package controller.servlets.io;

import com.opencsv.CSVReader;
import controller.DAO.ContactDAO;
import controller.DAO.EntityDAO;
import model.Contact;
import model.Entity;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

@MultipartConfig
public class ImportServlet extends HttpServlet {
    private static final String VIEW = "/WEB-INF/alimentation/importContacts.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ContactDAO contactDAO = ContactDAO.getInstance();
            Part filePart = req.getPart("file");
            String[] split_file_name = filePart.getSubmittedFileName().split("\\.");

            if("csv".equals(split_file_name[split_file_name.length-1])) {
                CSVReader reader = new CSVReader(new InputStreamReader(filePart.getInputStream()));
                Contact contact;
                Iterator<String[]> it = reader.iterator();
                // Skip header line
                it.next();
                while (it.hasNext()) {
                    String[] line = it.next()[0].split(";");
                    if (line.length == 7) {
                        String address = line[0];
                        String role = line[1];
                        String entite = line[2];
                        String[] mails = line[3].split("\t");
                        String[] tels = line[4].split("\t");
                        String name = line[5];
                        String surname = line[6];
                        // Check entries validity
                        if (!(name.isEmpty() || surname.isEmpty() || role.isEmpty())) {
                            ArrayList<String> roles = contactDAO.getAllRoles();

                            if (roles.contains(role)) {
                                // Set name, surname and role
                                contact = new Contact(name, surname, role, null, false);
                                // Set adress
                                contact.setAddress(address);

                                // Set entity
                                ArrayList<Entity> entities = EntityDAO.getInstance().getAllEntities();
                                for (Entity e : entities) {
                                    if (e.getName().toLowerCase().equals(entite.toLowerCase())) {
                                        contact.setEntity(e);
                                        break;
                                    }
                                }
                                // Set mails
                                for (String mail : mails) {
                                    if (!mail.isEmpty()) {
                                        contact.addMail(mail);
                                    }
                                }
                                // Set phones nb
                                for (String tel : tels) {
                                    if (!tel.isEmpty()) {
                                        contact.addPhone(tel);
                                    }
                                }
                                try {
                                    contactDAO.saveContact(contact);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
                req.setAttribute("successMessage", "Insertion OK !");
            } else {
                throw new Exception("Format de fichier non supporté");
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("successMessage", "Echec de l'insertion...");
        }

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);

    }




}
