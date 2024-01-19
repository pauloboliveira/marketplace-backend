package com.dev.suacomanda.domain.product;

import com.dev.suacomanda.domain.category.Category;

public record CategoryDTO(String title, String description, String ownerId) {

    public Category toCategory() {
        return new Category(this.title, this.description, this.ownerId);
    }
}
