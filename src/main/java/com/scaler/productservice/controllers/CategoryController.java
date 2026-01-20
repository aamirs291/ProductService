package com.scaler.productservice.controllers;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scaler.productservice.dtos.CategoryDTO;
import com.scaler.productservice.exceptions.CategoryNotFoundException;
import com.scaler.productservice.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){

        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getSingleProduct(@PathVariable("id") Long id) throws CategoryNotFoundException {
        CategoryDTO categoryDTO = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){

        List<CategoryDTO> categoryDTOList = categoryService.getAllCategories();

        return ResponseEntity.ok(categoryDTOList);

    }

}
