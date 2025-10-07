package CRUD_Implemantation;

import CRUD_Interface.CrudInterface;
import models.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CourseImplementation extends Course implements CrudInterface {
    private final Connection connection;
    String query;

    public CourseImplementation(Connection connection,String courseName, String description, int credits) {
        super(courseName, description, credits);
        this.connection = connection;
    }

    @Override
    public String create(){
        // Validating inputs
        if (getCourseName() == null || getCourseName().isBlank()) {
            return "Validation failed: 'course name' cannot be empty.";
        }

        if (getDescription() == null || getDescription().isBlank()) {
            return "Validation failed: 'course description' cannot be empty.";
        }

        if (getCredits() < 0) {
            return "Validation failed: 'credits' must be greater than 0.";
        }

        //Creating a student record in the DB
        query = """
        INSERT INTO courses (name, description, credits)
        VALUES (?, ?, ?)
        """;

        try(PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, getCourseName());
            statement.setString(2, getDescription());
            statement.setInt(3, getCredits());

            int rows = statement.executeUpdate();

            statement.close();

            if(rows > 0){
                return "Course created successfully!";
            } else {
                return "No Course inserted. Something went wrong.";
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
        String query = "SELECT * FROM courses";

        try(PreparedStatement statement = connection.prepareStatement(query)){
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id");

                //Getting values from the DB
                setCourseName(resultSet.getString("name"));
                setDescription(resultSet.getString("description"));
                setCredits(resultSet.getInt("credits"));

                System.out.println( "ID: " + id +
                        " \t Course Name : " + getCourseName() +
                        "\t Course Description: " + getDescription() +
                        "\t Credits: " + getCredits()
                );
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String update(int id){

        // Variables that will be used to get user in put
        String newCourseNames = null;
        String newDescription = null;
        int newCredits = 0;

        // Check if a particular course exists
        String selectQuery = "SELECT * FROM courses WHERE id = ?";

        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)){

            selectStatement.setInt(1, id);
            ResultSet resultSet = selectStatement.executeQuery();

            if (!resultSet.next()) {
                return "No course found with ID " + id;
            }

            //Setting new update values
            setCourseName(newCourseNames);
            setDescription(newDescription);
            setCredits(newCredits);

            //Getting values from the DB
            String previousCourseName = resultSet.getString("name");
            String previousDescription = resultSet.getString("description");
            int previousCredits = resultSet.getInt("credits");

            //Setting new update values
            newCourseNames = (getCourseName() != null) ? getCourseName() : previousCourseName;
            newDescription = (getDescription() != null) ? getDescription() : previousDescription;
            newCredits = (previousCredits > 0) ? getCredits() : previousCredits;

        } catch (Exception e){
            return e.getMessage();
        }

        // Update an existing record in courses
        query = """
                UPDATE courses SET 
                    name = ?,
                    description = ?,
                    credits = ?
                WHERE id = ?
                """;

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newCourseNames);
            statement.setString(2, newDescription);
            statement.setInt(3, newCredits);
            statement.setInt(4, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Course with id: " + id + " was updated successfully!";
    }

    @Override
    public String delete(int id){
        query = """
                DELETE FROM courses WHERE id = ?
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
