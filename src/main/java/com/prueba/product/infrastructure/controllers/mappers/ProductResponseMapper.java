package com.prueba.product.infrastructure.controllers.mappers;

import com.prueba.product.domain.model.Product;
import com.prueba.product.infrastructure.controllers.rest.model.ProductResponse;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class ProductResponseMapper {

    public ProductResponse mapProductToProductResponse(final Product product) {
        ProductResponse response = new ProductResponse();

        response.productId(product.productId());
        response.brandId(product.brandId());
        response.startDate(OffsetDateTime.of(product.startDate(), ZoneOffset.UTC));
        response.endDate(OffsetDateTime.of(product.endDate(), ZoneOffset.UTC));
        response.rateApply(product.rateApply());
        response.finalPrice(product.price());

        return response;
    }
}
