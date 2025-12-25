package com.automation.cucumber.stepdefinations.common;

import com.automation.core.driver.DriverFactory;
import com.automation.core.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Hooks - Cucumber hooks for setup and teardown
 */
public class Hooks {
    private static final Logger logger = LogManager.getLogger(Hooks.class);
    
    @Before
    public void setUp(Scenario scenario) {
        logger.info("Starting scenario: {}", scenario.getName());
        
        // Get browser from system property or default to chrome
        String browser = System.getProperty("browser", "chrome");
        
        // Create and set WebDriver
        WebDriver driver = DriverFactory.createDriver(browser);
        DriverManager.setDriver(driver);
        
        // Configure browser
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        
        logger.info("WebDriver setup completed for scenario: {}", scenario.getName());
    }
    
    @After
    public void tearDown(Scenario scenario) {
        logger.info("Finishing scenario: {} with status: {}", 
                    scenario.getName(), scenario.getStatus());
        
        // Take screenshot on failure
        if (scenario.isFailed() && DriverManager.isDriverInitialized()) {
            byte[] screenshot = ((org.openqa.selenium.TakesScreenshot) DriverManager.getDriver())
                    .getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot");
        }
        
        // Quit WebDriver
        if (DriverManager.isDriverInitialized()) {
            DriverManager.quitDriver();
            logger.info("WebDriver quit successfully for scenario: {}", scenario.getName());
        }
    }
}