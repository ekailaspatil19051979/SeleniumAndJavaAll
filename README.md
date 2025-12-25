# The Internet - Enterprise Automation Framework

## Overview
This is a comprehensive, enterprise-grade test automation framework built with Java, Selenium WebDriver, TestNG, REST Assured, and Cucumber BDD. The framework is designed to test [The Internet](https://the-internet.herokuapp.com/) application with scalable architecture and maintainable code structure.

## Framework Architecture
- **Page Object Model (POM)** - Encapsulation of page elements and actions
- **Behavior Driven Development (BDD)** - Cucumber for readable test scenarios
- **Parallel Execution** - TestNG parallel execution capabilities
- **Cross-Browser Testing** - Support for Chrome, Firefox, Edge
- **Reporting** - ExtentReports and Cucumber HTML reports
- **Configuration Management** - Environment-based configuration
- **Data-Driven Testing** - JSON-based test data management

## Project Structure
```
src/
├── main/java/com/automation/
│   ├── config/              # Configuration management
│   ├── core/                # Core framework components
│   │   ├── driver/          # WebDriver management
│   │   ├── browser/         # Browser configurations
│   │   ├── base/            # Base classes for pages and tests
│   │   └── listeners/       # TestNG listeners
│   ├── ui/                  # UI automation components
│   │   ├── pages/           # Page Object classes
│   │   ├── components/      # Reusable UI components
│   │   └── actions/         # Common UI actions
│   ├── api/                 # API automation components
│   ├── utils/               # Utility classes
│   └── constants/           # Framework constants
├── test/java/com/automation/
│   ├── ui/tests/            # UI test classes
│   ├── api/tests/           # API test classes
│   └── cucumber/            # Cucumber step definitions and runners
└── test/resources/
    └── features/            # Cucumber feature files
```

## Prerequisites
- Java 11 or higher
- Maven 3.6+
- Chrome/Firefox/Edge browser
- Git

## Getting Started

### 1. Clone the Repository
```bash
git clone <repository-url>
cd the-internet-automation
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Run Tests

#### Run All Tests
```bash
mvn test
```

#### Run Specific Test Suite
```bash
mvn test -Dsurefire.suiteXmlFiles=testng.xml
```

#### Run Tests with Specific Browser
```bash
mvn test -Dbrowser=firefox
```

#### Run Tests in Headless Mode
```bash
mvn test -Dbrowser=chrome-headless
```

#### Run Cucumber Tests
```bash
mvn test -Dcucumber.options="--tags @authentication"
```

## Configuration

### Environment Configuration
Configure different environments in `src/main/resources/config/`:
- `application.properties` - Common settings
- `dev.properties` - Development environment
- `staging.properties` - Staging environment
- `production.properties` - Production environment

### Test Data
Test data is stored in JSON format under `src/main/resources/testdata/`

## Test Scenarios Covered

### Authentication Tests
- Valid login functionality
- Invalid login attempts
- Logout functionality
- Session management

### Form Interactions
- Input field validation
- Checkbox interactions
- Dropdown selections
- File upload functionality

### Dynamic Content
- Dynamic loading scenarios
- Add/Remove elements
- Drag and drop interactions

### Navigation
- Multiple windows handling
- Context menu interactions
- Frame switching

### UI Components
- Hover interactions
- Challenging DOM elements
- Notification messages
- Floating menu navigation

### Error Handling
- Broken images handling
- Redirect functionality
- HTTP status code testing

## Reporting

### ExtentReports
HTML reports are generated in `reports/extent/`

### Cucumber Reports
Cucumber HTML reports are generated in `target/cucumber-reports/`

### Screenshots
Screenshots are captured on test failures in `reports/screenshots/`

## Parallel Execution
The framework supports parallel execution at method level:
```xml
<suite name="Parallel Suite" parallel="methods" thread-count="3">
```

## Best Practices Implemented
- Page Object Model pattern
- Dependency Injection with PicoContainer
- Fluent interface design
- Comprehensive logging
- Thread-safe WebDriver management
- Configurable wait strategies
- Environment-based configuration
- Data-driven testing approach

## Contributing
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Ensure all tests pass
6. Submit a pull request

## License
This project is licensed under the MIT License.

## Support
For questions and support, please create an issue in the repository.