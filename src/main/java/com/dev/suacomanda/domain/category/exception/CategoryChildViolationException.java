package com.dev.suacomanda.domain.category.exception;

public class CategoryChildViolationException extends RuntimeException {
    public CategoryChildViolationException(String message) {
        super(message);
    }
}
