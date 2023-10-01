package com.example.schoolapp.dto;

import com.example.schoolapp.entity.Student;
import com.example.schoolapp.entity.Teacher;
import lombok.Data;

import java.util.List;

@Data
public class CourseDto {
    private Long id;
    private String name;
    private String code;
    private List<Student> studentList;
    private List<Teacher> teachers;
    private String materialName;
}
