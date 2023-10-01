package com.example.schoolapp.service;

import com.example.schoolapp.dto.StudentDto;
import com.example.schoolapp.entity.Guardian;
import com.example.schoolapp.entity.Student;
import com.example.schoolapp.repo.StudentRepo;
import com.example.schoolapp.service.impl.StudentServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {
    @Mock
    private StudentRepo studentRepo;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    public void StudentService_Create_Student(){
        Guardian guardian = new Guardian();
        guardian.setGuardianEmail("ann@gmail.com");
        guardian.setGuardianContact("7353736363");
        guardian.setGuardianName("Ann");

        Student student = new Student();
        student.setName("Melvin Muthumbi");
        student.setEmail("melvin@gmail.com");
        student.setMobile("712356733");
        student.setGuardian(guardian);

        StudentDto studentDto = new StudentDto();
        studentDto.setName("Melvin Muthumbi");
        studentDto.setEmail("melvin@gmail.com");
        studentDto.setMobile("712356733");
        studentDto.setGuardianName("Ann");

        when(studentRepo.save(Mockito.any(Student.class))).thenReturn(student);

        StudentDto savedStudent = studentService.saveStudent(studentDto).toData();
        Assertions.assertThat(savedStudent).isNotNull();
    }
}
