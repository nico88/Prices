package unit.domain.exception;

import com.price.domain.exception.PriceException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class PriceExceptionTest {

    @Test
    void testPriceExceptionMessage() {
        // Given
        String errorMessage = "An error occurred while processing the price";
        // When
        PriceException exception = new PriceException(errorMessage);
        // Then
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }
}