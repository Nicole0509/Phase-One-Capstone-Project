package StudentPackage;

import SuperPackage.CollectionManager;
import models.Enrollment;
import models.Student;

import java.util.Date;
import java.util.Set;

public class Graduates extends Student {

    private String underGraduateDegree;

    public Graduates(String names, String email, String phoneNumber, Date dateOfBirth, String address, String underGraduateDegree) {
        super(names,email,phoneNumber,dateOfBirth,address);
        this.setUnderGraduateDegree(underGraduateDegree);
    }

    public String getUnderGraduateDegree() {
        return underGraduateDegree;
    }

    public void setUnderGraduateDegree(String underGraduateDegree) {
        this.underGraduateDegree = underGraduateDegree;
    }

    @Override
    public String toString() {
        return "\nGraduate Student Details: \n" +
                "Names: " + getNames() + "\n" +
                "Email: " + getEmail() + "\n" +
                "Phone Number: " + getPhoneNumber() + "\n" +
                "Date of Birth: " + getDateOfBirth() + "\n" +
                "Address: " + getAddress()   + "\n" +
                "Graduate Degree Of Study: " + getUnderGraduateDegree();
    }

    @Override
    public double calculateGPA(CollectionManager manager) {
        return super.calculateGPA(manager) + 0.3; //Adding a 0.3 for the thesis
    }

    public boolean isFullTime(CollectionManager manager){
        Set<Enrollment> enrollments = manager.getStudentEnrollments(this);

        if(enrollments.isEmpty()) return false;

        double credits = 0;
        for(Enrollment enrollment : enrollments){
            credits += enrollment.getCourse().getCredits();
        }

        System.out.println("Number of Credits: " + credits);
        if(credits >= 12) return true;
         else return false;
    }
}