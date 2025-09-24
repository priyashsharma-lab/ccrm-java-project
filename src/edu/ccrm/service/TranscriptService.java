package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
public class TranscriptService 
{
    private final EnrollmentService enrollmentService;

    public TranscriptService(EnrollmentService enrollmentService)
    {
        this.enrollmentService=enrollmentService;
    }

    public String generateTranscript(Student student)
    {
        StringBuilder sb=new StringBuilder();
        sb.append("======================================================================\n");
        sb.append("                     ACADEMIC TRANSCRIPT\n");
        sb.append   ("======================================================================\n\n");
        sb.append("  Student Name    : ").append(student.getFullName()).append("\n");
        sb.append("  Registration No : ").append(student.getRegNo()).append("\n\n");
        sb.append("----------------------------------------------------------------------\n");
        sb.append(String.format("  %-12s %-30s %-10s %-10s\n", "COURSE", "TITLE", "CREDITS", "GRADE"));
        sb.append("----------------------------------------------------------------------\n");

        double totalPoints = 0;
        int totalCredits = 0;

        for (Enrollment enrollment : enrollmentService.getEnrollmentsForStudent(student)) 
        {
            if (enrollment.getGrade() != null) 
            {
                sb.append(String.format("  %-12s %-30s %-10d %-10s\n",
                    enrollment.getCourse().getCode(),
                    enrollment.getCourse().getTitle(),
                    enrollment.getCourse().getCredits(),
                    enrollment.getGrade() + " (" + enrollment.getGrade().getPoints() + ")"
                ));
            
                totalCredits += enrollment.getCourse().getCredits();
                totalPoints += enrollment.getCourse().getCredits() * enrollment.getGrade().getPoints();
            }
        }

        double gpa = (totalCredits == 0) ? 0.0 : totalPoints / totalCredits;

        sb.append("\n======================================================================\n");
        sb.append("  ACADEMIC SUMMARY\n");
        sb.append("----------------------------------------------------------------------\n");
        sb.append("  Total Credits Earned : ").append(totalCredits).append("\n");
        sb.append(String.format("  Cumulative GPA       : %.2f\n", gpa)); 
        sb.append("======================================================================\n");

        return sb.toString();
    }
}

