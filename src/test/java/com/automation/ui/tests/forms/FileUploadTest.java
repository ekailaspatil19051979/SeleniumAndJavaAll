package com.automation.ui.tests.forms;

import com.automation.core.base.BaseTest;
import com.automation.ui.pages.forms.FileUploadPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadTest extends BaseTest {
    
    private FileUploadPage fileUploadPage;
    private String testFilePath;
    
    @BeforeMethod
    public void setUp() {
        fileUploadPage = new FileUploadPage();
        createTestFile();
    }
    
    private void createTestFile() {
        try {
            Path tempDir = Files.createTempDirectory("selenium-test");
            File testFile = new File(tempDir.toFile(), "test-file.txt");
            try (FileWriter writer = new FileWriter(testFile)) {
                writer.write("This is a test file for Selenium automation testing.");
            }
            testFilePath = testFile.getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException("Failed to create test file: " + e.getMessage());
        }
    }

    @Test
    public void testFileUploadPage() {
        fileUploadPage.navigateToFileUploadPage();
        Assert.assertTrue(fileUploadPage.isFileUploadPageDisplayed(),
                "File upload page should be displayed");
    }

    @Test
    public void testValidFileUpload() {
        fileUploadPage.navigateToFileUploadPage();
        
        // Select and upload file
        fileUploadPage.selectFile(testFilePath);
        fileUploadPage.clickUploadButton();
        
        // Verify upload success
        Assert.assertTrue(fileUploadPage.isFileUploaded("test-file.txt"),
                "File should be uploaded successfully");
    }

    @Test
    public void testFileSelectionDisplay() {
        fileUploadPage.navigateToFileUploadPage();
        
        // Select file
        fileUploadPage.selectFile(testFilePath);
        
        // Verify selected file name is displayed
        String selectedFileName = fileUploadPage.getSelectedFileName();
        Assert.assertEquals(selectedFileName, "test-file.txt",
                "Selected file name should be displayed correctly");
    }

    @Test
    public void testUploadButtonInitialState() {
        fileUploadPage.navigateToFileUploadPage();
        
        // Verify upload button is enabled initially
        Assert.assertTrue(fileUploadPage.isUploadButtonEnabled(),
                "Upload button should be enabled initially");
    }

    @Test(dataProvider = "fileTypes")
    public void testDifferentFileTypes(String fileName, String content) {
        try {
            // Create test file of different type
            Path tempDir = Files.createTempDirectory("selenium-test");
            File testFile = new File(tempDir.toFile(), fileName);
            try (FileWriter writer = new FileWriter(testFile)) {
                writer.write(content);
            }
            
            fileUploadPage.navigateToFileUploadPage();
            fileUploadPage.selectFile(testFile.getAbsolutePath());
            fileUploadPage.clickUploadButton();
            
            // Verify upload
            Assert.assertTrue(fileUploadPage.isFileUploaded(fileName),
                    "File " + fileName + " should be uploaded successfully");
                    
        } catch (IOException e) {
            Assert.fail("Failed to create test file: " + e.getMessage());
        }
    }

    @Test
    public void testUploadSequence() {
        fileUploadPage.navigateToFileUploadPage();
        
        // Step 1: Verify page is loaded
        Assert.assertTrue(fileUploadPage.isFileUploadPageDisplayed(),
                "File upload page should be loaded");
        
        // Step 2: Select file
        fileUploadPage.selectFile(testFilePath);
        String selectedFile = fileUploadPage.getSelectedFileName();
        Assert.assertFalse(selectedFile.isEmpty(),
                "File should be selected");
        
        // Step 3: Upload file
        fileUploadPage.clickUploadButton();
        
        // Step 4: Verify upload success
        Assert.assertTrue(fileUploadPage.isFileUploaded("test-file.txt"),
                "File upload should be completed successfully");
    }

    @Test
    public void testMultipleFileUploads() {
        try {
            // Create multiple test files
            Path tempDir = Files.createTempDirectory("selenium-test");
            String[] fileNames = {"file1.txt", "file2.txt", "file3.txt"};
            
            for (String fileName : fileNames) {
                File testFile = new File(tempDir.toFile(), fileName);
                try (FileWriter writer = new FileWriter(testFile)) {
                    writer.write("Content for " + fileName);
                }
                
                fileUploadPage.navigateToFileUploadPage();
                fileUploadPage.selectFile(testFile.getAbsolutePath());
                fileUploadPage.clickUploadButton();
                
                Assert.assertTrue(fileUploadPage.isFileUploaded(fileName),
                        "File " + fileName + " should be uploaded successfully");
            }
        } catch (IOException e) {
            Assert.fail("Failed to create test files: " + e.getMessage());
        }
    }

    @Test
    public void testUploadWithoutFileSelection() {
        fileUploadPage.navigateToFileUploadPage();
        
        // Try to upload without selecting file
        fileUploadPage.clickUploadButton();
        
        // Should handle gracefully (no specific assertion as behavior may vary)
        Assert.assertTrue(fileUploadPage.isFileUploadPageDisplayed(),
                "Page should remain functional after empty upload attempt");
    }

    @DataProvider
    public Object[][] fileTypes() {
        return new Object[][]{
                {"test-document.txt", "This is a plain text document."},
                {"test-data.csv", "Name,Age,City\nJohn,30,New York\nJane,25,Boston"},
                {"test-config.xml", "<?xml version=\"1.0\"?><config><setting>value</setting></config>"},
                {"test-script.json", "{\"name\": \"test\", \"type\": \"automation\", \"valid\": true}"}
        };
    }
}