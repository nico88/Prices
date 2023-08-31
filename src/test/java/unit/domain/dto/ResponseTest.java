package unit.domain.dto;

import com.price.domain.dto.Response;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ResponseTest {

    @Test
    void testResponseProperties() {
        // Given
        String message = "Test message";
        String time = "2023-08-29T12:34:56";
        // When
        Response response = Response.builder()
                .message(message)
                .time(time)
                .build();
        // Then
        assertThat(response.getMessage()).isEqualTo(message);
        assertThat(response.getTime()).isEqualTo(time);
    }
}