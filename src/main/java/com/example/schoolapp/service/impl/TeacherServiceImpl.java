package com.example.schoolapp.service.impl;

import com.example.schoolapp.dto.TeacherDto;
import com.example.schoolapp.entity.Teacher;
import com.example.schoolapp.repo.TeacherRepo;
import com.example.schoolapp.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepo teacherRepo;
    @Override
    public Teacher saveTeacher(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();
        teacher.setName(teacherDto.getName());
        teacher.setMail(teacherDto.getMail());
        teacher.setResidence(teacherDto.getResidence());
        return teacherRepo.save(teacher);
    }

    @Override
    public Page<Teacher> getTeachers(Pageable pageable) {
        return teacherRepo.findAll(pageable);
    }
}
