package com.prueba.product.infrastructure.controllers.rest;

import java.time.OffsetDateTime;

import com.prueba.product.domain.query.GetProductQuery;
import com.prueba.product.domain.services.in.GetProductUseCase;
import com.prueba.product.domain.model.Product;
import com.prueba.product.infrastructure.controllers.mappers.ProductResponseMapper;
import com.prueba.product.infrastructure.controllers.rest.model.ProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProductController implements ProductApi {

  private final GetProductUseCase service;
  private final ProductResponseMapper mapper;

  @Override
  public ResponseEntity<ProductResponse> getProduct(Long productId, Integer brand,
      OffsetDateTime applicationDate) {
    Product product = service.findProduct(GetProductQuery.builder()
            .productId(productId)
            .brand(brand)
            .applicationDate(applicationDate.toLocalDateTime())
            .build());
    return ResponseEntity.ok(mapper.mapProductToProductResponse(product));
  }



}
