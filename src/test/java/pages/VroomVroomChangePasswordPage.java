package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class VroomVroomChangePasswordPage extends BasePage {

    // Locators
    private By changePasswordToggle = By.xpath("/html/body/div/div[1]/main/div/div[2]/div[2]/div[2]/div[4]/button");
    private By currentPasswordField = By.xpath("/html/body/div[3]/form/div[1]/div/input");
    private By newPasswordField = By.xpath("/html/body/div[3]/form/div[2]/div/input");
    private By confirmPasswordField = By.xpath("/html/body/div[3]/form/div[3]/div/input");
    private By submitButton = By.xpath("/html/body/div[3]/div[2]/button[2]");
    private By toast = By.xpath("/html/body/div[1]/div[2]");
    private By currentPassInlineMessage = By.xpath("/html/body/div[3]/form/div[1]/p");
    private By newPassInlineMessage = By.xpath("/html/body/div[3]/form/div[2]/p");
    private By confirmPassInlineMessage = By.xpath("/html/body/div[3]/form/div[3]/p");

    public VroomVroomChangePasswordPage(WebDriver driver) {
        super(driver);
    }

    // Actions
    public void navigate() {
        navigateTo("https://vroomvroom.click/profile");
        wait.until(ExpectedConditions.elementToBeClickable(changePasswordToggle));
    }

    public void toggleChangePassword() {
        click(changePasswordToggle);
    }

    public By getChangePasswordToggleLocator() {
        return changePasswordToggle;
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
        // First, wait for either toast or inline messages to appear after form submission
        try {
            Thread.sleep(2000); // Wait for response
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Check for success toast first (only way to confirm success)
        if (isElementVisible(toast)) {
            String toastMessage = getText(toast);
            if (toastMessage.contains("success") || toastMessage.contains("Success") ||
                    toastMessage.contains("Successfully") || toastMessage.contains("Password changed successfully")) {
                return true;
            }
            // If toast exists but contains error message, it's a failure
            if (toastMessage.contains("error") || toastMessage.contains("Error") ||
                    toastMessage.contains("Failed") || toastMessage.contains("Incorrect")) {
                return false;
            }
        }

        // Check for any inline validation errors (all indicate failure)
        if (isElementVisible(currentPassInlineMessage)) {
            return false; // Any current password inline message indicates failure
        }

        if (isElementVisible(newPassInlineMessage)) {
            return false; // Any new password inline message indicates validation failure
        }

        if (isElementVisible(confirmPassInlineMessage)) {
            return false; // Any confirm password inline message indicates validation failure
        }

        // If no success toast and no inline errors, assume failure
        return false;
    }

    public String getMessageText(By locator) {
        return getText(locator);
    }
}