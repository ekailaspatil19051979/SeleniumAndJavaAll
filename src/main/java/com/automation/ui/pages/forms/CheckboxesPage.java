package com.automation.ui.pages.forms;

import com.automation.constants.FrameworkConstants;
import com.automation.core.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Checkboxes Page - Page Object for checkbox functionality
 * Handles checkbox state management and verification
 */
public class CheckboxesPage extends BasePage {
    
    // Page Elements
    @FindBy(css = "input[type='checkbox']")
    private List<WebElement> checkboxes;
    
    @FindBy(css = "h3")
    private WebElement pageHeading;
    
    @FindBy(css = "div.example")
    private WebElement checkboxContainer;
    
    // Page Navigation
    public CheckboxesPage navigateToCheckboxesPage() {
        navigateToUrl(config.getProperty("app.base.url") + FrameworkConstants.CHECKBOXES_URL);
        waitForPageLoad();
        logger.info("Navigated to checkboxes page");
        return this;
    }
    
    // Page Actions
    public CheckboxesPage clickCheckbox(int index) {
        if (index >= 0 && index < checkboxes.size()) {
            clickElement(checkboxes.get(index));
            logger.info("Clicked checkbox at index: {}", index);
        } else {
            logger.warn("Checkbox index {} is out of range. Total checkboxes: {}", 
                       index, checkboxes.size());
        }
        return this;
    }
    
    public CheckboxesPage toggleAllCheckboxes() {
        for (int i = 0; i < checkboxes.size(); i++) {
            clickCheckbox(i);
        }
        logger.info("Toggled all checkboxes");
        return this;
    }
    
    // Page Verifications
    public boolean isCheckboxesPageDisplayed() {
        return isElementDisplayed(pageHeading) && 
               isElementDisplayed(checkboxContainer) &&
               !checkboxes.isEmpty();
    }
    
    public boolean isCheckboxSelected(int index) {
        if (index >= 0 && index < checkboxes.size()) {
            return checkboxes.get(index).isSelected();
        }
        return false;
    }
    
    public boolean isCheckboxEnabled(int index) {
        if (index >= 0 && index < checkboxes.size()) {
            return checkboxes.get(index).isEnabled();
        }
        return false;
    }
    
    public int getCheckboxCount() {
        return checkboxes.size();
    }
    
    public String getPageHeading() {
        return getText(pageHeading);
    }
    
    // Validation Methods
    public boolean areCheckboxesTogglingCorrectly() {
        boolean[] initialStates = new boolean[checkboxes.size()];
        
        // Record initial states
        for (int i = 0; i < checkboxes.size(); i++) {
            initialStates[i] = isCheckboxSelected(i);
        }
        
        // Toggle all checkboxes
        toggleAllCheckboxes();
        
        // Verify states have changed
        for (int i = 0; i < checkboxes.size(); i++) {
            if (isCheckboxSelected(i) == initialStates[i]) {
                logger.error("Checkbox {} did not toggle correctly", i);
                return false;
            }
        }
        
        return true;
    }
}