package com.prueba.product.infrastructure.repositories.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "group_table")
public class GroupEntity {

  @Id
  @Column(name = "brand_id")
  private Integer brandId;

  @Column(name = "name")
  private String name;

  public GroupEntity() {

  }

  public GroupEntity(Integer brandId, String name) {
    this.brandId = brandId;
    this.name = name;
  }
}
