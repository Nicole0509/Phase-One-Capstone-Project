package SuperPackage;

import models.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CollectionManager {
    private final Set<Student> students = new HashSet<>();
    private final Set<Course> courses = new HashSet<>();
    private final Set<Instructor> instructors  = new HashSet<>();

    public final Set<Enrollment> enrollments  = new HashSet<>();
    public final Set<CourseInstructor> courseInstructors  = new HashSet<>();

    public <T>void addEntity (Collection<T> collection, T entity) {
        collection.add(entity);
    }

    public <T>Set<T> filterSet (Collection<T> collection, Predicate<T> predicate) {
        return collection.stream()
                .filter(predicate)
                .collect(Collectors.toSet());
    }

    public void addStudent(Student student) {
        addEntity(students, student);
    }

    public void addCourse(Course course) {
        addEntity(courses, course);
    }

    public void addInstructor(Instructor instructor) {
        addEntity(instructors, instructor);
    }

    public void enrollStudent(Enrollment enrollment) {
        addEntity(enrollments, enrollment);
    }

    public void addCourseInstructor(CourseInstructor courseInstructor) {
        addEntity(courseInstructors, courseInstructor);
    }

    public Set<Enrollment> getStudentEnrollments(Student student) {
        return filterSet(enrollments, e -> e.getStudent().equals(student));
    }

    public Set<CourseInstructor> getCourseInstructors(Instructor instructor) {
          return filterSet(courseInstructors,i -> i.getInstructor().equals(instructor) );
    }

    public Set<CourseInstructor> getCoursesInstructed(Course course) {
        return filterSet(courseInstructors,c -> c.getCourse().equals(course));
    }
}
