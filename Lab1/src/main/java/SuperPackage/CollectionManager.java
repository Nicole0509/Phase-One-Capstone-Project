package SuperPackage;

import models.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectionManager {
    private final Set<Student> students = new HashSet<>();
    private final Set<Course> courses = new HashSet<>();
    private final Set<Instructor> instructors  = new HashSet<>();

    public final Set<Enrollment> enrollments  = new HashSet<>();
    public final Set<CourseInstructor> courseInstructors  = new HashSet<>();

    public String addStudent(Student student) {
        students.add(student);
        return students + "\n";
    }

    public String addCourse(Course course) {
        courses.add(course);
        return courses + "\n";
    }

    public String addInstructor(Instructor instructor) {
        instructors.add(instructor);
        return instructors + "\n";
    }

    public String enrollStudent(Enrollment enrollment) {
        enrollments.add(enrollment);
        return enrollments + "\n";
    }

    public String addCourseInstructor(CourseInstructor courseInstructor) {
        courseInstructors.add(courseInstructor);
        return courseInstructors + "\n";
    }

    public Set<Enrollment> getStudentEnrollments(Student student) {
        return enrollments.stream()
                .filter(e -> e.getStudent().equals(student))
                .collect(Collectors.toSet());
    }
}
