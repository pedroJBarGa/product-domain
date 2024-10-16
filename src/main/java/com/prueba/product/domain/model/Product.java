package com.prueba.product.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Product {

  private long productId;
  private int brandId;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private int rateApply;
  private BigDecimal price;

}
