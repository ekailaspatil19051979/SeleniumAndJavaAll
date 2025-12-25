package com.automation.ui.pages.authentication;

import com.automation.constants.FrameworkConstants;
import com.automation.core.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Login Page - Page Object for login functionality
 * Encapsulates login page elements and operations
 */
public class LoginPage extends BasePage {
    
    // Page Elements using Selenium 4 optimized locators
    @FindBy(css = "input[name='username']")
    private WebElement usernameField;
    
    @FindBy(css = "input[name='password']")
    private WebElement passwordField;
    
    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;
    
    @FindBy(css = "div[id='flash']")
    private WebElement flashMessage;
    
    @FindBy(css = "div.login h2")
    private WebElement pageHeading;
    
    // Page Navigation
    public LoginPage navigateToLoginPage() {
        navigateToUrl(config.getProperty("app.base.url") + FrameworkConstants.LOGIN_URL);
        waitForPageLoad();
        logger.info("Navigated to login page");
        return this;
    }
    
    // Page Actions
    public LoginPage enterUsername(String username) {
        typeText(usernameField, username);
        logger.info("Entered username: {}", username);
        return this;
    }
    
    public LoginPage enterPassword(String password) {
        typeText(passwordField, password);
        logger.info("Entered password");
        return this;
    }
    
    public SecureAreaPage clickLoginButton() {
        clickElement(loginButton);
        logger.info("Clicked login button");
        return new SecureAreaPage();
    }
    
    public LoginPage clickLoginButtonExpectingError() {
        clickElement(loginButton);
        logger.info("Clicked login button expecting error");
        return this;
    }
    
    public LoginPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return this;
    }
    
    public SecureAreaPage loginSuccessfully(String username, String password) {
        login(username, password);
        return clickLoginButton();
    }
    
    // Page Verifications
    public boolean isLoginPageDisplayed() {
        return isElementDisplayed(pageHeading) && 
               isElementDisplayed(usernameField) && 
               isElementDisplayed(passwordField) && 
               isElementDisplayed(loginButton);
    }
    
    public boolean isUsernameFieldDisplayed() {
        return isElementDisplayed(usernameField);
    }
    
    public boolean isPasswordFieldDisplayed() {
        return isElementDisplayed(passwordField);
    }
    
    public boolean isLoginButtonDisplayed() {
        return isElementDisplayed(loginButton);
    }
    
    public boolean isLoginButtonEnabled() {
        return isElementEnabled(loginButton);
    }
    
    public String getFlashMessage() {
        return getText(flashMessage);
    }
    
    public boolean isFlashMessageDisplayed() {
        return isElementDisplayed(flashMessage);
    }
    
    public String getPageHeading() {
        return getText(pageHeading);
    }
    
    // Validation Methods
    public boolean isErrorMessageDisplayed() {
        return isFlashMessageDisplayed() && 
               (getFlashMessage().contains(FrameworkConstants.INVALID_LOGIN_MESSAGE) ||
                getFlashMessage().contains(FrameworkConstants.INVALID_PASSWORD_MESSAGE));
    }
    
    public boolean isSuccessMessageDisplayed() {
        return isFlashMessageDisplayed() && 
               getFlashMessage().contains(FrameworkConstants.LOGIN_SUCCESS_MESSAGE);
    }
}