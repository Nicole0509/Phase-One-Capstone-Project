package StudentPackage;

import SuperPackage.CollectionManager;
import models.Enrollment;
import models.Student;

import java.util.Date;
import java.util.Set;

public class UnderGraduates extends Student {
    private int yearOfStudy;

    public UnderGraduates(String names, String email, String phoneNumber, Date dateOfBirth, String address, int yearOfStudy) {
        super(names,email,phoneNumber,dateOfBirth,address);
        this.setYearOfStudy(yearOfStudy);
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    @Override
    public String toString() {
        return "\nUnderGraduate Student Details: \n" +
                "Names: " + getNames() + "\n" +
                "Email: " + getEmail() + "\n" +
                "Phone Number: " + getPhoneNumber() + "\n" +
                "Date of Birth: " + getDateOfBirth() + "\n" +
                "Address: " + getAddress()   + "\n" +
                "Year Of Study: " + getYearOfStudy();
    }

    @Override
    public double calculateGPA(CollectionManager manager) {
        return super.calculateGPA(manager);
    }

    @Override
    public boolean isFullTime(CollectionManager manager){
        Set<Enrollment> enrollments = manager.getStudentEnrollments(this);

        if(enrollments.isEmpty()) return false;

        double credits = 0;
        for(Enrollment enrollment : enrollments){
            credits += enrollment.getCourse().getCredits();
        }

        System.out.println("Number of Credits: " + credits);
        if(credits >= 8) return true;
        else return false;
    }
}
