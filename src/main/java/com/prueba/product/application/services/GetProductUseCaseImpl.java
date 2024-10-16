package com.prueba.product.application.services;

import java.time.LocalDateTime;

import com.prueba.product.domain.services.in.GetProductUseCase;
import com.prueba.product.domain.model.Product;
import com.prueba.product.domain.services.out.ProductRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetProductUseCaseImpl implements GetProductUseCase {

  private final ProductRepositoryPort adapter;

  @Override
  public Product findProduct(final long productId, final Integer brand, final LocalDateTime applicationDate) {
    return adapter.findProduct(productId, brand, applicationDate);
  }

}
