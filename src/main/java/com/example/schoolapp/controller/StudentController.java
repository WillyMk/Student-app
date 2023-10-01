package com.example.schoolapp.controller;

import com.example.schoolapp.dto.StudentDto;
import com.example.schoolapp.entity.Student;
import com.example.schoolapp.service.StudentService;
import com.example.schoolapp.utility.PaginationData;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/student")
    public ResponseEntity<?> saveStudent(@RequestBody StudentDto studentDto){
        StudentDto student = studentService.saveStudent(studentDto).toData();
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @GetMapping("/students")
    public ResponseEntity<?> getStudents(@RequestParam(name = "page", defaultValue = "1") int page,
                                         @RequestParam(name = "pageSize", defaultValue = "10") int pageSize){
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Student> students = studentService.getStudents(pageable);
        List<StudentDto> studentList = students.getContent().stream().map(Student::toData).toList();
        PaginationData<List<?>> content = new PaginationData<>();
        content.setContent(studentList);
        content.setPages(students.getNumber() + 1);
        content.setPageSize(students.getSize());
        content.setTotalElements(students.getTotalElements());
        return ResponseEntity.status(HttpStatus.OK).body(content);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Long id){
        StudentDto studentDto = studentService.getStudentById(id).toData();
        return ResponseEntity.status(HttpStatus.OK).body(studentDto);
    }

}


