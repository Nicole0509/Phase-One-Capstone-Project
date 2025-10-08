package MenuPackage;

import java.util.Scanner;

public class MainMenu {
    Scanner scanner = new Scanner(System.in);

    public int menu() {
        System.out.println("\n=== Student Management System ===");

        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Manage Instructors");
        System.out.println("4. Manage Enrollments");
        System.out.println("5. Assign Course Instructors");
        System.out.println("6. Exit\n");

        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }
}
