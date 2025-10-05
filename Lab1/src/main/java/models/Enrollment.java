package models;

import java.time.LocalDate;
import java.util.Date;

public class Enrollment {
    private final Student student;
    private final Course course;
    private Date enrollmentDate;
    private String completionStatus;
    private double gpa;

    public Enrollment(Student student, Course course, Date enrollmentDate, String completionStatus, double gpa) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.completionStatus = completionStatus;
        this.gpa = gpa;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(String completionStatus) {
        this.completionStatus = completionStatus;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString()
    {
            return "\nEnrollment Details\n" +
                "Student name: " + student.getNames() + "\n" +
                "Course name: " + course.getCourseName() + "\n" +
                "Enrollment: " + getEnrollmentDate() + "\n" +
                "Completion Status: " + getCompletionStatus() ;
    }
}
