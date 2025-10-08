package MenuPackage;

import java.util.Scanner;

public class MainMenu {
    StudentCRUDMenu studentMenu = new StudentCRUDMenu();
    Scanner scanner = new Scanner(System.in);

    public int menu() {
        System.out.println("\n=== Student Management System ===");

        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Manage Instructors");
        System.out.println("4. Manage Enrollments");
        System.out.println("5. Assign Course Instructors");
        System.out.println("0. Exit\n");

        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    public void switchMenuOptions(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Manage Students");
                studentMenu.studentMenu();
                break;
            case 2:
                System.out.println("Manage Courses");
                break;
            case 3:
                System.out.println("Manage Instructors");
                break;
            case 4:
                System.out.println("Manage Enrollments");
                break;
            case 5:
                System.out.println("Assign Course Instructors");
                break;
            case 0:
                System.out.println("Exit the Application");
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }
}
