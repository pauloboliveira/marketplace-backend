package com.dev.suacomanda.domain.product;

public record ProductDTO(String title, String description, Double price, CategoryDTO categoryDTO, String ownerId) {
}
