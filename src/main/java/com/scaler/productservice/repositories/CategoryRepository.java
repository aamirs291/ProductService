package com.scaler.productservice.repositories;

import com.scaler.productservice.models.*;
//import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findById(Long categoryId);

    Optional<Category> findByTitle(String name);

    List<Category> findAll();
}
