package com.dev.suacomanda.domain.product;

import com.dev.suacomanda.domain.category.Category;
import lombok.AllArgsConstructor;
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
    private Category category;

    public Product fromDTO(ProductDTO productDTO, Category category) {
        this.title = productDTO.title();
        this.description = productDTO.description();
        this.price = productDTO.price();
        this.ownerId = productDTO.ownerId();
        this.category = category;
        return this;
    }
}
