package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.UserService;

@DisplayName("Testing features of UserService")
class UserServiceTest {

    UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
        System.out.println("UserService instance created");
    }

    @Test
    @DisplayName("should recognize valid email formats")
    void testValidEmail() {
        assertTrue(userService.isValidEmail("mohammad.dev@gmail.com"), "expected valid email to return true");
        assertTrue(userService.isValidEmail("hello.world@outlook.net"), "expected valid email to return true");
    }

    @Test
    @DisplayName("should reject incorrect or malformed emails")
    void testInvalidEmail() {
        assertFalse(userService.isValidEmail("mohammad.dev.gmail.com"), "email missing @ should be invalid");
        assertFalse(userService.isValidEmail(""), "blank email should be invalid");
        assertFalse(userService.isValidEmail(null), "null email should return false");
    }

    @ParameterizedTest
    @CsvSource({
        "mohammad.dev@gmail.com",
        "hello.world@outlook.net"
    })
    @DisplayName("validate multiple email inputs using parameterized test")
    void testValidEmailsParameterized(String email) {
        assertTrue(userService.isValidEmail(email), "should accept email: " + email);
    }

    @Test
    @DisplayName("authenticate should pass with correct credentials")
    void testAuthenticateValid() {
        assertTrue(userService.authenticate("admin", "1234"), "correct login credentials should succeed");
    }

    @Test
    @DisplayName("authenticate should fail with incorrect credentials")
    void testAuthenticateInvalid() {
        assertFalse(userService.authenticate("admin", "wrongpassword"), "incorrect password should fail authentication");
        assertFalse(userService.authenticate("user", "1234"), "wrong username should not pass authentication");
    }

    @Test
    @Timeout(1)
    @DisplayName("ensure authentication completes quickly")
    void testAuthenticateTimeout() {
        assertTrue(userService.authenticate("admin", "1234"));
    }
}
