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

    public String update(int id){

        // Check if the enrollment exists
        String selectQuery = "SELECT * FROM enrollments WHERE id = ?";
        int previousStudentId = -1;
        int previousCourseId = -1;
        Date previousEnrollmentDate = null;
        String previousCompletionStatus = null;

        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            selectStatement.setInt(1, id);
            ResultSet resultSet = selectStatement.executeQuery();

            if (!resultSet.next()) {
                return "No enrollment found with ID " + id;
            }

            // Get existing DB values
            previousStudentId = resultSet.getInt("student_id");
            previousCourseId = resultSet.getInt("course_id");
            previousEnrollmentDate = resultSet.getDate("enrollment_date");
            previousCompletionStatus = resultSet.getString("completion_status");

        } catch (SQLException e) {
            return e.getMessage();
        }

        // Decide which values to update
        int newStudentId = (getStudent() != null && getStudent().getNames() != null) ? fetchStudentId(getStudent().getNames()) : previousStudentId;
        int newCourseId = (getCourse() != null && getCourse().getCourseName() != null) ? fetchCourseId(getCourse().getCourseName()) : previousCourseId;
        Date newEnrollmentDate = (getEnrollmentDate() != null) ? getEnrollmentDate() : previousEnrollmentDate;
        String newCompletionStatus = (getCompletionStatus() != null) ? getCompletionStatus() : previousCompletionStatus;

        // Update enrollment record
        String updateQuery = """
            UPDATE enrollments SET
                student_id = ?,
                course_id = ?,
                enrollment_date = ?,
                completion_status = ?::completionstatus
            WHERE id = ?
            """;

        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setInt(1, newStudentId);
            statement.setInt(2, newCourseId);
            statement.setDate(3, new java.sql.Date(newEnrollmentDate.getTime()));
            statement.setString(4, newCompletionStatus);
            statement.setInt(5, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "Enrollment with ID " + id + " updated successfully!";
    }

    // Helper methods to fetch IDs dynamically
    private int fetchStudentId(String studentName) {
        int id = -1;
        String query = "SELECT id FROM students WHERE names = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, studentName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) id = rs.getInt("id");
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return id;
    }

    private int fetchCourseId(String courseName) {
        int id = -1;
        String query = "SELECT id FROM courses WHERE name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, courseName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) id = rs.getInt("id");
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return id;
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
