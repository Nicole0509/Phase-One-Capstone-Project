package CRUD_Implemantation;

import CRUD_Interface.CrudInterface;
import models.Course;
import models.Enrollment;
import models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class EnrollmentImplementation extends Enrollment implements CrudInterface {
    private final Connection connection;
    String query;

    public EnrollmentImplementation(Connection connection, Student student, Course course, Date enrollmentDate, String completionStatus, double grade) {
        super(student, course, enrollmentDate, completionStatus, grade);
        this.connection = connection;
    }

    @Override
    public String create(){
        // Validating inputs
        if (getStudent() == null || getStudent().getNames() == null || getStudent().getNames().isBlank()) {
            return "Validation failed: 'student information' cannot be empty.";
        }

        if (getCourse() == null || getCourse().getCourseName() == null || getCourse().getCourseName().isBlank()) {
            return "Validation failed: 'course information' cannot be empty.";
        }

        if (getEnrollmentDate() == null) {
            return "Validation failed: Enrollment Date cannot be null.";
        }

        if (getCompletionStatus() == null || getCompletionStatus().isBlank()) {
            return "Validation failed: 'course description' cannot be empty.";
        }

//        Fetch student_id from DB using student name
        int studentId = -1;
        String studentQuery = "SELECT id FROM students WHERE names = ?";
        try (PreparedStatement studentStatement = connection.prepareStatement(studentQuery)) {
            studentStatement.setString(1, getStudent().getNames());
            ResultSet resultSet = studentStatement.executeQuery();
            if (resultSet.next()) {
                studentId = resultSet.getInt("id");
            }
            resultSet.close();
        }  catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }

        if (studentId == -1) {
            return "Error: Student not found in database.";
        }

//        Fetch course_id from DB using course name
        int courseId = -1;
        String courseQuery = "SELECT id FROM courses WHERE name = ?";
        try (PreparedStatement courseStatement = connection.prepareStatement(courseQuery)) {
            courseStatement.setString(1, getCourse().getCourseName());
            ResultSet resultSet = courseStatement.executeQuery();
            if (resultSet.next()) {
                courseId = resultSet.getInt("id");
            }
            resultSet.close();
        }  catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }

        if (courseId == -1) {
            return "Error: Course not found in database.";
        }

        //Creating a student record in the DB
        query = """
        INSERT INTO enrollments (student_id, course_id, enrollment_date, completion_status)
        VALUES (?, ?, ?, ?::completionstatus)
        """;

        try(PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1,studentId );
            statement.setInt(2,courseId );
            statement.setDate(3, new java.sql.Date(getEnrollmentDate().getTime()));
            statement.setString(4, getCompletionStatus());

            int rows = statement.executeUpdate();

            statement.close();

            if(rows > 0){
                return "Enrollment created successfully!";
            } else {
                return "No Enrollment inserted. Something went wrong.";
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void viewAll(){

    }

    @Override
    public String update(int id){
        return "";
    }
    @Override
    public String delete(int id){
        return "";
    }
}
