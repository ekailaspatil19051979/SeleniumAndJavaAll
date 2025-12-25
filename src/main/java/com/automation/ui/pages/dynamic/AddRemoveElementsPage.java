package com.automation.ui.pages.dynamic;

import com.automation.core.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AddRemoveElementsPage extends BasePage {
    
    @FindBy(xpath = "//button[contains(text(),'Add Element')]")
    private WebElement addElementButton;
    
    @FindBy(css = ".added-manually")
    private List<WebElement> deleteButtons;
    
    @FindBy(css = "h3")
    private WebElement pageTitle;
    
    @FindBy(id = "elements")
    private WebElement elementsContainer;

    public AddRemoveElementsPage() {
        super();
    }

    public void navigateToAddRemoveElementsPage() {
        navigateToUrl("/add_remove_elements/");
    }

    public boolean isAddRemoveElementsPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(pageTitle))
                    .getText().contains("Add/Remove Elements");
        } catch (Exception e) {
            return false;
        }
    }

    public void clickAddElement() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(addElementButton)).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Add Element button: " + e.getMessage());
        }
    }

    public void addElements(int count) {
        for (int i = 0; i < count; i++) {
            clickAddElement();
        }
    }

    public int getDeleteButtonsCount() {
        try {
            // Wait a moment for elements to be added to DOM
            Thread.sleep(500);
            return deleteButtons.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void clickDeleteButton(int index) {
        try {
            if (index >= 0 && index < deleteButtons.size()) {
                wait.until(ExpectedConditions.elementToBeClickable(deleteButtons.get(index))).click();
            } else {
                throw new RuntimeException("Delete button index out of bounds: " + index);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to click delete button: " + e.getMessage());
        }
    }

    public void clickFirstDeleteButton() {
        try {
            if (!deleteButtons.isEmpty()) {
                wait.until(ExpectedConditions.elementToBeClickable(deleteButtons.get(0))).click();
            } else {
                throw new RuntimeException("No delete buttons available");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to click first delete button: " + e.getMessage());
        }
    }

    public void clickLastDeleteButton() {
        try {
            if (!deleteButtons.isEmpty()) {
                int lastIndex = deleteButtons.size() - 1;
                wait.until(ExpectedConditions.elementToBeClickable(deleteButtons.get(lastIndex))).click();
            } else {
                throw new RuntimeException("No delete buttons available");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to click last delete button: " + e.getMessage());
        }
    }

    public void removeAllElements() {
        while (getDeleteButtonsCount() > 0) {
            clickFirstDeleteButton();
        }
    }

    public boolean isAddElementButtonDisplayed() {
        try {
            return addElementButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areDeleteButtonsVisible() {
        try {
            return !deleteButtons.isEmpty() && deleteButtons.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getDeleteButtonText(int index) {
        try {
            if (index >= 0 && index < deleteButtons.size()) {
                return deleteButtons.get(index).getText();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isElementsContainerPresent() {
        try {
            return elementsContainer.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void performAddRemoveSequence(int addCount, int removeCount) {
        addElements(addCount);
        for (int i = 0; i < removeCount && getDeleteButtonsCount() > 0; i++) {
            clickFirstDeleteButton();
        }
    }
}