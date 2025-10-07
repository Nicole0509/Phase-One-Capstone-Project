package CRUD_Implemantation;

import CRUD_Interface.CrudInterface;
import SuperPackage.CollectionManager;
import models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        System.out.println("Create a new student");
        System.out.println(getNames());
        System.out.println(getEmail());

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
        System.out.println("View all students");
    }

    @Override
    public void update(int i){
        System.out.println("Update student");
    }

    @Override
    public void delete(int i){
        System.out.println("Delete student");
    }

    @Override
    public boolean isFullTime(CollectionManager collectionManager) {
        return false;
    }
}
