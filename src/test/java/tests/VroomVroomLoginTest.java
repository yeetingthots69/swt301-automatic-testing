package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.VroomVroomLoginPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Login Tests for VroomVroom")
public class VroomVroomLoginTest extends BaseTest {

    static WebDriverWait wait;
    static VroomVroomLoginPage loginPage;

    @BeforeAll
    static void init() {
        loginPage = new VroomVroomLoginPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    @Order(1)
    @DisplayName("Login successfully with valid credentials")
    void testLoginSuccess() {
        loginPage.navigate();
        loginPage.login("minh.nguyen@gmail.com", "Abc@12345");

        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getSuccessToastLocator()));
        assertTrue(toast.getText().contains("Login Successful"));
    }

    @Test
    @Order(2)
    @DisplayName("Show error toast with invalid credentials")
    void testLoginFail() {
        loginPage.navigate();
        loginPage.login("minh.nguyen@gmail.com", "asdji");

        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getErrorToastLocator()));
        assertTrue(toast.getText().contains("An error occurred during login"));
    }

    @Test
    @Order(3)
    @DisplayName("Show validation error when email is empty")
    void testEmptyEmailValidation() {
        loginPage.navigate();
        loginPage.clearEmail();
        loginPage.enterPassword("somepassword");
        loginPage.clickLogin();

        boolean emailValid = loginPage.isEmailValid();
        Assertions.assertFalse(emailValid, "Email field should be invalid when empty");
    }

    @Test
    @Order(4)
    @DisplayName("Show validation error when password is empty")
    void testEmptyPasswordValidation() {
        loginPage.navigate();
        loginPage.enterEmail("user@example.com");
        loginPage.clearPassword();
        loginPage.clickLogin();

        boolean passwordValid = loginPage.isPasswordValid();
        Assertions.assertFalse(passwordValid, "Password field should be invalid when empty");
    }

    @Test
    @Order(5)
    @DisplayName("Show validation error when both fields are empty")
    void testBothFieldsEmptyValidation() {
        loginPage.navigate();
        loginPage.clearEmail();
        loginPage.clearPassword();
        loginPage.clickLogin();

        Assertions.assertFalse(loginPage.isEmailValid(), "Email field should be invalid when empty");
        Assertions.assertFalse(loginPage.isPasswordValid(), "Password field should be invalid when empty");
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}