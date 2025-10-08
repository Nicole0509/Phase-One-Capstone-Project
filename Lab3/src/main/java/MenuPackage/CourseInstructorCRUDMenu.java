package MenuPackage;

import java.sql.Connection;
import java.util.Scanner;

public class CourseInstructorCRUDMenu {
    Connection connection;
    Scanner scanner = new Scanner(System.in);

    public CourseInstructorCRUDMenu(Connection connection) {
        this.connection = connection;
    }

    public int courseInstructorMenu() {
        System.out.println("\n=== Course Instructor CRUD Menu  ===");

        System.out.println("1. Create Course Instructor");
        System.out.println("2. View All Course Instructors");
        System.out.println("3. Update Course Instructor");
        System.out.println("4. Delete Course Instructor");
        System.out.println("0. Back\n");

        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    public void switchCourseInstructorMenuOptions(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Create Course Instructor");
                break;
            case 2:
                System.out.println("View All Course Instructors");
                break;
            case 3:
                System.out.println("Update Course Instructor");
                break;
            case 4:
                System.out.println("Delete CCourse Instructor");
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
