package CRUD_Implemantation;

import CRUD_Interface.CrudInterface;
import models.Course;
import models.CourseInstructor;
import models.Instructor;

import java.util.Date;

public class CourseInstructorImplementation extends CourseInstructor implements CrudInterface {

    public CourseInstructorImplementation(Course course, Instructor instructor, int term, Date startDate, Date endDate){
        super(course, instructor, term, startDate, endDate);
    }

    @Override
    public String create(){
        return "";
    }

    @Override
    public void  viewAll(){
    }

    @Override
    public String update(int id){
        return "";
    }

    @Override
    public String delete(int id){
        return "";
    }
}
