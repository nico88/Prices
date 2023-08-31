package unit.application;

import com.price.application.PriceQueryByProductAndBrand;
import com.price.domain.dto.PriceCommand;
import com.price.domain.dto.Response;
import com.price.domain.exception.PriceException;
import com.price.infrastructure.PriceRepository;
import mother.PriceCommandMother;
import mother.PriceMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PriceQueryByProductAndBrandTest {

    @Mock
    private PriceRepository repository;

    @InjectMocks
    private PriceQueryByProductAndBrand priceQueryByProductAndBrand;

    @Test
    void testInvoke_PriceFound_ReturnsPricesCommand() {
        // Given
        Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime date = LocalDateTime.parse("2020-06-14 10:00:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        // When
        Mockito.when(repository.findPricesWithPriorityByBrandAndProduct(date, productId, brandId))
                .thenReturn(List.of(PriceMother.priceTwo(), PriceMother.priceOne()));
        Object result = priceQueryByProductAndBrand.execute(date, productId, brandId);
        // Then
        assertNotNull(result);
        assertTrue(result instanceof PriceCommand);
        PriceCommand priceCommand = (PriceCommand) result;
        PriceCommand expectedPriceCommand = PriceCommandMother.generatePriceCommand();
        assertEquals(expectedPriceCommand, priceCommand);
    }

    @Test
    void testInvoke_PriceNotFound_ReturnsResponseWithMessage() {
        // Given
        Long productId = new Random().nextLong(10000);
        Long brandId = 1L;
        LocalDateTime date = LocalDateTime.parse("2020-06-14 10:00:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String expectedMessage = "No price found for the given parameters";
        // When
        Mockito.when(repository.findPricesWithPriorityByBrandAndProduct(date, productId, brandId))
                .thenReturn(Collections.emptyList());
        Object result = priceQueryByProductAndBrand.execute(date, productId, brandId);
        // Then
        assertNotNull(result);
        assertTrue(result instanceof Response);
        Response response = (Response) result;
        assertEquals(expectedMessage, response.getMessage());
        assertNotNull(response.getTime());
    }

    @Test
    void testInvoke_ExceptionOccurs_ThrowsPriceException() {
        // Given
        Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime date = LocalDateTime.parse("2020-06-14 10:00:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String errorMessage = "Error occurred while searching prices";
        // When
        Mockito.when(repository.findPricesWithPriorityByBrandAndProduct(date, productId, brandId))
                .thenThrow(new RuntimeException(errorMessage));
        // Then
        assertThrows(PriceException.class, () -> priceQueryByProductAndBrand.execute(date, productId, brandId));
    }
}