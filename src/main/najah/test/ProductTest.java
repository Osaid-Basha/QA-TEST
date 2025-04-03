package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.Product;

@DisplayName("Product class behavior tests")
public class ProductTest {

    Product p;

    @BeforeAll
    static void globalSetUp() {
        System.out.println("=== Test Suite Initialization ===");
    }

    @BeforeEach
    void setUp() throws Exception {
        p = new Product("Book", 50);
        System.out.println("[Setup] Product instance created");
    }

    @Test
    @DisplayName("ensure product initializes correctly with valid values")
    void testValidProductCreation() {
        assertNotNull(p);
        assertEquals("Book", p.getName());
        assertEquals(50, p.getPrice());
        assertEquals(0, p.getDiscount());
    }

    @Test
    @DisplayName("verify exception for negative price during construction")
    void testInvalidProductCreationNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> new Product("Book", -10), "price must be non-negative");
    }

    @ParameterizedTest
    @CsvSource({
        "10.0, 45.0",
        "25.0, 37.5",
        "50.0, 25.0"
    })
    @DisplayName("check discount logic and final price calculation")
    void testApplyDiscountAndGetFinalPrice(double discount, double expectedFinalPrice) {
        p.applyDiscount(discount);
        assertEquals(expectedFinalPrice, p.getFinalPrice(), 0.001);
    }

    @Test
    @DisplayName("exception should be thrown for invalid discount percentages")
    void testInvalidDiscountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> p.applyDiscount(60.0), "invalid discount");
        assertThrows(IllegalArgumentException.class, () -> p.applyDiscount(-10.0), "invalid discount");
    }

    @Test
    @DisplayName("verify getName returns correct value")
    void testGetName() {
        assertEquals("Book", p.getName());
    }

    @Test
    @DisplayName("verify getPrice returns correct value")
    void testGetPrice() {
        assertEquals(50, p.getPrice());
    }

    @Test
    @DisplayName("check if getDiscount reflects applied value")
    void testGetDiscount() {
        p.applyDiscount(20);
        assertEquals(20, p.getDiscount());
    }

    @Test
    @Timeout(100)
    @DisplayName("ensure final price calculation completes within timeout")
    void timeoutFinalPrice() {
        p.applyDiscount(10);
        assertTrue(p.getFinalPrice() > 0);
    }

    @AfterEach
    void tearDown() {
        System.out.println("[Teardown] Test finished");
    }

    @AfterAll
    static void globalTearDown() {
        System.out.println("=== All Product tests completed ===");
    }
}
