package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.Calculator;

@DisplayName("calculator test")
@Execution(value = ExecutionMode.CONCURRENT)
class CalculatorTest {

Calculator calc;

@BeforeAll
static void setUpBeforeClass() {
    System.out.println("beginning calculator test suite");
}

@BeforeEach
void setUp() throws Exception {
    calc = new Calculator();
    System.out.println("new calculator instance created");
}

@Test
@DisplayName("verify addition results with typical numbers")
@Order(1)
void testAddValidInputs() {
    assertEquals(8, calc.add(4, 4));
    assertEquals(0, calc.add(-8, 8));
    assertEquals(14, calc.add(5, 9));
}

@ParameterizedTest
@CsvSource({"4, 4, 8",
            "-8, 8, 0",
            "5, 9, 14"
           })
@DisplayName("test add method with multiple input sets")
@Order(2)
void parameterizedTest(int a, int b, int expected) {
    assertEquals(expected, calc.add(a, b));
}

@Test
@DisplayName("check division logic with valid divisor")
@Order(3)
void testDivideValid() {
    assertEquals(5, calc.divide(10, 2));
}

@Test
@DisplayName("check exception when dividing by zero")
@Order(4)
void testDivideByZero() {
    assertThrows(ArithmeticException.class, () -> calc.divide(10, 0));
}

@Test
@DisplayName("verify factorial output for correct input")
@Order(5)
void testFactorialValid() {
    assertEquals(120, calc.factorial(5));
    assertEquals(1, calc.factorial(0));
}

@Test
@DisplayName("expect exception for negative factorial input")
@Order(6)
void testFactorialNegative() {
    assertThrows(IllegalArgumentException.class, () -> calc.factorial(-1));
}

@Test
@DisplayName("ensure factorial executes within time constraints")
@Timeout(100)
@Order(7)
void timeoutFactorial() {
    assertEquals(1, calc.factorial(0));
}

@Test
@DisplayName("example of disabled test case")
@Disabled("this test fails because expected value is incorrect")
@Order(8)
void failingTest(){
    assertEquals(8, calc.factorial(2));
    // correct->  assertEquals(2, calc.factorial(2));
}

@AfterEach
void postTest() {
    System.out.println("completed test method");
}

@AfterAll
static void afterAll() {
    System.out.println("all calculator tests completed");
}

}
