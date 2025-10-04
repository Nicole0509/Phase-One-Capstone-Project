package StudentPackage;

import java.util.Date;

public class Graduates extends Student {

    private int yearOfStudy;

    public Graduates(String names, String email, String phoneNumber, Date dateOfBirth, String address, int yearOfStudy) {
        super(names,email,phoneNumber,dateOfBirth,address);
        this.yearOfStudy = yearOfStudy;
    }

    @Override
    public double calculateGPA() {
        return 0;
    }

    @Override
    public String toString() {
        return "Student Details: \n\n" +
                "Names: " + getNames() + "\n" +
                "Email: " + getEmail() + "\n" +
                "Phone Number: " + getPhoneNumber() + "\n" +
                "Date of Birth: " + getDateOfBirth() + "\n" +
                "Address: " + getAddress()   + "\n" +
                "Year Of Study: " + yearOfStudy+ "\n" ;
    }
}
