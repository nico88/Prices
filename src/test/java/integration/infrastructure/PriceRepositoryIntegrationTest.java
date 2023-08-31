package integration.infrastructure;

import com.github.javafaker.Faker;
import com.price.Application;
import com.price.domain.model.Price;
import com.price.infrastructure.PriceRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.support.TestPropertySourceUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(classes = Application.class)
class PriceRepositoryIntegrationTest {

    @Autowired
    private PriceRepository priceRepository;

    @Test
    void testFindPricesWithPriorityByBrandAndProduct_FoundPrice() {
        // Given
        LocalDateTime date = LocalDateTime.parse("2020-06-14 16:00:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        var productId = 35455L;
        var brandId = 1L;
        // When
        List<Price> prices = priceRepository.findPricesWithPriorityByBrandAndProduct(date, productId, brandId);
        // Then
        assertThat(prices)
                .isNotNull()
                .hasSize(2)
                .allSatisfy(price -> {
                    assertThat(price.getStartDate()).isNotNull();
                    assertThat(price.getEndDate()).isNotNull();
                    assertThat(price.getPrice()).isGreaterThanOrEqualTo(0.0);
                });
    }

    @Test
    void testFindPricesWithPriorityByBrandAndProduct_NoPriceFound() {
        // Given
        final var random = new Faker();
        var date = LocalDateTime.now();
        var productId = random.random().nextLong();
        var brandId = random.random().nextLong();
        // When
        List<Price> prices = priceRepository.findPricesWithPriorityByBrandAndProduct(date, productId, brandId);
        // Then
        assertThat(prices).isEmpty();
    }

}
