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
    private List<String> studentNames;
    private String teacherName;
    private String materialName;
    private Long teacherId;
    private List<Long> studentIds;
}
