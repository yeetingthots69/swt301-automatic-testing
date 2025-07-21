package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.PracticeFormPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Practice Form Tests using Page Object Model")
public class PracticeFormTest extends BaseTest {
    static PracticeFormPage practiceFormPage;

    @BeforeAll
    static void initPage() {
        practiceFormPage = new PracticeFormPage(driver);
    }

    @Test
    @Order(1)
    @DisplayName("Should submit practice form successfully")
    void testPracticeFormSubmission() {
        practiceFormPage.navigate();
        practiceFormPage.enterFirstName("John");
        practiceFormPage.enterLastName("Doe");
        practiceFormPage.enterEmail("john.doe@example.com");
        practiceFormPage.selectGender("Male");
        practiceFormPage.enterMobile("1234567890");
        practiceFormPage.setDateOfBirth("10 May 1990");
        practiceFormPage.enterSubject("Maths");
        practiceFormPage.selectHobby("Sports");
        practiceFormPage.uploadPicture("/lamborghini.jpg");
        practiceFormPage.enterAddress("123 Main St, City");
        practiceFormPage.selectState("NCR");
        practiceFormPage.selectCity("Delhi");
        practiceFormPage.submitForm();

        assertTrue(practiceFormPage.isSubmissionSuccessful(), "Form submission should be successful");
    }

    @Test
    @Order(2)
    @DisplayName("Should not submit form with invalid mobile number")
    void testInvalidMobileSubmission() {
        practiceFormPage.navigate();
        practiceFormPage.enterFirstName("Jane");
        practiceFormPage.enterLastName("Smith");
        practiceFormPage.enterEmail("jane.smith@example.com");
        practiceFormPage.selectGender("Female");
        practiceFormPage.enterMobile("12345"); // Invalid mobile
        practiceFormPage.setDateOfBirth("15 Jun 1995");
        practiceFormPage.enterSubject("English");
        practiceFormPage.selectHobby("Reading");
        practiceFormPage.uploadPicture("/lamborghini.jpg");
        practiceFormPage.enterAddress("456 Elm St, City");
        practiceFormPage.selectState("NCR");
        practiceFormPage.selectCity("Delhi");
        practiceFormPage.submitForm();

        assertFalse(practiceFormPage.isSubmissionSuccessful(), "Form submission should fail for invalid mobile");
    }

    @ParameterizedTest
    @Order(3)
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    @DisplayName("Should submit practice form with CSV data")
    void testPracticeFormWithCsv(String firstName, String lastName, String email, String gender, String mobile, String dob, String subject, String hobbies, String address, String state, String city) {
        practiceFormPage.navigate();
        practiceFormPage.enterFirstName(firstName);
        practiceFormPage.enterLastName(lastName);
        practiceFormPage.enterEmail(email);
        practiceFormPage.selectGender(gender);
        practiceFormPage.enterMobile(mobile);
        practiceFormPage.setDateOfBirth(dob);
        practiceFormPage.enterSubject(subject);
        for (String hobby : hobbies.split(",")) {
            practiceFormPage.selectHobby(hobby.trim());
        }
        practiceFormPage.uploadPicture("/lamborghini.jpg");
        practiceFormPage.enterAddress(address);
        practiceFormPage.selectState(state);
        practiceFormPage.selectCity(city);
        practiceFormPage.submitForm();

        assertTrue(practiceFormPage.isSubmissionSuccessful(), "Form submission should be successful");
    }

    @ParameterizedTest
    @Order(4)
    @CsvFileSource(resources = "/faulty-data.csv", numLinesToSkip = 1)
    @DisplayName("Should not submit form with faulty CSV data (invalid email)")
    void testPracticeFormWithFaultyCsv(String firstName, String lastName, String email, String gender, String mobile, String dob, String subject, String hobbies, String address, String state, String city) {
        practiceFormPage.navigate();
        practiceFormPage.enterFirstName(firstName);
        practiceFormPage.enterLastName(lastName);
        practiceFormPage.enterEmail(email); // Invalid email
        practiceFormPage.selectGender(gender);
        practiceFormPage.enterMobile(mobile);
        practiceFormPage.setDateOfBirth(dob);
        practiceFormPage.enterSubject(subject);
        for (String hobby : hobbies.split(",")) {
            practiceFormPage.selectHobby(hobby.trim());
        }
        practiceFormPage.uploadPicture("/lamborghini.jpg");
        practiceFormPage.enterAddress(address);
        practiceFormPage.selectState(state);
        practiceFormPage.selectCity(city);
        practiceFormPage.submitForm();

        assertFalse(practiceFormPage.isSubmissionSuccessful(), "Form submission should fail for invalid email");
    }

    @Test
    @Order(5)
    @DisplayName("Should not submit form with missing required field (no first name)")
    void testPracticeFormMissingFirstName() {
        practiceFormPage.navigate();
        // Do not enter first name
        practiceFormPage.enterLastName("NoFirst");
        practiceFormPage.enterEmail("nofirst@example.com");
        practiceFormPage.selectGender("Male");
        practiceFormPage.enterMobile("1234567890");
        practiceFormPage.setDateOfBirth("01 Jan 1990");
        practiceFormPage.enterSubject("Maths");
        practiceFormPage.selectHobby("Sports");
        practiceFormPage.selectHobby("Reading");
        practiceFormPage.uploadPicture("/lamborghini.jpg");
        practiceFormPage.enterAddress("No First St");
        practiceFormPage.selectState("NCR");
        practiceFormPage.selectCity("Delhi");
        practiceFormPage.submitForm();

        assertFalse(practiceFormPage.isSubmissionSuccessful(), "Form submission should fail when first name is missing");
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}