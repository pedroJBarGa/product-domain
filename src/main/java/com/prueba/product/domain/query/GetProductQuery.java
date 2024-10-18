package com.prueba.product.domain.query;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record GetProductQuery(long productId, Integer brand, LocalDateTime applicationDate) {
}
