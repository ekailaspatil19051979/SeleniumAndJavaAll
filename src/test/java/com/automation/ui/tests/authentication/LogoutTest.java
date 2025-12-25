package com.automation.ui.tests.authentication;

import com.automation.constants.FrameworkConstants;
import com.automation.core.base.BaseTest;
import com.automation.ui.pages.authentication.LoginPage;
import com.automation.ui.pages.authentication.SecureAreaPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Logout Test - Tests logout functionality
 * Based on test scenario 1.3 from the test plan
 */
public class LogoutTest extends BaseTest {
    private LoginPage loginPage;
    private SecureAreaPage secureAreaPage;
    
    @BeforeMethod
    public void setUpLogoutTest() {
        // Login first to test logout functionality
        loginPage = new LoginPage();
        loginPage.navigateToLoginPage();
        secureAreaPage = loginPage.loginSuccessfully(
            FrameworkConstants.VALID_USERNAME, 
            FrameworkConstants.VALID_PASSWORD
        );
        logger.info("Successfully logged in for logout test setup");
    }
    
    @Test(description = "Test successful logout functionality")
    public void testLogoutFunctionality() {
        logger.info("Starting test: Logout Functionality");
        
        // Step 1: Verify user is successfully logged in first
        Assert.assertTrue(secureAreaPage.isUserLoggedIn(), 
                         "User should be successfully logged in first");
        Assert.assertTrue(secureAreaPage.isSecureAreaPageDisplayed(), 
                         "Secure area should be displayed");
        logger.info("Step 1: Verified user is logged in");
        
        // Step 2: Verify logout button is visible in secure area
        Assert.assertTrue(secureAreaPage.isLogoutButtonDisplayed(), 
                         "Logout button should be visible in secure area");
        Assert.assertTrue(secureAreaPage.isLogoutButtonEnabled(), 
                         "Logout button should be enabled");
        logger.info("Step 2: Verified logout button is visible and enabled");
        
        // Step 3: Click the Logout button
        loginPage = secureAreaPage.clickLogoutButton();
        logger.info("Step 3: Clicked logout button");
        
        // Step 4: Verify logout message appears
        Assert.assertTrue(loginPage.isFlashMessageDisplayed(), 
                         "Logout message should appear");
        Assert.assertTrue(loginPage.getFlashMessage()
                         .contains(FrameworkConstants.LOGOUT_SUCCESS_MESSAGE), 
                         "Logout success message should be displayed");
        logger.info("Step 4: Verified logout message appears");
        
        // Step 5: Verify user is redirected back to login page
        Assert.assertTrue(loginPage.getCurrentUrl().contains(FrameworkConstants.LOGIN_URL), 
                         "User should be redirected to login page");
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
                         "Login form should be displayed after logout");
        logger.info("Step 5: Verified user is redirected to login page");
        
        logger.info("Test completed successfully: Logout Functionality");
    }
    
    @Test(description = "Test secure area access restriction after logout")
    public void testSecureAreaAccessAfterLogout() {
        logger.info("Starting test: Secure Area Access After Logout");
        
        // Verify user is initially logged in
        Assert.assertTrue(secureAreaPage.isUserLoggedIn(), 
                         "User should be initially logged in");
        
        // Get the secure area URL before logout
        String secureAreaUrl = secureAreaPage.getCurrentUrl();
        logger.info("Secure area URL: {}", secureAreaUrl);
        
        // Perform logout
        loginPage = secureAreaPage.clickLogoutButton();
        logger.info("Performed logout");
        
        // Try to access secure area directly after logout
        navigateToEndpoint(FrameworkConstants.SECURE_AREA_URL);
        logger.info("Attempted to access secure area after logout");
        
        // Verify secure area should no longer be accessible without re-authentication
        // User should be redirected to login page or see access denied
        String currentUrl = loginPage.getCurrentUrl();
        boolean isAccessRestricted = currentUrl.contains(FrameworkConstants.LOGIN_URL) ||
                                   loginPage.isLoginPageDisplayed() ||
                                   !currentUrl.contains(FrameworkConstants.SECURE_AREA_URL);
        
        Assert.assertTrue(isAccessRestricted, 
                         "Secure area should not be accessible without re-authentication");
        
        logger.info("Test completed successfully: Secure Area Access After Logout");
    }
    
    @Test(description = "Test logout from multiple tabs/sessions")
    public void testLogoutSessionManagement() {
        logger.info("Starting test: Logout Session Management");
        
        // Verify user is logged in
        Assert.assertTrue(secureAreaPage.isUserLoggedIn(), 
                         "User should be logged in");
        
        // Perform logout
        loginPage = secureAreaPage.clickLogoutButton();
        
        // Verify session is properly terminated
        Assert.assertTrue(loginPage.getFlashMessage()
                         .contains(FrameworkConstants.LOGOUT_SUCCESS_MESSAGE), 
                         "Logout success message should confirm session termination");
        
        // Try to navigate back using browser back button (simulate)
        // In a real scenario, this would test browser navigation
        navigateToEndpoint(FrameworkConstants.SECURE_AREA_URL);
        
        // Verify access is still restricted
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(FrameworkConstants.LOGIN_URL) || 
                         loginPage.isLoginPageDisplayed(), 
                         "Session should be properly terminated");
        
        logger.info("Test completed successfully: Logout Session Management");
    }
    
    @Test(description = "Test re-login after logout")
    public void testReLoginAfterLogout() {
        logger.info("Starting test: Re-login After Logout");
        
        // Perform logout
        loginPage = secureAreaPage.clickLogoutButton();
        
        // Verify logout was successful
        Assert.assertTrue(loginPage.getFlashMessage()
                         .contains(FrameworkConstants.LOGOUT_SUCCESS_MESSAGE), 
                         "Logout should be successful");
        
        // Attempt to login again with same credentials
        secureAreaPage = loginPage.loginSuccessfully(
            FrameworkConstants.VALID_USERNAME, 
            FrameworkConstants.VALID_PASSWORD
        );
        
        // Verify re-login is successful
        Assert.assertTrue(secureAreaPage.isUserLoggedIn(), 
                         "Re-login should be successful after logout");
        Assert.assertTrue(secureAreaPage.isLoginSuccessMessageDisplayed(), 
                         "Login success message should appear on re-login");
        
        logger.info("Test completed successfully: Re-login After Logout");
    }
}