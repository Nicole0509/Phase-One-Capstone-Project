package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static final String URL = System.getenv("DB_URL");
    private static final String USERNAME = System.getenv("DB_USERNAME");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        if (connection != null) {
            System.out.println("Database connection established");
        } else  {
            System.out.println("Database connection could not be established");
        }

    }
}