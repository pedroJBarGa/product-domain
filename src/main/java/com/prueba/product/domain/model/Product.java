package com.prueba.product.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record Product(long productId, int brandId, LocalDateTime startDate,
                      LocalDateTime endDate, int rateApply, BigDecimal price) {

}
