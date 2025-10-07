package org.example;

import CRUD_Implemantation.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {

    private static final String URL = System.getenv("DB_URL");
    private static final String USERNAME = System.getenv("DB_USERNAME");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        if (connection != null) {
//            Database connection established


//            StudentImplementation student =new StudentImplementation(connection,"Angela","angela@gamil.com","+2507276098", Date.valueOf(LocalDate.now()),"Kimironko");
//            student.viewAll();

//            CourseImplementation course = new CourseImplementation(connection,"OS1", "Operating Systems", 10);
//            System.out.println(course.delete(32));

            InstructorImplementation instructor = new InstructorImplementation(connection,"Alice","alice@gamil.com","+2507242342", "Senior Lecturer");

            connection.close();

        } else  {
            System.out.println("Database connection could not be established");
        }

    }
}