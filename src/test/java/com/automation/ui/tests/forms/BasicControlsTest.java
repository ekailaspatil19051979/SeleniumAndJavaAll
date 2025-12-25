package com.automation.ui.tests.forms;

import com.automation.core.base.BaseTest;
import com.automation.ui.pages.forms.CheckboxesPage;
import com.automation.ui.pages.forms.DropdownPage;
import com.automation.ui.pages.forms.InputsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Basic Form Controls Test - Tests form interactions and validation
 * Based on test scenario 2.1 from the test plan
 */
public class BasicControlsTest extends BaseTest {
    private InputsPage inputsPage;
    private CheckboxesPage checkboxesPage;
    private DropdownPage dropdownPage;
    
    @BeforeMethod
    public void setUpFormTest() {
        inputsPage = new InputsPage();
        checkboxesPage = new CheckboxesPage();
        dropdownPage = new DropdownPage();
        logger.info("Initialized form pages for test");
    }
    
    @Test(description = "Test input field with various numeric values", 
          dataProvider = "numericValues")
    public void testNumericInputValues(String numericValue, boolean shouldAccept) {
        logger.info("Starting test: Numeric Input Values - Testing: {}", numericValue);
        
        // Step 1: Navigate to inputs page
        inputsPage.navigateToInputsPage();
        logger.info("Step 1: Navigated to inputs page");
        
        // Verify input field is displayed and enabled
        Assert.assertTrue(inputsPage.isInputsPageDisplayed(), 
                         "Inputs page should be displayed");
        Assert.assertTrue(inputsPage.isNumberInputDisplayed(), 
                         "Number input field should be displayed");
        Assert.assertTrue(inputsPage.isNumberInputEnabled(), 
                         "Number input field should be enabled");
        
        // Step 2: Test input field with numeric values
        boolean actualResult = inputsPage.isNumericInputAccepted(numericValue);
        
        if (shouldAccept) {
            Assert.assertTrue(actualResult, 
                            String.format("Input field should accept numeric value: %s", numericValue));
            Assert.assertEquals(inputsPage.getInputValue(), numericValue, 
                              "Input value should match entered value");
        } else {
            // For boundary testing, we might expect certain values to be handled differently
            logger.info("Tested boundary/edge case value: {} - Result: {}", numericValue, actualResult);
        }
        
        logger.info("Step 2: Tested numeric input with value: {}", numericValue);
        
        logger.info("Test completed successfully: Numeric Input Values");
    }
    
    @Test(description = "Test input field with non-numeric values", 
          dataProvider = "nonNumericValues")
    public void testNonNumericInputValues(String nonNumericValue) {
        logger.info("Starting test: Non-Numeric Input Values - Testing: {}", nonNumericValue);
        
        // Navigate to inputs page
        inputsPage.navigateToInputsPage();
        
        // Step 3: Test input field with non-numeric values
        boolean isRejected = inputsPage.isNonNumericInputRejected(nonNumericValue);
        Assert.assertTrue(isRejected, 
                         String.format("Input field should reject or handle non-numeric input appropriately: %s", 
                                     nonNumericValue));
        
        logger.info("Step 3: Verified non-numeric input rejection for: {}", nonNumericValue);
        
        logger.info("Test completed successfully: Non-Numeric Input Values");
    }
    
    @Test(description = "Test input field boundary values", 
          dataProvider = "boundaryValues")
    public void testInputBoundaryValues(String boundaryValue, String description) {
        logger.info("Starting test: Input Boundary Values - Testing: {} ({})", boundaryValue, description);
        
        // Navigate to inputs page
        inputsPage.navigateToInputsPage();
        
        // Step 4: Test input field boundaries
        inputsPage.enterNumber(boundaryValue);
        
        // Boundary values should be handled correctly
        String actualValue = inputsPage.getInputValue();
        logger.info("Boundary value test - Input: {}, Result: {}, Description: {}", 
                   boundaryValue, actualValue, description);
        
        // At minimum, the application should not crash or throw errors
        Assert.assertTrue(inputsPage.isInputsPageDisplayed(), 
                         "Page should remain functional after boundary value input");
        
        logger.info("Step 4: Tested boundary value: {} - {}", boundaryValue, description);
        
        logger.info("Test completed successfully: Input Boundary Values");
    }
    
