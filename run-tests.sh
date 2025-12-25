#!/bin/bash
# Automation Test Framework - Execution Script
# Usage examples for both headless and headed modes

echo "========================================"
echo "   Automation Test Framework Runner"
echo "========================================"
echo

case "$1" in
    headless)
        echo "Running tests in HEADLESS mode..."
        mvn clean test -Dheadless=true
        ;;
    headed)
        echo "Running tests in HEADED mode..."
        mvn clean test -Dheadless=false
        ;;
    config)
        echo "Running tests using APPLICATION.PROPERTIES configuration..."
        mvn clean test
        ;;
    *)
        echo "Usage:"
        echo "  ./run-tests.sh headless   - Run all tests in headless mode"
        echo "  ./run-tests.sh headed     - Run all tests in headed mode"  
        echo "  ./run-tests.sh config     - Use application.properties setting"
        echo
        echo "Additional options:"
        echo "  mvn test -Dheadless=true                    - Headless mode"
        echo "  mvn test -Dheadless=false                   - Headed mode"
        echo "  mvn test -Dheadless=true -Dtest=ValidLogin* - Headless with specific test"
        echo "  mvn test -Dbrowser=chrome -Dheadless=false  - Headed Chrome"
        echo
        ;;
esac