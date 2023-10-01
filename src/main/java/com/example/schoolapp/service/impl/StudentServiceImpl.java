package com.example.schoolapp.service.impl;

import com.example.schoolapp.dto.StudentDto;
import com.example.schoolapp.entity.Guardian;
import com.example.schoolapp.entity.Student;
import com.example.schoolapp.exception.APIException;
import com.example.schoolapp.repo.StudentRepo;
import com.example.schoolapp.service.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Guard;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;
    @Override
    public Student saveStudent(StudentDto studentDto) {
        Student s = studentRepo.findStudentByEmail(studentDto.getEmail());
        if (s != null) {
            throw new RuntimeException("Student with" + studentDto.getEmail() + "` already exists");
        }
        Guardian guardian = new Guardian();
        guardian.setGuardianContact(studentDto.getGuardianContact());
        guardian.setGuardianName(studentDto.getGuardianName());
        guardian.setGuardianEmail(studentDto.getGuardianEmail());

        Student student = new Student();
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setMobile(studentDto.getMobile());
        student.setGuardian(guardian);
        student.setIdNumber(studentDto.getIdNumber());
        student.setAge(studentDto.getAge());
        student.setResidence(studentDto.getResidence());
        student.setPrimaryContact(student.getPrimaryContact());
        return studentRepo.save(student);
    }

    @Override
    public Page<Student> getStudents(Pageable pageable) {
        return studentRepo.findAll(pageable);
    }

    @Override
    public Student getStudentById(Long id) {

        return studentRepo.findById(id).orElseThrow(() -> APIException.notFound("Student not found" + id));
    }
}
