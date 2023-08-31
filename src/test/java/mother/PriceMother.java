package mother;

import com.price.domain.model.Price;

import java.time.LocalDateTime;

public class PriceMother {

    public static Price priceOne() {
        return new Price(
                BrandMother.generateBrand(),
                LocalDateTime.parse("2020-06-14T00:00:00"),
                LocalDateTime.parse("2020-12-31T23:59:59"),
                1,
                35455L,
                0,
                35.50,
                "EUR");
    }

    public static Price priceTwo() {
        return new Price(
                BrandMother.generateBrand(),
                LocalDateTime.parse("2020-06-14T15:00:00"),
                LocalDateTime.parse("2020-06-14T18:30:00"),
                2,
                35455L,
                1,
                25.45,
                "EUR");
    }

    public static Price priceThree() {
        return new Price(
                BrandMother.generateBrand(),
                LocalDateTime.parse("2020-06-15T00:00:00"),
                LocalDateTime.parse("2020-06-15T11:00:00"),
                3,
                35455L,
                1,
                30.50,
                "EUR");
    }

    public static Price priceFour() {
        return new Price(
                BrandMother.generateBrand(),
                LocalDateTime.parse("2020-06-15T16:00:00"),
                LocalDateTime.parse("2020-12-31T23:59:59"),
                4,
                35455L,
                1,
                38.95,
                "EUR");
    }

}
