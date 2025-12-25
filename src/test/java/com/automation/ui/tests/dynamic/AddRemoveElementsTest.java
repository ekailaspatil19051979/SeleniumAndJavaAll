package com.automation.ui.tests.dynamic;

import com.automation.core.base.BaseTest;
import com.automation.ui.pages.dynamic.AddRemoveElementsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddRemoveElementsTest extends BaseTest {
    
    private AddRemoveElementsPage addRemoveElementsPage;
    
    @BeforeMethod
    public void setUp() {
        addRemoveElementsPage = new AddRemoveElementsPage();
    }

    @Test
    public void testAddRemoveElementsPage() {
        addRemoveElementsPage.navigateToAddRemoveElementsPage();
        Assert.assertTrue(addRemoveElementsPage.isAddRemoveElementsPageDisplayed(),
                "Add/Remove Elements page should be displayed");
    }

    @Test
    public void testAddElementButton() {
        addRemoveElementsPage.navigateToAddRemoveElementsPage();
        
        Assert.assertTrue(addRemoveElementsPage.isAddElementButtonDisplayed(),
                "Add Element button should be visible");
        
        // Initially no delete buttons should be present
        Assert.assertEquals(addRemoveElementsPage.getDeleteButtonsCount(), 0,
                "Initially there should be no delete buttons");
    }

    @Test
    public void testAddSingleElement() {
        addRemoveElementsPage.navigateToAddRemoveElementsPage();
        
        // Click Add Element button
        addRemoveElementsPage.clickAddElement();
        
        // Verify delete button is added
        Assert.assertEquals(addRemoveElementsPage.getDeleteButtonsCount(), 1,
                "One delete button should be added");
        
        Assert.assertTrue(addRemoveElementsPage.areDeleteButtonsVisible(),
                "Delete button should be visible");
    }

    @Test
    public void testAddMultipleElements() {
        addRemoveElementsPage.navigateToAddRemoveElementsPage();
        
        int elementsToAdd = 5;
        addRemoveElementsPage.addElements(elementsToAdd);
        
        Assert.assertEquals(addRemoveElementsPage.getDeleteButtonsCount(), elementsToAdd,
                "Should have " + elementsToAdd + " delete buttons");
    }

    @Test
    public void testRemoveSingleElement() {
        addRemoveElementsPage.navigateToAddRemoveElementsPage();
        
        // Add one element first
        addRemoveElementsPage.clickAddElement();
        Assert.assertEquals(addRemoveElementsPage.getDeleteButtonsCount(), 1,
                "Should have one delete button");
        
        // Remove the element
        addRemoveElementsPage.clickFirstDeleteButton();
        Assert.assertEquals(addRemoveElementsPage.getDeleteButtonsCount(), 0,
                "Should have no delete buttons after removal");
    }

    @Test
    public void testAddRemoveMultipleElements() {
        addRemoveElementsPage.navigateToAddRemoveElementsPage();
        
        // Add 3 elements
        addRemoveElementsPage.addElements(3);
        Assert.assertEquals(addRemoveElementsPage.getDeleteButtonsCount(), 3,
                "Should have 3 delete buttons");
        
        // Remove 2 elements
        addRemoveElementsPage.clickFirstDeleteButton();
        addRemoveElementsPage.clickFirstDeleteButton();
        
        Assert.assertEquals(addRemoveElementsPage.getDeleteButtonsCount(), 1,
                "Should have 1 delete button remaining");
    }

    @Test
    public void testRemoveAllElements() {
        addRemoveElementsPage.navigateToAddRemoveElementsPage();
        
        // Add several elements
        addRemoveElementsPage.addElements(4);
        Assert.assertEquals(addRemoveElementsPage.getDeleteButtonsCount(), 4,
                "Should have 4 delete buttons");
        
        // Remove all elements
        addRemoveElementsPage.removeAllElements();
        Assert.assertEquals(addRemoveElementsPage.getDeleteButtonsCount(), 0,
                "Should have no delete buttons after removing all");
    }

    @Test
    public void testDeleteButtonText() {
        addRemoveElementsPage.navigateToAddRemoveElementsPage();
        
        addRemoveElementsPage.clickAddElement();
        String buttonText = addRemoveElementsPage.getDeleteButtonText(0);
        
        Assert.assertFalse(buttonText.isEmpty(),
                "Delete button should have text");
        Assert.assertTrue(buttonText.toLowerCase().contains("delete"),
                "Delete button text should contain 'delete'");
    }

    @Test
    public void testSequentialAddRemoveOperations() {
        addRemoveElementsPage.navigateToAddRemoveElementsPage();
        
        // Sequence: Add 2, Remove 1, Add 3, Remove 2
        addRemoveElementsPage.addElements(2);
        Assert.assertEquals(addRemoveElementsPage.getDeleteButtonsCount(), 2,
                "Should have 2 buttons after adding 2");
        
        addRemoveElementsPage.clickFirstDeleteButton();
        Assert.assertEquals(addRemoveElementsPage.getDeleteButtonsCount(), 1,
                "Should have 1 button after removing 1");
        
        addRemoveElementsPage.addElements(3);
        Assert.assertEquals(addRemoveElementsPage.getDeleteButtonsCount(), 4,
                "Should have 4 buttons after adding 3 more");
        
        addRemoveElementsPage.clickFirstDeleteButton();
        addRemoveElementsPage.clickFirstDeleteButton();
        Assert.assertEquals(addRemoveElementsPage.getDeleteButtonsCount(), 2,
                "Should have 2 buttons after removing 2 more");
    }

    @Test
    public void testAddRemoveStressTest() {
        addRemoveElementsPage.navigateToAddRemoveElementsPage();
        
        // Stress test with many operations
        for (int i = 0; i < 3; i++) {
            addRemoveElementsPage.addElements(10);
            Assert.assertEquals(addRemoveElementsPage.getDeleteButtonsCount(), 10,
                    "Should have 10 buttons in iteration " + (i + 1));
            
            addRemoveElementsPage.removeAllElements();
            Assert.assertEquals(addRemoveElementsPage.getDeleteButtonsCount(), 0,
                    "Should have 0 buttons after removal in iteration " + (i + 1));
        }
    }

    @Test
    public void testElementsContainerPresence() {
        addRemoveElementsPage.navigateToAddRemoveElementsPage();
        
        Assert.assertTrue(addRemoveElementsPage.isElementsContainerPresent(),
                "Elements container should be present");
        
        addRemoveElementsPage.clickAddElement();
        Assert.assertTrue(addRemoveElementsPage.isElementsContainerPresent(),
                "Elements container should remain present after adding elements");
    }

    @Test
    public void testAddRemoveSequenceMethod() {
        addRemoveElementsPage.navigateToAddRemoveElementsPage();
        
        // Test the combined add/remove sequence method
        addRemoveElementsPage.performAddRemoveSequence(5, 3);
        
        Assert.assertEquals(addRemoveElementsPage.getDeleteButtonsCount(), 2,
                "Should have 2 buttons remaining after adding 5 and removing 3");
    }
}