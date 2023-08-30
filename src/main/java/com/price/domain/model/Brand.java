package com.price.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "brand")
@Data
public class Brand {
    @Id
    private Long brandId;
    private String brandName;

}
