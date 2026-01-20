package com.scaler.productservice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.scaler.productservice.dtos.CategoryDTO;
import com.scaler.productservice.exceptions.CategoryNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.repositories.CategoryRepository;

@Service
public class DefaultCategoryService implements CategoryService{

    private CategoryRepository categoryRepository;

    public DefaultCategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        
        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        List<Category> categoryList = categoryRepository.findAll();

        for (Category category: categoryList){
            categoryDTOList.add(convertToCategoryDTO(category));
        }

        return categoryDTOList;
    }

    @Override
    public CategoryDTO getCategoryById(Long categoryId) throws CategoryNotFoundException {
        
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Category not present"));
        CategoryDTO categoryDTO = convertToCategoryDTO(category);

        return categoryDTO;
    }


    private CategoryDTO convertToCategoryDTO(Category category){

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(category.getId());
        categoryDTO.setCategoryTitle(category.getTitle());

        return categoryDTO;
    }
    
}
