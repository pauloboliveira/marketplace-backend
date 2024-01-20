package com.dev.suacomanda.domain.category;

import com.dev.suacomanda.domain.product.CategoryDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    private String id;

    private String title;

    private String description;

    private String ownerId;

    public Category(CategoryDTO categoryDTO) {
        this.title = categoryDTO.title();
        this.description = categoryDTO.description();
        this.ownerId = categoryDTO.ownerId();
    }

    public Category(String id, String title, String description, String ownerId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ownerId = ownerId;
    }
}
