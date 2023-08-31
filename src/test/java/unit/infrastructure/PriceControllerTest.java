package unit.infrastructure;

import com.price.application.PriceQueryByProductAndBrand;
import com.price.domain.dto.PriceCommand;
import com.price.infrastructure.PriceController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PriceControllerTest {

    @InjectMocks
    private PriceController priceController;

    @Mock
    private PriceQueryByProductAndBrand priceQueryByProductAndBrand;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test1() {
        LocalDateTime dateTime = LocalDateTime.parse("2023-07-14 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Long productId = 35455L;
        Long brandId = 1L;
        PriceCommand expectedResponse = PriceCommand.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(1)
                .startDate(LocalDateTime.parse("2023-07-14 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .endDate(LocalDateTime.parse("2023-12-31 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .price(35.50)
                .curr("EUR")
                .build();
        when(priceQueryByProductAndBrand.execute(dateTime, productId, brandId)).thenReturn(expectedResponse);
        ResponseEntity<?> responseEntity = priceController.getPriceForProductAndBrand(
                "2023-07-14 10:00:00", productId, brandId);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        PriceCommand actualResponse = (PriceCommand) responseEntity.getBody();
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(priceQueryByProductAndBrand, times(1)).execute(dateTime, productId, brandId);
    }

    @Test
    void test2() {
        LocalDateTime dateTime = LocalDateTime.parse("2023-07-14 16:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Long productId = 35455L;
        Long brandId = 1L;
        PriceCommand expectedResponse = PriceCommand.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(2)
                .startDate(LocalDateTime.parse("2023-07-14 15:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .endDate(LocalDateTime.parse("2023-07-14 18:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .price(25.45)
                .curr("EUR")
                .build();
        when(priceQueryByProductAndBrand.execute(dateTime, productId, brandId)).thenReturn(expectedResponse);
        ResponseEntity<?> responseEntity = priceController.getPriceForProductAndBrand(
                "2023-07-14 16:00:00", productId, brandId);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        PriceCommand actualResponse = (PriceCommand) responseEntity.getBody();
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(priceQueryByProductAndBrand, times(1)).execute(dateTime, productId, brandId);
    }

    @Test
    void test3() {
        LocalDateTime dateTime = LocalDateTime.parse("2023-07-14 21:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Long productId = 35455L;
        Long brandId = 1L;
        PriceCommand expectedResponse = PriceCommand.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(1)
                .startDate(LocalDateTime.parse("2023-07-14 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .endDate(LocalDateTime.parse("2023-12-31 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .price(35.50)
                .curr("EUR")
                .build();
        when(priceQueryByProductAndBrand.execute(dateTime, productId, brandId)).thenReturn(expectedResponse);
        ResponseEntity<?> responseEntity = priceController.getPriceForProductAndBrand(
                "2023-07-14 21:00:00", productId, brandId);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        PriceCommand actualResponse = (PriceCommand) responseEntity.getBody();
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(priceQueryByProductAndBrand, times(1)).execute(dateTime, productId, brandId);
    }

    @Test
    void test4() {
        LocalDateTime dateTime = LocalDateTime.parse("2023-07-15 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Long productId = 35455L;
        Long brandId = 1L;
        PriceCommand expectedResponse = PriceCommand.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(3)
                .startDate(LocalDateTime.parse("2023-07-15 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .endDate(LocalDateTime.parse("2023-07-15 11:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .price(30.50)
                .curr("EUR")
                .build();
        when(priceQueryByProductAndBrand.execute(dateTime, productId, brandId)).thenReturn(expectedResponse);
        ResponseEntity<?> responseEntity = priceController.getPriceForProductAndBrand(
                "2023-07-15 10:00:00", productId, brandId);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        PriceCommand actualResponse = (PriceCommand) responseEntity.getBody();
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(priceQueryByProductAndBrand, times(1)).execute(dateTime, productId, brandId);
    }

    @Test
    void test5() {
        LocalDateTime dateTime = LocalDateTime.parse("2023-07-16 21:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Long productId = 35455L;
        Long brandId = 1L;
        PriceCommand expectedResponse = PriceCommand.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(4)
                .startDate(LocalDateTime.parse("2023-07-16 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .endDate(LocalDateTime.parse("2023-07-31 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .price(38.95)
                .curr("EUR")
                .build();

        when(priceQueryByProductAndBrand.execute(dateTime, productId, brandId)).thenReturn(expectedResponse);
        ResponseEntity<?> responseEntity = priceController.getPriceForProductAndBrand(
                "2023-07-16 21:00:00", productId, brandId);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        PriceCommand actualResponse = (PriceCommand) responseEntity.getBody();
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(priceQueryByProductAndBrand, times(1)).execute(dateTime, productId, brandId);
    }

}