package org.example;

import SuperPackage.Person;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("University Student Management System!");
        Person person =new Person("Ada","ADA@gamil.com","+2507282938");

        System.out.println(person.getNames());
        System.out.println(person.getEmail());
        System.out.println(person.getPhone());
    }
}