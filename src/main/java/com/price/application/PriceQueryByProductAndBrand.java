package com.price.application;

import com.price.infrastructure.PriceRepository;
import com.price.domain.dto.PriceCommand;
import com.price.domain.dto.Response;
import com.price.domain.exception.PriceException;
import com.price.domain.model.Price;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class PriceQueryByProductAndBrand {

    private final PriceRepository priceRepository;

    public PriceQueryByProductAndBrand(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Object execute(LocalDateTime date, Long productId, Long brandId) {
        try {
            List<Price> prices = priceRepository.findPricesWithPriorityByBrandAndProduct(date, productId, brandId);
            if (prices.isEmpty()) {
                log.warn("No price found for the given parameters - date: {}, productId: {}, brandId: {}", date, productId, brandId);
                return createNoPriceResponse();
            }
            log.info("Price found for date: {}, productId: {}, brandId: {}", date, productId, brandId);
            Price priceToApply = prices.get(0);
            return createPriceCommand(priceToApply);
        } catch (Exception e) {
            String message = String.format("An unexpected error occurred - date: %s, productId: %s, brandId: %s", date, productId, brandId);
            log.error(message, e);
            throw new PriceException(message);
        }
    }

    private Response createNoPriceResponse() {
        return Response.builder()
                .message("No price found for the given parameters")
                .time(String.valueOf(Instant.now()))
                .build();
    }

    private PriceCommand createPriceCommand(Price price) {
        return PriceCommand.builder()
                .productId(price.getProductId())
                .brandId(price.getBrand().getBrandId())
                .priceList(price.getPriceList())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .price(price.getPrice())
                .curr(price.getCurr())
                .build();
    }
}