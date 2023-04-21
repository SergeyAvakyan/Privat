package com.avakian.privat.service;

import com.avakian.privat.dto.CategoryDto;
import com.avakian.privat.dto.CategoryDtoResponse;
import com.avakian.privat.entity.Category;
import com.avakian.privat.exception.NotFoundException;
import com.avakian.privat.repository.CategoryRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {

    private CategoryRepo categoryRepo = mock(CategoryRepo.class);

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test createCategory method")
    public void testCreateCategory() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName("Test Category");

        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());

        categoryService.createCategory(categoryDto);

        verify(categoryRepo, times(1)).save(category);
    }

    @Test
    @DisplayName("Test updateCategory method")
    public void testUpdateCategory() {
        Long id = 1L;

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName("Test Category");

        Category existingCategory = new Category();
        existingCategory.setId(id);
        existingCategory.setCategoryName("Old Test Category");

        Category updatedCategory = new Category();
        updatedCategory.setId(id);
        updatedCategory.setCategoryName(categoryDto.getCategoryName());

        when(categoryRepo.findById(id)).thenReturn(Optional.of(existingCategory));
        when(categoryRepo.save(updatedCategory)).thenReturn(updatedCategory);

        CategoryDtoResponse response = categoryService.updateCategory(id, categoryDto);

        assertEquals(categoryDto.getCategoryName(), response.getCategoryName());
    }

    @Test
    @DisplayName("Test updateCategory method with NotFoundException")
    public void testUpdateCategoryWithNotFoundException() {
        Long id = 1L;

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName("Test Category");

        when(categoryRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            categoryService.updateCategory(id, categoryDto);
        });
    }

    @Test
    @DisplayName("Test deleteCategory method")
    public void testDeleteCategory() {
        Long id = 1L;

        Category existingCategory = new Category();
        existingCategory.setId(id);
        existingCategory.setCategoryName("Test Category");

        when(categoryRepo.findById(id)).thenReturn(Optional.of(existingCategory));

        categoryService.deleteCategory(id);

        verify(categoryRepo, times(1)).delete(existingCategory);
    }

    @Test
    @DisplayName("Test deleteCategory method with NotFoundException")
    public void testDeleteCategoryWithNotFoundException() {
        Long id = 1L;

        when(categoryRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            categoryService.deleteCategory(id);
        });
    }
}
