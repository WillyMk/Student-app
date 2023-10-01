package com.example.schoolapp.service;

import com.example.schoolapp.dto.StudentDto;
import com.example.schoolapp.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    Student saveStudent(StudentDto studentDto);

    Page<Student> getStudents(Pageable pageable);

    Student getStudentById(Long id);
}
