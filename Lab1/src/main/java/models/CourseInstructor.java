package models;

import java.util.Date;

public class CourseInstructor {
    private final Course course;
    private final Instructor instructor;
    private int term;
    private Date startDate;
    private Date endDate;

    public CourseInstructor(Course course, Instructor instructor, int term, Date startDate, Date endDate) {
        this.course = course;
        this.instructor = instructor;
        this.term = term;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "\nCourse Instructor Details\n" +
                "Instructor name: " + getInstructor().getNames() + "\n" +
                "Course name: " + getCourse().getCourseName() + "\n" +
                "Instructor rank: " + getInstructor().getPosition() + "\n" +
                "Term/Semester: " + getTerm() + "\n" +
                "Start Date: " + getStartDate() + "\n" +
                "End Date: " + getEndDate() ;
    }

    public Course getCourse() {
        return course;
    }

    public Instructor getInstructor() {
        return instructor;
    }
}
