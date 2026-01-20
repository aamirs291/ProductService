package com.scaler.productservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
// import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity(name="categories")
@NoArgsConstructor
public class Category extends BaseModel implements Serializable {
    @Column(unique=true, nullable=false)
    private String title;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Product> productList;

//    @JsonIgnore
//    @JsonBackReference
//    @JsonManagedReference
//    @OneToMany(fetch = jakarta.persistence.FetchType.EAGER, mappedBy = "category")
//    private List<Product> productList;

    public Category(String title){
        this.title = title;
    }
}
