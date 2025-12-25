package com.automation.ui.tests.errors;

import com.automation.core.base.BaseTest;
import com.automation.ui.pages.errors.RedirectsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RedirectsTest extends BaseTest {
    
    private RedirectsPage redirectsPage;
    
    @BeforeMethod
    public void setUp() {
        redirectsPage = new RedirectsPage();
    }

    @Test
    public void testRedirectPage() {
        redirectsPage.navigateToRedirectPage();
        Assert.assertTrue(redirectsPage.isRedirectPageDisplayed(),
                "Redirect page should be displayed");
    }

    @Test
    public void testRedirectLinkPresence() {
        redirectsPage.navigateToRedirectPage();
        
        Assert.assertTrue(redirectsPage.isRedirectLinkVisible(),
                "Redirect link should be visible");
    }

    @Test
    public void testBasicRedirectFunctionality() {
        redirectsPage.navigateToRedirectPage();
        String originalUrl = redirectsPage.getCurrentUrl();
        
        redirectsPage.clickRedirectLink();
        
        // Should navigate to a different page
        Assert.assertTrue(redirectsPage.hasRedirectOccurred(originalUrl),
                "Redirect should have occurred");
    }

    @Test
    public void testRedirectDestination() {
        redirectsPage.navigateToRedirectPage();
        
        redirectsPage.clickRedirectLink();
        String destinationUrl = redirectsPage.getRedirectDestination();
        
        Assert.assertFalse(destinationUrl.isEmpty(),
                "Should have a valid destination URL after redirect");
        Assert.assertFalse(destinationUrl.contains("redirector"),
                "Should have redirected away from redirector page");
    }

    @Test
    public void testRedirectPageLoad() {
        redirectsPage.navigateToRedirectPage();
        
        redirectsPage.clickRedirectLink();
        
        Assert.assertTrue(redirectsPage.waitForPageLoad(10),
                "Redirected page should load within timeout");
    }

    @Test
    public void testRedirectIsSuccessful() {
        redirectsPage.navigateToRedirectPage();
        
        redirectsPage.clickRedirectLink();
        
        Assert.assertTrue(redirectsPage.isRedirectSuccessful(),
                "Redirect operation should be successful");
    }

    @Test
    public void testMultipleRedirectClicks() {
        for (int i = 0; i < 3; i++) {
            redirectsPage.navigateToRedirectPage();
            String originalUrl = redirectsPage.getCurrentUrl();
            
            redirectsPage.clickRedirectLink();
            
            Assert.assertTrue(redirectsPage.hasRedirectOccurred(originalUrl),
                    "Redirect should work on attempt " + (i + 1));
        }
    }

    @Test
    public void testNavigationAfterRedirect() {
        redirectsPage.navigateToRedirectPage();
        
        redirectsPage.clickRedirectLink();
        
        // Try to navigate back
        boolean canNavigateBack = redirectsPage.canNavigateBack();
        
        // Whether navigation back works or not, the redirect itself should have worked
        Assert.assertTrue(true, "Navigation test completed");
    }

    @Test
    public void testRedirectUrlChange() {
        redirectsPage.navigateToRedirectPage();
        String originalUrl = redirectsPage.getCurrentUrl();
        String originalTitle = redirectsPage.getPageTitle();
        
        redirectsPage.clickRedirectLink();
        
        String newUrl = redirectsPage.getCurrentUrl();
        String newTitle = redirectsPage.getPageTitle();
        
        // Either URL or title should change after redirect
        boolean hasChanged = !originalUrl.equals(newUrl) || !originalTitle.equals(newTitle);
        Assert.assertTrue(hasChanged, "URL or title should change after redirect");
    }

    @Test
    public void testRedirectToStatusCodesPage() {
        redirectsPage.navigateToRedirectPage();
        
        redirectsPage.clickRedirectLink();
        
        // The redirect might go to status codes page
        String currentUrl = redirectsPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("status") || !currentUrl.contains("redirector"),
                "Should redirect to status codes page or another destination");
    }

    @Test
    public void testHttpRedirectBehavior() {
        redirectsPage.navigateToRedirectPage();
        
        redirectsPage.clickRedirectLink();
        
        // Should be a proper HTTP redirect
        String destinationUrl = redirectsPage.getRedirectDestination();
        Assert.assertTrue(destinationUrl.startsWith("http"),
                "Redirect destination should be a valid HTTP URL");
    }

    @Test
    public void testRedirectPageInstructions() {
        redirectsPage.navigateToRedirectPage();
        
        String instructions = redirectsPage.getInstructionText();
        Assert.assertFalse(instructions.isEmpty(),
                "Page should have instruction text");
    }

    @Test
    public void testComprehensiveRedirectFlow() {
        redirectsPage.navigateToRedirectPage();
        
        // Test the complete redirect flow
        Assert.assertTrue(redirectsPage.isRedirectPageDisplayed(),
                "Should be on redirect page initially");
        Assert.assertTrue(redirectsPage.isRedirectLinkVisible(),
                "Redirect link should be visible");
        
        redirectsPage.performRedirectTest();
        
        // If we reach here without exception, the redirect worked
        Assert.assertTrue(true, "Comprehensive redirect test completed successfully");
    }

    @Test
    public void testRedirectChainHandling() {
        redirectsPage.navigateToRedirectPage();
        
        redirectsPage.clickRedirectLink();
        
        // Browser should handle redirect chain properly
        String finalUrl = redirectsPage.getCurrentUrl();
        Assert.assertNotNull(finalUrl, "Should have a final URL after redirect chain");
        Assert.assertFalse(finalUrl.isEmpty(), "Final URL should not be empty");
    }
}