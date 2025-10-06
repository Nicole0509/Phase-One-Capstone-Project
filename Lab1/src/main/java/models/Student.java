package models;

import SuperPackage.Person;

import java.util.Date;
import java.util.Set;

import SuperPackage.CollectionManager;


public class Student extends Person{
    private Date dateOfBirth;
    private String address;

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

    public double calculateGPA(CollectionManager manager) {
        Set<Enrollment> enrollments = manager.getStudentEnrollments(this);

        if(enrollments.isEmpty()) return 0;

        double sumOfPoints = 0;

        for (Enrollment enrollment : enrollments){
            sumOfPoints += convertGradeToGPA(enrollment.getGrade());
        }
        return sumOfPoints/enrollments.size();
    }

    public double convertGradeToGPA(double grade) {
        if (grade >= 90) return 4.0;
        else if (grade >= 80) return 3.5;
        else if (grade >= 70) return 3.0;
        else if (grade >= 60) return 2.5;
        else if (grade >= 50) return 2.0;
        else return 0.0;
    }
}
