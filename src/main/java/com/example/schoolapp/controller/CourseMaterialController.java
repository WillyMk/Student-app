package com.example.schoolapp.controller;

import com.example.schoolapp.dto.CourseMaterialDto;
import com.example.schoolapp.entity.CourseMaterial;
import com.example.schoolapp.service.CourseMaterialService;
import com.example.schoolapp.utility.PaginationData;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CourseMaterialController {
    private final CourseMaterialService courseMaterialService;

    @PostMapping("material")
    public ResponseEntity<?> saveMaterial(@RequestBody CourseMaterialDto  courseMaterialDto){
        CourseMaterialDto courseMaterialDto1 = courseMaterialService.saveCourseMaterial(courseMaterialDto).toData();
        return ResponseEntity.status(HttpStatus.CREATED).body(courseMaterialDto1);
    }

    @GetMapping("material")
    public ResponseEntity<?> getCourseMaterial(@RequestParam(name = "page", defaultValue = "1") int page,
                                               @RequestParam(name = "pageSize", defaultValue = "10") int pageSize){
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<CourseMaterial> courseMaterials = courseMaterialService.getCourseMaterials(pageable);
        List<CourseMaterialDto> materilaList = courseMaterials.stream().map(CourseMaterial::toData).toList();
        PaginationData<List<?>> content = new PaginationData<List<?>>();
        content.setContent(materilaList);
        content.setPages(courseMaterials.getNumber() + 1);
        content.setPageSize(courseMaterials.getSize());
        content.setTotalElements(courseMaterials.getTotalElements());
        return ResponseEntity.status(HttpStatus.OK).body(content);
    }

    @PutMapping("material/{id}")
    public ResponseEntity<?> updateMaterial(@PathVariable Long id, @RequestBody CourseMaterialDto courseMaterialDto){
        CourseMaterialDto c = courseMaterialService.updateMaterial(id, courseMaterialDto).toData();
        return ResponseEntity.status(HttpStatus.OK).body(c);
    }
}
