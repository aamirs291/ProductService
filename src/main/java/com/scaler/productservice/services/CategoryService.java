package com.scaler.productservice.services;

import java.util.List;

import com.scaler.productservice.dtos.CategoryDTO;
import com.scaler.productservice.exceptions.CategoryNotFoundException;

public interface CategoryService {
    
    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(Long categoryId) throws CategoryNotFoundException;
}
