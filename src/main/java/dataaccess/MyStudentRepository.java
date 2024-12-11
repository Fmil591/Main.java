package dataaccess;

import domain.Course;
import domain.Student;

import java.util.List;
import java.util.Optional;

public interface MyStudentRepository extends BaseRepository<Course,Long>{
    Optional<Student> findAllStudentsById (Long id);
    List<Student> findAllStudentsByVorname (String name);
    List<Student> findAllStudentsByNachname (String name);
}
