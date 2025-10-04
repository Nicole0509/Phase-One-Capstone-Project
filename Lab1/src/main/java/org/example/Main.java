package org.example;

import StudentPackage.StudentDefinition;


import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("University Student Management System!");
        StudentDefinition person =new StudentDefinition("Ada","ADA@gamil.com","+2507282938", Date.valueOf(LocalDate.now()),"Kimironko");

        System.out.println(person.getNames());
        System.out.println(person.getEmail());
        System.out.println(person.getPhoneNumber());
        System.out.println(person.getDateOfBirth());
        System.out.println(person.getAddress());
    }
}