// Introduction to unit testing with JUnit 5

package io.github.batetolast1.junit5.healthycoderapp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class BMICalculatorTest { // class name + "Test"

    // Assumptions
    private final String environment = "dev";

    // assertTrue
    /*@Test
    void test() {
        assertTrue(BMICalculator.isDietRecommended(89.0, 1.72));
    }*/

    // @BeforeAll will be executed once before each test - it's used for tests too expensive to perform before each unit test
    // ie. start database connections or stop servers
    // this function can have any name, but must be static!
    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all unit tests.");
    }

    // used to ie. close database connections or stop servers
    @AfterAll
    static void afterAll() {
        System.out.println("After all unit tests");
    }

    // @Nested allows to organize tests into classes
    // it allows to use multiple levels of inner classes
    // each @Nested can have it's own @BeforeEach and @AfterEach
    @Nested
    class IsDietRecommendedTests {
    /*// assertTrue()
        @Test
        void should_ReturnTrue_When_DietRecommended() { // method name should reflect test expected result
            //given (or arrange) - initial conditions
            double weight = 89.0;
            double height = 1.72;

            //when (or act) - invoke method to test and store result in variable
            boolean recommended = BMICalculator.isDietRecommended(weight, height); // method executed normally

            //then (or assert) - provide assertion
            assertTrue(recommended);
        }*/

        // ParametrizedTest - for testing multiple values in method
        @ParameterizedTest(name = "weight= {0}, height={1}") // allows to display additional info in test results
        // @ValueSource(doubles = {89.0, 95.0, 110.0}) // source of data, but only for one value
        // @CsvSource(value = {"89.0, 1.72", "95.0, 1.75", "110.0, 1.78"}) // allows to use CSV data directly
        @CsvFileSource(resources = "/diet-recommended-input-data.csv", numLinesToSkip = 1)
        // allows to load data form CSV file (from test/resources)
        // numLinesToSkip parameter allows to skip header line with column names
        void should_ReturnTrue_When_DietRecommended(Double coderWeight, Double coderHeight) { // method name should reflect test expected result
            //given (or arrange) - initial conditions
            double weight = coderWeight;
            double height = coderHeight;

            //when (or act) - invoke method to test and store result in variable
            boolean recommended = BMICalculator.isDietRecommended(weight, height); // method executed normally

            //then (or assert) - provide assertion
            assertTrue(recommended);
        }

        // assertFalse()
        @Test
        void should_ReturnFalse_When_DietNotRecommended() { // method name should reflect test expected result
            //given (or arrange) - initial conditions
            double weight = 50.0;
            double height = 1.92;

            //when (or act) - invoke method to test and store result in variable
            boolean recommended = BMICalculator.isDietRecommended(weight, height); // method executed normally

            //then (or assert) - provide assertion
            assertFalse(recommended);
        }

        // assertThrows()
        @Test
        void should_ThrowArithmeticException_When_HeightZero() { // method name should reflect test expected result
            //given (or arrange) - initial conditions
            double weight = 50.0;
            double height = 0;

            //when (or act) - invoke method to test and store result in variable
            Executable executable = () -> BMICalculator.isDietRecommended(weight, height);
            // method will throw exception, thus assertThrows() needs executable
            // executable expects lambda expression
            // executable will not be executed immediately, but in assertThrows()

            //then (or assert) - provide assertion
            assertThrows(ArithmeticException.class, executable); //
        }
    }

    @Nested
    class FindCoderWithWorstBMITests {
        // assertAll()
        @Test
        void should_ReturnCoderWithWorstBMI_When_CoderListNotEmpty() {
            // given
            // Assumptions - allows to execute test only when certain conditions are met (ie. when project is moved from development to production
            // Assumption never makes tests to fail
            assumeTrue(BMICalculatorTest.this.environment.equals("prod"));

            CoderDAO coderDAO = new CoderDAO();
            List<Coder> coderList = coderDAO.getList();

            // when
            Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coderList);

            // then

            // assertEquals(1.82, coderWorstBMI.getHeight());
            // assertEquals(98.0, coderWorstBMI.getWeight());

            // if first assertion fails, result of second is newer shown, thus we need assertAll()
            // assertAll expects lambda expressions
            // use it for assertions that make sense as a whole
            assertAll(
                    () -> assertEquals(1.82, coderWorstBMI.getHeight()),
                    () -> assertEquals(98.0, coderWorstBMI.getWeight())
            );
        }

        // performance unit test
        @Test
        void should_ReturnCoderWithWorstBMIIn1Ms_When_CoderListHas10000Elements() {
            // given
            CoderDAO coderDAO = new CoderDAO();
            List<Coder> coderList = coderDAO.get10000CodersList();

            // when
            Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coderList);

            // then
            assertTimeout(Duration.ofMillis(500), executable); // asserts that execution time will be under provided value (only checks timeout in executable!)
        }

        // assertNull()
        @Test
        void should_ReturnNull_When_CoderListEmpty() {
            // given
            List<Coder> coderList = new ArrayList<>();

            // when
            Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coderList);

            // then
            assertNull(coderWorstBMI);
        }
    }

    @Nested
    class GetBMIScoresTests {
        @Test
        void should_ReturnCorrectBMIScoreArray_When_CoderListNotEmpty() {
            // given
            CoderDAO coderDAO = new CoderDAO();
            List<Coder> coderList = coderDAO.getList();
            double[] expected = {18.52, 29.59, 19.53};

            // when
            double[] bmiScores = BMICalculator.getBMIScores(coderList);

            // then
            // assertEquals(expected, bmiScores); test fails, because it compares memory addresses for arrays
            assertArrayEquals(expected, bmiScores);
        }
    }
}
