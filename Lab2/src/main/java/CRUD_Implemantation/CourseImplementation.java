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

    public CourseImplementation(Connection connection) {
        super();
        this.connection = connection;
    }

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
        System.out.println("A list of all courses\n");

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

    public String update(int id, String newCourseName, String newDescription, Integer newCredits) {
        // Check if course exists
        String selectQuery = "SELECT * FROM courses WHERE id = ?";
        try (PreparedStatement selectStmt = connection.prepareStatement(selectQuery)) {
            selectStmt.setInt(1, id);
            ResultSet rs = selectStmt.executeQuery();
            if (!rs.next()) return "No course found with ID " + id;

            // Get current values
            String oldName = rs.getString("name");
            String oldDescription = rs.getString("description");
            int oldCredits = rs.getInt("credits");

            // Replace only fields that are provided
            String updatedName = (newCourseName == null || newCourseName.isBlank()) ? oldName : newCourseName;
            String updatedDescription = (newDescription == null || newDescription.isBlank()) ? oldDescription : newDescription;
            int updatedCredits = (newCredits == null || newCredits <= 0) ? oldCredits : newCredits;

            query = "UPDATE courses SET name = ?, description = ?, credits = ? WHERE id = ?";
            try (PreparedStatement updateStmt = connection.prepareStatement(query)) {
                updateStmt.setString(1, updatedName);
                updateStmt.setString(2, updatedDescription);
                updateStmt.setInt(3, updatedCredits);
                updateStmt.setInt(4, id);
                int rows = updateStmt.executeUpdate();
                return rows > 0
                        ? "Course with ID " + id + " updated successfully!"
                        : "No changes were made.";
            }
        } catch (SQLException e) {
            return "Error updating course: " + e.getMessage();
        }
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
