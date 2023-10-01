package com.example.schoolapp.service.impl;

import com.example.schoolapp.dto.TeacherDto;
import com.example.schoolapp.entity.Teacher;
import com.example.schoolapp.exception.APIException;
import com.example.schoolapp.repo.TeacherRepo;
import com.example.schoolapp.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepo teacherRepo;
    @Override
    public Teacher saveTeacher(TeacherDto teacherDto) {
        if(teacherDto.getMail() == null) {
            throw new RuntimeException("Email not found!");
        }
        Teacher t = teacherRepo.findByMail(teacherDto.getMail());
        if(t == null) {
            Teacher teacher = new Teacher();
            teacher.setName(teacherDto.getName());
            teacher.setMail(teacherDto.getMail());
            teacher.setResidence(teacherDto.getResidence());
            return teacherRepo.save(teacher);
        }else{
            throw new RuntimeException("Teacher with email "+ teacherDto.getMail() + "already exists");
        }
    }

    @Override
    public Page<Teacher> getTeachers(Pageable pageable) {
        return teacherRepo.findAll(pageable);
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherRepo.findById(id).orElseThrow(() -> APIException.notFound("Teacher with id "+ id + " not found"));
    }


}
