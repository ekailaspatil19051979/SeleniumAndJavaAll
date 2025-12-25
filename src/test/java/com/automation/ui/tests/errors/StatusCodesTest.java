package com.automation.ui.tests.errors;

import com.automation.core.base.BaseTest;
import com.automation.ui.pages.errors.StatusCodesPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StatusCodesTest extends BaseTest {
    
    private StatusCodesPage statusCodesPage;
    
    @BeforeMethod
    public void setUp() {
        statusCodesPage = new StatusCodesPage();
    }

    @Test
    public void testStatusCodesPage() {
        statusCodesPage.navigateToStatusCodesPage();
        Assert.assertTrue(statusCodesPage.isStatusCodesPageDisplayed(),
                "Status codes page should be displayed");
    }

    @Test
    public void testStatusLinksPresence() {
        statusCodesPage.navigateToStatusCodesPage();
        
        Assert.assertTrue(statusCodesPage.areStatusLinksVisible(),
                "Status code links should be visible");
        Assert.assertTrue(statusCodesPage.getStatusLinksCount() >= 4,
                "Should have at least 4 status code links");
    }

    @Test
    public void testStatus200Link() {
        statusCodesPage.navigateToStatusCodesPage();
        
        statusCodesPage.clickStatus200Link();
        
        // Should navigate successfully (200 OK)
        Assert.assertTrue(statusCodesPage.isPageAccessible(),
                "Should be able to access page after clicking 200 link");
    }

    @Test
    public void testStatus404Link() {
        statusCodesPage.navigateToStatusCodesPage();
        
        statusCodesPage.clickStatus404Link();
        
        // Should show 404 error page
        String currentUrl = statusCodesPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("404"),
                "URL should contain '404' after clicking 404 link");
    }

    @Test
    public void testStatus500Link() {
        statusCodesPage.navigateToStatusCodesPage();
        
        statusCodesPage.clickStatus500Link();
        
        // Should show 500 error page
        String currentUrl = statusCodesPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("500"),
                "URL should contain '500' after clicking 500 link");
    }

    @Test(dataProvider = "statusCodes")
    public void testStatusCodeLinks(String statusCode, String expectedInUrl) {
        statusCodesPage.navigateToStatusCodesPage();
        
        statusCodesPage.clickStatusLinkAndVerify(statusCode);
        
        String currentUrl = statusCodesPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(expectedInUrl),
                "URL should contain '" + expectedInUrl + "' after clicking " + statusCode + " link");
    }

    @Test
    public void testNavigationBetweenStatusPages() {
        statusCodesPage.navigateToStatusCodesPage();
        
        // Test navigation between different status pages
        statusCodesPage.clickStatus200Link();
        String url200 = statusCodesPage.getCurrentUrl();
        
        statusCodesPage.navigateBack();
        statusCodesPage.clickStatus404Link();
        String url404 = statusCodesPage.getCurrentUrl();
        
        Assert.assertNotEquals(url200, url404,
                "Different status code pages should have different URLs");
    }

    @Test
    public void testBrowserHandlingOfStatusCodes() {
        statusCodesPage.navigateToStatusCodesPage();
        
        // Test that browser can handle different status codes
        String[] statusCodes = {"200", "404", "500"};
        
        for (String code : statusCodes) {
            statusCodesPage.navigateToStatusCodesPage();
            statusCodesPage.clickStatusLinkAndVerify(code);
            
            // Browser should handle the response (no exceptions thrown)
            Assert.assertNotNull(statusCodesPage.getCurrentUrl(),
                    "Should be able to get current URL after " + code + " response");
        }
    }

    @Test
    public void testStatusCodePagesAccessibility() {
        statusCodesPage.navigateToStatusCodesPage();
        
        // Test that we can access different status code pages
        statusCodesPage.clickStatus200Link();
        Assert.assertFalse(statusCodesPage.getCurrentUrl().isEmpty(),
                "Should have valid URL after 200 response");
        
        statusCodesPage.navigateToStatusCodesPage();
        statusCodesPage.clickStatus404Link();
        Assert.assertFalse(statusCodesPage.getCurrentUrl().isEmpty(),
                "Should have valid URL after 404 response");
    }

    @Test
    public void testPageFunctionalityAfterStatusCodeNavigation() {
        statusCodesPage.navigateToStatusCodesPage();
        
        // Navigate to different status pages and back
        statusCodesPage.clickStatus200Link();
        statusCodesPage.navigateBack();
        
        Assert.assertTrue(statusCodesPage.isStatusCodesPageDisplayed(),
                "Should be back on status codes page after navigation");
        Assert.assertTrue(statusCodesPage.areStatusLinksVisible(),
                "Status links should still be visible after navigation");
    }

    @DataProvider
    public Object[][] statusCodes() {
        return new Object[][]{
                {"200", "200"},
                {"301", "301"},
                {"404", "404"},
                {"500", "500"}
        };
    }
}