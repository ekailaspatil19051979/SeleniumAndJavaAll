package com.automation.ui.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

import java.time.Duration;

/**
 * Wait Actions - Centralized wait strategies and utilities
 */
public class WaitActions {
    private static final Logger logger = LogManager.getLogger(WaitActions.class);
    private WebDriver driver;
    private WebDriverWait wait;
    private static final int DEFAULT_TIMEOUT = 30;
    
    public WaitActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
    }
    
    public WaitActions(WebDriver driver, int timeoutInSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }
    
    public WebElement waitForElementToBeVisible(WebElement element) {
        logger.debug("Waiting for element to be visible");
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    public WebElement waitForElementToBeClickable(WebElement element) {
        logger.debug("Waiting for element to be clickable");
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    public boolean waitForElementToBeInvisible(WebElement element) {
        logger.debug("Waiting for element to be invisible");
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }
    
    public void waitForPageLoad() {
        logger.debug("Waiting for page to load completely");
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }
    
    /**
     * Wait for element to be present by locator (Selenium 4 optimized)
     */
    public WebElement waitForElementToBePresent(By locator) {
        logger.debug("Waiting for element to be present: {}", locator);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    /**
     * Wait for elements to be present by locator (Selenium 4 optimized)
     */
    public List<WebElement> waitForElementsToBePresent(By locator) {
        logger.debug("Waiting for elements to be present: {}", locator);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }
    
    /**
     * Wait for element to be visible by locator (Selenium 4 optimized)
     */
    public WebElement waitForElementToBeVisible(By locator) {
        logger.debug("Waiting for element to be visible: {}", locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    /**
     * Wait for element to be clickable by locator (Selenium 4 optimized)
     */
    public WebElement waitForElementToBeClickable(By locator) {
        logger.debug("Waiting for element to be clickable: {}", locator);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    public void waitForUrlToContain(String urlFragment) {
        logger.debug("Waiting for URL to contain: {}", urlFragment);
        wait.until(ExpectedConditions.urlContains(urlFragment));
    }
    
    public void waitForTitleToContain(String title) {
        logger.debug("Waiting for title to contain: {}", title);
        wait.until(ExpectedConditions.titleContains(title));
    }
    
    public void waitForTextToBePresentInElement(WebElement element, String text) {
        logger.debug("Waiting for text '{}' to be present in element", text);
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }
    
    public void sleep(long milliseconds) {
        try {
            logger.debug("Sleeping for {} milliseconds", milliseconds);
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            logger.error("Sleep interrupted", e);
            Thread.currentThread().interrupt();
        }
    }
}