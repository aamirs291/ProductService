package com.scaler.productservice.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {

    @NotBlank
    private String title;
    private String description;
    private long price;
    private String imageUrl;

    @NotNull
    private String category;
}
