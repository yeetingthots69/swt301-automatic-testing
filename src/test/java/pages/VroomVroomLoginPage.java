package pages;

import org.openqa.selenium.*;

public class VroomVroomLoginPage extends BasePage {

    public VroomVroomLoginPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By emailInput = By.id("email");
    private By passwordInput = By.id("password");
    private By submitButton = By.cssSelector("button[type='submit']");

    // Toasts
    private By successToast = By.xpath("//div[contains(text(), 'Login Successful')]");
    private By errorToast = By.xpath("//div[contains(text(), 'An error occurred during login')]");

    // Actions
    public void navigate() {
        navigateTo("https://vroomvroom.click/auth/login");
    }

    public void login(String email, String password) {
        type(emailInput, email);
        type(passwordInput, password);
        click(submitButton);
    }

    public By getSuccessToastLocator() {
        return successToast;
    }

    public By getErrorToastLocator() {
        return errorToast;
    }

    public void clearEmail() {
        driver.findElement(emailInput).clear();
    }

    public void clearPassword() {
        driver.findElement(passwordInput).clear();
    }

    public void enterEmail(String email) {
        type(emailInput, email);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void clickLogin() {
        click(submitButton);
    }

    // Use JavaScript to check validity
    public boolean isEmailValid() {
        return (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].checkValidity();", driver.findElement(emailInput));
    }

    public boolean isPasswordValid() {
        return (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].checkValidity();", driver.findElement(passwordInput));
    }

}