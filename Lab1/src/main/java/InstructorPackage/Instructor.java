package InstructorPackage;

import SuperPackage.Person;

public class Instructor extends Person {
    private String position;

    public Instructor(String names, String email, String phoneNumber, String position) {
        super(names, email, phoneNumber);
        this.setPosition(position);
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