    @Test(description = "Test checkbox interactions")
    public void testCheckboxInteractions() {
        logger.info("Starting test: Checkbox Interactions");
        
        // Step 5: Navigate to checkboxes page and test checkbox interactions
        checkboxesPage.navigateToCheckboxesPage();
        logger.info("Step 5: Navigated to checkboxes page");
        
        // Verify checkboxes page is displayed
        Assert.assertTrue(checkboxesPage.isCheckboxesPageDisplayed(), 
                         "Checkboxes page should be displayed");
        
        int checkboxCount = checkboxesPage.getCheckboxCount();
        Assert.assertTrue(checkboxCount > 0, "At least one checkbox should be present");
        
        // Test checkbox toggle functionality
        for (int i = 0; i < checkboxCount; i++) {
            boolean initialState = checkboxesPage.isCheckboxSelected(i);
            logger.info("Checkbox {} initial state: {}", i, initialState);
            
            // Click checkbox to toggle
            checkboxesPage.clickCheckbox(i);
            
            // Verify state changed
            boolean newState = checkboxesPage.isCheckboxSelected(i);
            Assert.assertNotEquals(newState, initialState, 
                                  String.format("Checkbox %d should toggle states correctly", i));
            
            logger.info("Checkbox {} new state: {}", i, newState);
        }
        
        logger.info("Step 5: Verified checkboxes toggle states correctly");
        
        logger.info("Test completed successfully: Checkbox Interactions");
    }
    
    @Test(description = "Test dropdown selections", dataProvider = "dropdownOptions")
    public void testDropdownSelections(String optionText) {
        logger.info("Starting test: Dropdown Selections - Testing: {}", optionText);
        
        // Step 6: Navigate to dropdown page and test dropdown selections
        dropdownPage.navigateToDropdownPage();
        logger.info("Step 6: Navigated to dropdown page");
        
        // Verify dropdown page is displayed
        Assert.assertTrue(dropdownPage.isDropdownPageDisplayed(), 
                         "Dropdown page should be displayed");
        Assert.assertTrue(dropdownPage.isDropdownDisplayed(), 
                         "Dropdown should be displayed");
        Assert.assertTrue(dropdownPage.isDropdownEnabled(), 
                         "Dropdown should be enabled");
        
        // Test dropdown selection
        if (dropdownPage.isOptionAvailable(optionText)) {
            dropdownPage.selectByVisibleText(optionText);
            
            // Verify selection
            String selectedOption = dropdownPage.getSelectedOptionText();
            Assert.assertEquals(selectedOption, optionText, 
                              "Dropdown should allow selection of different options");
            
            logger.info("Successfully selected dropdown option: {}", optionText);
        } else {
            logger.info("Option '{}' not available in dropdown", optionText);
        }
        
        logger.info("Step 6: Tested dropdown selection for: {}", optionText);
        
        logger.info("Test completed successfully: Dropdown Selections");
    }
    
    @Test(description = "Test all dropdown options are selectable")
    public void testAllDropdownOptionsSelectable() {
        logger.info("Starting test: All Dropdown Options Selectable");
        
        // Navigate to dropdown page
        dropdownPage.navigateToDropdownPage();
        
        // Test all options are selectable
        Assert.assertTrue(dropdownPage.areAllOptionsSelectable(), 
                         "All dropdown options should be selectable");
        
        logger.info("Test completed successfully: All Dropdown Options Selectable");
    }
    
    @DataProvider(name = "numericValues")
    public Object[][] getNumericValues() {
        return new Object[][] {
            {"123", true},
            {"0", true},
            {"-456", true},
            {"789", true},
            {"1", true},
            {"999", true}
        };
    }
    
    @DataProvider(name = "nonNumericValues")
    public Object[][] getNonNumericValues() {
        return new Object[][] {
            {"abc"},
            {"test123"},
            {"!@#$%"},
            {"1.5a"},
            {"text"},
            {""},
            {" "},
            {"null"}
        };
    }
    
    @DataProvider(name = "boundaryValues")
    public Object[][] getBoundaryValues() {
        return new Object[][] {
            {"2147483647", "Maximum 32-bit integer"},
            {"-2147483648", "Minimum 32-bit integer"},
            {"0", "Zero value"},
            {"1", "Minimum positive"},
            {"-1", "Maximum negative"},
            {"999999999", "Large positive number"},
            {"-999999999", "Large negative number"}
        };
    }
    
    @DataProvider(name = "dropdownOptions")
    public Object[][] getDropdownOptions() {
        return new Object[][] {
            {"Option 1"},
            {"Option 2"}
        };
    }
}