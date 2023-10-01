package com.example.schoolapp.service.impl;

import com.example.schoolapp.dto.CourseMaterialDto;
import com.example.schoolapp.entity.Course;
import com.example.schoolapp.entity.CourseMaterial;
import com.example.schoolapp.exception.APIException;
import com.example.schoolapp.repo.CourseMaterialRepo;
import com.example.schoolapp.service.CourseMaterialService;
import com.example.schoolapp.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseMaterialImpl implements CourseMaterialService {
    private final CourseMaterialRepo courseMaterialRepo;
    private final CourseService courseService;
    @Override
    public CourseMaterial saveCourseMaterial(CourseMaterialDto courseMaterialDto) {
        CourseMaterial c = new CourseMaterial();
        c.setName(courseMaterialDto.getName());
        Course course = courseService.getCourseById(courseMaterialDto.getCourseId());
        c.setCourse(course);
        return courseMaterialRepo.save(c);
    }

    @Override
    public Page<CourseMaterial> getCourseMaterials(Pageable pageable) {
        return courseMaterialRepo.findAll(pageable);
    }

    @Override
    public CourseMaterial updateMaterial(Long id, CourseMaterialDto courseMaterialDto) {
        CourseMaterial c = courseMaterialRepo.findById(id).orElseThrow(() -> APIException.notFound("Course material with id " + id + " not found"));
        // Check if the field is provided in the DTO before updating
        if (courseMaterialDto.getName() != null) {
            c.setName(courseMaterialDto.getName());
        }

        if (courseMaterialDto.getPages() != null) {
            c.setPages(courseMaterialDto.getPages());
        }

        if (courseMaterialDto.getCourseId() != null) {
            Course course = courseService.getCourseById(courseMaterialDto.getCourseId());
            c.setCourse(course);
        }
        return courseMaterialRepo.save(c);
    }
}
