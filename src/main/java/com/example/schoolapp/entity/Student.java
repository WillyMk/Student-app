package com.example.schoolapp.entity;

import com.example.schoolapp.dto.StudentDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Indexed;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Indexed
@Inheritance(strategy = InheritanceType.JOINED) // Use a separate table for each subclass
public class Student extends Person{
    @Embedded
    private Guardian guardian;
    @ManyToMany(mappedBy = "students", cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();

    public StudentDto toData () {
        StudentDto studentDto = new StudentDto();
        studentDto.setEmail(this.getEmail());
        studentDto.setId(this.getId());
        studentDto.setName(this.getName());
        studentDto.setResidence(this.getResidence());
        studentDto.setIdNumber(this.getIdNumber());
        studentDto.setPrimaryContact(this.getPrimaryContact());
        studentDto.setAge(this.getAge());
        studentDto.setMobile(this.getMobile());
        studentDto.setGuardianName(this.getGuardian().getGuardianName());
        studentDto.setGuardianContact(this.getGuardian().getGuardianContact());
        studentDto.setGuardianEmail(this.getGuardian().getGuardianEmail());
        studentDto.setCourses(this.getCourses());
        return studentDto;
    }
}
