package com.prueba.product.domain.services.in;

import java.time.LocalDateTime;

import com.prueba.product.domain.model.Product;

public interface GetProductUseCase {

  Product findProduct(final long productId, final Integer brand, final LocalDateTime applicationDate);

}
