package com.avakian.privat.controller.impl;

import com.avakian.privat.dto.CategoryDto;
import com.avakian.privat.dto.CategoryDtoResponse;
import com.avakian.privat.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CategoryControllerTest {

    private CategoryService categoryService = mock(CategoryService.class);
    private CategoryController categoryController = new CategoryController(categoryService);

    @Test
    void createCategoryShouldReturnCreated() {
        CategoryDto categoryDto = new CategoryDto();
        ResponseEntity<Void> expectedResponse = ResponseEntity.status(HttpStatus.CREATED).build();

        ResponseEntity<Void> response = categoryController.createCategory(categoryDto);

        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        verify(categoryService).createCategory(categoryDto);
    }

    @Test
    void updateCategoryShouldReturnUpdatedCategory() {
        Long id = 1L;
        CategoryDto categoryDto = new CategoryDto();
        CategoryDtoResponse expectedCategoryDtoResponse = new CategoryDtoResponse();
        when(categoryService.updateCategory(id, categoryDto)).thenReturn(expectedCategoryDtoResponse);

        ResponseEntity<CategoryDtoResponse> response = categoryController.updateCategory(id, categoryDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCategoryDtoResponse, response.getBody());
        verify(categoryService).updateCategory(id, categoryDto);
    }

    @Test
    void deleteCategoryShouldReturnNoContent() {
        Long id = 1L;
        ResponseEntity<Void> expectedResponse = ResponseEntity.noContent().build();

        ResponseEntity<Void> response = categoryController.deleteCategory(id);

        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        verify(categoryService).deleteCategory(id);
    }
}
