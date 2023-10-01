package com.example.schoolapp.entity;

import com.example.schoolapp.dto.CourseMaterialDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseMaterial {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String pages;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    public CourseMaterialDto toData(){
        CourseMaterialDto c = new CourseMaterialDto();
        c.setId(this.getId());
        c.setName(this.getName());
        c.setPages(this.getPages());
        c.setCourseName(this.getCourse().getName());
        return c;
    }
}
