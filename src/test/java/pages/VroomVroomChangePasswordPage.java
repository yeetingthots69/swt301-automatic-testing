package pages;

import org.openqa.selenium.*;
import java.io.File;

public class VroomVroomChangePasswordPage extends BasePage {

    // Locators
    private By changePasswordToggle = By.xpath("/html/body/div/div[1]/main/div/div[2]/div[2]/div[2]/div[4]/button");
    private By currentPasswordField = By.xpath("/html/body/div[3]/form/div[1]/div/input");
    private By newPasswordField = By.xpath("/html/body/div[3]/form/div[2]/div/input");
    private By confirmPasswordField = By.xpath("/html/body/div[3]/form/div[3]/div/input");
    private By submitButton = By.xpath("/html/body/div[3]/div[2]/button[2]");
    private By successToast= By.xpath("example-modal-sizes-title-lg");
    private By errorToast = By.xpath("/html/body/div[1]/div[2]");

    public VroomVroomChangePasswordPage(WebDriver driver) {
        super(driver);
    }

    // Actions
    public void navigate() {
        navigateTo("https://vroomvroom.click/profile");
    }

    public void toggleChangePassword() {
        click(changePasswordToggle);
    }

    public void enterCurrentPassword(String currentPassword) {
        type(currentPasswordField, currentPassword);
    }

    public void enterNewPassword(String newPassword) {
        type(newPasswordField, newPassword);
    }

    public void enterConfirmPassword(String confirmPassword) {
        type(confirmPasswordField, confirmPassword);
    }


    public void submitForm() {
        click(submitButton);
    }

    public boolean isSubmissionSuccessful() {
        return isElementVisible(successToast);
    }

    public boolean isSubmissionFailed() {
        return isElementVisible(errorToast);
    }

    public String getMessageText(By locator) {
        return getText(locator);
    }
}