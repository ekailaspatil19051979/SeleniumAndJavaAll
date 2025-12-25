package com.automation.ui.pages.dynamic;

import com.automation.core.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DragAndDropPage extends BasePage {
    
    @FindBy(id = "column-a")
    private WebElement columnA;
    
    @FindBy(id = "column-b")
    private WebElement columnB;
    
    @FindBy(css = "h3")
    private WebElement pageTitle;
    
    @FindBy(css = "#column-a header")
    private WebElement columnAHeader;
    
    @FindBy(css = "#column-b header")
    private WebElement columnBHeader;

    public DragAndDropPage() {
        super();
    }

    public void navigateToDragAndDropPage() {
        navigateToUrl("/drag_and_drop");
    }

    public boolean isDragAndDropPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(pageTitle))
                    .getText().contains("Drag and Drop");
        } catch (Exception e) {
            return false;
        }
    }

    public void dragFromAToB() {
        try {
            Actions actions = new Actions(driver);
            WebElement sourceElement = wait.until(ExpectedConditions.visibilityOf(columnA));
            WebElement targetElement = wait.until(ExpectedConditions.visibilityOf(columnB));
            
            actions.dragAndDrop(sourceElement, targetElement).perform();
        } catch (Exception e) {
            throw new RuntimeException("Failed to drag from A to B: " + e.getMessage());
        }
    }

    public void dragFromBToA() {
        try {
            Actions actions = new Actions(driver);
            WebElement sourceElement = wait.until(ExpectedConditions.visibilityOf(columnB));
            WebElement targetElement = wait.until(ExpectedConditions.visibilityOf(columnA));
            
            actions.dragAndDrop(sourceElement, targetElement).perform();
        } catch (Exception e) {
            throw new RuntimeException("Failed to drag from B to A: " + e.getMessage());
        }
    }

    public String getColumnAText() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(columnAHeader)).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getColumnBText() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(columnBHeader)).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean areColumnsVisible() {
        try {
            return columnA.isDisplayed() && columnB.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isColumnADraggable() {
        try {
            String draggable = columnA.getAttribute("draggable");
            return "true".equalsIgnoreCase(draggable);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isColumnBDraggable() {
        try {
            String draggable = columnB.getAttribute("draggable");
            return "true".equalsIgnoreCase(draggable);
        } catch (Exception e) {
            return false;
        }
    }

    public void performCustomDragAndDrop(WebElement source, WebElement target) {
        try {
            Actions actions = new Actions(driver);
            actions.clickAndHold(source)
                   .moveToElement(target)
                   .release()
                   .perform();
        } catch (Exception e) {
            throw new RuntimeException("Failed to perform custom drag and drop: " + e.getMessage());
        }
    }

    public void performPreciseDragAndDrop() {
        try {
            Actions actions = new Actions(driver);
            WebElement sourceElement = wait.until(ExpectedConditions.visibilityOf(columnA));
            WebElement targetElement = wait.until(ExpectedConditions.visibilityOf(columnB));
            
            // More precise drag and drop with offset
            actions.clickAndHold(sourceElement)
                   .moveToElement(targetElement, 0, 0)
                   .release()
                   .perform();
        } catch (Exception e) {
            throw new RuntimeException("Failed to perform precise drag and drop: " + e.getMessage());
        }
    }

    public boolean verifyElementsSwapped(String expectedAText, String expectedBText) {
        try {
            Thread.sleep(1000); // Wait for animation/DOM update
            String actualAText = getColumnAText();
            String actualBText = getColumnBText();
            
            return expectedAText.equals(actualAText) && expectedBText.equals(actualBText);
        } catch (Exception e) {
            return false;
        }
    }

    public void dragAndDropWithOffset(int xOffset, int yOffset) {
        try {
            Actions actions = new Actions(driver);
            WebElement sourceElement = wait.until(ExpectedConditions.visibilityOf(columnA));
            
            actions.clickAndHold(sourceElement)
                   .moveByOffset(xOffset, yOffset)
                   .release()
                   .perform();
        } catch (Exception e) {
            throw new RuntimeException("Failed to drag and drop with offset: " + e.getMessage());
        }
    }

    public void resetToInitialState() {
        // If elements are swapped, swap them back
        String columnAText = getColumnAText();
        String columnBText = getColumnBText();
        
        if ("B".equals(columnAText) && "A".equals(columnBText)) {
            dragFromAToB(); // This will swap them back
        }
    }
}