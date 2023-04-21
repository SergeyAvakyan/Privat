package com.avakian.privat.repository;

import com.avakian.privat.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    void delete(Category category);

    Category findCategoryByCategoryName(String categoryName);
}
