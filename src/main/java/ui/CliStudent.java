package ui;

import com.mysql.cj.BindValue;
import com.mysql.cj.x.protobuf.MysqlxCursor;
import dataaccess.DatabaseException;
import dataaccess.MyStudentRepository;
import domain.Course;
import domain.CourseType;
import domain.InvalidValueException;
import domain.Student;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.sql.Date;

public class CliStudent {

    Scanner scan;
    MyStudentRepository repo;

    public CliStudent(MyStudentRepository repo)
    {
        this.scan = new Scanner(System.in);
        this.repo = repo;
    }

    public void start()
    {
        String input = "-";
        while(!input.equals("x"))
        {
            showMenue();
            input = scan.nextLine();
            switch (input){
                case "1":
                    findStudentbyId();
                    break;
                case "2":
findStudentByVorname();
                    break;
                case "3":
findStudentByNachname();
                    break;
                case "x":
                    System.out.println("Auf Wiedersehen");
                    break;
                default:
                    inputError();
                    break;
            }
        }
        scan.close();
    }

    private void findStudentByVorname() {
        System.out.println("Geben Sie einen Vornamen an!");
        String searchString = scan.nextLine();
        List<Student> studentList;
        try {
            studentList = repo.findAllStudentsByVorname(searchString);
            for(Student student : studentList)
            {
                System.out.println(student);
            }
        } catch (DatabaseException databaseException){
            System.out.println("Datenbankfehler bei der Studentensuche: " + databaseException.getMessage());
        } catch (Exception exception){
            System.out.println("Unbekannter Fehler bei der Studentensuche: " + exception.getMessage());
        }
    }

    private void findStudentByNachname() {
        System.out.println("Geben Sie einen Nachnamen an!");
        String searchString = scan.nextLine();
        List<Student> studentList;
        try {
            studentList = repo.findAllStudentsByNachname(searchString);
            for(Student student : studentList)
            {
                System.out.println(student);
            }
        } catch (DatabaseException databaseException){
            System.out.println("Datenbankfehler bei der Studentensuche: " + databaseException.getMessage());
        } catch (Exception exception){
            System.out.println("Unbekannter Fehler bei der Studentensuche: " + exception.getMessage());
        }
    }

    private void findStudentbyId() {
        System.out.println("Geben Sie eine ID an!");
        String searchString = scan.nextLine();
        int id;
        try {
            id = Integer.parseInt(searchString);
        } catch (NumberFormatException e) {
            System.out.println("Ungültige Eingabe. Bitte eine numerische ID eingeben.");
            return;
        }
        List<Student> studentList;
        try {
            studentList = repo.findAllStudentsById(String.valueOf(id));
            for(Student student : studentList) {
                System.out.println(student);
            }
        } catch (DatabaseException databaseException){
            System.out.println("Datenbankfehler bei der Studentensuche: " + databaseException.getMessage());
        } catch (Exception exception){
            System.out.println("Unbekannter Fehler bei der Studentensuche: " + exception.getMessage());
        }
    }


    private void showMenue()
    {
        System.out.println("-----------------------------------------------------KURSMANAGEMENT---------------------------");
        System.out.println("(1) Durch ID suchen \t (2) Durch Vorname suchen \t" + "(3) Durch Nachname suchen");
        System.out.println("(x) ENDE");
    }

    private void inputError()
    {
        System.out.println("Bitte nur die Zahlen der Menüauswahl eingeben!");
    }
}
