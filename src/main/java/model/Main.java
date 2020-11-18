package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String args[]){


        // 5 contacts created
        Contact contact1 = new Contact("Renault", "Giles", "Administrateur reseau", null, false);
        Contact contact2 = new Contact("Racinne-Divet", "Mathis", "Responsable de spécialité", null, true);
        Contact contact3 = new Contact("Hamon", "Alexandre", "Responsable alternance", contact2, false);
        Contact contact4 = new Contact("Schnelzauer", "Margaux", "Responsable communication", contact1, true);


        // 2 sectors created
        Sector sector1 = new Sector("sector1");
        Sector sector2 = new Sector("sector2");

        sector1.addContact(contact1);
        sector1.addContact(contact2);
        sector1.addContact(contact3);
        sector1.addContact(contact4);

        sector2.addContact(contact1);
        sector2.addContact(contact2);
        sector2.addContact(contact3);

        ArrayList<Sector> sectorsList1 = new ArrayList<Sector>();
        sectorsList1.add(sector1);
        sectorsList1.add(sector2);

        ArrayList<Sector> sectorsList2 = new ArrayList<Sector>();
        sectorsList1.add(sector2);

        // 3 accounts created
        Account account1 = new Account("user1", "1234", "Gurvan","admin", contact1, sectorsList2);
        Account account2 = new Account("user2", "1234", "Mathis","alimentation CRM", contact2, sectorsList1);
        Account account3 = new Account("user3", "1234", "Margaux","alimentation CRM", contact4, sectorsList1);

        // 2 entities created
        Entity ensibs = new Entity("ENSIBS", "123456789", "Ecole");
        Entity iut = new Entity("IUT", "987654321", "IUT");

        contact1.setEntity(ensibs);
        contact2.setEntity(ensibs);
        contact4.setEntity(ensibs);

        contact3.setEntity(iut);

        // set comment
        contact1.addComment(new Comment(contact1,contact2,"bonjour"));
        contact3.addComment(new Comment(contact3,contact1,"salut"));
        contact4.addComment(new Comment(contact4,contact1,"oui"));


        // set event
        contact1.addEvent(new Event("rdv", new Date(14, Calendar.DECEMBER, 2020),contact1, "rdv" ));
        
    }
}
