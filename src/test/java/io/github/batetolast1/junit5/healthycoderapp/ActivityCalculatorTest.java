package io.github.batetolast1.junit5.healthycoderapp;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ActivityCalculatorTest {

    @Nested
    class RateActivityLevelTests {
        // 2. start writing unit tests that will fail initially, according to business logic
        // tests should be written as method has been already finished
        @Test
        void should_ReturnBad_WhenAvgBelow20() {
            // given
            int weeklyCardioMin = 40;
            int weeklyWorkoutSessions = 1;

            // when
            String actualActivityLevel = ActivityCalculator.rateActivityLevel(weeklyCardioMin, weeklyWorkoutSessions);

            // then
            assertEquals("bad", actualActivityLevel);
        }

        @Test
        void should_ReturnAverage_When_AvgBetween20And40() {
            // given
            int weeklyCardioMin = 40;
            int weeklyWorkoutSessions = 3;

            // when
            String actualActivityLevel = ActivityCalculator.rateActivityLevel(weeklyCardioMin, weeklyWorkoutSessions);

            // then
            assertEquals("average", actualActivityLevel);
        }

        @Test
        void should_ReturnGood_When_AvgAbove40() {
            // given
            int weeklyCardioMin = 40;
            int weeklyWorkoutSessions = 7;

            // when
            String actualActivityLevel = ActivityCalculator.rateActivityLevel(weeklyCardioMin, weeklyWorkoutSessions);

            // then
            assertEquals("good", actualActivityLevel);
        }

        @Test
        void should_ThrowException_When_InputBelowZero() {
            // given
            int weeklyCardioMin = -40;
            int weeklyWorkoutSessions = 7;

            // when
            Executable executable = () -> ActivityCalculator.rateActivityLevel(weeklyCardioMin, weeklyWorkoutSessions);

            // then
            assertThrows(RuntimeException.class, executable);
        }
    }
}