package com.dev.suacomanda.service;

import com.dev.suacomanda.domain.product.Product;
import com.dev.suacomanda.domain.product.ProductDTO;
import com.dev.suacomanda.repositories.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product insert(ProductDTO productDTO) {
        return productRepository.insert(new Product(productDTO));
    }
}
