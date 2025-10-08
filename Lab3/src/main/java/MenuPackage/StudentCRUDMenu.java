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
}
