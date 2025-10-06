package org.example;

import StudentPackage.*;
import SuperPackage.CollectionManager;
import models.*;

import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        double[] marks = {23,53,65};

        System.out.println("University Student Management System!");

        //Define different courses
        Course course1 = new Course ("MAT1", "Mathematics 1", 10);
        Course course2 = new Course ("OOP", "OOP in Java", 15);
        Course course3 = new Course ("CPP", "Advanced C++", 10);

        //Define different instructors
        Instructor instructor1 = new Instructor("Alice","alice@gamil.com","+2507242342", "Senior Lecturer");
        Instructor instructor2 = new Instructor("Linda","linda@gamil.com","+2507829032", "Teacher Assistant");
        Instructor instructor3 = new Instructor("Zelda","zelda@gamil.com","+2507282938", "Junior Lecturer");


        //Defining undergraduate students
        Student undergraduate1 =new UnderGraduates("Ada","ADA@gamil.com","+2507282938", Date.valueOf(LocalDate.now()),"Kimironko",2);

        //Defining graduate students
        Student graduate1 =new Graduates("Lisa","ADA@gamil.com","+2507282938", Date.valueOf(LocalDate.now()),"Kimironko","Computer Science");


        //Defining different enrollment details
        Enrollment enrollment1 = new Enrollment(graduate1,course1,Date.valueOf(LocalDate.now()),"On going",74);
        Enrollment enrollment2 = new Enrollment(graduate1,course2,Date.valueOf(LocalDate.now()),"On going",92);

//        System.out.println(enrollment1);
//        System.out.println(enrollment2);


        //Matching different course with their instructors
        CourseInstructor courseInstructor1 = new CourseInstructor(course1,instructor1,1,Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.now()));
        CourseInstructor courseInstructor2 = new CourseInstructor(course2,instructor1,2,Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.now()));
        CourseInstructor courseInstructor3 = new CourseInstructor(course1,instructor3,2,Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.now()));


//        System.out.println(courseInstructor1);

        CollectionManager  collectionManager = new CollectionManager();

        // Adding a new student in the Student Set

//        System.out.println(collectionManager.addStudent(graduate1));
//        System.out.println(collectionManager.addStudent(undergraduate1));

        // Adding a new course in the course Set

//        System.out.println(collectionManager.addCourse(course1));
//        System.out.println(collectionManager.addCourse(course3));
//        System.out.println(collectionManager.addCourse(course2));

        // Adding a new instructor in the instructor Set

//        System.out.println(collectionManager.addInstructor(instructor3));
//        System.out.println(collectionManager.addInstructor(instructor1));
//        System.out.println(collectionManager.addInstructor(instructor2));


        //Getting all courses a particular student is enrolled in

//        collectionManager.enrollStudent(enrollment1);
//        collectionManager.enrollStudent(enrollment2);
//        collectionManager.enrollStudent(new Enrollment(undergraduate1,course2,Date.valueOf(LocalDate.now()),"On going",92));
//        System.out.println(collectionManager.getStudentEnrollments(undergraduate1));

        //Getting the GPA of 2 different students

//        System.out.println("Student: " + graduate1.getNames()+ " got a GPA of " +graduate1.calculateGPA(collectionManager));
//        System.out.println("Student: " + undergraduate1.getNames()+ " got a GPA of " +undergraduate1.calculateGPA(collectionManager));

        //Getting all courses a particular instructor teaches

        collectionManager.addCourseInstructor(courseInstructor1);
        collectionManager.addCourseInstructor(courseInstructor2);
        collectionManager.addCourseInstructor(courseInstructor3);
//        System.out.println(collectionManager.getCourseInstructors(instructor1));

        //Getting all instructors that teach a particular course

        System.out.println(collectionManager.getCoursesInstructed(course1));

    }
}