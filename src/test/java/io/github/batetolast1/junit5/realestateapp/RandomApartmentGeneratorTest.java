package io.github.batetolast1.junit5.realestateapp;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Testing RandomApartmentGenerator class")
class RandomApartmentGeneratorTest {

    private static final double MAX_MULTIPLIER = 4.0;

    @Nested
    @DisplayName("Testing constructor with default parameters")
    class defaultConstructorTests {

        private RandomApartmentGenerator generator;

        @BeforeEach
        void setGenerator() {
            this.generator = new RandomApartmentGenerator();
        }

        @RepeatedTest(value = 10)
        @DisplayName("Generate correct Apartment objects with default minimal area and price")
        void should_GenerateCorrectApartment_When_DefaultMinAreaMinPrice(TestReporter testReporter) {
            // given
            double minArea = 30.0;
            double maxArea = minArea * MAX_MULTIPLIER;

            BigDecimal minPricePerSquareMeter = new BigDecimal("3000.0");
            BigDecimal maxPricePerSquareMeter = minPricePerSquareMeter.multiply(new BigDecimal(MAX_MULTIPLIER));

            // when
            Apartment apartment = generator.generate();
            BigDecimal minApartmentPrice = BigDecimal.valueOf(apartment.getArea()).multiply(minPricePerSquareMeter);
            BigDecimal maxApartmentPrice = BigDecimal.valueOf(apartment.getArea()).multiply(maxPricePerSquareMeter);

            // then
            assertAll(
                    () -> assertThat(apartment.getArea()).isBetween(minArea, maxArea),
                    () -> assertThat(apartment.getPrice()).isBetween(minApartmentPrice, maxApartmentPrice)
            );
            testReporter.publishEntry(String.valueOf(apartment));
        }
    }

    @Nested
    @DisplayName("Testing constructor with parameters provided by user")
    class customConstructorTests {

        private final double minArea = 20.0;
        private final BigDecimal minPricePerSquareMeter = new BigDecimal("6000.0");
        private RandomApartmentGenerator generator;

        @BeforeEach
        void setGenerator() {
            this.generator = new RandomApartmentGenerator(minArea, minPricePerSquareMeter);
        }

        @RepeatedTest(value = 10)
        @DisplayName("Generate correct Apartment objects with custom minimal area and price")
        void should_GenerateCorrectApartment_When_CustomMinAreaMinPrice(TestReporter testReporter) {
            // given
            double maxArea = minArea * MAX_MULTIPLIER;
            BigDecimal maxPricePerSquareMeter = minPricePerSquareMeter.multiply(new BigDecimal(MAX_MULTIPLIER));

            // when
            Apartment apartment = generator.generate();
            BigDecimal minApartmentPrice = BigDecimal.valueOf(apartment.getArea()).multiply(minPricePerSquareMeter);
            BigDecimal maxApartmentPrice = BigDecimal.valueOf(apartment.getArea()).multiply(maxPricePerSquareMeter);

            //then
            assertAll(
                    () -> assertThat(apartment.getArea()).isBetween(minArea, maxArea),
                    () -> assertThat(apartment.getPrice()).isBetween(minApartmentPrice, maxApartmentPrice)
            );
            testReporter.publishEntry(String.valueOf(apartment));
        }
    }
}
