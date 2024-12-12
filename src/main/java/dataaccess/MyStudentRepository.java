package dataaccess;

import domain.Course;
import domain.Student;

import java.util.List;

public interface MyStudentRepository extends BaseRepository<Course,Long>{
    List<Student> findAllStudentsById (String searchId);
    List<Student> findAllStudentsByVorname (String name);
    List<Student> findAllStudentsByNachname (String name);
}
