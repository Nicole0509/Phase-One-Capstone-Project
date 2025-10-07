package org.example;

import CRUD_Implemantation.StudentImplemenatation;

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

            StudentImplemenatation student =new StudentImplemenatation(connection,"Angela","angela@gamil.com","+2507282098", Date.valueOf(LocalDate.now()),"Kimironko");

            student.update(3);


            connection.close();

        } else  {
            System.out.println("Database connection could not be established");
        }

    }
}