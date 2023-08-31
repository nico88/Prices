package mother;

import com.price.domain.dto.PriceCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PriceCommandMother {

    public static PriceCommand generatePriceCommand() {
        return PriceCommand.builder()
                .productId(35455L)
                .brandId(1L)
                .priceList(2)
                .startDate(LocalDateTime.parse("2020-06-14 15:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .endDate(LocalDateTime.parse("2020-06-14 18:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .price(25.45)
                .curr("EUR")
                .build();
    }

}