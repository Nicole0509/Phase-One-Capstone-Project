package org.example;

import StudentPackage.*;
import models.*;

import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        double[] marks = {23,53,65};

//        System.out.println("University Student Management System!");
//        Student undergraduate =new UnderGraduates("Ada","ADA@gamil.com","+2507282938", Date.valueOf(LocalDate.now()),"Kimironko",2);
//        System.out.println(undergraduate.toString());
//        System.out.println("GPA: " + undergraduate.calculateGPA(marks));
//
//        Student graduate =new Graduates("Lisa","ADA@gamil.com","+2507282938", Date.valueOf(LocalDate.now()),"Kimironko","Computer Science");
//        System.out.println(graduate);
//        graduate.setAddress("Kayonza");
//        graduate.setEmail("lisa@gmail.com");
//        System.out.println(graduate);
//        System.out.println("GPA: " + graduate.calculateGPA(marks));
//
//        Instructor instructor = new Instructor("Linda","linda@gamil.com","+2507282938", "Teacher Asssistant");
//        System.out.println(instructor);
//        instructor.setPosition("Senior Lecturer");
//        System.out.println(instructor);

        Course course = new Course ("MAT1", "Mathematics 1", 10);
        System.out.println(course);
        course.setCredits(15);
        System.out.println(course);
    }
}