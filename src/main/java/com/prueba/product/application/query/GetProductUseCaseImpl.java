package com.prueba.product.application.query;

import com.prueba.product.domain.query.GetProductQuery;
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
  public Product findProduct(final GetProductQuery productQuery) {
    return adapter.findProduct(productQuery);
  }

}
