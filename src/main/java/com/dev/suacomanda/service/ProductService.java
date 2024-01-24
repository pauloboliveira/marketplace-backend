package com.dev.suacomanda.service;

import com.dev.suacomanda.domain.aws.MessageDTO;
import com.dev.suacomanda.domain.category.Category;
import com.dev.suacomanda.domain.product.Product;
import com.dev.suacomanda.domain.product.ProductDTO;
import com.dev.suacomanda.domain.product.exception.ProductNotFoundException;
import com.dev.suacomanda.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    private final SNSService snsService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, SNSService snsService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.snsService = snsService;
    }

    public Product insert(ProductDTO productDTO) {
        Category category = categoryService.findById(productDTO.categoryId());
        Product product = productRepository.insert(new Product().fromDTO(productDTO, category));

        snsService.sendMessage(new MessageDTO(product.getOwnerId()));
        return product;
    }

    public Product update(ProductDTO productDTO, String id) {
        Product product = productRepository.findById(id).orElseThrow(throwProductNotFoundException());
        Category category = categoryService.findById(productDTO.categoryId());

        if(!productDTO.title().isEmpty()) product.setTitle(productDTO.title());
        if(!productDTO.description().isEmpty()) product.setDescription(productDTO.description());
        if(Objects.nonNull(productDTO.price())) product.setPrice(productDTO.price());
        product.setCategory(category);

        productRepository.save(product);

        snsService.sendMessage(new MessageDTO(product.getOwnerId()));

        return product;
    }

    public void deleteById(String id) {
        productRepository.deleteById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(String id) {
        return productRepository.findById(id).orElseThrow(throwProductNotFoundException());
    }

    private static Supplier<ProductNotFoundException> throwProductNotFoundException() {
        return () -> new ProductNotFoundException("Não foi possível encontar o produto informado");
    }


}
