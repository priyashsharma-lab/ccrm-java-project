package edu.ccrm.service;

import edu.ccrm.domain.*;
import edu.ccrm.util.DuplicateEnrollmentException;
import edu.ccrm.util.MaxCreditLimitExceededException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EnrollmentService
{
    private List<Enrollment> enrollments=new ArrayList<>();
    private static final int MAX_CREDITS_PER_SEMESTER=18;

    private int calculateCurrentCredits(Student student,Semester semester)
    {
        return enrollments.stream().filter(e->e.getStudent().equals(student) && e.getCourse().getSemester()==semester).mapToInt(e->e.getCourse().getCredits()).sum();
    }

    public Enrollment enrollStudent(Student student,Course course) throws MaxCreditLimitExceededException, DuplicateEnrollmentException
    {
        boolean isAlreadyEnrolled=enrollments.stream().anyMatch(e->e.getStudent().equals(student) && e.getCourse().equals(course));
        if (isAlreadyEnrolled)
        {
            throw new DuplicateEnrollmentException
            (
                "Enrollment failed: Student " + student.getFullName() + " is already enrolled in " + course.getTitle()
            );
        }

        int currentCredits=calculateCurrentCredits(student, course.getSemester());
        if (currentCredits+course.getCredits()>MAX_CREDITS_PER_SEMESTER)
        {
            throw new MaxCreditLimitExceededException
            (
                "Enrollment failed: Exceeds max credit limit of " + MAX_CREDITS_PER_SEMESTER + " for the semester."
            );
        }

        Enrollment newEnrollment=new Enrollment(student, course);
        enrollments.add(newEnrollment);
        student.getEnrolledCourses().add(course);

        System.out.println("Enrollment Successful!");
        return newEnrollment;
    }

    public boolean assignGrade(Student student,Course course,Grade grade)
    {
        for (Enrollment i:enrollments)
        {
            if (i.getStudent().equals(student) && i.getCourse().equals(course))
            {
                i.setGrade(grade);
                return true;
            }
        }
        return false;
    }

    public List<Enrollment> getEnrollmentsForStudent(Student student)
    {
        return enrollments.stream().filter(e->e.getStudent().equals(student)).collect(Collectors.toList());
    }
}
