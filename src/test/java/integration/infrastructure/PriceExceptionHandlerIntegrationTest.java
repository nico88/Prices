package integration.infrastructure;

import com.price.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class PriceExceptionHandlerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHandlePriceException() throws Exception {
        // Given
        String errorMessage = "Custom error message";
        String date = "2020-06-14T10:00:00";
        long productId = 35455L;
        long brandId = 1L;
        // When and Then
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(
                                "/api/v1/price")
                        .param("date", date)
                        .param("productId", Long.toString(productId))
                        .param("brandId", Long.toString(brandId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andReturn();
    }

    @Test
    void testHandleGenericException() throws Exception {
        // When and Then
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(
                                "/api/v1/price")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andReturn();
    }
}