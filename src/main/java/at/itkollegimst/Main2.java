package at.itkollegimst;

import dataaccess.MySqlCourseRepository;
import dataaccess.MySqlStudentRepository;
import ui.Cli;
import ui.CliStudent;

import java.sql.SQLException;

public class Main2 {



    public static void main(String[] args) {
        try {
    CliStudent myCli  = new CliStudent(new MySqlStudentRepository());
            myCli.start();
    } catch (SQLException e) {
            System.out.println("Datenbankfehler: " + e.getMessage() + "SQL State: " +e.getSQLState());
    } catch (ClassNotFoundException e) {
            System.out.println("Datenbankfehler: " + e.getMessage());

        }

}
    }
