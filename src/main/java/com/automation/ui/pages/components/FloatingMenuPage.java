package com.automation.ui.pages.components;

import com.automation.core.base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class FloatingMenuPage extends BasePage {
    
    @FindBy(css = "h3")
    private WebElement pageTitle;
    
    @FindBy(id = "menu")
    private WebElement floatingMenu;
    
    @FindBy(css = "#menu a")
    private List<WebElement> menuItems;
    
    @FindBy(linkText = "Home")
    private WebElement homeLink;
    
    @FindBy(linkText = "News")
    private WebElement newsLink;
    
    @FindBy(linkText = "Contact")
    private WebElement contactLink;
    
    @FindBy(linkText = "About")
    private WebElement aboutLink;
    
    @FindBy(css = ".large-12")
    private WebElement contentArea;

    public FloatingMenuPage() {
        super();
    }

    public void navigateToFloatingMenuPage() {
        navigateToUrl("/floating_menu");
    }

    public boolean isFloatingMenuPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(pageTitle))
                    .getText().contains("Floating Menu");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFloatingMenuVisible() {
        try {
            return floatingMenu.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getMenuItemsCount() {
        return menuItems.size();
    }

    public void scrollToBottom() {
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(1000); // Wait for scroll animation
        } catch (Exception e) {
            throw new RuntimeException("Failed to scroll to bottom: " + e.getMessage());
        }
    }

    public void scrollToTop() {
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
            Thread.sleep(1000); // Wait for scroll animation
        } catch (Exception e) {
            throw new RuntimeException("Failed to scroll to top: " + e.getMessage());
        }
    }

    public void scrollByPixels(int pixels) {
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, " + pixels + ");");
            Thread.sleep(500); // Wait for scroll animation
        } catch (Exception e) {
            throw new RuntimeException("Failed to scroll by pixels: " + e.getMessage());
        }
    }

    public boolean isMenuStillVisibleAfterScroll() {
        return isFloatingMenuVisible();
    }

    public void clickHomeLink() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(homeLink)).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Home link: " + e.getMessage());
        }
    }

    public void clickNewsLink() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(newsLink)).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click News link: " + e.getMessage());
        }
    }

    public void clickContactLink() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(contactLink)).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Contact link: " + e.getMessage());
        }
    }

    public void clickAboutLink() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(aboutLink)).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click About link: " + e.getMessage());
        }
    }

    public void clickMenuLink(String linkText) {
        switch (linkText.toLowerCase()) {
            case "home":
                clickHomeLink();
                break;
            case "news":
                clickNewsLink();
                break;
            case "contact":
                clickContactLink();
                break;
            case "about":
                clickAboutLink();
                break;
            default:
                throw new RuntimeException("Unknown menu link: " + linkText);
        }
    }

    public String getMenuPosition() {
        try {
            return floatingMenu.getCssValue("position");
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isMenuFixed() {
        String position = getMenuPosition();
        return "fixed".equals(position) || "sticky".equals(position);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean hasNavigatedToSection(String section) {
        String currentUrl = getCurrentUrl();
        return currentUrl.contains("#" + section.toLowerCase());
    }

    public int getScrollPosition() {
        try {
            return ((Long) ((JavascriptExecutor) driver).executeScript("return window.pageYOffset;")).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean areMenuItemsClickable() {
        try {
            for (WebElement menuItem : menuItems) {
                if (!menuItem.isEnabled()) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getMenuItemText(int index) {
        try {
            if (index >= 0 && index < menuItems.size()) {
                return menuItems.get(index).getText();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    public void testMenuFloatingBehavior() {
        // Test sequence: scroll down, check menu, scroll up, check menu
        scrollToBottom();
        boolean visibleAfterScroll = isMenuStillVisibleAfterScroll();
        scrollToTop();
        boolean visibleAfterScrollBack = isMenuStillVisibleAfterScroll();
        
        if (!visibleAfterScroll || !visibleAfterScrollBack) {
            throw new RuntimeException("Floating menu should remain visible during scrolling");
        }
    }
}