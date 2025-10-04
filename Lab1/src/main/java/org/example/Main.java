package org.example;

import StudentPackage.Graduates;
import StudentPackage.Student;


import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("University Student Management System!");
        Student person =new Graduates("Ada","ADA@gamil.com","+2507282938", Date.valueOf(LocalDate.now()),"Kimironko",2);

        System.out.println(person.toString());
    }
}