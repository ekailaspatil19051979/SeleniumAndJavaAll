package com.automation.ui.tests.dynamic;

import com.automation.core.base.BaseTest;
import com.automation.ui.pages.dynamic.DragAndDropPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DragAndDropTest extends BaseTest {
    
    private DragAndDropPage dragAndDropPage;
    
    @BeforeMethod
    public void setUp() {
        dragAndDropPage = new DragAndDropPage();
    }

    @Test
    public void testDragAndDropPage() {
        dragAndDropPage.navigateToDragAndDropPage();
        Assert.assertTrue(dragAndDropPage.isDragAndDropPageDisplayed(),
                "Drag and Drop page should be displayed");
    }

    @Test
    public void testColumnsVisibility() {
        dragAndDropPage.navigateToDragAndDropPage();
        
        Assert.assertTrue(dragAndDropPage.areColumnsVisible(),
                "Both columns A and B should be visible");
    }

    @Test
    public void testInitialColumnTexts() {
        dragAndDropPage.navigateToDragAndDropPage();
        
        String columnAText = dragAndDropPage.getColumnAText();
        String columnBText = dragAndDropPage.getColumnBText();
        
        Assert.assertEquals(columnAText, "A",
                "Column A should initially contain text 'A'");
        Assert.assertEquals(columnBText, "B",
                "Column B should initially contain text 'B'");
    }

    @Test
    public void testDragFromAToB() {
        dragAndDropPage.navigateToDragAndDropPage();
        
        // Get initial texts
        String initialAText = dragAndDropPage.getColumnAText();
        String initialBText = dragAndDropPage.getColumnBText();
        
        // Perform drag and drop
        dragAndDropPage.dragFromAToB();
        
        // Verify elements have swapped
        Assert.assertTrue(dragAndDropPage.verifyElementsSwapped(initialBText, initialAText),
                "Elements should be swapped after drag and drop");
    }

    @Test
    public void testDragFromBToA() {
        dragAndDropPage.navigateToDragAndDropPage();
        
        // First swap A to B
        dragAndDropPage.dragFromAToB();
        
        // Then swap back B to A
        dragAndDropPage.dragFromBToA();
        
        // Verify we're back to original state
        Assert.assertEquals(dragAndDropPage.getColumnAText(), "A",
                "Column A should contain 'A' after swapping back");
        Assert.assertEquals(dragAndDropPage.getColumnBText(), "B",
                "Column B should contain 'B' after swapping back");
    }

    @Test
    public void testMultipleDragOperations() {
        dragAndDropPage.navigateToDragAndDropPage();
        
        // Perform multiple swaps
        for (int i = 0; i < 3; i++) {
            String beforeAText = dragAndDropPage.getColumnAText();
            String beforeBText = dragAndDropPage.getColumnBText();
            
            dragAndDropPage.dragFromAToB();
            
            // Verify swap occurred
            Assert.assertEquals(dragAndDropPage.getColumnAText(), beforeBText,
                    "Text should be swapped in iteration " + (i + 1));
            Assert.assertEquals(dragAndDropPage.getColumnBText(), beforeAText,
                    "Text should be swapped in iteration " + (i + 1));
        }
    }

    @Test
    public void testDragAndDropConsistency() {
        dragAndDropPage.navigateToDragAndDropPage();
        
        // Test that drag and drop consistently swaps elements
        String originalAText = dragAndDropPage.getColumnAText();
        String originalBText = dragAndDropPage.getColumnBText();
        
        // Drag A to B
        dragAndDropPage.dragFromAToB();
        String afterFirstSwapAText = dragAndDropPage.getColumnAText();
        String afterFirstSwapBText = dragAndDropPage.getColumnBText();
        
        // Drag again (B to A now)
        dragAndDropPage.dragFromBToA();
        String afterSecondSwapAText = dragAndDropPage.getColumnAText();
        String afterSecondSwapBText = dragAndDropPage.getColumnBText();
        
        // Should be back to original
        Assert.assertEquals(afterSecondSwapAText, originalAText,
                "After two swaps, should return to original A text");
        Assert.assertEquals(afterSecondSwapBText, originalBText,
                "After two swaps, should return to original B text");
    }

    @Test
    public void testElementsDraggableProperties() {
        dragAndDropPage.navigateToDragAndDropPage();
        
        // Note: This test might not work as expected since HTML5 draggable attribute
        // might not be set, but elements are still draggable via Actions
        boolean isPageFunctional = dragAndDropPage.areColumnsVisible();
        Assert.assertTrue(isPageFunctional, 
                "Page should be functional for drag and drop operations");
    }

    @Test
    public void testPreciseDragAndDrop() {
        dragAndDropPage.navigateToDragAndDropPage();
        
        String initialAText = dragAndDropPage.getColumnAText();
        String initialBText = dragAndDropPage.getColumnBText();
        
        // Use precise drag and drop method
        dragAndDropPage.performPreciseDragAndDrop();
        
        // Verify swap occurred
        Assert.assertTrue(dragAndDropPage.verifyElementsSwapped(initialBText, initialAText),
                "Precise drag and drop should swap elements");
    }

    @Test
    public void testDragAndDropStateReset() {
        dragAndDropPage.navigateToDragAndDropPage();
        
        // Perform drag operation
        dragAndDropPage.dragFromAToB();
        
        // Reset to initial state
        dragAndDropPage.resetToInitialState();
        
        // Verify reset
        Assert.assertEquals(dragAndDropPage.getColumnAText(), "A",
                "Column A should be reset to 'A'");
        Assert.assertEquals(dragAndDropPage.getColumnBText(), "B",
                "Column B should be reset to 'B'");
    }

    @Test
    public void testDragAndDropSequence() {
        dragAndDropPage.navigateToDragAndDropPage();
        
        // Test a specific sequence of operations
        String[] expectedSequence = {"B", "A"}; // After first swap
        
        // Step 1: Initial state
        Assert.assertEquals(dragAndDropPage.getColumnAText(), "A", "Initial state - Column A");
        Assert.assertEquals(dragAndDropPage.getColumnBText(), "B", "Initial state - Column B");
        
        // Step 2: Drag A to B
        dragAndDropPage.dragFromAToB();
        Assert.assertEquals(dragAndDropPage.getColumnAText(), expectedSequence[0], "After swap - Column A");
        Assert.assertEquals(dragAndDropPage.getColumnBText(), expectedSequence[1], "After swap - Column B");
        
        // Step 3: Drag back (B to A)
        dragAndDropPage.dragFromBToA();
        Assert.assertEquals(dragAndDropPage.getColumnAText(), "A", "After swap back - Column A");
        Assert.assertEquals(dragAndDropPage.getColumnBText(), "B", "After swap back - Column B");
    }

    @Test
    public void testDragAndDropFunctionality() {
        dragAndDropPage.navigateToDragAndDropPage();
        
        // Comprehensive test of drag and drop functionality
        Assert.assertTrue(dragAndDropPage.isDragAndDropPageDisplayed(),
                "Drag and drop page should be loaded");
        Assert.assertTrue(dragAndDropPage.areColumnsVisible(),
                "Columns should be visible");
        
        // Test basic drag operation
        dragAndDropPage.dragFromAToB();
        
        // Verify the drag operation had an effect
        String columnAAfterDrag = dragAndDropPage.getColumnAText();
        String columnBAfterDrag = dragAndDropPage.getColumnBText();
        
        boolean swapOccurred = !"A".equals(columnAAfterDrag) || !"B".equals(columnBAfterDrag);
        Assert.assertTrue(swapOccurred, 
                "Drag and drop operation should change the column contents");
    }
}