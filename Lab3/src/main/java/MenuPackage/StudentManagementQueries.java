package MenuPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentManagementQueries {

    private final Connection connection;
    private final Scanner scanner = new Scanner(System.in);

    public StudentManagementQueries(Connection connection) {
        this.connection = connection;
    }

    public int managementMenu() {
        System.out.println("\n=== Student Management Menu ===");

        System.out.println("1. View all courses a student is enrolled in");
        System.out.println("2. View all instructors that teach a course");
        System.out.println("3. View all courses an instructor teaches");
        System.out.println("4. View all students in a course");
        System.out.println("5. View all enrollments for a student");
        System.out.println("0. Back\n");

        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }


    public void switchManagementMenuOptions(int choice) {
        scanner.nextLine(); // Clear buffer

        switch (choice) {
            case 1 -> {
                System.out.print("Enter Student ID: ");
                int studentId = scanner.nextInt();
                scanner.nextLine();
                viewCoursesForStudent(studentId);
            }
            case 2 -> {
                System.out.print("Enter Course ID: ");
                int courseId = scanner.nextInt();
                scanner.nextLine();
                viewInstructorsForCourse(courseId);
            }
            case 3 -> {
                System.out.print("Enter Instructor ID: ");
                int instructorId = scanner.nextInt();
                scanner.nextLine();
                viewCoursesForInstructor(instructorId);
            }
            case 4 -> {
                System.out.print("Enter Course ID: ");
                int courseId = scanner.nextInt();
                scanner.nextLine();
                viewStudentsInCourse(courseId);
            }
            case 5 -> {
                System.out.print("Enter Student ID: ");
                int studentId = scanner.nextInt();
                scanner.nextLine();
                viewEnrollmentsForStudent(studentId);
            }
            case 0 -> System.out.println("Back to Main Menu");
            default -> System.out.println("Invalid choice");
        }
    }

    // 1. View all courses a student is enrolled in
    public void viewCoursesForStudent(int studentId) {
        String query = """
            SELECT c.id, c.name, c.description, c.credits
            FROM courses c
            JOIN enrollments e ON c.id = e.course_id
            WHERE e.student_id = ?
        """;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Courses for Student ID: " + studentId);
            while (rs.next()) {
                System.out.println(
                        "Course ID: " + rs.getInt("id") +
                                " | Name: " + rs.getString("name") +
                                " | Description: " + rs.getString("description") +
                                " | Credits: " + rs.getInt("credits")
                );
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // 2. View all instructors that teach a course
    public void viewInstructorsForCourse(int courseId) {
        String query = """
            SELECT i.id, i.names, i.position, ci.term, ci.start_date, ci.end_date
            FROM instructors i
            JOIN course_instructors ci ON i.id = ci.instructor_id
            WHERE ci.course_id = ?
        """;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Instructors for Course ID: " + courseId);
            while (rs.next()) {
                System.out.println(
                        "Instructor ID: " + rs.getInt("id") +
                                " | Name: " + rs.getString("names") +
                                " | Position: " + rs.getString("position") +
                                " | Term: " + rs.getInt("term") +
                                " | Start Date: " + rs.getDate("start_date") +
                                " | End Date: " + rs.getDate("end_date")
                );
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // 3. View all courses an instructor teaches
    public void viewCoursesForInstructor(int instructorId) {
        String query = """
            SELECT c.id, c.name, c.description, c.credits, ci.term, ci.start_date, ci.end_date
            FROM courses c
            JOIN course_instructors ci ON c.id = ci.course_id
            WHERE ci.instructor_id = ?
        """;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, instructorId);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Courses for Instructor ID: " + instructorId);
            while (rs.next()) {
                System.out.println(
                        "Course ID: " + rs.getInt("id") +
                                " | Name: " + rs.getString("name") +
                                " | Description: " + rs.getString("description") +
                                " | Term: " + rs.getInt("term") +
                                " | Start Date: " + rs.getDate("start_date") +
                                " | End Date: " + rs.getDate("end_date")
                );
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // 4. View all students enrolled in a course
    public void viewStudentsInCourse(int courseId) {
        String query = """
            SELECT s.id, s.names, s.email, s.phone_number, s.date_of_birth, s.address, e.completion_status
            FROM students s
            JOIN enrollments e ON s.id = e.student_id
            WHERE e.course_id = ?
        """;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Students in Course ID: " + courseId);
            while (rs.next()) {
                System.out.println(
                        "Student ID: " + rs.getInt("id") +
                                " | Name: " + rs.getString("names") +
                                " | Email: " + rs.getString("email") +
                                " | Phone: " + rs.getString("phone_number") +
                                " | DOB: " + rs.getDate("date_of_birth") +
                                " | Address: " + rs.getString("address") +
                                " | Status: " + rs.getString("completion_status")
                );
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // 5. View all enrollments (optionally with grades if maintained in memory)
    public void viewEnrollmentsForStudent(int studentId) {
        String query = """
            SELECT c.id AS course_id, c.name AS course_name, e.completion_status
            FROM enrollments e
            JOIN courses c ON e.course_id = c.id
            WHERE e.student_id = ?
        """;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Enrollments for Student ID: " + studentId);
            while (rs.next()) {
                System.out.println(
                        "Course ID: " + rs.getInt("course_id") +
                                " | Name: " + rs.getString("course_name") +
                                " | Completion Status: " + rs.getString("completion_status")
                );
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
