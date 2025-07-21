package pages;

import org.openqa.selenium.*;
import java.io.File;

public class PracticeFormPage extends BasePage {

    // Locators
    private By firstNameField = By.id("firstName");
    private By lastNameField = By.id("lastName");
    private By emailField = By.id("userEmail");
    private By genderLabel(String gender) {
        return By.xpath("//label[text()='" + gender + "']");
    }
    private By mobileField = By.id("userNumber");
    private By dobInput = By.id("dateOfBirthInput");
    private By subjectsInput = By.id("subjectsInput");
    private By hobbiesLabel(String hobby) {
        return By.xpath("//label[text()='" + hobby + "']");
    }
    private By pictureUpload = By.id("uploadPicture");
    private By addressField = By.id("currentAddress");
    private By stateDropdown = By.id("react-select-3-input");
    private By cityDropdown = By.id("react-select-4-input");
    private By submitButton = By.id("submit");
    private By successModal = By.id("example-modal-sizes-title-lg");

    public PracticeFormPage(WebDriver driver) {
        super(driver);
    }

    // Actions
    public void navigate() {
        navigateTo("https://demoqa.com/automation-practice-form");
    }

    public void enterFirstName(String firstName) {
        type(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        type(lastNameField, lastName);
    }

    public void enterEmail(String email) {
        type(emailField, email);
    }

    public void selectGender(String gender) {
        click(genderLabel(gender));
    }

    public void enterMobile(String mobile) {
        type(mobileField, mobile);
    }

    public void setDateOfBirth(String date) {
        click(dobInput);
        WebElement dob = waitForVisibility(dobInput);
        dob.sendKeys(Keys.CONTROL + "a");
        dob.sendKeys(date);
        dob.sendKeys(Keys.ENTER);
    }

    public void enterSubject(String subject) {
        type(subjectsInput, subject);
        WebElement input = waitForVisibility(subjectsInput);
        input.sendKeys(Keys.ENTER);
    }

    public void selectHobby(String hobby) {
        click(hobbiesLabel(hobby));
    }

    public void uploadPicture(String filePath) {
        String absolutePath = new File("src/test/resources" + filePath).getAbsolutePath();
        WebElement upload = waitForVisibility(pictureUpload);
        upload.sendKeys(absolutePath);
    }

    public void enterAddress(String address) {
        type(addressField, address);
    }

    public void selectState(String state) {
        WebElement stateInput = waitForVisibility(stateDropdown);
        stateInput.sendKeys(state);
        stateInput.sendKeys(Keys.ENTER);
    }

    public void selectCity(String city) {
        WebElement cityInput = waitForVisibility(cityDropdown);
        cityInput.sendKeys(city);
        cityInput.sendKeys(Keys.ENTER);
    }

    public void submitForm() {
        click(submitButton);
    }

    public boolean isSubmissionSuccessful() {
        return isElementVisible(successModal);
    }

    public String getMessageText(By locator) {
        return getText(locator);
    }
}