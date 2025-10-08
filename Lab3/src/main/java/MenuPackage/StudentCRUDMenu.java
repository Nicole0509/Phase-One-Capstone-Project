package MenuPackage;

import java.util.Scanner;

public class StudentCRUDMenu {
    Scanner scanner = new Scanner(System.in);

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
        switch (choice) {
            case 1:
                System.out.println("Create Student");
                break;
            case 2:
                System.out.println("View All Students");
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
}
