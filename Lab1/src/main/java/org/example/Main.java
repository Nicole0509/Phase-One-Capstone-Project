package org.example;

import StudentPackage.Student;


import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("University Student Management System!");
        Student person =new Student("Ada","ADA@gamil.com","+2507282938", Date.valueOf(LocalDate.now()),"Kimironko");

        System.out.println(person.toString());
    }
}