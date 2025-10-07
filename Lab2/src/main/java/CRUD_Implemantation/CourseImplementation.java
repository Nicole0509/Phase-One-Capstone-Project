package CRUD_Implemantation;

import CRUD_Interface.CrudInterface;
import models.Course;

import java.sql.Connection;

public class CourseImplementation extends Course implements CrudInterface {
    private final Connection connection;
    String query;

    public CourseImplementation(Connection connection,String courseName, String description, double credits) {
        super(courseName, description, credits);
        this.connection = connection;
    }

    @Override
    public String create() {
        return "";
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
