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
                        " \t Student id: " + instructorId +
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

    @Override
    public String update(int id){

        // Variables that will be used to get user in put
        int newInstructorId = 4;
        int newCourseId = 1;
        int newTerm = 1;
        Date newStartDate = null;
        Date newEndDate = null;

        // Check if a particular exists
        String selectQuery = "SELECT * FROM course_instructor WHERE id = ?";

        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)){

            selectStatement.setInt(1, id);
            ResultSet resultSet = selectStatement.executeQuery();

            if (!resultSet.next()) {
                return "No course instructor found with ID " + id;
            }

            //Setting new update values
            setTerm(newTerm);
            setStartDate(newStartDate);
            setEndDate(newEndDate);

            //Getting values from the DB
            int previousTerm = resultSet.getInt("term");
            Date previousStartDate = resultSet.getDate("start_date");
            Date previousEndDate = resultSet.getDate("end_date");

            //Setting new update values
            newTerm = (getTerm() < 0) ? getTerm() : previousTerm;
            newStartDate = (getStartDate() != null) ? getStartDate() : previousStartDate;
            newEndDate = (getEndDate() != null) ? getEndDate() : previousEndDate;

        } catch (Exception e){
            return e.getMessage();
        }

        // Update an existing record in enrollment
        query = """
                UPDATE course_instructor SET 
                    instructor_id = ?,
                    course_id = ?,
                    term = ?,
                    start_date = ?,
                    end_date = ?
                WHERE id = ?
                """;

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, newInstructorId);
            statement.setInt(2, newCourseId);
            statement.setInt(3, getTerm());
            statement.setDate(4, new java.sql.Date(newStartDate.getTime()));
            statement.setDate(5, new java.sql.Date(newEndDate.getTime()));
            statement.setInt(6, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Enrollment with id: " + id + " was updated successfully!";
    }

    @Override
    public String delete(int id){
        return "";
    }
}
