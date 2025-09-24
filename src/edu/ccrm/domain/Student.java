package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
public class Student extends Person 
{
    private int id;
    private String regNo;
    private String status;
    private LocalDate registrationDate;
    private List<Course> enrolledCourses;
    
    public Student(String x,String y,int id,String regNo,String status,LocalDate registrationDate)
    {
        super(x,y);
        this.id=id;
        this.regNo=regNo;
        this.status=status;
        this.registrationDate=registrationDate;
        this.enrolledCourses = new ArrayList<>();
    }

    public void setId(int id)
    {
        this.id=id;
    }
    public void setRegNo(String regNo)
    {
        this.regNo=regNo;
    }
    public void setStatus(String status)
    {
        this.status=status;
    }
    public void setRegistrationDate(LocalDate registrationDate)
    {
        this.registrationDate=registrationDate;
    }
    public void setEnrolledCourses(List<Course> enrolledCourses)
    {
        this.enrolledCourses=enrolledCourses;
    }
    public void setFullName(String full_name)
    {
        this.full_name=full_name;
    }
    public void setEmail(String email)
    {
        this.email=email;
    }

    public int getId()
    {
        return id;
    }
    public String getRegNo()
    {
        return regNo;
    }
    public String getStatus()
    {
        return status;
    }
    public LocalDate getRegistrationDate()
    {
        return registrationDate;
    }
    public List<Course> getEnrolledCourses()
    {
        return enrolledCourses;
    }
    public String getFullName()
    {
        return full_name;
    }
    public String getEmail()
    {
        return email;
    }
    
    @Override public String toString()
    {
        return "Student Profile:\n"+
               "Full Name: "+full_name+"\n"+
               "ID: "+id+"\n"+
               "Email: "+email+"\n"+
               "Registration No: "+regNo+"\n"+
               "Status: "+status+"\n"+
               "Registration Date: "+registrationDate+"\n";
    }

    public void getProfile()
    {
        System.out.println("================================");
        System.out.println("STUDENT PROFILE: "+full_name);
        System.out.println("================================\n");
        System.out.println("ID: "+id);
        System.out.println("Registration No.: "+regNo);
        System.out.println("Full Name: "+full_name);
        System.out.println("Email: "+ email);
        System.out.println("Status: "+status);
        System.out.println("Registration: "+registrationDate+"\n");
        System.out.println("--------------------------------");
    }

    public String toCsvString() 
    {
        return id + "," + regNo + "," + full_name + "," + email + "," + status;
    }
};
