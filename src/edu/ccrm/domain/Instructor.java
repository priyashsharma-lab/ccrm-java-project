package edu.ccrm.domain;

public class Instructor extends Person
{
    private int id;
    private String department;   

    public Instructor(int id, String fullName, String email, String department)
    {
        super(fullName, email); 
        this.id=id;
        this.department=department;
    }

    public void setEmpID(int id)
    {
        this.id=id;
    }
    public void setDepartment(String department)
    {
        this.department=department;
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
    public String getDepartment()
    {
        return department;
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
        return "Instructor Profile:\n"+
               "Full Name: "+full_name+"\n"+
               "Employee ID: "+id+"\n"+
               "Email: "+email+"\n"+
               "Department: "+department+"\n"; 
    }
    
    public void getProfile()
    {
        System.out.println("================================");
        System.out.println("INSTRUCTOR PROFILE: "+full_name);
        System.out.println("================================\n");
        System.out.println("Full Name: "+full_name);
        System.out.println("Employee ID: "+id);
        System.out.println("Email: "+email);
        System.out.println("Department: "+department);
        System.out.println("--------------------------------");
    }


    public String toCsvString() 
    {
    return getId() + "," + getFullName() + "," + getEmail() + "," + getDepartment();
    }
};
