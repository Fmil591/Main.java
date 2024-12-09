package ui;

import com.mysql.cj.BindValue;
import com.mysql.cj.x.protobuf.MysqlxCursor;
import dataaccess.DatabaseException;
import dataaccess.MyCourseRepository;
import domain.Course;
import domain.CourseType;
import domain.InvalidValueException;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.sql.Date;

public class Cli {

    Scanner scan;
    MyCourseRepository repo;

    public Cli(MyCourseRepository repo)
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
                    addCourse();
                    break;
                case "2":
                    showAllCourses();
                    break;
                case "3":
                    showCourseDetails();
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

    private void addCourse() {

        String name, description;
        int hours;
        Date dateFrom, dateTo;
        CourseType courseType;

        try {

            System.out.println("Bitte alle Kursdaten angeben:");
            System.out.println("Name: ");
            name = scan.nextLine();
            if(name.equals("")) throw new IllegalArgumentException("Eingabe darf nicht leer sein!");
            System.out.println("Beschreibung: ");
            description = scan.nextLine();
            if(description.equals("")) throw new IllegalArgumentException("Eingabe darf nicht leer sein!");
            System.out.println("Stundenanzahl");
            hours = Integer.parseInt(scan.nextLine());
        } catch (IllegalArgumentException illegalArgumentException)
        {
            System.out.println("Eingabefehler: " + illegalArgumentException.getMessage());

        } catch (InvalidValueException invalidValueException)
        {
            System.out.println("Kursdaten nicht korrekt angegeben: " + invalidValueException.getMessage());
        } catch (DatabaseException databaseException){
            System.out.println("Datenbankfehler beim Einfügen: " + databaseException.getMessage());
        } catch (Exception exception){
            System.out.println("Unbekannter Fehler beim Einfügen: " + exception.getMessage());
        }

    }

    private void showCourseDetails() {
        System.out.println("Für welchen Kusr möchten Sie die Kursdetails anzeigen?");
        Long courseId = Long.parseLong(scan.nextLine());
        try {
            Optional<Course> courseOptional = repo.getById(courseId);
            if(courseOptional.isPresent()){
                System.out.println(courseOptional.get());
            } else {
                System.out.println("Kurs mit der ID " + courseId + " nicht gefunden!");
            }
        } catch (DatabaseException databaseException) {
            System.out.println("Datenbankfehler bei Kurs-Detailanzeigen " + databaseException.getMessage());
        } catch (Exception exception)
        {
            System.out.println("Unbekannter Fehler bei Kurs-Detailanzeige: " + exception.getMessage());
        }
    }

    private void showAllCourses() {
        List<Course> list = null;

        try {
            list = repo.getAll();
            if (list.size() > 0) {
                for (Course course : list) {
                    System.out.println(course);
                }
            } else {
                System.out.println("Kursliste leer!");
            }
        } catch (DatabaseException databaseException) {
            System.out.println("Datenbankfehler bei Anzeigen aller Kurse: " + databaseException.getMessage());
        } catch (Exception exception){
            System.out.println("Unbekannter Fehlerbei Anzeige aller Kurse: " + exception.getMessage());
        }
    }

    private void showMenue()
    {
        System.out.println("-----------------------------------------------------KURSMANAGEMENT---------------------------");
        System.out.println("(1) Kurs eingeben \t (2) Alle Kurse anzeigen \t" + "(3) Kursdetails anzeigen");
        System.out.println("(x) ENDE");
    }

    private void inputError()
    {
        System.out.println("Bitte nur die Zahlen der Menüauswahl eingeben!");
    }
}
