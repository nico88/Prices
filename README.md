# API Prices
API to query prices to be applied based on a product code, a brand code and a date.

## Stack
- Java 17
- SpringBoot 3.1.3
- Maven
- H2 DataBase(in memory)
- Flyway
- JUnit
- Mockito

## Endpoint
curl --location 'http://localhost:8080/api/v1/price?date={date}&productId={productId}&brandId={brandId}'

## Author
Nicolas Gomez