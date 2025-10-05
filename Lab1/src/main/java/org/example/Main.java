package org.example;

import InstructorPackage.Instructor;
import StudentPackage.Graduates;
import StudentPackage.Student;
import StudentPackage.UnderGraduates;


import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        double[] marks = {23,53,65};

        System.out.println("University Student Management System!");
//        Student undergraduate =new UnderGraduates("Ada","ADA@gamil.com","+2507282938", Date.valueOf(LocalDate.now()),"Kimironko",2);
//        System.out.println(undergraduate.toString());
//        System.out.println("GPA: " + undergraduate.calculateGPA(marks));
//
//        Student graduate =new Graduates("Lisa","ADA@gamil.com","+2507282938", Date.valueOf(LocalDate.now()),"Kimironko","Computer Science");
//        System.out.println(graduate.toString());
//        graduate.setAddress("Kayonza");
//        graduate.setEmail("lisa@gmail.com");
//        System.out.println(graduate.toString());
//        System.out.println("GPA: " + graduate.calculateGPA(marks));

        Instructor instructor = new Instructor("Linda","linda@gamil.com","+2507282938", "Teacher Asssistant");
        System.out.println(instructor.getNames());
        System.out.println(instructor.getPosition());
        instructor.setPosition("Senior Lecturer");
        System.out.println(instructor.getPosition());

    }
}