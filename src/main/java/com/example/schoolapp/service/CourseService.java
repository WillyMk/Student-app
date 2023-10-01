package com.example.schoolapp.service;

import com.example.schoolapp.dto.CourseDto;
import com.example.schoolapp.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CourseService {
    Course saveCourse(CourseDto courseDto);

    Course getCourseById(Long id);

    Page<Course> getCourses(Pageable pageable);

    Course saveStudentToCourse(Long studentId, Long courseId);
}
