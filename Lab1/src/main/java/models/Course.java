package models;

public class Course {
    private String courseName;
    private String description;
    private double credits;

    public Course(String courseName, String description, double credits) {
        this.setCourseName(courseName);
        this.setDescription(description);
        this.setCredits(credits);
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    @Override
    public String toString()
    {
        return "\nCourse Details\n" +
                "Course name: " + getCourseName() + "\n" +
                "Course description: " + getDescription() + "\n" +
                "Course credits: : " + getCredits() ;
    }
}
