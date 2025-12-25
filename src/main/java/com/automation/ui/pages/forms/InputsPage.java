package com.automation.ui.pages.forms;

import com.automation.constants.FrameworkConstants;
import com.automation.core.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Inputs Page - Page Object for numeric inputs functionality
 * Handles numeric input field testing and validation
 */
public class InputsPage extends BasePage {
    
    // Page Elements
    @FindBy(css = "input[type='number']")
    private WebElement numberInput;
    
    @FindBy(css = "h3")
    private WebElement pageHeading;
    
    @FindBy(css = "div.example p")
    private WebElement pageDescription;
    
    // Page Navigation
    public InputsPage navigateToInputsPage() {
        navigateToUrl(config.getProperty("app.base.url") + FrameworkConstants.INPUTS_URL);
        waitForPageLoad();
        logger.info("Navigated to inputs page");
        return this;
    }
    
    // Page Actions
    public InputsPage enterNumber(String number) {
        typeText(numberInput, number);
        logger.info("Entered number: {}", number);
        return this;
    }
    
    public InputsPage enterNumber(int number) {
        return enterNumber(String.valueOf(number));
    }
    
    public InputsPage clearInput() {
        numberInput.clear();
        logger.info("Cleared number input field");
        return this;
    }
    
    // Page Verifications
    public boolean isInputsPageDisplayed() {
        return isElementDisplayed(pageHeading) && 
               isElementDisplayed(numberInput);
    }
    
    public boolean isNumberInputDisplayed() {
        return isElementDisplayed(numberInput);
    }
    
    public boolean isNumberInputEnabled() {
        return isElementEnabled(numberInput);
    }
    
    public String getInputValue() {
        return numberInput.getAttribute("value");
    }
    
    public String getInputType() {
        return numberInput.getAttribute("type");
    }
    
    public String getPageHeading() {
        return getText(pageHeading);
    }
    
    public String getPageDescription() {
        return getText(pageDescription);
    }
    
    // Validation Methods
    public boolean isNumericInputAccepted(String input) {
        clearInput();
        enterNumber(input);
        String actualValue = getInputValue();
        return !actualValue.isEmpty() && actualValue.equals(input);
    }
    
    public boolean isNonNumericInputRejected(String input) {
        clearInput();
        enterNumber(input);
        String actualValue = getInputValue();
        // Non-numeric input should be rejected or filtered
        return actualValue.isEmpty() || !actualValue.equals(input);
    }
}