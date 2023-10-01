package com.example.schoolapp.entity;

import com.example.schoolapp.dto.CourseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "students_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;
    @OneToOne(mappedBy = "course")
    private CourseMaterial courseMaterial;

    public void addTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    public void addStudent(Student student){
        students.add(student);
    }

    public CourseDto toData(){
        CourseDto courseDto = new CourseDto();
        courseDto.setId(this.getId());
        courseDto.setName(this.getName());
        courseDto.setCode(this.getCode());
        if(this.getCourseMaterial() != null) {
            courseDto.setMaterialName(this.getCourseMaterial().getName());
        }
        if(this.getTeacher() != null) {
            courseDto.setTeacherName(this.getTeacher().getName());
        }
//        List<String> studentsName = new ArrayList<>();
//        for(Student s = this.getStudents()){
//            studentsName.add(s.getName());
//        }
        List<String> studentsName = this.getStudents().stream()
                .map(Student::getName).toList();

        courseDto.setStudentNames(studentsName);
        return courseDto;
    }
}
