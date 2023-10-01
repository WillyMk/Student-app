package com.example.schoolapp.controller;

import com.example.schoolapp.dto.TeacherDto;
import com.example.schoolapp.entity.Teacher;
import com.example.schoolapp.service.TeacherService;
import com.example.schoolapp.utility.PaginationData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/teacher")
    public ResponseEntity<?> saveTeacher(@RequestBody TeacherDto teacherDto){
        TeacherDto teacher = teacherService.saveTeacher(teacherDto).toData();
        return ResponseEntity.status(HttpStatus.CREATED).body(teacher);
    }

    @GetMapping("/teachers")
    public ResponseEntity<?> getTeachers(@RequestParam(name = "page", defaultValue = "1") int page,
                                         @RequestParam(name = "pageSize", defaultValue = "10") int pageSize){
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Teacher> teachers = teacherService.getTeachers(pageable);
        List<TeacherDto> teacherList = teachers.stream().map(Teacher::toData).toList();
        PaginationData<List<?>> content = new PaginationData<>();
        content.setContent(teacherList);
        content.setPages(teachers.getNumber() + 1);
        content.setPageSize(teachers.getSize());
        content.setTotalElements(teachers.getTotalElements());
        return ResponseEntity.status(HttpStatus.OK).body(content);
    }
}
