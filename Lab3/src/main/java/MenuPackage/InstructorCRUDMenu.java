package MenuPackage;

import CRUD_Implemantation.InstructorImplementation;

import java.sql.Connection;
import java.util.Scanner;

public class InstructorCRUDMenu {
    Connection connection;
    Scanner scanner = new Scanner(System.in);
    private InstructorImplementation instructorImplementation;

    public InstructorCRUDMenu(Connection connection) {
        this.connection = connection;
        this.instructorImplementation = new InstructorImplementation(connection);
    }

    public int instructorMenu() {
        System.out.println("\n=== Instructor CRUD Menu  ===");

        System.out.println("1. Create Instructor");
        System.out.println("2. View All Instructors");
        System.out.println("3. Update Instructor");
        System.out.println("4. Delete Instructor");
        System.out.println("0. Back\n");

        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    public void switchInstructorMenuOptions(int choice) {
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Create Instructor");
                createInstructor();
                break;
            case 2:
                System.out.println("View All Instructors");
                viewAllInstructors();
                break;
            case 3:
                System.out.println("Update Instructor");
                break;
            case 4:
                System.out.println("Delete Instructor");
                deleteInstructor();
                break;
            case 0:
                System.out.println("Back to Main Menu");
                return;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    private void viewAllInstructors() {
        instructorImplementation.viewAll();
    }

    private void createInstructor() {

        System.out.print("Enter instructor name: ");
        String name = scanner.nextLine();

        System.out.print("Enter instructor email: ");
        String email = scanner.nextLine();

        System.out.print("Enter instructor phone number: ");
        String phone = scanner.nextLine();

        System.out.print("Enter instructor position/title: ");
        String position = scanner.nextLine();

        InstructorImplementation instructor = new InstructorImplementation(connection, name, email, phone, position);
        String result = instructor.create();
        System.out.println(result);
    }

}
