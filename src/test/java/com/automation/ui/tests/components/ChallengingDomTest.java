package com.automation.ui.tests.components;

import com.automation.core.base.BaseTest;
import com.automation.ui.pages.components.ChallengingDomPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChallengingDomTest extends BaseTest {
    
    private ChallengingDomPage challengingDomPage;
    
    @BeforeMethod
    public void setUp() {
        challengingDomPage = new ChallengingDomPage();
    }

    @Test
    public void testChallengingDomPage() {
        challengingDomPage.navigateToChallengingDomPage();
        Assert.assertTrue(challengingDomPage.isChallengingDomPageDisplayed(),
                "Challenging DOM page should be displayed");
    }

    @Test
    public void testButtonsPresence() {
        challengingDomPage.navigateToChallengingDomPage();
        
        Assert.assertTrue(challengingDomPage.areButtonsVisible(),
                "Buttons should be visible");
        Assert.assertEquals(challengingDomPage.getButtonsCount(), 3,
                "Should have exactly 3 buttons");
    }

    @Test
    public void testButtonClicks() {
        challengingDomPage.navigateToChallengingDomPage();
        
        // Test clicking each button
        challengingDomPage.clickRedButton();
        challengingDomPage.clickBlueButton();
        challengingDomPage.clickGreenButton();
        
        // Page should remain functional after clicks
        Assert.assertTrue(challengingDomPage.isChallengingDomPageDisplayed(),
                "Page should remain functional after button clicks");
    }

    @Test
    public void testTablePresence() {
        challengingDomPage.navigateToChallengingDomPage();
        
        Assert.assertTrue(challengingDomPage.isTableVisible(),
                "Data table should be visible");
        Assert.assertTrue(challengingDomPage.hasTableData(),
                "Table should contain data");
    }

    @Test
    public void testTableStructure() {
        challengingDomPage.navigateToChallengingDomPage();
        
        Assert.assertTrue(challengingDomPage.verifyTableStructure(),
                "Table should have proper structure");
        
        int headerCount = challengingDomPage.getTableHeadersCount();
        int rowCount = challengingDomPage.getTableRowsCount();
        
        Assert.assertTrue(headerCount > 0, "Table should have headers");
        Assert.assertTrue(rowCount > 0, "Table should have data rows");
    }

    @Test
    public void testTableHeaders() {
        challengingDomPage.navigateToChallengingDomPage();
        
        int headerCount = challengingDomPage.getTableHeadersCount();
        for (int i = 0; i < headerCount; i++) {
            String headerText = challengingDomPage.getTableHeaderText(i);
            Assert.assertFalse(headerText.isEmpty(),
                    "Table header " + (i + 1) + " should have text");
        }
    }

    @Test
    public void testTableData() {
        challengingDomPage.navigateToChallengingDomPage();
        
        int rowCount = Math.min(challengingDomPage.getTableRowsCount(), 3);
        for (int row = 0; row < rowCount; row++) {
            String cellText = challengingDomPage.getTableCellText(row, 0);
            Assert.assertFalse(cellText.isEmpty(),
                    "Table cell at row " + row + " should have content");
        }
    }

    @Test
    public void testCanvasElement() {
        challengingDomPage.navigateToChallengingDomPage();
        
        Assert.assertTrue(challengingDomPage.isCanvasVisible(),
                "Canvas element should be visible");
        
        String canvasId = challengingDomPage.getCanvasId();
        Assert.assertFalse(canvasId.isEmpty(),
                "Canvas should have an ID");
    }

    @Test
    public void testButtonProperties() {
        challengingDomPage.navigateToChallengingDomPage();
        
        for (int i = 0; i < 3; i++) {
            String buttonClass = challengingDomPage.getButtonClass(i);
            Assert.assertTrue(buttonClass.contains("button"),
                    "Button " + (i + 1) + " should have button class");
        }
    }

    @Test
    public void testInteractiveElements() {
        challengingDomPage.navigateToChallengingDomPage();
        
        // Test all major interactive elements are present
        Assert.assertTrue(challengingDomPage.areButtonsVisible(), "Buttons should be present");
        Assert.assertTrue(challengingDomPage.isTableVisible(), "Table should be present");
        Assert.assertTrue(challengingDomPage.isCanvasVisible(), "Canvas should be present");
    }

    @Test
    public void testButtonClickSequence() {
        challengingDomPage.navigateToChallengingDomPage();
        
        // Click all buttons in sequence
        challengingDomPage.clickAllButtons();
        
        // Verify page is still functional
        Assert.assertTrue(challengingDomPage.isChallengingDomPageDisplayed(),
                "Page should be functional after clicking all buttons");
        Assert.assertTrue(challengingDomPage.areButtonsVisible(),
                "Buttons should still be visible");
    }

    @Test
    public void testPageElements() {
        challengingDomPage.navigateToChallengingDomPage();
        
        // Comprehensive test of all page elements
        Assert.assertTrue(challengingDomPage.isChallengingDomPageDisplayed(),
                "Page should be displayed");
        Assert.assertEquals(challengingDomPage.getButtonsCount(), 3,
                "Should have 3 buttons");
        Assert.assertTrue(challengingDomPage.isTableVisible(),
                "Table should be visible");
        Assert.assertTrue(challengingDomPage.isCanvasVisible(),
                "Canvas should be visible");
        Assert.assertTrue(challengingDomPage.getTableRowsCount() > 0,
                "Table should have rows");
        Assert.assertTrue(challengingDomPage.getTableHeadersCount() > 0,
                "Table should have headers");
    }
}