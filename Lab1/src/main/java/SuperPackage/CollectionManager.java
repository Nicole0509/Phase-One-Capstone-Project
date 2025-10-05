package SuperPackage;

import models.*;

import java.util.*;

public class CollectionManager {
    private Map<Integer, Student> students = new HashMap<>();
    private Map<Integer, Course> courses = new HashMap<>();
    private Map<Integer, Instructor> instructors  = new HashMap<>();

    private Set<Enrollment> enrollments  = new HashSet<>();
    private Set<CourseInstructor> courseInstructors  = new HashSet<>();
}
