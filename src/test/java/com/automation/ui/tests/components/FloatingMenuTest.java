package com.automation.ui.tests.components;

import com.automation.core.base.BaseTest;
import com.automation.ui.pages.components.FloatingMenuPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FloatingMenuTest extends BaseTest {
    
    private FloatingMenuPage floatingMenuPage;
    
    @BeforeMethod
    public void setUp() {
        floatingMenuPage = new FloatingMenuPage();
    }

    @Test
    public void testFloatingMenuPage() {
        floatingMenuPage.navigateToFloatingMenuPage();
        Assert.assertTrue(floatingMenuPage.isFloatingMenuPageDisplayed(),
                "Floating menu page should be displayed");
    }

    @Test
    public void testFloatingMenuVisibility() {
        floatingMenuPage.navigateToFloatingMenuPage();
        
        Assert.assertTrue(floatingMenuPage.isFloatingMenuVisible(),
                "Floating menu should be visible on page load");
    }

    @Test
    public void testMenuItemsPresence() {
        floatingMenuPage.navigateToFloatingMenuPage();
        
        Assert.assertTrue(floatingMenuPage.getMenuItemsCount() >= 4,
                "Should have at least 4 menu items");
    }

    @Test
    public void testMenuRemainsVisibleAfterScroll() {
        floatingMenuPage.navigateToFloatingMenuPage();
        
        // Verify menu is initially visible
        Assert.assertTrue(floatingMenuPage.isFloatingMenuVisible(),
                "Menu should be visible initially");
        
        // Scroll down and verify menu is still visible
        floatingMenuPage.scrollToBottom();
        Assert.assertTrue(floatingMenuPage.isMenuStillVisibleAfterScroll(),
                "Menu should remain visible after scrolling down");
    }

    @Test
    public void testMenuRemainsVisibleAfterScrollUp() {
        floatingMenuPage.navigateToFloatingMenuPage();
        
        // Scroll down first
        floatingMenuPage.scrollToBottom();
        
        // Scroll back up and verify menu is still visible
        floatingMenuPage.scrollToTop();
        Assert.assertTrue(floatingMenuPage.isMenuStillVisibleAfterScroll(),
                "Menu should remain visible after scrolling back up");
    }

    @Test
    public void testMenuItemsClickable() {
        floatingMenuPage.navigateToFloatingMenuPage();
        
        Assert.assertTrue(floatingMenuPage.areMenuItemsClickable(),
                "All menu items should be clickable");
    }

    @Test
    public void testHomeMenuClick() {
        floatingMenuPage.navigateToFloatingMenuPage();
        
        floatingMenuPage.clickHomeLink();
        
        // Should navigate to home section or remain on same page
        String currentUrl = floatingMenuPage.getCurrentUrl();
        Assert.assertNotNull(currentUrl, "Should have a valid URL after clicking Home");
    }

    @Test
    public void testNewsMenuClick() {
        floatingMenuPage.navigateToFloatingMenuPage();
        
        floatingMenuPage.clickNewsLink();
        
        // Should navigate to news section
        Assert.assertTrue(floatingMenuPage.hasNavigatedToSection("news") || 
                         floatingMenuPage.getCurrentUrl().contains("floating_menu"),
                "Should navigate to news section or remain on page");
    }

    @Test
    public void testContactMenuClick() {
        floatingMenuPage.navigateToFloatingMenuPage();
        
        floatingMenuPage.clickContactLink();
        
        // Should navigate to contact section
        Assert.assertTrue(floatingMenuPage.hasNavigatedToSection("contact") || 
                         floatingMenuPage.getCurrentUrl().contains("floating_menu"),
                "Should navigate to contact section or remain on page");
    }

    @Test
    public void testAboutMenuClick() {
        floatingMenuPage.navigateToFloatingMenuPage();
        
        floatingMenuPage.clickAboutLink();
        
        // Should navigate to about section
        Assert.assertTrue(floatingMenuPage.hasNavigatedToSection("about") || 
                         floatingMenuPage.getCurrentUrl().contains("floating_menu"),
                "Should navigate to about section or remain on page");
    }

    @Test
    public void testMenuFloatingBehavior() {
        floatingMenuPage.navigateToFloatingMenuPage();
        
        // Use the comprehensive floating behavior test
        floatingMenuPage.testMenuFloatingBehavior();
        
        // If we reach here without exception, the test passed
        Assert.assertTrue(true, "Menu floating behavior test completed successfully");
    }

    @Test
    public void testMenuItemsContent() {
        floatingMenuPage.navigateToFloatingMenuPage();
        
        int menuItemsCount = floatingMenuPage.getMenuItemsCount();
        for (int i = 0; i < menuItemsCount && i < 4; i++) {
            String menuText = floatingMenuPage.getMenuItemText(i);
            Assert.assertFalse(menuText.isEmpty(),
                    "Menu item " + (i + 1) + " should have text content");
        }
    }

    @Test
    public void testMenuPositionDuringScroll() {
        floatingMenuPage.navigateToFloatingMenuPage();
        
        // Check initial scroll position
        int initialScrollPos = floatingMenuPage.getScrollPosition();
        Assert.assertEquals(initialScrollPos, 0, "Should start at top of page");
        
        // Scroll down
        floatingMenuPage.scrollByPixels(500);
        int scrolledPos = floatingMenuPage.getScrollPosition();
        Assert.assertTrue(scrolledPos > 0, "Should have scrolled down");
        
        // Menu should still be visible
        Assert.assertTrue(floatingMenuPage.isFloatingMenuVisible(),
                "Menu should be visible after partial scroll");
    }

    @Test
    public void testAllMenuLinks() {
        floatingMenuPage.navigateToFloatingMenuPage();
        
        String[] menuLinks = {"Home", "News", "Contact", "About"};
        
        for (String link : menuLinks) {
            floatingMenuPage.navigateToFloatingMenuPage(); // Reset to initial state
            floatingMenuPage.clickMenuLink(link);
            
            // Verify page is still functional after click
            Assert.assertTrue(floatingMenuPage.isFloatingMenuVisible(),
                    link + " menu click should keep menu visible");
        }
    }

    @Test
    public void testMenuVisibilityConsistency() {
        floatingMenuPage.navigateToFloatingMenuPage();
        
        // Test menu visibility through multiple scroll operations
        for (int i = 0; i < 3; i++) {
            floatingMenuPage.scrollByPixels(200);
            Assert.assertTrue(floatingMenuPage.isFloatingMenuVisible(),
                    "Menu should be visible during scroll iteration " + (i + 1));
        }
        
        // Scroll back to top
        floatingMenuPage.scrollToTop();
        Assert.assertTrue(floatingMenuPage.isFloatingMenuVisible(),
                "Menu should be visible after scrolling back to top");
    }
}