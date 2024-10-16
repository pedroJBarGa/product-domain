package com.prueba.product.infrastructure.repositories.mappers;

import com.prueba.product.domain.model.Product;
import com.prueba.product.infrastructure.repositories.entities.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product mapEntityToProduct(ProductEntity product) {
        return Product.builder()
                .productId(product.getProductId())
                .brandId(product.getGroup().getBrandId())
                .startDate(product.getStartDate().toLocalDateTime())
                .endDate(product.getEndDate().toLocalDateTime())
                .rateApply(product.getPriceList())
                .price(product.getPrice())
                .build();
    }
}
