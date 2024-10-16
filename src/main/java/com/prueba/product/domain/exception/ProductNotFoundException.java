package com.prueba.product.domain.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(final Long productId) {
        super("Product not found with id " + productId);
    }
}
