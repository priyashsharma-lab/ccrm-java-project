package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Instructor;
import edu.ccrm.domain.Semester;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
// import java.util.stream.Collector;
// import java.util.stream.Collector;
public class CourseService 
{
    private List<Course> courses=new ArrayList<>();

    public List<Course> getAllCourses() 
    {
        return courses;
    }

    public Course addCourse(String code, String title, int credits,Instructor instructor,Semester semester,String department)
    {
        Course newCourse=new Course(code,title,credits,instructor,semester,department);
        courses.add(newCourse);
        return newCourse;
    }

    public Course findCourseByCode(String x)
    {
        for (Course i:courses)
        {
            if (i.getCode().equalsIgnoreCase(x))
            {
                return i;
            }
        }
        return null;
    }

    public List<Course> findCoursesByDepartment(String x)
    {
        return courses.stream().filter(course->course.getDepartment().equalsIgnoreCase(x)).collect(Collectors.toList());
    }

    public List<Course> findCoursesByInstructor(String instructorName)
    {
        return courses.stream().filter(course->course.getInstructor().getFullName().equalsIgnoreCase(instructorName)).collect(Collectors.toList());
    }

    public List<Course> findCoursesBySemester(Semester semester)
    {
        return courses.stream().filter(course->course.getSemester().equals(semester)).collect(Collectors.toList());
    }

    public boolean updateCourse(String courseCode,String newTitle,int newCredits,Instructor newInstructor)
    {
        for (Course i:courses)
        {
            if (i.getCode()==courseCode)
            {
                i.setTitle(newTitle);
                i.setCredits(newCredits);
                i.setInstructor(newInstructor);
                return true;
            }
        }
        return false;
    }

    public boolean deactivateCourse(String x)
    {
        Course courseToDeactivate=findCourseByCode(x);
        if (courseToDeactivate!=null)
        {
            courseToDeactivate.setStatus("Inactive");
            return true;
        }
        return false;
    }
}
