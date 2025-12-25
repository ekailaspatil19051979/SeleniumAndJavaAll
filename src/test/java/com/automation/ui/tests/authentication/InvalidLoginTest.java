package com.automation.ui.tests.authentication;

import com.automation.constants.FrameworkConstants;
import com.automation.core.base.BaseTest;
import com.automation.ui.pages.authentication.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Invalid Login Test - Tests invalid login attempts
 * Based on test scenario 1.2 from the test plan
 */
public class InvalidLoginTest extends BaseTest {
    private LoginPage loginPage;
    
    @BeforeMethod
    public void setUpLoginTest() {
        loginPage = new LoginPage();
        loginPage.navigateToLoginPage();
        logger.info("Initialized LoginPage for invalid login test");
    }
    
    @Test(description = "Test invalid username with correct password")
    public void testInvalidUsernameValidPassword() {
        logger.info("Starting test: Invalid Username Valid Password");
        
        // Enter invalid username with valid password
        loginPage.enterUsername(FrameworkConstants.INVALID_USERNAME);
        loginPage.enterPassword(FrameworkConstants.VALID_PASSWORD);
        loginPage.clickLoginButtonExpectingError();
        
        // Verify error message appears
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                         "Error message should appear for invalid credentials");
        Assert.assertTrue(loginPage.getFlashMessage()
                         .contains(FrameworkConstants.INVALID_LOGIN_MESSAGE), 
                         "Should show invalid username error message");
        
        // Verify user remains on login page
        Assert.assertTrue(loginPage.getCurrentUrl().contains(FrameworkConstants.LOGIN_URL), 
                         "User should remain on login page");
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
                         "Login form should still be visible");
        
        logger.info("Test completed successfully: Invalid Username Valid Password");
    }
    
    @Test(description = "Test valid username with incorrect password")
    public void testValidUsernameInvalidPassword() {
        logger.info("Starting test: Valid Username Invalid Password");
        
        // Enter valid username with invalid password
        loginPage.enterUsername(FrameworkConstants.VALID_USERNAME);
        loginPage.enterPassword(FrameworkConstants.INVALID_PASSWORD);
        loginPage.clickLoginButtonExpectingError();
        
        // Verify error message appears
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                         "Error message should appear for invalid credentials");
        Assert.assertTrue(loginPage.getFlashMessage()
                         .contains(FrameworkConstants.INVALID_PASSWORD_MESSAGE), 
                         "Should show invalid password error message");
        
        // Verify user remains on login page
        Assert.assertTrue(loginPage.getCurrentUrl().contains(FrameworkConstants.LOGIN_URL), 
                         "User should remain on login page");
        
        logger.info("Test completed successfully: Valid Username Invalid Password");
    }
    
    @Test(description = "Test empty username and password fields")
    public void testEmptyCredentials() {
        logger.info("Starting test: Empty Credentials");
        
        // Leave fields empty and click login
        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.clickLoginButtonExpectingError();
        
        // Verify form should not submit with empty fields
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                         "Form should not submit with empty fields");
        Assert.assertTrue(loginPage.getCurrentUrl().contains(FrameworkConstants.LOGIN_URL), 
                         "User should remain on login page");
        
        logger.info("Test completed successfully: Empty Credentials");
    }
    
    @Test(description = "Test special characters in username and password", 
          dataProvider = "specialCharacterCredentials")
    public void testSpecialCharacterCredentials(String username, String password, 
                                              String expectedErrorFragment) {
        logger.info("Starting test: Special Character Credentials - Username: {}", username);
        
        // Enter credentials with special characters
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButtonExpectingError();
        
        // Verify error messages are displayed appropriately
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                         "Error message should be clear and informative");
        
        // Verify no sensitive information is exposed
        String errorMessage = loginPage.getFlashMessage().toLowerCase();
        Assert.assertFalse(errorMessage.contains(password.toLowerCase()), 
                          "Password should not be exposed in error messages");
        Assert.assertFalse(errorMessage.contains("exception") || 
                          errorMessage.contains("error") || 
                          errorMessage.contains("sql"), 
                          "No sensitive technical information should be exposed");
        
        // Verify user remains on login page
        Assert.assertTrue(loginPage.getCurrentUrl().contains(FrameworkConstants.LOGIN_URL), 
                         "User should remain on login page");
        
        logger.info("Test completed successfully: Special Character Credentials");
    }
    
    @Test(description = "Test comprehensive invalid login scenarios", 
          dataProvider = "invalidCredentialsSet")
    public void testMultipleInvalidCredentials(String username, String password, 
                                             String expectedError) {
        logger.info("Starting test: Multiple Invalid Credentials - Username: {}", username);
        
        // Test various invalid credential combinations
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButtonExpectingError();
        
        // Verify appropriate error handling
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                         "Error message should appear");
        
        if (expectedError != null && !expectedError.isEmpty()) {
            Assert.assertTrue(loginPage.getFlashMessage().contains(expectedError), 
                             String.format("Expected error containing '%s' but got '%s'", 
                                         expectedError, loginPage.getFlashMessage()));
        }
        
        // Verify user remains on login page
        Assert.assertTrue(loginPage.getCurrentUrl().contains(FrameworkConstants.LOGIN_URL), 
                         "User should remain on login page");
        
        logger.info("Test completed successfully: Multiple Invalid Credentials");
    }
    
    @DataProvider(name = "specialCharacterCredentials")
    public Object[][] getSpecialCharacterCredentials() {
        return new Object[][] {
            {"user@#$%", "pass@#$%", "invalid"},
            {"<script>alert('test')</script>", "password123", "invalid"},
            {"user'; DROP TABLE users; --", "password", "invalid"},
            {"user\\n\\r\\t", "pass\\n\\r", "invalid"}
        };
    }
    
    @DataProvider(name = "invalidCredentialsSet")
    public Object[][] getInvalidCredentialsSet() {
        return new Object[][] {
            {"admin", "admin123", "Your username is invalid!"},
            {"test", "test", "Your username is invalid!"},
            {"user123", FrameworkConstants.VALID_PASSWORD, "Your username is invalid!"},
            {FrameworkConstants.VALID_USERNAME, "wrongpass", "Your password is invalid!"},
            {"", "password", "Your username is invalid!"},
            {"username", "", "Your password is invalid!"},
            {" ", " ", "Your username is invalid!"},
            {"null", "null", "Your username is invalid!"}
        };
    }
}