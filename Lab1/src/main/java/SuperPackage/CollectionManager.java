package SuperPackage;

import models.*;

import java.util.HashSet;
import java.util.Set;

public class CollectionManager {
    private final Set<Student> students = new HashSet<>();
    private final Set<Course> courses = new HashSet<>();
    private final Set<Instructor> instructors  = new HashSet<>();

    private final Set<Enrollment> enrollments  = new HashSet<>();
    private final Set<CourseInstructor> courseInstructors  = new HashSet<>();

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
}
