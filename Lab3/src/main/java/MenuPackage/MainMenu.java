package MenuPackage;

import java.util.Scanner;

public class MainMenu {
    StudentCRUDMenu studentMenu = new StudentCRUDMenu();
    CourseCRUDMenu courseMenu = new CourseCRUDMenu();
    InstructorCRUDMenu instructorMenu = new InstructorCRUDMenu();
    EnrollmentCRUDMenu enrollmentMenu = new EnrollmentCRUDMenu();
    CourseInstructorCRUDMenu courseInstructorMenu = new CourseInstructorCRUDMenu();

    Scanner scanner = new Scanner(System.in);

    public void start(){
        int choice;
        do {
            choice = menu();
            switchMenuOptions (choice);
        } while (choice != 0);

    }

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
                studentMenu.switchStudentMenuOptions(studentMenu.studentMenu());

                break;
            case 2:
                System.out.println("Manage Courses");
                courseMenu.switchCourseMenuOptions(courseMenu.courseMenu());

                break;
            case 3:
                System.out.println("Manage Instructors");
                instructorMenu.switchInstructorMenuOptions(instructorMenu.instructorMenu());

                break;
            case 4:
                System.out.println("Manage Enrollments");
                enrollmentMenu.switchEnrollmentMenuOptions(enrollmentMenu.enrollmentMenu());

                break;
            case 5:
                System.out.println("Assign Course Instructors");
                courseInstructorMenu.switchCourseInstructorMenuOptions(courseInstructorMenu.courseInstructorMenu());

                break;
            case 0:
                System.out.println("The System says GoodBye!");
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }
}
