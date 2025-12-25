package com.automation.ui.pages.navigation;

import com.automation.core.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

/**
 * Frames Page - Handles frame switching and nested frame interactions
 * This page represents /frames functionality including nested frames
 * Covers test scenario 4.3: Frame handling
 */
public class FramesPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(FramesPage.class);
    
    // Page URLs
    private static final String FRAMES_URL = "/frames";
    private static final String NESTED_FRAMES_URL = "/nested_frames";
    private static final String IFRAME_URL = "/iframe";
    
    // Main frames page locators
    private final By pageHeading = By.cssSelector("h3");
    private final By nestedFramesLink = By.linkText("Nested Frames");
    private final By iframeLink = By.linkText("iFrame");
    
    // Nested frames locators
    private final By topFrame = By.name("frame-top");
    private final By middleFrame = By.name("frame-middle");
    private final By leftFrame = By.name("frame-left");
    private final By rightFrame = By.name("frame-right");
    private final By bottomFrame = By.name("frame-bottom");
    
    // iFrame locators
    private final By iframe = By.id("mce_0_ifr");
    private final By iframeTextArea = By.id("tinymce");
    private final By iframeEditor = By.cssSelector("#tinymce p");
    
    // Text content selectors for frame content
    private final By frameText = By.tagName("body");
    
    // Expected text constants
    private static final String EXPECTED_HEADING = "Frames";
    private static final String TOP_FRAME_TEXT = "TOP";
    private static final String MIDDLE_FRAME_TEXT = "MIDDLE";
    private static final String LEFT_FRAME_TEXT = "LEFT";
    private static final String RIGHT_FRAME_TEXT = "RIGHT";
    private static final String BOTTOM_FRAME_TEXT = "BOTTOM";
    private static final String DEFAULT_IFRAME_TEXT = "Your content goes here.";
    
    /**
     * Navigate to frames main page
     */
    public void navigateToFramesPage() {
        logger.info("Navigating to frames page");
        navigateToUrl(config.getProperty("app.base.url") + FRAMES_URL);
        waitForFramesPageLoad();
    }
    
    /**
     * Navigate to nested frames page
     */
    public void navigateToNestedFramesPage() {
        logger.info("Navigating to nested frames page");
        navigateToUrl(config.getProperty("app.base.url") + NESTED_FRAMES_URL);
        waitForNestedFramesLoad();
    }
    
    /**
     * Navigate to iframe page
     */
    public void navigateToIframePage() {
        logger.info("Navigating to iframe page");
        navigateToUrl(config.getProperty("app.base.url") + IFRAME_URL);
        waitForIframeLoad();
    }
    
    /**
     * Check if frames page is displayed
     * @return true if page is loaded
     */
    public boolean isFramesPageDisplayed() {
        try {
            WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeading));
            boolean isDisplayed = heading.getText().equals(EXPECTED_HEADING);
            logger.debug("Frames page displayed: {}", isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.error("Error checking if frames page is displayed: {}", e.getMessage());
            return false;
        }
    }
    
    /**
     * Click nested frames link
     */
    public void clickNestedFramesLink() {
        try {
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(nestedFramesLink));
            logger.info("Clicking nested frames link");
            link.click();
            waitForNestedFramesLoad();
        } catch (Exception e) {
            logger.error("Error clicking nested frames link: {}", e.getMessage());
            throw new RuntimeException("Failed to click nested frames link", e);
        }
    }
    
    /**
     * Click iframe link
     */
    public void clickIframeLink() {
        try {
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(iframeLink));
            logger.info("Clicking iframe link");
            link.click();
            waitForIframeLoad();
        } catch (Exception e) {
            logger.error("Error clicking iframe link: {}", e.getMessage());
            throw new RuntimeException("Failed to click iframe link", e);
        }
    }
    
    /**
     * Switch to top frame in nested frames
     */
    public void switchToTopFrame() {
        try {
            logger.info("Switching to top frame");
            WebElement frame = wait.until(ExpectedConditions.presenceOfElementLocated(topFrame));
            driver.switchTo().frame(frame);
            logger.debug("Switched to top frame successfully");
        } catch (Exception e) {
            logger.error("Error switching to top frame: {}", e.getMessage());
            throw new RuntimeException("Failed to switch to top frame", e);
        }
    }
    
    /**
     * Switch to middle frame (within top frame)
     */
    public void switchToMiddleFrame() {
        try {
            logger.info("Switching to middle frame");
            WebElement frame = wait.until(ExpectedConditions.presenceOfElementLocated(middleFrame));
            driver.switchTo().frame(frame);
            logger.debug("Switched to middle frame successfully");
        } catch (Exception e) {
            logger.error("Error switching to middle frame: {}", e.getMessage());
            throw new RuntimeException("Failed to switch to middle frame", e);
        }
    }
    
    /**
     * Switch to left frame (within top frame)
     */
    public void switchToLeftFrame() {
        try {
            logger.info("Switching to left frame");
            WebElement frame = wait.until(ExpectedConditions.presenceOfElementLocated(leftFrame));
            driver.switchTo().frame(frame);
            logger.debug("Switched to left frame successfully");
        } catch (Exception e) {
            logger.error("Error switching to left frame: {}", e.getMessage());
            throw new RuntimeException("Failed to switch to left frame", e);
        }
    }
    
    /**
     * Switch to right frame (within top frame)
     */
    public void switchToRightFrame() {
        try {
            logger.info("Switching to right frame");
            WebElement frame = wait.until(ExpectedConditions.presenceOfElementLocated(rightFrame));
            driver.switchTo().frame(frame);
            logger.debug("Switched to right frame successfully");
        } catch (Exception e) {
            logger.error("Error switching to right frame: {}", e.getMessage());
            throw new RuntimeException("Failed to switch to right frame", e);
        }
    }
    
    /**
     * Switch to bottom frame
     */
    public void switchToBottomFrame() {
        try {
            logger.info("Switching to bottom frame");
            WebElement frame = wait.until(ExpectedConditions.presenceOfElementLocated(bottomFrame));
            driver.switchTo().frame(frame);
            logger.debug("Switched to bottom frame successfully");
        } catch (Exception e) {
            logger.error("Error switching to bottom frame: {}", e.getMessage());
            throw new RuntimeException("Failed to switch to bottom frame", e);
        }
    }
    
    /**
     * Switch to iframe
     */
    public void switchToIframe() {
        try {
            logger.info("Switching to iframe");
            WebElement frame = wait.until(ExpectedConditions.presenceOfElementLocated(iframe));
            driver.switchTo().frame(frame);
            logger.debug("Switched to iframe successfully");
        } catch (Exception e) {
            logger.error("Error switching to iframe: {}", e.getMessage());
            throw new RuntimeException("Failed to switch to iframe", e);
        }
    }
    
    /**
     * Switch back to main/default content
     */
    public void switchToDefaultContent() {
        try {
            logger.info("Switching back to default content");
            driver.switchTo().defaultContent();
            logger.debug("Switched to default content successfully");
        } catch (Exception e) {
            logger.error("Error switching to default content: {}", e.getMessage());
            throw new RuntimeException("Failed to switch to default content", e);
        }
    }
    
    /**
     * Switch to parent frame
     */
    public void switchToParentFrame() {
        try {
            logger.info("Switching to parent frame");
            driver.switchTo().parentFrame();
            logger.debug("Switched to parent frame successfully");
        } catch (Exception e) {
            logger.error("Error switching to parent frame: {}", e.getMessage());
            throw new RuntimeException("Failed to switch to parent frame", e);
        }
    }
    
    /**
     * Get current frame text content
     * @return frame text content
     */
    public String getCurrentFrameText() {
        try {
            WebElement body = wait.until(ExpectedConditions.presenceOfElementLocated(frameText));
            String text = body.getText().trim();
            logger.debug("Current frame text: '{}'", text);
            return text;
        } catch (Exception e) {
            logger.error("Error getting current frame text: {}", e.getMessage());
            return "";
        }
    }
    
    /**
     * Verify frame text matches expected
     * @param expectedText expected frame text
     * @return true if text matches
     */
    public boolean verifyFrameText(String expectedText) {
        try {
            String actualText = getCurrentFrameText();
            boolean matches = expectedText.equals(actualText);
            logger.debug("Frame text verification: Expected='{}', Actual='{}', Match={}", 
                        expectedText, actualText, matches);
            return matches;
        } catch (Exception e) {
            logger.error("Error verifying frame text: {}", e.getMessage());
            return false;
        }
    }
    
    /**
     * Get iframe editor text content
     * @return iframe editor text
     */
    public String getIframeEditorText() {
        try {
            WebElement editor = wait.until(ExpectedConditions.presenceOfElementLocated(iframeEditor));
            String text = editor.getText().trim();
            logger.debug("Iframe editor text: '{}'", text);
            return text;
        } catch (Exception e) {
            logger.error("Error getting iframe editor text: {}", e.getMessage());
            return "";
        }
    }
    
    /**
     * Type text in iframe editor
     * @param text text to type
     */
    public void typeInIframeEditor(String text) {
        try {
            WebElement editor = wait.until(ExpectedConditions.elementToBeClickable(iframeEditor));
            logger.info("Typing text in iframe editor: '{}'", text);
            editor.clear();
            editor.sendKeys(text);
            logger.debug("Text typed successfully in iframe editor");
        } catch (Exception e) {
            logger.error("Error typing in iframe editor: {}", e.getMessage());
            throw new RuntimeException("Failed to type in iframe editor", e);
        }
    }
    
    /**
     * Test complete nested frames navigation sequence
     * @return true if all frames can be navigated successfully
     */
    public boolean testNestedFramesNavigation() {
        try {
            logger.info("Testing nested frames navigation sequence");
            
            // Test top frame -> left frame
            switchToTopFrame();
            switchToLeftFrame();
            if (!verifyFrameText(LEFT_FRAME_TEXT)) {
                logger.error("Left frame text verification failed");
                return false;
            }
            
            // Back to top frame, then middle
            switchToParentFrame();
            switchToMiddleFrame();
            if (!verifyFrameText(MIDDLE_FRAME_TEXT)) {
                logger.error("Middle frame text verification failed");
                return false;
            }
            
            // Back to top frame, then right
            switchToParentFrame();
            switchToRightFrame();
            if (!verifyFrameText(RIGHT_FRAME_TEXT)) {
                logger.error("Right frame text verification failed");
                return false;
            }
            
            // Back to default, then bottom
            switchToDefaultContent();
            switchToBottomFrame();
            if (!verifyFrameText(BOTTOM_FRAME_TEXT)) {
                logger.error("Bottom frame text verification failed");
                return false;
            }
            
            // Return to default
            switchToDefaultContent();
            
            logger.info("Nested frames navigation sequence completed successfully");
            return true;
        } catch (Exception e) {
            logger.error("Error in nested frames navigation: {}", e.getMessage());
            switchToDefaultContent(); // Ensure we're back to default
            return false;
        }
    }
    
    /**
     * Test iframe text editing
     * @param testText text to test with
     * @return true if iframe editing works
     */
    public boolean testIframeEditing(String testText) {
        try {
            logger.info("Testing iframe editing with text: '{}'", testText);
            
            switchToIframe();
            
            // Get initial text
            String initialText = getIframeEditorText();
            logger.debug("Initial iframe text: '{}'", initialText);
            
            // Type new text
            typeInIframeEditor(testText);
            
            // Verify text was typed
            String currentText = getIframeEditorText();
            boolean success = testText.equals(currentText);
            
            logger.info("Iframe editing test - Success: {}, Expected: '{}', Actual: '{}'", 
                       success, testText, currentText);
            
            switchToDefaultContent();
            return success;
        } catch (Exception e) {
            logger.error("Error in iframe editing test: {}", e.getMessage());
            switchToDefaultContent(); // Ensure we're back to default
            return false;
        }
    }
    
    /**
     * Get count of available frames
     * @return number of frames on current page
     */
    public int getFrameCount() {
        try {
            List<WebElement> frames = driver.findElements(By.tagName("frame"));
            List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
            int totalFrames = frames.size() + iframes.size();
            logger.debug("Total frames found: {} (frames: {}, iframes: {})", 
                        totalFrames, frames.size(), iframes.size());
            return totalFrames;
        } catch (Exception e) {
            logger.error("Error getting frame count: {}", e.getMessage());
            return 0;
        }
    }
    
    /**
     * Wait for nested frames page to load
     */
    private void waitForNestedFramesLoad() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(topFrame));
            wait.until(ExpectedConditions.presenceOfElementLocated(bottomFrame));
            logger.debug("Nested frames page loaded");
        } catch (Exception e) {
            logger.error("Error waiting for nested frames load: {}", e.getMessage());
            throw new RuntimeException("Nested frames page failed to load", e);
        }
    }
    
    /**
     * Wait for iframe page to load
     */
    private void waitForIframeLoad() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(iframe));
            logger.debug("Iframe page loaded");
        } catch (Exception e) {
            logger.error("Error waiting for iframe load: {}", e.getMessage());
            throw new RuntimeException("Iframe page failed to load", e);
        }
    }
    
    /**
     * Wait for frames page to load
     */
    protected void waitForFramesPageLoad() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeading));
            wait.until(ExpectedConditions.elementToBeClickable(nestedFramesLink));
            wait.until(ExpectedConditions.elementToBeClickable(iframeLink));
            logger.debug("Frames page loaded");
        } catch (Exception e) {
            logger.error("Error waiting for frames page load: {}", e.getMessage());
            throw new RuntimeException("Frames page failed to load", e);
        }
    }
}