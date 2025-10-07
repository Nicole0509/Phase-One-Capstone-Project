package CRUD_Implemantation;

import CRUD_Interface.CrudInterface;
import models.Course;
import models.Enrollment;
import models.Student;

import java.sql.Connection;
import java.util.Date;

public class EnrollmentImplementation extends Enrollment implements CrudInterface {
    private final Connection connection;
    String query;

    public EnrollmentImplementation(Connection connection, Student student, Course course, Date enrollmentDate, String completionStatus, double grade) {
        super(student, course, enrollmentDate, completionStatus, grade);
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
