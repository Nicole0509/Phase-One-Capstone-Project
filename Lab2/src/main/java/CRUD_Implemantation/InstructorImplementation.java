package CRUD_Implemantation;

import CRUD_Interface.CrudInterface;
import models.Instructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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
        String query = "SELECT * FROM instructors";

        try(PreparedStatement statement = connection.prepareStatement(query)){
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id");

                //Getting values from the DB
                setNames(resultSet.getString("names"));
                setEmail(resultSet.getString("email"));
                setPhoneNumber(resultSet.getString("phone_number"));
                setPosition(resultSet.getString("position"));

                System.out.println( "ID: " + id +
                        " \t Names : " + getNames() +
                        "\t Email: " + getPhoneNumber() +
                        "\t Position/Rank : " + getPosition()
                );
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Override
    public String update(int id){

        // Variables that will be used to get user in put
        String newNames = null;
        String newEmail = null;
        String newPhone = null;
        String newPosition = null;

        // Check if a particular exists
        String selectQuery = "SELECT * FROM instructors WHERE id = ?";

        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)){

            selectStatement.setInt(1, id);
            ResultSet resultSet = selectStatement.executeQuery();

            if (!resultSet.next()) {
                return "No Instructor found with ID " + id;
            }

            //Setting new update values
            setNames(newNames);
            setEmail(newEmail);
            setPhoneNumber(newPhone);
            setPosition(newPosition);

            //Getting values from the DB
            String previousNames = resultSet.getString("names");
            String previousEmail = resultSet.getString("email");
            String previousPhone = resultSet.getString("phone_number");
            String previousPosition = resultSet.getString("position");

            //Setting new update values
            newNames = (getNames() != null) ? getNames() : previousNames;
            newEmail = (getEmail() != null) ? getEmail() : previousEmail;
            newPhone = (getPhoneNumber() != null) ? getPhoneNumber() : previousPhone;
            newPosition = (getPosition() != null) ? getPosition() : previousPosition;

        } catch (Exception e){
            return e.getMessage();
        }



        // Update an existing record in Instructors
        query = """
                UPDATE instructors SET 
                    names = ?,
                    email = ?,
                    phone_number = ?,
                    position = ?
                WHERE id = ?
                """;

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newNames);
            statement.setString(2, newEmail);
            statement.setString(3, newPhone);
            statement.setString(4, newPosition);
            statement.setInt(5, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Instructor with id: " + id + " was updated successfully!";
    }


    @Override
    public String delete(int id){
        query = """
                DELETE FROM instructors WHERE id = ?
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
