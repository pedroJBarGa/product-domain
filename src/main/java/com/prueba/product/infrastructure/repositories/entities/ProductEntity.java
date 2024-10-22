package com.prueba.product.infrastructure.repositories.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class ProductEntity {

  @Id
  private Integer id;

  @Column(name = "product_id")
  private long productId;

  @Column(name = "start_date")
  private Timestamp startDate;

  @Column(name = "end_date")
  private Timestamp endDate;

  @Column(name = "price_list")
  private int priceList;

  @Column(name = "priority")
  private int priority;

  @Column(name = "price")
  private BigDecimal price;

  @Column(name = "curr")
  private String curr;

  @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "brand_id")
  private GroupEntity group;

}
