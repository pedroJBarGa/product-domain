package com.prueba.product.infrastruture.repositories;

import com.prueba.product.domain.exception.ProductNotFoundException;
import com.prueba.product.domain.model.Product;
import com.prueba.product.infrastructure.controllers.rest.model.ProductResponse;
import com.prueba.product.infrastructure.repositories.JpaProductRepository;
import com.prueba.product.infrastructure.repositories.JpaProductRepositoryAdapter;
import com.prueba.product.infrastructure.repositories.entities.GroupEntity;
import com.prueba.product.infrastructure.repositories.entities.ProductEntity;
import com.prueba.product.infrastructure.repositories.mappers.ProductMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JpaProductRepositoryAdapterTest {
    private static final long PRODUCT_ID = 35455;
    private static final int BRAND_VALUE = 1;
    private static final String DATE = "2020-06-14T10:00:00Z";

    @Mock
    private JpaProductRepository repository;
    @Mock
    private ProductMapper mapper;

    @InjectMocks
    private JpaProductRepositoryAdapter adapter;

    @Test
    public void whenProductExistsThenProductInfo() {
        Optional<ProductEntity> productEntity = getProductEntity();
        when(repository.findProduct(PRODUCT_ID, BRAND_VALUE, LocalDateTime.parse(DATE, getDateTimeFormat())))
                .thenReturn(productEntity);
        when(mapper.mapEntityToProduct(productEntity.get())).thenCallRealMethod();

        Product product = adapter.findProduct(PRODUCT_ID,BRAND_VALUE, LocalDateTime.parse(DATE, getDateTimeFormat()));

        assertEquals(product.getProductId(), PRODUCT_ID);
        assertEquals(product.getBrandId(), BRAND_VALUE);
    }

    @Test
    public void whenProductNotExistsThenProductNotFoundException() {
        when(repository.findProduct(PRODUCT_ID, BRAND_VALUE, LocalDateTime.parse(DATE, getDateTimeFormat())))
                .thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class,
                () -> adapter.findProduct(PRODUCT_ID,BRAND_VALUE, LocalDateTime.parse(DATE, getDateTimeFormat())));

    }

    private Optional<ProductEntity> getProductEntity() {
        return Optional.of(new ProductEntity(1, PRODUCT_ID
                , Timestamp.valueOf(LocalDateTime.parse("2020-06-16T10:00:00Z", getDateTimeFormat()))
                , Timestamp.valueOf(LocalDateTime.parse("2020-06-16T21:00:00Z", getDateTimeFormat()))
                , 1, 1, BigDecimal.valueOf(35455), "EUR", getGroupEntity()));
    }
    private GroupEntity getGroupEntity() {
        return new GroupEntity(1, "ZARA");
    }

    private DateTimeFormatter getDateTimeFormat() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    }
}
