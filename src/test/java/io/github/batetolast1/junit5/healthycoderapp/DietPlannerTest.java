package io.github.batetolast1.junit5.healthycoderapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DietPlannerTest {

    // test for non-static method, so we first declare object, but don't initialize it - tests should be independent of each other
    private DietPlanner dietPlanner;

    // this function will be invoked before each test in this test class
    @BeforeEach
    void setup() {
        this.dietPlanner = new DietPlanner(20, 30, 50);
    }

    // this function will be invoked after each test in this test class
    @AfterEach()
    void afterEach() {
        System.out.println("A unit test was finished.");
    }

    // repeat the same test multiple times - useful when there's random data, method uses threads etc. (it's not useful here!)
    // each test is treated as separate unit test - @BeforeEach and @AfterEach will be executed as many times
    @RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME)
    // name displays full test name in test results field
    void should_ReturnCorrectDietPlan_When_CorrectCoder() {
        // given
        Coder coder = new Coder(1.82, 75.0, 26, Gender.MALE);
        DietPlan expected = new DietPlan(2202, 110, 73, 275);

        // when
        DietPlan actual = dietPlanner.calculateDiet(coder);

        // then
        // simple assertEquals() not working here without overriding equals() in DietPlan
        // assertEquals(expected, actual);

        assertAll(
                () -> assertEquals(expected.getCalories(), actual.getCalories()),
                () -> assertEquals(expected.getProtein(), actual.getProtein()),
                () -> assertEquals(expected.getFat(), actual.getFat()),
                () -> assertEquals(expected.getCarbohydrate(), actual.getCarbohydrate())
        );
    }
}
