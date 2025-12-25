package com.automation.core.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Driver Manager - Thread-safe WebDriver management
 * Uses ThreadLocal to ensure thread safety in parallel execution
 */
public class DriverManager {
    private static final Logger logger = LogManager.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    
    public static void setDriver(WebDriver driver) {
        logger.debug("Setting WebDriver for thread: {}", Thread.currentThread().getName());
        driverThreadLocal.set(driver);
    }
    
    public static WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver == null) {
            logger.error("WebDriver is null for thread: {}", Thread.currentThread().getName());
            throw new IllegalStateException("WebDriver is not initialized. Call setDriver() first.");
        }
        return driver;
    }
    
    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            logger.info("Quitting WebDriver for thread: {}", Thread.currentThread().getName());
            driver.quit();
            driverThreadLocal.remove();
        }
    }
    
    public static boolean isDriverInitialized() {
        return driverThreadLocal.get() != null;
    }
}