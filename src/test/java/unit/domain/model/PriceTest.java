package unit.domain.model;

import com.price.domain.model.Brand;
import com.price.domain.model.Price;
import mother.BrandMother;
import mother.PriceMother;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PriceTest {

    @Test
    void testPriceProperties() {
        // Given
        Brand brand = BrandMother.generateBrand();
        LocalDateTime startDate = LocalDateTime.parse("2020-06-14T00:00:00");
        LocalDateTime endDate = LocalDateTime.parse("2020-12-31T23:59:59");
        int priceList = 1;
        Long productId = 35455L;
        int priority = 0;
        double price = 35.50;
        String curr = "EUR";
        // When
        Price priceEntity = PriceMother.priceOne();
        // Then
        assertThat(priceEntity.getBrand()).isEqualTo(brand);
        assertThat(priceEntity.getStartDate()).isEqualTo(startDate);
        assertThat(priceEntity.getEndDate()).isEqualTo(endDate);
        assertThat(priceEntity.getPriceList()).isEqualTo(priceList);
        assertThat(priceEntity.getProductId()).isEqualTo(productId);
        assertThat(priceEntity.getPriority()).isEqualTo(priority);
        assertThat(priceEntity.getPrice()).isEqualTo(price);
        assertThat(priceEntity.getCurr()).isEqualTo(curr);
    }
}