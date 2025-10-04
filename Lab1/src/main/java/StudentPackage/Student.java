package StudentPackage;

import SuperPackage.Person;

import java.util.Date;


public class Student extends Person{
    private Date dateOfBirth;
    private String address;

    public Student(String names, String email, String phoneNumber, Date dateOfBirth, String address) {
        super(names, email, phoneNumber);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        System.out.println( dateOfBirth.toString() + "\t" + address);
    }
}
