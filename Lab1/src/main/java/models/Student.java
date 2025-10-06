package models;

import SuperPackage.Person;

import java.util.Date;

import SuperPackage.CollectionManager;


public class Student extends Person{
    private Date dateOfBirth;
    private String address;
    private double gpa;

    CollectionManager manager = new CollectionManager();

    public Student(String names, String email, String phoneNumber, Date dateOfBirth, String address) {
        super(names, email, phoneNumber);
        this.setDateOfBirth(dateOfBirth);
        this.setAddress(address);
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }


    public double calculateGPA(){
        return 0;
    }
}
