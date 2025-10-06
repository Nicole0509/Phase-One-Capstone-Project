package models;

import java.util.Date;

public class Enrollment {
    private final Student student;
    private final Course course;
    private Date enrollmentDate;
    private String completionStatus;
    private double grade;

    public Enrollment(Student student, Course course, Date enrollmentDate, String completionStatus, double grade) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.completionStatus = completionStatus;
        this.grade = grade;
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

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString()
    {
            return "\nEnrollment Details\n" +
                "Student name: " + getStudent().getNames() + "\n" +
                "Course name: " + getCourse().getCourseName() + "\n" +
                "Enrollment: " + getEnrollmentDate() + "\n" +
                "Completion Status: " + getCompletionStatus() ;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }
}
