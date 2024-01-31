package com.dev.suacomanda.domain.product;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "product")
@Data
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    private String title;
    private String description;
    private Double price;
    private String ownerId;
    private String category;

    public Product fromDTO(ProductDTO productDTO) {
        this.title = productDTO.title();
        this.description = productDTO.description();
        this.price = productDTO.price();
        this.ownerId = productDTO.ownerId();
        this.category = productDTO.categoryId();
        return this;
    }
}
