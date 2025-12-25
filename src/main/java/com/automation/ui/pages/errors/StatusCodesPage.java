package com.automation.ui.pages.errors;

import com.automation.core.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class StatusCodesPage extends BasePage {
    
    @FindBy(css = "h3")
    private WebElement pageTitle;
    
    @FindBy(linkText = "200")
    private WebElement status200Link;
    
    @FindBy(linkText = "301")
    private WebElement status301Link;
    
    @FindBy(linkText = "404")
    private WebElement status404Link;
    
    @FindBy(linkText = "500")
    private WebElement status500Link;
    
    @FindBy(css = "a")
    private List<WebElement> statusLinks;
    
    @FindBy(css = "p")
    private WebElement statusMessage;

    public StatusCodesPage() {
        super();
    }

    public void navigateToStatusCodesPage() {
        navigateToUrl("/status_codes");
    }

    public boolean isStatusCodesPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(pageTitle))
                    .getText().contains("Status Codes");
        } catch (Exception e) {
            return false;
        }
    }

    public void clickStatus200Link() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(status200Link)).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click status 200 link: " + e.getMessage());
        }
    }

    public void clickStatus301Link() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(status301Link)).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click status 301 link: " + e.getMessage());
        }
    }

    public void clickStatus404Link() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(status404Link)).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click status 404 link: " + e.getMessage());
        }
    }

    public void clickStatus500Link() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(status500Link)).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click status 500 link: " + e.getMessage());
        }
    }

    public boolean areStatusLinksVisible() {
        try {
            return status200Link.isDisplayed() && status404Link.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getStatusLinksCount() {
        return statusLinks.size();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public boolean isErrorPageDisplayed() {
        try {
            String pageSource = getPageSource().toLowerCase();
            return pageSource.contains("error") || pageSource.contains("not found") || 
                   pageSource.contains("internal server error");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSuccessPageDisplayed() {
        try {
            String pageSource = getPageSource().toLowerCase();
            return pageSource.contains("success") || pageSource.contains("ok") || getCurrentUrl().contains("200");
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Navigate back in browser history
     */
    public void navigateBack() {
        driver.navigate().back();
    }

    public String getStatusMessage() {
        try {
            return statusMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void clickStatusLinkAndVerify(String statusCode) {
        switch (statusCode) {
            case "200":
                clickStatus200Link();
                break;
            case "301":
                clickStatus301Link();
                break;
            case "404":
                clickStatus404Link();
                break;
            case "500":
                clickStatus500Link();
                break;
            default:
                throw new RuntimeException("Unsupported status code: " + statusCode);
        }
    }

    public boolean isPageAccessible() {
        try {
            return pageTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}