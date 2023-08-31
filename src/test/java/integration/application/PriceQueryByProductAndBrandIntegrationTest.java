package integration.application;

import com.github.javafaker.Faker;
import com.price.Application;
import com.price.application.PriceQueryByProductAndBrand;
import com.price.domain.dto.PriceCommand;
import com.price.domain.dto.Response;
import com.price.domain.exception.PriceException;
import com.price.infrastructure.PriceRepository;
import mother.PriceCommandMother;
import mother.PriceMother;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ActiveProfiles("test")
@SpringBootTest(classes = Application.class)
class PriceQueryByProductAndBrandIntegrationTest {

    @Autowired
    private PriceQueryByProductAndBrand priceQueryByProductAndBrand;

    @MockBean
    private PriceRepository repository;

    @Test
    void testExecute_PriceFound_ReturnsPriceCommand() {
        // Given
        LocalDateTime date = LocalDateTime.parse("2020-06-14 16:00:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Long productId = 35455L;
        Long brandId = 1L;
        PriceCommand expectedPriceCommand = PriceCommandMother.generatePriceCommand();
        Mockito.when(repository.findPricesWithPriorityByBrandAndProduct(date, productId, brandId))
                .thenReturn(List.of(PriceMother.priceTwo()));
        // When
        Object result = priceQueryByProductAndBrand.execute(date, productId, brandId);
        // Then
        assertThat(result).isNotNull().isInstanceOf(PriceCommand.class);
        PriceCommand priceCommand = (PriceCommand) result;
        assertThat(priceCommand.getProductId()).isEqualTo(expectedPriceCommand.getProductId());
        assertThat(priceCommand.getBrandId()).isEqualTo(expectedPriceCommand.getBrandId());
        assertThat(priceCommand.getPriceList()).isEqualTo(expectedPriceCommand.getPriceList());
        assertThat(priceCommand.getStartDate()).isEqualTo(expectedPriceCommand.getStartDate());
        assertThat(priceCommand.getEndDate()).isEqualTo(expectedPriceCommand.getEndDate());
        assertThat(priceCommand.getPrice()).isEqualTo(expectedPriceCommand.getPrice());
        assertThat(priceCommand.getCurr()).isEqualTo(expectedPriceCommand.getCurr());
    }

    @Test
    void testExecute_PriceNotFound_ReturnsResponseWithMessage() {
        // Given
        final var random = new Faker();
        LocalDateTime date = LocalDateTime.now();
        Long productId = random.random().nextLong();
        Long brandId = random.random().nextLong();
        Mockito.when(repository.findPricesWithPriorityByBrandAndProduct(date, productId, brandId))
                .thenReturn(Collections.emptyList());
        // When
        Object result = priceQueryByProductAndBrand.execute(date, productId, brandId);
        // Then
        assertThat(result).isNotNull().isInstanceOf(Response.class);
        Response response = (Response) result;
        assertThat(response.getMessage()).isEqualTo("No price found for the given parameters");
    }

    @Test
    void testExecute_ExceptionOccurs_ThrowsPriceException() {
        // Given
        final var random = new Faker();
        LocalDateTime date = LocalDateTime.now();
        Long productId = random.random().nextLong();
        Long brandId = random.random().nextLong();
        // When
        Mockito.when(repository.findPricesWithPriorityByBrandAndProduct(date, productId, brandId))
                .thenThrow(new RuntimeException("Simulated database error"));
        // Then
        assertThatThrownBy(() -> priceQueryByProductAndBrand.execute(date, productId, brandId))
                .isInstanceOf(PriceException.class)
                .hasMessageContaining("An unexpected error occurred");
    }
}