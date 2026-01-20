package com.scaler.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {

    private Long productId;
    private String title;
    private String description;
    private long price;
    private String imageUrl;
    private CategoryDTO category;
}
