package com.avakian.privat.service;

import com.avakian.privat.dto.CategoryDto;
import com.avakian.privat.dto.CategoryDtoResponse;
import com.avakian.privat.entity.Category;
import com.avakian.privat.exception.NotFoundException;
import com.avakian.privat.repository.CategoryRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class CategoryService {
    public static final String CATEGORY_NOT_FOUND_WITH_ID = "Category not found with id: {}";

    private final CategoryRepo categoryRepo;

    public void createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        categoryRepo.save(category);
    }

    @Transactional
    public CategoryDtoResponse updateCategory(Long id, CategoryDto categoryDto) {
        Category updateCategory = categoryRepo.findById(id)
                .orElseThrow(() -> {
                    log.error(CATEGORY_NOT_FOUND_WITH_ID, id);
                    throw new NotFoundException();
                });
        updateCategory.setCategoryName(categoryDto.getCategoryName());

        return new CategoryDtoResponse(updateCategory.getCategoryName());
    }

    public void deleteCategory(Long id) {
        Category existingCategory = categoryRepo.findById(id)
                .orElseThrow(() -> {
                    log.error(CATEGORY_NOT_FOUND_WITH_ID, id);
                    throw new NotFoundException();
                });
        categoryRepo.delete(existingCategory);
    }
}
