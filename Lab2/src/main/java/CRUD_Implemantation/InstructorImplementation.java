package CRUD_Implemantation;

import CRUD_Interface.CrudInterface;
import models.Instructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InstructorImplementation extends Instructor implements CrudInterface {
    private final Connection connection;
    String query;

    public InstructorImplementation(Connection connection,String names, String email, String phoneNumber, String position){
        super(names,email,phoneNumber,position);
        this.connection = connection;
    }

    @Override
    public String create(){
        // Validating inputs
        if (getNames() == null || getNames().isBlank()) {
            return "Validation failed: 'names' cannot be empty.";
        }

        if (getEmail() == null || !getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return "Validation failed: Invalid email format.";
        }

        if (getPhoneNumber() == null || !getPhoneNumber().matches("^\\+?[0-9]{7,15}$")) {
            return "Validation failed: Invalid phone number.";
        }

        if (getPosition() == null || getPosition().isBlank()) {
            return "Validation failed: 'position/rank' cannot be empty.";
        }


        //Checking if there's no duplicate emails
        String checkQuery = "SELECT COUNT(*) FROM instructors WHERE email = ?";

        try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {

            checkStatement.setString(1, getEmail());
            ResultSet rs = checkStatement.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                return "An instructor with this email already exists";
            }
            rs.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }

        // Duplicate phone number check
        String checkPhoneQuery = "SELECT COUNT(*) FROM instructors WHERE phone_number = ?";

        try (PreparedStatement checkPhoneStmt = connection.prepareStatement(checkPhoneQuery)) {

            checkPhoneStmt.setString(1, getPhoneNumber());
            ResultSet rs = checkPhoneStmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                return "An instructor with this phone number already exists.";
            }
            rs.close();

        }  catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }

        //Creating a student record in the DB
        query = """
        INSERT INTO instructors (names, email, phone_number, position)
        VALUES (?, ?, ?, ?)
        """;

        try(PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, getNames());
            statement.setString(2, getEmail());
            statement.setString(3, getPhoneNumber());
            statement.setString(4, getPosition());

            int rows = statement.executeUpdate();

            if(rows>0){
                return "Instructor created successfully!";
            } else {
                return "No instructor inserted. Something went wrong.";
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
