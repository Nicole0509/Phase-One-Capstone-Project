package models;

public class Course {
    private String courseName;
    private String description;
    private int credits;

    public Course(String courseName, String description, int credits) {
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

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
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
