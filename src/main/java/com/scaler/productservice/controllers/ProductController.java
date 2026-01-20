package com.scaler.productservice.controllers;

import com.scaler.productservice.dtos.ProductRequestDTO;
import com.scaler.productservice.dtos.ProductResponseDTO;
import com.scaler.productservice.exceptions.CategoryNotFoundException;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final RestTemplate restTemplate;

    public ProductController(@Qualifier("defaultProductService") ProductService productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        ProductResponseDTO productResponseDTO = productService.getSingleProduct(id);
        return ResponseEntity.ok(productResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        //return new ArrayList<>();
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping(params = "categoryId")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByCategory(@RequestParam Long categoryId){
        List<ProductResponseDTO> productResponseDTOs = productService.getProductsByCategory(categoryId);
        return ResponseEntity.ok(productResponseDTOs);
    }

    @PostMapping
    public ProductResponseDTO createProduct(@RequestBody ProductRequestDTO productRequestDTO) throws CategoryNotFoundException {
        return productService.createProduct(productRequestDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ProductNotFoundExceptionDTO> ProductNotFoundExceptionHandler(ProductNotFoundException e){
//        ProductNotFoundExceptionDTO productNotFoundExceptionDTO = new ProductNotFoundExceptionDTO();
//        productNotFoundExceptionDTO.setId(e.getId());
////        System.out.println(e.getId());
//        productNotFoundExceptionDTO.setMessage("Product Not Found");
//        productNotFoundExceptionDTO.setResolutionDetails("Add the product first and then search");
//
//        return new ResponseEntity<>(productNotFoundExceptionDTO, HttpStatus.UNAUTHORIZED);
//    }

}
