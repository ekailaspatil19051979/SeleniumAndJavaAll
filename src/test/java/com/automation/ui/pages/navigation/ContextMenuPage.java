package com.automation.ui.pages.navigation;

import com.automation.core.base.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Context Menu Page - Handles right-click context menu interactions and JavaScript alerts
 * This page represents /context_menu functionality
 * Covers test scenario 4.2: Context Menu interactions
 */
public class ContextMenuPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(ContextMenuPage.class);
    
    // Page URL
    private static final String CONTEXT_MENU_URL = "/context_menu";
    
    // Locators
    private final By pageHeading = By.cssSelector("h3");
    private final By contextMenuArea = By.id("hot-spot");
    private final By instructionText = By.cssSelector("div.example p");
    
    // Expected text constants
    private static final String EXPECTED_HEADING = "Context Menu";
    private static final String EXPECTED_ALERT_TEXT = "You selected a context menu";
    private static final String EXPECTED_INSTRUCTION = "Right-click in the box below to see one called 'the-internet'";
    
    /**
     * Navigate to context menu page
     */
    public void navigateToContextMenuPage() {
        logger.info("Navigating to context menu page");
        navigateToUrl(config.getProperty("app.base.url") + CONTEXT_MENU_URL);
        waitForPageLoad();
    }
    
    /**
     * Check if context menu page is displayed
     * @return true if page is loaded
     */
    public boolean isContextMenuPageDisplayed() {
        try {
            WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeading));
            boolean isDisplayed = heading.getText().equals(EXPECTED_HEADING);
            logger.debug("Context menu page displayed: {}", isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.error("Error checking if context menu page is displayed: {}", e.getMessage());
            return false;
        }
    }
    
    /**
     * Get page heading text
     * @return heading text
     */
    public String getPageHeading() {
        try {
            WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeading));
            String headingText = heading.getText();
            logger.debug("Page heading: {}", headingText);
            return headingText;
        } catch (Exception e) {
            logger.error("Error getting page heading: {}", e.getMessage());
            return "";
        }
    }
    
    /**
     * Check if context menu area is displayed
     * @return true if context menu area is visible
     */
    public boolean isContextMenuAreaDisplayed() {
        try {
            WebElement area = wait.until(ExpectedConditions.visibilityOfElementLocated(contextMenuArea));
            boolean isDisplayed = area.isDisplayed();
            logger.debug("Context menu area displayed: {}", isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.error("Error checking if context menu area is displayed: {}", e.getMessage());
            return false;
        }
    }
    
    /**
     * Get instruction text
     * @return instruction text
     */
    public String getInstructionText() {
        try {
            WebElement instruction = wait.until(ExpectedConditions.visibilityOfElementLocated(instructionText));
            String text = instruction.getText();
            logger.debug("Instruction text: {}", text);
            return text;
        } catch (Exception e) {
            logger.error("Error getting instruction text: {}", e.getMessage());
            return "";
        }
    }
    
    /**
     * Perform right-click on context menu area
     */
    public void rightClickOnContextMenuArea() {
        try {
            WebElement area = wait.until(ExpectedConditions.elementToBeClickable(contextMenuArea));
            logger.info("Performing right-click on context menu area");
            
            Actions actions = new Actions(driver);
            actions.contextClick(area).perform();
            
            logger.debug("Right-click performed successfully");
        } catch (Exception e) {
            logger.error("Error performing right-click on context menu area: {}", e.getMessage());
            throw new RuntimeException("Failed to right-click on context menu area", e);
        }
    }
    
    /**
     * Check if JavaScript alert is present
     * @return true if alert is present
     */
    public boolean isAlertPresent() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            logger.debug("JavaScript alert is present");
            return true;
        } catch (Exception e) {
            logger.debug("No JavaScript alert present: {}", e.getMessage());
            return false;
        }
    }
    
    /**
     * Get JavaScript alert text
     * @return alert text
     */
    public String getAlertText() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            logger.debug("Alert text: {}", alertText);
            return alertText;
        } catch (Exception e) {
            logger.error("Error getting alert text: {}", e.getMessage());
            return "";
        }
    }
    
    /**
     * Accept JavaScript alert
     */
    public void acceptAlert() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            logger.info("Accepting JavaScript alert");
            alert.accept();
            logger.debug("Alert accepted successfully");
        } catch (Exception e) {
            logger.error("Error accepting alert: {}", e.getMessage());
            throw new RuntimeException("Failed to accept alert", e);
        }
    }
    
    /**
     * Dismiss JavaScript alert
     */
    public void dismissAlert() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            logger.info("Dismissing JavaScript alert");
            alert.dismiss();
            logger.debug("Alert dismissed successfully");
        } catch (Exception e) {
            logger.error("Error dismissing alert: {}", e.getMessage());
            throw new RuntimeException("Failed to dismiss alert", e);
        }
    }
    
    /**
     * Check if alert text is correct
     * @return true if alert text matches expected
     */
    public boolean isAlertTextCorrect() {
        try {
            String actualText = getAlertText();
            boolean isCorrect = EXPECTED_ALERT_TEXT.equals(actualText);
            logger.debug("Alert text correct: {} (Expected: '{}', Actual: '{}')", 
                        isCorrect, EXPECTED_ALERT_TEXT, actualText);
            return isCorrect;
        } catch (Exception e) {
            logger.error("Error checking alert text: {}", e.getMessage());
            return false;
        }
    }
    
    /**
     * Perform complete right-click context menu test sequence
     * @return true if sequence completed successfully
     */
    public boolean performContextMenuTestSequence() {
        try {
            logger.info("Starting context menu test sequence");
            
            // Step 1: Right-click on area
            rightClickOnContextMenuArea();
            
            // Step 2: Wait for and verify alert
            if (!isAlertPresent()) {
                logger.error("Alert not present after right-click");
                return false;
            }
            
            // Step 3: Verify alert text
            if (!isAlertTextCorrect()) {
                logger.error("Alert text is incorrect");
                acceptAlert(); // Clean up
                return false;
            }
            
            // Step 4: Accept alert
            acceptAlert();
            
            logger.info("Context menu test sequence completed successfully");
            return true;
        } catch (Exception e) {
            logger.error("Error in context menu test sequence: {}", e.getMessage());
            // Try to clean up any remaining alert
            try {
                if (isAlertPresent()) {
                    acceptAlert();
                }
            } catch (Exception cleanupError) {
                logger.warn("Could not clean up alert: {}", cleanupError.getMessage());
            }
            return false;
        }
    }
    
    /**
     * Wait for page to load completely
     */
    protected void waitForContextMenuPageLoad() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeading));
            wait.until(ExpectedConditions.visibilityOfElementLocated(contextMenuArea));
            wait.until(ExpectedConditions.visibilityOfElementLocated(instructionText));
            logger.debug("Context menu page loaded completely");
        } catch (Exception e) {
            logger.error("Error waiting for page load: {}", e.getMessage());
            throw new RuntimeException("Context menu page failed to load", e);
        }
    }
    
    /**
     * Check if instruction text is correct
     * @return true if instruction text matches expected
     */
    public boolean isInstructionTextCorrect() {
        try {
            String actualText = getInstructionText();
            boolean isCorrect = actualText.contains("Right-click in the box below");
            logger.debug("Instruction text correct: {}", isCorrect);
            return isCorrect;
        } catch (Exception e) {
            logger.error("Error checking instruction text: {}", e.getMessage());
            return false;
        }
    }
    
    /**
     * Get context menu area element for advanced interactions
     * @return WebElement of context menu area
     */
    public WebElement getContextMenuArea() {
        try {
            WebElement area = wait.until(ExpectedConditions.visibilityOfElementLocated(contextMenuArea));
            logger.debug("Retrieved context menu area element");
            return area;
        } catch (Exception e) {
            logger.error("Error getting context menu area element: {}", e.getMessage());
            throw new RuntimeException("Failed to get context menu area element", e);
        }
    }
    
    /**
     * Test alert handling with different approaches
     * @param acceptAlert whether to accept or dismiss the alert
     * @return true if handling was successful
     */
    public boolean testAlertHandling(boolean acceptAlert) {
        try {
            logger.info("Testing alert handling - Accept: {}", acceptAlert);
            
            rightClickOnContextMenuArea();
            
            if (!isAlertPresent()) {
                logger.error("No alert appeared after right-click");
                return false;
            }
            
            if (!isAlertTextCorrect()) {
                logger.error("Alert text is incorrect");
                return false;
            }
            
            if (acceptAlert) {
                acceptAlert();
            } else {
                dismissAlert();
            }
            
            // Verify alert is gone
            boolean alertStillPresent = isAlertPresent();
            if (alertStillPresent) {
                logger.error("Alert still present after handling");
                return false;
            }
            
            logger.info("Alert handling test completed successfully");
            return true;
        } catch (Exception e) {
            logger.error("Error in alert handling test: {}", e.getMessage());
            return false;
        }
    }
}