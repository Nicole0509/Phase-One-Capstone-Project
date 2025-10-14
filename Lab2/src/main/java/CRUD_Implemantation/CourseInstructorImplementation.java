package CRUD_Implemantation;

import CRUD_Interface.CrudInterface;
import models.Course;
import models.CourseInstructor;
import models.Instructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CourseInstructorImplementation extends CourseInstructor implements CrudInterface {
    private final Connection connection;
    String query;

    public CourseInstructorImplementation(Connection connection) {
        super();
        this.connection = connection;
    }

    public CourseInstructorImplementation(Connection connection,Course course, Instructor instructor, int term, Date startDate, Date endDate){
        super(course, instructor, term, startDate, endDate);
        this.connection = connection;
    }

    @Override
    public String create(){
        // Validating inputs

        if (getCourse() == null || getCourse().getCourseName() == null || getCourse().getCourseName().isBlank()) {
            return "Validation failed: 'course information' cannot be empty.";
        }

        if (getInstructor() == null || getInstructor().getNames() == null || getInstructor().getNames().isBlank()) {
            return "Validation failed: 'instructor information' cannot be empty.";
        }

        if (getTerm() < 0) {
            return "Validation failed: 'term' must not be less than 0.";
        }

        if (getStartDate() == null) {
            return "Validation failed: Start Date cannot be null.";
        }

        if (getEndDate() == null) {
            return "Validation failed: End Date cannot be null.";
        }

//        Fetch student_id from DB using student name
        int instructorId = -1;
        String instructorQuery = "SELECT id FROM instructors WHERE names = ?";
        try (PreparedStatement instructorStatement = connection.prepareStatement(instructorQuery)) {
            instructorStatement.setString(1, getInstructor().getNames());
            ResultSet resultSet = instructorStatement.executeQuery();
            if (resultSet.next()) {
                instructorId = resultSet.getInt("id");
            }
            resultSet.close();
        }  catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }

        if (instructorId == -1) {
            return "Error: Instructor not found in database.";
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
        INSERT INTO course_instructor ( course_id, instructor_id, term, start_date, end_date)
        VALUES (?, ?, ?, ?,?)
        """;

        try(PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1,courseId );
            statement.setInt(2,instructorId );
            statement.setInt(3,getTerm() );
            statement.setDate(4, new java.sql.Date(getStartDate().getTime()));
            statement.setDate(5, new java.sql.Date(getEndDate().getTime()));

            int rows = statement.executeUpdate();

            statement.close();

            if(rows > 0){
                return "Course Instructor created successfully!";
            } else {
                return "No Course Instructor inserted. Something went wrong.";
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
        System.out.println("A list of all course instructors\n");

        String query = "SELECT * FROM course_instructor";

        try(PreparedStatement statement = connection.prepareStatement(query)){
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id");

                //Getting values from the DB
                int instructorId = resultSet.getInt("instructor_id");
                int courseId = resultSet.getInt("course_id");
                setTerm(resultSet.getInt("term"));
                setStartDate(resultSet.getDate("start_date"));
                setEndDate(resultSet.getDate("end_date"));

                System.out.println( "ID: " + id +
                        " \t Instructor id: " + instructorId +
                        "\t Course id: " + courseId +
                        "\t Term/Semester: " + getTerm() +
                        "\t Start Date: " + getStartDate() +
                        "\t End Date: " + getEndDate()
                );
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public String update(int id, int instructorId, int courseId, int term, Date startDate, Date endDate) {
        // Check if the record exists
        String selectQuery = "SELECT * FROM course_instructor WHERE id = ?";

        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            selectStatement.setInt(1, id);
            ResultSet resultSet = selectStatement.executeQuery();

            if (!resultSet.next()) {
                return "No course instructor found with ID " + id;
            }

            // Keep old values if new values are "empty" (for term, dates, IDs, 0 means keep old)
            int currentInstructorId = resultSet.getInt("instructor_id");
            int currentCourseId = resultSet.getInt("course_id");
            int currentTerm = resultSet.getInt("term");
            Date currentStartDate = resultSet.getDate("start_date");
            Date currentEndDate = resultSet.getDate("end_date");

            int finalInstructorId = instructorId > 0 ? instructorId : currentInstructorId;
            int finalCourseId = courseId > 0 ? courseId : currentCourseId;
            int finalTerm = term > 0 ? term : currentTerm;
            Date finalStartDate = startDate != null ? startDate : currentStartDate;
            Date finalEndDate = endDate != null ? endDate : currentEndDate;

            // Update record
            String updateQuery = """
                UPDATE course_instructor SET 
                    instructor_id = ?,
                    course_id = ?,
                    term = ?,
                    start_date = ?,
                    end_date = ?
                WHERE id = ?
                """;

            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                updateStatement.setInt(1, finalInstructorId);
                updateStatement.setInt(2, finalCourseId);
                updateStatement.setInt(3, finalTerm);
                updateStatement.setDate(4, new java.sql.Date(finalStartDate.getTime()));
                updateStatement.setDate(5, new java.sql.Date(finalEndDate.getTime()));
                updateStatement.setInt(6, id);

                int rows = updateStatement.executeUpdate();
                return rows > 0 ? "Course Instructor updated successfully!" : "Update failed!";
            }

        } catch (Exception e) {
            return e.getMessage();
        }
    }


    @Override
    public String delete(int id){
        query = """
                DELETE FROM course_instructor WHERE id = ?
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
