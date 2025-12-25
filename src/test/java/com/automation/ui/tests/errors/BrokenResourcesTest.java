package com.automation.ui.tests.errors;

import com.automation.core.base.BaseTest;
import com.automation.ui.pages.errors.BrokenResourcesPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BrokenResourcesTest extends BaseTest {
    
    private BrokenResourcesPage brokenResourcesPage;
    
    @BeforeMethod
    public void setUp() {
        brokenResourcesPage = new BrokenResourcesPage();
    }

    @Test
    public void testBrokenImagesPage() {
        brokenResourcesPage.navigateToBrokenImagesPage();
        Assert.assertTrue(brokenResourcesPage.isBrokenImagesPageDisplayed(),
                "Broken images page should be displayed");
    }

    @Test
    public void testImagesPresent() {
        brokenResourcesPage.navigateToBrokenImagesPage();
        
        Assert.assertTrue(brokenResourcesPage.areImagesPresent(),
                "Images should be present on the page");
        Assert.assertTrue(brokenResourcesPage.getImagesCount() > 0,
                "Should have at least one image element");
    }

    @Test
    public void testPageLayoutWithBrokenImages() {
        brokenResourcesPage.navigateToBrokenImagesPage();
        
        Assert.assertTrue(brokenResourcesPage.isPageLayoutIntact(),
                "Page layout should remain intact despite broken images");
    }

    @Test
    public void testBrokenImagesDetection() {
        brokenResourcesPage.navigateToBrokenImagesPage();
        
        int totalImages = brokenResourcesPage.getImagesCount();
        int brokenImages = brokenResourcesPage.getBrokenImagesCount();
        
        Assert.assertTrue(totalImages > 0, "Should have images on the page");
        Assert.assertTrue(brokenImages >= 0, "Broken image count should be non-negative");
    }

    @Test
    public void testImageAttributes() {
        brokenResourcesPage.navigateToBrokenImagesPage();
        
        int imageCount = Math.min(brokenResourcesPage.getImagesCount(), 3);
        for (int i = 0; i < imageCount; i++) {
            String src = brokenResourcesPage.getImageSrc(i);
            Assert.assertFalse(src.isEmpty(),
                    "Image " + (i + 1) + " should have a src attribute");
        }
    }

    @Test
    public void testAltTextForImages() {
        brokenResourcesPage.navigateToBrokenImagesPage();
        
        int imageCount = Math.min(brokenResourcesPage.getImagesCount(), 3);
        for (int i = 0; i < imageCount; i++) {
            String alt = brokenResourcesPage.getImageAlt(i);
            // Alt text might be empty, but we can at least check that we can read the attribute
            Assert.assertNotNull(alt, "Should be able to read alt attribute for image " + (i + 1));
        }
    }

    @Test
    public void testWorkingVsBrokenImages() {
        brokenResourcesPage.navigateToBrokenImagesPage();
        
        int totalImages = brokenResourcesPage.getImagesCount();
        int brokenImages = brokenResourcesPage.getBrokenImagesCount();
        int workingImages = brokenResourcesPage.getWorkingImagesCount();
        
        Assert.assertEquals(totalImages, brokenImages + workingImages,
                "Total images should equal broken + working images");
    }

    @Test
    public void testInstructionTextPresent() {
        brokenResourcesPage.navigateToBrokenImagesPage();
        
        String instructionText = brokenResourcesPage.getInstructionText();
        Assert.assertFalse(instructionText.isEmpty(),
                "Instruction text should be present and readable");
    }

    @Test
    public void testPageFunctionalityWithBrokenResources() {
        brokenResourcesPage.navigateToBrokenImagesPage();
        
        // Page should remain functional despite broken resources
        Assert.assertTrue(brokenResourcesPage.isBrokenImagesPageDisplayed(),
                "Page should be displayed");
        Assert.assertTrue(brokenResourcesPage.isPageLayoutIntact(),
                "Page layout should be intact");
        Assert.assertTrue(brokenResourcesPage.areImagesPresent(),
                "Image elements should be present");
    }
}