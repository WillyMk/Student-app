package com.example.schoolapp.service;

import com.example.schoolapp.dto.CourseMaterialDto;
import com.example.schoolapp.entity.CourseMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CourseMaterialService {
    CourseMaterial saveCourseMaterial(CourseMaterialDto courseMaterialDto);

    Page<CourseMaterial> getCourseMaterials(Pageable pageable);

    CourseMaterial updateMaterial(Long id, CourseMaterialDto courseMaterialDto);
}
