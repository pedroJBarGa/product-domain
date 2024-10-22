package com.prueba.product.infrastructure.repositories.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "group_table")
public class GroupEntity {

  @Id
  @Column(name = "brand_id")
  private Integer brandId;

  @Column(name = "name")
  private String name;

}
