package StudentPackage;

import SuperPackage.CollectionManager;
import models.Student;

import java.util.Date;

public class Graduates extends Student {

    private String underGraduateDegree;

    public Graduates(String names, String email, String phoneNumber, Date dateOfBirth, String address, String underGraduateDegree) {
        super(names,email,phoneNumber,dateOfBirth,address);
        this.setUnderGraduateDegree(underGraduateDegree);
    }

    @Override
    public double calculateGPA(CollectionManager manager) {
        return super.calculateGPA(manager) + 0.3; //Adding a 0.3 for the thesis
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

    public String getUnderGraduateDegree() {
        return underGraduateDegree;
    }

    public void setUnderGraduateDegree(String underGraduateDegree) {
        this.underGraduateDegree = underGraduateDegree;
    }
}
