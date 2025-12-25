package com.automation.ui.tests.navigation;

import com.automation.core.base.BaseTest;
import com.automation.ui.pages.navigation.FramesPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Frames Test - Tests frame handling, nested frames, and iframe interactions
 * Based on test scenario 4.3 from the test plan
 */
public class FramesTest extends BaseTest {
    private FramesPage framesPage;
    
    @BeforeMethod
    public void setUpFramesTest() {
        framesPage = new FramesPage();
        logger.info("Initialized FramesPage for test");
    }
    
    @Test(description = "Test basic frames page navigation")
    public void testFramesPageNavigation() {
        logger.info("Starting test: Frames Page Navigation");
        
        // Step 1: Navigate to frames main page
        framesPage.navigateToFramesPage();
        logger.info("Step 1: Navigated to frames main page");
        
        // Verify frames page is displayed
        Assert.assertTrue(framesPage.isFramesPageDisplayed(), 
                         "Frames main page should be displayed");
        
        // Step 2: Click nested frames link
        framesPage.clickNestedFramesLink();
        logger.info("Step 2: Clicked nested frames link");
        
        // Verify nested frames are loaded
        int frameCount = framesPage.getFrameCount();
        Assert.assertTrue(frameCount > 0, 
                         "Nested frames should be present");
        logger.info("Nested frames count: {}", frameCount);
        
        // Step 3: Navigate back to frames main page
        framesPage.navigateToFramesPage();
        logger.info("Step 3: Navigated back to frames main page");
        
        // Step 4: Click iframe link
        framesPage.clickIframeLink();
        logger.info("Step 4: Clicked iframe link");
        
        // Verify iframe is loaded
        int iframeCount = framesPage.getFrameCount();
        Assert.assertTrue(iframeCount > 0, 
                         "Iframe should be present");
        logger.info("Iframe count: {}", iframeCount);
        
        logger.info("Test completed successfully: Frames Page Navigation");
    }
    
    @Test(description = "Test nested frames navigation and content verification")
    public void testNestedFramesNavigation() {
        logger.info("Starting test: Nested Frames Navigation");
        
        // Step 1: Navigate to nested frames page
        framesPage.navigateToNestedFramesPage();
        logger.info("Step 1: Navigated to nested frames page");
        
        // Step 2: Test switching to top frame and left frame
        framesPage.switchToTopFrame();
        framesPage.switchToLeftFrame();
        logger.info("Step 2: Switched to top->left frame");
        
        // Verify left frame content
        String leftFrameText = framesPage.getCurrentFrameText();
        Assert.assertEquals(leftFrameText, "LEFT", 
                           "Left frame should contain 'LEFT' text");
        logger.info("Left frame text verified: '{}'", leftFrameText);
        
        // Step 3: Switch to middle frame (from parent)
        framesPage.switchToParentFrame();
        framesPage.switchToMiddleFrame();
        logger.info("Step 3: Switched to middle frame");
        
        // Verify middle frame content
        String middleFrameText = framesPage.getCurrentFrameText();
        Assert.assertEquals(middleFrameText, "MIDDLE", 
                           "Middle frame should contain 'MIDDLE' text");
        logger.info("Middle frame text verified: '{}'", middleFrameText);
        
        // Step 4: Switch to right frame (from parent)
        framesPage.switchToParentFrame();
        framesPage.switchToRightFrame();
        logger.info("Step 4: Switched to right frame");
        
        // Verify right frame content
        String rightFrameText = framesPage.getCurrentFrameText();
        Assert.assertEquals(rightFrameText, "RIGHT", 
                           "Right frame should contain 'RIGHT' text");
        logger.info("Right frame text verified: '{}'", rightFrameText);
        
        // Step 5: Switch to bottom frame (from default content)
        framesPage.switchToDefaultContent();
        framesPage.switchToBottomFrame();
        logger.info("Step 5: Switched to bottom frame");
        
        // Verify bottom frame content
        String bottomFrameText = framesPage.getCurrentFrameText();
        Assert.assertEquals(bottomFrameText, "BOTTOM", 
                           "Bottom frame should contain 'BOTTOM' text");
        logger.info("Bottom frame text verified: '{}'", bottomFrameText);
        
        // Step 6: Return to default content
        framesPage.switchToDefaultContent();
        logger.info("Step 6: Returned to default content");
        
        logger.info("Test completed successfully: Nested Frames Navigation");
    }
    
    @Test(description = "Test complete nested frames sequence")
    public void testCompleteNestedFramesSequence() {
        logger.info("Starting test: Complete Nested Frames Sequence");
        
        // Navigate to nested frames
        framesPage.navigateToNestedFramesPage();
        
        // Test complete navigation sequence
        boolean result = framesPage.testNestedFramesNavigation();
        Assert.assertTrue(result, 
                         "Complete nested frames navigation should be successful");
        
        logger.info("Test completed successfully: Complete Nested Frames Sequence");
    }
    
    @DataProvider(name = "iframeTestData")
    public Object[][] iframeTestData() {
        return new Object[][] {
            {"Hello, this is a test message!"},
            {"Testing iframe text editing functionality."},
            {"Special characters: @#$%^&*()"},
            {"Numbers: 123456789"},
            {"Mixed content: Test123 with special chars @#$"}
        };
    }
    
