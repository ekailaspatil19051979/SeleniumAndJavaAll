package com.automation.ui.pages.components;

import com.automation.core.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HoverInteractionsPage extends BasePage {
    
    @FindBy(css = ".figure")
    private List<WebElement> userAvatars;
    
    @FindBy(css = "h3")
    private WebElement pageTitle;
    
    @FindBy(css = ".figcaption")
    private List<WebElement> userInfoContainers;
    
    @FindBy(css = ".figcaption h5")
    private List<WebElement> userNames;
    
    @FindBy(css = ".figcaption a")
    private List<WebElement> viewProfileLinks;

    public HoverInteractionsPage() {
        super();
    }

    public void navigateToHoverPage() {
        navigateToUrl("/hovers");
    }

    public boolean isHoverPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(pageTitle))
                    .getText().contains("Hovers");
        } catch (Exception e) {
            return false;
        }
    }

    public int getUserAvatarsCount() {
        return userAvatars.size();
    }

    public void hoverOverAvatar(int index) {
        try {
            if (index >= 0 && index < userAvatars.size()) {
                Actions actions = new Actions(driver);
                WebElement avatar = userAvatars.get(index);
                actions.moveToElement(avatar).perform();
            } else {
                throw new RuntimeException("Avatar index out of bounds: " + index);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to hover over avatar: " + e.getMessage());
        }
    }

    public boolean isUserInfoVisible(int index) {
        try {
            if (index >= 0 && index < userInfoContainers.size()) {
                return userInfoContainers.get(index).isDisplayed();
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserName(int index) {
        try {
            if (index >= 0 && index < userNames.size()) {
                return userNames.get(index).getText();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    public void clickViewProfile(int index) {
        try {
            if (index >= 0 && index < viewProfileLinks.size()) {
                viewProfileLinks.get(index).click();
            } else {
                throw new RuntimeException("View profile link index out of bounds: " + index);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to click view profile: " + e.getMessage());
        }
    }

    public boolean areAvatarsVisible() {
        try {
            return !userAvatars.isEmpty() && userAvatars.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void hoverAndVerifyUserInfo(int index, String expectedUserName) {
        hoverOverAvatar(index);
        
        // Wait a moment for hover effect
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // Ignore
        }
        
        if (!isUserInfoVisible(index)) {
            throw new RuntimeException("User info not visible after hovering over avatar " + index);
        }
        
        String actualUserName = getUserName(index);
        if (!expectedUserName.equals(actualUserName)) {
            throw new RuntimeException("Expected user name '" + expectedUserName + "' but got '" + actualUserName + "'");
        }
    }

    public boolean isViewProfileLinkVisible(int index) {
        try {
            if (index >= 0 && index < viewProfileLinks.size()) {
                return viewProfileLinks.get(index).isDisplayed();
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}