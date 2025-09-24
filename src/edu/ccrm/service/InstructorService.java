// Create this new file in the edu.ccrm.service package

package edu.ccrm.service;

import edu.ccrm.domain.Instructor;
import java.util.ArrayList;
import java.util.List;

public class InstructorService {
    private final List<Instructor> instructors = new ArrayList<>();
    private int nextInstructorId = 1;

    public Instructor addInstructor(String fullName, String email, String department) {
        Instructor newInstructor = new Instructor(nextInstructorId++, fullName, email, department);
        instructors.add(newInstructor);
        return newInstructor;
    }

    public Instructor findInstructorById(int id) {
        return instructors.stream()
                .filter(inst -> inst.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Instructor> getAllInstructors() {
        return instructors;
    }

    public boolean updateInstructor(int id, String newFullName, String newEmail, String newDepartment) {
        Instructor instructor = findInstructorById(id);
        if (instructor != null) {
            instructor.setFullName(newFullName);
            instructor.setEmail(newEmail);
            instructor.setDepartment(newDepartment);
            return true;
        }
        return false;
    }

    public Instructor findInstructorByName(String name) 
    {
        return instructors.stream()
            .filter(inst -> inst.getFullName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }
}