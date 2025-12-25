package com.automation.core.base;

import com.automation.config.ConfigManager;
import com.automation.core.driver.DriverManager;
import com.automation.ui.actions.WaitActions;
import com.automation.utils.ScreenshotUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Base Page - Parent class for all Page Objects
 * Contains common functionality and utilities for page interactions
 */
public abstract class BasePage {
    protected final Logger logger = LogManager.getLogger(this.getClass());
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WaitActions waitActions;
    protected ConfigManager config;
    protected JavascriptExecutor jsExecutor;
    
    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.config = ConfigManager.getInstance();
        int timeoutSeconds = config.getIntProperty("selenium.timeout", 30);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        this.waitActions = new WaitActions(driver);
        this.jsExecutor = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
        logger.debug("Initialized page: {}", this.getClass().getSimpleName());
    }
    
    /**
     * Navigate to a specific URL
     */
    protected void navigateToUrl(String url) {
        logger.info("Navigating to URL: {}", url);
        driver.get(url);
    }
    
    /**
     * Get current page title
     */
    public String getPageTitle() {
        String title = driver.getTitle();
        logger.debug("Current page title: {}", title);
        return title;
    }
    
    /**
     * Get current page URL
     */
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        logger.debug("Current page URL: {}", url);
        return url;
    }
    
    /**
     * Check if element is displayed
     */
    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            logger.debug("Element not displayed: {}", e.getMessage());
            return false;
        }
    }
    
    /**
     * Check if element is enabled
     */
    protected boolean isElementEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (Exception e) {
            logger.debug("Element not enabled: {}", e.getMessage());
            return false;
        }
    }
    
    /**
     * Click element with wait
     */
    protected void clickElement(WebElement element) {
        waitActions.waitForElementToBeClickable(element);
        element.click();
        logger.debug("Clicked element: {}", element.toString());
    }
    
    /**
     * Type text into element
     */
    protected void typeText(WebElement element, String text) {
        waitActions.waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
        logger.debug("Typed text '{}' into element: {}", text, element.toString());
    }
    
    /**
     * Get text from element
     */
    protected String getText(WebElement element) {
        waitActions.waitForElementToBeVisible(element);
        String text = element.getText();
        logger.debug("Retrieved text '{}' from element: {}", text, element.toString());
        return text;
    }
    
    /**
     * Take screenshot
     */
    public void takeScreenshot(String testName) {
        ScreenshotUtils.captureScreenshot(driver, testName);
    }
    
    /**
     * Wait for page to load completely
     */
    protected void waitForPageLoad() {
        waitActions.waitForPageLoad();
    }
}