package CRUD_Implemantation;

import CRUD_Interface.CrudInterface;
import SuperPackage.CollectionManager;
import models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class StudentImplemenatation extends Student implements CrudInterface {
    private final Connection connection;
    String query;

    public StudentImplemenatation(Connection connection, String names, String email, String phoneNumber, Date dateOfBirth, String address) {
        super(names, email, phoneNumber, dateOfBirth, address);
        this.connection = connection;
    }

    @Override
    public void create(){

        query = "INSERT INTO students (names, email, phone_number, date_of_birth, address) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, getNames());
            statement.setString(2, getEmail());
            statement.setString(3, getPhoneNumber());
            statement.setDate(4, new java.sql.Date(getDateOfBirth().getTime()));
            statement.setString(5, getAddress());
            statement.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void viewAll(){
        String query = "SELECT * FROM students";

        try(PreparedStatement statement = connection.prepareStatement(query)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                setNames(resultSet.getString("names"));
                setEmail(resultSet.getString("email"));
                setPhoneNumber(resultSet.getString("phone_number"));
                setDateOfBirth(resultSet.getDate("date_of_birth"));
                setAddress(resultSet.getString("address"));

                System.out.println("ID: " + id + " \t Names : " + getNames() + "\t Email: " + getPhoneNumber() + "\t Date of Birth: " + getDateOfBirth() + "\t Address : " + getAddress());
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id){
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

        setNames("Rose");
        setEmail("Rose@gmail.com");

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, getNames());
            statement.setString(2, getEmail());
            statement.setString(3, getPhoneNumber());
            statement.setDate(4, new java.sql.Date(getDateOfBirth().getTime()));
            statement.setString(5, getAddress());
            statement.setInt(6, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int id){
    }

    @Override
    public boolean isFullTime(CollectionManager collectionManager) {
        return true;
    }
}
