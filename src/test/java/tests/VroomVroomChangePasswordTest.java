package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.VroomVroomChangePasswordPage;
import pages.VroomVroomLoginPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Change password test using Page Object Model")
public class VroomVroomChangePasswordTest extends BaseTest {
    static VroomVroomChangePasswordPage changePassPage;
    static VroomVroomLoginPage loginPage;
    static WebDriverWait wait;
    private static String password;

    @BeforeAll
    static void initPage() {
        changePassPage = new VroomVroomChangePasswordPage(driver);
        loginPage = new VroomVroomLoginPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        password = "123"; // Initial password for login
    }

    @BeforeEach
    void login() {
        loginPage.navigate();
        loginPage.enterEmail("abc@abc.com");
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        // Wait for successful login and dismiss the success toast
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getSuccessToastLocator()));
        loginPage.dismissSuccessToast();

        // Wait for profile icon to be visible and clickable, then click it
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.getProfileIconLocator()));
        loginPage.clickProfileIcon();

        wait.until(ExpectedConditions.elementToBeClickable(loginPage.getProfileItemLocator()));
        loginPage.clickProfileItem();

        // Wait for navigation to profile page and change password toggle to be visible
        wait.until(ExpectedConditions.elementToBeClickable(changePassPage.getChangePasswordToggleLocator()));
    }

    @Test
    @Order(1)
    @DisplayName("Should submit form successfully")
    void testFormSubmission() {
        changePassPage.navigate();
        changePassPage.toggleChangePassword();
        changePassPage.enterCurrentPassword("123");
        changePassPage.enterNewPassword("Abc@12345");
        changePassPage.enterConfirmPassword("Abc@12345");
        changePassPage.submitForm();

        assertTrue(changePassPage.isSubmissionSuccessful(), "Form submission should be successful");
        password = "Abc@12345"; // Update password for subsequent tests
    }

    @Test
    @Order(2)
    @DisplayName("Should not submit form with invalid current password")
    void testInvalidCurrentPasswordSubmission() {
        changePassPage.navigate();
        changePassPage.toggleChangePassword();
        changePassPage.enterCurrentPassword("Abc@12");
        changePassPage.enterNewPassword("Abc@12345678");
        changePassPage.enterConfirmPassword("Abc@12345678");
        changePassPage.submitForm();

        assertFalse(changePassPage.isSubmissionSuccessful(), "Form submission should fail due to invalid current password");
    }

    @Test
    @Order(3)
    @DisplayName("Should not submit form with less than 8 characters in new password")
    void testWeakNewPasswordSubmission() {
        changePassPage.navigate();
        changePassPage.toggleChangePassword();
        changePassPage.enterCurrentPassword("Abc@12345");
        changePassPage.enterNewPassword("Abc@1");
        changePassPage.enterConfirmPassword("Abc@1");
        changePassPage.submitForm();

        assertFalse(changePassPage.isSubmissionSuccessful(), "Form submission should fail due to new password having less than 8 characters");
    }

    @Test
    @Order(4)
    @DisplayName("Should not submit form with no lowercase letters in new password")
    void testNoLowercaseNewPasswordSubmission() {
        changePassPage.navigate();
        changePassPage.toggleChangePassword();
        changePassPage.enterCurrentPassword("Abc@12345");
        changePassPage.enterNewPassword("ABC@1");
        changePassPage.enterConfirmPassword("ABC@1");
        changePassPage.submitForm();

        assertFalse(changePassPage.isSubmissionSuccessful(), "Form submission should fail due to new password having no lowercase letters");
    }

    @Test
    @Order(5)
    @DisplayName("Should not submit form with no uppercase letters in new password")
    void testNoUppercaseNewPasswordSubmission() {
        changePassPage.navigate();
        changePassPage.toggleChangePassword();
        changePassPage.enterCurrentPassword("Abc@12345");
        changePassPage.enterNewPassword("abc@1");
        changePassPage.enterConfirmPassword("abc@1");
        changePassPage.submitForm();

        assertFalse(changePassPage.isSubmissionSuccessful(), "Form submission should fail due to new password having no uppercase letters");
    }

    @Test
    @Order(6)
    @DisplayName("Should not submit form with no numbers in new password")
    void testNoNumberNewPasswordSubmission() {
        changePassPage.navigate();
        changePassPage.toggleChangePassword();
        changePassPage.enterCurrentPassword("Abc@12345");
        changePassPage.enterNewPassword("Abc@defg");
        changePassPage.enterConfirmPassword("Abc@defg");
        changePassPage.submitForm();

        assertFalse(changePassPage.isSubmissionSuccessful(), "Form submission should fail due to new password having no numbers");
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}