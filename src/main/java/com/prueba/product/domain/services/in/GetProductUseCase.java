package com.prueba.product.domain.services.in;

import java.time.LocalDateTime;

import com.prueba.product.domain.model.Product;
import com.prueba.product.domain.query.GetProductQuery;

public interface GetProductUseCase {

  Product findProduct(final GetProductQuery productQuery);

}
