
package com.pluralcamp.daw.ui.console;
import java.util.Scanner;

import com.pluralcamp.daw.entities.core.Color;
import com.pluralcamp.daw.entities.core.Event;
import com.pluralcamp.daw.persistence.daos.impl.jdbc.ColorDAOJDBCImpl;
import com.pluralcamp.daw.persistence.daos.impl.jdbc.EventDAOJDBCImpl;
import com.pluralcamp.daw.persistence.exceptions.DAOException;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Scanner stdin = new Scanner (System.in);

        ColorDAOJDBCImpl ColorDAO = new ColorDAOJDBCImpl();
        EventDAOJDBCImpl EventDAO = new EventDAOJDBCImpl();

        try {
            Color c = ColorDAO.getColorById(4);
            if (c != null) {
                System.out.println(c.toString());
            }

        } catch (DAOException ex) {
            System.out.printf("Error:: %s %n", ex.getMessage());
        }

        System.out.println("Has hecho la consulta por ID, Pulsa una tecla para continuar -----------------------------------------------------------------");
        stdin.nextLine();


        try {
            List<Color> colors = ColorDAO.getColors();
            for (Color c : colors) {
                System.out.println(c.toString());
            }
        } catch (Exception e) {
            System.out.printf("Error:: %s %n", e.getMessage());
        }

        System.out.println("Has hecho la consulta de todos los colores, Pulsa una tecla para continuar ------------------------------------------------------------");
        stdin.nextLine();

        try {
            List<Color> colors = ColorDAO.getColors(3,5);
            for (Color c : colors) {
                System.out.println(c.toString());
            }
        } catch (Exception e) {
            System.out.printf("Error:: %s %n", e.getMessage());
        }
        
        System.out.println("Has hecho la consulta de a partir de un parametro cuenta tantos segun parametro, Pulsa una tecla para continuar y buscar el Blue ---------------------------------------------------------");
        stdin.nextLine();

        try {
            List<Color> colors = ColorDAO.getColors("blue");
            for (Color c : colors) {
                System.out.println(c.toString());
            }
        } catch (Exception e) {
            System.out.printf("Error:: %s %n", e.getMessage());
        }
        
        System.out.println("Has hecho la consulta de nombre 'Blue', Pulsa una tecla para continuar ---------------------------------------------------------------");
        stdin.nextLine();

        try {
            List<Color> colors = ColorDAO.getColors("blue", 3,5);
            for (Color c : colors) {
                System.out.println(c.toString());
            }
        } catch (Exception e) {
            System.out.printf("Error:: %s %n", e.getMessage());
        }
        
        System.out.println("Has hecho la consulta de nombre 'blue', a partir de el parametro, contar segun el parametro, Pulsa una tecla para continuar ---------------------------------------------------");
        stdin.nextLine();

        

// EVENTOSS -----------------------------------------------------


        try {
            Event e = EventDAO.getEventById(4);
            if (e != null) {
                System.out.println(e.toString());
            }

        } catch (DAOException ex) {
            System.out.printf("Error:: %s %n", ex.getMessage());
        }

        System.out.println("Pulsa una tecla para continuar");
        stdin.nextLine();

        try {
            List<Event> events = EventDAO.getEvents();
            for (Event e : events) {
                System.out.println(e.toString());
            }
        } catch (DAOException ex) {
            System.out.printf("Error:: %s %n", ex.getMessage());
        }

        System.out.println("Pulsa una tecla para continuar");
        stdin.nextLine();
    }
}
