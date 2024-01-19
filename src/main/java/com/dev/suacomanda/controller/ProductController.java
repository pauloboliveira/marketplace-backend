package com.dev.suacomanda.controller;

import com.dev.suacomanda.domain.product.Product;
import com.dev.suacomanda.domain.product.ProductDTO;
import com.dev.suacomanda.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(productService.insert(productDTO));
    }
}
