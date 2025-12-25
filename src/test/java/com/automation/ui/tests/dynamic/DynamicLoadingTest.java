package com.automation.ui.tests.dynamic;

import com.automation.core.base.BaseTest;
import com.automation.ui.pages.dynamic.DynamicLoadingPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Dynamic Loading Test - Tests dynamic content loading functionality
 * Based on test scenario 3.1 from the test plan
 */
public class DynamicLoadingTest extends BaseTest {
    private DynamicLoadingPage dynamicLoadingPage;
    
    @BeforeMethod
    public void setUpDynamicLoadingTest() {
        dynamicLoadingPage = new DynamicLoadingPage();
        logger.info("Initialized DynamicLoadingPage for test");
    }
    
    @Test(description = "Test dynamic loading example 1 - Element hidden initially")
    public void testDynamicLoadingExample1() {
        logger.info("Starting test: Dynamic Loading Example 1");
        
        // Step 1: Navigate to dynamic loading example 1
        dynamicLoadingPage.navigateToExample1();
        logger.info("Step 1: Navigated to dynamic loading example 1");
        
        // Verify page is displayed correctly
        Assert.assertTrue(dynamicLoadingPage.isDynamicLoadingPageDisplayed(), 
                         "Dynamic loading page should be displayed");
        
        // Step 2: Verify start button is clickable
        Assert.assertTrue(dynamicLoadingPage.isStartButtonDisplayed(), 
                         "Start button should be displayed");
        Assert.assertTrue(dynamicLoadingPage.isStartButtonEnabled(), 
                         "Start button should be clickable");
        logger.info("Step 2: Verified start button is clickable");
        
        // Initial state: content should be hidden
        Assert.assertFalse(dynamicLoadingPage.isHiddenContentDisplayed(), 
                          "Content should be initially hidden");
        
        // Step 3: Click the Start button
        dynamicLoadingPage.clickStartButton();
        logger.info("Step 3: Clicked start button");
        
        // Step 4: Wait for loading indicator to appear
        Assert.assertTrue(dynamicLoadingPage.isLoadingIndicatorDisplayed(), 
                         "Loading indicator should appear after clicking Start");
        logger.info("Step 4: Loading indicator appeared");
        
        // Step 5: Wait for content to be revealed
        dynamicLoadingPage.waitForLoading();
        dynamicLoadingPage.waitForContentToBeRevealed();
        logger.info("Step 5: Loading completed");
        
        // Step 6: Verify different loading behaviors
        Assert.assertTrue(dynamicLoadingPage.isHiddenContentDisplayed(), 
                         "Hidden content should become visible after loading completes");
        Assert.assertFalse(dynamicLoadingPage.isLoadingIndicatorDisplayed(), 
                          "Loading indicator should disappear after loading");
        
        String contentText = dynamicLoadingPage.getHiddenContentText();
        Assert.assertTrue(contentText.contains("Hello World!"), 
                         "Revealed content should contain expected text");
        logger.info("Step 6: Verified content is revealed with text: {}", contentText);
        
        logger.info("Test completed successfully: Dynamic Loading Example 1");
    }
    
    @Test(description = "Test dynamic loading example 2 - Element not present initially")
    public void testDynamicLoadingExample2() {
        logger.info("Starting test: Dynamic Loading Example 2");
        
        // Step 1: Navigate to dynamic loading example 2
        dynamicLoadingPage.navigateToExample2();
        logger.info("Step 1: Navigated to dynamic loading example 2");
        
        // Verify page is displayed correctly
        Assert.assertTrue(dynamicLoadingPage.isDynamicLoadingPageDisplayed(), 
                         "Dynamic loading page should be displayed");
        
        // Perform the same test sequence for example 2
        Assert.assertTrue(dynamicLoadingPage.isStartButtonDisplayed(), 
                         "Start button should be displayed");
        Assert.assertTrue(dynamicLoadingPage.isStartButtonEnabled(), 
                         "Start button should be clickable");
        
        // Step 5: Navigate to dynamic_loading/2 and repeat test
        dynamicLoadingPage.clickStartButton();
        logger.info("Step 2: Clicked start button for example 2");
        
        // Wait for loading process
        dynamicLoadingPage.waitForLoading();
        dynamicLoadingPage.waitForContentToBeRevealed();
        
        // Step 6: Verify different loading behaviors
        Assert.assertTrue(dynamicLoadingPage.isHiddenContentDisplayed(), 
                         "Both dynamic loading examples should work correctly");
        
        String contentText = dynamicLoadingPage.getHiddenContentText();
        Assert.assertTrue(contentText.contains("Hello World!"), 
                         "Example 2 should also reveal content with expected text");
        logger.info("Step 3: Verified example 2 works correctly with text: {}", contentText);
        
        logger.info("Test completed successfully: Dynamic Loading Example 2");
    }
    
    @Test(description = "Test loading sequence correctness")
    public void testLoadingSequenceCorrectness() {
        logger.info("Starting test: Loading Sequence Correctness");
        
        // Test example 1
        dynamicLoadingPage.navigateToExample1();
        Assert.assertTrue(dynamicLoadingPage.isLoadingSequenceCorrect(), 
                         "Loading sequence should be correct for example 1");
        
        // Test example 2
        dynamicLoadingPage.navigateToExample2();
        Assert.assertTrue(dynamicLoadingPage.isLoadingSequenceCorrect(), 
                         "Loading sequence should be correct for example 2");
        
        logger.info("Test completed successfully: Loading Sequence Correctness");
    }
    
    @Test(description = "Test loading time is reasonable")
    public void testLoadingTimeIsReasonable() {
        logger.info("Starting test: Loading Time Is Reasonable");
        
        // Test loading time for example 1
        dynamicLoadingPage.navigateToExample1();
        long loadingTime1 = dynamicLoadingPage.measureLoadingTime();
        
        // Loading time should be reasonable (less than 10 seconds)
        Assert.assertTrue(loadingTime1 < 10000, 
                         String.format("Loading time should be reasonable (was %d ms)", loadingTime1));
        logger.info("Example 1 loading time: {} ms", loadingTime1);
        
        // Test loading time for example 2
        dynamicLoadingPage.navigateToExample2();
        long loadingTime2 = dynamicLoadingPage.measureLoadingTime();
        
        Assert.assertTrue(loadingTime2 < 10000, 
                         String.format("Loading time should be reasonable (was %d ms)", loadingTime2));
        logger.info("Example 2 loading time: {} ms", loadingTime2);
        
        logger.info("Test completed successfully: Loading Time Is Reasonable");
    }
    
    @Test(description = "Test multiple consecutive loading operations")
    public void testMultipleConsecutiveLoading() {
        logger.info("Starting test: Multiple Consecutive Loading");
        
        dynamicLoadingPage.navigateToExample1();
        
        // Perform multiple loading operations to test consistency
        for (int i = 1; i <= 3; i++) {
            logger.info("Performing loading operation #{}", i);
            
            // Refresh page to reset state
            dynamicLoadingPage.navigateToExample1();
            
            // Test loading sequence
            Assert.assertTrue(dynamicLoadingPage.isLoadingSequenceCorrect(), 
                             String.format("Loading operation #%d should work correctly", i));
            
            logger.info("Loading operation #{} completed successfully", i);
        }
        
        logger.info("Test completed successfully: Multiple Consecutive Loading");
    }
}