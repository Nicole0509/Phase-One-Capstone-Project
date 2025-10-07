package CRUD_Implemantation;

import CRUD_Interface.CrudInterface;
import models.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public String viewAll() {
        return "";
    }

    @Override
    public String update(int id) {
        return "";
    }

    public String delete(int id) {
        return "";
    }
}
