package com.dev.suacomanda.service;

import com.dev.suacomanda.domain.aws.MessageDTO;
import com.dev.suacomanda.domain.product.Product;
import com.dev.suacomanda.domain.product.ProductDTO;
import com.dev.suacomanda.domain.product.exception.ProductNotFoundException;
import com.dev.suacomanda.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    private final MessageService messageService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, MessageService messageService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.messageService = messageService;
    }

    public Product insert(ProductDTO productDTO) {
        categoryService.findById(productDTO.categoryId());
        Product product = productRepository.insert(new Product().fromDTO(productDTO));

        messageService.sendMessage(new MessageDTO(product.getOwnerId()));
        return product;
    }

    public Product update(ProductDTO productDTO, String id) {
        Product product = productRepository.findById(id).orElseThrow(throwProductNotFoundException());


        if(!productDTO.title().isEmpty()) product.setTitle(productDTO.title());
        if(!productDTO.description().isEmpty()) product.setDescription(productDTO.description());
        if(Objects.nonNull(productDTO.price())) product.setPrice(productDTO.price());

        if(Objects.nonNull(productDTO.categoryId())) {
            categoryService.findById(productDTO.categoryId());
            product.setCategory(productDTO.categoryId());
        }

        productRepository.save(product);

        messageService.sendMessage(new MessageDTO(product.getOwnerId()));

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
