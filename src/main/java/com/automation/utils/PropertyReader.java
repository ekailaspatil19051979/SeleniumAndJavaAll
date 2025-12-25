package com.automation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Property Reader - Utility for reading property files
 */
public class PropertyReader {
    private static final Logger logger = LogManager.getLogger(PropertyReader.class);
    private Properties properties;
    
    public PropertyReader() {
        this.properties = new Properties();
    }
    
    public void loadProperties(String filePath) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream != null) {
                properties.load(inputStream);
                logger.info("Successfully loaded properties from: {}", filePath);
            } else {
                logger.warn("Property file not found: {}", filePath);
            }
        } catch (IOException e) {
            logger.error("Error loading properties from file: {}", filePath, e);
        }
    }
    
    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Property not found: {}", key);
        }
        return value;
    }
    
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}