package com.dev.suacomanda.domain.category;

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

    public Category(String title, String description, String ownerId) {
        this.title = title;
        this.description = description;
        this.ownerId = ownerId;
    }
}
