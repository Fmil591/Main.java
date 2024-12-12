package dataaccess;

import domain.Course;
import domain.Student;
import util.Assert;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class MySqlStudentRepository implements MyStudentRepository{

    private Connection con;

    public MySqlStudentRepository() throws SQLException, ClassNotFoundException {
        this.con = MysqlDatabaseConnection.getConnection("jdbc:mysql://localhost:3306/kurssystem","root","root");
    }

    @Override
    public List<Student> findAllStudentsById(String id) {
        Assert.notNull(id);

        {
            if(id.isEmpty()) {return List.of();}
            else {
                try {
                    String sql = "SELECT * FROM `courses` WHERE `id` = ?";
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    resultSet.next();
                    Student student = new Student(
                            resultSet.getLong("id"),
                            resultSet.getString("vorname"),
                            resultSet.getString("nachname"),
                            resultSet.getDate("geburtsdatum")
                    );
                    return List.of(student);

                } catch (SQLException sqlException){
                    throw new DatabaseException(sqlException.getMessage());
                }

            }
        }}



        private int countStudentsInDbWithId(String id){
            try {

                String countSql = "SELECT COUNT(*) FROM `student` WHERE `id`=?";
                PreparedStatement preparedStatementCount = con.prepareStatement(countSql);
                preparedStatementCount.setString(1,id);
                ResultSet resultSetCount = preparedStatementCount.executeQuery();
                resultSetCount.next();
                int studentCount = resultSetCount.getInt(1);
                return studentCount;
            } catch (SQLException sqlException){
                throw new DatabaseException(sqlException.getMessage());
            }
        }




    @Override
    public List<Student> findAllStudentsByVorname(String name) {
        Assert.notNull(name);

        {
           if(name.isEmpty()) {return List.of();}
         else {
            try {
                String sql = "SELECT * FROM `courses` WHERE `vorname` = ?";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, name);
                ResultSet resultSet = preparedStatement.executeQuery();

                resultSet.next();
                Student student = new Student(
                        resultSet.getLong("id"),
                        resultSet.getString("vorname"),
                        resultSet.getString("nachname"),
                        resultSet.getDate("geburtsdatum")
                );
                return List.of(student);

            } catch (SQLException sqlException){
                throw new DatabaseException(sqlException.getMessage());
            }

        }
    }}

    @Override
    public List<Student> findAllStudentsByNachname(String name) {
        Assert.notNull(name);

        {
            if(name.isEmpty()) {return List.of();}
            else {
                try {
                    String sql = "SELECT * FROM `courses` WHERE `nachname` = ?";
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1, name);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    resultSet.next();
                    Student student = new Student(
                            resultSet.getLong("id"),
                            resultSet.getString("vorname"),
                            resultSet.getString("nachname"),
                            resultSet.getDate("geburtsdatum")
                    );
                    return List.of(student);

                } catch (SQLException sqlException){
                    throw new DatabaseException(sqlException.getMessage());
                }

            }
        }}


    @Override
    public Optional<Course> insert(Course entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Course> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Course> getAll() {
        return List.of();
    }

    @Override
    public Optional<Course> update(Course entity) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }
}
