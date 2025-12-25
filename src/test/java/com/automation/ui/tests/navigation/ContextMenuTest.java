package com.automation.ui.tests.navigation;

import com.automation.core.base.BaseTest;
import com.automation.ui.pages.navigation.ContextMenuPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Context Menu Test - Tests right-click context menu and JavaScript alert handling
 * Based on test scenario 4.2 from the test plan
 */
public class ContextMenuTest extends BaseTest {
    private ContextMenuPage contextMenuPage;
    
    @BeforeMethod
    public void setUpContextMenuTest() {
        contextMenuPage = new ContextMenuPage();
        logger.info("Initialized ContextMenuPage for test");
    }
    
    @Test(description = "Test context menu right-click functionality")
    public void testContextMenuRightClick() {
        logger.info("Starting test: Context Menu Right Click");
        
        // Step 1: Navigate to context menu page
        contextMenuPage.navigateToContextMenuPage();
        logger.info("Step 1: Navigated to context menu page");
        
        // Verify context menu page is displayed
        Assert.assertTrue(contextMenuPage.isContextMenuPageDisplayed(), 
                         "Context menu page should be displayed");
        
        // Verify page heading
        String pageHeading = contextMenuPage.getPageHeading();
        Assert.assertEquals(pageHeading, "Context Menu", 
                           "Page heading should be 'Context Menu'");
        logger.info("Page heading verified: {}", pageHeading);
        
        // Step 2: Verify context menu area is displayed
        Assert.assertTrue(contextMenuPage.isContextMenuAreaDisplayed(), 
                         "Context menu area should be displayed");
        logger.info("Step 2: Context menu area verified");
        
        // Verify instruction text
        Assert.assertTrue(contextMenuPage.isInstructionTextCorrect(), 
                         "Instruction text should be correct");
        
        // Step 3: Right-click on context menu area
        contextMenuPage.rightClickOnContextMenuArea();
        logger.info("Step 3: Performed right-click on context menu area");
        
        // Step 4: Verify JavaScript alert appears
        Assert.assertTrue(contextMenuPage.isAlertPresent(), 
                         "JavaScript alert should appear after right-click");
        logger.info("Step 4: JavaScript alert appeared");
        
        // Step 5: Verify alert text is correct
        Assert.assertTrue(contextMenuPage.isAlertTextCorrect(), 
                         "Alert text should be correct");
        
        String alertText = contextMenuPage.getAlertText();
        logger.info("Step 5: Alert text verified: '{}'", alertText);
        
        // Step 6: Accept the alert
        contextMenuPage.acceptAlert();
        logger.info("Step 6: Alert accepted");
        
        // Step 7: Verify alert is dismissed
        Assert.assertFalse(contextMenuPage.isAlertPresent(), 
                          "Alert should be dismissed after accepting");
        logger.info("Step 7: Alert dismissed successfully");
        
        logger.info("Test completed successfully: Context Menu Right Click");
    }
    
    @Test(description = "Test JavaScript alert handling with accept")
    public void testAlertHandlingAccept() {
        logger.info("Starting test: Alert Handling - Accept");
        
        // Navigate to page
        contextMenuPage.navigateToContextMenuPage();
        
        // Test alert handling with accept
        boolean result = contextMenuPage.testAlertHandling(true);
        Assert.assertTrue(result, "Alert handling with accept should be successful");
        
        logger.info("Test completed successfully: Alert Handling - Accept");
    }
    
    @Test(description = "Test JavaScript alert handling with dismiss")
    public void testAlertHandlingDismiss() {
        logger.info("Starting test: Alert Handling - Dismiss");
        
        // Navigate to page
        contextMenuPage.navigateToContextMenuPage();
        
        // Test alert handling with dismiss
        boolean result = contextMenuPage.testAlertHandling(false);
        Assert.assertTrue(result, "Alert handling with dismiss should be successful");
        
        logger.info("Test completed successfully: Alert Handling - Dismiss");
    }
    
