package com.automation.constants;

/**
 * Framework Constants - Central location for all framework constants
 */
public final class FrameworkConstants {
    
    // Timeout Constants
    public static final int EXPLICIT_WAIT_TIMEOUT = 30;
    public static final int IMPLICIT_WAIT_TIMEOUT = 10;
    public static final int PAGE_LOAD_TIMEOUT = 60;
    
    // File Paths
    public static final String CONFIG_DIRECTORY = "src/main/resources/config/";
    public static final String TEST_DATA_DIRECTORY = "src/main/resources/testdata/";
    public static final String SCREENSHOTS_DIRECTORY = "reports/screenshots/";
    public static final String EXTENT_REPORTS_DIRECTORY = "reports/extent/";
    public static final String CUCUMBER_REPORTS_DIRECTORY = "reports/cucumber/";
    
    // Browser Constants
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    public static final String EDGE = "edge";
    public static final String SAFARI = "safari";
    
    // Test Data File Names
    public static final String LOGIN_TEST_DATA = "authentication/login-data.json";
    public static final String FORM_TEST_DATA = "forms/form-data.json";
    public static final String API_TEST_DATA = "api/api-test-data.json";
    
    // Application URLs
    public static final String LOGIN_URL = "/login";
    public static final String SECURE_AREA_URL = "/secure";
    public static final String INPUTS_URL = "/inputs";
    public static final String CHECKBOXES_URL = "/checkboxes";
    public static final String DROPDOWN_URL = "/dropdown";
    public static final String FILE_UPLOAD_URL = "/upload";
    public static final String DYNAMIC_LOADING_URL = "/dynamic_loading";
    public static final String ADD_REMOVE_ELEMENTS_URL = "/add_remove_elements/";
    public static final String DRAG_AND_DROP_URL = "/drag_and_drop";
    public static final String WINDOWS_URL = "/windows";
    public static final String CONTEXT_MENU_URL = "/context_menu";
    public static final String FRAMES_URL = "/frames";
    public static final String HOVERS_URL = "/hovers";
    public static final String CHALLENGING_DOM_URL = "/challenging_dom";
    public static final String NOTIFICATION_URL = "/notification_message_rendered";
    public static final String FLOATING_MENU_URL = "/floating_menu";
    public static final String BROKEN_IMAGES_URL = "/broken_images";
    public static final String REDIRECTOR_URL = "/redirector";
    public static final String STATUS_CODES_URL = "/status_codes";
    
    // Test Messages
    public static final String LOGIN_SUCCESS_MESSAGE = "You logged into a secure area!";
    public static final String LOGOUT_SUCCESS_MESSAGE = "You logged out of the secure area!";
    public static final String INVALID_LOGIN_MESSAGE = "Your username is invalid!";
    public static final String INVALID_PASSWORD_MESSAGE = "Your password is invalid!";
    
    // Test Data
    public static final String VALID_USERNAME = "tomsmith";
    public static final String VALID_PASSWORD = "SuperSecretPassword!";
    public static final String INVALID_USERNAME = "invaliduser";
    public static final String INVALID_PASSWORD = "invalidpass";
    
    // Private constructor to prevent instantiation
    private FrameworkConstants() {
        throw new AssertionError("Cannot instantiate constants class");
    }
}