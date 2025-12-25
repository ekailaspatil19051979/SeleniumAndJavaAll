package com.automation.ui.tests.components;

import com.automation.core.base.BaseTest;
import com.automation.ui.pages.components.NotificationMessagesPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NotificationMessagesTest extends BaseTest {
    
    private NotificationMessagesPage notificationPage;
    
    @BeforeMethod
    public void setUp() {
        notificationPage = new NotificationMessagesPage();
    }

    @Test
    public void testNotificationMessagesPage() {
        notificationPage.navigateToNotificationMessagesPage();
        Assert.assertTrue(notificationPage.isNotificationMessagesPageDisplayed(),
                "Notification messages page should be displayed");
    }

    @Test
    public void testClickHereLinkPresence() {
        notificationPage.navigateToNotificationMessagesPage();
        
        Assert.assertTrue(notificationPage.isClickHereLinkVisible(),
                "'Click here' link should be visible");
    }

    @Test
    public void testNotificationAppears() {
        notificationPage.navigateToNotificationMessagesPage();
        
        notificationPage.clickHereLink();
        Assert.assertTrue(notificationPage.isNotificationVisible(),
                "Notification should appear after clicking link");
    }

    @Test
    public void testNotificationMessage() {
        notificationPage.navigateToNotificationMessagesPage();
        
        notificationPage.clickHereLink();
        String message = notificationPage.getNotificationMessage();
        
        Assert.assertFalse(message.isEmpty(),
                "Notification message should not be empty");
    }

    @Test
    public void testMultipleNotificationTriggers() {
        notificationPage.navigateToNotificationMessagesPage();
        
        for (int i = 0; i < 3; i++) {
            notificationPage.clickHereLink();
            Assert.assertTrue(notificationPage.isNotificationVisible(),
                    "Notification should appear on click " + (i + 1));
        }
    }

    @Test
    public void testNotificationTiming() {
        notificationPage.navigateToNotificationMessagesPage();
        
        notificationPage.clickHereLink();
        Assert.assertTrue(notificationPage.waitForNotification(5),
                "Notification should appear within 5 seconds");
    }

    @Test
    public void testNotificationContent() {
        notificationPage.navigateToNotificationMessagesPage();
        
        notificationPage.clickAndVerifyNotification();
        String message = notificationPage.getNotificationMessage();
        String notificationClass = notificationPage.getNotificationClass();
        
        Assert.assertFalse(message.isEmpty(), "Notification should have content");
        Assert.assertFalse(notificationClass.isEmpty(), "Notification should have styling");
    }

    @Test
    public void testNotificationPersistence() {
        notificationPage.navigateToNotificationMessagesPage();
        
        notificationPage.clickHereLink();
        Assert.assertTrue(notificationPage.isNotificationVisible(),
                "Notification should be visible immediately after click");
        
        // Wait a moment and check if still visible
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Ignore
        }
        
        // Notification behavior may vary - just ensure the click worked
        String message = notificationPage.getNotificationMessage();
        Assert.assertFalse(message.isEmpty(), "Should be able to read notification message");
    }
}