    @Test(dataProvider = "iframeTestData", description = "Test iframe text editing")
    public void testIframeEditing(String testText) {
        logger.info("Starting test: Iframe Editing with text: '{}'", testText);
        
        // Step 1: Navigate to iframe page
        framesPage.navigateToIframePage();
        logger.info("Step 1: Navigated to iframe page");
        
        // Step 2: Switch to iframe
        framesPage.switchToIframe();
        logger.info("Step 2: Switched to iframe");
        
        // Step 3: Get initial text
        String initialText = framesPage.getIframeEditorText();
        logger.info("Step 3: Initial iframe text: '{}'", initialText);
        
        // Step 4: Type new text
        framesPage.typeInIframeEditor(testText);
        logger.info("Step 4: Typed test text in iframe");
        
        // Step 5: Verify text was typed correctly
        String currentText = framesPage.getIframeEditorText();
        Assert.assertEquals(currentText, testText, 
                           "Iframe should contain the typed text");
        logger.info("Step 5: Iframe text verified: '{}'", currentText);
        
        // Step 6: Switch back to default content
        framesPage.switchToDefaultContent();
        logger.info("Step 6: Switched back to default content");
        
        logger.info("Test completed successfully: Iframe Editing with text: '{}'", testText);
    }
    
    @Test(description = "Test iframe editing using page method")
    public void testIframeEditingWithPageMethod() {
        logger.info("Starting test: Iframe Editing with Page Method");
        
        framesPage.navigateToIframePage();
        
        String testText = "This is a comprehensive iframe test!";
        boolean result = framesPage.testIframeEditing(testText);
        
        Assert.assertTrue(result, 
                         "Iframe editing should be successful using page method");
        
        logger.info("Test completed successfully: Iframe Editing with Page Method");
    }
    
    @Test(description = "Test frame switching consistency")
    public void testFrameSwitchingConsistency() {
        logger.info("Starting test: Frame Switching Consistency");
        
        framesPage.navigateToNestedFramesPage();
        
        // Test multiple switching operations to ensure consistency
        for (int i = 1; i <= 3; i++) {
            logger.info("Frame switching iteration #{}", i);
            
            // Test top->left frame
            framesPage.switchToTopFrame();
            framesPage.switchToLeftFrame();
            Assert.assertTrue(framesPage.verifyFrameText("LEFT"), 
                             String.format("Left frame should be correct in iteration %d", i));
            
            // Back to default and test bottom frame
            framesPage.switchToDefaultContent();
            framesPage.switchToBottomFrame();
            Assert.assertTrue(framesPage.verifyFrameText("BOTTOM"), 
                             String.format("Bottom frame should be correct in iteration %d", i));
            
            // Return to default
            framesPage.switchToDefaultContent();
            
            logger.info("Frame switching iteration #{} completed successfully", i);
        }
        
        logger.info("Test completed successfully: Frame Switching Consistency");
    }
    
    @Test(description = "Test frame content verification")
    public void testFrameContentVerification() {
        logger.info("Starting test: Frame Content Verification");
        
        framesPage.navigateToNestedFramesPage();
        
        // Test all frame contents systematically
        
        // Test left frame
        framesPage.switchToTopFrame();
        framesPage.switchToLeftFrame();
        String leftText = framesPage.getCurrentFrameText();
        Assert.assertEquals(leftText, "LEFT", "Left frame content should be 'LEFT'");
        Assert.assertTrue(framesPage.verifyFrameText("LEFT"), "Left frame verification should pass");
        
        // Test middle frame
        framesPage.switchToParentFrame();
        framesPage.switchToMiddleFrame();
        String middleText = framesPage.getCurrentFrameText();
        Assert.assertEquals(middleText, "MIDDLE", "Middle frame content should be 'MIDDLE'");
        Assert.assertTrue(framesPage.verifyFrameText("MIDDLE"), "Middle frame verification should pass");
        
        // Test right frame
        framesPage.switchToParentFrame();
        framesPage.switchToRightFrame();
        String rightText = framesPage.getCurrentFrameText();
        Assert.assertEquals(rightText, "RIGHT", "Right frame content should be 'RIGHT'");
        Assert.assertTrue(framesPage.verifyFrameText("RIGHT"), "Right frame verification should pass");
        
        // Test bottom frame
        framesPage.switchToDefaultContent();
        framesPage.switchToBottomFrame();
        String bottomText = framesPage.getCurrentFrameText();
        Assert.assertEquals(bottomText, "BOTTOM", "Bottom frame content should be 'BOTTOM'");
        Assert.assertTrue(framesPage.verifyFrameText("BOTTOM"), "Bottom frame verification should pass");
        
        // Return to default
        framesPage.switchToDefaultContent();
        
        logger.info("Test completed successfully: Frame Content Verification");
    }
    
    @Test(description = "Test frame count verification")
    public void testFrameCountVerification() {
        logger.info("Starting test: Frame Count Verification");
        
        // Test nested frames count
        framesPage.navigateToNestedFramesPage();
        int nestedFramesCount = framesPage.getFrameCount();
        Assert.assertTrue(nestedFramesCount >= 4, 
                         String.format("Nested frames page should have at least 4 frames, found: %d", 
                                     nestedFramesCount));
        logger.info("Nested frames count: {}", nestedFramesCount);
        
        // Test iframe count
        framesPage.navigateToIframePage();
        int iframeCount = framesPage.getFrameCount();
        Assert.assertTrue(iframeCount >= 1, 
                         String.format("Iframe page should have at least 1 iframe, found: %d", 
                                     iframeCount));
        logger.info("Iframe count: {}", iframeCount);
        
        // Test main frames page (should have minimal frames)
        framesPage.navigateToFramesPage();
        int mainPageFrames = framesPage.getFrameCount();
        logger.info("Main frames page frame count: {}", mainPageFrames);
        
        logger.info("Test completed successfully: Frame Count Verification");
    }
}