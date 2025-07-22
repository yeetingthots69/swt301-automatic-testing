package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class VroomVroomLoginPage extends BasePage {

    public VroomVroomLoginPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By emailInput = By.id("email");
    private By passwordInput = By.id("password");
    private By submitButton = By.cssSelector("button[type='submit']");
    private By profileIcon = By.xpath("/html/body/div/div[1]/header/div/div/button[2]/span/span");
    private By profileItem = By.xpath("/html/body/div[2]/div/a[1]");
    private By successToastCloseButton = By.xpath("/html/body/div/div[2]/ol/li/button");

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

    public By getProfileIconLocator() {
        return profileIcon;
    }

    public By getProfileItemLocator() {
        return profileItem;
    }

    public By getSuccessToastCloseButtonLocator() {
        return successToastCloseButton;
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

    public void clickProfileIcon() {
        click(profileIcon);
    }

    public void clickProfileItem() {
        click(profileItem);
    }

    public void dismissSuccessToast() {
        if (isElementVisible(successToast)) {
            // Hover over the toast to reveal the close button
            hoverOverElement(successToast);

            // Wait for close button to appear and click it
            wait.until(ExpectedConditions.elementToBeClickable(successToastCloseButton));
            click(successToastCloseButton);

            // Wait for toast to disappear
            wait.until(ExpectedConditions.invisibilityOfElementLocated(successToast));
        }
    }

    // Use JavaScript to check validity
    public boolean isEmailValid() {
        return (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].checkValidity();", driver.findElement(emailInput));
    }

    public boolean isPasswordValid() {
        return (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].checkValidity();", driver.findElement(passwordInput));
    }

}