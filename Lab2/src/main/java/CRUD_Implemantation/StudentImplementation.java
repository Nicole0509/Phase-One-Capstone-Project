package CRUD_Implemantation;

import CRUD_Interface.CrudInterface;
import SuperPackage.CollectionManager;
import models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentImplementation extends Student implements CrudInterface {
    private final Connection connection;
    String query;

    public StudentImplementation(Connection connection) {
        super();
        this.connection = connection;
    }

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
        System.out.println("A list of all students\n");

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


    public String update(int id, String newNames, String newEmail, String newPhone, Date newDob, String newAddress) {
        // Check if student exists
        String selectQuery = "SELECT * FROM students WHERE id = ?";
        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            selectStatement.setInt(1, id);
            ResultSet resultSet = selectStatement.executeQuery();

            if (!resultSet.next()) {
                return "No student found with ID " + id;
            }

            // Fetch previous values
            String previousNames = resultSet.getString("names");
            String previousEmail = resultSet.getString("email");
            String previousPhone = resultSet.getString("phone_number");
            Date previousDob = resultSet.getDate("date_of_birth");
            String previousAddress = resultSet.getString("address");

            // If new values are null or blank, keep old values
            String finalNames = (newNames != null && !newNames.isBlank()) ? newNames : previousNames;
            String finalEmail = (newEmail != null && !newEmail.isBlank()) ? newEmail : previousEmail;
            String finalPhone = (newPhone != null && !newPhone.isBlank()) ? newPhone : previousPhone;
            Date finalDob = (newDob != null) ? newDob : previousDob;
            String finalAddress = (newAddress != null && !newAddress.isBlank()) ? newAddress : previousAddress;

            // Update query
            String query = """
                UPDATE students SET 
                    names = ?,
                    email = ?,
                    phone_number = ?,
                    date_of_birth = ?,
                    address = ?
                WHERE id = ?
                """;

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, finalNames);
                statement.setString(2, finalEmail);
                statement.setString(3, finalPhone);
                statement.setDate(4, new java.sql.Date(finalDob.getTime()));
                statement.setString(5, finalAddress);
                statement.setInt(6, id);

                int rows = statement.executeUpdate();
                return rows > 0 ? "Student with ID " + id + " updated successfully!" : "Update failed.";

            }

        } catch (SQLException e) {
            return "SQL Error: " + e.getMessage();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
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

    // Inside StudentImplementation.java
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Student student = new Student() {
                    @Override
                    public boolean isFullTime(CollectionManager collectionManager) {
                        return false;
                    }
                };
                student.setId(resultSet.getInt("id"));
                student.setNames(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                student.setPhoneNumber(resultSet.getString("phone"));
                student.setDateOfBirth(resultSet.getDate("date_of_birth"));
                student.setAddress(resultSet.getString("address"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public void createStudentFromObject(Student s) {
        try {
            String query = "INSERT INTO student (names, email, phone, date_of_birth, address) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, s.getNames());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getPhoneNumber());
            ps.setDate(4, new java.sql.Date(s.getDateOfBirth().getTime()));
            ps.setString(5, s.getAddress());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
