package org.example;

import StudentPackage.Graduates;
import models.*;

import java.sql.Date;
import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("University Student Management System");
        Student graduate1 =new Graduates("Lisa","ADA@gamil.com","+2507282938", Date.valueOf(LocalDate.now()),"Kimironko","Computer Science");

        System.out.println(graduate1);
        graduate1.setNames("Chantal");
        graduate1.setEmail("chantal@gamil.com");
        System.out.println(graduate1);
    }
}