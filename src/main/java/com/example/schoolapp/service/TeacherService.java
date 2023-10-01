package com.example.schoolapp.service;

import com.example.schoolapp.dto.TeacherDto;
import com.example.schoolapp.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface TeacherService {
    Teacher saveTeacher(TeacherDto teacherDto);

    Page<Teacher> getTeachers(Pageable pageable);
}
