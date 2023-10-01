package com.example.schoolapp.dto;

import com.example.schoolapp.entity.Course;
import lombok.Data;
import java.util.List;

@Data
public class StudentDto {
    private Long id;
    private String name;
    private String email;
    private String mobile;
    private String primaryContact;
    private int age;
    private String residence;
    private int idNumber;
    private String guardianName;
    private String guardianEmail;
    private String guardianContact;
    private List<Course> courses;
}
