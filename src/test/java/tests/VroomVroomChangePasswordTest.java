package tests;

import org.junit.jupiter.api.*;
import pages.VroomVroomChangePasswordPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Change password test using Page Object Model")
public class VroomVroomChangePasswordTest extends BaseTest {
    static VroomVroomChangePasswordPage page;

    @BeforeAll
    static void initPage() {
        page = new VroomVroomChangePasswordPage(driver);
    }

    @Test
    @Order(1)
    @DisplayName("Should submit form successfully")
    void testFormSubmission() {
        page.navigate();
        page.toggleChangePassword();
        page.enterCurrentPassword("123");
        page.enterNewPassword("Abc@12345");
        page.enterConfirmPassword("Abc@12345");
        page.submitForm();

        assertTrue(page.isSubmissionSuccessful(), "Form submission should be successful");
    }

    @Test
    @Order(2)
    @DisplayName("Should not submit form with invalid current password")
    void testInvalidCurrentPasswordSubmission() {
        page.navigate();
        page.toggleChangePassword();
        page.enterCurrentPassword("12");
        page.enterNewPassword("Abc@12345");
        page.enterConfirmPassword("Abc@12345");
        page.submitForm();

        assertFalse(page.isSubmissionSuccessful(), "Form submission should fail due to invalid current password");
    }

    @Test
    @Order(3)
    @DisplayName("Should not submit form with less than 8 characters in new password")
    void testWeakNewPasswordSubmission() {
        page.navigate();
        page.toggleChangePassword();
        page.enterCurrentPassword("123");
        page.enterNewPassword("Abc@1");
        page.enterConfirmPassword("Abc@1");
        page.submitForm();

        assertFalse(page.isSubmissionSuccessful(), "Form submission should fail due to new password having less than 8 characters");
    }

    @Test
    @Order(4)
    @DisplayName("Should not submit form with no lowercase letters in new password")
    void testNoLowercaseNewPasswordSubmission() {
        page.navigate();
        page.toggleChangePassword();
        page.enterCurrentPassword("123");
        page.enterNewPassword("ABC@1");
        page.enterConfirmPassword("ABC@1");
        page.submitForm();

        assertFalse(page.isSubmissionSuccessful(), "Form submission should fail due to new password having no lowercase letters");
    }

    @Test
    @Order(5)
    @DisplayName("Should not submit form with no uppercase letters in new password")
    void testNoUppercaseNewPasswordSubmission() {
        page.navigate();
        page.toggleChangePassword();
        page.enterCurrentPassword("123");
        page.enterNewPassword("abc@1");
        page.enterConfirmPassword("abc@1");
        page.submitForm();

        assertFalse(page.isSubmissionSuccessful(), "Form submission should fail due to new password having no uppercase letters");
    }

    @Test
    @Order(6)
    @DisplayName("Should not submit form with no numbers in new password")
    void testNoNumberNewPasswordSubmission() {
        page.navigate();
        page.toggleChangePassword();
        page.enterCurrentPassword("123");
        page.enterNewPassword("Abc@defg");
        page.enterConfirmPassword("Abc@defg");
        page.submitForm();

        assertFalse(page.isSubmissionSuccessful(), "Form submission should fail due to new password having no numbers");
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}