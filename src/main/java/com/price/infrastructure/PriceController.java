package com.price.infrastructure;

import com.price.application.PriceQueryByProductAndBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/v1")
public class PriceController {

    private final PriceQueryByProductAndBrand priceQueryByProductAndBrand;

    @Autowired
    public PriceController(PriceQueryByProductAndBrand priceQueryByProductAndBrand) {
        this.priceQueryByProductAndBrand = priceQueryByProductAndBrand;
    }

    @GetMapping("/price")
    public ResponseEntity<?> getPriceForProductAndBrand(
            @RequestParam("date") String date,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(priceQueryByProductAndBrand.execute(LocalDateTime.parse(date,
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), productId, brandId));
    }

}