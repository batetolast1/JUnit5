package io.github.batetolast1.junit5.realestateapp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Testing ApartmentRaterTest class")
class ApartmentRaterTest {

    @Nested
    @DisplayName("Testing rateApartment() method")
    class rateApartmentTests {

        @ParameterizedTest(name = "area={0}, price={1}. rating={2}")
        @DisplayName("Return correct apartment ratings for sample Apartments")
        @CsvSource(value = {"100.0, 450000.0, 0", "75, 450000.0, 1", "50.0, 450000.0, 2"})
        void should_ReturnCorrectRating_When_CorrectApartment(double area, double price, int expectedRating) {
            // given
            Apartment apartment = new Apartment(area, new BigDecimal(price));

            // when
            int actualRating = ApartmentRater.rateApartment(apartment);

            // then
            assertEquals(expectedRating, actualRating);
        }

        @Test
        @DisplayName("Return error value when Apartment is incorrect")
        void should_ReturnErrorValue_When_IncorrectApartment() {
            // given
            Apartment apartment = new Apartment(0, new BigDecimal("100000.0"));
            int expectedRating = -1;

            // when
            int actualRating = ApartmentRater.rateApartment(apartment);

            // then
            assertEquals(expectedRating, actualRating);
        }
    }

    @Nested
    @DisplayName("Testing calculateAverageRating() method")
    class calculateAverageRatingTests {

        @Test
        @DisplayName("Calculate correct average rating when Apartment list is correct")
        void should_CalculateAverageRating_When_CorrectApartmentList() {
            // given 100.0, 450000.0, 0", "75, 450000.0, 1", "50.0, 450000.0
            List<Apartment> apartmentList = new ArrayList<>();
            apartmentList.add(new Apartment(100, new BigDecimal("450000.0")));
            apartmentList.add(new Apartment(75, new BigDecimal("450000.0")));
            apartmentList.add(new Apartment(50, new BigDecimal("450000.0")));
            double expectedRating = 1.0;

            // when
            double actualRating = ApartmentRater.calculateAverageRating(apartmentList);

            // then
            assertEquals(expectedRating, actualRating);
        }

        @Test
        @DisplayName("Throw RuntimeException when Apartment list is empty")
        void should_ThrowExceptionInCalculateAverageRating_When_EmptyApartmentList() {
            // given
            List<Apartment> apartmentList = new ArrayList<>();

            // when
            Executable executable = () -> ApartmentRater.calculateAverageRating(apartmentList);

            // then
            assertThrows(RuntimeException.class, executable);
        }
    }
}
