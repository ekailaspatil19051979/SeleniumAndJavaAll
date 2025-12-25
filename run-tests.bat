@echo off
REM Automation Test Framework - Execution Script
REM Usage examples for both headless and headed modes

echo ========================================
echo   Automation Test Framework Runner
echo ========================================
echo.

if "%1"=="headless" (
    echo Running tests in HEADLESS mode...
    mvn clean test -Dheadless=true
) else if "%1"=="headed" (
    echo Running tests in HEADED mode...
    mvn clean test -Dheadless=false
) else if "%1"=="config" (
    echo Running tests using APPLICATION.PROPERTIES configuration...
    mvn clean test
) else (
    echo Usage:
    echo   run-tests.bat headless   - Run all tests in headless mode
    echo   run-tests.bat headed     - Run all tests in headed mode
    echo   run-tests.bat config     - Use application.properties setting
    echo.
    echo Additional options:
    echo   mvn test -Dheadless=true                    - Headless mode
    echo   mvn test -Dheadless=false                   - Headed mode
    echo   mvn test -Dheadless=true -Dtest=ValidLogin* - Headless with specific test
    echo   mvn test -Dbrowser=chrome -Dheadless=false  - Headed Chrome
    echo.
)