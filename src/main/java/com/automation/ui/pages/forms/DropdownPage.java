package com.automation.ui.pages.forms;

import com.automation.constants.FrameworkConstants;
import com.automation.core.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Dropdown Page - Page Object for dropdown functionality
 * Handles dropdown selection and option validation
 */
public class DropdownPage extends BasePage {
    
    // Page Elements
    @FindBy(id = "dropdown")
    private WebElement dropdownElement;
    
    @FindBy(css = "h3")
    private WebElement pageHeading;
    
    // Page Navigation
    public DropdownPage navigateToDropdownPage() {
        navigateToUrl(config.getProperty("app.base.url") + FrameworkConstants.DROPDOWN_URL);
        waitForPageLoad();
        logger.info("Navigated to dropdown page");
        return this;
    }
    
    // Page Actions
    public DropdownPage selectByVisibleText(String text) {
        waitActions.waitForElementToBeVisible(dropdownElement);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(text);
        logger.info("Selected dropdown option by text: {}", text);
        return this;
    }
    
    public DropdownPage selectByValue(String value) {
        waitActions.waitForElementToBeVisible(dropdownElement);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(value);
        logger.info("Selected dropdown option by value: {}", value);
        return this;
    }
    
    public DropdownPage selectByIndex(int index) {
        waitActions.waitForElementToBeVisible(dropdownElement);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(index);
        logger.info("Selected dropdown option by index: {}", index);
        return this;
    }
    
    // Page Verifications
    public boolean isDropdownPageDisplayed() {
        return isElementDisplayed(pageHeading) && 
               isElementDisplayed(dropdownElement);
    }
    
    public boolean isDropdownDisplayed() {
        return isElementDisplayed(dropdownElement);
    }
    
    public boolean isDropdownEnabled() {
        return isElementEnabled(dropdownElement);
    }
    
    public String getSelectedOptionText() {
        Select dropdown = new Select(dropdownElement);
        return dropdown.getFirstSelectedOption().getText();
    }
    
    public String getSelectedOptionValue() {
        Select dropdown = new Select(dropdownElement);
        return dropdown.getFirstSelectedOption().getAttribute("value");
    }
    
    public List<String> getAllOptionTexts() {
        Select dropdown = new Select(dropdownElement);
        return dropdown.getOptions().stream()
                .map(WebElement::getText)
                .toList();
    }
    
    public List<String> getAllOptionValues() {
        Select dropdown = new Select(dropdownElement);
        return dropdown.getOptions().stream()
                .map(option -> option.getAttribute("value"))
                .toList();
    }
    
    public int getOptionCount() {
        Select dropdown = new Select(dropdownElement);
        return dropdown.getOptions().size();
    }
    
    public String getPageHeading() {
        return getText(pageHeading);
    }
    
    // Validation Methods
    public boolean isOptionAvailable(String optionText) {
        return getAllOptionTexts().contains(optionText);
    }
    
    public boolean isOptionSelectable(String optionText) {
        try {
            selectByVisibleText(optionText);
            return getSelectedOptionText().equals(optionText);
        } catch (Exception e) {
            logger.error("Failed to select option: {}", optionText, e);
            return false;
        }
    }
    
    public boolean areAllOptionsSelectable() {
        List<String> options = getAllOptionTexts();
        String originalSelection = getSelectedOptionText();
        
        for (String option : options) {
            if (option.equals("Please select an option")) {
                continue; // Skip placeholder option
            }
            
            if (!isOptionSelectable(option)) {
                logger.error("Option '{}' is not selectable", option);
                return false;
            }
        }
        
        // Restore original selection
        try {
            selectByVisibleText(originalSelection);
        } catch (Exception e) {
            logger.warn("Could not restore original selection: {}", originalSelection);
        }
        
        return true;
    }
}