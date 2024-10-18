package com.prueba.product.infrastructure.repositories.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
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

  @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "brand_id")
  private GroupEntity group;

  public ProductEntity() {
  }

  public ProductEntity(Integer id, long productId, Timestamp startDate,
                       Timestamp endDate, int priceList, int priority,
                       BigDecimal price, String curr, GroupEntity group) {
    this.id = id;
    this.productId = productId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.priceList = priceList;
    this.priority = priority;
    this.price = price;
    this.curr = curr;
    this.group = group;
  }
}
