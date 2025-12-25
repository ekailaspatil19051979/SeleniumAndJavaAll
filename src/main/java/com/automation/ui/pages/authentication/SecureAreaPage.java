package com.automation.ui.pages.authentication;

import com.automation.constants.FrameworkConstants;
import com.automation.core.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Secure Area Page - Page Object for secure area functionality
 * Handles secure area navigation and logout operations
 */
public class SecureAreaPage extends BasePage {
    
    // Page Elements
    @FindBy(css = "a[href='/logout']")
    private WebElement logoutButton;
    
    @FindBy(id = "flash")
    private WebElement flashMessage;
    
    @FindBy(css = "h2")
    private WebElement pageHeading;
    
    @FindBy(css = "div.example p")
    private WebElement secureAreaMessage;
    
    // Page Actions
    public LoginPage clickLogoutButton() {
        clickElement(logoutButton);
        logger.info("Clicked logout button");
        return new LoginPage();
    }
    
    public LoginPage logout() {
        return clickLogoutButton();
    }
    
    // Page Verifications
    public boolean isSecureAreaPageDisplayed() {
        return isElementDisplayed(pageHeading) && 
               isElementDisplayed(logoutButton) &&
               getCurrentUrl().contains(FrameworkConstants.SECURE_AREA_URL);
    }
    
    public boolean isLogoutButtonDisplayed() {
        return isElementDisplayed(logoutButton);
    }
    
    public boolean isLogoutButtonEnabled() {
        return isElementEnabled(logoutButton);
    }
    
    public String getPageHeading() {
        return getText(pageHeading);
    }
    
    public String getSecureAreaMessage() {
        return getText(secureAreaMessage);
    }
    
    public String getFlashMessage() {
        return getText(flashMessage);
    }
    
    public boolean isFlashMessageDisplayed() {
        return isElementDisplayed(flashMessage);
    }
    
    // Validation Methods
    public boolean isUserLoggedIn() {
        return isSecureAreaPageDisplayed() && 
               isLogoutButtonDisplayed() &&
               getPageHeading().contains("Secure Area");
    }
    
    public boolean isLoginSuccessMessageDisplayed() {
        return isFlashMessageDisplayed() && 
               getFlashMessage().contains(FrameworkConstants.LOGIN_SUCCESS_MESSAGE);
    }
}