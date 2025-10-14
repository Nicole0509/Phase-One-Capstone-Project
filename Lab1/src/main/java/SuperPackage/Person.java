package SuperPackage;

public class Person {
    private int id;
    private String names;
    private String email;
    private String phoneNumber;

    public Person() {
        System.out.println();
    }

    public Person(String names, String email, String phoneNumber) {
        this.names = names;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
