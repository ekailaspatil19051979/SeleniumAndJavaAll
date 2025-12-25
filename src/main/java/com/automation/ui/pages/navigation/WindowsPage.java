package com.automation.ui.pages.navigation;

import com.automation.constants.FrameworkConstants;
import com.automation.core.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Set;

/**
 * Multiple Windows Page - Page Object for multiple window handling
 * Handles window switching and new window operations
 */
public class WindowsPage extends BasePage {
    
    // Page Elements
    @FindBy(linkText = "Click Here")
    private WebElement clickHereLink;
    
    @FindBy(css = "h3")
    private WebElement pageHeading;
    
    @FindBy(css = "div.example p")
    private WebElement pageDescription;
    
    // New window elements (for new window page)
    @FindBy(css = "h3")
    private WebElement newWindowHeading;
    
    private String originalWindowHandle;
    private String newWindowHandle;
    
    // Page Navigation
    public WindowsPage navigateToWindowsPage() {
        navigateToUrl(config.getProperty("app.base.url") + FrameworkConstants.WINDOWS_URL);
        waitForPageLoad();
        originalWindowHandle = driver.getWindowHandle();
        logger.info("Navigated to windows page. Original window handle: {}", originalWindowHandle);
        return this;
    }
    
    // Page Actions
    public WindowsPage clickHereLink() {
        // Store original window handle before clicking
        originalWindowHandle = driver.getWindowHandle();
        
        clickElement(clickHereLink);
        logger.info("Clicked 'Click Here' link");
        
        // Wait for new window to open and get its handle
        waitForNewWindowToOpen();
        
        return this;
    }
    
    public WindowsPage switchToNewWindow() {
        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindowHandle)) {
                newWindowHandle = windowHandle;
                driver.switchTo().window(newWindowHandle);
                logger.info("Switched to new window. Handle: {}", newWindowHandle);
                break;
            }
        }
        return this;
    }
    
    public WindowsPage switchToOriginalWindow() {
        if (originalWindowHandle != null) {
            driver.switchTo().window(originalWindowHandle);
            logger.info("Switched back to original window. Handle: {}", originalWindowHandle);
        }
        return this;
    }
    
    public WindowsPage closeNewWindow() {
        if (newWindowHandle != null) {
            driver.switchTo().window(newWindowHandle);
            driver.close();
            logger.info("Closed new window");
            
            // Switch back to original window
            switchToOriginalWindow();
        }
        return this;
    }
    
    private void waitForNewWindowToOpen() {
        int timeout = 10; // seconds
        int elapsed = 0;
        
        while (elapsed < timeout) {
            Set<String> allWindows = driver.getWindowHandles();
            if (allWindows.size() > 1) {
                logger.info("New window detected. Total windows: {}", allWindows.size());
                return;
            }
            
            try {
                Thread.sleep(500);
                elapsed += 1;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        
        logger.warn("New window did not open within timeout");
    }
    
    // Page Verifications
    public boolean isWindowsPageDisplayed() {
        return isElementDisplayed(pageHeading) && 
               isElementDisplayed(clickHereLink);
    }
    
    public boolean isClickHereLinkDisplayed() {
        return isElementDisplayed(clickHereLink);
    }
    
    public boolean isClickHereLinkEnabled() {
        return isElementEnabled(clickHereLink);
    }
    
    public boolean isNewWindowOpened() {
        return driver.getWindowHandles().size() > 1;
    }
    
    public String getPageHeading() {
        return getText(pageHeading);
    }
    
    public String getNewWindowHeading() {
        return getText(newWindowHeading);
    }
    
    public String getCurrentWindowTitle() {
        return driver.getTitle();
    }
    
    public int getWindowCount() {
        return driver.getWindowHandles().size();
    }
    
    // Validation Methods
    public boolean isNewWindowContentCorrect() {
        try {
            switchToNewWindow();
            waitActions.waitForPageLoad();
            
            String newWindowTitle = getCurrentWindowTitle();
            String newWindowHeadingText = getNewWindowHeading();
            
            logger.info("New window title: {}", newWindowTitle);
            logger.info("New window heading: {}", newWindowHeadingText);
            
            // Verify new window has expected content
            return newWindowTitle.contains("New Window") || 
                   newWindowHeadingText.contains("New Window");
                   
        } catch (Exception e) {
            logger.error("Error verifying new window content", e);
            return false;
        }
    }
    
    public boolean canSwitchBetweenWindows() {
        try {
            // Switch to new window
            switchToNewWindow();
            String newWindowTitle = getCurrentWindowTitle();
            
            // Switch back to original
            switchToOriginalWindow();
            String originalWindowTitle = getCurrentWindowTitle();
            
            // Verify we can distinguish between windows
            boolean canSwitch = !newWindowTitle.equals(originalWindowTitle);
            logger.info("Can switch between windows: {}", canSwitch);
            
            return canSwitch;
        } catch (Exception e) {
            logger.error("Error switching between windows", e);
            return false;
        }
    }
    
    public boolean isOriginalWindowFunctional() {
        try {
            switchToOriginalWindow();
            return isWindowsPageDisplayed() && isClickHereLinkDisplayed();
        } catch (Exception e) {
            logger.error("Error checking original window functionality", e);
            return false;
        }
    }
}