Feature: User Login Authentication
  As a user
  I want to login to the application
  So that I can access secure areas

  Background:
    Given I navigate to the login page

  @authentication @smoke
  Scenario: Successful login with valid credentials
    When I enter username "tomsmith"
    And I enter password "SuperSecretPassword!"
    And I click the login button
    Then I should be redirected to the secure area
    And I should see the success message "You logged into a secure area!"
    And I should see the logout button

  @authentication @negative
  Scenario: Login attempt with invalid username
    When I enter username "invaliduser"
    And I enter password "SuperSecretPassword!"
    And I click the login button
    Then I should remain on the login page
    And I should see the error message "Your username is invalid!"

  @authentication @negative
  Scenario: Login attempt with invalid password
    When I enter username "tomsmith"
    And I enter password "invalidpassword"
    And I click the login button
    Then I should remain on the login page
    And I should see the error message "Your password is invalid!"

  @authentication @negative
  Scenario: Login attempt with empty credentials
    When I enter username ""
    And I enter password ""
    And I click the login button
    Then I should remain on the login page
    And I should see an error message

  @authentication @datadriven
  Scenario Outline: Login with multiple invalid credentials
    When I enter username "<username>"
    And I enter password "<password>"
    And I click the login button
    Then I should remain on the login page
    And I should see the error message "<expectedError>"

    Examples:
      | username     | password             | expectedError             |
      | invaliduser  | SuperSecretPassword! | Your username is invalid! |
      | tomsmith     | wrongpassword        | Your password is invalid! |
      | admin        | admin123             | Your username is invalid! |
      | test@user    | testpass             | Your username is invalid! |