package MenuPackage;

import CRUD_Implemantation.EnrollmentImplementation;
import SuperPackage.CollectionManager;
import models.Course;
import models.Student;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class EnrollmentCRUDMenu {
    Connection connection;
    Scanner scanner = new Scanner(System.in);

    public EnrollmentCRUDMenu(Connection connection) {
        this.connection = connection;
    }

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
                createEnrollment();
                break;
            case 2:
                System.out.println("View All Enrollments");
                viewAllEnrollments();
                break;
            case 3:
                System.out.println("Update Enrollment");
                updateEnrollment();
                break;
            case 4:
                System.out.println("Delete Enrollment");
                deleteEnrollment();
                break;
            case 0:
                System.out.println("Back to Main Menu");
                return;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    private void createEnrollment() {
        scanner.nextLine();

        System.out.print("Enter Student Name: ");
        String studentName = scanner.nextLine();

        System.out.print("Enter Course Name: ");
        String courseName = scanner.nextLine();

        System.out.print("Enter Completion Status (Completed/On going/Dropped out): ");
        String status = scanner.nextLine();

        // Enrollment date = today
        Date enrollmentDate = Date.valueOf(LocalDate.now());

        Student student = new Student() {
            @Override
            public boolean isFullTime(CollectionManager collectionManager) {
                return false;
            }
        };
        student.setNames(studentName);

        Course course = new Course();
        course.setCourseName(courseName);

        EnrollmentImplementation enrollment = new EnrollmentImplementation(
                connection, student, course, enrollmentDate, status, 0.0
        );

        String result = enrollment.create();
        System.out.println(result != null ? result : "Something went wrong while creating enrollment.");
    }

    private void viewAllEnrollments() {
        EnrollmentImplementation enrollment = new EnrollmentImplementation(connection);
        enrollment.viewAll();
    }

    private void updateEnrollment() {
        System.out.println("\n=== Update Enrollment ===");
        System.out.print("Enter Enrollment ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // clear buffer

        System.out.print("Enter Student Name (leave blank to keep current): ");
        String studentName = scanner.nextLine();
        Student student = null;
        if (!studentName.isBlank()) {
            student = new Student() {
                @Override
                public boolean isFullTime(CollectionManager collectionManager) {
                    return false;
                }
            };
            student.setNames(studentName);
        }

        System.out.print("Enter Course Name (leave blank to keep current): ");
        String courseName = scanner.nextLine();
        Course course = null;
        if (!courseName.isBlank()) {
            course = new Course();
            course.setCourseName(courseName);
        }

        System.out.print("Enter Completion Status (Completed/On going/Dropped out) (leave blank to keep current): ");
        String completionStatus = scanner.nextLine();

        // Optional: let the user enter a custom enrollment date or leave blank to keep current
        System.out.print("Enter Enrollment Date (yyyy-mm-dd) (leave blank to keep current): ");
        String enrollmentDateStr = scanner.nextLine();
        Date enrollmentDate = enrollmentDateStr.isBlank() ? null : Date.valueOf(enrollmentDateStr);

        // Create the implementation object with these new values
        EnrollmentImplementation enrollment = new EnrollmentImplementation(connection, student, course, enrollmentDate, completionStatus, 0.0);

        // Call the update method
        String result = enrollment.update(id);
        System.out.println(result);
    }

    private void deleteEnrollment() {
        System.out.println("\n=== Delete Enrollment ===");
        System.out.print("Enter Enrollment ID to delete: ");
        int id = scanner.nextInt();

        EnrollmentImplementation enrollment = new EnrollmentImplementation(connection);
        String result = enrollment.delete(id);
        System.out.println(result);
    }

}
