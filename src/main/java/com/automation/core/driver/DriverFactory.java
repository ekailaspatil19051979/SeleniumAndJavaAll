package com.automation.core.driver;

import com.automation.config.ConfigManager;
import com.automation.constants.FrameworkConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * WebDriver Factory - Creates and configures WebDriver instances
 * Implements Factory Pattern for WebDriver creation
 */
public class DriverFactory {
    private static final Logger logger = LogManager.getLogger(DriverFactory.class);
    private static final ConfigManager config = ConfigManager.getInstance();
    
    public static WebDriver createDriver(String browserName) {
        logger.info("Creating WebDriver instance for browser: {}", browserName);
        
        switch (browserName.toLowerCase()) {
            case "chrome":
                return createChromeDriver();
            case "firefox":
                return createFirefoxDriver();
            case "edge":
                return createEdgeDriver();
            case "chrome-headless":
                return createChromeHeadlessDriver();
            case "firefox-headless":
                return createFirefoxHeadlessDriver();
            default:
                logger.warn("Browser '{}' not supported. Defaulting to Chrome", browserName);
                return createChromeDriver();
        }
    }
    
    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = getChromeOptions();
        return new ChromeDriver(options);
    }
    
    private static WebDriver createChromeHeadlessDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = getChromeOptions();
        options.addArguments("--headless");
        return new ChromeDriver(options);
    }
    
    private static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = getFirefoxOptions();
        return new FirefoxDriver(options);
    }
    
    private static WebDriver createFirefoxHeadlessDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = getFirefoxOptions();
        options.addArguments("--headless");
        return new FirefoxDriver(options);
    }
    
    private static WebDriver createEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = getEdgeOptions();
        return new EdgeDriver(options);
    }
    
    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        
        // Check headless mode - system property overrides config file
        boolean headlessMode = isHeadlessMode();
        if (headlessMode) {
            options.addArguments("--headless");
            logger.info("Running Chrome in headless mode");
        } else {
            logger.info("Running Chrome in headed mode");
        }
        
        // Only maximize in headed mode
        if (!headlessMode && config.getBooleanProperty("browser.maximize", true)) {
            options.addArguments("--start-maximized");
        }
        
        if (config.getBooleanProperty("browser.disable.notifications", true)) {
            options.addArguments("--disable-notifications");
        }
        
        return options;
    }
    
    /**
     * Determine if headless mode should be used
     * Priority: System property > Environment variable > Config file
     */
    private static boolean isHeadlessMode() {
        // Check system property first (highest priority)
        String headlessProperty = System.getProperty("headless");
        if (headlessProperty != null) {
            boolean headless = Boolean.parseBoolean(headlessProperty);
            logger.info("Headless mode set via system property: {}", headless);
            return headless;
        }
        
        // Check environment variable second
        String headlessEnv = System.getenv("HEADLESS");
        if (headlessEnv != null) {
            boolean headless = Boolean.parseBoolean(headlessEnv);
            logger.info("Headless mode set via environment variable: {}", headless);
            return headless;
        }
        
        // Fall back to config file (default)
        boolean headless = config.getBooleanProperty("browser.headless", false);
        logger.info("Headless mode set via config file: {}", headless);
        return headless;
    }
    
    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        return options;
    }
    
    private static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        return options;
    }
}