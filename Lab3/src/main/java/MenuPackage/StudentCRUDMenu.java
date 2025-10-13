package MenuPackage;

import CRUD_Implemantation.StudentImplementation;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class StudentCRUDMenu {
    Connection connection;
    Scanner scanner = new Scanner(System.in);
    private final StudentImplementation studentImplementation;

    public StudentCRUDMenu(Connection connection) {
        this.connection = connection;
        this.studentImplementation = new StudentImplementation(connection);
    }

    public int studentMenu() {
        System.out.println("\n=== Student CRUD Menu  ===");

        System.out.println("1. Create Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("0. Back\n");

        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    public void switchStudentMenuOptions(int choice) {
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("\nCreate A Student");
                createStudent();
                break;
            case 2:
                viewAllStudents();
                break;
            case 3:
                System.out.println("\nUpdate Student");
                updateStudent();
                break;
            case 4:
                System.out.println("\nDelete Student");
                deleteStudent();
                break;
            case 0:
                System.out.println("Back to Main Menu");
                return;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    private void viewAllStudents() {
        System.out.println("\n");
        studentImplementation.viewAll();
    }


    private void createStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        Date dateOfBirth = Date.valueOf(LocalDate.now());

        StudentImplementation student = new StudentImplementation(connection, name, email, phone, dateOfBirth, address);
        student.create();
    }

    private void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // clear buffer

        System.out.print("Enter new Name (leave blank to keep current): ");
        String newName = scanner.nextLine();

        System.out.print("Enter new Email (leave blank to keep current): ");
        String newEmail = scanner.nextLine();

        System.out.print("Enter new Phone Number (leave blank to keep current): ");
        String newPhone = scanner.nextLine();

        System.out.print("Enter new Address (leave blank to keep current): ");
        String newAddress = scanner.nextLine();

        System.out.print("Enter new Date of Birth (yyyy-mm-dd) (leave blank to keep current): ");
        String dobStr = scanner.nextLine();
        Date newDob = dobStr.isBlank() ? null : Date.valueOf(dobStr);

        // Call the update method in implementation class
        String result = studentImplementation.update(id, newName, newEmail, newPhone, newDob, newAddress);
        System.out.println(result);
    }


    private void deleteStudent() {

        System.out.print("Enter ID of student to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // clear buffer

        String result = studentImplementation.delete(id);
        System.out.println(result);
    }
}
