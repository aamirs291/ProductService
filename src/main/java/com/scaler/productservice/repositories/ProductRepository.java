package com.scaler.productservice.repositories;

import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    Optional<Product> findById(Long productId);

    List<Product> findAll();

    @Query("""
    SELECT p FROM products p
    JOIN FETCH p.category""")
    List<Product> findAllWithCategory();

    Page<Product> findByTitleContainsIgnoreCase(String title, Pageable pageable);

    List<Product> findByPriceBetween(Double priceAfter, Double priceBefore);

    // List<Product> findByCategory(Category category);

    @Query("""
    SELECT p
    FROM products p
    JOIN FETCH p.category
    WHERE p.category.id = :categoryId""")
    List<Product> findByCategoryId(@Param("categoryId") Long categoryId);

    // List<Product> findByCategoryTitle(String categoryTitle);

    // Product save(Product product);

    // void deleteById(Long productId);
}
