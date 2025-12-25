package com.automation.ui.pages.components;

import com.automation.core.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NotificationMessagesPage extends BasePage {
    
    @FindBy(css = "h3")
    private WebElement pageTitle;
    
    @FindBy(linkText = "Click here")
    private WebElement clickHereLink;
    
    @FindBy(id = "flash")
    private WebElement flashMessage;
    
    @FindBy(css = ".flash")
    private WebElement notificationContainer;

    public NotificationMessagesPage() {
        super();
    }

    public void navigateToNotificationMessagesPage() {
        navigateToUrl("/notification_message_rendered");
    }

    public boolean isNotificationMessagesPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(pageTitle))
                    .getText().contains("Notification");
        } catch (Exception e) {
            return false;
        }
    }

    public void clickHereLink() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(clickHereLink)).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Click here' link: " + e.getMessage());
        }
    }

    public boolean isNotificationVisible() {
        try {
            return flashMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getNotificationMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(flashMessage)).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isClickHereLinkVisible() {
        try {
            return clickHereLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getNotificationClass() {
        try {
            return flashMessage.getAttribute("class");
        } catch (Exception e) {
            return "";
        }
    }

    public void clickAndVerifyNotification() {
        clickHereLink();
        if (!isNotificationVisible()) {
            throw new RuntimeException("Notification should be visible after clicking link");
        }
    }

    public boolean waitForNotification(int timeoutInSeconds) {
        try {
            wait.until(ExpectedConditions.visibilityOf(flashMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}