package models;

import SuperPackage.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Student extends Person{
    private Date dateOfBirth;
    private String address;

    private List<Enrollment> enrollments = new ArrayList<>();
    private double gpa;

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

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
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
