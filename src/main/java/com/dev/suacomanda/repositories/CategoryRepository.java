package com.dev.suacomanda.repositories;

import com.dev.suacomanda.domain.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
