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
                createStudent();
                break;
            case 2:
                viewAllStudents();
                break;
            case 3:
                System.out.println("Update Student");
                break;
            case 4:
                System.out.println("Delete Student");
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
        System.out.println("\n=== Create Student ===");
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
}
