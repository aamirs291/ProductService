package com.scaler.productservice.services;

import com.scaler.productservice.dtos.CategoryDTO;
import com.scaler.productservice.dtos.ProductRequestDTO;
import com.scaler.productservice.dtos.ProductResponseDTO;
import com.scaler.productservice.exceptions.CategoryNotFoundException;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    ProductResponseDTO getSingleProduct(Long productId) throws ProductNotFoundException;

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO createProduct(ProductRequestDTO productRequest) throws CategoryNotFoundException;

    void deleteProduct(Long productId);

    List<ProductResponseDTO> getProductsByCategory(Long categoryId);

    Page<Product> getProductsByTitle(String title, int pageNumber, int pageSize);

}
