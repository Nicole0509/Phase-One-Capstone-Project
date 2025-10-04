package StudentPackage;

import java.util.Date;

public class UnderGraduates extends Student {
    private int yearOfStudy;

    public UnderGraduates(String names, String email, String phoneNumber, Date dateOfBirth, String address, int yearOfStudy) {
        super(names,email,phoneNumber,dateOfBirth,address);
        this.setYearOfStudy(yearOfStudy);
    }

    @Override
    public double calculateGPA(double[] marks) {
        return 0;
    }

    @Override
    public String toString() {
        return "UnderGraduate Student Details: \n\n" +
                "Names: " + getNames() + "\n" +
                "Email: " + getEmail() + "\n" +
                "Phone Number: " + getPhoneNumber() + "\n" +
                "Date of Birth: " + getDateOfBirth() + "\n" +
                "Address: " + getAddress()   + "\n" +
                "Year Of Study: " + getYearOfStudy();
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }
}
