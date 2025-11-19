package edu.univ.erp.service;

import edu.univ.erp.dao.EnrollmentDAO;

public class StudentService {

    EnrollmentDAO enrollDAO = new EnrollmentDAO();

    public String register(int studentId, int sectionId) {
        try {
            // 1. Duplicate check
            if (enrollDAO.isAlreadyEnrolled(studentId, sectionId)) {
                return "Already enrolled in this section.";
            }

            // 2. Capacity check
            int current = enrollDAO.getCurrentEnrollmentCount(sectionId);
            int max = enrollDAO.getSectionCapacity(sectionId);

            if (current >= max) {
                return "Section is full.";
            }

            // 3. Insert row
            if (enrollDAO.enroll(studentId, sectionId)) {
                return "Registration successful!";
            }

            return "Registration failed.";

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
