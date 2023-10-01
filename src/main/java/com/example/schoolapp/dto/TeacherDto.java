package com.example.schoolapp.dto;

import com.example.schoolapp.entity.Course;
import lombok.Data;

import java.util.List;

@Data
public class TeacherDto {
    private Long id;
    private String name;
    private String residence;
    private String mail;
    private List<Course> courseList;
}
