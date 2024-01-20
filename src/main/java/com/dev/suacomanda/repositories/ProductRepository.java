package com.dev.suacomanda.repositories;

import com.dev.suacomanda.domain.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<List<Product>> findByCategory_Id(String categoryId);
}
