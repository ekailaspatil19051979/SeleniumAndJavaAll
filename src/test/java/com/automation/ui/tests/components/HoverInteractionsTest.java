package com.automation.ui.tests.components;

import com.automation.core.base.BaseTest;
import com.automation.ui.pages.components.HoverInteractionsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HoverInteractionsTest extends BaseTest {
    
    private HoverInteractionsPage hoverPage;
    
    @BeforeMethod
    public void setUp() {
        hoverPage = new HoverInteractionsPage();
    }

    @Test
    public void testHoverPage() {
        hoverPage.navigateToHoverPage();
        Assert.assertTrue(hoverPage.isHoverPageDisplayed(),
                "Hover page should be displayed");
    }

    @Test
    public void testUserAvatarsPresence() {
        hoverPage.navigateToHoverPage();
        
        Assert.assertTrue(hoverPage.areAvatarsVisible(),
                "User avatars should be visible");
        Assert.assertTrue(hoverPage.getUserAvatarsCount() >= 3,
                "Should have at least 3 user avatars");
    }

    @Test
    public void testHoverOverFirstAvatar() {
        hoverPage.navigateToHoverPage();
        
        hoverPage.hoverOverAvatar(0);
        Assert.assertTrue(hoverPage.isUserInfoVisible(0),
                "User info should be visible after hovering over first avatar");
    }

    @Test
    public void testHoverOverAllAvatars() {
        hoverPage.navigateToHoverPage();
        
        int avatarCount = hoverPage.getUserAvatarsCount();
        for (int i = 0; i < avatarCount && i < 3; i++) {
            hoverPage.hoverOverAvatar(i);
            Assert.assertTrue(hoverPage.isUserInfoVisible(i),
                    "User info should be visible after hovering over avatar " + (i + 1));
        }
    }

    @Test
    public void testUserNameDisplay() {
        hoverPage.navigateToHoverPage();
        
        hoverPage.hoverOverAvatar(0);
        String userName = hoverPage.getUserName(0);
        Assert.assertFalse(userName.isEmpty(),
                "User name should be displayed on hover");
    }

    @Test
    public void testViewProfileLinks() {
        hoverPage.navigateToHoverPage();
        
        int avatarCount = Math.min(hoverPage.getUserAvatarsCount(), 3);
        for (int i = 0; i < avatarCount; i++) {
            hoverPage.hoverOverAvatar(i);
            Assert.assertTrue(hoverPage.isViewProfileLinkVisible(i),
                    "View profile link should be visible for avatar " + (i + 1));
        }
    }

    @Test
    public void testHoverInteractionSequence() {
        hoverPage.navigateToHoverPage();
        
        // Test sequence: hover over each avatar and verify info appears
        int avatarCount = Math.min(hoverPage.getUserAvatarsCount(), 3);
        
        for (int i = 0; i < avatarCount; i++) {
            // Hover over avatar
            hoverPage.hoverOverAvatar(i);
            
            // Verify user info is visible
            Assert.assertTrue(hoverPage.isUserInfoVisible(i),
                    "User info should appear when hovering over avatar " + (i + 1));
            
            // Verify user name is not empty
            String userName = hoverPage.getUserName(i);
            Assert.assertFalse(userName.isEmpty(),
                    "User name should be displayed for avatar " + (i + 1));
        }
    }

    @Test
    public void testConsistentHoverBehavior() {
        hoverPage.navigateToHoverPage();
        
        // Test that hovering multiple times over same avatar works consistently
        for (int attempt = 0; attempt < 3; attempt++) {
            hoverPage.hoverOverAvatar(0);
            Assert.assertTrue(hoverPage.isUserInfoVisible(0),
                    "User info should be consistently visible on attempt " + (attempt + 1));
        }
    }

    @Test
    public void testAllAvatarsHaveUserInfo() {
        hoverPage.navigateToHoverPage();
        
        int avatarCount = Math.min(hoverPage.getUserAvatarsCount(), 3);
        
        for (int i = 0; i < avatarCount; i++) {
            hoverPage.hoverOverAvatar(i);
            
            Assert.assertTrue(hoverPage.isUserInfoVisible(i),
                    "Avatar " + (i + 1) + " should show user info on hover");
            Assert.assertTrue(hoverPage.isViewProfileLinkVisible(i),
                    "Avatar " + (i + 1) + " should show view profile link on hover");
        }
    }
}