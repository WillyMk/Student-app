package com.example.schoolapp.service.impl;

import com.example.schoolapp.dto.CourseDto;
import com.example.schoolapp.entity.Course;
import com.example.schoolapp.entity.Student;
import com.example.schoolapp.entity.Teacher;
import com.example.schoolapp.exception.APIException;
import com.example.schoolapp.repo.CourseRepo;
import com.example.schoolapp.service.CourseService;
import com.example.schoolapp.service.StudentService;
import com.example.schoolapp.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CourseServiceImpl implements CourseService {
    private final CourseRepo courseRepo;
    private final TeacherService teacherService;
    private final StudentService studentService;
    @Override
    public Course saveCourse(CourseDto courseDto) {
        Course c = courseRepo.findByName(courseDto.getName());
        if(c != null) {
            throw new RuntimeException("Course by name " + courseDto.getName() + " already exists ");
        }
        Course course = new Course();
        course.setCode(courseDto.getCode());
        course.setName(courseDto.getName());
        Teacher teacher = teacherService.getTeacherById(courseDto.getTeacherId());
        course.setTeacher(teacher);
        if(courseDto.getStudentIds() == null) {
            throw new RuntimeException("Please add a student");
        }
//        List<Student> studentList = new ArrayList<>();
//        for(Long s : courseDto.getStudentIds()){
//            Student student = studentService.getStudentById(s);
//            studentList.add(student);
//        }
        List<Student> studentList = courseDto.getStudentIds().stream()
                .map(studentService::getStudentById)
                .toList();
        course.setStudents(studentList);
        return courseRepo.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepo.findById(id).orElseThrow(() -> APIException.notFound("Course by id " + id + " not found"));
    }

    @Override
    public Page<Course> getCourses(Pageable pageable) {
        return courseRepo.findAll(pageable);
    }

    @Override
    public Course saveStudentToCourse(Long studentId, Long courseId) {
        Student student = studentService.getStudentById(studentId);
        Course course = getCourseById(courseId);
        course.addStudent(student);
        return courseRepo.save(course);
    }
}
