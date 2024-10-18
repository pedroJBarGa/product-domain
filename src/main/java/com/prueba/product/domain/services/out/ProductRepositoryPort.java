package com.prueba.product.domain.services.out;

import com.prueba.product.domain.model.Product;
import com.prueba.product.domain.query.GetProductQuery;

import java.time.LocalDateTime;

public interface ProductRepositoryPort {
    Product findProduct(final GetProductQuery productQuery);
}
