package com.example.schoolapp.controller;

import com.example.schoolapp.dto.CourseDto;
import com.example.schoolapp.entity.Course;
import com.example.schoolapp.service.CourseService;
import com.example.schoolapp.utility.PaginationData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/course")
    public ResponseEntity<?> saveCourse(@RequestBody CourseDto courseDto){
        CourseDto course = courseService.saveCourse(courseDto).toData();
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    @GetMapping("courses")
    public ResponseEntity<?> getCourses(@RequestParam(name = "page", defaultValue = "1") int page,
                                        @RequestParam(name = "pageSize", defaultValue = "10") int pageSize){
        Pageable pageable = PageRequest.of(page -1 , pageSize);
        Page<Course> courses = courseService.getCourses(pageable);
        List<CourseDto> courseList = courses.stream().map(Course::toData).toList();
        PaginationData<List<?>> content = new PaginationData<>();
        content.setContent(courseList);
        content.setPageSize(courses.getSize());
        content.setPages(courses.getNumber() + 1);
        content.setTotalElements(courses.getTotalElements());
        return ResponseEntity.status(HttpStatus.OK).body(content);
    }

    @GetMapping("course/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id){
        CourseDto courseDto = courseService.getCourseById(id).toData();
        return ResponseEntity.status(HttpStatus.OK).body(courseDto);
    }

    @PostMapping("course/addStudent/{studentId}/{courseId}")
    public ResponseEntity<?> saveStudentToCourse(@PathVariable Long studentId, @PathVariable Long courseId){
        CourseDto courseDto = courseService.saveStudentToCourse(studentId, courseId).toData();
        return ResponseEntity.status(HttpStatus.CREATED).body(courseDto);
    }
}
