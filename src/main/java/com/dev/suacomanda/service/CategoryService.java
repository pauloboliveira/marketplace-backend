package com.dev.suacomanda.service;

import com.dev.suacomanda.domain.category.Category;
import com.dev.suacomanda.domain.category.exception.CategoryNotFoundException;
import com.dev.suacomanda.domain.category.CategoryDTO;
import com.dev.suacomanda.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category insert(CategoryDTO categoryDTO) {
        Category category = new Category(categoryDTO);
        return categoryRepository.save(category);
    }

    public Category update(CategoryDTO categoryDTO, String id) {
        Category category = categoryRepository.findById(id).orElseThrow(throwCategoryNotFoundException());
        if(!categoryDTO.description().isEmpty()) category.setDescription(categoryDTO.description());
        if(!categoryDTO.title().isEmpty()) category.setTitle(categoryDTO.title());

        return categoryRepository.save(category);
    }

    public void deleteById(String id) {
        categoryRepository.deleteById(id);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(String id) {
        return categoryRepository.findById(id).orElseThrow(throwCategoryNotFoundException());
    }

    private static Supplier<CategoryNotFoundException> throwCategoryNotFoundException() {
        return () -> new CategoryNotFoundException("Não foi possível encontrar a categoria informada");
    }
}
