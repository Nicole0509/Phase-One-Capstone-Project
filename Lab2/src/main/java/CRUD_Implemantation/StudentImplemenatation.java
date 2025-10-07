package CRUD_Implemantation;

import CRUD_Interface.CrudInterface;
import SuperPackage.CollectionManager;
import models.Student;

import java.util.Date;

public class StudentImplemenatation extends Student implements CrudInterface {

    public StudentImplemenatation(String names, String email, String phoneNumber, Date dateOfBirth, String address) {
        super(names, email, phoneNumber, dateOfBirth, address);
    }

    @Override
    public void create(){
        System.out.println("Create a new student");
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
