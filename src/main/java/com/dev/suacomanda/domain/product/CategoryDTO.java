package com.dev.suacomanda.domain.product;

import com.dev.suacomanda.domain.category.Category;

public record CategoryDTO(String id, String title, String description, String ownerId) {

    public Category toCategory() {
        return new Category(this.id, this.title, this.description, this.ownerId);
    }
}
