package edu.ccrm.service;

import java.time.LocalDate;
import edu.ccrm.domain.Student;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class StudentService 
{
    private List<Student> students=new ArrayList<>();
    private int uniqueID=1;

    public Student addStudent(String name,String Email,String RegNo)
    {
        Student newStudent=new Student(name,Email,uniqueID++,RegNo,"Active",LocalDate.now());
        students.add(newStudent);
        return newStudent;
    }

    public Student findStudentById(int x)
    {
        for (Student i:students)
        {
            if (i.getId()==x)
            {
                return i;
            }
        }
        return null;
    }

    public boolean updateStudent(int studentId,String newFullName,String newEmail)
    {
        Student studentToUpdate=findStudentById(studentId);
        if (studentToUpdate!=null)
        {
            studentToUpdate.setFullName(newFullName);
            studentToUpdate.setEmail(newEmail);
            return true;
        }
        return false;
    }

    public List<Student> getAllStudents()
    {
        return students;
    }

    public boolean deactivateStudent(int x)
    {
        edu.ccrm.domain.Student s=findStudentById(x);
        if (s!=null)
        {
            s.setStatus("Inactive");
            return true;
        }
        return false;
    }
};