    @Test(description = "Test complete context menu sequence")
    public void testCompleteContextMenuSequence() {
        logger.info("Starting test: Complete Context Menu Sequence");
        
        // Navigate to context menu page
        contextMenuPage.navigateToContextMenuPage();
        
        // Verify initial page state
        Assert.assertTrue(contextMenuPage.isContextMenuPageDisplayed(), 
                         "Page should be loaded");
        Assert.assertTrue(contextMenuPage.isContextMenuAreaDisplayed(), 
                         "Context menu area should be visible");
        Assert.assertTrue(contextMenuPage.isInstructionTextCorrect(), 
                         "Instructions should be correct");
        
        // Perform complete test sequence
        boolean sequenceResult = contextMenuPage.performContextMenuTestSequence();
        Assert.assertTrue(sequenceResult, 
                         "Complete context menu sequence should be successful");
        
        // Verify page is still functional after sequence
        Assert.assertTrue(contextMenuPage.isContextMenuPageDisplayed(), 
                         "Page should remain functional after test sequence");
        
        logger.info("Test completed successfully: Complete Context Menu Sequence");
    }
    
    @Test(description = "Test multiple context menu interactions")
    public void testMultipleContextMenuInteractions() {
        logger.info("Starting test: Multiple Context Menu Interactions");
        
        contextMenuPage.navigateToContextMenuPage();
        
        // Perform multiple right-click interactions
        for (int i = 1; i <= 3; i++) {
            logger.info("Context menu interaction #{}", i);
            
            // Right-click and handle alert
            contextMenuPage.rightClickOnContextMenuArea();
            Assert.assertTrue(contextMenuPage.isAlertPresent(), 
                             String.format("Alert should appear in interaction %d", i));
            
            Assert.assertTrue(contextMenuPage.isAlertTextCorrect(), 
                             String.format("Alert text should be correct in interaction %d", i));
            
            contextMenuPage.acceptAlert();
            Assert.assertFalse(contextMenuPage.isAlertPresent(), 
                              String.format("Alert should be dismissed in interaction %d", i));
            
            logger.info("Context menu interaction #{} completed successfully", i);
            
            // Small wait between interactions
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        // Verify page is still functional
        Assert.assertTrue(contextMenuPage.isContextMenuAreaDisplayed(), 
                         "Context menu area should still be functional");
        
        logger.info("Test completed successfully: Multiple Context Menu Interactions");
    }
    
    @Test(description = "Test context menu area properties")
    public void testContextMenuAreaProperties() {
        logger.info("Starting test: Context Menu Area Properties");
        
        contextMenuPage.navigateToContextMenuPage();
        
        // Verify page elements
        Assert.assertTrue(contextMenuPage.isContextMenuPageDisplayed(), 
                         "Page should be displayed");
        
        String heading = contextMenuPage.getPageHeading();
        Assert.assertNotNull(heading, "Page heading should not be null");
        Assert.assertFalse(heading.trim().isEmpty(), "Page heading should not be empty");
        
        String instructionText = contextMenuPage.getInstructionText();
        Assert.assertNotNull(instructionText, "Instruction text should not be null");
        Assert.assertFalse(instructionText.trim().isEmpty(), "Instruction text should not be empty");
        Assert.assertTrue(instructionText.contains("Right-click"), 
                         "Instructions should mention right-click");
        
        // Verify context menu area is interactive
        Assert.assertTrue(contextMenuPage.isContextMenuAreaDisplayed(), 
                         "Context menu area should be displayed");
        
        // Test that area is clickable (this will trigger alert)
        contextMenuPage.rightClickOnContextMenuArea();
        Assert.assertTrue(contextMenuPage.isAlertPresent(), 
                         "Area should be interactive and trigger alert");
        
        // Clean up
        contextMenuPage.acceptAlert();
        
        logger.info("Test completed successfully: Context Menu Area Properties");
    }
    
    @Test(description = "Test alert text verification")
    public void testAlertTextVerification() {
        logger.info("Starting test: Alert Text Verification");
        
        contextMenuPage.navigateToContextMenuPage();
        
        // Trigger alert
        contextMenuPage.rightClickOnContextMenuArea();
        
        // Get and verify alert text
        String alertText = contextMenuPage.getAlertText();
        Assert.assertNotNull(alertText, "Alert text should not be null");
        Assert.assertFalse(alertText.trim().isEmpty(), "Alert text should not be empty");
        
        logger.info("Alert text received: '{}'", alertText);
        
        // Verify specific expected text
        Assert.assertEquals(alertText, "You selected a context menu", 
                           "Alert should contain expected message");
        
        // Verify alert text correctness method
        Assert.assertTrue(contextMenuPage.isAlertTextCorrect(), 
                         "Alert text should be marked as correct");
        
        // Clean up
        contextMenuPage.acceptAlert();
        
        logger.info("Test completed successfully: Alert Text Verification");
    }
}