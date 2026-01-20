package com.scaler.productservice.services;

import com.scaler.productservice.dtos.CategoryDTO;
import com.scaler.productservice.dtos.ProductRequestDTO;
import com.scaler.productservice.dtos.ProductResponseDTO;
import com.scaler.productservice.exceptions.CategoryNotFoundException;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.CategoryRepository;
import com.scaler.productservice.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("defaultProductService")
public class DefaultProductService implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public DefaultProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductResponseDTO getSingleProduct(Long productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
        ProductResponseDTO productResponseDTO = convertToProductResponseDTO(product);

        return productResponseDTO;
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();

        List<Product> productList =  productRepository.findAllWithCategory();

        for (Product product: productList){
            ProductResponseDTO productResponseDTO = convertToProductResponseDTO(product);
            productResponseDTOList.add(productResponseDTO);
        }

        return productResponseDTOList;
    }

    @Override
    public List<ProductResponseDTO> getProductsByCategory(Long categoryId) {
        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();
        List<Product> productList = productRepository.findByCategoryId(categoryId);

        for (Product product: productList){
            ProductResponseDTO productResponseDTO = convertToProductResponseDTO(product);
            productResponseDTOList.add(productResponseDTO);
        }


        return productResponseDTOList;
    }



    @Override
    public Page<Product> getProductsByTitle(String title, int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequest) throws CategoryNotFoundException{
        
        String categoryTitle = productRequest.getCategory();

        // if (category == null) {
        //     throw new CategoryNotFoundException("Add a category to the product first");
        // }

        Category category = categoryRepository.findByTitle(categoryTitle)
                                            .orElseGet(()-> categoryRepository.save(new Category(categoryTitle)));

        // CategoryDTO categoryDTO = convertToCategoryDTO(category);

        Product product = new Product();
        product.setDescription(productRequest.getDescription());
        product.setImageUrl(productRequest.getImageUrl());
        product.setPrice(productRequest.getPrice());
        product.setTitle(productRequest.getTitle());
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        ProductResponseDTO productResponseDTO = convertToProductResponseDTO(savedProduct);
        return productResponseDTO;
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    private ProductResponseDTO convertToProductResponseDTO(Product product){

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setProductId(product.getId());
        productResponseDTO.setTitle(product.getTitle());
        productResponseDTO.setDescription(product.getDescription());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setImageUrl(product.getImageUrl());

        Category category = product.getCategory();
        CategoryDTO categoryDTO = convertToCategoryDTO(category);

        productResponseDTO.setCategory(categoryDTO);

        return productResponseDTO;
    }

    private CategoryDTO convertToCategoryDTO(Category category){

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(category.getId());
        categoryDTO.setCategoryTitle(category.getTitle());

        return categoryDTO;
    }
}

//19:45:22	DROP TABLE `product_service`.`categories`	Error Code: 3730. Cannot drop table 'categories' referenced by a foreign key constraint 'FKay05pwbasmn71505wo72iy466' on table 'prod'.	0.0015 sec
//19:46:13	DROP TABLE `product_service`.`categories`	Error Code: 3730. Cannot drop table 'categories' referenced by a foreign key constraint 'FKog2rp4qthbtt2lfyhfo32lsw9' on table 'products'.	0.00087 sec
