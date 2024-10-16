package com.prueba.product.domain.services.out;

import com.prueba.product.domain.model.Product;

import java.time.LocalDateTime;

public interface ProductRepositoryPort {
    Product findProduct(final long productId, final Integer brand, final LocalDateTime applicationDate);
}
