package com.avakian.privat.controller;

import com.avakian.privat.dto.CategoryDto;
import com.avakian.privat.dto.CategoryDtoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/")
public interface ICategoryController {
    @PostMapping("/category")
    ResponseEntity<Void> createCategory(@RequestBody CategoryDto categoryDto);

    @PutMapping("/category/{id}")
    ResponseEntity<CategoryDtoResponse> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto);

    @DeleteMapping("/category/{id}")
    ResponseEntity<Void> deleteCategory(@PathVariable Long id);
}
