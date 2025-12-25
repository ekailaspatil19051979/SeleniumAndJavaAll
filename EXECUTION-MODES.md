# Test Execution Modes

This framework supports both **headless** and **headed** execution modes for maximum flexibility.

## Quick Start

### Using Scripts (Recommended)
```bash
# Windows
run-tests.bat headless   # Run all tests in headless mode
run-tests.bat headed     # Run all tests in headed mode  
run-tests.bat config     # Use application.properties setting

# Linux/Mac
./run-tests.sh headless  # Run all tests in headless mode
./run-tests.sh headed    # Run all tests in headed mode
./run-tests.sh config    # Use application.properties setting
```

### Using Maven Commands
```bash
# Headless Mode (CI/CD, Performance Testing)
mvn test -Dheadless=true

# Headed Mode (Local Debugging, Visual Verification) 
mvn test -Dheadless=false

# Use Config File Setting (application.properties)
mvn test

# Run Specific Tests
mvn test -Dheadless=true -Dtest=ValidLoginTest
mvn test -Dheadless=false -Dtest="*Authentication*"
```

## Configuration Priority

The framework determines execution mode in this order:

1. **System Property** (highest priority)
   ```bash
   mvn test -Dheadless=true
   ```

2. **Environment Variable**
   ```bash
   export HEADLESS=true
   mvn test
   ```

3. **Config File** (application.properties)
   ```properties
   browser.headless=false
   ```

## When to Use Each Mode

### Headless Mode (`-Dheadless=true`)
- ✅ CI/CD pipeline execution
- ✅ Performance testing
- ✅ Automated regression testing
- ✅ Resource-constrained environments
- ✅ Background test execution

### Headed Mode (`-Dheadless=false`)  
- ✅ Local development and debugging
- ✅ Visual test verification
- ✅ Test script development
- ✅ Element inspection and troubleshooting
- ✅ Demo and presentation purposes

## Examples

```bash
# Development workflow
mvn test -Dheadless=false -Dtest=ValidLoginTest  # Debug specific test
mvn test -Dheadless=true                         # Full regression

# CI/CD pipeline
mvn clean test -Dheadless=true                   # Automated testing

# Mixed scenarios
mvn test -Dheadless=false -Dbrowser=chrome       # Headed Chrome
mvn test -Dheadless=true -Dtest="*Login*,*Auth*" # Headless subset
```

## Configuration Details

Current application.properties setting:
```properties
browser.headless=false  # Default to headed for local development
```

The framework automatically:
- Disables browser maximization in headless mode
- Applies appropriate Chrome options for each mode
- Logs the execution mode for clarity