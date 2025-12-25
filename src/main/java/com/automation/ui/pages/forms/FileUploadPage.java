package com.automation.ui.pages.forms;

import com.automation.core.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

public class FileUploadPage extends BasePage {
    
    @FindBy(id = "file-upload")
    private WebElement fileInput;
    
    @FindBy(id = "file-submit")
    private WebElement uploadButton;
    
    @FindBy(id = "uploaded-files")
    private WebElement uploadedFiles;
    
    @FindBy(css = "h3")
    private WebElement pageTitle;
    
    @FindBy(id = "drag-drop-upload")
    private WebElement dragDropArea;

    public FileUploadPage() {
        super();
    }

    public void navigateToFileUploadPage() {
        navigateToUrl("/upload");
    }

    public boolean isFileUploadPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(pageTitle))
                    .getText().contains("File Upload");
        } catch (Exception e) {
            return false;
        }
    }

    public void selectFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new RuntimeException("File does not exist: " + filePath);
            }
            wait.until(ExpectedConditions.elementToBeClickable(fileInput))
                .sendKeys(file.getAbsolutePath());
        } catch (Exception e) {
            throw new RuntimeException("Failed to select file: " + e.getMessage());
        }
    }

    public void clickUploadButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(uploadButton)).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click upload button: " + e.getMessage());
        }
    }

    public boolean isFileUploaded(String fileName) {
        try {
            WebElement uploadedFilesElement = wait.until(ExpectedConditions.visibilityOf(uploadedFiles));
            return uploadedFilesElement.getText().contains(fileName);
        } catch (Exception e) {
            return false;
        }
    }

    public String getUploadedFileName() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(uploadedFiles)).getText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get uploaded file name: " + e.getMessage());
        }
    }

    public boolean isUploadButtonEnabled() {
        try {
            return uploadButton.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDragDropAreaVisible() {
        try {
            return dragDropArea.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void uploadFileAndVerify(String filePath, String expectedFileName) {
        selectFile(filePath);
        clickUploadButton();
        if (!isFileUploaded(expectedFileName)) {
            throw new RuntimeException("File upload verification failed for: " + expectedFileName);
        }
    }

    public String getSelectedFileName() {
        try {
            String value = fileInput.getAttribute("value");
            return value != null ? new File(value).getName() : "";
        } catch (Exception e) {
            return "";
        }
    }
}