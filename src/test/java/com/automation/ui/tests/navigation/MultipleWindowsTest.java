package com.automation.ui.tests.navigation;

import com.automation.core.base.BaseTest;
import com.automation.ui.pages.navigation.WindowsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Multiple Windows Test - Tests multiple window handling functionality
 * Based on test scenario 4.1 from the test plan
 */
public class MultipleWindowsTest extends BaseTest {
    private WindowsPage windowsPage;
    
    @BeforeMethod
    public void setUpWindowsTest() {
        windowsPage = new WindowsPage();
        logger.info("Initialized WindowsPage for test");
    }
    
    @Test(description = "Test opening and handling multiple windows")
    public void testMultipleWindowsHandling() {
        logger.info("Starting test: Multiple Windows Handling");
        
        // Step 1: Navigate to windows page
        windowsPage.navigateToWindowsPage();
        logger.info("Step 1: Navigated to windows page");
        
        // Verify windows page is displayed
        Assert.assertTrue(windowsPage.isWindowsPageDisplayed(), 
                         "Windows page should be displayed");
        
        // Verify initial window count
        int initialWindowCount = windowsPage.getWindowCount();
        Assert.assertEquals(initialWindowCount, 1, 
                           "Should start with one window");
        logger.info("Initial window count: {}", initialWindowCount);
        
        // Step 2: Click 'Click Here' link to open new window
        Assert.assertTrue(windowsPage.isClickHereLinkDisplayed(), 
                         "Link should be displayed");
        Assert.assertTrue(windowsPage.isClickHereLinkEnabled(), 
                         "Link should be clickable");
        
        windowsPage.clickHereLink();
        logger.info("Step 2: Clicked 'Click Here' link");
        
        // Step 3: Verify new window should open
        Assert.assertTrue(windowsPage.isNewWindowOpened(), 
                         "New window should open");
        
        int newWindowCount = windowsPage.getWindowCount();
        Assert.assertEquals(newWindowCount, 2, 
                           "Should have two windows after clicking link");
        logger.info("Step 3: New window opened. Total windows: {}", newWindowCount);
        
        // Step 4: Switch to new window
        windowsPage.switchToNewWindow();
        logger.info("Step 4: Switched to new window");
        
        // Step 5: Verify new window should contain expected content
        Assert.assertTrue(windowsPage.isNewWindowContentCorrect(), 
                         "New window should contain expected content");
        logger.info("Step 5: Verified new window content");
        
        // Step 6: Switch back to original window
        windowsPage.switchToOriginalWindow();
        logger.info("Step 6: Switched back to original window");
        
        // Step 7: Verify original window should remain functional
        Assert.assertTrue(windowsPage.isOriginalWindowFunctional(), 
                         "Original window should remain functional");
        logger.info("Step 7: Verified original window functionality");
        
        // Step 8: Verify should be able to switch between windows
        Assert.assertTrue(windowsPage.canSwitchBetweenWindows(), 
                         "Should be able to switch between windows");
        logger.info("Step 8: Verified window switching capability");
        
        // Step 9: Close new window
        windowsPage.closeNewWindow();
        logger.info("Step 9: Closed new window");
        
        // Verify windows should close properly
        int finalWindowCount = windowsPage.getWindowCount();
        Assert.assertEquals(finalWindowCount, 1, 
                           "Should return to single window after closing");
        logger.info("Final window count: {}", finalWindowCount);
        
        logger.info("Test completed successfully: Multiple Windows Handling");
    }
    
    @Test(description = "Test window switching consistency")
    public void testWindowSwitchingConsistency() {
        logger.info("Starting test: Window Switching Consistency");
        
        // Navigate and open new window
        windowsPage.navigateToWindowsPage();
        windowsPage.clickHereLink();
        
        // Test multiple switches to ensure consistency
        for (int i = 1; i <= 3; i++) {
            logger.info("Switch iteration #{}", i);
            
            // Switch to new window
            windowsPage.switchToNewWindow();
            String newWindowTitle = windowsPage.getCurrentWindowTitle();
            Assert.assertNotNull(newWindowTitle, 
                               String.format("New window title should be available in iteration %d", i));
            
            // Switch back to original
            windowsPage.switchToOriginalWindow();
            Assert.assertTrue(windowsPage.isOriginalWindowFunctional(), 
                             String.format("Original window should be functional in iteration %d", i));
            
            logger.info("Switch iteration #{} completed successfully", i);
        }
        
        // Clean up
        windowsPage.closeNewWindow();
        
        logger.info("Test completed successfully: Window Switching Consistency");
    }
    
    @Test(description = "Test new window content verification")
    public void testNewWindowContentVerification() {
        logger.info("Starting test: New Window Content Verification");
        
        // Navigate and open new window
        windowsPage.navigateToWindowsPage();
        windowsPage.clickHereLink();
        
        // Switch to new window and verify content
        windowsPage.switchToNewWindow();
        
        String newWindowTitle = windowsPage.getCurrentWindowTitle();
        String newWindowHeading = windowsPage.getNewWindowHeading();
        
        logger.info("New window title: {}", newWindowTitle);
        logger.info("New window heading: {}", newWindowHeading);
        
        // Verify new window has distinct content
        Assert.assertNotNull(newWindowTitle, "New window should have a title");
        Assert.assertNotNull(newWindowHeading, "New window should have a heading");
        
        // Switch back and compare with original
        windowsPage.switchToOriginalWindow();
        String originalWindowTitle = windowsPage.getCurrentWindowTitle();
        String originalWindowHeading = windowsPage.getPageHeading();
        
        logger.info("Original window title: {}", originalWindowTitle);
        logger.info("Original window heading: {}", originalWindowHeading);
        
        // Verify windows have different content
        Assert.assertNotEquals(newWindowTitle, originalWindowTitle, 
                              "Windows should have different titles");
        
        // Clean up
        windowsPage.closeNewWindow();
        
        logger.info("Test completed successfully: New Window Content Verification");
    }
    
    @Test(description = "Test window cleanup and resource management")
    public void testWindowCleanupAndResourceManagement() {
        logger.info("Starting test: Window Cleanup and Resource Management");
        
        // Navigate and open multiple windows
        windowsPage.navigateToWindowsPage();
        
        // Open and close window multiple times to test resource management
        for (int i = 1; i <= 3; i++) {
            logger.info("Window lifecycle iteration #{}", i);
            
            // Open new window
            windowsPage.clickHereLink();
            Assert.assertEquals(windowsPage.getWindowCount(), 2, 
                              String.format("Should have 2 windows in iteration %d", i));
            
            // Verify new window works
            windowsPage.switchToNewWindow();
            Assert.assertTrue(windowsPage.isNewWindowContentCorrect(), 
                             String.format("New window should work correctly in iteration %d", i));
            
            // Close window
            windowsPage.closeNewWindow();
            Assert.assertEquals(windowsPage.getWindowCount(), 1, 
                              String.format("Should have 1 window after closing in iteration %d", i));
            
            logger.info("Window lifecycle iteration #{} completed", i);
        }
        
        // Verify original window is still functional
        Assert.assertTrue(windowsPage.isOriginalWindowFunctional(), 
                         "Original window should remain functional after multiple operations");
        
        logger.info("Test completed successfully: Window Cleanup and Resource Management");
    }
}