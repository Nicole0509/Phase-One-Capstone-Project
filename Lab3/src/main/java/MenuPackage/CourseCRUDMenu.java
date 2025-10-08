package MenuPackage;

import java.sql.Connection;
import java.util.Scanner;

public class CourseCRUDMenu {
    Connection connection;
    Scanner scanner = new Scanner(System.in);

    public CourseCRUDMenu(Connection connection) {
        this.connection = connection;
    }

    public int courseMenu() {
        System.out.println("\n=== Course CRUD Menu  ===");

        System.out.println("1. Create Course");
        System.out.println("2. View All Courses");
        System.out.println("3. Update Course");
        System.out.println("4. Delete Course");
        System.out.println("0. Back\n");

        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    public void switchCourseMenuOptions(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Create Course");
                break;
            case 2:
                System.out.println("View All Courses");
                break;
            case 3:
                System.out.println("Update Course");
                break;
            case 4:
                System.out.println("Delete Course");
                break;
            case 0:
                System.out.println("Back to Main Menu");
                return;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }
}
