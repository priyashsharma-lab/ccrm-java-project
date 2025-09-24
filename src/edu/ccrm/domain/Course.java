package edu.ccrm.domain;

public class Course
{
    private String code;
    private String title;
    private int credits;
    private Instructor instructor;
    private Semester semester;
    private String department;
    private String status;

    public Course(String code,String title,int credits,Instructor instructor,Semester semester,String department)
    {
        this.code=code;
        this.title=title;
        this.credits=credits;
        this.instructor=instructor;
        this.semester=semester;
        this.department=department;
        this.status="Active";
    }

    public void setCode(String code)
    {
        this.code=code;
    }
    public void setTitle(String title)
    {
        this.title=title;
    }
    public void setCredits(int credits)
    {
        this.credits=credits;
    }
    public void setInstructor(Instructor instructor)
    {
        this.instructor=instructor;
    }
    public void setSemester(Semester semester)
    {
        this.semester=semester;
    }
    public void setDepartment(String department)
    {
        this.department=department;
    }
    public void setStatus(String status)
    {
        this.status=status;
    }

    public String getCode()
    {
        return code;
    }
    public String getTitle()
    {
        return title;
    }
    public int getCredits()
    {
        return credits;
    }
    public Instructor getInstructor()
    {
        return instructor;
    }
    public Semester getSemester()
    {
        return semester;
    }
    public String getDepartment()
    {
        return department;
    }
    public String getStatus()
    {
        return status;
    }

    @Override public String toString()
    {
        return "Course Details:\n"+
               "Code: "+code+"\n"+
               "Title: "+title+"\n"+
               "Credits: "+credits+"\n"+
               "Instructor: "+instructor+"\n"+
               "Semester: "+semester+"\n"+
               "Department: "+department+"\n"+
               "Status: "+status; 
    }

    public String toCsvString() 
    {
        String instructorName = (instructor != null) ? instructor.getFullName() : "N/A";
        return code + "," + title + "," + credits + "," + instructorName + "," + semester + "," + status;
    }
}
