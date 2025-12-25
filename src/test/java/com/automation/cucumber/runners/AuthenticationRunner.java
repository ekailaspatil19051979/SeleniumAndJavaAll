package com.automation.cucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * Authentication Test Runner - Cucumber runner for authentication features
 */
@CucumberOptions(
        features = "src/test/resources/features/authentication",
        glue = {"com.automation.cucumber.stepdefinations"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/authentication",
                "json:target/cucumber-reports/authentication/Cucumber.json",
                "junit:target/cucumber-reports/authentication/Cucumber.xml"
        },
        tags = "@authentication",
        monochrome = true,
        dryRun = false
)
public class AuthenticationRunner extends AbstractTestNGCucumberTests {
    
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}