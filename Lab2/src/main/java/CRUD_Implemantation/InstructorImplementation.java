package CRUD_Implemantation;

import CRUD_Interface.CrudInterface;
import models.Instructor;

import java.sql.Connection;

public class InstructorImplementation extends Instructor implements CrudInterface {
    private final Connection connection;
    String query;

    public InstructorImplementation(Connection connection,String names, String email, String phoneNumber, String position){
        super(names,email,phoneNumber,position);
        this.connection = connection;
    }

    @Override
    public String create(){
        return "";
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
