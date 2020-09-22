package io.github.batetolast1.junit5.realestateapp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testing Apartment Class")
class ApartmentTest {

    @Test
    @DisplayName("Create valid Apartment object")
    void should_CreateValidApartment_When_ValuesAreCorrect() {
        // given
        double area = 100.0;
        BigDecimal price = new BigDecimal("450000");

        // when
        Apartment apartment = new Apartment(area, price);

        // then
        assertAll(
                () -> assertEquals(100, apartment.getArea()),
                () -> assertEquals(new BigDecimal("450000"), apartment.getPrice())
        );
    }

    @ParameterizedTest(name = "area={0}")
    @CsvSource(value = {"0.0", "-100.0"})
    @DisplayName("Throw IllegalArgumentException when area is <= 0")
    void should_ThrowIAException_When_AreaIsLessOrEqualThan0(double area) {
        // given
        BigDecimal price = new BigDecimal("450000");

        // when
        Executable executable = () -> new Apartment(area, price);

        // then
        assertThrows(IllegalArgumentException.class, executable);
    }

    @ParameterizedTest(name = "price={0}")
    @CsvSource(value = {"0", "-450000"})
    @DisplayName("Throw IllegalArgumentException when price is <= 0")
    void should_ThrowIAException_When_PriceIsLessorEqualThan0(String price) {
        // given
        double area = 100.0;

        // when
        Executable executable = () -> new Apartment(area, new BigDecimal(price));

        // then
        assertThrows(IllegalArgumentException.class, executable);
    }
}
