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

    public EnrollmentImplementation(Connection connection) {
        super();
        this.connection = connection;
    }

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
        System.out.println("A list of all enrollments\n");

        String query = "SELECT * FROM enrollments";

        try(PreparedStatement statement = connection.prepareStatement(query)){
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id");

                //Getting values from the DB
                int studentId = resultSet.getInt("student_id");
                int courseId = resultSet.getInt("course_id");

                setEnrollmentDate(resultSet.getDate("enrollment_date"));
                setCompletionStatus(resultSet.getString("completion_status"));

                System.out.println( "ID: " + id +
                        " \t Student id: " + studentId +
                        "\t Course id: " + courseId +
                        "\t Enrollment Date: " + getEnrollmentDate() +
                        "\t Completion Status: " + getCompletionStatus()
                );
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String update(int id){

        // Variables that will be used to get user in put
        int newStudentId = 3;
        int newCourseId = 1;
        Date newEnrollmentDate = null;
        String newCompletionStatus = null;

        // Check if a particular exists
        String selectQuery = "SELECT * FROM enrollments WHERE id = ?";

        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)){

            selectStatement.setInt(1, id);
            ResultSet resultSet = selectStatement.executeQuery();

            if (!resultSet.next()) {
                return "No enrollments found with ID " + id;
            }

            //Setting new update values
            setEnrollmentDate(newEnrollmentDate);
            setCompletionStatus(newCompletionStatus);

            //Getting values from the DB
            Date previousEnrollmentDate = resultSet.getDate("enrollment_date");
            String previousCompletionStatus = resultSet.getString("completion_status");

            //Setting new update values
            newEnrollmentDate = (getEnrollmentDate() != null) ? getEnrollmentDate() : previousEnrollmentDate;
            newCompletionStatus = (getCompletionStatus() != null) ? getCompletionStatus() : previousCompletionStatus;

        } catch (Exception e){
            return e.getMessage();
        }

        // Update an existing record in enrollment
        query = """
                UPDATE enrollments SET 
                    student_id = ?,
                    course_id = ?,
                    enrollment_date = ?,
                    completion_status = ?::completionstatus
                WHERE id = ?
                """;

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, newStudentId);
            statement.setInt(2, newCourseId);
            statement.setDate(3, new java.sql.Date(newEnrollmentDate.getTime()));
            statement.setString(4, newCompletionStatus);
            statement.setInt(5, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Enrollment with id: " + id + " was updated successfully!";
    }

    @Override
    public String delete(int id){
        query = """
                DELETE FROM enrollments WHERE id = ?
        """;

        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);
            int rows = statement.executeUpdate();

            if (rows>0){
                return "Record with id " + id + " was successfully deleted!" ;
            } else {
                return "Found no record with id " + id;
            }
        } catch (SQLException e) {
            return  e.getMessage();
        }
    }

}
