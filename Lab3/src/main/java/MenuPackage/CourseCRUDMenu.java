package MenuPackage;

import CRUD_Implemantation.CourseImplementation;
import CRUD_Implemantation.StudentImplementation;

import java.sql.Connection;
import java.util.Scanner;

public class CourseCRUDMenu {
    private final Connection connection;
    private final Scanner scanner = new Scanner(System.in);
    private final CourseImplementation courseImplementation;

    public CourseCRUDMenu(Connection connection) {
        this.connection = connection;
        this.courseImplementation = new CourseImplementation(connection);
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
                createCourse();
                break;
            case 2:
                viewAllCourses();
                break;
            case 3:
                System.out.println("Update Course");
                break;
            case 4:
                System.out.println("Delete Course");
                deleteCourse();
                break;
            case 0:
                System.out.println("Back to Main Menu");
                return;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    private void viewAllCourses() {
        courseImplementation.viewAll();
    }

    private void createCourse() {
        scanner.nextLine();

        System.out.print("Enter course name: ");
        String name = scanner.nextLine();

        System.out.print("Enter course description: ");
        String description = scanner.nextLine();

        System.out.print("Enter course credits: ");
        int credits = scanner.nextInt();
        scanner.nextLine(); // clear buffer

        CourseImplementation course = new CourseImplementation(connection, name, description, credits);
        String result = course.create();
        System.out.println(result);
    }

    private void deleteCourse() {

        System.out.print("Enter ID of course to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // clear buffer

        String result = courseImplementation.delete(id);
        System.out.println(result);
    }
}
