package controller.servlets.io;

import com.opencsv.CSVWriter;
import controller.DAO.ContactDAO;
import model.Contact;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ExportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        try {
            // Define the request to provide file data stream
            resp.setContentType("APPLICATION/OCTET-STREAM");
            resp.setHeader("Content-Disposition","attachment; filename=\"ExportContacts.csv\"");

            // Forward the response writer to the CSV builder so that each line written to the CSVbuilder is forwarded to the file under download
            CSVWriter writer = new CSVWriter(resp.getWriter(), ';', '\0', '\\', "\n");
            // Write the header (i.e. the first line) of the csv

            writer.writeNext(new String[]{"Adresse","Role","Entite","Mail","Tel","Nom","Prenom"});

            // Get all contacts from the database
            ArrayList<Contact> contacts = ContactDAO.getInstance().getAllContacts();
            for (Contact contact : contacts) {
                if (contact != null) {
                    String[] parsedContact = new String[7];
                    parsedContact[0] = contact.getAddress();
                    parsedContact[1] = contact.getRole();
                    parsedContact[2] = contact.getEntity() != null ? contact.getEntity().getName() : "";

                    // Store all mails in a tab separated string
                    String mails = "";
                    for (String mail : contact.getMailsList()) {
                        if (mail != null && !mail.isEmpty()) {
                            // Insert tab btw each mail
                            if (mails.length() > 0) {
                                mails += "\t";
                            }
                            mails += mail;
                        }
                    }
                    parsedContact[3] = mails;

                    // Same tab separation for the phone numbers
                    String tels = "";
                    for (String tel : contact.getPhonesList()) {
                        if (tel != null && !tel.isEmpty()) {
                            // Insert tab btw each phone
                            if (tels.length() > 0) {
                                tels += "\t";
                            }
                            tels += tel;
                        }
                    }
                    parsedContact[4] = tels;
                    parsedContact[5] = contact.getName();
                    parsedContact[6] = contact.getSurname();
                    writer.writeNext(parsedContact);
                }
            }

            writer.close();


        }
        catch(Exception e)
        {
            System.err.println("Error while downloading csv !");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {}
}
