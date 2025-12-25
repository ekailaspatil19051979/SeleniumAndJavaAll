package com.automation.ui.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    
    public void waitForElementToBeVisible(WebElement element) {
        logger.debug("Waiting for element to be visible");
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    public void waitForElementToBeClickable(WebElement element) {
        logger.debug("Waiting for element to be clickable");
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    public void waitForElementToBeInvisible(WebElement element) {
        logger.debug("Waiting for element to be invisible");
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
    
    public void waitForPageLoad() {
        logger.debug("Waiting for page to load completely");
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
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