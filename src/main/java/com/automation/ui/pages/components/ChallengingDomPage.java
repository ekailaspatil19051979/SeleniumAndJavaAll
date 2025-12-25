package com.automation.ui.pages.components;

import com.automation.core.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ChallengingDomPage extends BasePage {
    
    @FindBy(css = ".button")
    private List<WebElement> buttons;
    
    @FindBy(css = ".button:nth-child(1)")
    private WebElement redButton;
    
    @FindBy(css = ".button:nth-child(2)")
    private WebElement blueButton;
    
    @FindBy(css = ".button:nth-child(3)")
    private WebElement greenButton;
    
    @FindBy(css = "h3")
    private WebElement pageTitle;
    
    @FindBy(css = "table")
    private WebElement dataTable;
    
    @FindBy(css = "table tbody tr")
    private List<WebElement> tableRows;
    
    @FindBy(css = "table thead th")
    private List<WebElement> tableHeaders;
    
    @FindBy(css = "canvas")
    private WebElement canvasElement;

    public ChallengingDomPage() {
        super();
    }

    public void navigateToChallengingDomPage() {
        navigateToUrl("/challenging_dom");
    }

    public boolean isChallengingDomPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(pageTitle))
                    .getText().contains("Challenging DOM");
        } catch (Exception e) {
            return false;
        }
    }

    public int getButtonsCount() {
        return buttons.size();
    }

    public void clickRedButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(redButton)).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click red button: " + e.getMessage());
        }
    }

    public void clickBlueButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(blueButton)).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click blue button: " + e.getMessage());
        }
    }

    public void clickGreenButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(greenButton)).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click green button: " + e.getMessage());
        }
    }

    public void clickButton(int index) {
        try {
            if (index >= 0 && index < buttons.size()) {
                wait.until(ExpectedConditions.elementToBeClickable(buttons.get(index))).click();
            } else {
                throw new RuntimeException("Button index out of bounds: " + index);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to click button at index " + index + ": " + e.getMessage());
        }
    }

    public boolean areButtonsVisible() {
        try {
            return buttons.size() >= 3 && buttons.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTableVisible() {
        try {
            return dataTable.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getTableRowsCount() {
        return tableRows.size();
    }

    public int getTableHeadersCount() {
        return tableHeaders.size();
    }

    public String getTableHeaderText(int index) {
        try {
            if (index >= 0 && index < tableHeaders.size()) {
                return tableHeaders.get(index).getText();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    public String getTableCellText(int row, int col) {
        try {
            if (row >= 0 && row < tableRows.size()) {
                List<WebElement> cells = tableRows.get(row).findElements(org.openqa.selenium.By.tagName("td"));
                if (col >= 0 && col < cells.size()) {
                    return cells.get(col).getText();
                }
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isCanvasVisible() {
        try {
            return canvasElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCanvasId() {
        try {
            return canvasElement.getAttribute("id");
        } catch (Exception e) {
            return "";
        }
    }

    public String getButtonText(int index) {
        try {
            if (index >= 0 && index < buttons.size()) {
                return buttons.get(index).getText();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    public String getButtonClass(int index) {
        try {
            if (index >= 0 && index < buttons.size()) {
                return buttons.get(index).getAttribute("class");
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    public boolean hasTableData() {
        return getTableRowsCount() > 0 && getTableHeadersCount() > 0;
    }

    public void clickAllButtons() {
        for (int i = 0; i < Math.min(buttons.size(), 3); i++) {
            clickButton(i);
        }
    }

    public boolean verifyTableStructure() {
        return isTableVisible() && hasTableData() && 
               getTableHeadersCount() > 0 && getTableRowsCount() > 0;
    }
}