package domain;

import java.sql.Date;

public class Student extends BaseEntity{

    private String vorname;
    private String nachname;
    private Date geburtsdatum;


    public String getVorname() {
        return vorname;
    }


    public Student(Long id, String vorname, String nachname, Date geburtsdatum) {
        super(id);
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
    }

    public Student(String vorname, String nachname, Date geburtsdatum) {
        super(null);
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
    }



    public void setVorname(String vorname) throws InvalidValueException{
        if(vorname!=null && !vorname.isEmpty()){
            this.vorname = vorname;
        }else{
            throw new InvalidValueException("Vorname muss angegeben werden!");
        }
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) throws InvalidValueException{
        if(nachname!=null && !nachname.isEmpty()){
            this.nachname = nachname;
        }else{
            throw new InvalidValueException("Nachname muss angegeben werden!");
        }
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) throws InvalidValueException {
        if (geburtsdatum!=   null){ //sehen ob Geburtsdatum null ist oder nicht
            this.geburtsdatum = geburtsdatum;
        } else {
            throw new InvalidValueException("Geburtsdatum muss angegeben werden!");
        }

    }

    @Override
    public String toString() {
        return "Student{" +
                "id'=" + this.getId() + '\'' +
                "vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", geburtsdatum=" + geburtsdatum +
                '}';
    }
}

