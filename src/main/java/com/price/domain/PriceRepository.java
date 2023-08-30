package com.price.domain;

import com.price.domain.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p " +
            "WHERE p.brand.brandId = :brandId " +
            "AND p.productId = :productId " +
            "AND p.startDate <= :date " +
            "AND p.endDate >= :date " +
            "ORDER BY p.priority DESC")
    List<Price> findPricesWithPriorityByBrandAndProduct(@Param("date") LocalDateTime date,
                                                        @Param("productId") Long productId,
                                                        @Param("brandId") Long brandId);

}