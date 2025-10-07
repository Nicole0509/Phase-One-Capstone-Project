package CRUD_Implemantation;

import CRUD_Interface.CrudInterface;
import SuperPackage.CollectionManager;
import models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class StudentImplementation extends Student implements CrudInterface {
    private final Connection connection;
    String query;

    public StudentImplementation(Connection connection, String names, String email, String phoneNumber, Date dateOfBirth, String address) {
        super(names, email, phoneNumber, dateOfBirth, address);
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

        if (getDateOfBirth() == null) {
            return "Validation failed: Date of birth cannot be null.";
        }

        if (getAddress() == null || getAddress().isBlank()) {
            return "Validation failed: Address cannot be empty.";
        }


        //Checking if there's no duplicate emails
        String checkQuery = "SELECT COUNT(*) FROM students WHERE email = ?";

        try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {

            checkStatement.setString(1, getEmail());
            ResultSet rs = checkStatement.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                return "A student with this email already exists";
            }
            rs.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }

        // Duplicate phone number check
        String checkPhoneQuery = "SELECT COUNT(*) FROM students WHERE phone_number = ?";

        try (PreparedStatement checkPhoneStmt = connection.prepareStatement(checkPhoneQuery)) {

            checkPhoneStmt.setString(1, getPhoneNumber());
            ResultSet rs = checkPhoneStmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                return "A student with this phone number already exists.";
            }
            rs.close();

        }  catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }

        //Creating a student record in the DB
        query = """
        INSERT INTO students (names, email, phone_number, date_of_birth, address)
        VALUES (?, ?, ?, ?, ?)
        """;

        try(PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, getNames());
            statement.setString(2, getEmail());
            statement.setString(3, getPhoneNumber());
            statement.setDate(4, new java.sql.Date(getDateOfBirth().getTime()));
            statement.setString(5, getAddress());

            int rows = statement.executeUpdate();

            if(rows>0){
                return "Student created successfully!";
            } else {
                return "No student inserted. Something went wrong.";
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
        String query = "SELECT * FROM students";

        try(PreparedStatement statement = connection.prepareStatement(query)){
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id");

                //Getting values from the DB
                setNames(resultSet.getString("names"));
                setEmail(resultSet.getString("email"));
                setPhoneNumber(resultSet.getString("phone_number"));
                setDateOfBirth(resultSet.getDate("date_of_birth"));
                setAddress(resultSet.getString("address"));

                System.out.println( "ID: " + id +
                        " \t Names : " + getNames() +
                        "\t Email: " + getPhoneNumber() +
                        "\t Date of Birth: " + getDateOfBirth() +
                        "\t Address : " + getAddress()
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
        String newEmail = "angela@gmail.com";
        String newPhone = null;
        Date newDob = null;
        String newAddress = null;

        // Check if a particular exists
        String selectQuery = "SELECT * FROM students WHERE id = ?";

        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)){

            selectStatement.setInt(1, id);
            ResultSet resultSet = selectStatement.executeQuery();

            if (!resultSet.next()) {
                return "No student found with ID " + id;
            }

            //Setting new update values
            setNames(newNames);
            setEmail(newEmail);
            setPhoneNumber(newPhone);
            setDateOfBirth(newDob);
            setAddress(newAddress);

            //Getting values from the DB
            String previousNames = resultSet.getString("names");
            String previousEmail = resultSet.getString("email");
            String previousPhone = resultSet.getString("phone_number");
            Date previousDob = resultSet.getDate("date_of_birth");
            String previousAddress = resultSet.getString("address");

            //Setting new update values
            newNames = (getNames() != null) ? getNames() : previousNames;
            newEmail = (getEmail() != null) ? getEmail() : previousEmail;
            newPhone = (getPhoneNumber() != null) ? getPhoneNumber() : previousPhone;
            newDob = (getDateOfBirth() != null) ? getDateOfBirth() : previousDob;
            newAddress = (getAddress() != null) ? getAddress() : previousAddress;

        } catch (Exception e){
            return e.getMessage();
        }



        // Update an existing record in students
        System.out.println("Update student");
        query = """
                UPDATE students SET 
                    names = ?,
                    email = ?,
                    phone_number = ?,
                    date_of_birth = ?,
                    address = ?
                WHERE id = ?
                """;

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newNames);
            statement.setString(2, newEmail);
            statement.setString(3, newPhone);
            statement.setDate(4, new java.sql.Date(newDob.getTime()));
            statement.setString(5, newAddress);
            statement.setInt(6, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Student with id: " + id + " was updated successfully!";
    }

    @Override
    public String delete(int id){
        query = """
                DELETE FROM students WHERE id = ?
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

    @Override
    public boolean isFullTime(CollectionManager collectionManager) {
        return true;
    }
}
