package MenuPackage;

import java.util.Scanner;

public class EnrollmentCRUDMenu {
    Scanner scanner = new Scanner(System.in);

    public int enrollmentMenu() {
        System.out.println("\n=== Enrollment CRUD Menu  ===");

        System.out.println("1. Create Enrollment");
        System.out.println("2. View All Enrollments");
        System.out.println("3. Update Enrollment");
        System.out.println("4. Delete Enrollment");
        System.out.println("0. Back\n");

        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    public void switchEnrollmentMenuOptions(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Create Enrollment");
                break;
            case 2:
                System.out.println("View All Enrollments");
                break;
            case 3:
                System.out.println("Update Enrollment");
                break;
            case 4:
                System.out.println("Delete Enrollment");
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
