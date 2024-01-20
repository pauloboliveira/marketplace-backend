package com.dev.suacomanda.controller;

import com.dev.suacomanda.domain.product.Product;
import com.dev.suacomanda.domain.product.ProductDTO;
import com.dev.suacomanda.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(productService.insert(productDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@RequestBody ProductDTO productDTO, @PathVariable(value = "id") String id){
        return ResponseEntity.ok(productService.update(productDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(value = "id") String id){
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
