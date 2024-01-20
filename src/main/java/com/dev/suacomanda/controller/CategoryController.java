package com.dev.suacomanda.controller;

import com.dev.suacomanda.domain.category.Category;
import com.dev.suacomanda.domain.product.CategoryDTO;
import com.dev.suacomanda.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.ok(categoryService.insert(categoryDTO));
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> update(@RequestBody CategoryDTO categoryDTO, @PathVariable(value = "id") String id) {
        return ResponseEntity.ok(categoryService.update(categoryDTO, id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
