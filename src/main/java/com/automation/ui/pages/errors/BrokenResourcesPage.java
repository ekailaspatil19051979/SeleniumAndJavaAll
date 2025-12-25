package com.automation.ui.pages.errors;

import com.automation.core.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BrokenResourcesPage extends BasePage {
    
    @FindBy(css = "h3")
    private WebElement pageTitle;
    
    @FindBy(css = "img")
    private List<WebElement> images;
    
    @FindBy(css = "p")
    private WebElement instructionText;

    public BrokenResourcesPage() {
        super();
    }

    public void navigateToBrokenImagesPage() {
        navigateToUrl("/broken_images");
    }

    public boolean isBrokenImagesPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(pageTitle))
                    .getText().contains("Broken Images");
        } catch (Exception e) {
            return false;
        }
    }

    public int getImagesCount() {
        return images.size();
    }

    public boolean areImagesPresent() {
        return !images.isEmpty();
    }

    public boolean isImageBroken(int index) {
        try {
            if (index >= 0 && index < images.size()) {
                WebElement image = images.get(index);
                // Check if image has loaded by examining naturalWidth
                Object naturalWidth = jsExecutor.executeScript("return arguments[0].naturalWidth", image);
                return naturalWidth.equals(0L);
            }
            return false;
        } catch (Exception e) {
            return true; // Consider it broken if we can't check
        }
    }

    public String getImageSrc(int index) {
        try {
            if (index >= 0 && index < images.size()) {
                return images.get(index).getAttribute("src");
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    public String getImageAlt(int index) {
        try {
            if (index >= 0 && index < images.size()) {
                return images.get(index).getAttribute("alt");
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    public int getBrokenImagesCount() {
        int brokenCount = 0;
        for (int i = 0; i < images.size(); i++) {
            if (isImageBroken(i)) {
                brokenCount++;
            }
        }
        return brokenCount;
    }

    public int getWorkingImagesCount() {
        return images.size() - getBrokenImagesCount();
    }

    public boolean isPageLayoutIntact() {
        try {
            return pageTitle.isDisplayed() && instructionText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getInstructionText() {
        try {
            return instructionText.getText();
        } catch (Exception e) {
            return "";
        }
    }
}