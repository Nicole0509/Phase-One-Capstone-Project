package org.example;

import CRUD_Implemantation.*;
import models.CourseInstructor;
import models.Enrollment;

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


            StudentImplementation student =new StudentImplementation(connection,"Leila","leila@gamil.com","+2507476098", Date.valueOf(LocalDate.now()),"Kimironko");
//            student.viewAll();
//            System.out.println(student.create());

            CourseImplementation course = new CourseImplementation(connection,"OS1", "Operating Systems", 10);
//            System.out.println(course.delete(32));

            InstructorImplementation instructor = new InstructorImplementation(connection,"Linda","linda@gamil.com","+2507232382", "Senior Lecturer");
//            System.out.println(instructor.delete(3));
//            instructor.viewAll();
            EnrollmentImplementation enrollment = new EnrollmentImplementation(connection,student,course,Date.valueOf(LocalDate.now()),"On going",74);
//            System.out.println(enrollment.create());
//            enrollment.viewAll();

            CourseInstructorImplementation courseInstructor = new CourseInstructorImplementation(connection,course,instructor,1,Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.now()));
//            System.out.println(courseInstructor.create());
            courseInstructor.viewAll();

            connection.close();

        } else  {
            System.out.println("Database connection could not be established");
        }

    }
}