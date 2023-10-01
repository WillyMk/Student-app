package com.example.schoolapp.repo;

import com.example.schoolapp.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo  extends JpaRepository<Course, Long> {
    Course findByName(String name);
}
