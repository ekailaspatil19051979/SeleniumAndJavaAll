package com.automation.ui.pages.dynamic;

import com.automation.constants.FrameworkConstants;
import com.automation.core.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Dynamic Loading Page - Page Object for dynamic content loading
 * Handles dynamic loading scenarios and content revelation
 */
public class DynamicLoadingPage extends BasePage {
    
    // Page Elements
    @FindBy(css = "button")
    private WebElement startButton;
    
    @FindBy(id = "loading")
    private WebElement loadingIndicator;
    
    @FindBy(id = "finish")
    private WebElement hiddenContent;
    
    @FindBy(css = "h3")
    private WebElement pageHeading;
    
    @FindBy(css = "div.example p")
    private WebElement pageDescription;
    
    // Page Navigation
    public DynamicLoadingPage navigateToExample1() {
        navigateToUrl(config.getProperty("app.base.url") + FrameworkConstants.DYNAMIC_LOADING_URL + "/1");
        waitForPageLoad();
        logger.info("Navigated to dynamic loading example 1");
        return this;
    }
    
    public DynamicLoadingPage navigateToExample2() {
        navigateToUrl(config.getProperty("app.base.url") + FrameworkConstants.DYNAMIC_LOADING_URL + "/2");
        waitForPageLoad();
        logger.info("Navigated to dynamic loading example 2");
        return this;
    }
    
    // Page Actions
    public DynamicLoadingPage clickStartButton() {
        clickElement(startButton);
        logger.info("Clicked start button");
        return this;
    }
    
    public DynamicLoadingPage waitForLoading() {
        // Wait for loading indicator to appear
        waitActions.waitForElementToBeVisible(loadingIndicator);
        logger.info("Loading indicator appeared");
        
        // Wait for loading to complete (indicator to disappear)
        waitActions.waitForElementToBeInvisible(loadingIndicator);
        logger.info("Loading completed");
        
        return this;
    }
    
    public DynamicLoadingPage waitForContentToBeRevealed() {
        waitActions.waitForElementToBeVisible(hiddenContent);
        logger.info("Hidden content revealed");
        return this;
    }
    
    // Page Verifications
    public boolean isDynamicLoadingPageDisplayed() {
        return isElementDisplayed(pageHeading) && 
               isElementDisplayed(startButton);
    }
    
    public boolean isStartButtonDisplayed() {
        return isElementDisplayed(startButton);
    }
    
    public boolean isStartButtonEnabled() {
        return isElementEnabled(startButton);
    }
    
    public boolean isLoadingIndicatorDisplayed() {
        return isElementDisplayed(loadingIndicator);
    }
    
    public boolean isHiddenContentDisplayed() {
        return isElementDisplayed(hiddenContent);
    }
    
    public String getHiddenContentText() {
        return getText(hiddenContent);
    }
    
    public String getPageHeading() {
        return getText(pageHeading);
    }
    
    public String getPageDescription() {
        return getText(pageDescription);
    }
    
    // Validation Methods
    public boolean isLoadingSequenceCorrect() {
        // Initial state: Start button visible, content hidden
        if (!isStartButtonDisplayed() || isHiddenContentDisplayed()) {
            logger.error("Initial state incorrect");
            return false;
        }
        
        // Click start and verify loading sequence
        clickStartButton();
        
        // Loading indicator should appear
        if (!isLoadingIndicatorDisplayed()) {
            logger.error("Loading indicator did not appear");
            return false;
        }
        
        // Wait for loading to complete
        waitForLoading();
        
        // Content should be revealed
        if (!isHiddenContentDisplayed()) {
            logger.error("Content was not revealed after loading");
            return false;
        }
        
        return true;
    }
    
    public long measureLoadingTime() {
        long startTime = System.currentTimeMillis();
        
        clickStartButton();
        waitForLoading();
        waitForContentToBeRevealed();
        
        long endTime = System.currentTimeMillis();
        long loadingTime = endTime - startTime;
        
        logger.info("Loading time measured: {} ms", loadingTime);
        return loadingTime;
    }
}