package edu.ccrm.domain;

import java.time.LocalDate;

public class Enrollment 
{
    private Student student;
    private Course course;
    private Grade grade;
    private LocalDate enrollmentDate;
    
    public Enrollment(Student student,Course course)
    {
        this.student=student;
        this.course=course;
        grade=null;
        enrollmentDate=LocalDate.now();
    }

    public void setGrade(Grade grade)
    {
        this.grade=grade;
    }
    public void setStudent(Student student)
    {
        this.student=student;
    }
    public void setCourse(Course course)
    {
        this.course=course;
    }

    public Grade getGrade()
    {
        return grade;
    }
    public Student getStudent()
    {
        return student;
    }
    public Course getCourse()
    {
        return course;
    }


}
