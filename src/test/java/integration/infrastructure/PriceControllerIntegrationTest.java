package integration.infrastructure;

import com.price.Application;
import com.price.application.PriceQueryByProductAndBrand;
import com.price.domain.dto.PriceCommand;
import jakarta.transaction.Transactional;
import mother.PriceCommandMother;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

@Transactional
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private PriceQueryByProductAndBrand priceQueryByProductAndBrand;

    @Test
    public void testGetPriceForProductAndBrandEndpoint() {
        String date = "2020-06-14 16:00:00";
        Long productId = 35455L;
        Long brandId = 1L;
        PriceCommand expectedResponse = PriceCommandMother.generatePriceCommand();
        Mockito.when(priceQueryByProductAndBrand.execute(
                        LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        productId,
                        brandId))
                .thenReturn(expectedResponse);
        ResponseEntity<PriceCommand> response = restTemplate.exchange(
                "/api/v1/price?date={date}&productId={productId}&brandId={brandId}",
                HttpMethod.GET,
                null,
                PriceCommand.class,
                date, productId, brandId
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

}