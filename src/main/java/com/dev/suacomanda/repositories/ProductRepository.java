package com.dev.suacomanda.repositories;

import com.dev.suacomanda.domain.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
