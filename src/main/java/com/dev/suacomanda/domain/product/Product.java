package com.dev.suacomanda.domain.product;

import com.dev.suacomanda.domain.category.Category;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "product")
@Data
public class Product {
    @Id
    private String id;
    private String title;
    private String description;
    private Double price;
    private String ownerId;
    private Category category;

    public Product(ProductDTO productDTO) {
        this.title = productDTO.title();
        this.description = productDTO.description();
        this.price = productDTO.price();
        this.ownerId = productDTO.ownerId();
        this.category = productDTO.categoryDTO().toCategory();
    }
}
