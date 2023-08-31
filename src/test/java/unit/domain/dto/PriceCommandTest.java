package unit.domain.dto;

import com.price.domain.dto.PriceCommand;
import com.price.domain.model.Price;
import mother.PriceMother;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class PriceCommandTest {

    @Test
    void testPriceCommandProperties() {
        // Given
        Price price = PriceMother.priceOne();
        // When
        PriceCommand priceCommand = PriceCommand.builder()
                .productId(price.getProductId())
                .brandId(price.getBrand().getBrandId())
                .priceList(price.getPriceList())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .price(price.getPrice())
                .curr(price.getCurr())
                .build();
        // Then
        assertThat(priceCommand.getProductId()).isEqualTo(price.getProductId());
        assertThat(priceCommand.getBrandId()).isEqualTo(price.getBrand().getBrandId());
        assertThat(priceCommand.getPriceList()).isEqualTo(price.getPriceList());
        assertThat(priceCommand.getStartDate()).isEqualTo(price.getStartDate());
        assertThat(priceCommand.getEndDate()).isEqualTo(price.getEndDate());
        assertThat(priceCommand.getPrice()).isEqualTo(price.getPrice());
        assertThat(priceCommand.getCurr()).isEqualTo(price.getCurr());
    }
}