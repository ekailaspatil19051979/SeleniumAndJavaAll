package com.automation.config;

import com.automation.utils.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Configuration Manager - Centralized configuration management
 * Responsible for loading and providing access to application configurations
 */
public class ConfigManager {
    private static final Logger logger = LogManager.getLogger(ConfigManager.class);
    private static ConfigManager instance;
    private PropertyReader propertyReader;
    private String environment;
    
    private ConfigManager() {
        this.environment = System.getProperty("environment", "dev");
        this.propertyReader = new PropertyReader();
        loadConfiguration();
    }
    
    public static ConfigManager getInstance() {
        if (instance == null) {
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }
    
    private void loadConfiguration() {
        logger.info("Loading configuration for environment: {}", environment);
        propertyReader.loadProperties("config/application.properties");
        propertyReader.loadProperties("config/" + environment + ".properties");
    }
    
    public String getProperty(String key) {
        return propertyReader.getProperty(key);
    }
    
    public String getProperty(String key, String defaultValue) {
        return propertyReader.getProperty(key, defaultValue);
    }
    
    public int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }
    
    public int getIntProperty(String key, int defaultValue) {
        try {
            return Integer.parseInt(getProperty(key));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    public boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }
    
    public boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = getProperty(key);
        return value != null ? Boolean.parseBoolean(value) : defaultValue;
    }
    
    public String getEnvironment() {
        return environment;
    }
}