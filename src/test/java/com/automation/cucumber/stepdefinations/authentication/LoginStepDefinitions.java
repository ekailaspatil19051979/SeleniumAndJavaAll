package com.automation.cucumber.stepdefinations.authentication;

import com.automation.constants.FrameworkConstants;
import com.automation.ui.pages.authentication.LoginPage;
import com.automation.ui.pages.authentication.SecureAreaPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

/**
 * Login Step Definitions - Cucumber step definitions for login functionality
 */
public class LoginStepDefinitions {
    private LoginPage loginPage;
    private SecureAreaPage secureAreaPage;
    
    @Given("I navigate to the login page")
    public void iNavigateToTheLoginPage() {
        loginPage = new LoginPage().navigateToLoginPage();
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page is not displayed");
    }
    
    @When("I enter username {string}")
    public void iEnterUsername(String username) {
        loginPage.enterUsername(username);
    }
    
    @And("I enter password {string}")
    public void iEnterPassword(String password) {
        loginPage.enterPassword(password);
    }
    
    @And("I click the login button")
    public void iClickTheLoginButton() {
        // Check if expecting success or error based on previous steps
        if (loginPage.getCurrentUrl().contains(FrameworkConstants.LOGIN_URL)) {
            try {
                secureAreaPage = loginPage.clickLoginButton();
            } catch (Exception e) {
                // If exception occurs, stay on login page for error scenarios
                loginPage = loginPage.clickLoginButtonExpectingError();
            }
        }
    }
    
    @Then("I should be redirected to the secure area")
    public void iShouldBeRedirectedToTheSecureArea() {
        Assert.assertNotNull(secureAreaPage, "Secure area page is null");
        Assert.assertTrue(secureAreaPage.isSecureAreaPageDisplayed(), 
                         "User is not redirected to secure area");
    }
    
    @And("I should see the success message {string}")
    public void iShouldSeeTheSuccessMessage(String expectedMessage) {
        Assert.assertTrue(secureAreaPage.isFlashMessageDisplayed(), 
                         "Success message is not displayed");
        Assert.assertTrue(secureAreaPage.getFlashMessage().contains(expectedMessage), 
                         "Success message does not match expected text");
    }
    
    @And("I should see the logout button")
    public void iShouldSeeTheLogoutButton() {
        Assert.assertTrue(secureAreaPage.isLogoutButtonDisplayed(), 
                         "Logout button is not displayed");
        Assert.assertTrue(secureAreaPage.isLogoutButtonEnabled(), 
                         "Logout button is not enabled");
    }
    
    @Then("I should remain on the login page")
    public void iShouldRemainOnTheLoginPage() {
        Assert.assertTrue(loginPage.getCurrentUrl().contains(FrameworkConstants.LOGIN_URL), 
                         "User is not on login page");
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
                         "Login page elements are not displayed");
    }
    
    @And("I should see the error message {string}")
    public void iShouldSeeTheErrorMessage(String expectedError) {
        Assert.assertTrue(loginPage.isFlashMessageDisplayed(), 
                         "Error message is not displayed");
        Assert.assertTrue(loginPage.getFlashMessage().contains(expectedError), 
                         String.format("Expected error '%s' but got '%s'", 
                                     expectedError, loginPage.getFlashMessage()));
    }
    
    @And("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                         "Error message is not displayed");
    }
    
    @Given("I login with valid credentials")
    public void iLoginWithValidCredentials() {
        if (loginPage == null) {
            loginPage = new LoginPage().navigateToLoginPage();
        }
        secureAreaPage = loginPage.loginSuccessfully(
            FrameworkConstants.VALID_USERNAME, 
            FrameworkConstants.VALID_PASSWORD
        );
    }
    
    @Given("I am on the secure area page")
    public void iAmOnTheSecureAreaPage() {
        Assert.assertNotNull(secureAreaPage, "Secure area page is null");
        Assert.assertTrue(secureAreaPage.isUserLoggedIn(), 
                         "User is not logged in to secure area");
    }
    
    @When("I click the logout button")
    public void iClickTheLogoutButton() {
        loginPage = secureAreaPage.clickLogoutButton();
    }
    
    @And("I should see the logout success message {string}")
    public void iShouldSeeTheLogoutSuccessMessage(String expectedMessage) {
        Assert.assertTrue(loginPage.isFlashMessageDisplayed(), 
                         "Logout success message is not displayed");
        Assert.assertTrue(loginPage.getFlashMessage().contains(expectedMessage), 
                         "Logout success message does not match expected text");
    }
    
    @And("I should see the login form")
    public void iShouldSeeTheLoginForm() {
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
                         "Login form is not displayed after logout");
    }
}