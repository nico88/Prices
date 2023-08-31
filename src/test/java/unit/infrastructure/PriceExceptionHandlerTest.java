package unit.infrastructure;

import com.price.domain.dto.Response;
import com.price.domain.exception.PriceException;
import com.price.infrastructure.PriceExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

class PriceExceptionHandlerTest {

    @InjectMocks
    private PriceExceptionHandler priceExceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandlePriceException() {
        // Given
        PriceException ex = new PriceException("Custom Price Exception");
        // When
        Response response = priceExceptionHandler.handlePriceException(ex);
        // Then
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("Custom Price Exception");
    }

    @Test
    void testHandleGenericException() {
        // Given
        Exception ex = new Exception("Generic Exception");
        // When
        Response response = priceExceptionHandler.handleGenericException(ex);
        // Then
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("Internal Server Error");
    }
}
