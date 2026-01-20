package com.scaler.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity(name="products")
@NoArgsConstructor
public class Product extends BaseModel implements Serializable{

//    private Long id;
    private String title;
    private String description;
    private Long price;
    private String imageUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id", nullable= false)
//    @JsonManagedReference
//    @JsonBackReference
    private Category category;

}
