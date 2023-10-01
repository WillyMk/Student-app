package com.example.schoolapp.entity;

import com.example.schoolapp.dto.TeacherDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String residence;
    private String mail;
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Course> courses;

    public TeacherDto toData(){
        TeacherDto dto = new TeacherDto();
        dto.setMail(this.getMail());
        dto.setName(this.getName());
        dto.setResidence(this.getResidence());
        if(this.getCourses().size() > 0){
            dto.setCourseList(this.getCourses());
        }
        return dto;
    }
}
