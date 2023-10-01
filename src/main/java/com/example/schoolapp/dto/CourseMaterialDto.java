package com.example.schoolapp.dto;

import lombok.Data;

@Data
public class CourseMaterialDto {
    private Long id;
    private String name;
    private Long courseId;
    private String courseName;
    private String pages;
}
