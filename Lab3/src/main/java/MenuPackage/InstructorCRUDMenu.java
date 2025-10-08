package MenuPackage;

import CRUD_Implemantation.InstructorImplementation;

import java.sql.Connection;
import java.util.Scanner;

public class InstructorCRUDMenu {
    Connection connection;
    Scanner scanner = new Scanner(System.in);
    private final InstructorImplementation instructorImplementation;

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
        switch (choice) {
            case 1:
                System.out.println("Create Instructor");
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
}
