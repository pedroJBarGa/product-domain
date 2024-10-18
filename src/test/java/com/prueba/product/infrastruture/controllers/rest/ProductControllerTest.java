package com.prueba.product.infrastruture.controllers.rest;

import com.prueba.product.domain.exception.ProductNotFoundException;
import com.prueba.product.domain.model.Product;
import com.prueba.product.domain.query.GetProductQuery;
import com.prueba.product.domain.services.in.GetProductUseCase;
import com.prueba.product.infrastructure.controllers.mappers.ProductResponseMapper;
import com.prueba.product.infrastructure.controllers.rest.ProductController;
import com.prueba.product.infrastructure.controllers.rest.model.ProductResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    private static final long PRODUCT_ID = 35455;
    private static final int BRAND_VALUE = 1;
    private static final String DATE = "2020-06-14T10:00:00Z";

    @Mock
    private ProductResponseMapper mapper;
    @Mock
    private GetProductUseCase useCase;

    @InjectMocks
    private ProductController controller;

    @Test
    public void whenProductOKThenStatus200() {
        when(useCase.findProduct(build(PRODUCT_ID, BRAND_VALUE, LocalDateTime.parse(DATE, getDateTimeFormat())))).thenReturn(getProduct());
        when(mapper.mapProductToProductResponse(getProduct())).thenCallRealMethod();

        ResponseEntity<ProductResponse> productResponse = controller.getProduct(PRODUCT_ID,BRAND_VALUE, OffsetDateTime.parse("2020-06-14T10:00:00Z"));

        assertTrue(productResponse.getStatusCode().is2xxSuccessful());
        assertEquals(productResponse.getBody().getProductId(), PRODUCT_ID);
        assertEquals(productResponse.getBody().getBrandId(), BRAND_VALUE);
    }

    @Test
    public void whenProductNotFoundThen404() {
        when(useCase.findProduct(build(PRODUCT_ID, BRAND_VALUE, LocalDateTime.parse(DATE, getDateTimeFormat()))))
                .thenThrow(ProductNotFoundException.class);

        assertThrows(ProductNotFoundException.class,
                () -> controller.getProduct(PRODUCT_ID,BRAND_VALUE, OffsetDateTime.parse("2020-06-14T10:00:00Z")));
    }

    private Product getProduct() {
        return Product.builder()
                .productId(PRODUCT_ID)
                .brandId(BRAND_VALUE)
                .price(BigDecimal.valueOf(35455))
                .endDate(LocalDateTime.parse("2020-06-16T21:00:00Z", getDateTimeFormat()))
                .startDate(LocalDateTime.parse("2020-06-16T21:00:00Z", getDateTimeFormat()))
                .rateApply(1)
                .build();
    }

    private GetProductQuery build(final long productId, final Integer brandId, final LocalDateTime applicationDate) {
        return GetProductQuery.builder()
                .productId(productId)
                .brand(brandId)
                .applicationDate(applicationDate)
                .build();
    }

    private DateTimeFormatter getDateTimeFormat() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    }

}
