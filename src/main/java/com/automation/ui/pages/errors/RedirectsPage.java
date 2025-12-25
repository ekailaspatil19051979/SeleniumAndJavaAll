package com.automation.ui.pages.errors;

import com.automation.core.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RedirectsPage extends BasePage {
    
    @FindBy(css = "h3")
    private WebElement pageTitle;
    
    @FindBy(linkText = "here")
    private WebElement redirectLink;
    
    @FindBy(id = "redirect")
    private WebElement redirectButton;
    
    @FindBy(css = "p")
    private WebElement instructionText;

    public RedirectsPage() {
        super();
    }

    public void navigateToRedirectPage() {
        navigateToUrl("/redirector");
    }

    public boolean isRedirectPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(pageTitle))
                    .getText().contains("Redirection") || 
                   driver.getCurrentUrl().contains("redirector");
        } catch (Exception e) {
            return false;
        }
    }

    public void clickRedirectLink() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(redirectLink)).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click redirect link: " + e.getMessage());
        }
    }

    public boolean isRedirectLinkVisible() {
        try {
            return redirectLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean hasRedirectOccurred(String originalUrl) {
        String currentUrl = getCurrentUrl();
        return !currentUrl.equals(originalUrl);
    }

    public void navigateBackToPreviousPage() {
        driver.navigate().back();
    }

    public boolean isStatusCodePage() {
        try {
            String currentUrl = getCurrentUrl();
            return currentUrl.contains("status_codes");
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

    public boolean waitForPageLoad(int timeoutInSeconds) {
        try {
            wait.until(ExpectedConditions.visibilityOf(pageTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void performRedirectTest() {
        String originalUrl = getCurrentUrl();
        clickRedirectLink();
        
        // Wait for potential redirect
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // Ignore
        }
        
        if (!hasRedirectOccurred(originalUrl)) {
            throw new RuntimeException("Redirect should have occurred from: " + originalUrl);
        }
    }

    public boolean isRedirectSuccessful() {
        try {
            String currentUrl = getCurrentUrl();
            String pageSource = driver.getPageSource();
            
            // Check if we've been redirected to a different page
            return !currentUrl.contains("redirector") || 
                   pageSource.contains("status") || 
                   pageSource.contains("redirect");
        } catch (Exception e) {
            return false;
        }
    }

    public String getRedirectDestination() {
        return getCurrentUrl();
    }

    public boolean canNavigateBack() {
        try {
            navigateBackToPreviousPage();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isHttpRedirect() {
        String currentUrl = getCurrentUrl();
        return currentUrl.startsWith("http") && !currentUrl.contains("redirector");
    }
}