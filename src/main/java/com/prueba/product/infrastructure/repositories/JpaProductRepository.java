package com.prueba.product.infrastructure.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import com.prueba.product.infrastructure.repositories.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, Integer> {

  @Query(value = "SELECT p "
      + "FROM ProductEntity p "
      + "WHERE p.group.brandId = :brand and p.productId = :productId and :applicationDate BETWEEN p.startDate and p.endDate "
      + "ORDER BY p.priority DESC "
      + "LIMIT 1")
  Optional<ProductEntity> findProduct(final long productId, final Integer brand, final LocalDateTime applicationDate);
}
