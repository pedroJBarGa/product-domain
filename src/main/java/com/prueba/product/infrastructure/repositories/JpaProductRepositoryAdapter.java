package com.prueba.product.infrastructure.repositories;

import com.prueba.product.domain.exception.ProductNotFoundException;
import com.prueba.product.domain.model.Product;
import com.prueba.product.domain.query.GetProductQuery;
import com.prueba.product.domain.services.out.ProductRepositoryPort;
import com.prueba.product.infrastructure.repositories.mappers.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class JpaProductRepositoryAdapter implements ProductRepositoryPort {

    private final JpaProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public Product findProduct(final GetProductQuery productQuery) {
        return mapper.mapEntityToProduct(repository.findProduct(productQuery.productId(), productQuery.brand(), productQuery.applicationDate())
                .orElseThrow(() ->new ProductNotFoundException(productQuery.productId())));
    }

}
