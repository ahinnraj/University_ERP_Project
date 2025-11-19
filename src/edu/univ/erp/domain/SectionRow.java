package edu.univ.erp.domain;

public record SectionRow(
        int sectionId,
        String courseTitle,
        int credits,
        String instructor,
        int capacity,
        String dayTime
) {}
