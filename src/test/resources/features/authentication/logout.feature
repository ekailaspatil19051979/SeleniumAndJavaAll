Feature: User Logout Functionality
  As a logged in user
  I want to logout from the application
  So that I can secure my session

  Background:
    Given I navigate to the login page
    And I login with valid credentials

  @authentication @smoke
  Scenario: Successful logout from secure area
    Given I am on the secure area page
    When I click the logout button
    Then I should be redirected to the login page
    And I should see the logout success message "You logged out of the secure area!"
    And I should see the login form

  @authentication @security
  Scenario: Access secure area after logout should be restricted
    Given I am on the secure area page
    When I click the logout button
    And I navigate back to the secure area URL
    Then I should be redirected to the login page
    And I should not be able to access the secure content