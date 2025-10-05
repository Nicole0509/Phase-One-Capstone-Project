package org.example;

import StudentPackage.*;
import models.*;

import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        double[] marks = {23,53,65};

        System.out.println("University Student Management System!");

        Student undergraduate =new UnderGraduates("Ada","ADA@gamil.com","+2507282938", Date.valueOf(LocalDate.now()),"Kimironko",2);

        Student graduate =new Graduates("Lisa","ADA@gamil.com","+2507282938", Date.valueOf(LocalDate.now()),"Kimironko","Computer Science");

        Instructor instructor = new Instructor("Linda","linda@gamil.com","+2507282938", "Teacher Asssistant");

        Course course = new Course ("MAT1", "Mathematics 1", 10);

        Enrollment enrollment = new Enrollment(graduate,course,Date.valueOf(LocalDate.now()),"Not completed",3.2);
        System.out.println(enrollment);

        graduate.setNames("Anaya");

        System.out.println(enrollment);

        CourseInstructor courseInstructor = new CourseInstructor(course,instructor,1,Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.now()));

        System.out.println(courseInstructor);
    }
}