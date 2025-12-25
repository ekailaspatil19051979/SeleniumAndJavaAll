package com.automation.core.base;

import com.automation.config.ConfigManager;
import com.automation.core.driver.DriverFactory;
import com.automation.core.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * Base Test Class - Parent class for all UI test classes
 * Handles WebDriver lifecycle and common test setup/teardown
 */
public abstract class BaseTest {
    protected final Logger logger = LogManager.getLogger(this.getClass());
    protected ConfigManager config;
    protected String baseUrl;
    
    public BaseTest() {
        this.config = ConfigManager.getInstance();
        this.baseUrl = config.getProperty("app.base.url", "https://the-internet.herokuapp.com");
    }
    
    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) {
        logger.info("Setting up test with browser: {}", browser);
        
        // Override browser from system property if provided
        String browserFromProperty = System.getProperty("browser");
        if (browserFromProperty != null && !browserFromProperty.isEmpty()) {
            browser = browserFromProperty;
        }
        
        // Create and set WebDriver
        WebDriver driver = DriverFactory.createDriver(browser);
        DriverManager.setDriver(driver);
        
        // Configure browser
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        
        logger.info("WebDriver setup completed for: {}", browser);
    }
    
    @AfterMethod
    public void tearDown() {
        logger.info("Tearing down test");
        if (DriverManager.isDriverInitialized()) {
            DriverManager.quitDriver();
            logger.info("WebDriver quit successfully");
        }
    }
    
    /**
     * Navigate to base URL
     */
    protected void navigateToBaseUrl() {
        DriverManager.getDriver().get(baseUrl);
        logger.info("Navigated to base URL: {}", baseUrl);
    }
    
    /**
     * Navigate to specific endpoint
     */
    protected void navigateToEndpoint(String endpoint) {
        String fullUrl = baseUrl + endpoint;
        DriverManager.getDriver().get(fullUrl);
        logger.info("Navigated to endpoint: {}", fullUrl);
    }
}